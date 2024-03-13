package com.example.hayawanpedia;

public class Model {
    private String id;
    private String indo;
    private String arab;
    private String penjelasan;
    private String foto;

    public Model() {
    }

    public Model(String id, String indo, String arab, String penjelasan, String foto) {
        this.id = id;
        this.indo = indo;
        this.arab = arab;
        this.penjelasan = penjelasan;
        this.foto = foto;
    }

    public Model(String id) {
        this.id = id;
        this.arab = "";
        this.foto = "";
        this.indo = "";
        this.penjelasan = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndo() {
        return indo;
    }

    public void setIndo(String indo) {
        this.indo = indo;
    }

    public String getArab() {
        return arab;
    }



    public void setArab(String arab) {
        this.arab = arab;
    }

    public String getPenjelasan() {
        return penjelasan;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}