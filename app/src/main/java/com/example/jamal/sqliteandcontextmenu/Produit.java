package com.example.jamal.sqliteandcontextmenu;

/**
 * Created by jamal on 05/05/2018.
 */

public class Produit {
    private long code;
    private String description;
    private float prix;
    public Produit( String description, float prix) {
        this.description = description;
        this.prix = prix;
    }
    public long getCode() {
        return code;
    }
    public void setCode(long code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public float getPrix() {
        return prix;
    }
    public void setPrix(float prix) {
        this.prix = prix;
    }
}