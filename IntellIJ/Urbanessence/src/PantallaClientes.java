import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton btnBuscar;

    static ArrayList<Cliente> clientes = new ArrayList<>();

    public PantallaClientes() {
        super("Panel Clientes");
        setContentPane(panelGeneral);
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        crearTabla();
        cargarIconos();
        formatoBotones();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaEditarClientes();
                        frame.setVisible(true);
                        frame.setSize(745,620);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaEditarClientes();
                        frame.setVisible(true);
                        frame.setSize(745,620);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
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

        ImageIcon imgBuscar = new ImageIcon("imagenes/iconoLupa.png");
        btnBuscar.setIcon(imgBuscar);
        btnBuscar.setUI(new BasicButtonUI());
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);

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

        tabla.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "NOMBRE", "GENERO", "F.NACIMIENTO", "H.TELEFONO", "DIRECCION"}
        ));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setEnabled(true);
        tabla.setDefaultEditor(Object.class, null);
    }
}
