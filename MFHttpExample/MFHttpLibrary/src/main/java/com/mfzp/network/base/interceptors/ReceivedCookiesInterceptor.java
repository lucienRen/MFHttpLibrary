package com.mfzp.network.base.interceptors;

import android.content.Context;

import com.mfzp.network.utils.MFHttpUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by mofang on 2016/10/25.
 */

public class ReceivedCookiesInterceptor implements Interceptor {

    private Context context;

    public ReceivedCookiesInterceptor(Context context) {
        super();
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        //这里获取请求返回的cookie
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            List<String> headers = originalResponse.headers("Set-Cookie");
            if (headers == null || headers.size() == 0) return originalResponse;

            for (String str : headers) {
                if (str.contains(";")) {
                    String[] content = str.split(";");
                    cookies.add(content[0]);
                } else {
                    cookies.add(str);
                }
            }
            MFHttpUtil.saveCookies(context, cookies);
        }
        return originalResponse;
    }
}
