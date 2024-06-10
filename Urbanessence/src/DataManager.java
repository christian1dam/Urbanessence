import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static Integer usuarioID = null;

    private static  Empleado empleado;

    private static List<Empleado> listEmpleados = new ArrayList<>();

    public static int getUsuarioID(String usuario, String contrasenya) {
        if (DBManager.connect()){
            try (ResultSet rs = DBManager.getUsuarioID(usuario, contrasenya)) {
                if (rs.next()) {  // Check if the result set is not empty
                    usuarioID = rs.getInt(1);
                } else {
                    usuarioID = null; // Explicitly set to null if no data found
                }
                rs.close();
                DBManager.close();
                System.out.println(usuarioID);
                return usuarioID != null ? usuarioID : -1; // Return -1 if usuarioID is null
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public static Empleado getAllDataFromEmployee(int usuarioID) {
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getAllDataFromEmployee(usuarioID);
                while (rs.next()){
                    empleado = new Empleado(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getString(5),
                            rs.getDate(6).toLocalDate(),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getString(9),
                            rs.getInt(10),
                            rs.getString(11),
                            rs.getString(12)
                    );
                }
                rs.close();
                DBManager.close();
                System.out.println(empleado);
                return empleado;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }

    public static boolean updateEmployeeData(int usuarioID, String nuevaContrasenya) {
        try {
            if(DBManager.connect() && DBManager.updateEmployeePassword(usuarioID, nuevaContrasenya)){
                DBManager.close();
                return true;
            } else {
                DBManager.close();
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Empleado> getListaEmpleados() {
        return listEmpleados;
    }

    public static boolean getEmpleados(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getEmpleados();
                while (rs.next()){
                    listEmpleados.add(new Empleado(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getString(5),
                            rs.getDate(6).toLocalDate(),
                            rs.getString(7),
                            rs.getDouble(8),
                            rs.getString(9),
                            rs.getInt(10),
                            rs.getString(11),
                            rs.getString(12)
                    ));
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

    public static boolean insertarNuevoEmpleado(Empleado empleado) {
        if(DBManager.connect() && DBManager.insertarNuevoEmpleado(empleado)){
            DBManager.close();
            return true;
        } else {
            DBManager.close();
            return false;
        }
    }

    public static boolean updateEmployeeSalarioAndCargo(Empleado empleado) {
        try {
            if(DBManager.connect() && DBManager.updateEmployee(empleado)) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean deleteEmpleado(Empleado empleado) {
        if (DBManager.connect() && DBManager.deleteEmpleado(empleado)){
            DBManager.close();
            return true;
        } else return false;
    }

    private static ArrayList<Ciudad> listCiudades = new ArrayList<>();
    private static ArrayList<Producto> listProductos = new ArrayList<>();

    /*** CIUDADES ***/
    private static ArrayList<Ciudad> ciudades = new ArrayList<>();

    public static boolean getCiudades(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getCiudades();
                while (rs.next()){
                    ciudades.add(new Ciudad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                }
                rs.close();
                //DBManager.close();
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
    }

    /*** PRODUCTOS ***/
    private static ArrayList<Producto> productos = new ArrayList<>();

    public static boolean getProductos(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getProductos();
                while (rs.next()){
                    productos.add(new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
                }
                rs.close();
                //DBManager.close();
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
    }


    private static ArrayList<Cliente> listClientes = new ArrayList<>();
    private static ArrayList<Ciudad> listCiudades = new ArrayList<>();
    private static ArrayList<Producto> listProductos = new ArrayList<>();

    public static boolean getClientes(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getClientes();
                while (rs.next()){
                    listClientes.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getByte(3), rs.getString(4), rs.getInt(5), rs.getDate(6).toLocalDate()));
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

    public static int addCliente (Cliente cliente){
        if (DBManager.connect()){
            try {
                int resultado = DBManager.addCliente(cliente);
                if (resultado > 0){
                    listClientes.add(cliente);
                }
                DBManager.close();
                return resultado;
            } catch (SQLException e) {
                e.printStackTrace();
                DBManager.close();
            }
        }
        return 0;
    }

    public static void editCliente (Cliente cliente){
        DBManager.connect();
        try {
            DBManager.editCliente(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList getListaClientes (){
        return listClientes;
    }

    public static int borrarCliente(int id){
        int rs=0;
        if (DBManager.connect()){
            try {
                rs = DBManager.borrarCliente(id);

                if (rs == 1){
                    for (int i = 0; i < listClientes.size(); i++) {
                        if (listClientes.get(i).getId() == id){
                            listClientes.remove(i);
                        }
                    }
                }
            }catch (SQLException e){
                DBManager.close();
                e.printStackTrace();
                return 0;
            }
        }
        return rs;
    }



    /*** TIENDAS ***/
    private static ArrayList<Tienda> tiendas = new ArrayList<>();

    public static boolean getTiendas(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getTiendas();
                while (rs.next()){
                    tiendas.add(new Tienda(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTime(5).toLocalTime(), rs.getTime(6).toLocalTime(), rs.getInt(7), rs.getInt(8)));
                }
                rs.close();
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

    public static void addTienda(String nom, String dir, String tlf, LocalTime hApertura, LocalTime hCierre, int numEmpleados, int idCiudad) {
        DBManager.addTienda(nom, dir, tlf, hApertura, hCierre, numEmpleados, idCiudad);
    }

    public static void editarTienda(int id, String nom, String dir, String tlf, LocalTime hApertura, LocalTime hCierre, int numEmpleados, int idCiudad) {
        DBManager.editarTienda(id, nom, dir, tlf, hApertura, hCierre, numEmpleados, idCiudad);
    }

    public static void borrarTienda(int id) {
        DBManager.borrarTienda(id);
    }

    public static int idAutoGenTienda() {
        return DBManager.idAutoGenTienda();
    }



    public static boolean getCiudades(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getCiudades();
                while (rs.next()){
                    listCiudades.add(new Ciudad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                }
                rs.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList getListaCiudades(){
        return listCiudades;
    }

    public static boolean getProductos(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getProductos();
                while (rs.next()){
                    listProductos.add(new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
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

    public static ArrayList getListaProductos(){
        return listProductos;
    }


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
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList<String> obtenerNombresTipo() {
        return DBManager.obtenerNombresTipo();
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

    /*** PROVEEDORES ***/
    private static ArrayList<Proveedor> proveedores = new ArrayList<>();

    public static boolean getProveedores(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getProveedores();
                while (rs.next()){
                    proveedores.add(new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
                }
                rs.close();
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

    public static void addProveedor(String nom, String dir, String tlf, String NIF, String correo, int IDCiudad) {
        DBManager.addProveedor(nom, dir, NIF, tlf, correo, IDCiudad);
    }

    public static void editarProveedor(int id, String nom, String dir, String tlf, String NIF, String correo, int IDCiudad) {
        DBManager.editarProveedor(id, nom, correo, NIF, tlf, dir, IDCiudad);
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
}