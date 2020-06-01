package com.kiwi.waiterly.controladores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiwi.waiterly.R;
import com.kiwi.waiterly.modelo.EntrantesList;
import com.kiwi.waiterly.modelo.Plato;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolderDatos> implements View.OnClickListener{

    private ArrayList<Plato> listaEntrates;
    private View.OnClickListener listener;

    public AdaptadorDatos(ArrayList<Plato> listaEntrates) {
        this.listaEntrates = listaEntrates;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entrante_list,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.titulo.setText(listaEntrates.get(position).getTitulo());
        holder.detalle.setText(listaEntrates.get(position).getDetalle());
        String precio = String.valueOf(listaEntrates.get(position).getPrecio()) ;
        holder.precio.setText(precio);
        //holder.foto.setImageResource(listaEntrates.get(position).getFoto());

        Picasso.get()
                .load(listaEntrates.get(position).getFoto())
                //.load("https://www.196flavors.com/wp-content/uploads/2018/12/pique-macho-2.jpg")

                .placeholder(R.drawable.place_holder)
                .error(R.drawable.menu)
                .into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return listaEntrates.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView titulo, detalle, precio;
        ImageView foto;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewEntranteTitulo);
            detalle = itemView.findViewById(R.id.textViewEntranteDescripcion);
            precio = itemView.findViewById(R.id.textViewPrecio);
            foto = itemView.findViewById(R.id.imageViewEntrante);

        }

    }
}
