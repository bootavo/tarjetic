package fia.proy2.devmov.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
public class CompraFragment extends Fragment {

    public static int id_producto = 0;
    public static int cantidad = 0;
    public static double pago_efectivo = 0;
    public static int pago_oro = 0;
    public static int pago_plata = 0;
    public static int pago_bronce = 0;
    public static String direccion = "";
    public static String telefono_contacto = "";

    private Button btnFinalizar;
    private EditText txtDireccion;
    private EditText txtTelefono;

    public CompraFragment() {
        // Required empty public constructor
    }

    public static CompraFragment newInstance(){
        return new CompraFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compra, container, false);
        btnFinalizar = (Button) view.findViewById(R.id.btnFinalizar);
        txtDireccion = (EditText) view.findViewById(R.id.editDireccion);
        txtTelefono = (EditText) view.findViewById(R.id.editTelefono);
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direc=txtDireccion.getText().toString();
                String tel=txtTelefono.getText().toString();

                if(!direc.equals("") && !tel.equals("")){

                    direccion = direc;
                    telefono_contacto = tel;

                    RestAdapter restAdapter = new RestAdapter.Builder()
                            .setEndpoint(Constants.BASE_URL)
                            .build();
                    PedidoInterface iP = restAdapter.create(PedidoInterface.class);
                    iP.registrarDelivery(id_producto, cantidad, pago_efectivo, pago_oro, pago_plata, pago_bronce, direccion, telefono_contacto, (int) MainActivity.usuario_id, new Callback<PedidoEntity>() {
                        @Override
                        public void success(PedidoEntity pedidoEntity, Response response) {
                            if(pedidoEntity.getId_producto()>0){
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, DeliveryFragment.newInstance()).commit();
                                Toast.makeText(getActivity(),"Compra Realizada",Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getActivity(), "Saldo Insuficiente", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(getActivity(), "Revise su conexion a internet", Toast.LENGTH_LONG).show();
                        }
                    });

                }else {
                    Toast.makeText(getActivity(),"Ingrese todos los datos requeridos",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    public void resetValues(){
        id_producto = 0;
        cantidad = 0;
        pago_efectivo = 0;
        pago_oro = 0;
        pago_plata = 0;
        pago_bronce = 0;
    }

}
