package com.example.findmypet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    ControladorBD bd;
    Context c;
    ArrayList<Pet> pets;
    int selectedPos;

    public MyAdapter(Context c, ArrayList<Pet> planets,ControladorBD bd) {
        this.c = c;
        this.pets = planets;
        this.bd=bd;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_list,null,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.nombre.setText(pets.get(position).getNombre());
        holder.raza.setText(pets.get(position).getRaza());
        holder.direccion.setText(pets.get(position).getDireccion());
        holder.ciudad.setText(pets.get(position).getCiudad());

        holder.setLongClickListener(new MyLongClickListener() {
            @Override
            public void onLongClick(int pos) {
                selectedPos=pos;
            }
        });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    //DELETE PLANET
    public void deletePet()
    {


        //GET ID
        Pet p=pets.get(selectedPos);
        long id=p.getCodigo();

        //DELETE IT FROM DB
        bd.borrarRegistro(p);
        //DELETE ALSO FROM ARRAYLIST
        pets.remove(selectedPos);





        this.notifyItemRemoved(selectedPos);
    }

}


