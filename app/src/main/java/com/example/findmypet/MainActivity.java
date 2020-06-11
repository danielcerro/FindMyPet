package com.example.findmypet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nombre, raza, ciudad, direccion;
    Button registrar, lista;
    ControladorBD controlador;
    double latitud,longitud;
    String direccion_completa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        nombre=findViewById(R.id.edtNombre);
        raza=findViewById(R.id.edtRaza);
        ciudad=findViewById(R.id.edtCiudad);
        direccion=findViewById(R.id.edtDireccion);
        registrar=findViewById(R.id.btnagregar);
        lista=findViewById(R.id.btnlista);

        controlador = new ControladorBD(getApplicationContext());

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!limpio()){
                    Toast.makeText(getApplicationContext(), "Error en el ingreso de datos, ningun dato debe quedar vacio", Toast.LENGTH_SHORT).show();
                } else{
                    obtenerDireccion();
                    Pet mas=new Pet(nombre.getText().toString(),raza.getText().toString(),ciudad.getText().toString(),direccion.getText().toString(),latitud,longitud);
                    long retorno = controlador.agregarRegistro(mas);
                    if (retorno != -1) {
                        Toast.makeText(v.getContext(), "Mascota guardada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), "Mascota no guardada", Toast.LENGTH_SHORT).show();
                    }

                }
                limpiar();
            }

        });

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ListActivity.class);
                startActivity(i);
            }
        });

    }


    private void limpiar() {
        nombre.setText("");
        raza.setText("");
        ciudad.setText("");
        direccion.setText("");
    }

    private boolean limpio() {
        if((nombre.getText().toString().trim().length()==0)||(raza.getText().toString().trim().length()==0)||(ciudad.getText().toString().trim().length()==0)||(direccion.getText().toString().trim().length()==0)){
            return false;
        }else{
            return true;
        }
    }
    private void obtenerDireccion(){
        //esto es para obtener la latitud y longitud de una direccion
        Geocoder g=new Geocoder(this);
        direccion_completa=direccion.getText().toString().concat(" "+ciudad.getText().toString());
        try {
            List<Address> list= g.getFromLocationName(direccion_completa,1);
            Address add=list.get(0);
            latitud=add.getLatitude();
            longitud=add.getLongitude();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
