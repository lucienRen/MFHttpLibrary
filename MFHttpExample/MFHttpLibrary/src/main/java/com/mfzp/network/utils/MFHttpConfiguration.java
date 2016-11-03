package com.mfzp.network.utils;

import android.content.Context;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mofang on 2016/10/21.
 */

public class MFHttpConfiguration {

    private static MFHttpConfiguration mfHttpConfiguration;
    /**
     * Set the API base URL
     */
    public String baseURL;
    public Map<String, Map<String, String>> commParams = new HashMap<>();
    public Context context;

    public static MFHttpConfiguration Builder(Context context) {
        if (mfHttpConfiguration == null) {
            mfHttpConfiguration = new MFHttpConfiguration(context);
        }
        return mfHttpConfiguration;
    }

    MFHttpConfiguration(Context context) {
        this.context = context;
    }

    public MFHttpConfiguration setBaseUrl(String baseUrl) {
        this.baseURL = baseUrl;
        return this;
    }

    public MFHttpConfiguration addCommParams(String key, Map<String, String> params) {
        if (TextUtils.isEmpty(key) || params == null || params.size() == 0) return this;
        this.commParams.put(MFHttpUtil.commParamsKey(key), params);
        return this;
    }
}
