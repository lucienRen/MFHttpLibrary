package com.mfzp.network;


import org.json.JSONObject;

import retrofit2.Response;

/**
 * Created by mofang on 2016/10/18.
 */

public interface IMFFactory {

    /**
     * body参数的post请求
     *
     * @param path
     * @param obj
     * @param clazz
     * @param listener
     */
    void executePostByBody(final String path, final JSONObject obj, final Class clazz, final MFHttpCallBackListener listener);

    /**
     * get请求方式
     *
     * @param url
     * @param clazz
     * @param listener
     */
    void executeGetByUrl(final String url, final Class clazz, final MFHttpCallBackListener listener);

    /**
     * body参数的put请求
     *
     * @param path
     * @param obj
     * @param clazz
     * @param listener
     */
    void executePutByBody(final String path, final JSONObject obj, final Class clazz, final MFHttpCallBackListener listener);

    /**
     * put请求方式
     *
     * @param url
     * @param clazz
     * @param listener
     */
    void executePutByUrl(final String url, final Class clazz, final MFHttpCallBackListener listener);

    /**
     * delete请求方式
     *
     * @param url
     * @param clazz
     * @param listener
     */
    void executeDelByUrl(final String url, final Class clazz, final MFHttpCallBackListener listener);

    /**
     * body参数的delete请求
     *
     * @param path
     * @param obj
     * @param clazz
     * @param listener
     */
    void executeDelByBody(final String path, final JSONObject obj, final Class clazz, final MFHttpCallBackListener listener);

    /**
     * params 参数的get请求
     *
     * @param path
     * @param params
     * @param listener
     */
    void executeGetByParams(final String path, final String params, final Class clazz, MFHttpCallBackListener listener);

    /**
     * params 参数的Post请求
     *
     * @param path
     * @param params
     * @param listener
     */
    void executePostByParams(final String path, String params, final Class clazz, final MFHttpCallBackListener listener);

    /**
     * 针对局对路径URL
     *
     * @param url
     * @param params
     */
    void executeGetByAbsolutUrl(String url, String params, final Class clazz, final MFHttpCallBackListener listener);

    /**
     * 针对局对路径URL
     *
     * @param url
     * @param params
     */
    void executePostByAbsolutUrl(String url, String params, final Class clazz, final MFHttpCallBackListener listener);

    /**
     * 针对局对路径URL
     *
     * @param url
     * @param
     */
    void executeGetByAbsolutUrl(String url,  final Class clazz, final MFHttpCallBackListener listener);

    /**
     * 针对局对路径URL
     *
     * @param url
     * @param
     */
    void executePostByAbsolutUrl(String url, final Class clazz, final MFHttpCallBackListener listener);

    /**
     * 请求结果的处理
     *
     * @param key
     * @param response
     * @param clazz
     * @param listener
     */
    void handleResult(String key, Response<JSONObject> response, Class clazz, MFHttpCallBackListener listener);

    /**
     * 请求的异常处理
     *
     * @param t
     * @param listener
     */
    void handleException(Throwable t, final MFHttpCallBackListener listener);

    /**
     * 对应的请求回调
     *
     * @param
     */
    interface MFHttpCallBackListener<T> {
        void callback(T result, int code, String message);
    }
}
