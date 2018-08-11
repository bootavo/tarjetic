package fia.proy2.devmov.entities;

/**
 * Created by james on 25/05/2017.
 */

public class ProductoPromocionEntity {
    private int id_producto_promocion;
    private String nombre;
    private String descripcion;
    private int cartas_oro_requerida;
    private int cartas_plata_requerida;
    private int cartas_bronce_requerida;
    private int estado;

    public ProductoPromocionEntity(int id_producto_promocion, String nombre, String descripcion, int cartas_oro_requerida, int cartas_plata_requerida, int cartas_bronce_requerida, int estado) {
        this.id_producto_promocion = id_producto_promocion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cartas_oro_requerida = cartas_oro_requerida;
        this.cartas_plata_requerida = cartas_plata_requerida;
        this.cartas_bronce_requerida = cartas_bronce_requerida;
        this.estado = estado;
    }

    public int getId_producto_promocion() {
        return id_producto_promocion;
    }

    public void setId_producto_promocion(int id_producto_promocion) {
        this.id_producto_promocion = id_producto_promocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCartas_oro_requerida() {
        return cartas_oro_requerida;
    }

    public void setCartas_oro_requerida(int cartas_oro_requerida) {
        this.cartas_oro_requerida = cartas_oro_requerida;
    }

    public int getCartas_plata_requerida() {
        return cartas_plata_requerida;
    }

    public void setCartas_plata_requerida(int cartas_plata_requerida) {
        this.cartas_plata_requerida = cartas_plata_requerida;
    }

    public int getCartas_bronce_requerida() {
        return cartas_bronce_requerida;
    }

    public void setCartas_bronce_requerida(int cartas_bronce_requerida) {
        this.cartas_bronce_requerida = cartas_bronce_requerida;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ProductoPromocionEntity{" +
                "id_producto_promocion=" + id_producto_promocion +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cartas_oro_requerida=" + cartas_oro_requerida +
                ", cartas_plata_requerida=" + cartas_plata_requerida +
                ", cartas_bronce_requerida=" + cartas_bronce_requerida +
                ", estado=" + estado +
                '}';
    }
}
