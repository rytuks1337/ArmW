package com.rytis.armw;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

import java.io.IOException;

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        System.out.println("Sending request: " + request);
        System.out.println("Request headers: " + request.headers());
        if (request.body() != null) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            System.out.println("Request body: " + buffer.readUtf8());
        }
        Response response = chain.proceed(request);
        return response;
    }
}