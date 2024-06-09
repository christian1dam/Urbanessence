import java.util.Date;

public class Empleado {
    private int id;
    private String nombre;
    private String apellidos;
    private Date fechaNac;
    private String direccion;
    private Date fechaCont;
    private String cargo;
    private Double salario;
    private String nuss;
    private int idTienda;
    private String usuario;
    private String contrasenya;

    public Empleado(int id, String nombre, String apellidos, Date fechaNac, String direccion, Date fechaCont, String cargo, Double salario, String nuss, int idTienda, String usuario, String contrasenya) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.fechaCont = fechaCont;
        this.cargo = cargo;
        this.salario = salario;
        this.nuss = nuss;
        this.idTienda = idTienda;
        this.usuario = usuario;
        this.contrasenya = contrasenya;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaCont() {
        return fechaCont;
    }

    public void setFechaCont(Date fechaCont) {
        this.fechaCont = fechaCont;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getNuss() {
        return nuss;
    }

    public void setNuss(String nuss) {
        this.nuss = nuss;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNac=" + fechaNac +
                ", direccion='" + direccion + '\'' +
                ", fechaCont=" + fechaCont +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", nuss='" + nuss + '\'' +
                ", idTienda=" + idTienda +
                '}';
    }
}
