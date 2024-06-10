public class Proveedor {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String NIF;
    private String correo;
    private int IDCiudad;

    public Proveedor(int id, String nombre, String direccion, String telefono, String NIF, String correo, int IDCiudad) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.NIF = NIF;
        this.correo = correo;
        this.IDCiudad = IDCiudad;
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

    public String getNIF() {
        return NIF;
    }

    public String getCorreo() {
        return correo;
    }

    public int getIDCiudad() {
        return IDCiudad;
    }
}
