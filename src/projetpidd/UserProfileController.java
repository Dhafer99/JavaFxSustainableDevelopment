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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Souid
 */
public class UserProfileController implements Initializable {

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
    private Label donRecu;
    @FXML
    private TextField n1;
    @FXML
    private TextField n2;
    @FXML
    private TextField n3;
    @FXML
    private TextField n4;
    @FXML
    private TextField n5;
    @FXML
    private TextField n6;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
imageuser.setFitWidth(200); // set the width of the image view
imageuser.setFitHeight(200); // set the height of the image view

// create a circle clip with a radius equal to half the width or height of the image view, whichever is smaller
Circle clip = new Circle();
clip.setCenterX(imageuser.getFitWidth() / 2);
clip.setCenterY(imageuser.getFitHeight() / 2);
clip.setRadius(Math.min(imageuser.getFitWidth(), imageuser.getFitHeight()) / 2);

imageuser.setClip(clip); // apply the clip to the image view

        User user = ProjetPiDD.user ;
                if (projetpidd.ProjetPiDD.user.getType().equals("Donneur"))
                {
                    tableDon.setVisible(false);
                 //donRecu.setVisible(false);
                }
                else
                {
                    tableDon.setVisible(true);
                    //donRecu.setVisible(true);
                }
                n1.setText(user.getNom());
                n2.setText(user.getPrenom());
                n3.setText(user.getEmail());
                n4.setText(Integer.toString(user.getScore()));
                n5.setText(Integer.toString(user.getNb_etoile()));
                n6.setText(user.getType());
       /* noml.setText(user.getNom());
        prenoml.setText(user.getPrenom());
        emaill.setText(user.getEmail());
        scorel.setText(Integer.toString(user.getScore()));
        etoilel.setText(Integer.toString(user.getNb_etoile()));
        typel.setText(user.getType());*/
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
