import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class DBManager {
    private static Connection conn = null;

    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "urbanessence";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private static int idGenCliente, idGenTipo, idGenCiudad, idGenTienda, idGenEmpleado, idGenProducto, idGenProveedor, idGenPedido;

    private static final String SQL_GET_CLIENTES = "SELECT * FROM clientes";
    private static final String SQL_GET_PEDIDOS = "SELECT * FROM pedido";
    private static final String SQL_GET_PROVEEDORES = "SELECT * FROM proveedor";
    private static final String SQL_GET_TIPO = "SELECT * FROM tipo";
    private static final String SQL_GET_TIENDAS = "SELECT * FROM tienda";
    private static final String SQL_GET_CIUDADES = "SELECT * FROM ciudad";
    private static final String SQL_GET_EMPLEADOS = "SELECT * FROM empleado";
    private static final String SQL_GET_PRODUCTOS = "SELECT * FROM producto";

    private static final String SQL_INSERT_CLIENTES = "INSERT INTO cliente (nombre, genero, direccion, telefono, fechaNac) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_TIPOS = "INSERT INTO tipo (nombre) VALUES (?)";
    private static final String SQL_INSERT_CIUDADES = "INSERT INTO ciudad (nombre, provincia, numHabitantes) VALUES (?, ?, ?)";
    private static final String SQL_INSERT_TIENDAS= "INSERT INTO tienda (nombre, direccion, telefono, horarioApertura, horarioCierre, numEmpleados, idCiudad) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_EMPLEADOS  = "INSERT INTO empleado (nombre, apellidos, fechaNac, direccion, fechaCont, cargo, salario, NUSS, idTienda) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_PRODUCTOS = "INSERT INTO producto (nombre, marca, talla, color, material, precio, idTipo, idProveedor, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_PROVEEDORES = "INSERT INTO proveedor (nombre, direccion, telefono, NIF, correoElectronico, ciudadID) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_CLIENTES = "UPDATE cliente SET nombre = ?, genero = ?, direccion = ?, telefono = ?, fechaNac = ? WHERE id = ?";
    private static final String SQL_UPDATE_TIPOS = "UPDATE tienda SET nombre = ? WHERE id = ?";
    private static final String SQL_UPDATE_CIUDADES = "UPDATE ciudad SET nombre = ?, provincia = ?, numHabitantes = ? WHERE id = ?";
    private static final String SQL_UPDATE_TIENDAS = "UPDATE tipo SET nombre = ?, direccion = ?, telefono = ?, horarioApertura = ?, horarioCierre = ?, numEmpleados = ?, idCiudad = ? WHERE id = ?";
    private static final String SQL_UPDATE_EMPLEADOS = "UPDATE empleado SET nombre = ?, apellidos = ?, fechaNac = ?, direccion = ?, fechaCont = ?, cargo = ?, salario = ?, NUSS = ?, idTienda = ? WHERE id = ?";
    private static final String SQL_UPDATE_PRODUCTOS = "UPDATE producto SET nombre = ?, marca = ?, talla = ?, color = ?, material = ?, precio = ?, idTipo = ?, idProveedor = ?, cantidad = ? WHERE id = ?";
    private static final String SQL_UPDATE_PROVEEDORES = "UPDATE proveedor SET nombre = ?, direccion = ?, telefono = ?, NIF = ?, correoElectronico = ?, ciudadID = ? WHERE id = ?";
    private static final String SQL_UPDATE_PEDIDOS = "UPDATE pedido SET fecha = ?, totalPedido = ?, estado = ?, idCliente = ?, idEmpleado = ? WHERE id = ?";

    private static final String SQL_DELETE_CLIENTES = "DELETE FROM cliente WHERE id = ?";
    private static final String SQL_DELETE_TIPOS = "DELETE FROM tipo WHERE id = ?";
    private static final String SQL_DELETE_CIUDADES = "DELETE FROM ciudad WHERE id = ?";
    private static final String SQL_DELETE_TIENDAS = "DELETE FROM tienda WHERE id = ?";
    private static final String SQL_DELETE_EMPLEADOS = "DELETE FROM empleado WHERE id = ?";
    private static final String SQL_DELETE_PRODUCTOS = "DELETE FROM producto WHERE id = ?";
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



    /*** CLIENTES ***/
    public static ResultSet getClientes() {
        ResultSet rs = null;
        try {
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(SQL_GET_CLIENTES);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void addCliente(String nom, byte gen, String dir, int tlf, LocalDate fecha) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_CLIENTES, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nom);
            pstmt.setByte(2, gen);
            pstmt.setString(3, dir);
            pstmt.setInt(4, tlf);
            pstmt.setDate(5, Date.valueOf(fecha));

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idGenCliente = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editarCliente(int id, String nom, byte gen, String dir, int tlf, LocalDate fecha) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_CLIENTES);
            pstmt.setString(1, nom);
            pstmt.setByte(2, gen);
            pstmt.setString(3, dir);
            pstmt.setInt(4, tlf);
            pstmt.setDate(5, Date.valueOf(fecha));
            pstmt.setInt(6, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarCliente(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_CLIENTES);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int idAutoGenCliente() {
        return idGenCliente;
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

    public static int idAutoGenPedido() {
        return idGenPedido;
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

    public static void addTienda(String nom, String dir, LocalTime hApertura, LocalTime hCierre, int numEmpleados, int idCiudad) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_TIENDAS, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nom);
            pstmt.setString(2, dir);
            pstmt.setTime(3, Time.valueOf(hApertura));
            pstmt.setTime(4, Time.valueOf(hCierre));
            pstmt.setInt(3, numEmpleados);
            pstmt.setInt(5, idCiudad);

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

    public static void editarTienda(int id, String nom, String dir, LocalTime hApertura, LocalTime hCierre, int numEmpleados, int idCiudad) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_TIENDAS);
            pstmt.setString(1, nom);
            pstmt.setString(2, dir);
            pstmt.setTime(3, Time.valueOf(hApertura));
            pstmt.setTime(4, Time.valueOf(hCierre));
            pstmt.setInt(3, numEmpleados);
            pstmt.setInt(5, idCiudad);
            pstmt.setInt(6, id);

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

    /*** EMPLEADOS ***/
    public static ResultSet getEmpleados() throws SQLException {
        ResultSet rs = null;
        try {
            Statement stat = conn.createStatement();
            rs = stat.executeQuery(SQL_GET_EMPLEADOS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void addEmpleado(String nom, String apell, LocalDate fechaNac, String dir, LocalDate fechaCont, String cargo, double salario, String NUSS, int idTienda) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_EMPLEADOS, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nom);
            pstmt.setString(2, apell);
            pstmt.setDate(3, Date.valueOf(fechaNac));
            pstmt.setString(4, dir);
            pstmt.setDate(5, Date.valueOf(fechaCont));
            pstmt.setString(6, cargo);
            pstmt.setDouble(7, salario);
            pstmt.setString(8, NUSS);
            pstmt.setInt(9, idTienda);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idGenEmpleado = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editarEmpleado(int id, String nom, String apell, LocalDate fechaNac, String dir, LocalDate fechaCont, String cargo, double salario, String NUSS, int idTienda) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_EMPLEADOS);
            pstmt.setString(1, nom);
            pstmt.setString(2, apell);
            pstmt.setDate(3, Date.valueOf(fechaNac));
            pstmt.setString(4, dir);
            pstmt.setDate(5, Date.valueOf(fechaCont));
            pstmt.setString(6, cargo);
            pstmt.setDouble(7, salario);
            pstmt.setString(8, NUSS);
            pstmt.setInt(9, idTienda);
            pstmt.setInt(10, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarEmpleado(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_EMPLEADOS);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int idAutoGenEmpleado() {
        return idGenEmpleado;
    }

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