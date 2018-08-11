package fia.proy2.devmov.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import fia.proy2.devmov.Adapters.PedidoAdapter;
import fia.proy2.devmov.Constants.Constants;
import fia.proy2.devmov.Entities.PedidoEntity;
import fia.proy2.devmov.Interfaces.PedidoInterface;
import fia.proy2.devmov.Activities.MainActivity;
import fia.proy2.devmov.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedidoFragment extends Fragment {

    private List<PedidoEntity> pedidos;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog mProgressDialog;

    public PedidoFragment() {
        // Required empty public constructor
    }

    public static PedidoFragment newInstance(){
        return new PedidoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pedido, container, false);
        getActivity().setTitle("Pedidos");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.listaPedidos);
        mLayoutManager= new LinearLayoutManager(getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mProgressDialog = new ProgressDialog(view.getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("cargando historial ...");
        mProgressDialog.show();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .build();

        PedidoInterface iP = restAdapter.create(PedidoInterface.class);
        iP.getPedidos((int) MainActivity.usuario_id, new Callback<List<PedidoEntity>>() {
            @Override
            public void success(List<PedidoEntity> pedidoEntities, Response response) {
                mProgressDialog.dismiss();
                if (!pedidoEntities.isEmpty()) {
                    mAdapter = new PedidoAdapter(pedidoEntities,R.layout.item_list_pedidos,getActivity());
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "No hay lista de pedidos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                mProgressDialog.dismiss();
                Toast.makeText(getActivity().getApplicationContext(), "Revise su conexion a internet", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
