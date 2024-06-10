import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField nussEmpleado;
    private JTextField salarioEmpleado;
    private JTextField nombreUsuario;
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
    private JPanel panelPrincipalEditarEmpleado;

    public EditarEmpleado(JFrame parent, String titulo, Empleado empleado){
       super(parent, titulo, true);
        setContentPane(panelPrincipalEditarEmpleado);
        pack();
        setLocationRelativeTo(null);
        setLogo();
        configurarBotones();

        cargarDatos(empleado);
        guardarBtn.addActionListener(e ->{
            empleado.setCargo(cargoEmpleado.getText());
            empleado.setSalario(Double.valueOf(salarioEmpleado.getText()));
            if(DataManager.updateEmployeeSalarioAndCargo(empleado)) {
                JOptionPane.showMessageDialog(this, "Se ha actualizado correctamente el empleado.", "Datos actualizados", JOptionPane.INFORMATION_MESSAGE);
                EditarEmpleado.this.dispose();
            } else JOptionPane.showMessageDialog(this, "Ha habido un error al actualizar el empleaod", "ERROR", JOptionPane.ERROR_MESSAGE);
        });

        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaClientes();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
            }
        });
        btnPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaPedidos(usuarioID);
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
            }
        });
        btnProveedores.addActionListener(new ActionListener() {
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
        btnProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaProductos();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
            }
        });
        btnTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaTipo();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
            }
        });
        btnTienda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaTiendas();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
            }
        });
        btnCiudades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaCiudades();
                        frame.setVisible(true);
                        frame.setSize(1080,670);
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                    }
                });
                dispose();
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaLogin();
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

    private void cargarDatos(Empleado empleado) {
        nombreEmpleado.setText(empleado.getNombre());
        apellidosEmpleado.setText(empleado.getApellidos());
        fechaNacEmpleado.setText(String.valueOf(empleado.getFechaNac()));
        fechaContrEmpleado.setText(String.valueOf(empleado.getFechaCont()));
        nussEmpleado.setText(empleado.getNuss());
        cargoEmpleado.setText(empleado.getCargo());
        salarioEmpleado.setText(String.valueOf(empleado.getSalario()));
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
