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

    private static final String SQL_GET_CLIENTES = "SELECT * from cliente";
    private static final String SQL_GET_TIENDAS = "SELECT * from tienda";
    private static final String SQL_DELETE_CLIENTE = "DELETE FROM cliente WHERE cliente.CODIGO = ?";


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

    public static ResultSet getClientes () throws SQLException {
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(SQL_GET_CLIENTES);
        return rs;
    }

    public static ResultSet getTiendas () throws SQLException {
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(SQL_GET_TIENDAS);
        return rs;
    }

    public static int borrarCliente(int id) throws SQLException{
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_CLIENTE)){
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }

}
