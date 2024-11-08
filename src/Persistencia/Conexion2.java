
package Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.Statement;


public class Conexion2 {
    Connection conexion;
    String url="jdbc:mysql://localhost:3306/login";
    String usuario="root";
    String clave="highlander";
    String Drive="com.mysql.cj.jdbc.Driver";
    
    
            
        


public Conexion2() {
        try{
            Class.forName(Drive);
            //Modulo de conexion
            conexion=DriverManager.getConnection(url,usuario,clave);
            System.out.println("Conexion Exitosa");
            
        }catch(ClassNotFoundException e){
            System.out.println("Error al cargar el drive de MySQL"+e.getMessage());
           }catch(SQLException e){
               System.out.println("Error al conectar la Base de Datos"+e.getMessage());
           }
    }


    public Connection getConexion(){
        return conexion;
    }

    
     public void CerrarConexion(){
        try{
            if(conexion !=null && !conexion.isClosed()){
                conexion.close();
                System.out.println("Se a Cerrado la conexion");
            }
        }catch(SQLException e){
            System.out.println("Error al cerrar la conexion"+e.getMessage());
        }
    }
     
    public String[] ejecutarConsulta(String consulta){
         String[] result=new String[14];
        try{
            Statement stmt=conexion.createStatement();
            ResultSet rs=stmt.executeQuery(consulta);
            
            while(rs.next()){
                //Aqui coloca el nombre de las columnas que tiene en MySQL
                
                
                result[0]="cc: "+rs.getInt("cc");
                result[1]="nombre: "+rs.getString("nombre");
                result[11]="Contraseña: "+rs.getString("loginpassword");
            }
        }catch(SQLException e){
            System.out.println("Error al ejecutar la consulta "+e.getMessage());
        }
             return result;
    }
    public String[] login(String cons){
        String[] result=new String[2];
        try {
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(cons);
            while(rs.next()){
                //Aqui coloca el nombre de las columnas que tiene en MySQL
                
                result[0]= String.valueOf( rs.getInt("cc"));
                result[1]="Contraseña: "+rs.getString("loginpassword");
            }
        } catch (SQLException e){
            System.out.println("Error en la consulta :"+e.getMessage());
        }
        return result;
    }

    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

