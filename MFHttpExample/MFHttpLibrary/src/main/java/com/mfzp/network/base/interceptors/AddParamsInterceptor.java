package com.mfzp.network.base.interceptors;

import android.text.TextUtils;

import com.mfzp.network.utils.MFHttpUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by mofang on 2016/10/26.
 */

public class AddParamsInterceptor implements Interceptor {
    private Map<String, Map<String, String>> maps;
    private Map<String, String> commonParams;

    public AddParamsInterceptor(Map<String, Map<String, String>> maps) {
        this.maps = maps;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request orgRequest = chain.request();
        if (maps == null || maps.size() == 0) return chain.proceed(orgRequest);
        RequestBody body = orgRequest.body();
        String key = MFHttpUtil.commParamsKey(orgRequest.url().toString());
        if (key.lastIndexOf("/") > 6) key = key.substring(0, key.length() - 1);
        commonParams = maps.get(key);
        StringBuilder paramsBuilder = new StringBuilder();
        if (body != null) {
            RequestBody newBody = null;
            if (body instanceof FormBody) {
                newBody = addParamsToFormBody((FormBody) body, paramsBuilder);
            } else if (body instanceof MultipartBody) {
                newBody = addParamsToMultipartBody((MultipartBody) body, paramsBuilder);
            }
            if (null != newBody) {
                Request newRequest = orgRequest.newBuilder()
                        .url(orgRequest.url())
                        .method(orgRequest.method(), newBody)
                        .build();
                return chain.proceed(newRequest);
            }
        } else {
            if (!TextUtils.isEmpty(orgRequest.method()) && "get".equals(orgRequest.method().toLowerCase())) {
                HttpUrl.Builder authorizedUrlBuilder = orgRequest.url()
                        .newBuilder()
                        .scheme(orgRequest.url().scheme())
                        .host(orgRequest.url().host());
                if (commonParams == null || commonParams.size() == 0)
                    return chain.proceed(orgRequest);
                Iterator iter = commonParams.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    authorizedUrlBuilder.addQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                }
                // 新的请求
                Request newRequest = orgRequest.newBuilder()
                        .method(orgRequest.method(), orgRequest.body())
                        .url(authorizedUrlBuilder.build())
                        .build();
                return chain.proceed(newRequest);
            }
        }
        return chain.proceed(orgRequest);
    }

    /**
     * 为MultipartBody类型请求体添加参数
     *
     * @param body
     * @param paramsBuilder
     * @return
     */
    private MultipartBody addParamsToMultipartBody(MultipartBody body, StringBuilder paramsBuilder) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        //动态添加新的参数
        if (commonParams == null || commonParams.size() == 0) return builder.build();
        Iterator iter = commonParams.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            builder.addFormDataPart(entry.getKey().toString(), entry.getValue().toString());
            paramsBuilder.append("&");
            paramsBuilder.append(entry.getKey().toString() + "=" + entry.getValue());
        }
        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addPart(body.part(i));
        }
        return builder.build();
    }

    /**
     * 为FormBody类型请求体添加参数
     *
     * @param body
     * @param paramsBuilder
     * @return
     */
    private FormBody addParamsToFormBody(FormBody body, StringBuilder paramsBuilder) {
        FormBody.Builder builder = new FormBody.Builder();
        //动态添加新的参数
        if (commonParams == null || commonParams.size() == 0) return builder.build();
        Iterator iter = commonParams.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            builder.add(entry.getKey().toString(), entry.getValue().toString());
            paramsBuilder.append("&");
            paramsBuilder.append(entry.getKey().toString() + "=" + entry.getValue());
        }
        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
            paramsBuilder.append("&");
            paramsBuilder.append(body.name(i));
            paramsBuilder.append("=");
            paramsBuilder.append(body.value(i));
        }

        return builder.build();
    }


}