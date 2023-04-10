/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class DataBase {
           private String url="jdbc:mysql://localhost:3306/gestion des annonces";
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
      
}
