package fia.proy2.devmov.Entities;

/**
 * Created by gtufinof on 1/06/2017.
 */

public class InventarioCartaEntity {

    private int id_inventario;
    private int id_carta;
    private int cantidad;
    private int max_cartas;
    private String tipo;
    private String descripcion;
    private String imagen;

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getId_carta() {
        return id_carta;
    }

    public void setId_carta(int id_carta) {
        this.id_carta = id_carta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getMax_cartas() {
        return max_cartas;
    }

    public void setMax_cartas(int max_cartas) {
        this.max_cartas = max_cartas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "InventarioCartaEntity{" +
                "id_inventario=" + id_inventario +
                ", id_carta=" + id_carta +
                ", cantidad=" + cantidad +
                ", max_cartas=" + max_cartas +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }

}
