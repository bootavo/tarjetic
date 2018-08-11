package fia.proy2.devmov.services;

import java.util.List;

import fia.proy2.devmov.entities.PaquetesEntity;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by bootavo on 25/05/2017.
 */

public interface PaqueteCartasInterface {

    @GET("/Paquete_Cartas")
    void obtenerPaqueteCartas(Callback<List<PaquetesEntity>> callback);

}
