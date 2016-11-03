package com.mfzp.network;

import android.content.Context;
import android.util.Log;

import com.mfzp.network.base.HttpType;
import com.mfzp.network.utils.MFHttpConfiguration;
import com.mfzp.network.utils.MFHttpUtil;
import com.mfzp.network.utils.RequestConstants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


/**
 * @author renxiaoming@mofanghr.com.
 * @time .
 */
public class MFHttpManager {

    private static final String TAG = MFHttpManager.class.getSimpleName();
    private static MFHttpManager manager;
    protected IMFFactory factory;
    protected Context context;
    private MFHttpConfiguration configuration;

    public static MFHttpManager instance(Context context) {
        if (manager == null) {
            manager = new MFHttpManager(context);
        }
        return manager;
    }

    MFHttpManager(Context context) {
        this.context = context;
    }


    /**
     * 设置配置信息
     *
     * @param config
     */
    public void setConfiguration(MFHttpConfiguration config) {
        this.configuration = config;
        this.factory = new OKHttpFactory(config);
    }


    /**
     * 对外接口
     *
     * @param httpType
     * @param url
     * @param obj
     * @param clazz
     * @param listener
     */
    public void executeByBody(HttpType httpType, String url, JSONObject obj, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        switch (httpType) {
            case POST:
                executePost(url, obj, clazz, listener);
                break;
            case PUT:
                executePut(url, obj, clazz, listener);
                break;
            case DELETE:
                executeDel(url, obj, clazz, listener);
                break;
        }
    }

    /**
     * 该方法是通过绝对路径来请求的
     *
     * @param httpType
     * @param url
     * @param params
     * @param clazz
     * @param listener
     */
    public void executeByAbsoluteUrl(HttpType httpType, String url, String params, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        switch (httpType) {
            case POST:
                executePostByAbsolutUrl(url, clazz, params, listener);
                break;
            case GET:
                executeGetByAbsolutUrl(url, clazz, params, listener);
                break;
        }
    }

    /**
     * 该方法是通过绝对路径来请求的
     *
     * @param httpType
     * @param url
     * @param clazz
     * @param listener
     */
    public void executeByAbsoluteUrl(HttpType httpType, String url, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        switch (httpType) {
            case POST:
                executePostByAbsolutUrl(url, clazz, listener);
                break;
            case GET:
                executeGetByAbsolutUrl(url, clazz, listener);
                break;
        }
    }

    /**
     * 对外接口
     *
     * @param url
     * @param clazz
     * @param listener
     */
    public void executeByBody(String url, JSONObject obj, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        executePost(url, obj, clazz, listener);
    }


    /**
     * 对外接口
     *
     * @param url
     * @param clazz
     * @param listener
     */
    public void execute(String url, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        executeGet(url, clazz, listener);
    }

    /**
     * 对外接口
     *
     * @param httpType
     * @param url
     * @param clazz
     * @param listener
     */
    public void execute(HttpType httpType, String url, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        switch (httpType) {
            case GET:
                executeGet(url, clazz, listener);
                break;
            case PUT:
                executePut(url, clazz, listener);
                break;
            case DELETE:
                executeDel(url, clazz, listener);
                break;
        }
    }


    /**
     * 通用调用
     *
     * @param httpType
     * @param url
     * @param params
     * @param clazz
     * @param listener
     */
    public void executeByParams(HttpType httpType, String url, String params, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        switch (httpType) {
            case GET:
                executeGetByParams(url, clazz, params, listener);
                break;
            case POST:
                executePostByParams(url, clazz, params, listener);
                break;
        }
    }


    /**
     * 通用请求，供外界使用，仅仅用于put请求
     *
     * @param url
     * @param obj
     * @param clazz
     * @param listener
     */
    private void executePut(String url, JSONObject obj, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        executePutByBody(url, obj, clazz, listener);
    }

    /**
     * 通用请求，供外界使用，仅仅用于put请求
     *
     * @param url
     * @param clazz
     * @param listener
     */
    private void executePut(String url, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        executePutByUrl(url, clazz, listener);
    }


    /**
     * 通用请求，供外界使用，仅仅用于Del请求
     *
     * @param url
     * @param clazz
     * @param listener
     */
    private void executeDel(String url, JSONObject obj, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        executeDeleteByBody(url, obj, clazz, listener);
    }

    /**
     * 通用请求，供外界使用，仅仅用于Del请求
     *
     * @param url
     * @param clazz
     * @param listener
     */
    private void executeDel(String url, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        executeDeleteByUrl(url, clazz, listener);
    }


    /**
     * 通用请求，供外界使用，仅仅用于post请求
     *
     * @param url
     * @param obj
     * @param clazz
     * @param listener
     */
    private void executePost(String url, JSONObject obj, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executePostByBody  url: " + configuration.baseURL + url + "   params:" + obj);
        if (!MFHttpUtil.isConnected(context)) {
            if (listener != null)
                listener.callback(null, RequestConstants.EXCEPITON_NETWORK_DISABLE, "亲，您没有连接网络...");
        } else
            factory.executePostByBody(url, obj, clazz, listener);
    }

    /**
     * 通用请求，供外界使用，仅仅用于get请求
     *
     * @param url
     * @param clazz
     * @param listener
     */
    private void executeGet(String url, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executeGetByUrl  url: " + configuration.baseURL + url);
        if (!MFHttpUtil.isConnected(context)) {
            if (listener != null)
                listener.callback(null, RequestConstants.EXCEPITON_NETWORK_DISABLE, "亲，您没有连接网络...");
        } else
            factory.executeGetByUrl(url, clazz, listener);
    }

    /**
     * 私有请求，供executePut使用
     *
     * @param url
     * @param clazz
     * @param listener
     */
    private void executePutByUrl(String url, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executePutByUrl  url: " + configuration.baseURL + url);
        if (!MFHttpUtil.isConnected(context)) {
            if (listener != null)
                listener.callback(null, RequestConstants.EXCEPITON_NETWORK_DISABLE, "亲，您没有连接网络...");
        } else
            factory.executePutByUrl(url, clazz, listener);
    }

    /**
     * 私有请求，供executePut使用
     *
     * @param url
     * @param obj
     * @param clazz
     * @param listener
     */
    private void executePutByBody(String url, JSONObject obj, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executePutByBody  url: " + configuration.baseURL + url + "   params:" + obj);
        if (!MFHttpUtil.isConnected(context)) {
            if (listener != null)
                listener.callback(null, RequestConstants.EXCEPITON_NETWORK_DISABLE, "亲，您没有连接网络...");
        } else
            factory.executePutByBody(url, obj, clazz, listener);
    }

    /**
     * 私有请求，executeDel
     *
     * @param url
     * @param obj
     * @param clazz
     * @param listener
     */
    private void executeDeleteByBody(String url, JSONObject obj, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executeDelByBody  url: " + configuration.baseURL + url + "   params:" + obj);
        if (!MFHttpUtil.isConnected(context)) {
            if (listener != null)
                listener.callback(null, RequestConstants.EXCEPITON_NETWORK_DISABLE, "亲，您没有连接网络...");
        } else
            factory.executeDelByBody(url, obj, clazz, listener);
    }

    /**
     * 私有请求，executeDel
     *
     * @param url
     * @param clazz
     * @param listener
     */
    private void executeDeleteByUrl(String url, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executeDelByUrl  url: " + configuration.baseURL + url);
        if (!MFHttpUtil.isConnected(context)) {
            if (listener != null)
                listener.callback(null, RequestConstants.EXCEPITON_NETWORK_DISABLE, "亲，您没有连接网络...");
        } else
            factory.executeDelByUrl(url, clazz, listener);
    }

    /**
     * executeGetByParams
     *
     * @param url
     * @param params
     * @param listener
     */
    private void executeGetByParams(String url, Class clazz, String params, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executeGetByParams  url: " + configuration.baseURL + url);
        if (!MFHttpUtil.isConnected(context)) {
            if (listener != null)
                listener.callback(null, RequestConstants.EXCEPITON_NETWORK_DISABLE, "亲，您没有连接网络...");
        } else
            factory.executeGetByParams(url, params, clazz, listener);
    }

    /**
     * 该方法是针对绝对的URL处理的，主要get请求方式
     *
     * @param url
     * @param clazz
     * @param listener
     */
    private void executeGetByAbsolutUrl(String url, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executePostByBody  url: " + configuration.baseURL + url);
        factory.executeGetByAbsolutUrl(url, clazz, listener);
    }

    /**
     * 该方法是针对绝对的URL处理的，主要get请求方式
     *
     * @param url
     * @param clazz
     * @param listener
     */
    private void executePostByAbsolutUrl(String url, Class clazz, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executePostByBody  url: " + configuration.baseURL + url);
        factory.executePostByAbsolutUrl(url, clazz, listener);
    }

    /**
     * 该方法是针对绝对的URL处理的，主要get请求方式
     *
     * @param url
     * @param clazz
     * @param params
     * @param listener
     */
    private void executeGetByAbsolutUrl(String url, Class clazz, String params, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executePostByBody  url: " + configuration.baseURL + url + "   params:" + params);
        factory.executeGetByAbsolutUrl(url, params, clazz, listener);
    }

    /**
     * 该方法是针对绝对的URL处理的，主要get请求方式
     *
     * @param url
     * @param clazz
     * @param params
     * @param listener
     */
    private void executePostByAbsolutUrl(String url, Class clazz, String params, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executePostByBody  url: " + configuration.baseURL + url + "   params:" + params);
        factory.executePostByAbsolutUrl(url, params, clazz, listener);
    }

    /**
     * executePostByParams
     *
     * @param url
     * @param params
     * @param listener
     */
    private void executePostByParams(String url, Class clazz, String params, IMFFactory.MFHttpCallBackListener listener) {
        Log.d(TAG, "executeGetByParams  url: " + configuration.baseURL + url);
        if (!MFHttpUtil.isConnected(context)) {
            if (listener != null)
                listener.callback(null, RequestConstants.EXCEPITON_NETWORK_DISABLE, "亲，您没有连接网络...");
        } else
            factory.executePostByParams(url, params, clazz, listener);
    }
}
