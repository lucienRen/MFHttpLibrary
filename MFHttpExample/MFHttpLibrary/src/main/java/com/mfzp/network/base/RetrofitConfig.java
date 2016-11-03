package com.mfzp.network.base;


import com.mfzp.network.base.interceptors.AddCookiesInterceptor;
import com.mfzp.network.base.interceptors.AddParamsInterceptor;
import com.mfzp.network.base.interceptors.ReceivedCookiesInterceptor;
import com.mfzp.network.utils.MFHttpConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author renxiaoming@mofanghr.com.
 * @time .
 */
public class RetrofitConfig {


    /**
     * @return
     */
    public static Retrofit getRetrofit(MFHttpConfiguration configuration) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(new GsonConverterFactory())
                .baseUrl(configuration.baseURL)
                .client(genericClient(configuration));
        return builder.build();
    }


    /**
     * 配置OKhttp
     *
     * @param
     * @return
     */
    private static OkHttpClient genericClient(final MFHttpConfiguration configuration) {
//        final JSONObject jsonObject = new JSONObject();
//        try {
//            if (configuration.headParams != null) {
//                Iterator<Map.Entry<String, String>> entries = configuration.headParams.entrySet().iterator();
//                while (entries.hasNext()) {
//                    Map.Entry<String, String> entry = entries.next();
//                    jsonObject.put(entry.getKey(), entry.getValue());
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(15, TimeUnit.SECONDS);
        httpClient.addInterceptor(new AddCookiesInterceptor(configuration.context));
        httpClient.addInterceptor(new ReceivedCookiesInterceptor(configuration.context));
        httpClient.addInterceptor(new AddParamsInterceptor(configuration.commParams));
        return httpClient.build();
    }
}
