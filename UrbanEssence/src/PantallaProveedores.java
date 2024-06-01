import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PantallaProveedores extends JFrame{
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
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JTextField buscarTextField;
    private JLabel lblLogo;
    private JTable tabla;

    static ArrayList<Proveedor> proveedores = new ArrayList<>();

    public PantallaProveedores() {
        super("Panel Proveedores");
        setContentPane(panelGeneral);
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        crearTabla();
        cargarIconos();
        formatoBotones();
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnCliente.setIcon(iconoClientes);

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidos.png");
        btnPedido.setIcon(iconoPedidos);

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
        btnCliente.setUI(new BasicButtonUI());
        btnCliente.setBorder(null);
        btnCliente.setContentAreaFilled(false);

        btnPedido.setUI(new BasicButtonUI());
        btnPedido.setBorder(null);
        btnPedido.setContentAreaFilled(false);

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

        btnAdd.setUI(new BasicButtonUI());
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);

        btnEdit.setUI(new BasicButtonUI());
        btnEdit.setFocusPainted(false);
        btnEdit.setBorderPainted(false);

        btnDelete.setUI(new BasicButtonUI());
        btnDelete.setFocusPainted(false);
        btnDelete.setBorderPainted(false);

        btnLogout.setBackground(null);
        btnLogout.setBorder(null);
        btnLogout.setContentAreaFilled(false);

        btnPerfil.setUI(new BasicButtonUI());
        btnPerfil.setFocusPainted(false);
        btnPerfil.setBorderPainted(false);
    }

    private void crearTabla() {
        String[][] data = new String[proveedores.size()][7];

        for (int i = 0; i < proveedores.size(); i++) {
            data[i][0] = String.valueOf(proveedores.get(i).getId());
            data[i][1] = proveedores.get(i).getNombre();
            data[i][2] = proveedores.get(i).getCorreo();
            data[i][3] = String.valueOf(proveedores.get(i).getNIF());
            data[i][4] = proveedores.get(i).getTelefono();
            data[i][5] = proveedores.get(i).getDireccion();
            data[i][6] = String.valueOf(proveedores.get(i).getIDCiudad());
        }

        tabla.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "NOMBRE", "CORREO", "NIF", "TELÉFONO", "DIRECCIÓN", "ID CIUDAD"}
        ));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setEnabled(true);
        tabla.setDefaultEditor(Object.class, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new PantallaProveedores();
                frame.setVisible(true);
                frame.setSize(1080,670);
                frame.setLocationRelativeTo(null);
            }
        });
    }
}
