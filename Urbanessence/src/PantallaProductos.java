import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PantallaProductos extends JFrame{
    private JPanel panelGeneral;
    private JButton btnPedido;
    private JButton btnProductos;
    private JButton btnProveedor;
    private JButton btnTiendas;
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
    private JTable tabla;
    private JComboBox boxBuscar;
    private JButton btnBuscar;

    static ArrayList<Producto> productos = new ArrayList<>();
    static String id = "", nombre = "", marca = "" , talla = "", color = "", material = "",
            precio = "", idTipo = "", idProveedor = "", cantidad = "";
    static int accion = 0;

    public PantallaProductos() {
        super("Panel Productos");
        setContentPane(panelGeneral);
        productos.clear();
        cargarProdcutos();
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);
        boxBuscar.setModel(new DefaultComboBoxModel(new String[]{"ID", "NOMBRE", "MARCA", "TALLA", "COLOR", "MATERIAL", "PRECIO", "IDTIPO", "CANTIDAD"}));

        crearTabla();
        cargarIconos();
        formatoBotones();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accion = 1;
                PantallaEditarProductos.tipoAccion(accion);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaEditarProductos();
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
                PantallaEditarProductos.tipoAccion(accion);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaEditarProductos();
                        frame.setVisible(true);
                        frame.setSize(750,620);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
            }
        });
        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaClientes();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
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
                        frame.setResizable(false);
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
                        frame.setResizable(false);
                    }
                });
                dispose();
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
                        frame.setResizable(false);
                    }
                });
                dispose();
            }
        });
        btnTiendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaTiendas();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
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
                        frame.setResizable(false);
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
                    DataManager.borrarProducto(idP);
                    for (int i = 0; i < productos.size(); i++) {
                        if (productos.get(i).getId() == idP) {
                            productos.remove(productos.get(i));
                        }
                    }
                    crearTabla();
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                rellenarDatos(e);
            }
        });


        buscarTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                filtrarTabla();
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
                        frame.setResizable(false);
                    }
                });
                dispose();
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaLogin();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
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
                case "MARCA":
                    campo = 2;
                    break;
                case "TALLA":
                    campo = 3;
                    break;
                case "COLOR":
                    campo = 4;
                    break;
                case "PRECIO":
                    campo = 5;
                    break;
                case "ID TIPO":
                    campo = 6;
                    break;
                case "ID PROVEEDOR":
                    campo = 7;
                    break;
                case "CANTIDAD":
                    campo = 8;
                    break;
            }
            //"(?i)" regex para no distinguir entre mayúsculas y minúsculas al buscar
            filtroTabla.setRowFilter(RowFilter.regexFilter("(?i)" + parametroBusqueda, campo));
        }
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnClientes.setIcon(iconoClientes);
        btnClientes.setUI(new BasicButtonUI());

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidos.png");
        btnPedido.setIcon(iconoPedidos);
        btnPedido.setUI(new BasicButtonUI());

        ImageIcon iconoProveedores = new ImageIcon("imagenes/iconoProveedores.png");
        btnProveedor.setIcon(iconoProveedores);

        ImageIcon iconoProductos = new ImageIcon("imagenes/iconoProductosBlanco.png");
        btnProductos.setIcon(iconoProductos);

        ImageIcon iconoTipo = new ImageIcon("imagenes/iconoTipo.png");
        btnTipo.setIcon(iconoTipo);

        ImageIcon iconoTiendas = new ImageIcon("imagenes/iconoTiendas.png");
        btnTiendas.setIcon(iconoTiendas);

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

        btnTiendas.setBorder(null);
        btnTiendas.setContentAreaFilled(false);

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

    private void rellenarDatos(MouseEvent mouseEvent) {
        JTable table = (JTable) mouseEvent.getSource();
        Point p = mouseEvent.getPoint();
        int row = table.rowAtPoint(p);
        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

            id = table.getModel().getValueAt(row, 0).toString();
            nombre = table.getModel().getValueAt(row, 1).toString();
            marca = table.getModel().getValueAt(row, 2).toString();
            talla = table.getModel().getValueAt(row, 3).toString();
            color = table.getModel().getValueAt(row, 4).toString();
            material = table.getModel().getValueAt(row, 5).toString();
            precio = table.getModel().getValueAt(row, 6).toString();
            idTipo = table.getModel().getValueAt(row, 7).toString();
            idProveedor = table.getModel().getValueAt(row, 8).toString();
            cantidad = table.getModel().getValueAt(row, 9).toString();
            PantallaEditarProductos.pasarDatos(id, nombre, marca, talla, color, material, precio, idTipo, idProveedor, cantidad);
        }
    }

    public void cargarProdcutos (){
        if (DataManager.getProductos()){
            productos = DataManager.getListaProductos();
            crearTabla();
        }
    }

    private void crearTabla() {
        String[][] data = new String[productos.size()][8];

        for (int i = 0; i < productos.size(); i++) {
            data[i][0] = String.valueOf(productos.get(i).getId());
            data[i][1] = productos.get(i).getNombre();
            data[i][2] = productos.get(i).getMarca();
            data[i][3] = productos.get(i).getTalla();
            data[i][4] = productos.get(i).getMaterial();
            data[i][5] = String.valueOf(productos.get(i).getPrecio());
            data[i][6] = String.valueOf(productos.get(i).getIdTipo());
            data[i][7] = String.valueOf(productos.get(i).getIdProveedor());
            data[i][7] = String.valueOf(productos.get(i).getCantidad());
        }

        tabla.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "NOMBRE", "MARCA", "TALLA", "COLOR", "MATERIAL", "PRECIO", "IDTIPO", "CANTIDAD"}
        ));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setEnabled(true);
        tabla.setDefaultEditor(Object.class, null);
    }
}
