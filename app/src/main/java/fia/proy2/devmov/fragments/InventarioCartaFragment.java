package fia.proy2.devmov.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import fia.proy2.devmov.adapters.InventarioCartaAdapter;
import fia.proy2.devmov.utilities.Constants;
import fia.proy2.devmov.entities.InventarioCartaEntity;
import fia.proy2.devmov.services.InventarioInterface;
import fia.proy2.devmov.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gtufinof on 1/06/2017.
 */

public class InventarioCartaFragment extends Fragment {

    private View rootView;
    private Context ctx;

    private LinearLayoutManager mLinearlayout;
    private RecyclerView recyclerViewMenu;
    private ProgressDialog mProgressDialog;
    private List<InventarioCartaEntity> listInventarioCartaFinal;

    private int id_usuario;
    private String tipo;

    public static InventarioCartaFragment newInstance(){
        return new InventarioCartaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Inventario de Cartas");
        rootView = inflater.inflate(R.layout.fragment_invetnario_carta, null);
        ctx = container.getContext();
        tipo = getArguments().getString("tipo");
        id_usuario = getArguments().getInt("id_usuario");

        mLinearlayout = new LinearLayoutManager(ctx);
        recyclerViewMenu = (RecyclerView) rootView.findViewById(R.id.fragment_invetnario_carta);
        mProgressDialog = new ProgressDialog(rootView.getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Cargando menús...");
        mProgressDialog.show();

        callService();

        return rootView;
    }

    public void callService(){

        String accion = "listarInventarioCarta";

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .build();

        InventarioInterface servicio = restAdapter.create(InventarioInterface.class);
        servicio.obtenerCartasInventarioXUsuario(accion, id_usuario, tipo, new Callback<List<InventarioCartaEntity>>() {
            @Override
            public void success(List<InventarioCartaEntity> inventarioCartaEntities, Response response) {
                mProgressDialog.dismiss();
                listInventarioCartaFinal = inventarioCartaEntities;
                if (inventarioCartaEntities.get(0).getId_inventario()>=1){
                    recyclerViewMenu.setHasFixedSize(true);
                    recyclerViewMenu.setLayoutManager(mLinearlayout);

                    InventarioCartaAdapter adapter = new InventarioCartaAdapter(ctx, listInventarioCartaFinal);
                    recyclerViewMenu.setAdapter(adapter);
                    Toast.makeText(ctx, "Lista de cartas coleccionables", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ctx, "Aún no tiene cartas de este tipo", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                mProgressDialog.dismiss();
                Toast.makeText(ctx, "Revise su conexión a internet", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
