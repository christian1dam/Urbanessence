import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaClientes();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                    }
                });
            }
        });
    }

    public static void main(String[] args) {
        Frame frame = new PantallaLogin();
        frame.setVisible(true);
        frame.setSize(800,650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
}