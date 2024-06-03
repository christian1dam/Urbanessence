import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class DataManager {
    private static ArrayList<Cliente> listClientes = new ArrayList<>();
    private static ArrayList<Tienda> listTiendas = new ArrayList<>();

    public static boolean getClientes(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getClientes();
                while (rs.next()){
                    listClientes.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getByte(3), rs.getString(4), rs.getInt(5), rs.getDate(6).toLocalDate()));
                }
                rs.close();
                DBManager.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList getListaClientes (){
        return listClientes;
    }

    public static boolean getTiendas(){
        if (DBManager.connect()){
            try {
                ResultSet rs = DBManager.getTiendas();
                while (rs.next()){
                    listTiendas.add(new Tienda(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTime(5).toLocalTime(), rs.getTime(6).toLocalTime(), rs.getInt(7), rs.getInt(8)));
                }
                rs.close();
                DBManager.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList getListaTiendas (){
        return listTiendas;
    }

    public static int borrarCliente(int id){
        int rs=0;
        if (DBManager.connect()){
            try {
                rs = DBManager.borrarCliente(id);

                if (rs == 1){
                    for (int i = 0; i < listClientes.size(); i++) {
                        if (listClientes.get(i).getId() == id){
                            listClientes.remove(i);
                        }
                    }
                }
            }catch (SQLException e){
                DBManager.close();
                e.printStackTrace();
                return 0;
            }
        }
        return rs;
    }
}
