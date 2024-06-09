import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.time.LocalDate;
import java.util.Date;
import java.util.EnumMap;

public class CrearTarea extends JDialog {
    private JPanel panelPrincipalCrearTarea;
    private JPanel headerPanel;
    private JPanel panelLogo;
    private JLabel imagenUrbanEssence;
    private JPanel main;
    private JPanel fechaInicioTareaTag;
    private JLabel tituloTareaTag;
    private JLabel descripcionTareaTag;
    private JLabel nombreUsuarioLabel;
    private JTextField tituloText;
    private JLabel fechaFinTareaTag;
    private JTextField fechaInicoTareaTXT;
    private JTextField fechaFinTareaTXT;
    private JLabel crearEmpleadoLABEL;
    private JButton cancelarBtn;
    private JButton crearBtn;
    private JTextArea descripcionTareaText;

    public CrearTarea(JFrame parent, String title, int empleadoID) {
        super(parent, title);
        setContentPane(panelPrincipalCrearTarea);
        pack();
        setLocationRelativeTo(null);

        crearBtn.addActionListener(e ->{
            Tarea tarea = recogerTarea(empleadoID);
            if(DataManager.crearTarea(tarea)){
                JOptionPane.showMessageDialog(this, "La tarea se ha creado correctamente");
            } else JOptionPane.showMessageDialog(this, "Ha habido un error al crear la tarea");
        });

        configurarBotones();
    }

    private Tarea recogerTarea(int empleadoID) {
        return new Tarea(tituloText.getText(), descripcionTareaText.getText(), LocalDate.parse(fechaInicoTareaTXT.getText()), LocalDate.parse(fechaFinTareaTXT.getText()), empleadoID);
    }

    private void configurarBotones() {
        crearBtn.setUI(new BasicButtonUI());
        crearBtn.setBorder(null);

        cancelarBtn.setUI(new BasicButtonUI());
        cancelarBtn.setBorder(null);
    }
}
