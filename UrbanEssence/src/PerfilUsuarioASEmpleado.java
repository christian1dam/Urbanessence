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
    private JPanel divBotonesHeader;

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
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.magenta, 2, true)); //quitar luego
        headerPanel.setPreferredSize(new Dimension(750, 120));
        headerPanel.setBackground(Color.WHITE);
        configuracionDivBotonesHeader();
        perfil.add(headerPanel, BorderLayout.NORTH);

    }

    private void configuracionDivBotonesHeader() {
        divBotonesHeader.setPreferredSize(new Dimension(300, 117));
        divBotonesHeader.setLayout(new FlowLayout(FlowLayout.CENTER));
        divBotonesHeader.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
        divBotonesHeader.setBackground(Color.white);
        configuracionBotonesHeader(divBotonesHeader);
        headerPanel.add(divBotonesHeader, BorderLayout.EAST);
    }



    private void configuracionBotonesHeader(JPanel panel) {
        btnPerfil.setUI(new BasicButtonUI());
        btnPerfil.setContentAreaFilled(true);
        btnPerfil.setBorder(BorderFactory.createEmptyBorder());
        btnPerfil.setBackground(Color.BLACK);
        btnPerfil.setForeground(Color.WHITE);
        btnPerfil.setPreferredSize(new Dimension(80, 20));
        panel.add(btnPerfil);

        btnLogout.setUI(new BasicButtonUI());
        btnLogout.setContentAreaFilled(true);
        btnLogout.setBorder(BorderFactory.createEmptyBorder());
        btnLogout.setBackground(Color.BLACK);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setPreferredSize(new Dimension(20, 20));
        panel.add(btnLogout);
    }


    private void setImagenes() {
        imagenUrbanEssence.setBorder(null);
        ImageIcon icon = new ImageIcon("imagenes/logo.png");
        Image image = icon.getImage().getScaledInstance(300, 83, Image.SCALE_SMOOTH);
        imagenUrbanEssence.setIcon(new ImageIcon(image));
        headerPanel.add(imagenUrbanEssence);
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
