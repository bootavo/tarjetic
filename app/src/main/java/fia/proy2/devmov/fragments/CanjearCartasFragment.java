package fia.proy2.devmov.fragments;

import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

import fia.proy2.devmov.adapters.CanjeAdapter;
import fia.proy2.devmov.utilities.Constants;
import fia.proy2.devmov.entities.InventarioEntity;
import fia.proy2.devmov.entities.PaquetesEntity;
import fia.proy2.devmov.services.InventarioInterface;
import fia.proy2.devmov.services.PaqueteCartasInterface;
import fia.proy2.devmov.activities.MainActivity;
import fia.proy2.devmov.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bootavo on 25/05/2017.
 */

public class CanjearCartasFragment extends Fragment{

    View rootView = null;
    Context ctx = null;

    SharedPreferences prefs;

    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;

    private GridView gridViewCanje;
    private List<PaquetesEntity> listCafeteriaFinal;
    private ProgressDialog mProgressDialog;
    private CanjeAdapter canjeAdapter;
    private int id_paquete_cartas;

    public static CanjearCartasFragment newInstance(){
        return new CanjearCartasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Canjear cartas");
        rootView = inflater.inflate(R.layout.fragment_canjear, null);
        ctx = container.getContext();

        gridViewCanje = (GridView) rootView.findViewById(R.id.fragment_canjear);
        mProgressDialog = new ProgressDialog(rootView.getContext());

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callService();

    }

    private void callService(){

        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Cargando cafetería...");
        mProgressDialog.show();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .build();

        PaqueteCartasInterface servicio = restAdapter.create(PaqueteCartasInterface.class);
        servicio.obtenerPaqueteCartas(new Callback<List<PaquetesEntity>>() {

            @Override
            public void success(List<PaquetesEntity> listPaquetes, Response response) {
                mProgressDialog.dismiss();

                listCafeteriaFinal = listPaquetes;

                if (listCafeteriaFinal.get(0).getId_paquete_cartas()>=1){
                    Log.d("ID_PAQUETE_CARTAS: ", ""+listCafeteriaFinal.get(0).getId_paquete_cartas());
                    canjeAdapter = new CanjeAdapter(getActivity().getApplicationContext(), listCafeteriaFinal);

                    gridViewCanje.setAdapter(canjeAdapter);
                    gridViewCanje.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                            id_paquete_cartas = listCafeteriaFinal.get(position).getId_paquete_cartas();
                            //Toast.makeText(getActivity().getApplicationContext(), ""+listCafeteriaFinal.get(position).getId_paquete_cartas(), Toast.LENGTH_SHORT).show();

                            //Construyendo Modal
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
                            LayoutInflater factory = LayoutInflater.from(ctx);
                            final View mView = factory.inflate(R.layout.dialog_canje_pack, null);
                            alertDialog.setView(mView);
                            alertDialog.setCancelable(true);

                            final AlertDialog show = alertDialog.show();

                            TextView nombre_pack = (TextView) mView.findViewById(R.id.nombre_pack);

                            Object o = listCafeteriaFinal.get(position).getNombre();
                            String o2 = o.toString();
                            nombre_pack.setText(o2.replace("\\n", "\n"));
                            nombre_pack.setSingleLine(false);
                            //nombre_pack.setText(listCafeteriaFinal.get(position).getNombre());

                            ImageView imagen_pack = (ImageView) mView.findViewById(R.id.imagePack);

                            if (id_paquete_cartas == 1){
                                Glide.with(mView.getContext()).load(R.drawable.pack_bronce).into(imagen_pack);
                            }else if(id_paquete_cartas == 2){
                                Glide.with(mView.getContext()).load(R.drawable.pack_plata).into(imagen_pack);
                            }else if(id_paquete_cartas == 3){
                                Glide.with(mView.getContext()).load(R.drawable.pack_oro).into(imagen_pack);
                            }else if(id_paquete_cartas == 4){
                                Glide.with(mView.getContext()).load(R.drawable.pack_premium).into(imagen_pack);
                            }



                            // DATOS MODAL 1
                            relativeLayout1 = (RelativeLayout) mView.findViewById(R.id.relative1);
                            relativeLayout2 = (RelativeLayout) mView.findViewById(R.id.relative2);

                            TextView descripcion_pack = (TextView) mView.findViewById(R.id.descripcion_pack);
                            descripcion_pack.setText(listCafeteriaFinal.get(position).getDescripcion());

                            Button btn_canje_pack = (Button) mView.findViewById(R.id.btnCanjePack);
                            // DATOS MODAL 2
                            final Button btn_aceptar_cartas = (Button) mView.findViewById(R.id.btnAceptarCartas);
                            final ImageView carta1 = (ImageView) mView.findViewById(R.id.imageCarta1);
                            final ImageView carta2 = (ImageView) mView.findViewById(R.id.imageCarta2);
                            final ImageView carta3 = (ImageView) mView.findViewById(R.id.imageCarta3);
                            final ImageView carta4 = (ImageView) mView.findViewById(R.id.imageCarta4);
                            final ImageView carta5 = (ImageView) mView.findViewById(R.id.imageCarta5);

                            Random rand = new Random();
                            final int c1 = rand.nextInt(10 - 1) + 1;
                            final int c2 = rand.nextInt(10 - 1) + 1;
                            final int c3 = rand.nextInt(10 - 1) + 1;
                            final int c4 = rand.nextInt(10 - 1) + 1;
                            final int c5 = rand.nextInt(10 - 1) + 1;

                            int contador_oro = 0;
                            int contador_plata = 0;
                            int contador_bronce = 0;

                            if (c1 == 1 || c1 == 4 || c1 == 7){
                                Glide.with(mView.getContext()).load(R.drawable.naipe_oro).into(carta1);
                                contador_oro = contador_oro+1;
                            }else if ( c1 == 2 || c1 == 5 || c1 == 8){
                                contador_plata = contador_plata+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_plata).into(carta1);
                            }else if ( c1 == 3 || c1 == 6 || c1 == 9){
                                contador_bronce = contador_bronce+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_bronce).into(carta1);
                            }

                            if (c2 == 1 || c2 == 4 || c2 == 7){
                                contador_oro = contador_oro+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_oro).into(carta2);
                            }else if ( c2 == 2 || c2 == 5 || c2 == 8){
                                contador_plata = contador_plata+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_plata).into(carta2);
                            }else if ( c2 == 3 || c2 == 6 || c2 == 9){
                                contador_bronce = contador_bronce+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_bronce).into(carta2);
                            }

                            if (c3 == 1 || c3 == 4 || c3 == 7){
                                contador_oro = contador_oro+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_oro).into(carta3);
                            }else if ( c3 == 2 || c3 == 5 || c3 == 8){
                                contador_plata = contador_plata+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_plata).into(carta3);
                            }else if ( c3 == 3 || c3 == 6 || c3 == 9){
                                contador_bronce = contador_bronce+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_bronce).into(carta3);
                            }

                            if (c4 == 1 || c4 == 4 || c4 == 7){
                                contador_oro = contador_oro+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_oro).into(carta4);
                            }else if ( c4 == 2 || c4 == 5 || c4 == 8){
                                contador_plata = contador_plata+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_plata).into(carta4);
                            }else if ( c4 == 3 || c4 == 6 || c4 == 9){
                                contador_bronce = contador_bronce+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_bronce).into(carta4);
                            }

                            if (c5 == 1 || c5 == 4 || c5 == 7){
                                contador_oro = contador_oro+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_oro).into(carta5);
                            }else if ( c5 == 2 || c5 == 5 || c5 == 8){
                                contador_plata = contador_plata+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_plata).into(carta5);
                            }else if ( c5 == 3 || c5 == 6 || c5 == 9){
                                contador_bronce = contador_bronce+1;
                                Glide.with(mView.getContext()).load(R.drawable.naipe_bronce).into(carta5);
                            }

                            Log.d("CARTA 1",""+c1);
                            Log.d("CARTA 2",""+c2);
                            Log.d("CARTA 3",""+c3);
                            Log.d("CARTA 4",""+c4);
                            Log.d("CARTA 5",""+c5);

                            Log.d("CONTADOR ORO", ""+contador_oro);
                            Log.d("CONTADOR PLATA", ""+contador_plata);
                            Log.d("CONTADOR BRONCE", ""+contador_bronce);

                            final int finalContador_oro = contador_oro;
                            final int finalContador_plata = contador_plata;
                            final int finalContador_bronce = contador_bronce;

                            btn_canje_pack.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    //CANJEAR PAQUETE ------------------------------------------------------------------------------------------->
                                    //GETTER
                                    int resta = 0;

                                    prefs = ctx.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                                    int fichas_actual = prefs.getInt("total_fichas", 0);
                                    Log.d("FICHAS ACTUAL: ",""+fichas_actual);
                                    if (fichas_actual<listCafeteriaFinal.get(position).getFichas_requeridas()){
                                        Toast.makeText(getActivity().getApplicationContext(), "Te faltan más fichas!", Toast.LENGTH_LONG).show();
                                        show.dismiss();
                                    }else{
                                        Log.d("FICHAS REQUERIDAS WS: ",""+listCafeteriaFinal.get(position).getFichas_requeridas());

                                        resta = fichas_actual - listCafeteriaFinal.get(position).getFichas_requeridas();

                                        Log.d("RESTA DE FICHAS: ",""+resta);

                                        //SETEAR
                                        SharedPreferences prefs = ctx.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor edit = prefs.edit();
                                        edit.putInt("total_fichas", resta);

                                        MainActivity.fichas_totalM = resta;
                                        MainActivity.nav_user.setText(""+resta);
                                        edit.commit();

                                        RestAdapter restAdapter = new RestAdapter.Builder()
                                                .setEndpoint(Constants.BASE_URL)
                                                .build();

                                        InventarioInterface servicio = restAdapter.create(InventarioInterface.class);
                                        final int finalResta = resta;
                                        servicio.canjearPaquete((int) MainActivity.usuario_id, listCafeteriaFinal.get(position).getFichas_requeridas(), finalContador_oro, finalContador_plata, finalContador_bronce, new Callback<InventarioEntity>() {

                                            @Override
                                            public void success(InventarioEntity inventarioEntity, Response response) {
                                                if (inventarioEntity.getId_inventario()>0){
                                                    Toast.makeText(getActivity().getApplicationContext(), "Fichas actual: "+ finalResta +" fichas", Toast.LENGTH_LONG).show();

                                                    RestAdapter restAdapterCartas = new RestAdapter.Builder()
                                                            .setEndpoint(Constants.BASE_URL)
                                                            .build();

                                                    String accion = "registrar_cartas";

                                                    InventarioInterface servicioCartas = restAdapterCartas.create(InventarioInterface.class);
                                                    Log.d("XDDDD","------> ACCION:"+accion+" ID_PERSONA:"+(int) MainActivity.usuario_id+" C1:"+c1+" C2:"+c2+" C3:"+c3+" C4:"+c4+" C5:"+c5);
                                                    servicioCartas.registrarCartas(accion, (int) MainActivity.usuario_id, c1, c2, c3, c4, c5, new Callback<InventarioEntity>() {
                                                        @Override
                                                        public void success(InventarioEntity inventarioEntity, Response response) {
                                                            //Log.d("TIPO DE CARTAS","REGISTROOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                                                        }

                                                        @Override
                                                        public void failure(RetrofitError error) {
                                                            //Log.d("TIPO DE CARTAS","NO REGISTROOOOOOOOOOOOOOOOOOOOOOOOO");
                                                        }
                                                    });

                                                }else {

                                                }
                                            }

                                            @Override
                                            public void failure(RetrofitError error) {
                                                Toast.makeText(getActivity().getApplicationContext(), "Revise su conexión a internet", Toast.LENGTH_LONG).show();
                                            }
                                        });

                                        //show.dismiss(); PEIMER CIERRE DE MODAL
                                        Log.d("FICHAS FINAL ANDROID: ",""+ resta);

                                        //PASO DOS CONFIRMAR CARTAS
                                        relativeLayout1.setVisibility(View.GONE);
                                        relativeLayout2.setVisibility(View.VISIBLE);

                                        btn_aceptar_cartas.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                show.dismiss();

                                            }
                                        });


                                    }

                                    //                     ------------------------------------------------------------------------------------------->

                                }
                            });

                            //Bundle arguments = new Bundle();
                            //arguments.putInt("cod_dia", id_paquete_cartas);

                            //final MenuFragment menuFragment = new MenuFragment();
                            //menuFragment.setArguments(arguments);

                            //getFragmentManager().beginTransaction().replace(R.id.content_frame, menuFragment).addToBackStack(null).commit();

                        }
                    });

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "No hay paquetes disponibles", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Revise su conexión a internet", Toast.LENGTH_LONG).show();
            }

        });
    }

}
