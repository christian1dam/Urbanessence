import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class PantallaEditarTiendas extends JFrame{
    private JPanel panelGeneral;
    private JButton btnCliente;
    private JButton btnTiendas;
    private JButton btnProveedor;
    private JButton btnProducto;
    private JButton btnTipo;
    private JButton btnPedidos;
    private JButton btnEmpleado;
    private JButton btnCiudad;
    private JButton btnPerfil;
    private JButton btnLogout;
    private JButton btnGuardar;
    private JButton btnCerrar;
    private JLabel lblLogo;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtHApertura;
    private JTextField txtHCierre;
    private JTextField txtIdCiudad;
    private JTextField txtNEmpleados;
    private JLabel lblNombre;
    private JLabel lblDireccion;
    private JLabel lblTelefono;
    private JLabel lblHApertura;
    private JLabel lblHCierre;
    private JLabel lblNEmpleados;
    private JLabel lblIdCiudad;
    private JLabel lblTitulo;

    private static String id = "", nombre = "", direccion = "", telefono = "", hApertura = "", hCierre = "", numEmpleados = "", idCiudad = "";
    static int accion;

    static ArrayList<Tienda> tiendas = new ArrayList<>();

    public PantallaEditarTiendas() {
        super("Editar Tiendas");
        setContentPane(panelGeneral);
        if (accion == 1) {
            lblTitulo.setText("Añadir Proveedores");
        }else {
            lblTitulo.setText("Editar Proveedores");
        }

        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        cargarIconos();
        formatoBotones();
        rellenarCampos();

        btnCerrar.addActionListener(new ActionListener() {
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

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idT, numEmpleadosT, idCiudadT;
                String nombreT, direccionT, tlfT, hAperturaTP, hCierreT;
                try {
                    switch (accion) {
                        case 1 -> {
                            nombreT = txtNombre.getText();
                            direccionT = txtDireccion.getText();
                            tlfT = txtTelefono.getText();
                            hAperturaTP = txtHApertura.getText();
                            hCierreT = txtHCierre.getText();
                            numEmpleadosT = Integer.parseInt(txtNEmpleados.getText());
                            idCiudadT = Integer.parseInt(txtIdCiudad.getText());
                            DataManager.addTienda(nombreT, direccionT, tlfT, LocalTime.parse(hAperturaTP), LocalTime.parse(hCierreT), numEmpleadosT, idCiudadT);
                            PantallaTiendas.tiendas.add(new Tienda(DataManager.idAutoGenTienda(), nombreT, direccionT, tlfT, LocalTime.parse(hAperturaTP), LocalTime.parse(hCierreT), numEmpleadosT, idCiudadT));
                        }
                        case 2 -> {
                            idT = Integer.parseInt(id);
                            nombreT = txtNombre.getText();
                            direccionT = txtDireccion.getText();
                            tlfT = txtTelefono.getText();
                            hAperturaTP = txtHApertura.getText();
                            hCierreT = txtHCierre.getText();
                            numEmpleadosT = Integer.parseInt(txtNEmpleados.getText());
                            idCiudadT = Integer.parseInt(txtIdCiudad.getText());
                            DataManager.editarTienda(idT, nombreT, direccionT, tlfT, LocalTime.parse(hAperturaTP), LocalTime.parse(hCierreT), numEmpleadosT, idCiudadT);

                            for (int i = 0; i < PantallaTiendas.tiendas.size(); i++) {
                                if (PantallaTiendas.tiendas.get(i).getId() == idT) {
                                    PantallaTiendas.tiendas.set(i, new Tienda(idT, nombreT, direccionT, tlfT, LocalTime.parse(hAperturaTP), LocalTime.parse(hCierreT), numEmpleadosT, idCiudadT));
                                }
                            }
                        }
                    }
                } catch (NumberFormatException | DateTimeParseException ex) {
                    ex.printStackTrace();
                }

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
        btnPedidos.addActionListener(new ActionListener() {
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

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnCliente.setIcon(iconoClientes);

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidos.png");
        btnPedidos.setIcon(iconoPedidos);

        ImageIcon iconoProveedores = new ImageIcon("imagenes/iconoProveedores.png");
        btnProveedor.setIcon(iconoProveedores);

        ImageIcon iconoProductos = new ImageIcon("imagenes/iconoProductos.png");
        btnProducto.setIcon(iconoProductos);

        ImageIcon iconoTipo = new ImageIcon("imagenes/iconoTipo.png");
        btnTipo.setIcon(iconoTipo);

        ImageIcon iconoTiendas = new ImageIcon("imagenes/iconoTiendasBlanco.png");
        btnTiendas.setIcon(iconoTiendas);

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

        btnTiendas.setUI(new BasicButtonUI());

        btnProveedor.setUI(new BasicButtonUI());
        btnProveedor.setBorder(null);
        btnProveedor.setContentAreaFilled(false);

        btnProducto.setUI(new BasicButtonUI());
        btnProducto.setBorder(null);
        btnProducto.setContentAreaFilled(false);

        btnTipo.setUI(new BasicButtonUI());
        btnTipo.setBorder(null);
        btnTipo.setContentAreaFilled(false);

        btnPedidos.setUI(new BasicButtonUI());
        btnPedidos.setBorder(null);
        btnPedidos.setContentAreaFilled(false);

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

    public void rellenarCampos(){
        id = PantallaTiendas.getId();
        accion = PantallaTiendas.getAccion();
        nombre = PantallaTiendas.getNombre();
        txtNombre.setText(nombre);
        direccion = PantallaTiendas.getDireccion();
        txtDireccion.setText(direccion);
        telefono = PantallaTiendas.getTelefono();
        txtTelefono.setText(String.valueOf(telefono));
        hApertura = PantallaTiendas.gethApertura();
        txtHApertura.setText(hApertura);
        hCierre = PantallaTiendas.gethCierre();
        txtHCierre.setText(hCierre);
        numEmpleados = PantallaTiendas.getNumEmpleados();
        txtNEmpleados.setText(String.valueOf(numEmpleados));
        idCiudad = PantallaTiendas.getIdCiudad();
        txtIdCiudad.setText(String.valueOf(idCiudad));
    }

    private void resDatos() {
        id = ""; nombre = ""; direccion = ""; telefono = ""; hApertura = ""; hCierre = ""; numEmpleados = ""; idCiudad = "";
    }
}