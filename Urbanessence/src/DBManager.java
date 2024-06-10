import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DBManager {
    private static Connection conn = null;

    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "urbanessence";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static int idGenTipo, idGenProveedor;

    private static final String SQL_GET_PEDIDOS = "SELECT * FROM pedido";
    private static final String SQL_GET_PROVEEDORES = "SELECT * FROM proveedor";
    private static final String SQL_GET_TIPO = "SELECT * FROM tipo";

    private static final String SQL_INSERT_TIPOS = "INSERT INTO tipo (nombre) VALUES (?)";
    private static final String SQL_INSERT_PROVEEDORES = "INSERT INTO proveedor (nombre, direccion, telefono, NIF, correoElectronico, ciudadID) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_TIPOS = "UPDATE tienda SET nombre = ? WHERE id = ?";
    private static final String SQL_UPDATE_PROVEEDORES = "UPDATE proveedor SET nombre = ?, direccion = ?, telefono = ?, NIF = ?, correoElectronico = ?, ciudadID = ? WHERE id = ?";
    private static final String SQL_UPDATE_PEDIDOS = "UPDATE pedido SET fecha = ?, totalPedido = ?, estado = ?, idCliente = ?, idEmpleado = ? WHERE id = ?";

    private static final String SQL_DELETE_TIPOS = "DELETE FROM tipo WHERE id = ?";
    private static final String SQL_DELETE_PROVEEDORES = "DELETE FROM proveedor WHERE id = ?";
    private static final String SQL_DELETE_PEDIDOS = "DELETE FROM pedido WHERE id = ?";
    private static final String SQL_DELETE_PEDIDO_PRODUCTO = "DELETE FROM pedidoProducto WHERE idPedido = ?";

    public static boolean loadDriver() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
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
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /*** PEDIDOS ***/
    public static ResultSet getPedidos() throws SQLException {
        ResultSet rs = null;
        try {
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(SQL_GET_PEDIDOS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void editarPedido(int id, LocalDate fecha, double totalPedido, int estado, int idCliente, int idEmpleado) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_PEDIDOS);
            pstmt.setDate(1, Date.valueOf(fecha));
            pstmt.setDouble(2, totalPedido);
            pstmt.setInt(3, estado);
            pstmt.setInt(4, idCliente);
            pstmt.setInt(5, idEmpleado);
            pstmt.setInt(6, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarPedidoProducto(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_PEDIDO_PRODUCTO);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarPedido(int id) {
        try {
            borrarPedidoProducto(id);
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_PEDIDOS);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*** PROVEEDORES ***/
    public static ResultSet getProveedores() throws SQLException {
        ResultSet rs = null;
        try {
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(SQL_GET_PROVEEDORES);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void addProveedor(String nom, String dir, String tlf, String NIF, String correo, int IDCiudad) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_PROVEEDORES, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nom);
            pstmt.setString(2, dir);
            pstmt.setString(3, tlf);
            pstmt.setString(4, NIF);
            pstmt.setString(5, correo);
            pstmt.setInt(6, IDCiudad);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idGenProveedor = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editarProveedor(int id, String nom, String dir, String tlf, String NIF, String correo, int IDCiudad) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_PROVEEDORES);
            pstmt.setString(1, nom);
            pstmt.setString(2, dir);
            pstmt.setString(3, tlf);
            pstmt.setString(4, NIF);
            pstmt.setString(5, correo);
            pstmt.setInt(6, IDCiudad);
            pstmt.setInt(7, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarProveedor(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_PROVEEDORES);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int idAutoGenProveedor() {
        return idGenProveedor;
    }

    /*** TIPOS ***/
    public static ResultSet getTipos() throws SQLException {
        ResultSet rs = null;
        try {
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(SQL_GET_TIPO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ArrayList<String> obtenerNombresTipo() {
        try (PreparedStatement stmt = conn.prepareStatement(SQL_GET_TIPO);
             ResultSet rs = stmt.executeQuery()) {

            ArrayList<String> listaConNombresDeTipos = new ArrayList<>();
            while (rs.next()) {
                listaConNombresDeTipos.add(rs.getString(2));
            }
            return listaConNombresDeTipos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addTipo(String nom) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_TIPOS, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nom);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idGenTipo = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editarTipo(int id, String nom) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_TIPOS);
            pstmt.setString(1, nom);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarTipo(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_TIPOS);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int idAutoGenTipo() {
        return idGenTipo;
    }
}