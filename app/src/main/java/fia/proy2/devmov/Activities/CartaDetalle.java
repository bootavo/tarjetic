package fia.proy2.devmov.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import fia.proy2.devmov.R;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by gtufinof on 9/06/2017.
 */

public class CartaDetalle extends AppCompatActivity{

    private Toolbar toolbarEntrega;
    private ImageView imageView;
    private String imagen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.carta_detalle);
        usarToolBar();

        imagen = getIntent().getStringExtra("imagen");
        imageView = (ImageView) findViewById(R.id.imagen_cronograma_extendida);

        Glide.with(getApplicationContext()).load(imagen).into(imageView);

        PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);
        photoView.update();

    }

    public void usarToolBar(){
        //Toolbar creada para regresar al fragmento invocador
        toolbarEntrega = (Toolbar) findViewById(R.id.toolbarEntrega);
        toolbarEntrega.setTitle("Cronograma Evaluación");
        toolbarEntrega.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbarEntrega);
        toolbarEntrega.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbarEntrega.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
