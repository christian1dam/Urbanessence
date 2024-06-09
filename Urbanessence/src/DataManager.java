import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataManager {
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
}
