package fia.proy2.devmov.Entities;

/**
 * Created by bootavo on 24/05/2017.
 */

public class UsuarioEntity {

    private long id_usuario;
    private String usuario;
    private String clave;
    private int id_rol;
    private int estado;
    private String nombres;
    private String imagen;
    private int total_fichas;

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getTotal_fichas() {
        return total_fichas;
    }

    public void setTotal_fichas(int total_fichas) {
        this.total_fichas = total_fichas;
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id_usuario=" + id_usuario +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", id_rol=" + id_rol +
                ", estado=" + estado +
                ", nombres='" + nombres + '\'' +
                ", imagen='" + imagen + '\'' +
                ", total_fichas=" + total_fichas +
                '}';
    }

}
