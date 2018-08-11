package fia.proy2.devmov.Interfaces;

import java.util.List;

import fia.proy2.devmov.Entities.PaquetesEntity;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by bootavo on 25/05/2017.
 */

public interface PaqueteCartasInterface {

    @GET("/Paquete_Cartas")
    void obtenerPaqueteCartas(Callback<List<PaquetesEntity>> callback);

}
