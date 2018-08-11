package fia.proy2.devmov.entities;

/**
 * Created by bootavo on 25/05/2017.
 */

public class CartaEntity {

    private int id_carta;
    private String tipo;
    private String imagen;

    public int getId_carta() {
        return id_carta;
    }

    public void setId_carta(int id_carta) {
        this.id_carta = id_carta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "CartaEntity{" +
                "id_carta=" + id_carta +
                ", tipo='" + tipo + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }

}
