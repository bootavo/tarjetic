package fia.proy2.devmov.Entities;

/**
 * Created by bootavo on 25/05/2017.
 */

public class InventarioEntity {

    private int id_inventario;
    private int id_usuario;
    private int total_fichas;
    private int total_tarjetas_oro;
    private int total_tarjetas_plata;
    private int total_tarjetas_bronce;

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getTotal_fichas() {
        return total_fichas;
    }

    public void setTotal_fichas(int total_fichas) {
        this.total_fichas = total_fichas;
    }

    public int getTotal_tarjetas_oro() {
        return total_tarjetas_oro;
    }

    public void setTotal_tarjetas_oro(int total_tarjetas_oro) {
        this.total_tarjetas_oro = total_tarjetas_oro;
    }

    public int getTotal_tarjetas_plata() {
        return total_tarjetas_plata;
    }

    public void setTotal_tarjetas_plata(int total_tarjetas_plata) {
        this.total_tarjetas_plata = total_tarjetas_plata;
    }

    public int getTotal_tarjetas_bronce() {
        return total_tarjetas_bronce;
    }

    public void setTotal_tarjetas_bronce(int total_tarjetas_bronce) {
        this.total_tarjetas_bronce = total_tarjetas_bronce;
    }

    @Override
    public String toString() {
        return "InventarioEntity{" +
                "id_inventario=" + id_inventario +
                ", id_usuario=" + id_usuario +
                ", total_fichas=" + total_fichas +
                ", total_tarjetas_oro=" + total_tarjetas_oro +
                ", total_tarjetas_plata=" + total_tarjetas_plata +
                ", total_tarjetas_bronce=" + total_tarjetas_bronce +
                '}';
    }

}
