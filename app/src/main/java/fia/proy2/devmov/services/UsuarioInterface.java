package fia.proy2.devmov.services;

import fia.proy2.devmov.entities.UsuarioEntity;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by bootavo on 24/05/2017.
 */

public interface UsuarioInterface {

    @GET("/Login_Android")
    void validarUsuario(@Query("usuario") String usuario, @Query("clave")String clave, Callback<UsuarioEntity> callback);

}
