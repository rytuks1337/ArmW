package com.rytis.armw.dataModels;

import java.lang.reflect.Array;
import java.util.List;

public class UserRegisterModel {
    UserRegisterData u_data;
    UserRegisterModelResp u_data_resp;

    public static class UserRegisterData{


        public UserRegisterData(String vardas, String pavarde, String el_pastas, String slaptazodis, Integer amzius) {
            this.vardas = vardas;
            this.pavarde = pavarde;
            this.el_pastas = el_pastas;
            this.slaptazodis = slaptazodis;
            this.amzius = amzius;
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

        public Integer getAmzius() {
            return amzius;
        }

        public void setAmzius(Integer amzius) {
            this.amzius = amzius;
        }

        String vardas, pavarde, el_pastas, slaptazodis;
        Integer amzius;
    }

    public static class UserRegisterModelResp{


        public UserRegisterModelResp(String id, String vardas, String pavarde, String el_pastas, String slaptazodis, Integer amzius) {
            this.id = id;
            this.vardas = vardas;
            this.pavarde = pavarde;
            this.el_pastas = el_pastas;
            this.slaptazodis = slaptazodis;
            this.amzius = amzius;
        }

        String id;
        String vardas;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public Integer getAmzius() {
            return amzius;
        }

        public void setAmzius(Integer amzius) {
            this.amzius = amzius;
        }

        String pavarde;
        String el_pastas;
        String slaptazodis;
        Integer amzius;


    }
    public static class UserRegisterModelRespErr{
        public UserRegisterModelRespErr(List<ErrorResponse> errors) {
            this.errors = errors;
        }

        public List<ErrorResponse> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorResponse> errors) {
            this.errors = errors;
        }

        List<ErrorResponse> errors;
        public static class ErrorResponse {
            public ErrorResponse(String type, String value, String msg, String path, String location) {
                this.type = type;
                this.value = value;
                this.msg = msg;
                this.path = path;
                this.location = location;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            private String type;
            private String value;
            private String msg;
            private String path;
            private String location;


            @Override
            public String toString() {
                return "ErrorResponse{" +
                        "type='" + type + '\'' +
                        ", value='" + value + '\'' +
                        ", msg='" + msg + '\'' +
                        ", path='" + path + '\'' +
                        ", location='" + location + '\'' +
                        '}';
            }
        }

    }
}
