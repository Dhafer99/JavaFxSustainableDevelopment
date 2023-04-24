

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Asus
 */


import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import MyBD.DataBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService {

     private static UserService instance;
     Connection cn = DataBase.getInstance().getConnection();
    DataBase cnx = DataBase.getInstance();
    public UserService(){
        
    }
    
   public static UserService getInstance(){
        if(instance == null)
            instance = new UserService();
        return instance;
    }
    
     
    public void adduser(user user) throws SQLException
    {
                  
           String query = "INSERT INTO user (username,  categories) VALUES (?, ?)";
            PreparedStatement statement = cn.prepareStatement(query);
             statement.setString(1, user.getUsername());
             statement.setString(2, String.join(",", user.getCategories()));// transformer la liste des categories sous formle d'une chaine pour l'inserer dans la baase
              statement.executeUpdate();

    }
      
    
    public List<String> getUserInterests(String userId) throws SQLException {
    
    List<String> interests = new ArrayList<>();
            DataBase cs=DataBase.getInstance();
  Statement st;
          
    String query = "SELECT categories FROM user WHERE username = ?";
    PreparedStatement stmt = cs.prepareStatement(query);
    
    stmt.setString(1, userId);
    ResultSet rs = stmt.executeQuery();
    
    while (rs.next()) {
         String categoriesString = rs.getString("categories");
        String[] categoriesArray = categoriesString.split(",");
        interests = Arrays.asList(categoriesArray);
    }
    
   
    
    return interests;

    
}

    
}