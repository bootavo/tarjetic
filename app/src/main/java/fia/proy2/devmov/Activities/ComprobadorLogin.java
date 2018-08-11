package fia.proy2.devmov.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import fia.proy2.devmov.MainActivity;
import fia.proy2.devmov.R;

/**
 * Created by bootavo on 24/05/2017.
 */

public class ComprobadorLogin extends Activity {

    SharedPreferences prefs;
    long id;
    String usuario;
    int total_fichas;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loader);
        Intent i;
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        id = prefs.getLong("id", 0);
        usuario = prefs.getString("usuario", null);
        total_fichas = prefs.getInt("total_fichas", 0);
        if(id!=0 && usuario!=null){
            i = new Intent(this,MainActivity.class);
        } else {
            i = new Intent(this, Login.class);
        }
        startActivity(i);
        finish();
    }

}
