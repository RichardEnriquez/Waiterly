package com.kiwi.waiterly.vista.carrito;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiwi.waiterly.R;
import com.kiwi.waiterly.controladores.WaiterlyManager;
import com.kiwi.waiterly.modelo.Plato;
import com.kiwi.waiterly.vista.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorCarrito extends RecyclerView.Adapter<AdaptadorCarrito.ViewHolderDatos> implements View.OnClickListener{
    private ArrayList<Plato> listaPlatosCarrito;
    private View.OnClickListener listener;

    public AdaptadorCarrito(ArrayList<Plato> listaPlatos) {
        this.listaPlatosCarrito = listaPlatos;
    }


    @NonNull
    @Override
    public AdaptadorCarrito.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito_list,null,false);
        return new ViewHolderDatos(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, final int position) {
        holder.titulo.setText(listaPlatosCarrito.get(position).getTitulo());
        holder.detalle.setText(listaPlatosCarrito.get(position).getDetalle());
        String precio = String.valueOf(listaPlatosCarrito.get(position).getPrecio()+" €") ;
        holder.precio.setText(precio);
        //holder.foto.setImageResource(listaEntrates.get(position).getFoto());

        Picasso.get()
                .load(listaPlatosCarrito.get(position).getFoto())
                //.load("https://www.196flavors.com/wp-content/uploads/2018/12/pique-macho-2.jpg")

                .placeholder(R.drawable.place_holder)
                .error(R.drawable.menu)
                .into(holder.foto);

    }



    @Override
    public int getItemCount() {
        return listaPlatosCarrito.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView titulo, detalle, precio;
        ImageView foto ,delete;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTituloCarrito);
            detalle = itemView.findViewById(R.id.textViewDescripcionCarrito);
            precio = itemView.findViewById(R.id.textViewPrecioCarrito);
            foto = itemView.findViewById(R.id.imageViewCarrito);
            delete = itemView.findViewById(R.id.imageViewCarritoDelete);

        }

    }

}
