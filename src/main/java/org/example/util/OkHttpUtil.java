package org.example.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {

    private static final OkHttpClient httpClient = new OkHttpClient()
            .newBuilder().writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(30, TimeUnit.SECONDS).build();

    private static final MediaType jsonMediaType = MediaType.parse("application/json;charset=UTF-8");

    private static Headers toHeader(Map<String, String> header) {
        if (header == null) {
            return Headers.of();
        }
        return Headers.of(header);
    }

    public static Response getToResponse(String url, Map<String, String> param, Map<String, String> header) throws IOException {
        Request request;
        Request.Builder requestBuilder = new Request.Builder().headers(toHeader(header));
        if (param != null) {
            HttpUrl httpUrl = HttpUrl.parse(url);
            if (httpUrl == null) {
                throw new RuntimeException("request url error: " + url);
            }
            HttpUrl.Builder urlBuilder = httpUrl.newBuilder();
            param.forEach(urlBuilder::addQueryParameter);
            request = requestBuilder.url(urlBuilder.build()).build();
        } else {
            request = requestBuilder.url(url).build();
        }
        Call r = httpClient.newCall(request);
        return r.execute();
    }

    public static Response postToResponse(String url, Object param, Map<String, String> header) throws IOException {
        Request request = new Request.Builder().url(url).headers(toHeader(header))
                .post(RequestBody.create(jsonMediaType, param instanceof String ? (String) param :
                        JSON.toJSONString(param))).build();
        Call r = httpClient.newCall(request);
        return r.execute();
    }

    public static Response postToResponseByForm(String url, Map<String, String> param, Map<String, String> header) throws IOException {
        FormBody.Builder build = new FormBody.Builder();
        param.forEach(build::add);
        Request request = new Request.Builder().url(url).headers(toHeader(header))
                .post(build.build()).build();
        Call r = httpClient.newCall(request);
        return r.execute();
    }

    public static String getByRest(String url) throws IOException {
        ResponseBody body = getToResponse(url, null, null).body();
        return body != null ? body.string() : null;
    }

    public static String getByRest(String url, Map<String, String> param) throws IOException {
        ResponseBody body = getToResponse(url, param, null).body();
        return body != null ? body.string() : null;
    }

    public static String getByRest(String url, Map<String, String> param, Map<String, String> header) throws IOException {
        ResponseBody body = getToResponse(url, param, header).body();
        return body != null ? body.string() : null;
    }

    public static String postByRest(String url) throws IOException {
        ResponseBody body = postToResponse(url, null, null).body();
        return body != null ? body.string() : null;
    }

    public static String postByRest(String url, Object param) throws IOException {
        ResponseBody body = postToResponse(url, param, null).body();
        return body != null ? body.string() : null;
    }

    public static String postByForm(String url, Map<String, String> param) throws IOException {
        ResponseBody body = postToResponseByForm(url, param, null).body();
        return body != null ? body.string() : null;
    }

    public static String postByRest(String url, Object param, Map<String, String> header) throws IOException {
        ResponseBody body = postToResponse(url, param, header).body();
        return body != null ? body.string() : null;
    }

    public static String postByForm(String url, Map<String, String> param, Map<String, String> header) throws IOException {
        ResponseBody body = postToResponseByForm(url, param, header).body();
        return body != null ? body.string() : null;
    }
}