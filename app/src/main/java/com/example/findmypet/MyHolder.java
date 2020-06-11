package com.example.findmypet;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,View.OnCreateContextMenuListener {

    TextView nombre,raza,direccion,ciudad;
    MyLongClickListener longClickListener;

    public MyHolder(View itemView) {
        super(itemView);

        nombre=itemView.findViewById(R.id.idnombre);
        raza=itemView.findViewById(R.id.idRaza);
        direccion=itemView.findViewById(R.id.iddireccion);
        ciudad=itemView.findViewById(R.id.idciudad);

        itemView.setOnLongClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }

    public void setLongClickListener(MyLongClickListener longClickListener)
    {
        this.longClickListener=longClickListener;
    }

    @Override
    public boolean onLongClick(View v) {
        this.longClickListener.onLongClick(getLayoutPosition());
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        //OUR CONTEXT MENU

        menu.add(0,1,0,"Delete");


    }
}
