import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PantallaEditarPedidos extends JFrame{
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
    private JTextField txtFecha;
    private JTextField txtTotal;
    private JTextField txtEstado;
    private JTextField txtIDCliente;
    private JTextField txtIDEmpleado;
    private JLabel lblNombre;
    private JLabel lblGenero;
    private JLabel lblDireccion;
    private JLabel lblTelefono;
    private JLabel lblFNac;
    private JLabel lblTitulo;

    static String id, fecha, totalPedido, estado, idCliente, idEmpleado;

    public PantallaEditarPedidos() {
        super("Editar Pedidos");
        setContentPane(panelGeneral);
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);
        setDatos();
        cargarIconos();
        formatoBotones();

        btnCerrar.addActionListener(new ActionListener() {
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
                resDatos();
                dispose();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validaCampos()) {
                    JOptionPane.showMessageDialog(null, "ERROR, rellene todos los campos",
                            "EDITAR PEDIDO", JOptionPane.ERROR_MESSAGE);
                } else{
                    try {
                        int idP = Integer.parseInt(id);
                        LocalDate fecha = LocalDate.parse(txtFecha.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        double totalPedidoP = Double.parseDouble(txtTotal.getText());

                        int estadoP;
                        if (txtEstado.getText().equalsIgnoreCase("entregado")) estadoP = 1;
                        else estadoP = 0;

                        int idClienteP = Integer.parseInt(txtIDCliente.getText());
                        int idEmpleadoP = Integer.parseInt(txtIDEmpleado.getText());

                        DataManager.editarPedido(idP, fecha, totalPedidoP, estadoP, idClienteP, idEmpleadoP);

                        for (int i = 0; i < PantallaPedidos.pedidos.size(); i++) {
                            if (PantallaPedidos.pedidos.get(i).getId() == idP) {
                                PantallaPedidos.pedidos.set(i, new Pedido(idP, fecha, totalPedidoP, estadoP, idClienteP, idEmpleadoP));
                            }
                        }
                    } catch (NumberFormatException | DateTimeParseException ex) {
                        ex.printStackTrace();
                    }
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
                    JOptionPane.showMessageDialog(null, "Pedido modificado correctamente",
                            "EDITAR PEDIDO", JOptionPane.INFORMATION_MESSAGE);
                    resDatos();
                    dispose();
                }
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

    public static void pasarDatos(String idStr, String fechaStr, String totalPedidoStr, String estadoStr, String idClienteStr, String idEmpleadoStr) {
        id = idStr;
        fecha = fechaStr;
        totalPedido = totalPedidoStr;
        estado = estadoStr;
        idCliente = idClienteStr;
        idEmpleado = idEmpleadoStr;
    }

    private void setDatos() {
        txtFecha.setText(fecha);
        txtTotal.setText(totalPedido);
        txtEstado.setText(estado);
        txtIDCliente.setText(idCliente);
        txtIDEmpleado.setText(idEmpleado);
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnClientes.setIcon(iconoClientes);

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidosBlanco.png");
        btnPedidos.setIcon(iconoPedidos);

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
    }

    private void formatoBotones() {

        btnClientes.setUI(new BasicButtonUI());
        btnClientes.setBorder(null);
        btnClientes.setContentAreaFilled(false);

        btnPedidos.setUI(new BasicButtonUI());

        btnProveedor.setUI(new BasicButtonUI());
        btnProveedor.setBorder(null);
        btnProveedor.setContentAreaFilled(false);

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

    private void resDatos() {
        id = ""; fecha = ""; totalPedido = ""; estado = ""; idCliente = ""; idEmpleado = "";
    }

    private boolean validaCampos(){
        return txtFecha.getText().isEmpty() || txtTotal.getText().isEmpty() || txtEstado.getText().isEmpty()
                || txtIDCliente.getText().isEmpty() || txtIDEmpleado.getText().isEmpty();
    }
}