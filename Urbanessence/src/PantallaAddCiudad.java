import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantallaAddCiudad extends JFrame{
    private JPanel panelGeneral;
    private JButton btnPedidos;
    private JButton btnCiudad;
    private JButton btnProveedor;
    private JButton btnClientes;
    private JButton btnTipo;
    private JButton btnTienda;
    private JButton btnEmpleado;
    private JButton btnProductos;
    private JButton btnPerfil;
    private JButton btnLogout;
    private JButton btnGuardar;
    private JButton btnCerrar;
    private JLabel lblLogo;
    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JLabel lblProvincia;
    private JLabel lblNumHabitantes;
    private JTextField txtNombre;
    private JTextField txtProvincia;
    private JTextField txtNumHabitantes;

    static String id = "", nombre = "", provincia = "", numHabitantes = "";

    public PantallaAddCiudad() {
        super("Añadir Ciudad");
        setContentPane(panelGeneral);
        resTXTs();
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
                        JFrame frame = new PantallaCiudades();
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
                String nombreC, provinciaC, numHabitanteC;

                nombreC = txtNombre.getText();
                provinciaC = txtProvincia.getText();
                numHabitanteC = txtNumHabitantes.getText();
                DataManager.addCiudad(nombreC, provinciaC, Integer.parseInt(numHabitanteC));
                PantallaCiudades.ciudades.add(new Ciudad(DataManager.idAutoGenCiudad(), nombreC, provinciaC, Integer.parseInt(numHabitanteC)));
                JOptionPane.showMessageDialog(null, "Ciudad añadida correctamente",
                        "AÑADIR CIUDAD", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnProductos.addActionListener(new ActionListener() {
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
    }

    public static void pasarDatos(String idStr, String nombreStr, String provinciaStr, String numHabitantesStr) {
        id = idStr;
        nombre = nombreStr;
        provincia = provinciaStr;
        numHabitantes = numHabitantesStr;
    }

    private void resDatos() {
        id = ""; nombre = ""; provincia = ""; numHabitantes = "";
    }

    private void resTXTs() {
        txtNombre.setText("");
        txtProvincia.setText("");
        txtNumHabitantes.setText("");
    }

    private boolean validaCampos(){
        return nombre.isEmpty() || provincia.isEmpty() || numHabitantes.isEmpty();
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnClientes.setIcon(iconoClientes);

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidos.png");
        btnPedidos.setIcon(iconoPedidos);

        ImageIcon iconoProveedores = new ImageIcon("imagenes/iconoProveedores.png");
        btnProveedor.setIcon(iconoProveedores);

        ImageIcon iconoProductos = new ImageIcon("imagenes/iconoProductos.png");
        btnProductos.setIcon(iconoProductos);

        ImageIcon iconoTipo = new ImageIcon("imagenes/iconoTipo.png");
        btnTipo.setIcon(iconoTipo);

        ImageIcon iconoTiendas = new ImageIcon("imagenes/iconoTiendas.png");
        btnTienda.setIcon(iconoTiendas);

        ImageIcon iconoEmpleados = new ImageIcon("imagenes/iconoEmpleados.png");
        btnEmpleado.setIcon(iconoEmpleados);

        ImageIcon iconoCiudades = new ImageIcon("imagenes/iconoCiudadesBlanco.png");
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

        btnCiudad.setUI(new BasicButtonUI());

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

        btnProductos.setUI(new BasicButtonUI());
        btnProductos.setBorder(null);
        btnProductos.setContentAreaFilled(false);

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
