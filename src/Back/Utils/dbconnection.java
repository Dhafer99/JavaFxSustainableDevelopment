/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Utils;


import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Souid
 */
public class dbconnection {
    
     static dbconnection instance;
    public Connection cnx;
    
    public static dbconnection getInstance() {
        if (instance == null) {
            instance = new dbconnection();
        }

        return instance;
    }
    
    private dbconnection() 
    {
        try
        {
            String url = "jdbc:mysql://localhost:3306/testprojet";
            String login = "root";
            String pwd = "";
            cnx = DriverManager.getConnection(url, login, pwd);

            System.out.println("Connexion établie");
        } 
        catch (SQLException ex) 
        {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getConnection()
    {
        return cnx;
    }
}
