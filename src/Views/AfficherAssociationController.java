/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Association;
import Models.Association;
import ServiceAssociation.AssociationService;
import Utils.MyCnx;
import Views.listdata;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class AfficherAssociationController implements Initializable {

    @FXML
    private TableView<Association> tabAssociation;
    @FXML
    private TableColumn<Association, Integer> colid;
    @FXML
    private TableColumn<Association, String> colnom;
    @FXML
    private TableColumn<Association, Integer> colnumero;
    @FXML
    private TableColumn<Association,String > colmail;
    @FXML
    private TableColumn<Association, String> coladresse;
    @FXML
    private TableColumn<Association, Integer> colcode_postal;
    @FXML
    private TableColumn<Association, String> colville;
    private listdata Ls = new listdata();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tabAssociation.setItems(Ls.getPersons());
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colnumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colcode_postal.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
        colville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        
         
    }    
  public void delete(){
    AssociationService pdao =new AssociationService();
    pdao.delete(tabAssociation.getSelectionModel().getSelectedItem().getId());
    System.out.println(tabAssociation.getSelectionModel().getSelectedItem().getId());
    }
  
  
    private void supprimer(MouseEvent event) {
      delete();
   tabAssociation.getItems().removeAll(tabAssociation.getSelectionModel().getSelectedItem());
   System.out.println(tabAssociation);  
        
        
    }

    @FXML
    private void supprimer1(MouseEvent event) {
        delete();
   tabAssociation.getItems().removeAll(tabAssociation.getSelectionModel().getSelectedItem());
   System.out.println(tabAssociation); 
        
    }

   
}
