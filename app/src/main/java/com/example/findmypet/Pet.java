package com.example.findmypet;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Pet implements Parcelable, Serializable {

    private long codigo;
    private String nombre;
    private String raza;
    private String ciudad;
    private String direccion;
    private double latitud;
    private double longitud;

    public Pet(long codigo, String nombre, String raza, String ciudad, String direccion, double latitud, double longitud) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.raza = raza;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Pet(String nombre, String raza, String ciudad, String direccion, double latitud, double longitud) {
        this.nombre = nombre;
        this.raza = raza;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    protected Pet(Parcel in) {
        codigo = in.readLong();
        nombre = in.readString();
        raza = in.readString();
        ciudad = in.readString();
        direccion = in.readString();
        latitud = in.readDouble();
        longitud = in.readDouble();
    }

    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(codigo);
        dest.writeString(nombre);
        dest.writeString(raza);
        dest.writeString(ciudad);
        dest.writeString(direccion);
        dest.writeDouble(latitud);
        dest.writeDouble(longitud);
    }
}
