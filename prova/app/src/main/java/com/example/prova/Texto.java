package com.example.prova;

public class Texto {

    private int id;
    private String texto;
    private int cor;

    public Texto(int id, String texto, int cor) {
        this.id = id;
        this.texto = texto;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public Texto(String texto, int cor) {
        this.texto = texto;
        this.cor = cor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }
}
