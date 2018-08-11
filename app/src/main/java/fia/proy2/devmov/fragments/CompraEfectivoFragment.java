package fia.proy2.devmov.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import fia.proy2.devmov.adapters.ProductoAdapter;
import fia.proy2.devmov.utilities.Constants;
import fia.proy2.devmov.entities.ProductoEntity;
import fia.proy2.devmov.services.ProductoInterface;
import fia.proy2.devmov.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompraEfectivoFragment extends Fragment implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView imgBtnMas;
    private ImageView imgBtnMenos;
    private TextView txtCantidad;
    private TextView txtPrecio;
    private int cantidad;
    private double precio_producto;
    private Button btnComprar;

    private AlertDialog.Builder builder;
    private AlertDialog ad;

    public CompraEfectivoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compra_efectivo, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.listaProductos);
        mLayoutManager= new GridLayoutManager(getActivity(),2);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .build();

        ProductoInterface iP = restAdapter.create(ProductoInterface.class);
        iP.getProdutos(new Callback<List<ProductoEntity>>() {
                           @Override
                           public void success(List<ProductoEntity> productoEntities, Response response) {
                               if (!productoEntities.isEmpty()) {
                                   mAdapter = new ProductoAdapter(productoEntities, R.layout.item_list_producto, getActivity(), new ProductoAdapter.OnItemClickListener() {
                                       @Override
                                       public void onItemClick(ProductoEntity producto, int position) {
                                           cantidad = 1;
                                           builder = new AlertDialog.Builder(getContext());
                                           LayoutInflater inflater = getActivity().getLayoutInflater();
                                           View dialogView = inflater.inflate(R.layout.dialog_compra, null);
                                           TextView title = (TextView) dialogView.findViewById(R.id.txtTitle);
                                           title.setText(producto.getNombre());
                                           ImageView imgProducto = (ImageView) dialogView.findViewById(R.id.imgProducto);
                                           switch (producto.getId_producto()){
                                               case 1:
                                                   imgProducto.setImageResource(R.drawable.t1);
                                                   break;
                                               case 2:
                                                   imgProducto.setImageResource(R.drawable.t2);
                                                   break;
                                               case 3:
                                                   imgProducto.setImageResource(R.drawable.t3);
                                                   break;
                                               case 4:
                                                   imgProducto.setImageResource(R.drawable.t4);
                                                   break;
                                               case 5:
                                                   imgProducto.setImageResource(R.drawable.t5);
                                                   break;
                                               case 6:
                                                   imgProducto.setImageResource(R.drawable.t6);
                                                   break;
                                               case 7:
                                                   imgProducto.setImageResource(R.drawable.t7);
                                                   break;
                                               case 8:
                                                   imgProducto.setImageResource(R.drawable.t8);
                                                   break;
                                               case 9:
                                                   imgProducto.setImageResource(R.drawable.t9);
                                                   break;
                                               case 10:
                                                   imgProducto.setImageResource(R.drawable.t10);
                                                   break;
                                               case 11:
                                                   imgProducto.setImageResource(R.drawable.t11);
                                                   break;
                                               case 12:
                                                   imgProducto.setImageResource(R.drawable.t12);
                                                   break;
                                               default:
                                                   imgProducto.setImageResource(R.drawable.t13);
                                                   break;
                                           }

                                           TextView desc = (TextView) dialogView.findViewById(R.id.txtDesc);
                                           desc.setText(producto.getDescripcion());
                                           imgBtnMas = (ImageView) dialogView.findViewById(R.id.imageButton);
                                           imgBtnMenos = (ImageView) dialogView.findViewById(R.id.imageButton2);
                                           btnComprar = (Button) dialogView.findViewById(R.id.btnComprar);
                                           txtCantidad = (TextView) dialogView.findViewById(R.id.txtCantidad);
                                           txtPrecio = (TextView) dialogView.findViewById(R.id.txtPrecio);
                                           imgBtnMas.setOnClickListener(CompraEfectivoFragment.this);
                                           imgBtnMenos.setOnClickListener(CompraEfectivoFragment.this);
                                           btnComprar.setOnClickListener(CompraEfectivoFragment.this);
                                           precio_producto = producto.getPrecio();
                                           txtPrecio.setText("S/." + String.valueOf((float) Math.round((precio_producto * cantidad) * 100) / 100));
                                           builder.setView(dialogView);
                                           txtCantidad.setText(String.valueOf(cantidad));
                                           ad = builder.show();
                                           CompraFragment.id_producto=producto.getId_producto();
                                       }
                                   });
                                   mRecyclerView.setAdapter(mAdapter);
                               } else {
                                   Toast.makeText(getActivity().getApplicationContext(), "No hay lista de productos", Toast.LENGTH_LONG).show();
                               }
                           }

                           @Override
                           public void failure(RetrofitError error) {

                           }
                       });

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton:
                cantidad++;
                txtCantidad.setText(String.valueOf(cantidad));
                txtPrecio.setText("S/."+String.valueOf((float)Math.round((precio_producto*cantidad) * 100) / 100));
                break;
            case R.id.imageButton2:
                if(cantidad>1){
                    cantidad--;
                    txtCantidad.setText(String.valueOf(cantidad));
                    txtPrecio.setText("S/."+String.valueOf((float)Math.round((precio_producto*cantidad) * 100) / 100));
                }
                break;
            case R.id.btnComprar:
                ad.dismiss();
                CompraFragment.cantidad=cantidad;
                CompraFragment.pago_efectivo=((float)Math.round((precio_producto*cantidad) * 100) / 100);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CompraFragment.newInstance()).commit();
                break;
        }
    }
}