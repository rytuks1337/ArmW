package com.rytis.armw.auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

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
}
