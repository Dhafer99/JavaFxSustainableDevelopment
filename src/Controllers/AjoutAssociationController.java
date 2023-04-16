/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Association;
import Models.categorieA;
import Services.AssociationService;
import Services.CategorieAssoService;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class AjoutAssociationController implements Initializable {

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
    private ChoiceBox<String> combo;
private File file; 
    private String lien="";
    public static final String ACCOUNT_SID = "AC7baee01e459dc347a9e9f0a9b8f744c5";
  public static final String AUTH_TOKEN = "89da9ac629f6cf7374346a905c91de41";
   private CategorieAssoListdata CategorieAssoListdata = new CategorieAssoListdata();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         tfnumero.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfnumero.setText(newValue.replaceAll("[^\\d]", ""));            
            }
        });
        
         tfcode_postal.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfcode_postal.setText(newValue.replaceAll("[^\\d]", ""));            
            }
        });
        
        List<categorieA> categoriePromotions = CategorieAssoListdata.getPersons();
ObservableList<String> observableList = FXCollections.observableArrayList();
for (categorieA categoriePromotion : categoriePromotions) {
    observableList.add(categoriePromotion.toString()); 
}
combo.setItems(observableList);
// end
         
         
        
      
    }    
private boolean ValidateEmptyForm(TextField nom, TextField mail){
         if (nom.getText().equals("")  || mail.getText().equals("") )
         {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText("Veuillez remplir tous les champs");
             alert.showAndWait();
             
             return false;  
        } else {
             return true;  
         }
     }
    @FXML
    private void Ajout(MouseEvent event) {
         if(ValidateEmptyForm(tfnom,tfmail)){
        
        
categorieA asso; // instance
           String nom_categ = (String) combo.getValue(); // te5ou ell valeur mill combo box 
        CategorieAssoService cdao=new CategorieAssoService(); // instance service categorie 
        asso= cdao.getOneByName(nom_categ); // 
        
 
    String nom=tfnom.getText();
    String numero=tfnumero.getText();
    String mail=tfmail.getText();
    String adresse=tfadresse.getText();
    String code_postal=tfcode_postal.getText();
    String ville=tfville.getText();
   
    
    
    
   // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
    Association p=new Association(nom,Integer.parseInt(numero),mail,adresse,Integer.parseInt(code_postal),ville,asso);
    AssociationService promotiondao=new AssociationService();
    
    
    JOptionPane.showMessageDialog(null, "ajouter un event   !");
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21698773438"),
        new PhoneNumber("+12764098996"), 
        "une nouvelle association est ajouter  ").create();
                    
                    
    promotiondao.insert(p);        
    }
    }
    
}
