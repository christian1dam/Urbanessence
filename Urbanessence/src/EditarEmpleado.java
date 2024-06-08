import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class EditarEmpleado extends JDialog {
    private JPanel main;
    private JPanel datos2panel;
    private JLabel labelNombre;
    private JLabel labelApellidos;
    private JLabel labelFechaNacimietno;
    private JLabel labelCargo;
    private JTextField nombreEmpleado;
    private JTextField apellidosEmpleado;
    private JTextField fechaNacEmpleado;
    private JTextField cargoEmpleado;
    private JLabel labelFechaContratacion;
    private JTextField fechaContrEmpleado;
    private JPanel datos1panel;
    private JLabel nussLabel;
    private JLabel salarioLabel;
    private JLabel nombreUsuarioLabel;
    private JTextField nussEmpleado;
    private JTextField salarioEmpleado;
    private JLabel contrasenyaLabe;
    private JTextField confirmarContraseña;
    private JTextField nombreUsuario;
    private JLabel nuevaContrasenyaLabel;
    private JTextField contrasenya;
    private JLabel crearEmpleadoLABEL;
    private JButton cancelarBtn;
    private JButton guardarBtn;
    private JPanel asideLeft;
    private JButton btnTienda;
    private JButton btnProductos;
    private JButton btnTipo;
    private JButton btnEmpleados;
    private JButton btnCiudades;
    private JButton btnProveedores;
    private JButton btnPedidos;
    private JButton btnClientes;
    private JPanel headerPanel;
    private JPanel divBotonesHeader;
    private JButton btnPerfil;
    private JButton btnLogout;
    private JPanel panelLogo;
    private JLabel imagenUrbanEssence;
    private JTextField nuevaContrasenya;
    private JLabel confirmarContrasenyaLabel;
    private JPanel panelPrincipalEditarEmpleado;

    public EditarEmpleado(JFrame parent, String titulo){
       super(parent, titulo, true);
        setContentPane(panelPrincipalEditarEmpleado);
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

        btnTipo.setUI(new BasicButtonUI());
        btnTipo.setBorder(null);

        btnTienda.setUI(new BasicButtonUI());
        btnTienda.setBorder(null);

        btnProductos.setUI(new BasicButtonUI());
        btnProductos.setBorder(null);

        btnEmpleados.setUI(new BasicButtonUI());
        btnEmpleados.setBorder(null);

        btnCiudades.setUI(new BasicButtonUI());
        btnCiudades.setBorder(null);

        btnProveedores.setUI(new BasicButtonUI());
        btnProveedores.setBorder(null);

        btnPedidos.setUI(new BasicButtonUI());
        btnPedidos.setBorder(null);

        btnClientes.setUI(new BasicButtonUI());
        btnClientes.setBorder(null);

        cancelarBtn.setUI(new BasicButtonUI());
        cancelarBtn.setBorder(null);

        guardarBtn.setUI(new BasicButtonUI());
        guardarBtn.setBorder(null);
    }
}