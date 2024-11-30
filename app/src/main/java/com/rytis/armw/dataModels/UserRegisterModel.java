package com.rytis.armw.dataModels;

import java.lang.reflect.Array;
import java.util.List;

public class UserRegisterModel {
    UserRegisterData u_data;
    UserRegisterModelResp u_data_resp;

    public static class UserRegisterData{


        public UserRegisterData(String vardas, String pavarde, String el_pastas, String slaptazodis, String amzius, String lytis) {
            this.vardas = vardas;
            this.pavarde = pavarde;
            this.el_pastas = el_pastas;
            this.slaptazodis = slaptazodis;
            this.amzius = amzius;
            this.lytis = lytis;
        }


        public String getVardas() {
            return vardas;
        }

        public void setVardas(String vardas) {
            this.vardas = vardas;
        }

        public String getPavarde() {
            return pavarde;
        }

        public void setPavarde(String pavarde) {
            this.pavarde = pavarde;
        }

        public String getEl_pastas() {
            return el_pastas;
        }

        public void setEl_pastas(String el_pastas) {
            this.el_pastas = el_pastas;
        }

        public String getSlaptazodis() {
            return slaptazodis;
        }

        public void setSlaptazodis(String slaptazodis) {
            this.slaptazodis = slaptazodis;
        }

        public String getAmzius() {
            return amzius;
        }

        public void setAmzius(String amzius) {
            this.amzius = amzius;
        }

        public String getGender() {
            return lytis;
        }

        public void setGender(String gender) {
            this.lytis = gender;
        }


        String vardas;
        String pavarde;
        String el_pastas;
        String slaptazodis;
        String amzius;
        String lytis;




    }

    public static class UserRegisterModelResp {


        public static class SuccessResponse {
            private String message;

            public SuccessResponse(String message) {
                this.message = message;
            }

            public String getMessage() {
                return message;
            }
        }

    }
    public static class ValidationErrorMultipleResponse {
        private List<ErrorDetails> errors;

        public ValidationErrorMultipleResponse(List<ErrorDetails> errors) {
            this.errors = errors;
        }

        public List<ErrorDetails> getErrors() {
            return errors;
        }

        public static class ErrorDetails {
            private String type;
            private String msg;
            private String path;
            private String location;

            public ErrorDetails(String type, String msg, String path, String location) {
                this.type = type;
                this.msg = msg;
                this.path = path;
                this.location = location;
            }

            public String getType() {
                return type;
            }

            public String getMsg() {
                return msg;
            }

            public String getPath() {
                return path;
            }

            public String getLocation() {
                return location;
            }
        }
    }

    public static class ValidationErrorSingleResponse {
        private String error;

        public ValidationErrorSingleResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
