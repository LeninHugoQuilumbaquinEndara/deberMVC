package modelo;


import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransaccionesBD1 extends Conexion {
    
    PreparedStatement ps;
    ResultSet rs;
    Estudiante estudiante;
Statement st;
boolean adelante, atras;
    public boolean insertar(Estudiante estudiante) {
        try {
            Connection conexion = getConnection();
        ps = conexion.prepareStatement("insert into estudiante (codigoE,nombreE,nota,sexo,materia) values(?,?,?,?,?)");
        ps.setInt(1, estudiante.getCodigo());
        ps.setString(2, estudiante.getNombre());
        ps.setDouble(3, estudiante.getNota());
        ps.setString(4, estudiante.getGenero());
        ps.setString(5, estudiante.getMateria());
        
        
        int resultado = ps.executeUpdate();
        
        
        if(resultado>0){
            return true;
            
        }else {
            return false;
        }
        
        }catch (Exception ex) {
            System.out.println("Error "+ex);
        }
        return true;

    }

    public boolean modificar(Estudiante estudiante){
        Connection conexion = getConnection();
        String sqlite = "UPDATE estudiante SET nombreE=?, nota=?, sexo=?, materia=? WHERE codigoE=?";
        try{
        ps = conexion.prepareStatement(sqlite,java.sql.ResultSet.TYPE_SCROLL_SENSITIVE,java.sql.ResultSet.CONCUR_UPDATABLE);
        
        ps.setString(1, estudiante.getNombre());
        ps.setDouble(2, estudiante.getNota());
        ps.setString(3, estudiante.getGenero());
        ps.setString(4, estudiante.getMateria());
        ps.setInt(5, estudiante.getCodigo());
        ps.execute();
        return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransaccionesBD1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
    }

    public boolean eliminar(Estudiante estudiante) {
        Connection conexion = getConnection();
        String sqlite = "DELETE FROM estudiante WHERE codigoE=?";
        try{
         ps = conexion.prepareStatement(sqlite,java.sql.ResultSet.TYPE_SCROLL_SENSITIVE,java.sql.ResultSet.CONCUR_UPDATABLE);
         ps.setInt(1, estudiante.getCodigo());
         ps.execute();
         return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(TransaccionesBD1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        public boolean buscar(Estudiante estudiante) {
Connection conexion = getConnection();
        String sqlite = "SELECT * FROM estudiante WHERE codigoE=?";
         try{
             ps =conexion.prepareStatement(sqlite,java.sql.ResultSet.TYPE_SCROLL_SENSITIVE,java.sql.ResultSet.CONCUR_UPDATABLE);
             ps.setInt(1, estudiante.getCodigo());
             rs =ps.executeQuery();
             while(rs.next()){
                 estudiante.setNombre(rs.getString("nombreE"));
                 estudiante.setNota(rs.getDouble("nota"));
                 estudiante.setGenero(rs.getString("sexo"));
                 estudiante.setMateria(rs.getString("materia"));
             }
         return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally {
    try {
        conexion.close();
    } catch (SQLException ex) {
        Logger.getLogger(TransaccionesBD1.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
    }
   
public boolean llenardatos(Estudiante estudiante){
    try{
        estudiante.setCodigo(rs.getInt("codigoE"));
        estudiante.setNombre(rs.getString("nombreE"));
        estudiante.setNota(rs.getDouble("nota"));
        estudiante.setGenero(rs.getString("sexo"));
        estudiante.setMateria(rs.getString("materia"));
    }catch(SQLException ex){
        Logger.getLogger(TransaccionesBD1.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
}
    
    public boolean siguiente(Estudiante estudiante) { 
      String sqlite = "SELECT * FROM estudiante";   
      try{
          atras = false;
          if(adelante == false){
          Connection conexion = getConnection();
          ps=conexion.prepareStatement(sqlite);
          rs=ps.executeQuery(); 
          adelante = true;
          }if(rs.isLast()==false){
              rs.next();
              llenardatos(estudiante); 
          } 
      }catch(Exception ex){
          System.err.println("Error "+ex.getMessage());
      }
      return true;
    }

    public boolean anterior (Estudiante estudiante) {
        String sqlite = "SELECT * FROM estudiante";
        String sqlite2 = "SELECT * FROM estudiante order by codigoE desc";
        try{
            adelante = false;
            if(atras == false){
            Connection conexion = getConnection();
ps=conexion.prepareStatement(sqlite,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = ps.executeQuery(sqlite2);  
          atras=true;
            }
            if(rs.isLast()==false){
               rs.next();
            llenardatos(estudiante);  
            }
           
        }catch(Exception ex){
          System.err.println("Error" + ex);
      }
return true;
    }

}
