package fia.proy2.devmov.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fia.proy2.devmov.Constants.Constants;
import fia.proy2.devmov.Entities.UsuarioEntity;
import fia.proy2.devmov.Interfaces.UsuarioInterface;
import fia.proy2.devmov.MainActivity;
import fia.proy2.devmov.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Login extends Activity implements OnClickListener {

    private EditText user, pass;
    private Button mSubmit;
    private ProgressDialog mProgressDialog;

    public static int fichas_total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgressDialog = new ProgressDialog(this);
        user = (EditText) findViewById(R.id.usuario);
        pass = (EditText) findViewById(R.id.clave);
        mSubmit = (Button) findViewById(R.id.btnLogin);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnLogin:
                if(user.getText().toString().equals("")||pass.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Ingrese su usuario y contraseña", Toast.LENGTH_SHORT).show();
                }else {
                    String usuario = user.getText().toString();
                    String clave = pass.getText().toString();
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage("confirmando datos ...");
                    mProgressDialog.show();
                    RestAdapter restAdapter = new RestAdapter.Builder()
                            .setEndpoint(Constants.BASE_URL)
                            .build();

                    UsuarioInterface servicio = restAdapter.create(UsuarioInterface.class);
                    servicio.validarUsuario(usuario,clave,new Callback<UsuarioEntity>(){

                        @Override
                        public void success(UsuarioEntity usuarioEntity, Response response) {

                            //IF SETEADO CON BEAN USUARIO DECLARADO EN EL ONCREATE
                            if (usuarioEntity.getId_usuario()>0) {
                                SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = prefs.edit();
                                edit.putLong("id", usuarioEntity.getId_usuario());
                                edit.putString("usuario", usuarioEntity.getUsuario());
                                edit.putInt("total_fichas", usuarioEntity.getTotal_fichas());
                                Log.d("GFICHAS LOGIN: ",""+usuarioEntity.getTotal_fichas());

                                fichas_total = usuarioEntity.getTotal_fichas();

                                edit.commit();
                                mProgressDialog.dismiss();
                                Intent i = new Intent(Login.this, MainActivity.class);
                                startActivity(i);
                                finish();
                                Toast.makeText(Login.this, "BIENVENIDO "+usuarioEntity.getNombres(), Toast.LENGTH_LONG).show();
                            } else {
                                mProgressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Datos Incorrectos", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Revise su conexion a internet", Toast.LENGTH_LONG).show();

                        }
                    });
                }
                    break;

            default:
                    break;
        }
    }

}