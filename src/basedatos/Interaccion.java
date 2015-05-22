package basedatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aitor
 */
public class Interaccion {

    //variables de conexion
    static Connection cn;
    static Statement st;
    static ResultSet rs;
    DefaultTableModel modelo = new DefaultTableModel();

    //metodo que muestra el contenido
    public DefaultTableModel lista() {
        try {
            cn = Conexion.Enlace(cn);
            Statement st = cn.createStatement();
            //consulta
            String query = "select * from alumno";
            rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            //numero columnas
            int CanColumns = rsmd.getColumnCount();
            //cargar columnas
            for (int i = 1; i <= CanColumns; i++) {
                modelo.addColumn(rsmd.getColumnLabel(i));
            }
            //crease el array
            while (rs.next()) {
                Object[] fila = new Object[CanColumns];
                for (int i = 0; i < CanColumns; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }

        } catch (Exception e) {
        }
        return modelo;
    }

    //metodo insertar datos
    public void AgregarDatos(String id, String nombre, String nota) {
            try {
                Statement st = cn.createStatement();
                String query = "INSERT INTO alumno(id,nombre,nota)values (" + id + ",'" + nombre + "'," + nota + ")";
                st.executeUpdate(query);
                st.close();
                cn.close();
                JOptionPane.showMessageDialog(null, "Agregado");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"ID en uso");
            }
        }
    //}

    //metodo eliminar datos
    public void ELiminarConsulta(String id) {
        try {
            Statement st = cn.createStatement();
            String query = "Delete from alumno where id=" + id + "";
            st.executeUpdate(query);
            st.close();
            cn.close();
            JOptionPane.showMessageDialog(null, "Borrado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //metodo modificar datos
    public void ModificarConsulta(String nombre, String nota, String id) {
        try {
            Statement st = cn.createStatement();
            String query = "UPDATE alumno SET nombre='" + nombre + "',nota=" + nota + " WHERE id=" + id + "";
            st.executeUpdate(query);
            st.close();
            cn.close();
            JOptionPane.showMessageDialog(null, "MODIFICADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
}
