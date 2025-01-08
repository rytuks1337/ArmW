package com.rytis.armw.auth;

import android.content.Intent;

public interface OnActionSuccessListener {
    void onLoginSuccess();
    void onChoiceSuccess(Intent i);

}
