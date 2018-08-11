package fia.proy2.devmov.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import fia.proy2.devmov.Adapters.InventarioAdapter;
import fia.proy2.devmov.Constants.Constants;
import fia.proy2.devmov.Entities.CartaEntity;
import fia.proy2.devmov.Interfaces.InventarioInterface;
import fia.proy2.devmov.MainActivity;
import fia.proy2.devmov.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bootavo on 25/05/2017.
 */

public class InventarioFragment extends Fragment{

    View rootView = null;
    Context ctx = null;

    private GridView gridViewInventario;
    private List<CartaEntity> listCartaFinal;
    private ProgressDialog mProgressDialog;
    private InventarioAdapter inventarioAdapter;

    public static InventarioFragment newInstance(){
        return new InventarioFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Inventario");
        rootView = inflater.inflate(R.layout.fragment_inventario, null);
        ctx = container.getContext();

        gridViewInventario = (GridView) rootView.findViewById(R.id.fragment_inventario);
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
        mProgressDialog.setMessage("Cargando Inventario...");
        mProgressDialog.show();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .build();

        InventarioInterface servicio = restAdapter.create(InventarioInterface.class);
        servicio.obtenerCartasInventario(new Callback<List<CartaEntity>>() {

            @Override
            public void success(final List<CartaEntity> listCarta, Response response) {
                mProgressDialog.dismiss();

                listCartaFinal = listCarta;

                if (listCartaFinal.get(0).getId_carta()>=1){
                    Log.d("ID_CARTAS: ", ""+listCartaFinal.get(0).getId_carta());
                    inventarioAdapter = new InventarioAdapter(getActivity().getApplicationContext(), listCartaFinal);

                    gridViewInventario.setAdapter(inventarioAdapter);
                    gridViewInventario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //id_carta = listCartaFinal.get(position).getId_carta();
                            String tipo = "";
                            int id_usuario = (int) MainActivity.usuario_id;

                            tipo = listCartaFinal.get(position).getTipo();

                            Bundle arguments = new Bundle();
                            arguments.putString("tipo", tipo);
                            arguments.putInt("id_usuario", id_usuario);

                            Log.d("XDD","----------------------------> TIPO:"+tipo+" ID_USUARIO:"+id_usuario);

                            InventarioCartaFragment inventarioCartaFragment = new InventarioCartaFragment();
                            inventarioCartaFragment.setArguments(arguments);
                            getFragmentManager().beginTransaction().replace(R.id.content_frame, inventarioCartaFragment).addToBackStack(null).commit();

                        }
                    });

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Tipo de Cartas Vacio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Revise su conexión a internet", Toast.LENGTH_LONG).show();
            }

        });
    }

}
