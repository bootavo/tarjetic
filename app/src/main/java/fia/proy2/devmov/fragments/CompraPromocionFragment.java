package fia.proy2.devmov.Fragments;


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

import fia.proy2.devmov.Adapters.ProductoPromocionAdapter;
import fia.proy2.devmov.Constants.Constants;
import fia.proy2.devmov.Entities.InventarioEntity;
import fia.proy2.devmov.Entities.ProductoPromocionEntity;
import fia.proy2.devmov.Interfaces.InventarioInterface;
import fia.proy2.devmov.Interfaces.ProductoInterface;
import fia.proy2.devmov.Activities.MainActivity;
import fia.proy2.devmov.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompraPromocionFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView imgBtnMas;
    private ImageView imgBtnMenos;
    private TextView txtCantidad;
    private Button txtOroReq;
    private Button txtPlataReq;
    private Button txtBronceReq;
    private int cantidad;

    private int precio_oro;
    private int precio_plata;
    private int precio_bronce;

    private AlertDialog ad;

    RestAdapter restAdapter;

    public CompraPromocionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compra_promocion, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.listaProductosPromocion);
        mLayoutManager= new GridLayoutManager(getActivity(),2);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);


        restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .build();

        ProductoInterface iP = restAdapter.create(ProductoInterface.class);
        iP.getProdutosPromocion(new Callback<List<ProductoPromocionEntity>>() {
                           @Override
                           public void success(List<ProductoPromocionEntity> productoEntities, Response response) {
                               if (!productoEntities.isEmpty()) {
                                   mAdapter = new ProductoPromocionAdapter(productoEntities,R.layout.item_list_producto_promocion,getActivity(),new ProductoPromocionAdapter.OnItemClickListener() {
                                       @Override
                                       public void onItemClick(ProductoPromocionEntity producto, int position) {

                                           cantidad = 1;

                                           AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                           LayoutInflater inflater = getActivity().getLayoutInflater();
                                           View dialogView = inflater.inflate(R.layout.dialog_compra_promocion, null);
                                           TextView title = (TextView) dialogView.findViewById(R.id.txtTitle);
                                           title.setText(producto.getNombre());
                                           ImageView imgProducto = (ImageView) dialogView.findViewById(R.id.imgProducto);
                                           switch (producto.getId_producto_promocion()){
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

                                           txtCantidad = (TextView) dialogView.findViewById(R.id.txtCantidad);
                                           txtOroReq = (Button) dialogView.findViewById(R.id.pagoOro);
                                           txtPlataReq = (Button) dialogView.findViewById(R.id.pagoPlata);
                                           txtBronceReq = (Button) dialogView.findViewById(R.id.pagoBronce);


                                           txtOroReq.setOnClickListener(CompraPromocionFragment.this);
                                           txtPlataReq.setOnClickListener(CompraPromocionFragment.this);
                                           txtBronceReq.setOnClickListener(CompraPromocionFragment.this);
                                           imgBtnMas.setOnClickListener(CompraPromocionFragment.this);
                                           imgBtnMenos.setOnClickListener(CompraPromocionFragment.this);
                                           precio_oro = producto.getCartas_oro_requerida();
                                           precio_plata = producto.getCartas_plata_requerida();
                                           precio_bronce = producto.getCartas_bronce_requerida();
                                           txtOroReq.setText("Pago " + String.valueOf(precio_oro * cantidad) + " Oro");
                                           txtPlataReq.setText("Pago " + String.valueOf(precio_plata * cantidad) + " Plata");
                                           txtBronceReq.setText("Pago " + String.valueOf(precio_bronce * cantidad) + " Bronce");
                                           txtCantidad.setText(String.valueOf(cantidad));
                                           builder.setView(dialogView);
                                           ad = builder.show();
                                           CompraFragment.id_producto=producto.getId_producto_promocion();
                                       }
                                   });
                                   mRecyclerView.setAdapter(mAdapter);
                               } else {
                                   Toast.makeText(getActivity().getApplicationContext(), "No hay lista de productos", Toast.LENGTH_LONG).show();
                               }
                           }

                           @Override
                           public void failure(RetrofitError error) {
                               Toast.makeText(getActivity().getApplicationContext(), "Revise su conexion a internet", Toast.LENGTH_LONG).show();
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
                txtOroReq.setText("Pago "+String.valueOf(precio_oro*cantidad)+" Oro");
                txtPlataReq.setText("Pago "+String.valueOf(precio_plata*cantidad)+" Plata");
                txtBronceReq.setText("Pago "+String.valueOf(precio_bronce*cantidad)+" Bronce");
                break;
            case R.id.imageButton2:
                if(cantidad>1){
                    cantidad--;
                    txtCantidad.setText(String.valueOf(cantidad));
                    txtOroReq.setText("Pago "+String.valueOf(precio_oro*cantidad)+" Oro");
                    txtPlataReq.setText("Pago "+String.valueOf(precio_plata*cantidad)+" Plata");
                    txtBronceReq.setText("Pago "+String.valueOf(precio_bronce*cantidad)+" Bronce");
                }
                break;
            case R.id.pagoOro:
                //ad.dismiss();
                //CompraFragment.cantidad=cantidad;
                CompraFragment.pago_oro=(precio_oro*cantidad);
                verificarSaldo("oro");
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CompraFragment.newInstance()).commit();
                break;
            case R.id.pagoPlata:
                //ad.dismiss();
                //CompraFragment.cantidad=cantidad;
                CompraFragment.pago_plata=(precio_plata*cantidad);
                verificarSaldo("plata");
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CompraFragment.newInstance()).commit();
                break;
            case R.id.pagoBronce:
                //ad.dismiss();
                //CompraFragment.cantidad=cantidad;
                CompraFragment.pago_bronce=(precio_bronce*cantidad);
                verificarSaldo("bronce");
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CompraFragment.newInstance()).commit();
                break;
        }
    }

    public void verificarSaldo(final String tipo_carta){

        InventarioInterface iI = restAdapter.create(InventarioInterface.class);
        iI.obtenerDatosInventario((int) MainActivity.usuario_id, new Callback<InventarioEntity>() {
            @Override
            public void success(InventarioEntity inventarioEntity, Response response) {
                if(inventarioEntity.getId_inventario()>0){
                    if(tipo_carta.equals("oro")){
                        if(inventarioEntity.getTotal_tarjetas_oro()>=(precio_oro*cantidad)){
                            ad.dismiss();
                            CompraFragment.cantidad=cantidad;
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CompraFragment.newInstance()).commit();
                        } else {
                            Toast.makeText(getActivity(), "Saldo Insuficiente", Toast.LENGTH_SHORT).show();
                        }
                    }else if(tipo_carta.equals("plata")){
                        if(inventarioEntity.getTotal_tarjetas_plata()>=(precio_plata*cantidad)){
                            ad.dismiss();
                            CompraFragment.cantidad=cantidad;
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CompraFragment.newInstance()).commit();
                        } else {
                            Toast.makeText(getActivity(), "Saldo Insuficiente", Toast.LENGTH_SHORT).show();
                        }
                    }else if(tipo_carta.equals("bronce")){
                        if(inventarioEntity.getTotal_tarjetas_bronce()>=(precio_bronce*cantidad)){
                            ad.dismiss();
                            CompraFragment.cantidad=cantidad;
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CompraFragment.newInstance()).commit();
                        } else {
                            Toast.makeText(getActivity(), "Saldo Insuficiente", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Revise su conexion a internet", Toast.LENGTH_LONG).show();
            }
        });
    }
}
