import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantallaProductos extends JFrame{
    private JPanel panelGeneral;
    private JButton btnPedido;
    private JButton btnProductos;
    private JButton btnProveedor;
    private JButton btnTiendas;
    private JButton btnTipo;
    private JButton btnClientes;
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

    static ArrayList<Producto> productos = new ArrayList<>();

    public PantallaProductos() {
        super("Panel Productos");
        setContentPane(panelGeneral);
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);

        crearTabla();
        cargarIconos();
        formatoBotones();
        cargarProdcutos();
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaEditarProductos();
                        frame.setVisible(true);
                        frame.setSize(745,620);
                        frame.setLocationRelativeTo(null);
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
                        JFrame frame = new PantallaEditarProductos();
                        frame.setVisible(true);
                        frame.setSize(745,620);
                        frame.setLocationRelativeTo(null);
                    }
                });
            }
        });
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnClientes.setIcon(iconoClientes);
        btnClientes.setUI(new BasicButtonUI());

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidos.png");
        btnPedido.setIcon(iconoPedidos);
        btnPedido.setUI(new BasicButtonUI());

        ImageIcon iconoProveedores = new ImageIcon("imagenes/iconoProveedores.png");
        btnProveedor.setIcon(iconoProveedores);

        ImageIcon iconoProductos = new ImageIcon("imagenes/iconoProductosBlanco.png");
        btnProductos.setIcon(iconoProductos);

        ImageIcon iconoTipo = new ImageIcon("imagenes/iconoTipo.png");
        btnTipo.setIcon(iconoTipo);

        ImageIcon iconoTiendas = new ImageIcon("imagenes/iconoTiendas.png");
        btnTiendas.setIcon(iconoTiendas);

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

        btnTiendas.setBorder(null);
        btnTiendas.setContentAreaFilled(false);

        btnTipo.setBorder(null);
        btnTipo.setContentAreaFilled(false);

        btnClientes.setBorder(null);
        btnClientes.setContentAreaFilled(false);

        btnEmpleado.setBorder(null);
        btnEmpleado.setContentAreaFilled(false);

        btnCiudad.setBorder(null);
        btnCiudad.setContentAreaFilled(false);

        btnLogout.setBackground(null);
        btnLogout.setBorder(null);
        btnLogout.setContentAreaFilled(false);
    }


    public void cargarProdcutos (){
        if (DataManager.getProductos()){
            productos = DataManager.getListaProductos();
            crearTabla();
        }
    }

    private void crearTabla() {
        String[][] data = new String[productos.size()][8];

        for (int i = 0; i < productos.size(); i++) {
            data[i][0] = String.valueOf(productos.get(i).getId());
            data[i][1] = productos.get(i).getNombre();
            data[i][2] = productos.get(i).getMarca();
            data[i][3] = productos.get(i).getTalla();
            data[i][4] = productos.get(i).getMaterial();
            data[i][5] = String.valueOf(productos.get(i).getPrecio());
            data[i][6] = String.valueOf(productos.get(i).getIdTipo());
            data[i][7] = String.valueOf(productos.get(i).getIdProveedor());
            data[i][7] = String.valueOf(productos.get(i).getCantidad());
        }

        tabla.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "NOMBRE", "MARCA", "TALLA", "COLOR", "MATERIAL", "PRECIO", "IDTIPO", "CANTIDAD"}
        ));

        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setEnabled(true);
        tabla.setDefaultEditor(Object.class, null);
    }
}
