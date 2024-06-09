import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.invokeLater;

public class PantallaUsuario extends JFrame {
    private int contadorNumTareasAntes = 0;
    private static int contadorNumTareasDespuesInsert = 0;
    private static List<Tarea> tareas = new ArrayList<>();
    private JPanel headerPanel;
    private JLabel imagenUrbanEssence;
    private JPanel divBotonesHeader;
    private JButton btnLogout;
    private JButton btnPerfil;
    private JPanel asideLeft;
    private JButton btnVistaGeneral;
    private JButton btnCalendarioDeTareas;
    private JButton btnHistorialDePedidos;
    private JPanel main;
    private JPanel panelPrincipalPantallaUsuario;
    private JButton btnEditarMisDatos;
    private JLabel AcabadoLabel;
    private JButton crearTareaButton;
    private JPanel tareasPendientes;
    private JPanel tareasAcabadas;
    private JPanel tareasEnCurso;
    private JPanel panelLogo;
    private JPanel fotoYNombrePanel;
    private JLabel fotoUsuario;
    private JLabel NombreUsuario;
    private JLabel FechaIncorporacion;
    private JPanel headerPendiente;
    private JLabel tareaPendienteLabel;
    private JPanel tareasPendientesPanel;
    private JLabel enCursoLabel;
    private JPanel headerEnCurso;
    private JPanel tareasDnDPendientes;
    private JPanel tareasDnDEnCurso;
    private JPanel tareasDnDAcabadas;

    public PantallaUsuario() {
        // Inicialización de componentes
        panelPrincipalPantallaUsuario = new JPanel(new BorderLayout());
        tareasDnDPendientes = new JPanel();
        tareasDnDEnCurso = new JPanel();
        tareasDnDAcabadas = new JPanel();

        // Configuración inicial
        if (DBManager.loadDriverSQLServer() && DBManager.openConnectionToDatabase()) {
            JOptionPane.showMessageDialog(this, "JDBC cargado correctamente y conexion con sqlserver correcta");
        } else {
            JOptionPane.showMessageDialog(this, "error al conectar con la bd");
        }

        JFrame perfil = new JFrame("Tu perfil");
        perfil.setContentPane(panelPrincipalPantallaUsuario);
        perfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        perfil.setVisible(true);
        perfil.pack();
        perfil.setLocationRelativeTo(null);
        setLogo();
        configurarBotones();
        cargarTareas();

        // Configuración de paneles de tareas
        tareasDnDPendientes.setLayout(new BoxLayout(tareasDnDPendientes, BoxLayout.Y_AXIS));
        tareasDnDEnCurso.setLayout(new BoxLayout(tareasDnDEnCurso, BoxLayout.Y_AXIS));
        tareasDnDAcabadas.setLayout(new BoxLayout(tareasDnDAcabadas, BoxLayout.Y_AXIS));

        enableDragAndDrop(tareasDnDPendientes);
        enableDragAndDrop(tareasDnDEnCurso);
        enableDragAndDrop(tareasDnDAcabadas);

        // Crear una tarea de ejemplo y añadirla al panel de tareas pendientes
        JPanel tareaEjemplo = crearPanelTarea("Título de la Tarea", "Descripción de la tarea", "01/06/2024", "05/06/2024");
        tareasDnDPendientes.add(tareaEjemplo);

        // Añadir paneles de tareas al panel principal
        panelPrincipalPantallaUsuario.add(tareasDnDPendientes, BorderLayout.WEST);
        panelPrincipalPantallaUsuario.add(tareasDnDEnCurso, BorderLayout.CENTER);
        panelPrincipalPantallaUsuario.add(tareasDnDAcabadas, BorderLayout.EAST);

        btnEditarMisDatos.addActionListener(e -> {
            JDialog editarDatos = new EditarDatosUsuario(this, "Tú perfil");
            editarDatos.setVisible(true);
        });

        btnHistorialDePedidos.addActionListener(e -> {
            JDialog historialPedidos = new HistorialDePedidosDelUsuario(this, "Tú perfil");
            historialPedidos.setVisible(true);
        });

        crearTareaButton.addActionListener(e -> {
            JDialog crearTarea = new CrearTarea(this, "Crear tarea", 1);
            crearTarea.setVisible(true);
        });
    }

    private void cargarTareas() {
        if (DataManager.getTareas()) {
            tareas = DataManager.getListaTareas();
            contadorNumTareasAntes = tareas.size();
            System.out.println(contadorNumTareasAntes);
        }
    }

    public static void actualizarTareas() {
        tareas.clear();
        if (DataManager.getTareas()) {
            tareas = DataManager.getListaTareas();
            contadorNumTareasDespuesInsert = tareas.size();
            System.out.println(contadorNumTareasDespuesInsert);
        }
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

        btnHistorialDePedidos.setUI(new BasicButtonUI());
        btnHistorialDePedidos.setBorder(null);

        btnEditarMisDatos.setUI(new BasicButtonUI());
        btnEditarMisDatos.setBorder(null);

        crearTareaButton.setUI(new BasicButtonUI());
        crearTareaButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        btnVistaGeneral.setUI(new BasicButtonUI());
        btnVistaGeneral.setBorder(null);
    }

    private void enableDragAndDrop(JPanel panel) {
        panel.setTransferHandler(new PanelTransferHandler());
        panel.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    Transferable transferable = evt.getTransferable();
                    Component component = (Component) transferable.getTransferData(PanelTransferHandler.PANEL_FLAVOR);
                    if (component != null) {
                        JPanel parent = (JPanel) component.getParent();
                        parent.remove(component);
                        panel.add(component);
                        panel.revalidate();
                        panel.repaint();
                        evt.dropComplete(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    evt.dropComplete(false);
                }
            }
        });
    }

    private JPanel crearPanelTarea(String titulo, String descripcion, String fechaInicio, String fechaFin) {
        JPanel panelTarea = new JPanel();
        panelTarea.setLayout(new BoxLayout(panelTarea, BoxLayout.Y_AXIS));
        panelTarea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelTarea.add(new JLabel("Título: " + titulo));
        panelTarea.add(new JLabel("Descripción: " + descripcion));
        panelTarea.add(new JLabel("Fecha Inicio: " + fechaInicio));
        panelTarea.add(new JLabel("Fecha Fin: " + fechaFin));

        panelTarea.setTransferHandler(new PanelTransferHandler());
        panelTarea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JComponent jc = (JComponent) evt.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, evt, TransferHandler.MOVE);
            }
        });

        return panelTarea;
    }

    public static void main(String[] args) {
        invokeLater(PantallaUsuario::new);
    }

    private static class PanelTransferHandler extends TransferHandler {
        public static final DataFlavor PANEL_FLAVOR = new DataFlavor(Component.class, "A Component");

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new TransferablePanel(c);
        }

        @Override
        public int getSourceActions(JComponent c) {
            return MOVE;
        }

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(PANEL_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            try {
                Component component = (Component) support.getTransferable().getTransferData(PANEL_FLAVOR);
                if (component != null) {
                    JComponent target = (JComponent) support.getComponent();
                    target.add(component);
                    target.revalidate();
                    target.repaint();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private static class TransferablePanel implements Transferable {
        private Component component;

        public TransferablePanel(Component component) {
            this.component = component;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{PanelTransferHandler.PANEL_FLAVOR};
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(PanelTransferHandler.PANEL_FLAVOR);
        }

        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return component;
        }
    }
}
