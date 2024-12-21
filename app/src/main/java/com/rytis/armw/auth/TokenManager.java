package com.rytis.armw.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.routes.AuthenticationRoute;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TokenManager {
    private static final String SEC_PREF = "secure_preferences";
    private static final String TOKEN = "accessToken";
    private static final String EXP_KEY = "tokenExpiration";

    // Save token for later use (also extract expiration time)
    public static void saveJwtToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SEC_PREF, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TOKEN, token);

        editor.putLong(EXP_KEY, getExpirationTime(token));

        editor.apply();
    }

    // Get token
    public static String getJwtToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SEC_PREF, Context.MODE_PRIVATE);
        long expirationTime = sharedPreferences.getLong(EXP_KEY, 0);

        // Check if the token is expired
        if (System.currentTimeMillis() > expirationTime) {
            clearJwtToken(context);
            return null;
        }
        return sharedPreferences.getString(TOKEN, null);
    }

    // Clear token
    public static void clearJwtToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SEC_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(TOKEN);
        editor.remove(EXP_KEY);

        editor.apply();
    }

    // Check token
    public static boolean isTokenValid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SEC_PREF, Context.MODE_PRIVATE);
        long expirationTime = sharedPreferences.getLong(EXP_KEY, 0);

        return System.currentTimeMillis() <= expirationTime;
    }
    public static long getExpirationTime(String token) {
        DecodedJWT jwt = JWT.decode(token);

        // Get the expiration time from the payload (exp claim)
        return jwt.getExpiresAt().getTime();
    }
    //Using interface to interact with the callback,
    public interface TokenValidationCallback {
        void onTokenValidationResult(boolean isValid);
    }
    public static boolean isTokenValidWWW(Context context, TokenValidationCallback callback) {
        Retrofit_Pre preRetrofit = new Retrofit_Pre(context);
        Retrofit retrofit = preRetrofit.getRetrofit(true);

        AuthenticationRoute authInstance  = retrofit.create(AuthenticationRoute.class);

        String authToken = getJwtToken(context);

        // Make the GET request
        Call<ResponseBody> call = authInstance.getAuthTest(authToken);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        callback.onTokenValidationResult(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.onTokenValidationResult(false);
                    }
                } else {
                    Log.e("API_ERROR", "Request failed with code: " + response.code());
                    callback.onTokenValidationResult(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle network errors or other issues
                Log.e("API_ERROR", "Request failed: " + t.getMessage());
                callback.onTokenValidationResult(false);
            }
        });
        return false;
    }
}
