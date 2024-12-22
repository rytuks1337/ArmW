package com.rytis.armw;

import android.content.Context;

import androidx.annotation.NonNull;

import com.rytis.armw.dataModels.UserloginModel;
import com.rytis.armw.routes.AuthenticationRoute;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.rytis.armw.auth.TokenManager;

import java.io.IOException;

public class Retrofit_Pre {
    Retrofit retrofit;
    private final Context context;
    final String baseUrl = "http://192.168.0.121:3000/";
    //final String baseUrl = "https://webhook.site/8a653772-1361-4e37-99c9-041c70cade4e/";
    public Retrofit getRetrofit(boolean includeToken) {
        if (includeToken) {
            // Create a new Retrofit instance with the authentication header
            String token = TokenManager.getJwtToken(context);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new AuthenticationInterceptor(token));
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        } else {
            // Use the existing Retrofit instance without the authentication header
            if (retrofit == null) {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(new LoggingInterceptor());
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();
            }
        }
        return retrofit;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Retrofit_Pre(Context context) {
        this.context = context;
    }

    // AuthenticationInterceptor class
    private static class AuthenticationInterceptor implements Interceptor {
        private final String token;

        public AuthenticationInterceptor(String token) {
            this.token = token;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder request = chain.request().newBuilder();
            if(token==null){
                return chain.proceed(request.build());
            }
            request.header("Authorization", token);
            return chain.proceed(request.build());
        }
    }
}
