import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
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
    private JTextField txtMarca;
    private JTextField txtTalla;

    static ArrayList<Ciudad> ciudades = new ArrayList<>();

    public PantallaAddCiudad() {
        super("Editar Clientes");
        setContentPane(panelGeneral);
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        cargarIconos();
        formatoBotones();

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
