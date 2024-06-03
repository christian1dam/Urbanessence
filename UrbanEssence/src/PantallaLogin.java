import javax.swing.*;
import java.awt.*;

public class PantallaLogin extends JFrame{
    private JPanel panel1;
    private JTextField txtUsuario;
    private JTextField txtContra;
    private JButton btnLogin;
    private JLabel lblRecu;
    private JLabel lblAyuda;
    private JLabel lblPPrivacidad;
    private JLabel lblImg;
    private JLabel lblTitulo;
    private JPanel JPanelLogin;

    public PantallaLogin(){
        super("Login");
        setContentPane(JPanelLogin);
        ImageIcon iconoTiendas = new ImageIcon("imagenes/LogoLogin.jpg");
        lblImg.setIcon(iconoTiendas);
    }

    public static void main(String[] args) {
        Frame frame = new PantallaLogin();
        frame.setVisible(true);
        frame.setSize(800,650);
        frame.setLocationRelativeTo(null);
    }
}
