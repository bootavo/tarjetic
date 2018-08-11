package fia.proy2.devmov.services;

import fia.proy2.devmov.entities.CodigoPromocionalEntity;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by bootavo on 25/05/2017.
 */

public interface CodigoPromocionalInterface {

    @GET("/Codigo_Promocional")
    void obtenerFichasXCodigo(@Query("codigo") String codigo, @Query("id_usuario") int id_usuario, Callback<CodigoPromocionalEntity> callback);

}
