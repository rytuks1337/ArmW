package com.rytis.armw.auth;

import android.content.Intent;

public interface OnLoginSuccessListener {
    void onLoginSuccess();

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
