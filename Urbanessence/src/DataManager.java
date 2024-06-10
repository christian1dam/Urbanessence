import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
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
}
