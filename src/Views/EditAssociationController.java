/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import static Controllers.AjoutAssociationController.ACCOUNT_SID;
import static Controllers.AjoutAssociationController.AUTH_TOKEN;
import Models.Association;
import ServiceAssociation.AssociationService;
import Utils.MyCnx;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class EditAssociationController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfnumero;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfcode_postal;
    @FXML
    private TextField tfville;
    @FXML
    private TableView<Association> tabAssociation;
    @FXML
    private TableColumn<Association, String> colnom;
    @FXML
    private TableColumn<Association, Integer> colnumero;
    @FXML
    private TableColumn<Association, String> colmail;
    @FXML
    private TableColumn<Association, String> coladresse;
    @FXML
    private TableColumn<Association, Integer> colcode_postal;
    @FXML
    private TableColumn<Association, String> colville;
private listdata Ls = new listdata();
    //private TextField searchField;
    
    public static final String ACCOUNT_SID = "AC7baee01e459dc347a9e9f0a9b8f744c5";
  public static final String AUTH_TOKEN = "9c4d3175f524029937ea448f92ae988e";
    @FXML
    private TextField search;
    
     private Parent fxml;
    @FXML
    private AnchorPane root;
    
    AssociationService ps = new AssociationService();
    /**
     * Initializes the controller class.
     */

@Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         affiche();
        List<Association> personnes = ps.displayAll();
        ObservableList<Association> olp = FXCollections.observableArrayList(personnes);
        //search//
        FilteredList<Association> filter = new FilteredList<>(olp, b->true);
        search.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(event -> {
            if(newValue.isEmpty() || newValue==null ) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(event.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;

            
            }else 
            return false;
        });
        });
        SortedList<Association> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tabAssociation.comparatorProperty());
        
        tabAssociation.setItems(sort);
      
    }    
 public void affiche(){
     
    
       tabAssociation.setItems(Ls.getPersons());
       
      //  colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colnumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colcode_postal.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
        colville.setCellValueFactory(new PropertyValueFactory<>("ville"));
       
   }
    
    @FXML
    private void handle(MouseEvent event) {
        
            
    Association asso=tabAssociation.getSelectionModel().getSelectedItem();
  
   // pourcentage.setText(""+promo.getPourcentage());
   // produit.setText(""+promo.getProduit().getId());
  //  categorie.setText(""+promo.getCategorie());
    //
     tfnom.setText(""+asso.getNom());   
     tfnumero.setText(""+asso.getNumero());   
     tfmail.setText(""+asso.getMail());   
     tfadresse.setText(""+asso.getAdresse());
     tfcode_postal.setText(""+asso.getCodePostal());
     tfville.setText(""+asso.getVille());
    }
 
    private void Update(MouseEvent event) {
       Association association = new Association(tfnom.getText(),Integer.parseInt(tfnumero.getText()),tfmail.getText(),tfadresse.getText(),Integer.parseInt(tfcode_postal.getText()),tfville.getText()); 
       AssociationService AssociationService = new AssociationService();
       AssociationService.update(association);
        System.out.println("updatee");
      //tabAssociation.refresh();
      // nom, numero, mail,  adresse,  CodePostal,  ville
       affiche();
       tabAssociation.refresh();
     affiche();
       /* try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/EditAssociation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }*/
       
       
       
}
     public void delete(){
    AssociationService pdao =new AssociationService();
    pdao.delete(tabAssociation.getSelectionModel().getSelectedItem().getId());
    System.out.println(tabAssociation.getSelectionModel().getSelectedItem().getId());
    affiche();
    }

    @FXML
    private void supprimer1(MouseEvent event) {
        
           delete();
   tabAssociation.getItems().removeAll(tabAssociation.getSelectionModel().getSelectedItem());
   System.out.println(tabAssociation); 
        affiche();
    }

    @FXML
    private void Update1(MouseEvent event) {
         Association association = new Association(tfnom.getText(),Integer.parseInt(tfnumero.getText()),tfmail.getText(),tfadresse.getText(),Integer.parseInt(tfcode_postal.getText()),tfville.getText()); 
       AssociationService AssociationService = new AssociationService();
       affiche();
       AssociationService.update(association);
       tabAssociation.refresh();
      
       
    }



    @FXML
    private void Ajout(MouseEvent event) {
        
         String nom=tfnom.getText();
    String numero=tfnumero.getText();
    String mail=tfmail.getText();
    String adresse=tfadresse.getText();
    String code_postal=tfcode_postal.getText();
    String ville=tfville.getText();
    
    
   // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
    Association p=new Association(nom,Integer.parseInt(numero),mail,adresse,Integer.parseInt(code_postal),ville);
    AssociationService promotiondao=new AssociationService();
    
    
    JOptionPane.showMessageDialog(null, "ajouter un event   !");
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21698773438"),
        new PhoneNumber("++12764098996"), 
        "une nouvelle association est ajouter  ").create();
                    
                    
    promotiondao.insert(p);
        
        
    }
      
        }


