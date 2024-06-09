import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PantallaClientes extends JFrame{
    private JPanel panelGeneral;
    private JButton btnPedido;
    private JButton btnCliente;
    private JButton btnProveedor;
    private JButton btnProducto;
    private JButton btnTipo;
    private JButton btnTienda;
    private JButton btnEmpleado;
    private JButton btnCiudad;
    private JButton btnPerfil;
    private JButton btnLogout;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JTextField buscarTextField;
    private JLabel lblLogo;
    private JTable tabla;
    private JComboBox boxBuscar;

    private static String id = "", nombre = "", genero = "", direccion = "", telefono = "", fechaNac = "";

    private static int accion = 0;


    static ArrayList<Cliente> clientes = new ArrayList<>();

    public PantallaClientes() {
        super("Panel Clientes");
        setContentPane(panelGeneral);
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        crearTabla();
        cargarIconos();
        formatoBotones();
        cargarClientes();

        boxBuscar.setModel(new DefaultComboBoxModel(new String []{"ID", "NOMBRE", "GENERO", "F.NACIMIENTO", "H.TELEFONO", "DIRECCION"}));

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accion = 1;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        limpiarCampos();
                        JFrame frame = new PantallaEditarClientes();
                        frame.setVisible(true);
                        frame.setSize(745,620);
                        frame.setLocationRelativeTo(null);
                    }
                });
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accion = 2;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaEditarClientes();
                        frame.setVisible(true);
                        frame.setSize(745,620);
                        frame.setLocationRelativeTo(null);
                    }
                    });
                }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idP = Integer.parseInt(id);
                DataManager.borrarCliente(idP);
                for (int i = 0; i < clientes.size(); i++) {
                    if (clientes.get(i).getId() == idP) {
                        clientes.remove(clientes.get(i));
                    }
                }
                crearTabla();
            }
        });

        btnPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaPedidos();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                    }
                });
            }
        });
        btnTienda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaTiendas();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                    }
                });
                dispose();
            }
        });
        btnProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaProveedores();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                    }
                });
            }
        });
        btnProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaProductos();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                    }
                });
            }
        });
        btnTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaTipo();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                    }
                });
            }
        });
        btnEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaPrincipalEmpleados();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                    }
                });
            }
        });
        btnCiudad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaCiudades();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                    }
                });
            }
        });

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                JTable table = (JTable) mouseEvent.getSource();
                Point p = mouseEvent.getPoint();
                int row = table.rowAtPoint(p);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    id = (table.getModel().getValueAt(row, 0).toString());
                    nombre = (table.getModel().getValueAt(row, 1).toString());
                    telefono = (table.getModel().getValueAt(row, 4).toString());
                    genero = (table.getModel().getValueAt(row, 2).toString());
                    fechaNac = ((table.getModel().getValueAt(row, 3).toString()));
                    direccion = (table.getModel().getValueAt(row, 5).toString());

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
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();

        // el TableRowSorter habilita la funcionalidad de ordenar y filtrar las filas de la tabla
        TableRowSorter<DefaultTableModel> filtroTabla = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(filtroTabla);

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
                case "GENERO":
                    campo = 2;
                    break;
                case "F.NACIMIENTO":
                    campo = 3;
                    break;
                case "H.TELEFONO":
                    campo = 4;
                    break;
                case "DIRECCION":
                    campo = 5;
                    break;
            }
            //"(?i)" regex para no distinguir entre mayúsculas y minúsculas al buscar
            filtroTabla.setRowFilter(RowFilter.regexFilter("(?i)" + parametroBusqueda, campo));
        }
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientesBlanco.png");
        btnCliente.setIcon(iconoClientes);
        btnCliente.setUI(new BasicButtonUI());
        
        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidos.png");
        btnPedido.setIcon(iconoPedidos);
        btnPedido.setUI(new BasicButtonUI());

        ImageIcon iconoProveedores = new ImageIcon("imagenes/iconoProveedores.png");
        btnProveedor.setIcon(iconoProveedores);

        ImageIcon iconoProductos = new ImageIcon("imagenes/iconoProductos.png");
        btnProducto.setIcon(iconoProductos);

        ImageIcon iconoTipo = new ImageIcon("imagenes/iconoTipo.png");
        btnTipo.setIcon(iconoTipo);

        ImageIcon iconoTiendas = new ImageIcon("imagenes/iconoTiendas.png");
        btnTienda.setIcon(iconoTiendas);

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

    public static int setAccion() {
        return accion;
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

        btnTienda.setBorder(null);
        btnTienda.setContentAreaFilled(false);

        btnEmpleado.setBorder(null);
        btnEmpleado.setContentAreaFilled(false);

        btnCiudad.setBorder(null);
        btnCiudad.setContentAreaFilled(false);

        btnLogout.setBackground(null);
        btnLogout.setBorder(null);
        btnLogout.setContentAreaFilled(false);
    }

    public void cargarClientes (){
        if (DataManager.getClientes()){
            clientes = DataManager.getListaClientes();
            crearTabla();
        }
    }

    private void crearTabla() {
        String[][] data = new String[clientes.size()][6];

        for (int i = 0; i < clientes.size(); i++) {
            String genero;
            if (clientes.get(i).getGenero() == 1) {genero = "H";}else {genero = "M";}
            data[i][0] = String.valueOf(clientes.get(i).getId());
            data[i][1] = String.valueOf(clientes.get(i).getNombre());
            data[i][2] = genero;
            data[i][3] = String.valueOf(clientes.get(i).getFechaNac());
            data[i][4] = String.valueOf(clientes.get(i).getTelefono());
            data[i][5] = String.valueOf(clientes.get(i).getDireccion());
        }

        tabla.setModel(new DefaultTableModel(data, new String[]{"ID", "NOMBRE", "GENERO", "F.NACIMIENTO", "H.TELEFONO", "DIRECCION"}));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setEnabled(true);
        tabla.setDefaultEditor(Object.class, null);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new PantallaClientes();
                frame.setVisible(true);
                frame.setSize(1080,670);
                frame.setLocationRelativeTo(null);
            }
        });
    }
    public static String getId(){
        return id;
    }

    public static String getNombre(){
        return nombre;
    }

    public static String getGenero(){
        return genero;
    }

    public static String getTelefono(){
        return telefono;
    }

    public static String getFechaNac(){
        return fechaNac;
    }

    public static String getDireccion(){
        return direccion;
    }

    public static void limpiarCampos(){
        nombre = "";
        genero = "";
        telefono = "";
        fechaNac = "";
        direccion = "";
    }
}
