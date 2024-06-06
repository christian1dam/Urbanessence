public class Proveedor {
    private int id;
    private String nombre;
    private String correo;
    private int NIF;
    private String telefono;
    private String direccion;
    private int IDCiudad;

    public Proveedor(int id, String nombre, String correo, int NIF, String telefono, String direccion, int IDCiudad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.NIF = NIF;
        this.telefono = telefono;
        this.direccion = direccion;
        this.IDCiudad = IDCiudad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getNIF() {
        return NIF;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getIDCiudad() {
        return IDCiudad;
    }
}
