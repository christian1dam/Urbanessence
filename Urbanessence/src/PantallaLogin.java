import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantallaLogin extends JFrame{
    private Integer usuarioID = 0;

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

    static ArrayList<String> usuarios = new ArrayList<>();
    static ArrayList<String> passwords = new ArrayList<>();

    public PantallaLogin(){
        super("Login");
        setContentPane(JPanelLogin);
        usuarios.clear();
        passwords.clear();

        usuarios.addAll(DataManager.obtenerUsuario());
        passwords.addAll(DataManager.obtenerPasswd());

        ImageIcon iconoTiendas = new ImageIcon("imagenes/LogoLogin.jpg");
        lblImg.setIcon(iconoTiendas);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < usuarios.size(); i++) {
                    if (usuarios.get(i).equalsIgnoreCase(txtUsuario.getText()) && passwords.get(i).equalsIgnoreCase(txtContra.getText())) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                usuarioID = DataManager.getUsuarioID(txtUsuario.getText(), txtContra.getText());
                                UserSession.getInstance().setUserId(usuarioID);
                                JFrame frame = new PantallaClientes();
                                frame.setVisible(true);
                                frame.setSize(1080,670);
                                frame.setLocationRelativeTo(null);
                                frame.setResizable(false);
                            }
                        });
                        dispose();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR, usuario o contraseña incorrectas",
                                "LOGIN", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
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