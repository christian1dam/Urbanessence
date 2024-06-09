import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static ArrayList<Ciudad> listCiudades = new ArrayList<>();
    private static ArrayList<Producto> listProductos = new ArrayList<>();
    private static List<Tarea> listTareas = new ArrayList<>();


    public static boolean getCiudades(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getCiudades();
                while (rs.next()){
                    listCiudades.add(new Ciudad(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
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

    public static boolean crearTarea(Tarea tarea) {
        if(DBManager.openConnectionToDatabase() && DBManager.crearTarea(tarea)){
            DBManager.closeConnectioToDatabase();
            return true;
        } else {
            DBManager.closeConnectioToDatabase();
            return false;
        }
    }

    public static boolean getTareas(){
        if (DBManager.openConnectionToDatabase()){
            try {
                ResultSet rs = DBManager.getTareas();
                while (rs.next()){
                    listTareas.add(
                            new Tarea(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getDate(4).toLocalDate(),
                                    rs.getDate(5).toLocalDate(),
                                    rs.getInt(6)
                            ));
                }
                rs.close();
                DBManager.closeConnectioToDatabase();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static List<Tarea> getListaTareas() { return listTareas;}
}
