import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

import static javax.swing.SwingUtilities.invokeLater;

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
    private JPanel panelLogo;
    private JPanel fotoYNombrePanel;
    private JLabel fotoUsuario;
    private JLabel NombreUsuario;
    private JLabel FechaIncorporacion;

    public PantallaUsuario(){
        JFrame perfil = new JFrame("Tu perfil");
        perfil.setContentPane(panelPrincipalPantallaUsuario);
        perfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        perfil.setVisible(true);
        perfil.pack();
        perfil.setLocationRelativeTo(null);
        setLogo();
        configurarBotones();

        btnEditarMisDatos.addActionListener(e -> {
            JDialog editarDatos = new EditarDatosUsuario(this, "Tú perfil");
            editarDatos.setVisible(true);
        });

        btnHistorialDePedidos.addActionListener(e -> {
            JDialog historialPedidos = new HistorialDePedidosDelUsuario(this, "Tú perfil");
            historialPedidos.setVisible(true);
        });
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

        crearTareaButton.setUI(new BasicButtonUI());
        crearTareaButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        btnVistaGeneral.setUI(new BasicButtonUI());
        btnVistaGeneral.setBorder(null);
    }

    public static void main(String[] args) {
        invokeLater(PantallaUsuario::new);
    }
}
