package com.mfzp.network.base;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 *
 * @author renxiaoming@mofanghr.com.
 * @time .
 */
public class GsonConverterFactory extends Converter.Factory {
    public static GsonConverterFactory create() {
        return new GsonConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new JsonResponseBodyConverter<JSONObject>();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new JsonRequestBodyConverter<JSONObject>();
    }

    final class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

        JsonRequestBodyConverter() {

        }

        public RequestBody convert(T value) throws IOException {
            return RequestBody.create(MEDIA_TYPE, value.toString());
        }
    }

    final class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

        JsonResponseBodyConverter() {

        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            JSONObject jsonObj;
            try {
                jsonObj = new JSONObject(value.string());
                return (T) jsonObj;
            } catch (JSONException e) {
                return null;
            }
        }
    }
}
