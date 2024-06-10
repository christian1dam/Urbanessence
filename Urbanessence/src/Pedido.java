import java.time.LocalDate;

public class Pedido {
    private int id;
    private LocalDate fecha;
    private double totalPedido;
    private String estado;
    private int idCliente;
    private int idEmpleado;

    public Pedido(int id, LocalDate fecha, double totalPedido, int estado, int idCliente, int idEmpleado) {
        this.id = id;
        this.fecha = fecha;
        this.totalPedido = totalPedido;
        setEstado(estado);
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        if (estado == 0 || estado == 1) {
            if (estado == 0) this.estado = "No entregado";
            else this.estado = "Entregado";
        }
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }
}
