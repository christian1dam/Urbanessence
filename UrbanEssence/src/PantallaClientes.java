import javax.swing.*;

public class PantallaClientes extends JFrame {
    private JPanel JPanelClientes;
    private JButton btnBienvenido;
    private JButton btnCiudades;
    private JButton btnEmpleados;
    private JButton btnTiendas;
    private JButton btnTipo;
    private JButton btnProductos;
    private JButton btnProveedores;
    private JButton btnPedidos;
    private JButton btnClientes;
    private JButton btnAnyadir;
    private JButton btnEditar;
    private JButton btnBorrar;
    private JButton btnBuscar;
    private JTable table1;
    private JLabel lblLogo;

    public PantallaClientes(){
        super("Pantalla Clientes");
        setContentPane(JPanelClientes);
        ImageIcon imageLogo = new ImageIcon("imagenes/LogoModificado.png");
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        lblLogo.setIcon(imageLogo);
        btnClientes.setIcon(iconoClientes);
    }

    public static void main(String[] args) {
        JFrame frame = new PantallaClientes();
        frame.setVisible(true);
        frame.setSize(1200,800);
        frame.setLocationRelativeTo(null);
    }
}
