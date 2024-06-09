import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.invokeLater;

public class PantallaPrincipalEmpleados extends JFrame {
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
    private MouseAdapter doubleClickListener;

    public PantallaPrincipalEmpleados() {
        if (DBManager.loadDriverSQLServer() && DBManager.openConnectionToDatabase()) {
            JOptionPane.showMessageDialog(this, "JDBC cargado correctamente y conexion con sqlserver correcta");
        } else {
            JOptionPane.showMessageDialog(this, "error al conectar con la bd");
        }

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
            JOptionPane.showMessageDialog(this, "Para poder editar un empleado haz doble click sobre él en la tabla");
            permitirDobleClick();
        });

        anyadirBTN.addActionListener(e -> {
            JDialog abrirCreacionEmpleado = new CrearEmpleado(this, "Crear empleado");
            abrirCreacionEmpleado.setVisible(true);
            this.cargarTabla();
        });

        borrarBTN.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Para eliminar un empleado haz TRIPLE click sobre él en la tabla", "Borrar empleado", JOptionPane.INFORMATION_MESSAGE);
            permitirTripleClick();
        });
    }

    private void permitirTripleClick() {
        this.tablaEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                assert empleados != null;
                empleados = DataManager.getListaEmpleados();
                JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                if (e.getClickCount() == 3 && table.getSelectedRow() != -1) {
                    if (DataManager.deleteEmpleado(empleados.get(row))) {
                        JOptionPane.showMessageDialog(PantallaPrincipalEmpleados.this, "El empleado se ha borrado correctamente", "Eliminar empleado", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(PantallaPrincipalEmpleados.this, "Ha habido un error al eliminar el empleado", "Eliminar empleado", JOptionPane.ERROR_MESSAGE);
                    }
                    cargarTabla();
                }
            }
        });
    }

    private void permitirDobleClick() {
        if (doubleClickListener == null) {
            doubleClickListener = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    empleados = DataManager.getListaEmpleados();
                    JTable table = (JTable) e.getSource();
                    Point point = e.getPoint();
                    int row = table.rowAtPoint(point);
                    if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                        EditarEmpleado edit = new EditarEmpleado(PantallaPrincipalEmpleados.this, "Editar empleado", empleados.get(row));
                        edit.setVisible(true);
                        cargarTabla();
                        deshabilitarDobleClick();
                    }
                }
            };
            this.tablaEmpleados.addMouseListener(doubleClickListener);
        }
    }

    private void deshabilitarDobleClick() {
        if (doubleClickListener != null) {
            this.tablaEmpleados.removeMouseListener(doubleClickListener);
            doubleClickListener = null;
        }
    }

    private void cargarTabla() {
        empleados.clear();
        if (DataManager.getEmpleados()) empleados = DataManager.getListaEmpleados();
        else JOptionPane.showMessageDialog(this, "Ha habido un erro la cargar los datos de los empleados");
        System.out.println(empleados);
        String[] columnNames = {"ID", "Nombre", "Apellidos", "Fecha de nacimiento", "dirección", "fecha de contratación", "cargo", "salario", "NUSS", "Tienda ID", "Usuario", "Contraseña"};
        String[][] data = new String[empleados.size()][columnNames.length];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = String.valueOf(empleados.get(i).getId());
            data[i][1] = empleados.get(i).getNombre();
            data[i][2] = empleados.get(i).getApellidos();
            data[i][3] = String.valueOf(empleados.get(i).getFechaNac());
            data[i][4] = empleados.get(i).getDireccion();
            data[i][5] = String.valueOf(empleados.get(i).getFechaCont());
            data[i][6] = empleados.get(i).getCargo();
            data[i][7] = String.valueOf(empleados.get(i).getSalario());
            data[i][8] = empleados.get(i).getNuss();
            data[i][9] = String.valueOf(empleados.get(i).getIdTienda());
            data[i][10] = empleados.get(i).getUsuario();
            data[i][11] = empleados.get(i).getContrasenya();
        }

        tablaEmpleados.setModel(new DefaultTableModel(data, columnNames) {
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
