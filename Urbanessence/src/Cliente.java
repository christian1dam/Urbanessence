import java.sql.Date;
import java.time.LocalDate;

public class Cliente {
    private int id;
    private String nombre;
    private byte genero;
    private String direccion;
    private int telefono;
    private LocalDate fechaNac;

    public Cliente(String nombre, byte genero, String direccion, int telefono, LocalDate fechaNac) {
        this.nombre = nombre;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
    }

    public Cliente(int id, String nombre, byte genero, String direccion, int telefono, LocalDate fechaNac) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
    }



    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public byte getGenero() {
        return genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public Date getFechaNac() {
        return Date.valueOf(fechaNac);
    }
}
