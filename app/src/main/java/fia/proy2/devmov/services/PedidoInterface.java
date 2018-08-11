package fia.proy2.devmov.services;

import java.util.List;
import fia.proy2.devmov.entities.PedidoEntity;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by james on 26/05/2017.
 */

public interface PedidoInterface {
    @GET("/Pedido")
    void getPedidos(@Query("id_usuario") int id_usuario, Callback<List<PedidoEntity>> callback);

    @FormUrlEncoded
    @POST("/Pedido")
    void registrarDelivery(@Field("id_producto") int id_producto, @Field("cantidad") int cantidad, @Field("pago_efectivo") double pago_efectivo, @Field("pago_oro") int pago_oro, @Field("pago_plata") int pago_plata, @Field("pago_bronce") int pago_bronce, @Field("direccion") String direccion, @Field("telefono_contacto") String telefono_contacto, @Field("id_usuario") int id_usuario, Callback<PedidoEntity> callback);
}
