package com.rytis.armw.dataModels;

public class UserloginModel {

    UserLoginData u_data;
    UserLoginDataResp u_data_resp;
    public static class UserLoginData {

        public UserLoginData(String email, String pass) {
            this.el_pastas = email;
            this.slaptazodis = pass;
        }
        public String getEmail() {
            return el_pastas;
        }

        public void setEmail(String email) {
            this.el_pastas = email;
        }

        public String getPass() {
            return slaptazodis;
        }

        public void setPass(String pass) {
            this.slaptazodis = pass;
        }



        String el_pastas, slaptazodis;


    }
    public static class UserLoginDataResp{
        public UserLoginDataResp(String accessToken) {
            this.accessToken = accessToken;
        }
        public String getJwt_token() {
            return accessToken;
        }
        public void setJwt_token(String accessToken) {
            this.accessToken = accessToken;
        }

        public String accessToken;
    }
}
