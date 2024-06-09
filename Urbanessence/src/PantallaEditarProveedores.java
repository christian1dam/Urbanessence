import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;

public class PantallaEditarProveedores extends JFrame{
    private JPanel panelGeneral;
    private JButton btnPedidos;
    private JButton btnClientes;
    private JButton btnProveedor;
    private JButton btnProducto;
    private JButton btnTipo;
    private JButton btnTienda;
    private JButton btnEmpleado;
    private JButton btnCiudad;
    private JButton btnPerfil;
    private JButton btnLogout;
    private JButton btnGuardar;
    private JButton btnCerrar;
    private JLabel lblLogo;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtNIF;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JLabel lblNombre;
    private JLabel lblGenero;
    private JLabel lblDireccion;
    private JLabel lblTelefono;
    private JLabel lblFNac;
    private JLabel lblTitulo;
    private JTextField txtIDCiudad;

    static String id, nombre, direccion , tlf, nif, correo, idCiudad;
    static int accion;

    public PantallaEditarProveedores() {
        super("Editar Proveedores");
        setContentPane(panelGeneral);
        if (accion == 1) {
            lblTitulo.setText("Añadir Proveedores");
            resTXTs();
        } else {
            lblTitulo.setText("Editar Proveedores");
            setDatos();
        }

        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        cargarIconos();
        formatoBotones();

        btnCerrar.addActionListener(new ActionListener() {
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
                resDatos();
                dispose();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idP, idCiudadP;
                String nombreP, direccionP, tlfP, nifP, correoP;

                try {
                    switch (accion) {
                        case 1 -> {
                            if (validaCampos()) {
                                JOptionPane.showMessageDialog(null, "ERROR, complete todos los campos",
                                        "AÑADIR PROVEEDOR", JOptionPane.ERROR_MESSAGE);
                            } else {
                                nombreP = txtNombre.getText();
                                direccionP = txtDireccion.getText();
                                tlfP = txtTelefono.getText();
                                nifP = txtNIF.getText();
                                correoP = txtCorreo.getText();
                                idCiudadP = Integer.parseInt(txtIDCiudad.getText());
                                DataManager.addProveedor(nombreP, direccionP, tlfP, nifP, correoP, idCiudadP);
                                PantallaProveedores.proveedores.add(new Proveedor(DataManager.idAutoGenProveedor(), nombreP, direccionP, tlfP, nifP, correoP, idCiudadP));
                                JOptionPane.showMessageDialog(null, "Proveedor añadido correctamente",
                                        "AÑADIR PROVEEDOR", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        case 2 -> {
                            if (validaCampos()) {
                                JOptionPane.showMessageDialog(null, "ERROR, complete todos los campos",
                                        "EDITAR PROVEEDOR", JOptionPane.ERROR_MESSAGE);
                            } else {
                                idP = Integer.parseInt(id);
                                nombreP = txtNombre.getText();
                                direccionP = txtDireccion.getText();
                                tlfP = txtTelefono.getText();
                                nifP = txtNIF.getText();
                                correoP = txtCorreo.getText();
                                idCiudadP = Integer.parseInt(txtIDCiudad.getText());
                                DataManager.editarProveedor(idP, nombreP, direccionP, tlfP, nifP, correoP, idCiudadP);

                                for (int i = 0; i < PantallaProveedores.proveedores.size(); i++) {
                                    if (PantallaProveedores.proveedores.get(i).getId() == idP) {
                                        PantallaProveedores.proveedores.set(i, new Proveedor(idP, nombreP, direccionP, tlfP, nifP, correoP, idCiudadP));
                                    }
                                }

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

                                JOptionPane.showMessageDialog(null, "Proveedor modificado correctamente",
                                        "EDITAR PROVEEDOR", JOptionPane.INFORMATION_MESSAGE);

                                resDatos();
                                dispose();
                            }
                        }
                    }
                } catch (NumberFormatException | DateTimeParseException ex) {
                    ex.printStackTrace();
                }
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
    }

    public static void pasarDatos(String idStr, String nombreStr, String direccionStr, String tlfStr, String nifStr, String correoStr, String idCiudadStr) {
        id = idStr;
        nombre = nombreStr;
        direccion = direccionStr;
        tlf = tlfStr;
        nif = nifStr;
        correo = correoStr;
        idCiudad = idCiudadStr;
    }

    public static void tipoAccion(int acc) {
        accion = acc;
    }

    private void setDatos() {
        txtNombre.setText(nombre);
        txtDireccion.setText(direccion);
        txtTelefono.setText(tlf);
        txtNIF.setText(nif);
        txtCorreo.setText(correo);
        txtIDCiudad.setText(idCiudad);
    }

    private void resDatos() {
        id = ""; nombre = ""; direccion = ""; tlf = ""; nif = ""; correo = ""; idCiudad = ""; accion = 0;
    }

    private void resTXTs() {
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtNIF.setText("");
        txtCorreo.setText("");
        txtIDCiudad.setText("");
    }

    private boolean validaCampos(){
        return txtNombre.getText().isEmpty() || txtDireccion.getText().isEmpty() || txtTelefono.getText().isEmpty()
                || txtNIF.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtIDCiudad.getText().isEmpty();
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnClientes.setIcon(iconoClientes);

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidos.png");
        btnPedidos.setIcon(iconoPedidos);

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

        btnClientes.setUI(new BasicButtonUI());
        btnClientes.setBorder(null);
        btnClientes.setContentAreaFilled(false);

        btnPedidos.setUI(new BasicButtonUI());
        btnPedidos.setBorder(null);
        btnPedidos.setContentAreaFilled(false);

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
