package fia.proy2.devmov.Entities;

/**
 * Created by bootavo on 25/05/2017.
 */

public class PaquetesEntity {

    private int id_paquete_cartas;
    private String nombre;
    private String descripcion;
    private int cantidad_cartas;
    private double prob_oro;
    private double prob_plata;
    private double prob_bronce;
    private int fichas_requeridas;
    private String imagen;

    public int getId_paquete_cartas() {
        return id_paquete_cartas;
    }

    public void setId_paquete_cartas(int id_paquete_cartas) {
        this.id_paquete_cartas = id_paquete_cartas;
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

    public int getCantidad_cartas() {
        return cantidad_cartas;
    }

    public void setCantidad_cartas(int cantidad_cartas) {
        this.cantidad_cartas = cantidad_cartas;
    }

    public double getProb_oro() {
        return prob_oro;
    }

    public void setProb_oro(double prob_oro) {
        this.prob_oro = prob_oro;
    }

    public double getProb_plata() {
        return prob_plata;
    }

    public void setProb_plata(double prob_plata) {
        this.prob_plata = prob_plata;
    }

    public double getProb_bronce() {
        return prob_bronce;
    }

    public void setProb_bronce(double prob_bronce) {
        this.prob_bronce = prob_bronce;
    }

    public int getFichas_requeridas() {
        return fichas_requeridas;
    }

    public void setFichas_requeridas(int fichas_requeridas) {
        this.fichas_requeridas = fichas_requeridas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "PaquetesEntity{" +
                "id_paquete_cartas=" + id_paquete_cartas +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad_cartas=" + cantidad_cartas +
                ", prob_oro=" + prob_oro +
                ", prob_plata=" + prob_plata +
                ", prob_bronce=" + prob_bronce +
                ", fichas_requeridas=" + fichas_requeridas +
                ", imagen='" + imagen + '\'' +
                '}';
    }

}
