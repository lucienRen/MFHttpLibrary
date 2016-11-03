package com.mfzp.network;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.mfzp.network.base.MFBaseRespone;
import com.mfzp.network.base.RetrofitConfig;
import com.mfzp.network.utils.MFHttpConfiguration;
import com.mfzp.network.utils.RequestConstants;

import org.json.JSONObject;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author renxiaoming@mofanghr.com.
 * @time .
 */
public class OKHttpFactory implements IMFFactory {
    private static final String TAG = MFHttpManager.class.getSimpleName();
    private Gson gson;
    private MFRequstServes retrofit;
    private MFHttpConfiguration configuration;


    public OKHttpFactory(MFHttpConfiguration configuration) {
        this.gson = new Gson();
        this.configuration = configuration;
        retrofit = RetrofitConfig.getRetrofit(configuration).create(MFRequstServes.class);
    }


    /**
     * 通过body参数来请求  ######通用########
     *
     * @param clazz
     * @param listener
     */
    public void executePostByBody(final String path, final JSONObject obj, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executePostByBody(path, obj).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(path, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });
    }


    /**
     * 通过URL来请求GET请求  ######通用########
     *
     * @param clazz
     * @param listener
     */
    public void executeGetByUrl(final String url, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executeGetByUrl(configuration.baseURL + url).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(url, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });

    }


    /**
     * 通过body来请求put请求  ######通用########
     *
     * @param clazz
     * @param listener
     */
    public void executePutByBody(final String path, final JSONObject obj, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executePutByBody(path, obj).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(path, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });
    }


    /**
     * 通过url来请求put请求  ######通用########
     *
     * @param url
     * @param clazz
     * @param listener
     */
    public void executePutByUrl(final String url, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executePutByUrl(configuration.baseURL + url).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(url, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });
    }

    /**
     * 通过绝对的URL来请求delete请求  ######通用########
     *
     * @param clazz
     * @param listener
     */
    public void executeDelByUrl(final String url, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executeDELByUrl(configuration.baseURL + url).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(url, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });
    }

    /**
     * 通过绝对的URL来请求delete请求  ######通用########
     *
     * @param clazz
     * @param listener
     */
    public void executeDelByBody(final String path, final JSONObject obj, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executeDELByBody(path, obj).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(path, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });
    }

    /**
     * 通过通过参数来请求post  ######通用########
     *
     * @param path
     * @param params
     * @param clazz
     * @param listener
     */
    public void executePostByParams(final String path, String params, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executePostByParams(path, params).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(path, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });
    }

    /**
     * 通过绝对的URL请求数据
     *
     * @param url
     * @param params
     * @param clazz
     * @param listener
     */
    @Override
    public void executePostByAbsolutUrl(final String url, String params, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executePostByURL(url, params).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(url, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });
    }

    /**
     * 通过绝对的URL请求数据
     *
     * @param url
     * @param
     * @param clazz
     * @param listener
     */
    @Override
    public void executeGetByAbsolutUrl(final String url, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executeGetByURL(url).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(url, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });
    }

    /**
     * 通过绝对的URL请求数据
     *
     * @param url
     * @param
     * @param clazz
     * @param listener
     */
    @Override
    public void executePostByAbsolutUrl(final String url, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executePostByURL(url).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(url, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });
    }

    /**
     * 通过绝对的URL请求数据
     *
     * @param url
     * @param params
     * @param clazz
     * @param listener
     */
    @Override
    public void executeGetByAbsolutUrl(final String url, String params, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executeGetByURL(url, params).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(url, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });
    }


    /**
     * 通过通过参数来请求get ######通用########
     *
     * @param path
     * @param params
     * @param clazz
     * @param listener
     */
    public void executeGetByParams(final String path, String params, final Class clazz, final MFHttpCallBackListener listener) {
        retrofit.executeGetByParams(path, params).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                handleResult(path, response, clazz, listener);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                handleException(t, listener);
            }
        });
    }


    /**
     * 处理请求结果
     *
     * @param response
     * @param listener
     */

    public void handleResult(String key, Response<JSONObject> response, Class
            clazz, MFHttpCallBackListener listener) {
        if (listener == null) return;
        try {
            if (response == null) {
                listener.callback(null, RequestConstants.UNDEFINED_CODE, "系统出现异常，请稍后再试！");
                return;
            }
            if (response.isSuccessful()) {
                Log.d(TAG, "HandleResult code: " + response.body().getInt("code") + "\nrequest:  " + response.raw().networkResponse().request().toString() + "\nrespone: " + response.body().get("data").toString());
                if (clazz != null && response.body().getInt("code") == 0) {
                    listener.callback(gson.fromJson(response.body().get("data").toString(), clazz), response.body().getInt("code"), response.body().getString("msg"));
                } else {
                    listener.callback(response.body().get("data"), response.body().getInt("code"), response.body().getString("msg"));
                }
            } else {
                MFBaseRespone mfRespone = gson.fromJson(response.errorBody().string(), MFBaseRespone.class);
                listener.callback(null, mfRespone.code, mfRespone.msg);
                Log.d(TAG, "HandleResult code: " + mfRespone.code + "\nrequest:" + response.raw().networkResponse().request().toString() + "\nrespone: " + mfRespone.msg);
            }
        } catch (Exception e) {
            listener.callback(null, RequestConstants.UNDEFINED_CODE, "系统出现异常，请稍后再试！");
            Log.e(TAG, "handleResult   onFailure  " + e.getMessage());
        }
    }

    /**
     * 处理http请求异常
     *
     * @param t
     * @param listener
     */
    public void handleException(Throwable t, final MFHttpCallBackListener listener) {
        Log.e(TAG, "handleException   onFailure  " + t.getMessage());
        if (listener == null) return;
        if (!TextUtils.isEmpty(t.getMessage()) && (t.getMessage().toLowerCase().contains("failed to connect") ||
                t.getMessage().toLowerCase().contains("timeout") ||
                t.getMessage().toLowerCase().contains("unable to resolve host"))) {
            listener.callback(null, RequestConstants.EXCEPITON_TIME_OUT, "当前网络存在问题");
        } else {
            listener.callback(null, RequestConstants.UNDEFINED_CODE, "系统出现异常，请稍后再试！");
        }
    }

}
