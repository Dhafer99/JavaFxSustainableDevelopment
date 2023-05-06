/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidd;

import Donneur.Entities.Don;
import Donneur.Service.ServiceDon;
import Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Souid
 */
public class UserProfileController implements Initializable {

    @FXML
    private Label noml;
    @FXML
    private Label prenoml;
    @FXML
    private Label emaill;
    @FXML
    private Label scorel;
    @FXML
    private Label etoilel;
    @FXML
    private Label typel;
    @FXML
    private ImageView imageuser;
    @FXML
    private TableView<Don> tableDon;
    @FXML
    private TableColumn<Don, String> nomDonC;
   
    @FXML
    private TableColumn<Don, String> localisationC;
    @FXML
    private TableColumn<Don, String> giverNameColumn;
    @FXML
    private TableColumn<Don, String> Description;
    @FXML
    private TableColumn<Don, String> quantiteC;
    @FXML
    private Label donRecu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        User user = ProjetPiDD.user ;
                if (projetpidd.ProjetPiDD.user.getType().equals("Donneur"))
                {
                    tableDon.setVisible(false);
                    donRecu.setVisible(false);
                }
                else
                {
                    tableDon.setVisible(true);
                    donRecu.setVisible(true);
                }
        noml.setText(user.getNom());
        prenoml.setText(user.getPrenom());
        emaill.setText(user.getEmail());
        scorel.setText(Integer.toString(user.getScore()));
        etoilel.setText(Integer.toString(user.getNb_etoile()));
        typel.setText(user.getType());
        Image image = new Image(user.getImage());
        imageuser.setImage(image);
        try {
            showDon();
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
                }    

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        
        ProjetPiDD m = new ProjetPiDD();
        m.changeScene("login.fxml");
    }

    @FXML
    private void toUpdate(ActionEvent event) throws IOException {
        ProjetPiDD m = new ProjetPiDD();
        m.changeScene("editprofile.fxml");
    }

    @FXML
    private void ToDonInterface(ActionEvent event) throws IOException {
        
       ProjetPiDD m = new ProjetPiDD();
       m.changeScene("/don/Front.fxml");
    }

    private void toEvenementinterface(ActionEvent event) throws IOException {
        ProjetPiDD m = new ProjetPiDD();
        m.changeScene("/don/Front.fxml");
    }
    
    public void showDon() throws SQLException{
        if (projetpidd.ProjetPiDD.user.getType().equals("Receveur"))
        {
             ServiceDon sv = new ServiceDon();
             List<Don> dons;
            try {
                dons = sv.getDonationForReceiver(projetpidd.ProjetPiDD.user.getId());
           
        ObservableList<Don> list = FXCollections.observableArrayList(dons);
                System.out.println(list);
            // User giver = new User(rs.getString("giver_name"));
       // Don donation = new Don(rs.getString("name_d"), rs.getString("description"), rs.getString("localisation"), giver);
            nomDonC.setCellValueFactory(new PropertyValueFactory("NameD"));
            localisationC.setCellValueFactory(new PropertyValueFactory("localisation"));
            Description.setCellValueFactory(new PropertyValueFactory("description"));
             giverNameColumn.setCellValueFactory(new PropertyValueFactory("Givername"));
             quantiteC.setCellValueFactory(new PropertyValueFactory("quantite"));
            
              
              tableDon.setItems(list);
        } catch (SQLException ex) {
                Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }


        
        }
       
        
        
    }

    @FXML
    private void Chat(ActionEvent event) throws IOException {
        ProjetPiDD m = new ProjetPiDD();
        m.changeScene("/views/LoginView.fxml");
    }

    @FXML
    private void ToAssistance(ActionEvent event) throws IOException {
        ProjetPiDD m = new ProjetPiDD();
        m.changeScene("ChatBot.fxml");
    }
    
}
