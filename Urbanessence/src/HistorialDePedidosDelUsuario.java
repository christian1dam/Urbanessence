import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialDePedidosDelUsuario extends JDialog {
    private List<Pedido> pedidos = new ArrayList<>();

    private JPanel panelPrincipalHPU;
    private JPanel headerPanel;
    private JPanel divBotonesHeader;
    private JButton btnPerfil;
    private JButton btnLogout;
    private JPanel panelLogo;
    private JLabel imagenUrbanEssence;
    private JPanel fotoYNombrePanel;
    private JLabel NombreUsuario;
    private JLabel FechaIncorporacion;
    private JLabel fotoUsuario;
    private JPanel asideLeft;
    private JButton btnCalendarioDeTareas;
    private JButton btnHistorialDePedidos;
    private JButton btnEditarMisDatos;
    private JButton btnVistaGeneral;
    private JPanel main;
    private JLabel miPerfil;
    private JTable tablaHistorialDePedidosUsuario;
    private JScrollPane scrollPane;

    public HistorialDePedidosDelUsuario(PantallaUsuario pantallaUsuario, String tuPerfil) {
        super(pantallaUsuario, tuPerfil, true);
        setContentPane(panelPrincipalHPU);
        pack();
        setLocationRelativeTo(null);
        setLogo();
        configurarBotones();

        cargarTabla();
    }

    private void cargarTabla() {
        String[] columnNames = {"ID", "Fecha", "Total pedido", "Estado", "Cliente ID", "ProductoID"};
        String[][] data = new String[pedidos.size()][columnNames.length];
        for (int i = 0; i <data.length; i++) {
//            LLENAR TABLA USUARIOS, EN ESTE MOMENTO NO SE HARÁ
        }

        tablaHistorialDePedidosUsuario.setModel(new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tablaHistorialDePedidosUsuario.getTableHeader().setReorderingAllowed(false);
        tablaHistorialDePedidosUsuario.getTableHeader().setOpaque(false);
        tablaHistorialDePedidosUsuario.getTableHeader().setBackground(Color.white);
        tablaHistorialDePedidosUsuario.setFillsViewportHeight(true);
        tablaHistorialDePedidosUsuario.setBackground(Color.WHITE);
    }

    private void setLogo() {
        ImageIcon icon = new ImageIcon("imagenes/logo.png");
        Image imagen = icon.getImage().getScaledInstance(230, 70, Image.SCALE_SMOOTH);
        imagenUrbanEssence.setIcon(new ImageIcon(imagen));
    }

    private void configurarBotones() {
        btnLogout.setUI(new BasicButtonUI());
        btnLogout.setIcon(new ImageIcon("imagenes/iconoLogOut.png"));
        btnLogout.setBorder(null);

        btnPerfil.setUI(new BasicButtonUI());
        btnPerfil.setBorder(null);

        btnCalendarioDeTareas.setUI(new BasicButtonUI());
        btnCalendarioDeTareas.setBorder(null);

        btnHistorialDePedidos.setUI(new BasicButtonUI());
        btnHistorialDePedidos.setBorder(null);

        btnEditarMisDatos.setUI(new BasicButtonUI());
        btnEditarMisDatos.setBorder(null);

        btnVistaGeneral.setUI(new BasicButtonUI());
        btnVistaGeneral.setBorder(null);
    }
}
