import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PantallaProveedores extends JFrame{
    private JPanel panelGeneral;
    private JButton btnCliente;
    private JButton btnPedido;
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
    private JButton btnBorrar;
    private JTextField buscarTextField;
    private JLabel lblLogo;
    private JTable tabla;
    private JComboBox boxBuscar;

    static ArrayList<Proveedor> proveedores = new ArrayList<>();
    static String id = "", nombre = "", direccion = "" , tlf = "", nif = "", correo = "", idCiudad = "";
    static int accion = 0;

    public PantallaProveedores() {
        super("Panel Proveedores");
        setContentPane(panelGeneral);
        proveedores.clear();
        cargarClientes();
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        crearTabla();
        cargarIconos();
        formatoBotones();

        boxBuscar.setModel(new DefaultComboBoxModel(new String []{"ID", "NOMBRE", "DIRECCIÓN", "TELÉFONO", "NIF", "CORREO", "ID CIUDAD"}));

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accion = 1;
                PantallaEditarProveedores.tipoAccion(accion);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaEditarProveedores();
                        frame.setVisible(true);
                        frame.setSize(750,620);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accion = 2;
                PantallaEditarProveedores.tipoAccion(accion);
                if (validaCampos()) {
                    JOptionPane.showMessageDialog(null, "ERROR, seleccione un proveedor",
                            "EDITAR PROVEEDOR", JOptionPane.ERROR_MESSAGE);
                } else {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JFrame frame = new PantallaEditarProveedores();
                            frame.setVisible(true);
                            frame.setSize(750,620);
                            frame.setLocationRelativeTo(null);
                            frame.setResizable(false);
                        }
                    });
                    dispose();
                }
            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validaCampos()) {
                        JOptionPane.showMessageDialog(null, "ERROR, seleccione un proveedor",
                                "BORRAR PROVEEDOR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int idP = Integer.parseInt(id);
                        DataManager.borrarProveedor(idP);
                        for (int i = 0; i < proveedores.size(); i++) {
                            if (proveedores.get(i).getId() == idP) {
                                proveedores.remove(proveedores.get(i));
                            }
                        }
                        crearTabla();
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaPedidos(usuarioID);
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
        btnCliente.addActionListener(new ActionListener() {
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
                        frame.setResizable(false);
                    }
                });
                dispose();
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

        tabla.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                rellenarDatos(mouseEvent);
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
                case "DIRECCIÓN":
                    campo = 2;
                    break;
                case "TELÉFONO":
                    campo = 3;
                    break;
                case "NIF":
                    campo = 4;
                    break;
                case "CORREO":
                    campo = 5;
                    break;
                case "ID CIUDAD":
                    campo = 6;
                    break;
            }
            //"(?i)" regex para no distinguir entre mayúsculas y minúsculas al buscar
            filtroTabla.setRowFilter(RowFilter.regexFilter("(?i)" + parametroBusqueda, campo));
        }
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnCliente.setIcon(iconoClientes);

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidos.png");
        btnPedido.setIcon(iconoPedidos);

        ImageIcon iconoProveedores = new ImageIcon("imagenes/iconoProveedoresBlanco.png");
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
    }

    private void formatoBotones() {
        btnCliente.setUI(new BasicButtonUI());
        btnCliente.setBorder(null);
        btnCliente.setContentAreaFilled(false);

        btnPedido.setUI(new BasicButtonUI());
        btnPedido.setBorder(null);
        btnPedido.setContentAreaFilled(false);

        btnProveedor.setUI(new BasicButtonUI());

        btnProducto.setUI(new BasicButtonUI());
        btnProducto.setBorder(null);
        btnProducto.setContentAreaFilled(false);

        btnTipo.setUI(new BasicButtonUI());
        btnTipo.setBorder(null);
        btnTipo.setContentAreaFilled(false);

        btnTienda.setUI(new BasicButtonUI());
        btnTienda.setBorder(null);
        btnTienda.setContentAreaFilled(false);

        btnEmpleado.setUI(new BasicButtonUI());
        btnEmpleado.setBorder(null);
        btnEmpleado.setContentAreaFilled(false);

        btnCiudad.setUI(new BasicButtonUI());
        btnCiudad.setBorder(null);
        btnCiudad.setContentAreaFilled(false);

        btnAdd.setUI(new BasicButtonUI());
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);

        btnEdit.setUI(new BasicButtonUI());
        btnEdit.setFocusPainted(false);
        btnEdit.setBorderPainted(false);

        btnBorrar.setUI(new BasicButtonUI());
        btnBorrar.setFocusPainted(false);
        btnBorrar.setBorderPainted(false);

        btnLogout.setBackground(null);
        btnLogout.setBorder(null);
        btnLogout.setContentAreaFilled(false);

        btnPerfil.setUI(new BasicButtonUI());
        btnPerfil.setFocusPainted(false);
        btnPerfil.setBorderPainted(false);
    }

    private void rellenarDatos(MouseEvent mouseEvent) {
        JTable table = (JTable) mouseEvent.getSource();
        Point p = mouseEvent.getPoint();
        int row = table.rowAtPoint(p);
        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

            id = table.getModel().getValueAt(row, 0).toString();
            nombre = table.getModel().getValueAt(row, 1).toString();
            direccion = table.getModel().getValueAt(row, 2).toString();
            tlf = table.getModel().getValueAt(row, 3).toString();
            nif = table.getModel().getValueAt(row, 4).toString();
            correo = table.getModel().getValueAt(row, 5).toString();
            idCiudad = table.getModel().getValueAt(row, 6).toString();
            PantallaEditarProveedores.pasarDatos(id, nombre, direccion, tlf, nif, correo, idCiudad);
        }
    }

    public  void cargarClientes(){
        if (DataManager.getProveedores()){
            proveedores = DataManager.getListaProveedores();
            crearTabla();
        }
    }

    private void crearTabla() {
        String[][] data = new String[proveedores.size()][7];

        for (int i = 0; i < proveedores.size(); i++) {
            data[i][0] = String.valueOf(proveedores.get(i).getId());
            data[i][1] = proveedores.get(i).getNombre();
            data[i][2] = proveedores.get(i).getDireccion();
            data[i][3] = proveedores.get(i).getTelefono();
            data[i][4] = proveedores.get(i).getNIF();
            data[i][5] = proveedores.get(i).getCorreo();
            data[i][6] = String.valueOf(proveedores.get(i).getIDCiudad());
        }

        tabla.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "NOMBRE", "DIRECCIÓN", "TELÉFONO", "NIF", "CORREO", "ID CIUDAD"}
        ));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setEnabled(true);
        tabla.setDefaultEditor(Object.class, null);
    }

    private boolean validaCampos(){
        return id.isEmpty() || nombre.isEmpty() || direccion.isEmpty()
                || tlf.isEmpty() || nif.isEmpty() || correo.isEmpty() || idCiudad.isEmpty();
    }
}