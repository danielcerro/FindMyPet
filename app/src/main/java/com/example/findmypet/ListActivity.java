package com.example.findmypet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {



    FloatingActionButton mapa;

    ControladorBD controlador;
    RecyclerView lista;
    ArrayList<Pet> listaMascotas;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        mapa=findViewById(R.id.IdMapa);
        controlador= new ControladorBD(getApplicationContext());
        listaMascotas = controlador.obtenerRegistros();
        lista=findViewById(R.id.recyclerView);
        adapter=new MyAdapter(this,listaMascotas,controlador);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(adapter);



        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
       if(item.getTitle()=="Delete")
        {
            adapter.deletePet();
        }
        return super.onContextItemSelected(item);
    }


}



