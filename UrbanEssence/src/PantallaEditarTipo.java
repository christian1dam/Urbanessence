import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantallaEditarTipo extends JFrame{
    private JPanel panelGeneral;
    private JButton btnTipo;
    private JButton btnClientes;
    private JButton btnProveedor;
    private JButton btnProducto;
    private JButton btnPedidos;
    private JButton btnTienda;
    private JButton btnEmpleado;
    private JButton btnCiudad;
    private JButton btnPerfil;
    private JButton btnLogout;
    private JButton btnGuardar;
    private JButton btnCerrar;
    private JLabel lblLogo;
    private JLabel lblNombre;
    private JLabel lblTitulo;
    private JComboBox boxNombres;

    static ArrayList<Tipo> tipos = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new PantallaEditarTipo();
                frame.setVisible(true);
                frame.setSize(750,620);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
            }
        });
    }

    public PantallaEditarTipo() {
        super("Editar Tipo");
        setContentPane(panelGeneral);
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        cargarIconos();
        formatoBotones();

        boxNombres.setModel(new DefaultComboBoxModel(new String []{"Camisetas", "Pantalones", "Chaquetas", "Zapatillas", "Jerseys", "Sudaderas", "Tops", "Ropa interior", "Shorts", "Abrigos", "Chalecos", "Ropa de deporte"}));

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        btnPedidos.addActionListener(new ActionListener() {
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
        btnClientes.setIcon(iconoClientes);

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidosBlanco.png");
        btnTipo.setIcon(iconoPedidos);

        ImageIcon iconoProveedores = new ImageIcon("imagenes/iconoProveedores.png");
        btnProveedor.setIcon(iconoProveedores);

        ImageIcon iconoProductos = new ImageIcon("imagenes/iconoProductos.png");
        btnProducto.setIcon(iconoProductos);

        ImageIcon iconoTipo = new ImageIcon("imagenes/iconoTipo.png");
        btnPedidos.setIcon(iconoTipo);

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

        btnClientes.setUI(new BasicButtonUI());
        btnClientes.setBorder(null);
        btnClientes.setContentAreaFilled(false);

        btnTipo.setUI(new BasicButtonUI());

        btnProveedor.setUI(new BasicButtonUI());
        btnProveedor.setBorder(null);
        btnProveedor.setContentAreaFilled(false);

        btnProducto.setUI(new BasicButtonUI());
        btnProducto.setBorder(null);
        btnProducto.setContentAreaFilled(false);

        btnPedidos.setUI(new BasicButtonUI());
        btnPedidos.setBorder(null);
        btnPedidos.setContentAreaFilled(false);

        btnTienda.setUI(new BasicButtonUI());
        btnTienda.setBorder(null);
        btnTienda.setContentAreaFilled(false);

        btnEmpleado.setUI(new BasicButtonUI());
        btnEmpleado.setBorder(null);
        btnEmpleado.setContentAreaFilled(false);

        btnCiudad.setUI(new BasicButtonUI());
        btnCiudad.setBorder(null);
        btnCiudad.setContentAreaFilled(false);

        btnGuardar.setUI(new BasicButtonUI());
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);

        btnCerrar.setUI(new BasicButtonUI());
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);

        btnLogout.setBackground(null);
        btnLogout.setBorder(null);
        btnLogout.setContentAreaFilled(false);

        btnPerfil.setUI(new BasicButtonUI());
        btnPerfil.setFocusPainted(false);
        btnPerfil.setBorderPainted(false);
    }
}
