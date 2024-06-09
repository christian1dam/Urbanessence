import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class PantallaEditarProductos extends JFrame{
    private JPanel panelGeneral;
    private JButton btnPedidos;
    private JButton btnProductos;
    private JButton btnProveedor;
    private JButton btnClientes;
    private JButton btnTipo;
    private JButton btnTienda;
    private JButton btnEmpleado;
    private JButton btnCiudad;
    private JButton btnPerfil;
    private JButton btnLogout;
    private JButton btnGuardar;
    private JButton btnCerrar;
    private JLabel lblLogo;
    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JLabel lblMarca;
    private JLabel lblTalla;
    private JLabel lblColor;
    private JLabel lblMaterial;
    private JLabel lblPrecio;
    private JLabel lblIdTipo;
    private JLabel lblCanitdad;
    private JTextField txtNombre;
    private JTextField txtMarca;
    private JTextField txtTalla;
    private JTextField txtColor;
    private JTextField txtMaterial;
    private JTextField txtPrecio;
    private JTextField txtIdTipo;
    private JTextField txtCantidad;
    private JTextField txtIdProveedor;
    private JLabel lblIdProveedor;

    static ArrayList<Producto> productos = new ArrayList<>();
    static String id = "", nombre = "", marca = "" , talla = "", color = "", material = "",
            precio = "", idTipo = "", idProveedor = "", cantidad = "";
    static int accion;

    public PantallaEditarProductos() {
        super("Editar Clientes");
        setContentPane(panelGeneral);
        if (accion == 1) {
            lblTitulo.setText("Añadir Producto");
            resTXTs();
        } else {
            lblTitulo.setText("Editar Producto");
            setDatos();
        }
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        cargarIconos();
        formatoBotones();

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
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idP;
                String nombreP, marcaP, tallaP, colorP, materialP, precioP, idTipoP, idProveedor, cantidad;

                try {
                    switch (accion) {
                        case 1 -> {
                            nombreP = txtNombre.getText();
                            marcaP = txtMarca.getText();
                            tallaP = txtTalla.getText();
                            colorP = txtColor.getText();
                            materialP = txtMaterial.getText();
                            precioP = txtPrecio.getText();
                            idTipoP = txtIdTipo.getText();
                            idProveedor = txtIdProveedor.getText();
                            cantidad = txtCantidad.getText();
                            DataManager.addProducto(nombreP, marcaP, tallaP, colorP, materialP, Double.parseDouble(precioP),
                                    Integer.parseInt(idTipoP), Integer.parseInt(idProveedor), Integer.parseInt(cantidad));
                            PantallaProductos.productos.add(new Producto(DataManager.idAutoGenProducto(), nombreP, marcaP, tallaP, colorP, materialP,
                                    Double.parseDouble(precioP), Integer.parseInt(idTipoP), Integer.parseInt(idProveedor), Integer.parseInt(cantidad)));
                            JOptionPane.showMessageDialog(null, "Producto añadido correctamente",
                                    "AÑADIR PRODUCTO", JOptionPane.INFORMATION_MESSAGE);
                        }

                        case 2 -> {
                            idP = Integer.parseInt(id);
                            nombreP = txtNombre.getText();
                            marcaP = txtMarca.getText();
                            tallaP = txtTalla.getText();
                            colorP = txtColor.getText();
                            materialP = txtMaterial.getText();
                            precioP = txtPrecio.getText();
                            idTipoP = txtIdTipo.getText();
                            idProveedor = txtIdProveedor.getText();
                            cantidad = txtCantidad.getText();
                            DataManager.addProducto(nombreP, marcaP, tallaP, colorP, materialP, Double.parseDouble(precioP),
                                    Integer.parseInt(idTipoP), Integer.parseInt(idProveedor), Integer.parseInt(cantidad));

                            for (int i = 0; i < PantallaProductos.productos.size(); i++) {
                                if (PantallaProductos.productos.get(i).getId() == idP) {
                                    PantallaProductos.productos.set(i, new Producto(idP, nombreP, marcaP, tallaP, colorP, materialP,
                                            Double.parseDouble(precioP), Integer.parseInt(idTipoP),
                                            Integer.parseInt(idProveedor), Integer.parseInt(cantidad)));
                                }
                            }

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    JFrame frame = new PantallaProductos();
                                    frame.setVisible(true);
                                    frame.setSize(1080, 670);
                                    frame.setLocationRelativeTo(null);
                                    frame.setResizable(false);
                                }
                            });

                            JOptionPane.showMessageDialog(null, "Producto modificado correctamente",
                                    "EDITAR PRODUCTO", JOptionPane.INFORMATION_MESSAGE);

                            resDatos();
                            dispose();
                        }
                    }
                }catch (NumberFormatException | DateTimeParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnCerrar.addActionListener(new ActionListener() {
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
                resDatos();
                dispose();
            }
        });
    }

    public static void pasarDatos(String idStr, String nombreStr, String marcaStr, String tallaStr, String colorStr, String materialStr,
                                  String precioStr, String idTipoStr, String idProveedorStr, String cantidadStr) {
        id = idStr;
        nombre = nombreStr;
        marca = marcaStr;
        talla = tallaStr;
        color = colorStr;
        material = materialStr;
        precio = precioStr;
        idTipo = idTipoStr;
        idProveedor = idProveedorStr;
        cantidad = cantidadStr;
    }

    public static void tipoAccion(int acc) {
        accion = acc;
    }

    private void setDatos() {
        txtNombre.setText(nombre);
        txtMarca.setText(marca);
        txtTalla.setText(talla);
        txtColor.setText(color);
        txtMaterial.setText(material);
        txtPrecio.setText(precio);
        txtIdTipo.setText(idTipo);
        txtIdProveedor.setText(idProveedor);
        txtCantidad.setText(cantidad);
    }

    private void resTXTs() {
        txtNombre.setText("");
        txtMarca.setText("");
        txtTalla.setText("");
        txtColor.setText("");
        txtMaterial.setText("");
        txtPrecio.setText("");
        txtIdTipo.setText("");
        txtIdProveedor.setText("");
        txtCantidad.setText("");
    }

    private void resDatos() {
        id = ""; nombre = ""; marca = ""; talla = ""; color = "";
        material = ""; precio = ""; idTipo = ""; idProveedor = ""; cantidad = "";
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnClientes.setIcon(iconoClientes);

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidos.png");
        btnPedidos.setIcon(iconoPedidos);

        ImageIcon iconoProveedores = new ImageIcon("imagenes/iconoProveedores.png");
        btnProveedor.setIcon(iconoProveedores);

        ImageIcon iconoProductos = new ImageIcon("imagenes/iconoProductosBlanco.png");
        btnProductos.setIcon(iconoProductos);

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
        btnPedidos.setUI(new BasicButtonUI());
        btnPedidos.setBorder(null);
        btnPedidos.setContentAreaFilled(false);

        btnProductos.setUI(new BasicButtonUI());

        btnProveedor.setUI(new BasicButtonUI());
        btnProveedor.setBorder(null);
        btnProveedor.setContentAreaFilled(false);

        btnClientes.setUI(new BasicButtonUI());
        btnClientes.setBorder(null);
        btnClientes.setContentAreaFilled(false);

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
