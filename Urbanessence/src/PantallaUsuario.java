import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.invokeLater;

public class PantallaUsuario extends JFrame{
    int contadorNumTareasAntes = 0;
    static int contadorNumTareasDespuesInsert = 0;
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

    public PantallaUsuario(){
        if(DBManager.loadDriverSQLServer() && DBManager.openConnectionToDatabase()){
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

        btnEditarMisDatos.addActionListener(e -> {
            JDialog editarDatos = new EditarDatosUsuario(this, "Tú perfil");
            editarDatos.setVisible(true);
        });

        btnHistorialDePedidos.addActionListener(e -> {
            JDialog historialPedidos = new HistorialDePedidosDelUsuario(this, "Tú perfil");
            historialPedidos.setVisible(true);
        });

        crearTareaButton.addActionListener(e ->{
            JDialog crearTarea = new CrearTarea(this, "Crear tarea", 1);
            crearTarea.setVisible(true);
        });
    }

    private void cargarTareas() {
        if (DataManager.getTareas()){
            tareas = DataManager.getListaTareas();
            contadorNumTareasAntes = tareas.size();
            System.out.println(contadorNumTareasAntes);
//        solo para ver que se han cargado por consola
//            System.out.println(tareas);
        }
    }

    public static void actualizarTareas() {
        tareas.clear();
        if (DataManager.getTareas()){
            tareas = DataManager.getListaTareas();
            contadorNumTareasDespuesInsert = tareas.size();
            System.out.println(contadorNumTareasDespuesInsert);
//        solo para ver que se han cargado por consola
//            System.out.println(tareas);
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

    public static void main(String[] args) {
        invokeLater(PantallaUsuario::new);
    }
}
