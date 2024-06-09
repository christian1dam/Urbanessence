import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class DataManager {
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
}

