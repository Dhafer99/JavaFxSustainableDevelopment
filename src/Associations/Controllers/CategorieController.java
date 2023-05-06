/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Associations.Controllers;

import Associations.Models.categorieA;
import Associations.Services.categorieService;
import Associations.Services.serviceCategorieAssociation;
import Associations.Views.categeListdata;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private TableColumn<categorieA, String> colnom;
    @FXML
    private TextField tfnom;
private categeListdata Ls = new categeListdata();
    /**
     * Initializes the controller class.
     */
 public void show(){
       tabcategorie.setItems(Ls.getPersons());
      
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom")); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show();
    }    
   public void delete(){
    categorieService pdao =new categorieService();
    pdao.delete(tabcategorie.getSelectionModel().getSelectedItem().getId());
    System.out.println(tabcategorie.getSelectionModel().getSelectedItem().getId());
    }
    @FXML
    private void handle(MouseEvent event) {
    }

    @FXML
    private void supp(MouseEvent event) {
           delete();
   tabcategorie.getItems().removeAll(tabcategorie.getSelectionModel().getSelectedItem());
   System.out.println(tabcategorie);
    Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
    alert3.setTitle("Erreur de saisie");
    alert3.setHeaderText(null);
   alert3.setContentText("are you suree");
    alert3.showAndWait();
    }

    @FXML
    private void modif(MouseEvent event) {
         categorieService se = new categorieService();
          categorieA p = new categorieA();
              p=tabcategorie.getSelectionModel().getSelectedItem();
           // p.setId(id);
            p.setNom(tfnom.getText()); 
           // System.out.println(""+p);
          
            if (se.update(p))
            {
                JOptionPane.showMessageDialog(null, " failed !");
            }

            JOptionPane.showMessageDialog(null, " modifié !");
            
            tabcategorie.refresh();
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

    @FXML
    private void trie(MouseEvent event) {
         serviceCategorieAssociation pd=new serviceCategorieAssociation();
         ObservableList<categorieA> nameliste = (ObservableList<categorieA>) pd.ordredbynom()  ;
         
     tabcategorie.setItems(nameliste);
     // show();
    }
    
}
