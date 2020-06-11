package com.example.findmypet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ControladorBD {
    private BaseDatos bd;

    public ControladorBD(Context context) {
        this.bd = new BaseDatos(context, DefDB.tabla_pet, null, 1);
    }

    public long agregarRegistro(Pet mascota) {

        try {
            SQLiteDatabase database = bd.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DefDB.col_nombre, mascota.getNombre());
            values.put(DefDB.col_raza, mascota.getRaza());
            values.put(DefDB.col_cidudad, mascota.getCiudad());
            values.put(DefDB.col_direccion, mascota.getDireccion());
            values.put(DefDB.col_latitud, mascota.getLatitud());
            values.put(DefDB.col_longitud, mascota.getLongitud());
            return database.insert(DefDB.tabla_pet, null, values);
        } catch (Exception e) {
            return -1L;
        }
    }

    public int actualizarRegistro(Pet mascota) {
        try {
            SQLiteDatabase database = bd.getWritableDatabase();
            ContentValues valoresActualizados = new ContentValues();
            valoresActualizados.put(DefDB.col_nombre, mascota.getNombre());
            valoresActualizados.put(DefDB.col_raza, mascota.getRaza());
            valoresActualizados.put(DefDB.col_cidudad, mascota.getCiudad());
            valoresActualizados.put(DefDB.col_direccion, mascota.getDireccion());
            valoresActualizados.put(DefDB.col_latitud, mascota.getLatitud());
            valoresActualizados.put(DefDB.col_longitud, mascota.getLongitud());

            String campoParaActualizar = DefDB.col_codigo + " = ?";
            String[] argumentosParaActualizar = {String.valueOf(mascota.getCodigo())};

            return database.update(DefDB.tabla_pet, valoresActualizados, campoParaActualizar, argumentosParaActualizar);
        } catch (Exception e) {
            return 0;
        }
    }

    public int borrarRegistro(Pet mascota) {
        try {
            SQLiteDatabase database = bd.getWritableDatabase();
            String[] argumentos = {String.valueOf(mascota.getCodigo())};
            return database.delete(DefDB.tabla_pet, DefDB.col_codigo + " = ?", argumentos);
        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<Pet> obtenerRegistros() {
        ArrayList<Pet> mascotas = new ArrayList<>();

        SQLiteDatabase database = bd.getReadableDatabase();

        String[] columnasConsultar = {DefDB.col_codigo, DefDB.col_nombre, DefDB.col_raza
                , DefDB.col_cidudad, DefDB.col_direccion,DefDB.col_latitud,DefDB.col_longitud};

        Cursor cursor = database.query(DefDB.tabla_pet, columnasConsultar
                , null, null, null, null, null);

        if (cursor == null) {
            return mascotas;
        }

        if (!cursor.moveToFirst()) {
            return mascotas;
        }

        do {

            Pet mascota = new Pet(cursor.getLong(0), cursor.getString(1)
                    , cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getDouble(5),cursor.getDouble(6));
            mascotas.add(mascota);
        } while (cursor.moveToNext());

        cursor.close();
        return mascotas;
    }

    public boolean buscarEstudiante(String cod){
        String[] args = new String[] {cod};
        SQLiteDatabase database = bd.getReadableDatabase();
        Cursor c = database.query(DefDB.tabla_pet, null, "codigo=?", args, null, null, null);
        if (c.getCount()>0) {
            database.close();
            return true;
        }
        else{
            database.close();
            return false;}

    }


}
