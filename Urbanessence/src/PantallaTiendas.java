import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PantallaTiendas extends JFrame{
    private JPanel panelGeneral;
    private JButton btnPedido;
    private JButton btnTiendas;
    private JButton btnProveedor;
    private JButton btnProducto;
    private JButton btnTipo;
    private JButton btnClientes;
    private JButton btnEmpleado;
    private JButton btnCiudad;
    private JButton btnPerfil;
    private JButton btnLogout;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JTextField buscarTextField;
    private JLabel lblLogo;
    private JTable tablaTiendas;
    private JComboBox boxBuscar;

    private static String id;
    private static String nombre;
    private static String direccion;
    private static String telefono;
    private static String hApertura;
    private static String hCierre;
    private static String numEmpleados;
    private static String idCiudad;
    private static int accion = 0;
    static ArrayList<Tienda> tiendas = new ArrayList<>();

    public PantallaTiendas() {
        super("Panel tiendas");
        setContentPane(panelGeneral);
        tiendas.clear();
        cargarTiendas();
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        crearTabla();
        cargarIconos();
        formatoBotones();

        boxBuscar.setModel(new DefaultComboBoxModel(new String []{"ID", "NOMBRE", "DIRECCION", "TELEFONO", "H.APERTURA", "H.CIERRE", "N. EMPLEADOS", "ID.CIUDAD"}));

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accion = 1;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        limpiarCampos();
                        JFrame frame = new PantallaEditarTiendas();
                        frame.setVisible(true);
                        frame.setSize(745,620);
                        frame.setLocationRelativeTo(null);
                    }
                });
                dispose();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accion = 2;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaEditarTiendas();
                        frame.setVisible(true);
                        frame.setSize(745,620);
                        frame.setLocationRelativeTo(null);
                    }
                });
                dispose();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idP = Integer.parseInt(id);
                    DataManager.borrarTienda(idP);
                    for (int i = 0; i < tiendas.size(); i++) {
                        if (tiendas.get(i).getId() == idP) {
                            tiendas.remove(tiendas.get(i));
                        }
                    }
                    crearTabla();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });

        tablaTiendas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                JTable table = (JTable) mouseEvent.getSource();
                Point p = mouseEvent.getPoint();
                int row = table.rowAtPoint(p);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    id = (table.getModel().getValueAt(row, 0).toString());
                    nombre = (table.getModel().getValueAt(row, 1).toString());
                    telefono = (table.getModel().getValueAt(row, 3).toString());
                    hApertura = (table.getModel().getValueAt(row, 4).toString());
                    hCierre = (table.getModel().getValueAt(row, 5).toString());
                    direccion = (table.getModel().getValueAt(row, 2).toString());
                    numEmpleados = (table.getModel().getValueAt(row, 6).toString());
                    idCiudad = (table.getModel().getValueAt(row, 7).toString());

                }
            }
        });

        buscarTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarTabla();
            }
        });
    }

    private void filtrarTabla() {
        String parametroBusqueda = buscarTextField.getText();
        String campoBusqueda = boxBuscar.getSelectedItem().toString();

        // obtenemos el modelo de la tabla para poder trabajar con los datos y la estructura de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaTiendas.getModel();

        // el TableRowSorter habilita la funcionalidad de ordenar y filtrar las filas de la tabla
        TableRowSorter<DefaultTableModel> filtroTabla = new TableRowSorter<>(modeloTabla);
        tablaTiendas.setRowSorter(filtroTabla);

        if (parametroBusqueda.length() == 0) {
            filtroTabla.setRowFilter(null);
        } else {
            int campo = -1;
            switch (campoBusqueda) {
                case "ID":
                    campo = 0;
                    break;
                case "NOMBRE":
                    campo = 1;
                    break;
                case "DIRECCION":
                    campo = 2;
                    break;
                case "TELEFONO":
                    campo = 3;
                    break;
                case "H.APERTURA":
                    campo = 4;
                    break;
                case "H.CIERRE":
                    campo = 5;
                    break;
                case "N. EMPLEADOS":
                    campo = 6;
                    break;
                case "ID.CIUDAD":
                    campo = 6;
                    break;
            }
            //"(?i)" regex para no distinguir entre mayúsculas y minúsculas al buscar
            filtroTabla.setRowFilter(RowFilter.regexFilter("(?i)" + parametroBusqueda, campo));
        }
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnClientes.setIcon(iconoClientes);

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidos.png");
        btnPedido.setIcon(iconoPedidos);
        btnPedido.setUI(new BasicButtonUI());

        ImageIcon iconoProveedores = new ImageIcon("imagenes/iconoProveedores.png");
        btnProveedor.setIcon(iconoProveedores);

        ImageIcon iconoProductos = new ImageIcon("imagenes/iconoProductos.png");
        btnProducto.setIcon(iconoProductos);

        ImageIcon iconoTipo = new ImageIcon("imagenes/iconoTipo.png");
        btnTipo.setIcon(iconoTipo);

        ImageIcon iconoTiendas = new ImageIcon("imagenes/iconoTiendasBlanco.png");
        btnTiendas.setIcon(iconoTiendas);
        btnTiendas.setUI(new BasicButtonUI());

        ImageIcon iconoEmpleados = new ImageIcon("imagenes/iconoEmpleados.png");
        btnEmpleado.setIcon(iconoEmpleados);

        ImageIcon iconoCiudades = new ImageIcon("imagenes/iconoCiudades.png");
        btnCiudad.setIcon(iconoCiudades);


        ImageIcon iconoLogOut = new ImageIcon("imagenes/iconoLogOut.png");
        btnLogout.setIcon(iconoLogOut);


        ImageIcon iconoPerfil = new ImageIcon("imagenes/iconoPerfil.png");
        btnPerfil.setIcon(iconoPerfil);
        btnPerfil.setUI(new BasicButtonUI());
        btnPerfil.setFocusPainted(false);
        btnPerfil.setBorderPainted(false);

        btnAdd.setUI(new BasicButtonUI());
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);

        btnDelete.setUI(new BasicButtonUI());
        btnDelete.setFocusPainted(false);
        btnDelete.setBorderPainted(false);

        btnEdit.setUI(new BasicButtonUI());
        btnEdit.setFocusPainted(false);
        btnEdit.setBorderPainted(false);

    }

    private void formatoBotones() {

        btnPedido.setBorder(null);
        btnPedido.setContentAreaFilled(false);

        btnProveedor.setBorder(null);
        btnProveedor.setContentAreaFilled(false);

        btnProducto.setBorder(null);
        btnProducto.setContentAreaFilled(false);

        btnTipo.setBorder(null);
        btnTipo.setContentAreaFilled(false);

        btnClientes.setBorder(null);
        btnClientes.setContentAreaFilled(false);

        btnEmpleado.setBorder(null);
        btnEmpleado.setContentAreaFilled(false);

        btnCiudad.setBorder(null);
        btnCiudad.setContentAreaFilled(false);

        btnLogout.setBackground(null);
        btnLogout.setBorder(null);
        btnLogout.setContentAreaFilled(false);
    }

    public void cargarTiendas(){
        if (DataManager.getTiendas()){
            tiendas = DataManager.getListaTiendas();
            crearTabla();
        }
    }

    private void crearTabla() {
        String[][] data = new String[tiendas.size()][8];

        for (int i = 0; i < tiendas.size(); i++) {
            data[i][0] = String.valueOf(tiendas.get(i).getId());
            data[i][1] = String.valueOf(tiendas.get(i).getNombre());
            data[i][2] = String.valueOf(tiendas.get(i).getDireccion());
            data[i][3] = String.valueOf(tiendas.get(i).getTelefono());
            data[i][4] = String.valueOf(tiendas.get(i).gethApertura());
            data[i][5] = String.valueOf(tiendas.get(i).gethCierre());
            data[i][6] = String.valueOf(tiendas.get(i).getNumEmpleados());
            data[i][7] = String.valueOf(tiendas.get(i).getIdCiudad());
        }

        tablaTiendas.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "NOMBRE", "DIRECCION", "TELEFONO", "H.APERTURA", "H.CIERRE", "N. EMPLEADOS", "ID.CIUDAD"}
        ));
        tablaTiendas.getTableHeader().setReorderingAllowed(false);
        tablaTiendas.setEnabled(true);
        tablaTiendas.setDefaultEditor(Object.class, null);
    }

    public static int getAccion() {
        return accion;
    }

    public static String getId() {
        return  id;
    }

    public static String getNombre(){
        return nombre;
    }

    public static String getTelefono(){
        return telefono;
    }

    public static String gethApertura(){
        return hApertura;
    }

    public static String gethCierre(){
        return hCierre;
    }

    public static String getDireccion(){
        return direccion;
    }

    public static String getNumEmpleados(){
        return numEmpleados;
    }

    public static String getIdCiudad(){
        return idCiudad;
    }

    public void limpiarCampos(){
        nombre = "";
        telefono = "";
        hApertura = "";
        hCierre = "";
        direccion = "";
        numEmpleados = "";
        idCiudad = "";
    }
}
