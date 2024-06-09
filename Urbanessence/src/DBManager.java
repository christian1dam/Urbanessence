import java.sql.*;
import java.time.LocalTime;

public class DBManager {
    private static Connection conn = null;

    // Configuración de la conexión a la base de datos
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "urbanessence";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static final String SQL_GET_CLIENTES = "SELECT * from cliente";
    private static final String SQL_GET_TIENDAS = "SELECT * from tienda";
    private static final String SQL_ADD_CLIENTE = "INSERT INTO cliente (NOMBRE, GENERO, DIRECCION, TELEFONO, FECHANAC) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE_CLIENTES = "UPDATE cliente SET nombre = ?, genero = ?, direccion = ?, telefono = ?, fechaNac = ? WHERE id = ?";

    private static final String SQL_GET_CIUDADES = "SELECT * from ciudad";
    private static final String SQL_GET_PRODUCTOS = "SELECT * from producto";

    private static final String SQL_DELETE_CLIENTE = "DELETE FROM cliente WHERE id = ?";
    private static final String SQL_DELETE_PEDIDOS = "DELETE FROM pedido WHERE idCliente = ?";
    private static final String SQL_DELETE_PEDIDO_PRODUCTO = "DELETE FROM pedidoProducto WHERE idPedido = ?";
    private static final String SQL_SELECT_ID_PEDIDO = "SELECT id FROM pedido WHERE idCliente = ?";

    private static final String SQL_INSERT_TIENDAS= "INSERT INTO tienda (nombre, direccion, telefono, horarioApertura, horarioCierre, numEmpleados, idCiudad) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_TIENDAS = "UPDATE tienda SET nombre = ?, direccion = ?, telefono = ?, horarioApertura = ?, horarioCierre = ?, numEmpleados = ?, idCiudad = ? WHERE id = ?";
    private static final String SQL_DELETE_TIENDAS = "DELETE FROM tienda WHERE id = ?";

    private static int idGenTienda;

    
    public boolean loadDriver() {
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


    /*** CLIENTES ***/
    public static ResultSet getClientes () throws SQLException {
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(SQL_GET_CLIENTES);
        return rs;
    }

    public static int addCliente (Cliente cliente) throws SQLException {
        try (PreparedStatement pstmt  = conn.prepareStatement(SQL_ADD_CLIENTE)){
            pstmt.setString(1, cliente.getNombre());
            pstmt.setByte(2, cliente.getGenero());
            pstmt.setString(3, cliente.getDireccion());
            pstmt.setInt(4, cliente.getTelefono());
            pstmt.setDate(5, cliente.getFechaNac());
            return pstmt.executeUpdate();
        }

    }

    public static void editCliente (Cliente cliente) throws SQLException {
        try (PreparedStatement pstmt  = conn.prepareStatement(SQL_UPDATE_CLIENTES)){
            pstmt.setString(1, cliente.getNombre());
            pstmt.setByte(2, cliente.getGenero());
            pstmt.setString(3, cliente.getDireccion());
            pstmt.setInt(4, cliente.getTelefono());
            pstmt.setDate(5, cliente.getFechaNac());
            pstmt.setInt(6, cliente.getId());
            pstmt.executeUpdate();
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

    public static void borrarPedido(int idCliente) {
        try {
            borrarPedidoProducto(idCliente);
            PreparedStatement pstmtID = conn.prepareStatement(SQL_SELECT_ID_PEDIDO);
            pstmtID.setInt(1, idCliente);
            ResultSet rs = pstmtID.executeQuery();

            while (rs.next()) {
                int idPedido = rs.getInt("id");
                borrarPedidoProducto(idPedido);
            }

            rs.close();
            pstmtID.close();

            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_PEDIDOS);
            pstmt.setInt(1, idCliente);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int borrarCliente(int id) throws SQLException{
        borrarPedido(id);
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_CLIENTE)){
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }

    /*** TIENDAS ***/
    public static ResultSet getTiendas() throws SQLException {
        ResultSet rs = null;
        try {
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(SQL_GET_TIENDAS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void addTienda(String nom, String dir, String tlf, LocalTime hApertura, LocalTime hCierre, int numEmpleados, int idCiudad) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_TIENDAS, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nom);
            pstmt.setString(2, dir);
            pstmt.setString(3, tlf);
            pstmt.setTime(4, Time.valueOf(hApertura));
            pstmt.setTime(5, Time.valueOf(hCierre));
            pstmt.setInt(6, numEmpleados);
            pstmt.setInt(7, idCiudad);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idGenTienda = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editarTienda(int id, String nom, String dir, String tlf, LocalTime hApertura, LocalTime hCierre, int numEmpleados, int idCiudad) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_TIENDAS);
            pstmt.setString(1, nom);
            pstmt.setString(2, dir);
            pstmt.setString(3, tlf);
            pstmt.setTime(4, Time.valueOf(hApertura));
            pstmt.setTime(5, Time.valueOf(hCierre));
            pstmt.setInt(6, numEmpleados);
            pstmt.setInt(7, idCiudad);
            pstmt.setInt(8, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarTienda(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_TIENDAS);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int idAutoGenTienda() {
        return idGenTienda;
    }





    /*** ***/
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


}


