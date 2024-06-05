import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DataManager {

    /*** CLIENTES ***/
    /*private static ArrayList<Cliente> clientes = new ArrayList<>();

    public static boolean getClientes(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getClientes();
                while (rs.next()){
                    clientes.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getByte(3), rs.getString(4), rs.getInt(5), rs.getDate(6).toLocalDate()));
                }
                rs.close();
                DBManager.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList<Cliente> getListaClientes(){
        return clientes;
    }

    public static void addCliente(String nom, byte gen, String dir, int tlf, LocalDate fecha) {
        DBManager.addCliente(nom, gen, dir, tlf, fecha);
    }

    public static void editarCliente(int id, String nom, byte gen, String dir, int tlf, LocalDate fecha) {
        DBManager.editarCliente(id, nom, gen, dir, tlf, fecha);
    }

    public static void borrarCliente(int id) {
        DBManager.borrarCliente(id);
    }

    public static int idAutoGenCliente() {
        return DBManager.idAutoGenCliente();
    }*/

    /*** TIPOS ***/
    private static ArrayList<Tipo> tipos = new ArrayList<>();

    public static boolean getTipos(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getTipos();
                while (rs.next()){
                    tipos.add(new Tipo(rs.getInt(1), rs.getString(2)));
                }
                rs.close();
                DBManager.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList<Tipo> getListaTipos(){
        return tipos;
    }

    public static void addTipo(String nom) {
        DBManager.addTipo(nom);
    }

    public static void editarTipo(int id, String nom) {
        DBManager.editarTipo(id, nom);
    }

    public static void borrarTipo(int id) {
        DBManager.borrarTipo(id);
    }

    public static int idAutoGenTipo() {
        return DBManager.idAutoGenTipo();
    }

    /*** CIUDADES ***/
    /*private static ArrayList<Ciudad> ciudades = new ArrayList<>();

    public static boolean getCiudades(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getCiudades();
                while (rs.next()){
                    ciudades.add(new Ciudad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                }
                rs.close();
                DBManager.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList<Ciudad> getListaCiudades(){
        return ciudades;
    }

    public static void addCiudad(String nom, String prov, int numHabitantes) {
        DBManager.addCiudad(nom, prov, numHabitantes);
    }

    public static void editarCiudad(int id, String nom, String prov, int numHabitantes) {
        DBManager.editarCiudad(id, nom, prov, numHabitantes);
    }

    public static void borrarCiudad(int id) {
        DBManager.borrarCiudad(id);
    }

    public static int idAutoGenCiudad() {
        return DBManager.idAutoGenCiudad();
    }*/

    /*** TIENDAS ***/
    /*private static ArrayList<Tienda> tiendas = new ArrayList<>();

    public static boolean getTiendas(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getTiendas();
                while (rs.next()){
                    tiendas.add(new Tienda(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTime(5).toLocalTime(), rs.getTime(6).toLocalTime(), rs.getInt(7), rs.getInt(8)));
                }
                rs.close();
                DBManager.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList<Tienda> getListaTiendas(){
        return tiendas;
    }

    public static void addTienda(String nom, String dir, LocalTime hApertura, LocalTime hCierre, int numEmpleados, int idCiudad) {
        DBManager.addTienda(nom, dir, hApertura, hCierre, numEmpleados, idCiudad);
    }

    public static void editarTienda(int id, String nom, String dir, LocalTime hApertura, LocalTime hCierre, int numEmpleados, int idCiudad) {
        DBManager.editarTienda(id, nom, dir, hApertura, hCierre, numEmpleados, idCiudad);
    }

    public static void borrarTienda(int id) {
        DBManager.borrarTienda(id);
    }

    public static int idAutoGenTienda() {
        return DBManager.idAutoGenTienda();
    }*/

    /*** EMPLEADOS ***/
    /*private static ArrayList<Empleado> empleados = new ArrayList<>();

    public static boolean getEmpleados(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getEmpleados();
                while (rs.next()){
                    empleados.add(new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getDouble(8), rs.getString(9), rs.getInt(10)));
                }
                rs.close();
                DBManager.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList<Empleado> getListaEmpleados(){
        return empleados;
    }

    public static void addEmpleado(String nom, String apell, LocalDate fechaNac, String dir, LocalDate fechaCont, String cargo, double salario, String NUSS, int idTienda) {
        DBManager.addEmpleado(nom, apell, fechaNac, dir, fechaCont, cargo, salario, NUSS, idTienda);
    }

    public static void editarEmpleado(int id, String nom, String apell, LocalDate fechaNac, String dir, LocalDate fechaCont, String cargo, double salario, String NUSS, int idTienda) {
        DBManager.editarEmpleado(id, nom, apell, fechaNac, dir, fechaCont, cargo, salario, NUSS, idTienda);
    }

    public static void borrarEmpleado(int id) {
        DBManager.borrarEmpleado(id);
    }

    public static int idAutoGenEmpleado() {
        return DBManager.idAutoGenEmpleado();
    }*/

    /*** PRODUCTOS ***/
    /*private static ArrayList<Producto> productos = new ArrayList<>();

    public static boolean getProductos(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getProductos();
                while (rs.next()){
                    productos.add(new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
                }
                rs.close();
                DBManager.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList<Producto> getListaProductos(){
        return productos;
    }

    public static void addProducto(String nom, String marca, String talla, String color, String material, double precio, int idTipo, int idProveedor, int cantidad) {
        DBManager.addProducto(nom, marca, talla, color, material, precio, idTipo, idProveedor, cantidad);
    }

    public static void editarProducto(int id, String nom, String marca, String talla, String color, String material, double precio, int idTipo, int idProveedor, int cantidad) {
        DBManager.editarProducto(id, nom, marca, talla, color, material, precio, idTipo, idProveedor, cantidad);
    }

    public static void borrarProducto(int id) {
        DBManager.borrarProducto(id);
    }

    public static int idAutoGenProducto() {
        return DBManager.idAutoGenProducto();
    }*/

    /*** PROVEEDORES ***/
    private static ArrayList<Proveedor> proveedores = new ArrayList<>();

    public static boolean getProveedores(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getProveedores();
                while (rs.next()){
                    proveedores.add(new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
                }
                rs.close();
                DBManager.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList<Proveedor> getListaProveedores(){
        return proveedores;
    }

    public static void addProveedor(String nom, String dir, int tlf, String NIF, String correo, LocalDate fecha) {
        DBManager.addProveedor(nom, dir, tlf, NIF, correo, fecha);
    }

    public static void editarProveedor(int id, String nom, String dir, int tlf, String NIF, String correo, LocalDate fecha) {
        DBManager.editarProveedor(id, nom, dir, tlf, NIF, correo, fecha);
    }

    public static void borrarProveedor(int id) {
        DBManager.borrarProveedor(id);
    }

    public static int idAutoGenProveedor() {
        return DBManager.idAutoGenProveedor();
    }

    /*** PEDIDOS ***/
    private static ArrayList<Pedido> pedidos = new ArrayList<>();

    public static boolean getPedidos(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getPedidos();
                while (rs.next()){
                    pedidos.add(new Pedido(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getDouble(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
                }
                rs.close();
                DBManager.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList<Pedido> getListaPedidos(){
        return pedidos;
    }

    public static void editarPedido(int id, LocalDate fecha, double totalPedido, int estado, int idCliente, int idEmpleado) {
        DBManager.editarPedido(id, fecha, totalPedido, estado, idCliente, idEmpleado);
    }

    public static void borrarPedido(int id) {
        DBManager.borrarPedido(id);
    }

    public static int idAutoGenPedido() {
        return DBManager.idAutoGenPedido();
    }
}