package fia.proy2.devmov.Interfaces;

import java.util.List;

import fia.proy2.devmov.Entities.ProductoEntity;
import fia.proy2.devmov.Entities.ProductoPromocionEntity;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by james on 26/05/2017.
 */

public interface ProductoInterface {
    @GET("/Producto")
    void getProdutos(Callback<List<ProductoEntity>> callback);

    @GET("/Producto_Promocion")
    void getProdutosPromocion(Callback<List<ProductoPromocionEntity>> callback);
}
