import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PantallaCiudades extends JFrame{
    private JPanel panelGeneral;
    private JButton btnPedido;
    private JButton btnCiudad;
    private JButton btnProveedor;
    private JButton btnProducto;
    private JButton btnTipo;
    private JButton btnClientes;
    private JButton btnEmpleado;
    private JButton btnTiendas;
    private JButton btnPerfil;
    private JButton btnLogout;
    private JButton btnAdd;
    private JButton btnDelete;
    private JTextField buscarTextField;
    private JLabel lblLogo;
    private JTable tabla;
    private JComboBox boxBuscar;

    static ArrayList<Ciudad> ciudades = new ArrayList<>();
    static String id = "", nombre = "", provincia = "", numHabitantes = "";


    public PantallaCiudades() {
        super("Panel Ciudades");
        setContentPane(panelGeneral);
        ciudades.clear();
        cargarCiudades();
        ImageIcon imageLogo = new ImageIcon("imagenes/Logo.png");
        lblLogo.setIcon(imageLogo);
        boxBuscar.setModel(new DefaultComboBoxModel(new String[]{"ID", "NOMBRE", "PROVINCIA", "NUMHABITANTES"}));

        crearTabla();
        cargarIconos();
        formatoBotones();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new PantallaAddCiudad();
                        frame.setVisible(true);
                        frame.setSize(745,620);
                        frame.setLocationRelativeTo(null);
                    }
                });
                dispose();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validaCampos()) {
                        JOptionPane.showMessageDialog(null, "ERROR, seleccione una ciudad",
                                "BORRAR CIUDAD", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int idC = Integer.parseInt(id);
                        DataManager.borrarCiudad(idC);
                        for (int i = 0; i < ciudades.size(); i++) {
                            if (ciudades.get(i).getId() == idC) {
                                ciudades.remove(ciudades.get(i));
                            }
                        }
                        crearTabla();
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        });


        tabla.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                rellenarDatos(mouseEvent);
            }
        });

        buscarTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                filtrarTabla();
            }
        });

        btnClientes.addActionListener(new ActionListener() {
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

        btnPedido.addActionListener(new ActionListener() {
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

        btnProveedor.addActionListener(new ActionListener() {
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

        btnProducto.addActionListener(new ActionListener() {
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
        btnTiendas.addActionListener(new ActionListener() {
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
        btnEmpleado.addActionListener(new ActionListener() {
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
    }

    private void filtrarTabla() {
        String parametroBusqueda = buscarTextField.getText();
        String campoBusqueda = boxBuscar.getSelectedItem().toString();

        // obtenemos el modelo de la tabla para poder trabajar con los datos y la estructura de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();

        // el TableRowSorter habilita la funcionalidad de ordenar y filtrar las filas de la tabla
        TableRowSorter<DefaultTableModel> filtroTabla = new TableRowSorter<>(modeloTabla);
        tabla.setRowSorter(filtroTabla);

        if (parametroBusqueda.length() == 0) {
            filtroTabla.setRowFilter(null);
        } else {
            int campo = -1;
            switch (campoBusqueda) {
                case "ID":
                    campo = 0;
                    break;
                case "NOMBRE":
                    campo = 1;
                    break;
                case "PROVINCIA":
                    campo = 2;
                    break;
                case "NUMHABITANTES":
                    campo = 3;
                    break;
            }
            //"(?i)" regex para no distinguir entre mayúsculas y minúsculas al buscar
            filtroTabla.setRowFilter(RowFilter.regexFilter("(?i)" + parametroBusqueda, campo));
        }
    }

    private void cargarIconos() {
        ImageIcon iconoClientes = new ImageIcon("imagenes/iconoClientes.png");
        btnClientes.setIcon(iconoClientes);
        btnClientes.setUI(new BasicButtonUI());

        ImageIcon iconoPedidos = new ImageIcon("imagenes/iconoPedidos.png");
        btnPedido.setIcon(iconoPedidos);
        btnPedido.setUI(new BasicButtonUI());

        ImageIcon iconoProveedores = new ImageIcon("imagenes/iconoProveedores.png");
        btnProveedor.setIcon(iconoProveedores);

        ImageIcon iconoProductos = new ImageIcon("imagenes/iconoProductos.png");
        btnProducto.setIcon(iconoProductos);

        ImageIcon iconoTipo = new ImageIcon("imagenes/iconoTipo.png");
        btnTipo.setIcon(iconoTipo);

        ImageIcon iconoTiendas = new ImageIcon("imagenes/iconoTiendas.png");
        btnTiendas.setIcon(iconoTiendas);

        ImageIcon iconoEmpleados = new ImageIcon("imagenes/iconoEmpleados.png");
        btnEmpleado.setIcon(iconoEmpleados);

        ImageIcon iconoCiudades = new ImageIcon("imagenes/iconoCiudadesBlanco.png");
        btnCiudad.setIcon(iconoCiudades);

        ImageIcon iconoLogOut = new ImageIcon("imagenes/iconoLogOut.png");
        btnLogout.setIcon(iconoLogOut);


        ImageIcon iconoPerfil = new ImageIcon("imagenes/iconoPerfil.png");
        btnPerfil.setIcon(iconoPerfil);
        btnPerfil.setUI(new BasicButtonUI());
        btnPerfil.setFocusPainted(false);
        btnPerfil.setBorderPainted(false);

        btnAdd.setUI(new BasicButtonUI());
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);

        btnDelete.setUI(new BasicButtonUI());
        btnDelete.setFocusPainted(false);
        btnDelete.setBorderPainted(false);
    }

    private void formatoBotones() {

        btnPedido.setBorder(null);
        btnPedido.setContentAreaFilled(false);

        btnProveedor.setBorder(null);
        btnProveedor.setContentAreaFilled(false);

        btnProducto.setBorder(null);
        btnProducto.setContentAreaFilled(false);

        btnTipo.setBorder(null);
        btnTipo.setContentAreaFilled(false);

        btnClientes.setBorder(null);
        btnClientes.setContentAreaFilled(false);

        btnEmpleado.setBorder(null);
        btnEmpleado.setContentAreaFilled(false);

        btnTiendas.setBorder(null);
        btnTiendas.setContentAreaFilled(false);

        btnLogout.setBackground(null);
        btnLogout.setBorder(null);
        btnLogout.setContentAreaFilled(false);
    }

    private void rellenarDatos(MouseEvent mouseEvent) {
        JTable table = (JTable) mouseEvent.getSource();
        Point p = mouseEvent.getPoint();
        int row = table.rowAtPoint(p);
        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

            id = table.getModel().getValueAt(row, 0).toString();
            nombre = table.getModel().getValueAt(row, 1).toString();
            provincia = table.getModel().getValueAt(row, 2).toString();
            numHabitantes = table.getModel().getValueAt(row, 3).toString();
            PantallaAddCiudad.pasarDatos(id, nombre, provincia, numHabitantes);
        }
    }

    public void cargarCiudades (){
        if (DataManager.getCiudades()){
            ciudades = DataManager.getListaCiudades();
            crearTabla();
        }
    }

    private void crearTabla() {
        String[][] data = new String[ciudades.size()][4];

        for (int i = 0; i < ciudades.size(); i++) {
            data[i][0] = String.valueOf(ciudades.get(i).getId());
            data[i][1] = ciudades.get(i).getNombre();
            data[i][2] = ciudades.get(i).getProvincia();
            data[i][3] = String.valueOf(ciudades.get(i).getNumHabitantes());
        }

        tabla.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "NOMBRE", "PROVINCIA", "NUMHABITANTES"}
        ));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setEnabled(true);
        tabla.setDefaultEditor(Object.class, null);
    }

    private boolean validaCampos(){
        return nombre.isEmpty() || provincia.isEmpty() || numHabitantes.isEmpty();
    }

    public static void main(String[] args) {
        if (DBManager.loadDriver()) {
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
        }
    }
}
