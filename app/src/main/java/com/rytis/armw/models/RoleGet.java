package com.rytis.armw.models;

public class RoleGet {
    private Integer id;
    private Integer vartotoju_ID;
    private String vartotojo_tipas;

    RoleGet(Integer id, Integer vartotoju_ID, String vartotojo_tipas) {
        this.id = id;
        this.vartotoju_ID = vartotoju_ID;
        this.vartotojo_tipas = vartotojo_tipas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getVartotoju_ID() {
        return vartotoju_ID;
    }

    public void setVartotoju_ID(Integer vartotoju_ID) {
        this.vartotoju_ID = vartotoju_ID;
    }
    public String getVartotojo_tipas() {
        return vartotojo_tipas;
    }
    public void setVartotojo_tipas(String vartotojo_tipas) {
        this.vartotojo_tipas = vartotojo_tipas;
    }

}
