import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.invokeLater;

public class PantallaPrincipalEmpleados extends JFrame{
    private List<Empleado> empleados = new ArrayList<>();

    private JPanel panelPrincipalEmpleado;
    private JPanel headerPanel;
    private JPanel divBotonesHeader;
    private JPanel panelLogo;
    private JPanel opcionesDelEmpleadoPanel;
    private JPanel main;
    private JPanel asideLeft;
    private JPanel panelOpciones;
    private JLabel imagenUrbanEssence;
    private JTextField buscarTextField;
    private JButton btnPerfil;
    private JButton btnBuscar;
    private JButton btnLogout;
    private JButton anyadirBTN;
    private JButton borrarBTN;
    private JButton editarBTN;
    private JButton btnTienda;
    private JButton btnProductos;
    private JButton btnTipo;
    private JButton btnEmpleados;
    private JButton btnCiudades;
    private JButton btnProveedores;
    private JButton btnPedidos;
    private JButton btnClientes;
    private JScrollPane scrollPane;
    private JTable tablaEmpleados;

    public PantallaPrincipalEmpleados(){
        JFrame perfil = new JFrame("Empleados");
        perfil.setContentPane(panelPrincipalEmpleado);
        perfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        perfil.setVisible(true);
        perfil.pack();
        perfil.setLocationRelativeTo(null);
        setLogo();
        configurarBotones();
        cargarTabla();

        editarBTN.addActionListener(e -> {
            JDialog abrirEditor = new EditarDatosUsuario(this, "Editar empleado");
            abrirEditor.setVisible(true);
        });

        anyadirBTN.addActionListener(e -> {
            JDialog abrirCreacionEmpleado = new CrearEmpleado(this, "Crear empleado");
            abrirCreacionEmpleado.setVisible(true);
        });
    }




    private void cargarTabla() {
        String[] columnNames = {"ID", "Nombre", "Apellidos", "Fecha de nacimiento", "dirección", "fecha de contratación", "cargo", "salario", "NUSS", "Tienda ID"};
        String[][] data = new String[empleados.size()][columnNames.length];
        for (int i = 0; i <data.length; i++) {
//            LLENAR TABLA USUARIOS, EN ESTE MOMENTO NO SE HARÁ
        }

        tablaEmpleados.setModel(new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tablaEmpleados.getTableHeader().setReorderingAllowed(false);
        tablaEmpleados.getTableHeader().setOpaque(false);
        tablaEmpleados.getTableHeader().setBackground(Color.white);
        tablaEmpleados.setFillsViewportHeight(true);
        Color white = new Color(255, 255, 255);
        tablaEmpleados.setBackground(white);
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

        btnBuscar.setUI(new BasicButtonUI());
        btnBuscar.setBorder(null);

        anyadirBTN.setUI(new BasicButtonUI());
        anyadirBTN.setBorder(null);

        borrarBTN.setUI(new BasicButtonUI());
        borrarBTN.setBorder(null);

        editarBTN.setUI(new BasicButtonUI());
        editarBTN.setBorder(null);

        btnCiudades.setUI(new BasicButtonUI());
        btnCiudades.setBorder(null);

        btnProveedores.setUI(new BasicButtonUI());
        btnProveedores.setBorder(null);

        btnPedidos.setUI(new BasicButtonUI());
        btnPedidos.setBorder(null);

        btnClientes.setUI(new BasicButtonUI());
        btnClientes.setBorder(null);
    }

    public static void main(String[] args) {
        invokeLater(PantallaPrincipalEmpleados::new);
    }
}
