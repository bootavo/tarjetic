package fia.proy2.devmov.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fia.proy2.devmov.entities.CartaEntity;
import fia.proy2.devmov.R;

/**
 * Created by bootavo on 25/05/2017.
 */

public class InventarioAdapter extends BaseAdapter{

    private List<CartaEntity> listaCafeteria;
    private Context context;

    public InventarioAdapter(Context context, List<CartaEntity> listaCafeteria) {
        this.listaCafeteria = listaCafeteria;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaCafeteria.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCafeteria.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaCafeteria.get(position).getId_carta();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View listItemView = view;

        if (listItemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listItemView = inflater.inflate(R.layout.grid_inventario, viewGroup, false);
        }

        int id_dia = (listaCafeteria.get(position).getId_carta());

        TextView nombreCoche = (TextView) listItemView.findViewById(R.id.nombre_carta);
        nombreCoche.setText(listaCafeteria.get(position).getTipo());

        ImageView imagenCoche = (ImageView) listItemView.findViewById(R.id.imagen_carta);

        if (id_dia == 1){
            Glide.with(context).load(R.drawable.naipe_oro).into(imagenCoche);
        }else if(id_dia == 2){
            Glide.with(context).load(R.drawable.naipe_plata).into(imagenCoche);
        }else if(id_dia == 3){
            Glide.with(context).load(R.drawable.naipe_bronce).into(imagenCoche);
        }
        return listItemView;
    }

}
