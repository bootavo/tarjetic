package fia.proy2.devmov.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fia.proy2.devmov.Entities.CodigoPromocionalEntity;
import fia.proy2.devmov.Interfaces.CodigoPromocionalInterface;
import fia.proy2.devmov.MainActivity;
import fia.proy2.devmov.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bootavo on 24/05/2017.
 */

public class CodigoPromocionalFragment extends Fragment{

    View rootView = null;
    Context ctx = null;

    SharedPreferences prefs;

    private EditText codigo_promocional;
    private Button btn_canjear;

    public static CodigoPromocionalFragment newInstance(){
        return new CodigoPromocionalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTheme(R.style.AppTheme);
        rootView = inflater.inflate(R.layout.fragment_codigo_promocional, container, false);
        ctx = container.getContext();

        codigo_promocional = (EditText) rootView.findViewById(R.id.codigo_promocional);
        btn_canjear = (Button) rootView.findViewById(R.id.btnCanjear);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Ingresa tu código");

        btn_canjear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod_pro = codigo_promocional.getText().toString();

                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("http://200.37.171.27:8080/delivery_trago")
                        .build();

                CodigoPromocionalInterface servicio = restAdapter.create(CodigoPromocionalInterface.class);
                Log.d("CODIGO_PROMOCIONES","ID_USUARIO: "+MainActivity.usuario_id+" CODIGO: "+cod_pro);
                servicio.obtenerFichasXCodigo(cod_pro, (int) MainActivity.usuario_id, new Callback<CodigoPromocionalEntity>(){

                    @Override
                    public void success(final CodigoPromocionalEntity codigoPromocionalEntity, Response response) {

                        //IF SETEADO CON BEAN USUARIO DECLARADO EN EL ONCREATE
                        if (codigoPromocionalEntity.getId_canje()>0) {
                            Log.d("IF","codigoPromocionalEntity.getId_canje(): "+codigoPromocionalEntity.getId_canje());

                            //Construyendo Modal
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
                            LayoutInflater factory = LayoutInflater.from(ctx);
                            View mView = factory.inflate(R.layout.dialog_codigo_promocional, null);
                            alertDialog.setView(mView);

                            final AlertDialog show = alertDialog.show();
                           // final AlertDialog dialog = mBuilder.create();

                            TextView cantidad_fichas_obtenidas = (TextView) mView.findViewById(R.id.fichas_obtenidas);
                            TextView contenido_fichas = (TextView) mView.findViewById(R.id.contenido_fichas);
                            Button btn_aceptar = (Button) mView.findViewById(R.id.btnAceptarCanje);

                            cantidad_fichas_obtenidas.setText("Conseguiste "+codigoPromocionalEntity.getCantidad_fichas()+" Fichas!");
                            contenido_fichas.setText("Obtuviste "+codigoPromocionalEntity.getCantidad_fichas()+" fichas que ahora estan disponibles para que puedas canjear packs de cartas, las cuales te ayudaran a conseguir el tragaoq ue queiras y gratis.");

                            btn_aceptar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    //GETTER
                                    prefs = ctx.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                                    int fichas_actual = prefs.getInt("total_fichas", 0);

                                    fichas_actual = fichas_actual+codigoPromocionalEntity.getCantidad_fichas();

                                    //SETEAR
                                    SharedPreferences prefs = ctx.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor edit = prefs.edit();
                                    edit.putInt("total_fichas", fichas_actual);
                                    MainActivity.fichas_totalM = fichas_actual;
                                    MainActivity.nav_user.setText(""+fichas_actual);
                                    edit.commit();

                                    show.dismiss();
                                    Toast.makeText(getActivity().getApplicationContext(), "Fichas obtenidas: "+codigoPromocionalEntity.getCantidad_fichas(), Toast.LENGTH_LONG).show();
                                }
                            });

                        } else {
                            //mProgressDialog.dismiss();
                            Toast.makeText(getActivity().getApplicationContext(), "Código usado, ingresa otro", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        //mProgressDialog.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), "Revise su conexión a internet", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

    }

}
