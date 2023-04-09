/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 21629
 */
public class db_connect {
      private static db_connect instance ;
    final String URL ="jdbc:mysql://127.0.0.1:3306/gestion don";
    final String USERNAME ="root";
    final String PWD ="";
   private  Connection cnx;
    
    private db_connect(){
        try {
             cnx =DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("connected ......");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public  Connection getCnx (){
        return this.cnx;
    }
    public static db_connect getInstance (){
        if (instance == null )
            instance = new db_connect();
        
        return instance;
    } 
}
