/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.gui;

import Annonces.MyBD.DataBase;
import Annonces.entities.Categorie;
import Annonces.entities.user;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Annonces.services.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Annonces.services.CategorieService;


/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutUserController {
    @FXML
    private TextField usernameTextField;
    
    private UserService userDao = new UserService(); 
      @FXML
    private AnchorPane anchorPane;
    
    private List<CheckBox> categoryCheckboxes = new ArrayList<>();
    @FXML
    private Button front;
    public void initialize() {
        List<Categorie> categories = getCategories();
    
    // ajouter des cases à cocher pour chaque catégorie
    int y = 75;
    for (Categorie c : categories) {
        CheckBox checkBox = new CheckBox(c.getNom());
        checkBox.setLayoutX(97);
        checkBox.setLayoutY(y);
        categoryCheckboxes.add(checkBox);
        y += 30;
        anchorPane.getChildren().add(checkBox);
    }
        
    }
    
    @FXML
public void addUser() throws SQLException {
    String username = usernameTextField.getText();

    // récupérer les catégories sélectionnées sous forme d'une liste
    List<String> selectedCategories = new ArrayList<>();
   
    for (CheckBox checkbox : categoryCheckboxes) {
        if (checkbox.isSelected()) {
            selectedCategories.add(checkbox.getText());
        }
    }

    // créer un objet User avec les informations saisies
    user user = new user(username,  selectedCategories);

    // ajouter l'utilisateur à la base de données
    userDao.adduser(user);

    // afficher un message de succès
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("User Added");
    alert.setHeaderText(null);
    alert.setContentText("User has been added successfully.");
    alert.showAndWait();

    // vider les champs
    usernameTextField.setText("");
    for (CheckBox checkbox : categoryCheckboxes) {
        checkbox.setSelected(false);
    }
}
     @FXML
    private void gotoFront(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        
        Parent root = loader.load();
        Controller c = loader.getController();
        front.getScene().setRoot(root);
    }
    
    public List<Categorie> getCategories() {
    List<Categorie> categories = new ArrayList<>();
         Statement st;
          ResultSet rs;
        DataBase cs=DataBase.getInstance();
       
        try {
            String query = "SELECT nom FROM categorie";
            st=cs.getConnection().createStatement();
             rs = st.executeQuery(query);
             
   
        while (rs.next()) {
            String categoryName = rs.getString("nom");
            Categorie category = new Categorie(categoryName);
            categories.add(category);
        }
        }
    catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
    return categories;
}

      
}

    
    