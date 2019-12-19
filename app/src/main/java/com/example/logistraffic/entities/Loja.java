package com.example.logistraffic.entities;

import java.io.Serializable;

public class Loja implements Serializable {
    private Integer id;
    private String nome;
    private String distancia;
    private String concelho;
    private String rua;
    private String lugares;

    public Loja (Integer id, String nome, String distancia, String concelho, String rua, String lugares){
        this.id = id;
        this.nome = nome;
        this.distancia = distancia;
        this.concelho = concelho;
        this.rua = rua;
        this.lugares = lugares;
    }

    // GETTERS
    public Integer getId() { return id; }

    public String getNome() { return nome; }

    public String getDistancia() { return distancia; }

    public String getConcelho() { return concelho; }

    public String getRua() { return rua; }

    public String getLugares() { return lugares; }

    // SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public void setConcelho(String concelho) {
        this.concelho = concelho;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setLugares(String lugares) {
        this.lugares = lugares;
    }
}
