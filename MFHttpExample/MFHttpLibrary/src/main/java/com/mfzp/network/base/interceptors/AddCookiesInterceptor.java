package com.mfzp.network.base.interceptors;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.mfzp.network.utils.MFHttpConfiguration;
import com.mfzp.network.utils.MFHttpUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.prefs.Preferences;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mofang on 2016/10/25.
 */

public class AddCookiesInterceptor implements Interceptor {


    private Context context;

    public AddCookiesInterceptor(Context context) {
        super();
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        if (MFHttpUtil.getCookieTime(context) > 0l && System.currentTimeMillis() - MFHttpUtil.getCookieTime(context) > 15 * 24 * 60 * 60 * 1000) {
            context.sendBroadcast(new Intent("COM.MFZP.ACTION.TO_LOGIN"));
            return chain.proceed(builder.build());
        }
        HashSet<String> cookies = MFHttpUtil.getCookie(context);
        if (cookies == null) return chain.proceed(builder.build());
        StringBuilder sb = new StringBuilder();
        for (String cookie
                : cookies) {
            sb.append(cookie + ";");
        }
        builder.addHeader("Cookie", sb.toString());
        return chain.proceed(builder.build());
    }
}