package basedatos;

/**
 *
 * @author Aitor
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    //creamos la conexion
    static Conexion cn=null;
    //Crear clase conexion
    public static Connection Enlace(Connection cn) throws SQLException{
        //ruta de la base de datos
        String ruta="C:\\DBalumno.db";
        try{
            Class.forName("org.sqlite.JDBC");
            cn = DriverManager.getConnection("jdbc:sqlite:"+ruta);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return cn;
    }
}
