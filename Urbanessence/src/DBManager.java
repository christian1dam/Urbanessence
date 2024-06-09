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

    private static int idGenCiudad, idGenProducto;

    private static final String SQL_GET_CIUDADES = "SELECT * from ciudad";
    private static final String SQL_GET_PRODUCTOS = "SELECT * from producto";

    private static final String SQL_INSERT_CIUDADES = "INSERT INTO ciudad (nombre, provincia, numHabitantes) VALUES (?, ?, ?)";
    private static final String SQL_INSERT_PRODUCTOS = "INSERT INTO producto (nombre, marca, talla, color, material, precio, idTipo, idProveedor, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_CIUDADES = "UPDATE ciudad SET nombre = ?, provincia = ?, numHabitantes = ? WHERE id = ?";
    private static final String SQL_UPDATE_PRODUCTOS = "UPDATE producto SET nombre = ?, marca = ?, talla = ?, color = ?, material = ?, precio = ?, idTipo = ?, idProveedor = ?, cantidad = ? WHERE id = ?";

    private static final String SQL_DELETE_CIUDADES = "DELETE FROM ciudad WHERE id = ?";
    private static final String SQL_DELETE_PRODUCTOS = "DELETE FROM producto WHERE id = ?";

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
/*
    public static void close() {
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }*/

    /*** CIUDADES ***/
    public static ResultSet getCiudades () throws SQLException {
        ResultSet rs = null;
        try {
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(SQL_GET_CIUDADES);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void addCiudad(String nom, String prov, int numHabitantes) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_CIUDADES, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nom);
            pstmt.setString(2, prov);
            pstmt.setInt(3, numHabitantes);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idGenCiudad = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editarCiudad(int id, String nom, String prov, int numHabitantes) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_CIUDADES);
            pstmt.setString(1, nom);
            pstmt.setString(2, prov);
            pstmt.setInt(3, numHabitantes);
            pstmt.setInt(4, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarCiudad(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_CIUDADES);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int idAutoGenCiudad() {
        return idGenCiudad;
    }

    /*** PRODUCTOS ***/
    public static ResultSet getProductos () throws SQLException {
        ResultSet rs = null;
        try {
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(SQL_GET_PRODUCTOS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void addProducto(String nom, String marca, String talla, String color, String material, double precio, int idTipo, int idProveedor, int cantidad) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_PRODUCTOS, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nom);
            pstmt.setString(2, marca);
            pstmt.setString(3, talla);
            pstmt.setString(4, color);
            pstmt.setString(5, material);
            pstmt.setDouble(6, precio);
            pstmt.setInt(7,idTipo);
            pstmt.setInt(8, idProveedor);
            pstmt.setInt(9, cantidad);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idGenProducto = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editarProducto(int id, String nom, String marca, String talla, String color, String material, double precio, int idTipo, int idProveedor, int cantidad) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_PRODUCTOS);
            pstmt.setString(1, nom);
            pstmt.setString(2, marca);
            pstmt.setString(3, talla);
            pstmt.setString(4, color);
            pstmt.setString(5, material);
            pstmt.setDouble(6, precio);
            pstmt.setInt(7,idTipo);
            pstmt.setInt(8, idProveedor);
            pstmt.setInt(9, cantidad);
            pstmt.setInt(10, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarProducto(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_PRODUCTOS);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int idAutoGenProducto() {
        return idGenProducto;
    }
}