import javax.swing.*;

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

        configurarBotones();
    }

    private void configurarBotones() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PantallaUsuario::new);
    }
}
