import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingUtilities.invokeLater;

public class PantallaUsuario extends JFrame {
    private int usuarioID;
    private Empleado empleado;

    private JButton btnLogout;
    private JButton btnPerfil;
    private JButton btnVistaGeneral;
    private JButton btnVolverAtras;
    private JButton editarButton;
    private JPanel panelPrincipalPantallaUsuario;
    private JLabel imagenUrbanEssence;
    private JPanel headerPanel;
    private JPanel divBotonesHeader;
    private JPanel asideLeft;
    private JPanel main;
    private JPanel panelLogo;
    private JPanel fotoYNombrePanel;
    private JLabel fotoUsuario;
    private JLabel NombreUsuario;
    private JLabel FechaIncorporacion;
    private JLabel miPerfil;
    private JPanel datos1panel;
    private JLabel labelFechaContratacion;
    private JLabel labelContrasenya;
    private JLabel labelNuevaContrasenya;
    private JLabel labelConfirmarContrasenya;
    private JPanel datos2panel;
    private JLabel labelNombre;
    private JLabel labelApellidos;
    private JLabel labelFechaNacimietno;
    private JLabel labelCargo;
    private JTextField contrasenyaUsuario;
    private JTextField nuevaContrasenyaUsuario;
    private JTextField confirmarNuevaContrasenyaUsuario;
    private JTextField fechaContrUsuario;
    private JTextField nombreUsuario;
    private JTextField apellidosUsuario;
    private JTextField fechaNacUsuario;
    private JTextField cargoUsuario;

    public PantallaUsuario(int usuarioID) {
        this.usuarioID = usuarioID;


        JFrame perfil = new JFrame("Tu perfil");
        perfil.setContentPane(panelPrincipalPantallaUsuario);
        perfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        perfil.setVisible(true);
        perfil.pack();
        perfil.setLocationRelativeTo(null);
        setLogo();

        configurarBotones();
        configurarTextFields();
        cargarDatosDelUsuario();

        btnVolverAtras.addActionListener(e -> {
            PantallaClientes pantallaClientes = new PantallaClientes(usuarioID);
            pantallaClientes.setVisible(true);
            pantallaClientes.setSize(1080,670);
            pantallaClientes.setLocationRelativeTo(null);
            perfil.dispose();
        });

        btnLogout.addActionListener(e -> {
            PantallaLogin logout = new PantallaLogin();
            logout.main(null);
            perfil.dispose();
        });

        editarButton.addActionListener(e -> {
            if (editarButton.getText().equals("CAMBIAR CONTRASEÑA")) {
                enableTextField();
                editarButton.setText("GUARDAR");
            } else {
                if (confirmarNuevaContrasenyaUsuario.getText().equals(nuevaContrasenyaUsuario.getText()) && DataManager.updateEmployeeData(usuarioID, confirmarNuevaContrasenyaUsuario.getText())) {
                    JOptionPane.showMessageDialog(this, "Se ha cambiado la contraseña correctamente.");
                    nuevaContrasenyaUsuario.setText(null);
                    confirmarNuevaContrasenyaUsuario.setText(null);
                    disableTextField();
                } else {
                    JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
                }
                editarButton.setText("CAMBIAR CONTRASEÑA");
            }
        });
    }

    private void enableTextField() {
        this.nuevaContrasenyaUsuario.setEnabled(true);
        this.nuevaContrasenyaUsuario.setBackground(new Color(255, 255, 255));
        this.confirmarNuevaContrasenyaUsuario.setEnabled(true);
        this.confirmarNuevaContrasenyaUsuario.setBackground(new Color(255, 255, 255));
    }

    private void disableTextField() {
        nuevaContrasenyaUsuario.setEnabled(false);
        nuevaContrasenyaUsuario.setBackground(new Color(169, 169, 169));
        confirmarNuevaContrasenyaUsuario.setEnabled(false);
        confirmarNuevaContrasenyaUsuario.setBackground(new Color(169, 169, 169));
    }

    private void cargarDatosDelUsuario() {
        this.empleado = DataManager.getAllDataFromEmployee(usuarioID);
        assert this.empleado != null;
        nombreUsuario.setText(this.empleado.getNombre());
        apellidosUsuario.setText(this.empleado.getApellidos());
        fechaNacUsuario.setText(String.valueOf(this.empleado.getFechaNac()));
        cargoUsuario.setText(this.empleado.getCargo());
        fechaContrUsuario.setText(String.valueOf(this.empleado.getFechaCont()));
        contrasenyaUsuario.setText("**********");
    }

    public PantallaUsuario() {
        JFrame perfil = new JFrame("Tu perfil");
        perfil.setContentPane(panelPrincipalPantallaUsuario);
        perfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        perfil.setVisible(true);
        perfil.pack();
        perfil.setLocationRelativeTo(null);
        setLogo();

        configurarBotones();
        configurarTextFields();
        btnVolverAtras.addActionListener(e -> {
            PantallaClientes pantallaClientes = new PantallaClientes(usuarioID);
            pantallaClientes.setVisible(true);
            pantallaClientes.setSize(1080,670);
            pantallaClientes.setLocationRelativeTo(null);
        });

        btnLogout.addActionListener(e -> {
            PantallaLogin logout = new PantallaLogin();
            logout.main(null);
            perfil.dispose();
        });

        editarButton.addActionListener(e -> {
            System.out.println("Editar button clicked"); // Agregado para depurar
            enableTextField();
            editarButton.setText("GUARDAR");
            editarButton.addActionListener(f ->{
                if(confirmarNuevaContrasenyaUsuario.equals(nuevaContrasenyaUsuario) && DataManager.updateEmployeeData(usuarioID, confirmarNuevaContrasenyaUsuario.getText())) {
                    JOptionPane.showMessageDialog(this, "Se ha cambiado la contraseña correctamente.");
                } else JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            });
        });


        btnVolverAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaProveedores();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
            }
        });
    }

    private void configurarTextFields() {
        contrasenyaUsuario.setEnabled(false);
        nuevaContrasenyaUsuario.setEnabled(false);
        confirmarNuevaContrasenyaUsuario.setEnabled(false);
        fechaContrUsuario.setEnabled(false);
        nombreUsuario.setEnabled(false);
        apellidosUsuario.setEnabled(false);
        fechaNacUsuario.setEnabled(false);
        cargoUsuario.setEnabled(false);
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

        btnVolverAtras.setUI(new BasicButtonUI());
        btnVolverAtras.setBorder(null);

        btnVistaGeneral.setUI(new BasicButtonUI());
        btnVistaGeneral.setBorder(null);

        editarButton.setUI(new BasicButtonUI());
        editarButton.setBorder(null);
    }

    public static void main(String[] args) {
        invokeLater(PantallaUsuario::new);
    }
}
