public class Producto {
    private int id;
    private String nombre;
    private String marca;
    private String talla;
    private String color;
    private String material;
    private double precio;
    private int idTipo;
    private int idProveedor;
    private int cantidad;

    public Producto(int id, String nombre, String marca, String talla, String color, String material, double precio, int idTipo, int idProveedor, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.talla = talla;
        this.color = color;
        this.material = material;
        this.precio = precio;
        this.idProveedor = idProveedor;
        this.idTipo = idTipo;
        this.cantidad = cantidad;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
