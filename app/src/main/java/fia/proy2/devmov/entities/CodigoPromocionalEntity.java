package fia.proy2.devmov.entities;

/**
 * Created by bootavo on 25/05/2017.
 */

public class CodigoPromocionalEntity {

    private int id_canje;
    private String codigo;
    private int cantidad_fichas;
    private String estado_cupon;

    public int getId_canje() {
        return id_canje;
    }

    public void setId_canje(int id_canje) {
        this.id_canje = id_canje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad_fichas() {
        return cantidad_fichas;
    }

    public void setCantidad_fichas(int cantidad_fichas) {
        this.cantidad_fichas = cantidad_fichas;
    }

    public String getEstado_cupon() {
        return estado_cupon;
    }

    public void setEstado_cupon(String estado_cupon) {
        this.estado_cupon = estado_cupon;
    }

    @Override
    public String toString() {
        return "CodigoPromocionalEntity{" +
                "id_canje=" + id_canje +
                ", codigo='" + codigo + '\'' +
                ", cantidad_fichas=" + cantidad_fichas +
                ", estado_cupon='" + estado_cupon + '\'' +
                '}';
    }

}
