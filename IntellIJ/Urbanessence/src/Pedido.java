import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private LocalDate fecha;
    private String estado;
    private double totalPedido;
    private int idCliente;
    private int idEmpleado;

    public Pedido(int id, LocalDate fecha, String estado, double totalPedido, int idCliente, int idEmpleado) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.totalPedido = totalPedido;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }
}
