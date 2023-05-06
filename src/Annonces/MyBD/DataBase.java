/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.MyBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class DataBase {
           private String url="jdbc:mysql://localhost:3306/testprojet";
    private String username="root";
      private String password="";
      private Connection connection;
      static DataBase instance;
      private DataBase(){
        try {
            connection=DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
      }
      public static DataBase getInstance(){
      if(instance==null)
      {instance=new DataBase();}
      return instance;
      }

    public Connection getConnection() {
        return connection;
    }
     
    
    public PreparedStatement prepareStatement(String sql, Object... params) throws SQLException {
    PreparedStatement pstmt = connection.prepareStatement(sql);
    
    for (int i = 0; i < params.length; i++) {
        Object param = params[i];
        
        if (param instanceof String) {
            pstmt.setString(i + 1, (String) param);
        } else if (param instanceof Integer) {
            pstmt.setInt(i + 1, (Integer) param);
        } else if (param instanceof Double) {
            pstmt.setDouble(i + 1, (Double) param);
        } else if (param instanceof Float) {
            pstmt.setFloat(i + 1, (Float) param);
        } else if (param instanceof Long) {
            pstmt.setLong(i + 1, (Long) param);
        } else if (param instanceof Boolean) {
            pstmt.setBoolean(i + 1, (Boolean) param);
        } else {
            pstmt.setObject(i + 1, param);
        }
    }
    
    return pstmt;
}

}
