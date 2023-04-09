/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import static Controllers.AjoutAssociationController.ACCOUNT_SID;
import static Controllers.AjoutAssociationController.AUTH_TOKEN;
import Models.Association;
import Models.categorieA;
import ServiceAssociation.AssociationService;
import ServiceAssociation.categorieService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class CategorieController implements Initializable {

    @FXML
    private TableView<categorieA> tabcategorie;
    @FXML
    private TextField tfnom;
private categeListdata Ls = new categeListdata();
    @FXML
    private TableColumn<?, ?> colnom;
    /**
     * Initializes the controller class.
     */
    
    public void show(){
       tabcategorie.setItems(Ls.getPersons());
      
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom")); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
show();
    }    

    
    @FXML
    private void ajout(MouseEvent event) {
        
    
    String nom=tfnom.getText();
 
    
    
   // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
    categorieA p=new categorieA(nom);
    categorieService promotiondao=new categorieService();
    
    
    JOptionPane.showMessageDialog(null, "ajouter un event   !");
            
                    
    promotiondao.insert(p);
     show();
        
        
        
    }
    
}
