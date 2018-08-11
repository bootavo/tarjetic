package fia.proy2.devmov.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import fia.proy2.devmov.Entities.InventarioCartaEntity;
import fia.proy2.devmov.R;

/**
 * Created by bootavo on 22/02/2017.
 */

public class InventarioCartaAdapter extends RecyclerView.Adapter<InventarioCartaHolder> {

    Context context;
    List<InventarioCartaEntity> listaMenu;

    public InventarioCartaAdapter(Context context, List<InventarioCartaEntity> listaMenu){

        this.context = context;
        this.listaMenu = listaMenu;

    }

    @Override
    public InventarioCartaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_inventario_carta,parent,false);
        InventarioCartaHolder holder=new InventarioCartaHolder(v, context, listaMenu);
        return holder;
    }

    @Override
    public void onBindViewHolder(InventarioCartaHolder holder, int position) {
        //BIND DATA
        holder.nombreMenu.setText(listaMenu.get(position).getDescripcion());

        String precio = "Cantidad: "+listaMenu.get(position).getCantidad();
        holder.precioMenu.setText(precio);

        //holder.progressBar.setVisibility(View.GONE);

        //IMAGE
        //PicassoClient.downloadPicassoCafeteria(context,listaMenu.get(position).getUrl(),holder.imagenMenuH);
        Picasso.with(context).load(listaMenu.get(position).getImagen()).placeholder(R.drawable.naipe_oro).into(holder.imagenMenuH);
    }

    @Override
    public int getItemCount() {
        return listaMenu.size();
    }
}
