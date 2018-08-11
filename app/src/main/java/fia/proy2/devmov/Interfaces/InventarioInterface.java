package fia.proy2.devmov.Interfaces;

import java.util.List;

import fia.proy2.devmov.Entities.CartaEntity;
import fia.proy2.devmov.Entities.InventarioCartaEntity;
import fia.proy2.devmov.Entities.InventarioEntity;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by bootavo on 25/05/2017.
 */

public interface InventarioInterface {

    @GET("/Carta")
    void obtenerCartasInventario(Callback<List<CartaEntity>> callback);

    @GET("/Inventario")
    void canjearPaquete(@Query("id_usuario") int id_usuario, @Query("fichas_usadas") int fichas_usadas, @Query("can_car_oro") int can_car_oro, @Query("can_car_pla") int can_car_pla, @Query("can_car_bro") int can_car_bro, Callback<InventarioEntity> callback);

    @GET("/InventarioCarta")
    void registrarCartas(@Query("accion") String accion, @Query("id_usuario") int id_usuario, @Query("id_car1") int id_car1, @Query("id_car2") int id_car2, @Query("id_car3") int id_car3, @Query("id_car4") int id_car4, @Query("id_car5") int id_car5, Callback<InventarioEntity> callback);

    @GET("/InventarioCarta")
    void obtenerCartasInventarioXUsuario(@Query("accion") String accion, @Query("id_usuario") int id_usuario, @Query("tipo") String tipo,  Callback<List<InventarioCartaEntity>> callback);

    @GET("/InventarioSaldo")
    void obtenerDatosInventario(@Query("id_usuario") int id_usuario,  Callback<InventarioEntity> callback);


}
