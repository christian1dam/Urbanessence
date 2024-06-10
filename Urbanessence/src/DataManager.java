import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DataManager {

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