package com.example.logistraffic.entities;

import java.io.Serializable;

public class Loja implements Serializable {
    private Integer id;
    private String nome;
    private String estado_lugar;
    private String rua;
    private String concelho_id;
    private int distancia;



    public Loja (Integer id, String nome, String estado_lugar, String rua, String concelho_id, Integer distancia){
        this.id = id;
        this.nome = nome;
        this.concelho_id = concelho_id;
        this.rua = rua;
        this.estado_lugar = estado_lugar;
        this.distancia = distancia;
    }

    // GETTERS
    public Integer getId() { return id; }

    public String getNome() { return nome; }

    public Integer getDistancia() { return distancia; }

    public String getConcelho() { return concelho_id; }

    public String getRua() { return rua; }

    public String getLugares() { return estado_lugar; }

    // SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }


    public void setConcelho(String concelho) {
        this.concelho_id = concelho;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setLugares(String lugares) {
        this.estado_lugar = lugares;
    }
}
