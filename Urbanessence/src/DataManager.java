import com.sun.management.UnixOperatingSystemMXBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static Integer usuarioID = null;

    private static  Empleado empleado;

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

    public static int getUsuarioID(String usuario, String contrasenya) {
        if (DBManager.openConnectionToDatabase()){
            try (ResultSet rs = DBManager.getUsuarioID(usuario, contrasenya)) {
                if (rs.next()) {  // Check if the result set is not empty
                    usuarioID = rs.getInt(1);
                } else {
                    usuarioID = null; // Explicitly set to null if no data found
                }
                rs.close();
                DBManager.closeConnectioToDatabase();
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
        if (DBManager.openConnectionToDatabase()){
            try {
                ResultSet rs = DBManager.getAllDataFromEmployee(usuarioID);
                while (rs.next()){
                        empleado = new Empleado(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getDate(4),
                                rs.getString(5),
                                rs.getDate(6),
                                rs.getString(7),
                                rs.getDouble(8),
                                rs.getString(9),
                                rs.getInt(10),
                                rs.getString(11),
                                rs.getString(12)
                        );
                }
                rs.close();
                DBManager.closeConnectioToDatabase();
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
            if(DBManager.openConnectionToDatabase() && DBManager.updateEmployeeData(usuarioID, nuevaContrasenya)){
                DBManager.closeConnectioToDatabase();
                return true;
            } else {
                DBManager.closeConnectioToDatabase();
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
