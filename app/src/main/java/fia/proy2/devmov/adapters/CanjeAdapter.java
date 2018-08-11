package fia.proy2.devmov.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fia.proy2.devmov.Entities.PaquetesEntity;
import fia.proy2.devmov.R;

/**
 * Created by bootavo on 25/05/2017.
 */

public class CanjeAdapter extends BaseAdapter{

    private List<PaquetesEntity> listaPackCanje;
    private Context context;

    public CanjeAdapter(Context context, List<PaquetesEntity> listaCafeteria) {
        this.listaPackCanje = listaCafeteria;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaPackCanje.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPackCanje.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaPackCanje.get(position).getId_paquete_cartas();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View listItemView = view;

        if (listItemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listItemView = inflater.inflate(R.layout.grid_canjear, viewGroup, false);
        }

        int id_dia = (listaPackCanje.get(position).getId_paquete_cartas());

        TextView nombrePack = (TextView) listItemView.findViewById(R.id.nombre_paquete);

        Object o = listaPackCanje.get(position).getNombre();
        String o2 = o.toString();
        nombrePack.setText(o2.replace("\\n", "\n"));
        nombrePack.setSingleLine(false);

        ImageView imagenPack = (ImageView) listItemView.findViewById(R.id.imagen_paquete);

        if (id_dia == 1){
            //imagenCoche.setImageResource(R.drawable.lunes);
            Glide.with(context).load(R.drawable.pack_bronce).into(imagenPack);
        }else if(id_dia == 2){
            //imagenCoche.setImageResource(R.drawable.martes);
            Glide.with(context).load(R.drawable.pack_plata).into(imagenPack);
        }else if(id_dia == 3){
            //imagenCoche.setImageResource(R.drawable.miercoles);
            Glide.with(context).load(R.drawable.pack_oro).into(imagenPack);
        }else if(id_dia == 4){
            //imagenCoche.setImageResource(R.drawable.jueves);
            Glide.with(context).load(R.drawable.pack_premium).into(imagenPack);
        }
        return listItemView;
    }

}
