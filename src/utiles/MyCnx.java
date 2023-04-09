/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ghofrane
 */
public class MyCnx {
    private String url="jdbc:mysql://127.0.0.1:3306/gestion des reclamations";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static MyCnx instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private MyCnx() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            Logger.getLogger(MyCnx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static MyCnx getInstance(){
       
       if(instance==null)
           instance=new MyCnx();
       return instance;
   }
    
}
