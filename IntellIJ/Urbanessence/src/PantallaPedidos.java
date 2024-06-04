import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton btnBuscar;

    static ArrayList<Pedido> pedidos = new ArrayList<>();

    public PantallaPedidos() {
        super("Panel Pedidos");
        setContentPane(panelGeneral);
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        crearTabla();
        cargarIconos();
        formatoBotones();

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        btnProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaPedidos(); //todo
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
            }
        });//todo: cambiar la pantalla (productos)

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
            }
        });

        btnEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaPedidos(); //todo: cambiar la pantalla
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
            }
        });//todo: cambiar la pantalla (empleado)

        btnCiudad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaPedidos(); //todo: cambiar la pantalla
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
            }
        });//todo: cambiar la pantalla (ciudad)

        btnPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaPedidos(); //todo: cambiar la pantalla
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
            }
        });//todo: cambiar la pantalla (perfil)

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
            }
        });
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

        ImageIcon iconoLupa = new ImageIcon("imagenes/iconoLupa.png");
        btnBuscar.setIcon(iconoLupa);

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

        btnLogout.setBackground(null);
        btnLogout.setBorder(null);
        btnLogout.setContentAreaFilled(false);

        btnPerfil.setUI(new BasicButtonUI());
        btnPerfil.setFocusPainted(false);
        btnPerfil.setBorderPainted(false);

        btnBuscar.setUI(new BasicButtonUI());
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
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
}
