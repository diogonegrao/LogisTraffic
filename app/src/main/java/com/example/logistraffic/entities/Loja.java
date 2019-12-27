package com.example.logistraffic.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Loja implements Parcelable {
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

    protected Loja(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        nome = in.readString();
        estado_lugar = in.readString();
        rua = in.readString();
        concelho_id = in.readString();
        distancia = in.readInt();
    }

    public static final Creator<Loja> CREATOR = new Creator<Loja>() {
        @Override
        public Loja createFromParcel(Parcel in) {
            return new Loja(in);
        }

        @Override
        public Loja[] newArray(int size) {
            return new Loja[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(nome);
        dest.writeString(estado_lugar);
        dest.writeString(rua);
        dest.writeString(concelho_id);
        dest.writeInt(distancia);
    }
}
