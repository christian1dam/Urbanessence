import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

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
    private JLabel lblNombreEdit;
    private JTextField txtNombre;

    static ArrayList<String> tipos = new ArrayList<>();
    static String id, nombre;
    static int accion;

    public PantallaEditarTipo() {
        super("Editar Tipo");
        setContentPane(panelGeneral);
        tipos.clear();
        if (accion == 1) {
            lblTitulo.setText("Añadir Tipo");
            boxNombres.setEnabled(false);
            setDatos();
        }else {
            lblTitulo.setText("Editar Tipo");
            txtNombre.setEnabled(false);
            resTXTs();
        }

        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        cargarIconos();
        formatoBotones();
        tipos.addAll(DataManager.obtenerNombresTipo());
        //tipos.addAll(Arrays.asList("Camisetas", "Pantalones", "Chaquetas", "Zapatillas", "Jerseys", "Sudaderas", "Tops", "Ropa interior", "Shorts", "Abrigos", "Chalecos", "Ropa de deporte"));

        boxNombres.setModel(new DefaultComboBoxModel(tipos.toArray()));

        for (int i = 0; i < tipos.size(); i++) {
            if (tipos.get(i).equalsIgnoreCase(nombre)) {
                boxNombres.setSelectedIndex(i);
            }
        }

        btnCerrar.addActionListener(new ActionListener() {
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
                resDatos();
                dispose();
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idP;
                String nombreP;
                try {
                    switch (accion) {
                        case 1 -> {
                            nombreP = txtNombre.getText();
                            DataManager.addTipo(nombreP);
                            PantallaTipo.tipos.add(new Tipo(DataManager.idAutoGenTipo(), nombreP));
                            tipos.add(nombreP);
                            boxNombres.setModel(new DefaultComboBoxModel(tipos.toArray()));
                        }
                        case 2 -> {
                            idP = Integer.parseInt(id);
                            nombreP = txtNombre.getText();
                            
                            DataManager.editarTipo(idP, nombreP);

                            for (int i = 0; i < PantallaTipo.tipos.size(); i++) {
                                if (PantallaTipo.tipos.get(i).getId() == idP) {
                                    PantallaTipo.tipos.set(i, new Tipo(idP, nombreP));
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
                        JFrame frame = new PantallaTipo();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                resDatos();
                resTXTs();
                dispose();
            }
        });

        /*btnPedidos.addActionListener(new ActionListener() {
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
        });*/

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
    }

    public static void pasarDatos(String idStr, String nombreStr) {
        id = idStr;
        nombre = nombreStr;
    }
    public static void tipoAccion(int acc) {
        accion = acc;
    }

    private void setDatos() {
        txtNombre.setText(nombre);

    }

    private void resDatos() {
        id = ""; nombre = ""; accion = 0;
    }

    private void resTXTs() {
        txtNombre.setText("");
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
