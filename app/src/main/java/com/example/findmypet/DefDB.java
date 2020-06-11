package com.example.findmypet;

public class DefDB {
    public static final String nameDb = "Universidad";
    public static final String tabla_pet = "mascotas";
    public static final String col_codigo ="codigo";
    public static final String col_nombre = "nombre";
    public static final String col_raza = "raza";
    public static final String col_cidudad = "ciudad";
    public static final String col_direccion = "direccion";
    public static final String col_latitud = "latitud";
    public static final String col_longitud = "longitud";


    public static final String CREATE_TABLA_MASCOTAS =
            "CREATE TABLE " +tabla_pet+"(" + col_codigo+" INTEGER primary key AUTOINCREMENT,"+
            col_nombre+" TEXT not null," +
            col_raza+" TEXT not null,"+
            col_cidudad+" TEXT not null," +
            col_direccion+" TEXT not null," +
            col_latitud+" REAL not null," +
            col_longitud+" REAL not null);";
}