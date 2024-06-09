import javax.print.DocFlavor;
import java.security.PublicKey;
import java.sql.*;

public class DBManager {
    private static Connection conn = null;

    /**
     * INICIO CONSTANTES PARA LA CONEXION CON SQL SERVER
     * **/


    //CONSTATNTES PARA LA CONEXION
    private static final String DBHOST =  "192.168.56.1";
    private static final String PORT = "1433";
    private static final String DBNAME = "Urbanessence";
    //CONSTANTES PARA EL USUARIO
    private static final String DBUSER = "UrbanessenceAdmin";
    private static final String PASSWORD = "1234";
    //CONSTANTES SEGURIDAD
    private static final String ENCRYPT = "true";
    private static final String TRUST_SERVER_CERTIFICATE = "true";
    //URL FINAL
    private static final String URL ="jdbc:sqlserver://" + DBHOST + ":" + PORT + ";databaseName=" + DBNAME + ";user=" + DBUSER + ";password=" + PASSWORD + ";encrypt=" + ENCRYPT + ";trustServerCertificate=" + TRUST_SERVER_CERTIFICATE;

    /**
     * FIN CONSTANTES PARA LA CONEXION CON SQL SERVER
     * **/

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

    /**
     * METODOS PARA CARGAR EL JDBC PARA SQL SERVER Y LA COENXION CON LA BD
     */


    //METODO PARA CARGAR EL DRIVER SQLSEREVER
    public static boolean loadDriverSQLServer(){
        try {
            System.out.println("Cargando Driver JDBC...");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            System.out.println("DRIVER JDBC SUCCESSFULLY LOADED!");
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    //METODO PARA CREAR LA CONEXION CON LA BASE DE DATOS
    public static boolean openConnectionToDatabase(){
        try {
            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(URL);
            System.out.println("Successfully connect to the database " + DBNAME + " !");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    //METODO PARA CERRAR LA CONEXION CON LA BASE DE DATOS
    public static void closeConnectioToDatabase(){
        try {
            System.out.println("Closing your connection to the database " + DBNAME);
            conn.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * FIN METODOS CONEXION SQLSERVER
     *
     */

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

    public static boolean crearTarea(Tarea tarea) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into tareas(titulo, descripcion, fechaInicio, fechaFin, empleadoID) values(?,?,?,?,?);");
            ps.setString(1, tarea.getTitulo());
            ps.setString(2, tarea.getDescripcion());
            ps.setDate(3, Date.valueOf(tarea.getFechaInicio()));
            ps.setDate(4, Date.valueOf(tarea.getFechaFin()));
            ps.setInt(5, tarea.getEmpleadoID());
            return !ps.execute(); //solo devuelve true si el resultado es un resultset
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR AL INSERTAR LA TAREA EN LA BD: " + e.getMessage());
            return false;
        }
    }

    public static ResultSet getTareas() throws SQLException {
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(SQL_GET_TAREAS);
        return rs;
    }
}
