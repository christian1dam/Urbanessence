public class Ciudad {
    private int id;
    private String nombre;
    private String provincia;
    private int numHabitantes;

    public Ciudad(int id, String nombre, String provincia, int numHabitantes) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
        this.numHabitantes = numHabitantes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getNumHabitantes() {
        return numHabitantes;
    }

    public void setNumHabitantes(int numHabitantes) {
        this.numHabitantes = numHabitantes;
    }
}
