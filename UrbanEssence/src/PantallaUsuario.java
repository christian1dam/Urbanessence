import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class PantallaUsuario extends JFrame{
    private JPanel headerPanel;
    private JLabel imagenUrbanEssence;
    private JPanel divBotonesHeader;
    private JButton btnLogout;
    private JButton btnPerfil;
    private JPanel asideLeft;
    private JButton btnVistaGeneral;
    private JButton btnCalendarioDeTareas;
    private JButton btnHistorialDePedidos;
    private JPanel main;
    private JPanel panelPrincipalPantallaUsuario;
    private JButton btnEditarMisDatos;
    private JLabel enCursoLabel;
    private JLabel pruebasLabel;
    private JLabel AcabadoLabel;
    private JLabel tareaPendienteLabel;
    private JButton crearTareaButton;
    private JPanel tareasPendientes;
    private JPanel tareasAcabadas;
    private JPanel tareasEnCurso;
    private JPanel tareasEnPruebas;
    private JPanel crearTareaPanel;

    public PantallaUsuario(){
        JFrame perfil = new JFrame("Tu perfil");
        perfil.setContentPane(panelPrincipalPantallaUsuario);
        perfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        perfil.setVisible(true);
        perfil.pack();

        configurarLogo();
        configurarBotones();
    }

    private void configurarLogo() {
        ImageIcon logo = new ImageIcon("imagenes/Logo.png");
        Image image = logo.getImage().getScaledInstance(230, 70, Image.SCALE_FAST);
        imagenUrbanEssence.setIcon(new ImageIcon(image));
    }

    private void configurarBotones() {
        btnCalendarioDeTareas.setUI(new BasicButtonUI());
        btnCalendarioDeTareas.setBorder(null);

        btnPerfil.setUI(new BasicButtonUI());
        btnPerfil.setBorder(null);

        btnLogout.setUI(new BasicButtonUI());
        btnLogout.setBorder(null);

        btnVistaGeneral.setUI(new BasicButtonUI());
        btnVistaGeneral.setBorder(null);

        btnEditarMisDatos.setUI(new BasicButtonUI());
        btnEditarMisDatos.setBorder(null);

        btnHistorialDePedidos.setUI(new BasicButtonUI());
        btnHistorialDePedidos.setBorder(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PantallaUsuario::new);
    }
}
