package fia.proy2.devmov.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import fia.proy2.devmov.Fragments.CanjearCartasFragment;
import fia.proy2.devmov.Fragments.CodigoPromocionalFragment;
import fia.proy2.devmov.Fragments.DeliveryFragment;
import fia.proy2.devmov.Fragments.InicioFragment;
import fia.proy2.devmov.Fragments.InventarioFragment;
import fia.proy2.devmov.Fragments.PedidoFragment;
import fia.proy2.devmov.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences prefs;
    public static long usuario_id = 0;
    public static int fichas_totalM = 0;
    public static TextView nav_user = null;

    //public TextView fichasTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PREFS TOTAL FICHAS
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        fichas_totalM = Login.fichas_total;

        //if (fichas_totalM == 0){
        //    fichas_totalM = prefs.getInt("fichas_total", 0);
        //}

        //nom_usuL = (TextView) findViewById(R.id.nom_usu);
        //nom_usuL.setText("asdas");

        //email_usuL = (TextView) findViewById(R.id.email_usu);
        //email_usuL.setText("asd");
        // END EDITAR TEXTP DEL MENU LATERL

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Version de la aplicación 1.0.0", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0);
        nav_user = (TextView) hView.findViewById(R.id.fichas_total);
        nav_user.setText(""+fichas_totalM);

        Log.d("MAIN ACTIVITY","------------->"+fichas_totalM);

        if (savedInstanceState == null) {
            MenuItem item =  navigationView.getMenu().getItem(0).setChecked(true);
            onNavigationItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.inicio) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, InicioFragment.newInstance()).commit();
        } else if (id == R.id.pedidos) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, PedidoFragment.newInstance()).commit();
            prefs =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            usuario_id=prefs.getLong("id", 0);
        } else if (id == R.id.delivery) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, DeliveryFragment.newInstance()).commit();
            prefs =getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            usuario_id=prefs.getLong("id", 0);
        } else if (id == R.id.inventario) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, InventarioFragment.newInstance()).commit();
            prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            usuario_id = prefs.getLong("id", 0);
            fichas_totalM = prefs.getInt("total_fichas", 0);
        }else if (id == R.id.codigo_promocional) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CodigoPromocionalFragment.newInstance()).commit();
            prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            usuario_id = prefs.getLong("id", 0);
            fichas_totalM = prefs.getInt("total_fichas", 0);
        }else if(id == R.id.canjear_cartas){
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, CanjearCartasFragment.newInstance()).commit();
            prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            usuario_id = prefs.getLong("id", 0);
            fichas_totalM = prefs.getInt("total_fichas", 0);
        }else if(id == R.id.salir){
            System.exit(0);
            prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            edit.clear();
            edit.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
