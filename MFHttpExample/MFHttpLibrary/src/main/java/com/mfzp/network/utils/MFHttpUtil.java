package com.mfzp.network.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.HashSet;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MFHttpUtil {

    /**
     * 网络类型
     */
    public static final int NETWORK_TYPE_NONE = 0;
    public static final int NETWORK_TYPE_MOBILE = 1;    // 非CMNET、CMWAP
    public static final int NETWORK_TYPE_MOBILE_CMNET = 2;
    public static final int NETWORK_TYPE_MOBILE_CMWAP = 3;
    public static final int NETWORK_TYPE_WIFI = 4;

    private MFHttpUtil() {
    }

    /**
     * 网络是否已连接
     *
     * @return true 已连接；false 未连接
     */
    public static boolean isConnected(Context context) {
        return isWiFiConnected(context) || isMobileConnected(context);
    }

    /**
     * WiFi是否已连接
     *
     * @return true 已连接；false 未连接
     */
    public static boolean isWiFiConnected(Context context) {
        return getNetworkType(context) == NETWORK_TYPE_WIFI;
    }

    /**
     * 移动数据网络是否已连接
     *
     * @return true 已连接；false 未连接
     */
    public static boolean isMobileConnected(Context context) {
        return getNetworkType(context) == NETWORK_TYPE_MOBILE_CMNET
                || getNetworkType(context) == NETWORK_TYPE_MOBILE_CMWAP
                || getNetworkType(context) == NETWORK_TYPE_MOBILE;
    }

    /**
     * 获取网络类型
     */
    public static int getNetworkType(Context context) {
        int networkType = NETWORK_TYPE_NONE;
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            int type = networkInfo.getType();
            if (type == ConnectivityManager.TYPE_MOBILE) {
                networkType = NETWORK_TYPE_MOBILE;
                String extraInfo = networkInfo.getExtraInfo();
                if ("cmnet".equalsIgnoreCase(extraInfo)) {
                    networkType = NETWORK_TYPE_MOBILE_CMNET;
                } else if ("cmwap".equalsIgnoreCase(extraInfo)) {
                    networkType = NETWORK_TYPE_MOBILE_CMWAP;
                }
            } else if (type == ConnectivityManager.TYPE_WIFI) {
                networkType = NETWORK_TYPE_WIFI;
            }
        }
        return networkType;
    }

    /**
     * 缓存COOKIES
     *
     * @param context
     * @param value
     */
    public static void saveCookies(Context context, HashSet value) {
        SharedPreferences spCookies = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spCookies.edit();
        editor.putStringSet("cookie", value);
        if (value != null)
            editor.putLong("cookie_time", System.currentTimeMillis());
        else
            editor.putLong("cookie_time", 0l);
        editor.commit();
    }

    /**
     * 获取COOKIESTime
     *
     * @param context
     * @return
     */
    public static Long getCookieTime(Context context) {
        SharedPreferences spCookies = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        return spCookies.getLong("cookie_time", 0l);
    }

    /**
     * 获取COOKIES
     *
     * @param context
     * @return
     */
    public static HashSet getCookie(Context context) {
        SharedPreferences spCookies = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        return (HashSet) spCookies.getStringSet("cookie", null);
    }

    /**
     * 通过URL获取对应公共参数的key
     *
     * @param key
     * @return
     */
    public static String commParamsKey(String key) {
        Pattern p = Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+/?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(key);
        matcher.find();
        return matcher.group();
    }
}
