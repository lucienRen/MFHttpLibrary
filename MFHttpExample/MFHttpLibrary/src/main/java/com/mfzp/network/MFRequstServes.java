package com.mfzp.network;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author renxiaoming@mofanghr.com.
 * @time .
 */
public interface MFRequstServes {


    /**
     * 埋点
     *
     * @author renxiaoming@mofanghr.com.
     * @time .
     */
    @POST("/logs/app")
    Call<JSONObject> statistice(@Body JSONObject obj);

    /**
     * 埋点
     *
     * @author renxiaoming@mofanghr.com.
     * @time .
     */
    @POST("/logs/app")
    Call<JSONObject> statistice(@Body JSONArray array);


    /**
     * 通过body参数来请求  ######通用接口########
     *
     * @param obj
     * @return
     */
    @FormUrlEncoded
    @POST("{path}")
    Call<JSONObject> executePostByBody(@Path("path") String path, @Body JSONObject obj);

    /**
     * 通过URL来请求GET请求  ######通用接口########
     *
     * @param
     * @return
     */
    @GET
    Call<JSONObject> executeGetByUrl(@Url String url);


    /**
     * 通过body来请求put请求  ######通用接口########
     *
     * @param obj
     * @return
     */
    @PUT("{path}")
    Call<JSONObject> executePutByBody(@Path("path") String path, @Body JSONObject obj);

    /**
     * 通过body来请求put请求  ######通用接口########
     *
     * @param url
     * @return
     */
    @PUT
    Call<JSONObject> executePutByUrl(@Url String url);


    /**
     * 通过绝对的URL来请求delete请求  ######通用接口########
     *
     * @param url
     * @return
     */
    @DELETE
    Call<JSONObject> executeDELByUrl(@Url String url);

    /**
     * 通过绝对的body来请求delete请求  ######通用接口########
     *
     * @param path
     * @return
     */
    @HTTP(method = "DELETE", path = "{path}", hasBody = true)
    Call<JSONObject> executeDELByBody(@Path("path") String path, @Body JSONObject obj);

    /**
     * 通过参数来请求POST请求 ######通用接口########
     *
     * @param path
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("{path}")
    Call<JSONObject> executePostByParams(@Path("path") String path, @Field("params") String params);

    /**
     * 通过参数来请求POST请求 ######通用接口########
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST
    Call<JSONObject> executePostByURL(@Url String url, @Field("params") String params);


    /**
     * 通过参数来请求GET请求 ######通用接口########
     *
     * @param params
     * @return
     */
    @GET
    Call<JSONObject> executeGetByURL(@Url String url, @Query("params") String params);

    /**
     * 通过参数来请求POST请求 ######通用接口########
     *
     * @return
     */
    @FormUrlEncoded
    @POST
    Call<JSONObject> executePostByURL(@Url String url);


    /**
     * 通过参数来请求GET请求 ######通用接口########
     *
     * @return
     */
    @GET
    Call<JSONObject> executeGetByURL(@Url String url);
    /**
     * 通过参数来请求GET请求 ######通用接口########
     *
     * @param path
     * @param params
     * @return
     */
    @GET("{path}")
    Call<JSONObject> executeGetByParams(@Path("path") String path, @Query("params") String params);

}
