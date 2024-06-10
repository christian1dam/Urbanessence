import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PantallaPedidos extends JFrame{
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
    private JButton btnEdit;
    private JTextField buscarTextField;
    private JLabel lblLogo;
    private JTable tabla;
    private JButton btnBorrar;
    private JComboBox boxBuscar;

    static ArrayList<Pedido> pedidos = new ArrayList<>();
    static String id = "", fecha = "", totalPedido = "", estado = "", idCliente = "", idEmpleado = "";

    public PantallaPedidos(int usuarioID) {
        super("Panel Pedidos");
        setContentPane(panelGeneral);
        pedidos.clear();
        cargarClientes();
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        crearTabla();
        cargarIconos();
        formatoBotones();

        btnPerfil.addActionListener(e ->{
            PantallaUsuario userScreen = new PantallaUsuario(usuarioID);
        });

        boxBuscar.setModel(new DefaultComboBoxModel(new String []{"ID", "FECHA", "TOTAL PEDIDO", "ESTADO", "ID CLIENTE", "ID EMPLEADO"}));

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validaCampos()) {
                    JOptionPane.showMessageDialog(null, "ERROR, seleccione un pedido",
                            "EDITAR PEDIDO", JOptionPane.ERROR_MESSAGE);
                } else {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JFrame frame = new PantallaEditarPedidos();
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
                        JOptionPane.showMessageDialog(null, "ERROR, seleccione un pedido",
                                "BORRAR PEDIDO", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int idP = Integer.parseInt(id);
                        DataManager.borrarPedido(idP);
                        for (int i = 0; i < pedidos.size(); i++) {
                            if (pedidos.get(i).getId() == idP) {
                                pedidos.remove(pedidos.get(i));
                            }
                        }
                        crearTabla();
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
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
                case "FECHA":
                    campo = 1;
                    break;
                case "TOTAL PEDIDO":
                    campo = 2;
                    break;
                case "ESTADO":
                    campo = 3;
                    break;
                case "ID CLIENTE":
                    campo = 4;
                    break;
                case "ID EMPLEADO":
                    campo = 5;
                    break;
            }
            //"(?i)" regex para no distinguir entre mayúsculas y minúsculas al buscar
            filtroTabla.setRowFilter(RowFilter.regexFilter("(?i)" + parametroBusqueda, campo));
        }
    }

    private void rellenarDatos(MouseEvent mouseEvent) {
        JTable table = (JTable) mouseEvent.getSource();
        Point p = mouseEvent.getPoint();
        int row = table.rowAtPoint(p);
        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

            id = table.getModel().getValueAt(row, 0).toString();
            fecha = table.getModel().getValueAt(row, 1).toString();
            totalPedido = table.getModel().getValueAt(row, 2).toString();
            estado = table.getModel().getValueAt(row, 3).toString();
            idCliente = table.getModel().getValueAt(row, 4).toString();
            idEmpleado = table.getModel().getValueAt(row, 5).toString();
            PantallaEditarPedidos.pasarDatos(id, fecha, totalPedido, estado, idCliente, idEmpleado);
        }
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnCliente.setIcon(iconoClientes);

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidosBlanco.png");
        btnPedido.setIcon(iconoPedidos);

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
    }

    private void formatoBotones() {
        btnCliente.setUI(new BasicButtonUI());
        btnCliente.setBorder(null);
        btnCliente.setContentAreaFilled(false);

        btnPedido.setUI(new BasicButtonUI());

        btnProveedor.setUI(new BasicButtonUI());
        btnProveedor.setBorder(null);
        btnProveedor.setContentAreaFilled(false);

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

    public  void cargarClientes(){
        if (DataManager.getPedidos()){
            pedidos = DataManager.getListaPedidos();
            crearTabla();
        }
    }

    private void crearTabla() {
        String[][] data = new String[pedidos.size()][6];

        for (int i = 0; i < pedidos.size(); i++) {
            data[i][0] = String.valueOf(pedidos.get(i).getId());
            data[i][1] = String.valueOf(pedidos.get(i).getFecha());
            data[i][2] = String.valueOf(pedidos.get(i).getTotalPedido());
            data[i][3] = pedidos.get(i).getEstado();
            data[i][4] = String.valueOf(pedidos.get(i).getIdCliente());
            data[i][5] = String.valueOf(pedidos.get(i).getIdEmpleado());
        }

        tabla.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "FECHA", "TOTAL PEDIDO", "ESTADO", "ID CLIENTE", "ID EMPLEADO"}
        ));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setEnabled(true);
        tabla.setDefaultEditor(Object.class, null);
    }

    private boolean validaCampos(){
        return id.isEmpty() || fecha.isEmpty() || totalPedido.isEmpty()
                || estado.isEmpty() || idCliente.isEmpty() || idEmpleado.isEmpty();
    }
}
