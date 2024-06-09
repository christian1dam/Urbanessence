import java.time.LocalTime;

public class Tienda {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private LocalTime hApertura;
    private LocalTime hCierre;
    private int numEmpleados;
    private int idCiudad;

    public Tienda(int id, String nombre, String direccion, String telefono, LocalTime hApertura, LocalTime hCierre, int numEmpleados, int idCiudad) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.hApertura = hApertura;
        this.hCierre = hCierre;
        this.numEmpleados = numEmpleados;
        this.idCiudad = idCiudad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalTime gethApertura() {
        return hApertura;
    }

    public LocalTime gethCierre() {
        return hCierre;
    }

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public int getIdCiudad() {
        return idCiudad;
    }
}
