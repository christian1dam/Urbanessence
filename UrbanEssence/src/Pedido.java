import java.util.Date;

public class Pedido {
//    "ID", "Fecha", "Total pedido", "Estado", "Cliente ID", "ProductoID"
        private int id;
        private Date fecha;
        private Double totalPedido;
        private int estado;
        private int idCliente;
        private int idEmpleado;

    public Pedido(int id, Date fecha, Double totalPedido, int estado, int idCliente, int idEmpleado) {
        this.id = id;
        this.fecha = fecha;
        this.totalPedido = totalPedido;
        this.estado = estado;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", totalPedido=" + totalPedido +
                ", estado=" + estado +
                ", idCliente=" + idCliente +
                ", idEmpleado=" + idEmpleado +
                '}';
    }
}
