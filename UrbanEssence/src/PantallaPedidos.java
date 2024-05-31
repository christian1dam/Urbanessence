import javax.swing.*;

public class PantallaPedidos extends JFrame{
    private JPanel panelGeneral;
    private JTable table1;
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
    private JTextField textField1;
    private JPanel panelTabla;

    public PantallaPedidos() {
        super("");
        setContentPane(panelGeneral);



        //Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 4, true);
        //panelTabla.setBorder(roundedBorder);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new PantallaPedidos();
                frame.setVisible(true);
                frame.setSize(1400,800);
                frame.setLocationRelativeTo(null);
            }
        });
    }
}
