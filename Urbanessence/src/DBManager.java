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

    private static final String SQL_GET_CIUDADES = "SELECT * from ciudad";
    private static final String SQL_GET_PRODUCTOS = "SELECT * from producto";
    private static final String SQL_GET_TAREAS = "SELECT * from tareas";
    private static final String SQL_GET_EMPLEADOS = "SELECT * from empleado";

    private static final String SQL_INSERT_TIPOS = "INSERT INTO tipo (nombre) VALUES (?)";
    private static final String SQL_INSERT_PROVEEDORES = "INSERT INTO proveedor (nombre, direccion, telefono, NIF, correoElectronico, ciudadID) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_TIPOS = "UPDATE tienda SET nombre = ? WHERE id = ?";
    private static final String SQL_UPDATE_PROVEEDORES = "UPDATE proveedor SET nombre = ?, direccion = ?, telefono = ?, NIF = ?, correoElectronico = ?, ciudadID = ? WHERE id = ?";
    private static final String SQL_UPDATE_PEDIDOS = "UPDATE pedido SET fecha = ?, totalPedido = ?, estado = ?, idCliente = ?, idEmpleado = ? WHERE id = ?";

    private static final String SQL_DELETE_TIPOS = "DELETE FROM tipo WHERE id = ?";
    private static final String SQL_DELETE_PROVEEDORES = "DELETE FROM proveedor WHERE id = ?";
    private static final String SQL_DELETE_PEDIDOS = "DELETE FROM pedido WHERE id = ?";
    private static final String SQL_DELETE_PEDIDO_PRODUCTO = "DELETE FROM pedidoProducto WHERE idPedido = ?";

    private static int idGenCiudad, idGenProducto;
    private static int idGenTienda;

    private static final String SQL_GET_CLIENTES = "SELECT * from cliente";
    private static final String SQL_GET_TIENDAS = "SELECT * from tienda";
    private static final String SQL_ADD_CLIENTE = "INSERT INTO cliente (NOMBRE, GENERO, DIRECCION, TELEFONO, FECHANAC) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE_CLIENTES = "UPDATE cliente SET nombre = ?, genero = ?, direccion = ?, telefono = ?, fechaNac = ? WHERE id = ?";

    private static final String SQL_DELETE_CLIENTE = "DELETE FROM cliente WHERE id = ?";
    private static final String SQL_SELECT_ID_PEDIDO = "SELECT id FROM pedido WHERE idCliente = ?";

    private static final String SQL_INSERT_TIENDAS= "INSERT INTO tienda (nombre, direccion, telefono, horarioApertura, horarioCierre, numEmpleados, idCiudad) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_TIENDAS = "UPDATE tienda SET nombre = ?, direccion = ?, telefono = ?, horarioApertura = ?, horarioCierre = ?, numEmpleados = ?, idCiudad = ? WHERE id = ?";
    private static final String SQL_DELETE_TIENDAS = "DELETE FROM tienda WHERE id = ?";

    private static final String SQL_INSERT_CIUDADES = "INSERT INTO ciudad (nombre, provincia, numHabitantes) VALUES (?, ?, ?)";
    private static final String SQL_INSERT_PRODUCTOS = "INSERT INTO producto (nombre, marca, talla, color, material, precio, idTipo, idProveedor, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_CIUDADES = "UPDATE ciudad SET nombre = ?, provincia = ?, numHabitantes = ? WHERE id = ?";
    private static final String SQL_UPDATE_PRODUCTOS = "UPDATE producto SET nombre = ?, marca = ?, talla = ?, color = ?, material = ?, precio = ?, idTipo = ?, idProveedor = ?, cantidad = ? WHERE id = ?";

    private static final String SQL_DELETE_CIUDADES = "DELETE FROM ciudad WHERE id = ?";
    private static final String SQL_DELETE_PRODUCTOS = "DELETE FROM producto WHERE id = ?";

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