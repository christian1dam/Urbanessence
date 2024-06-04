import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class EditarDatosUsuario extends JDialog {

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
    private JButton btnVistaGeneral;
    private JButton btnCalendarioDeTareas;
    private JButton btnHistorialDePedidos;
    private JButton btnEditarMisDatos;
    private JPanel main;
    private JPanel panelPrincipalEditarDatos;
    private JLabel miPerfil;
    private JTextField nombreUsuario;
    private JLabel labelFechaContratacion;
    private JLabel labelContrasenya;
    private JLabel labelNuevaContrasenya;
    private JLabel labelConfirmarContrasenya;
    private JLabel labelNombre;
    private JLabel labelApellidos;
    private JLabel labelFechaNacimietno;
    private JLabel labelCargo;
    private JTextField apellidosUsuario;
    private JTextField fechaNacUsuario;
    private JTextField cargoUsuario;
    private JTextField fechaContrUsuario;
    private JTextField contrasenyaUsuario;
    private JTextField nuevaContrasenyaUsuario;
    private JTextField confirmarNuevaContrasenyaUsuario;
    private JButton guardarBtn;
    private JPanel datos1panel;
    private JPanel datos2panel;

    public EditarDatosUsuario(JFrame parent, String titulo) {
        super(parent, titulo, true);
        setContentPane(panelPrincipalEditarDatos);
        pack();
        setLocationRelativeTo(null);
        setLogo();
        configurarBotones();
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

        guardarBtn.setUI(new BasicButtonUI());
        guardarBtn.setBorder(null);
    }
}
