import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;

public class PerfilUsuarioASEmpleado extends JFrame{
    private JPanel panelPrincipalPantallaUsuario;
    private JButton btnHistorialDePedidos;
    private JButton btnCalendarioDeTareas;
    private JButton btnVistaGeneral;
    private JButton btnPerfil;
    private JButton btnLogout;
    private JButton btnEditarMisDatos;
    private JPanel headerPanel;
    private JPanel asideLeft;
    private JPanel main;
    private JLabel imagenUrbanEssence;

    public PerfilUsuarioASEmpleado(){
        JFrame perfil = new JFrame("Tú perfil");
        perfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        perfil.setBounds(250, 150,750, 620);
        perfil.setBackground(Color.WHITE);
        panelPrincipalPantallaUsuario.setLayout(new BorderLayout());
        perfil.setContentPane(panelPrincipalPantallaUsuario);
        configuracionHeader(perfil);
        configuracionAsideLeft(perfil);
        configuracionMainPanel(perfil);
        setImagenes();
        perfil.setVisible(true);
    }

    private void configuracionHeader(JFrame perfil) {
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(null);
        headerPanel.setPreferredSize(new Dimension(750, 120));
        headerPanel.setBackground(Color.WHITE);
        configuracionBotonesHeader(headerPanel);
        perfil.add(headerPanel, BorderLayout.NORTH);
    }

    private void configuracionBotonesHeader(JPanel headerPanel) {
//        btnPerfil.setUI(new BasicButtonUI());
        btnPerfil.setBorder(null);
//        btnPerfil.setContentAreaFilled(false);
        btnPerfil.setBackground(Color.black);
        btnPerfil.setPreferredSize(new Dimension(80, 20));
        headerPanel.add(btnPerfil, BorderLayout.EAST);

        btnLogout.setUI(new BasicButtonUI());
        btnLogout.setBorder(null);
//        btnLogout.setContentAreaFilled(false);
        btnLogout.setPreferredSize(new Dimension(20, 20));
        btnLogout.setBackground(Color.white);
        headerPanel.add(btnLogout, BorderLayout.EAST);
    }

    private void setImagenes() {
        imagenUrbanEssence.setBorder(null);
        ImageIcon icon = new ImageIcon("imagenes/logo.png");
        Image image = icon.getImage().getScaledInstance(300, 83, Image.SCALE_SMOOTH);
        imagenUrbanEssence.setIcon(new ImageIcon(image));
        headerPanel.add(imagenUrbanEssence, BorderLayout.WEST);
    }

    private void configuracionMainPanel(JFrame perfil) {
        main.setBorder(null);
        main.setBackground(Color.WHITE);
        perfil.add(main, BorderLayout.CENTER);
    }

    private void configuracionAsideLeft(JFrame perfil) {
        asideLeft.setBorder(null);
        asideLeft.setBackground(Color.WHITE);
        asideLeft.setPreferredSize(new Dimension(200, 500));
        asideLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        configuracionBotonesALeft(asideLeft);
        perfil.add(asideLeft, BorderLayout.WEST);
    }

    private void configuracionBotonesALeft(JPanel asideLeft) {
        btnVistaGeneral.setBackground(Color.BLACK);
        btnVistaGeneral.setUI(new BasicButtonUI());
        btnVistaGeneral.setBorder(null);
        btnVistaGeneral.setPreferredSize(new Dimension(200, 50));
        btnVistaGeneral.setVisible(true);

        asideLeft.add(btnVistaGeneral);

        btnEditarMisDatos.setUI(new BasicButtonUI());
        btnEditarMisDatos.setBackground(Color.gray);
        btnEditarMisDatos.setBorder(null);
        btnEditarMisDatos.setPreferredSize(new Dimension(200, 50));
        asideLeft.add(btnEditarMisDatos);

        btnCalendarioDeTareas.setUI(new BasicButtonUI());
        btnCalendarioDeTareas.setBackground(Color.gray);
        btnCalendarioDeTareas.setBorder(null);
        btnCalendarioDeTareas.setPreferredSize(new Dimension(200, 50));
        asideLeft.add(btnCalendarioDeTareas);

        btnHistorialDePedidos.setUI(new BasicButtonUI());
        btnHistorialDePedidos.setBackground(Color.gray);
        btnHistorialDePedidos.setBorder(null);
        btnHistorialDePedidos.setPreferredSize(new Dimension(200, 50));
        asideLeft.add(btnHistorialDePedidos);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PerfilUsuarioASEmpleado::new);
    }
}
