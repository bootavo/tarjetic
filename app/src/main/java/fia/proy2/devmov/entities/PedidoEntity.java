package fia.proy2.devmov.entities;

/**
 * Created by james on 25/05/2017.
 */

public class PedidoEntity {
    private int id_pedido;
    private int id_producto;
    private String nombre_producto;
    private int cantidad;
    private double pago_efectivo;
    private int pago_oro;
    private int pago_plata;
    private int pago_bronce;
    private String direccion;
    private String telefono_contacto;
    private long id_usuario;
    private String fecha;
    private int estado;

    public PedidoEntity(int id_pedido, int id_producto, String nombre_producto, int cantidad, double pago_efectivo, int pago_oro, int pago_plata, int pago_bronce, String direccion, String telefono_contacto, long id_usuario, String fecha, int estado) {
        this.id_pedido = id_pedido;
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.cantidad = cantidad;
        this.pago_efectivo = pago_efectivo;
        this.pago_oro = pago_oro;
        this.pago_plata = pago_plata;
        this.pago_bronce = pago_bronce;
        this.direccion = direccion;
        this.telefono_contacto = telefono_contacto;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPago_efectivo() {
        return pago_efectivo;
    }

    public void setPago_efectivo(double pago_efectivo) {
        this.pago_efectivo = pago_efectivo;
    }

    public int getPago_oro() {
        return pago_oro;
    }

    public void setPago_oro(int pago_oro) {
        this.pago_oro = pago_oro;
    }

    public int getPago_plata() {
        return pago_plata;
    }

    public void setPago_plata(int pago_plata) {
        this.pago_plata = pago_plata;
    }

    public int getPago_bronce() {
        return pago_bronce;
    }

    public void setPago_bronce(int pago_bronce) {
        this.pago_bronce = pago_bronce;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono_contacto() {
        return telefono_contacto;
    }

    public void setTelefono_contacto(String telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "id_pedido=" + id_pedido +
                ", id_producto=" + id_producto +
                ", nombre_producto=" + nombre_producto +
                ", cantidad=" + cantidad +
                ", pago_efectivo=" + pago_efectivo +
                ", pago_oro=" + pago_oro +
                ", pago_plata=" + pago_plata +
                ", pago_bronce=" + pago_bronce +
                ", direccion='" + direccion + '\'' +
                ", telefono_contacto='" + telefono_contacto + '\'' +
                ", id_usuario=" + id_usuario +
                ", fecha='" + fecha + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
