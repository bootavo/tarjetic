package fia.proy2.devmov.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import fia.proy2.devmov.activities.CartaDetalle;
import fia.proy2.devmov.entities.InventarioCartaEntity;
import fia.proy2.devmov.R;

/**
 * Created by bootavo on 21/02/2017.
 */

public class InventarioCartaHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView nombreMenu;
    TextView precioMenu;
    ImageView imagenMenuH;
    ProgressBar progressBar;

    Context context;
    List<InventarioCartaEntity> inventarioEntity;

    public InventarioCartaHolder(View itemView, Context context, List<InventarioCartaEntity> inventarioEntity) {
        super(itemView);
        this.context = context;
        this.inventarioEntity = inventarioEntity;

        nombreMenu = (TextView) itemView.findViewById(R.id.nameMenu);
        precioMenu = (TextView) itemView.findViewById(R.id.priceMenu);
        imagenMenuH = (ImageView) itemView.findViewById(R.id.imageMenu);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progress_load_gallery);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final int position = getAdapterPosition();

        Intent intent = new Intent(this.context, CartaDetalle.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("imagen", inventarioEntity.get(position).getImagen());
        this.context.startActivity(intent);
    }
}
