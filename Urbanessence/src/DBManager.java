import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class DBManager {
    private static Connection conn = null;

    // Configuración de la conexión a la base de datos
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "urbanessence";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private static final String SQL_GET_CIUDADES = "SELECT * from ciudad";
    private static final String SQL_GET_PRODUCTOS = "SELECT * from producto";
    private static final String SQL_GET_TAREAS = "SELECT * from tareas";
    private static final String SQL_GET_EMPLEADOS = "SELECT * from empleado";

    public static boolean loadDriver() {
        try {
            System.out.print("Cargando Driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("OK!");
            return true;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean connect() {
        try {
            System.out.print("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("OK!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void close() {
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static ResultSet getCiudades () throws SQLException {
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(SQL_GET_CIUDADES);
        return rs;
    }

    public static ResultSet getProductos () throws SQLException {
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(SQL_GET_PRODUCTOS);
        return rs;
    }

    public static ResultSet getTareas() throws SQLException {
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(SQL_GET_TAREAS);
        return rs;
    }


    public static ResultSet getUsuarioID(String usuario, String contrasenya) throws SQLException {
        String query = "SELECT id FROM empleado WHERE usuario = ? AND contrasenya = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, usuario);
        ps.setString(2, contrasenya);
        return ps.executeQuery();
    }



    public static ResultSet getAllDataFromEmployee(int usuarioID) throws SQLException {
        String query = "SELECT * FROM empleado WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, usuarioID);
        return ps.executeQuery();
    }

    public static boolean updateEmployeePassword(int usuarioID, String nuevaContrasenya) throws SQLException {
        String query = "UPDATE empleado SET contrasenya = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, nuevaContrasenya);
        ps.setInt(2, usuarioID);
        return ps.executeUpdate() > 0;
    }

    public static boolean updateEmployee(Empleado empleado) throws SQLException {
        String query = "UPDATE empleado SET cargo = ?, salario = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, empleado.getCargo());
            ps.setDouble(2, empleado.getSalario());
            ps.setInt(3, empleado.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public static ResultSet getEmpleados() throws SQLException {
        Statement stat = conn.createStatement();
        return stat.executeQuery(SQL_GET_EMPLEADOS);
    }


    public static boolean insertarNuevoEmpleado(Empleado empleado) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into empleado(nombre, apellidos, fechaNac, direccion, fechaCont, cargo, salario, NUSS, idTienda, usuario, contrasenya) VALUES(?,?,?,?,?,?,?,?,?,?,?);");
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidos());
            ps.setDate(3, Date.valueOf(empleado.getFechaNac()));
            ps.setString(4, empleado.getDireccion());
            ps.setDate(5, Date.valueOf(empleado.getFechaCont()));
            ps.setString(6, empleado.getCargo());
            ps.setDouble(7, empleado.getSalario());
            ps.setString(8, empleado.getNuss());
            ps.setInt(9, empleado.getIdTienda());
            ps.setString(10, empleado.getUsuario());
            ps.setString(11, empleado.getContrasenya());
            return !ps.execute(); //solo devuelve true si el resultado es un resultset
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR AL INSERTAR LA TAREA EN LA BD: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteEmpleado(Empleado empleado) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from empleado where id = ?;");
            ps.setInt(1, empleado.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }
}
