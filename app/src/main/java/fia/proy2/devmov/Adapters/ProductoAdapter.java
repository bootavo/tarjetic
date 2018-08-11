package fia.proy2.devmov.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fia.proy2.devmov.Entities.ProductoEntity;
import fia.proy2.devmov.R;

/**
 * Created by james on 25/05/2017.
 */

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<ProductoEntity> productos;
    private int layout;
    private Activity activity;
    private OnItemClickListener itemClickListener;

    public ProductoAdapter(List<ProductoEntity> productos, int layout, Activity activity, OnItemClickListener itemClickListener) {
        this.productos = productos;
        this.layout = layout;
        this.activity = activity;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(productos.get(position),itemClickListener);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textViewName;
        public TextView textViewPrecio;
        public ViewHolder(View v){
            super(v);
            imageView = (ImageView) v.findViewById(R.id.imagenTrago);
            textViewName = (TextView) v.findViewById(R.id.nombreTrago);
            textViewPrecio = (TextView) v.findViewById(R.id.precioTrago);
        }

        public void bind(final ProductoEntity producto,final OnItemClickListener listener){
            switch (producto.getId_producto()){
                case 1:
                    Picasso.with(activity).load(R.drawable.t1).fit().into(imageView);
                    break;
                case 2:
                    Picasso.with(activity).load(R.drawable.t2).fit().into(imageView);
                    break;
                case 3:
                    Picasso.with(activity).load(R.drawable.t3).fit().into(imageView);
                    break;
                case 4:
                    Picasso.with(activity).load(R.drawable.t4).fit().into(imageView);
                    break;
                case 5:
                    Picasso.with(activity).load(R.drawable.t5).fit().into(imageView);
                    break;
                case 6:
                    Picasso.with(activity).load(R.drawable.t6).fit().into(imageView);
                    break;
                case 7:
                    Picasso.with(activity).load(R.drawable.t7).fit().into(imageView);
                    break;
                case 8:
                    Picasso.with(activity).load(R.drawable.t8).fit().into(imageView);
                    break;
                case 9:
                    Picasso.with(activity).load(R.drawable.t9).fit().into(imageView);
                    break;
                case 10:
                    Picasso.with(activity).load(R.drawable.t10).fit().into(imageView);
                    break;
                case 11:
                    Picasso.with(activity).load(R.drawable.t11).fit().into(imageView);
                    break;
                case 12:
                    Picasso.with(activity).load(R.drawable.t12).fit().into(imageView);
                    break;
                default:
                    Picasso.with(activity).load(R.drawable.t13).fit().into(imageView);
                    break;
            }

            textViewName.setText(producto.getNombre());
            textViewPrecio.setText("S/."+String.valueOf(producto.getPrecio()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(producto,getAdapterPosition());
                }
            });
        }

    }

    public interface  OnItemClickListener{
        void onItemClick(ProductoEntity producto, int position);
    }

}