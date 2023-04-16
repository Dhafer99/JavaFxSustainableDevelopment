/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.categorieA;
import Services.CategorieAssoService;
import java.net.URL;
import java.util.ResourceBundle;
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
public class AssociationCategorieController implements Initializable {

    @FXML
    private TableView<categorieA> tabcategorie;
    @FXML
    private TableColumn<categorieA, String> colnom;
    @FXML
    private TextField tfnom;
private CategorieAssoListdata Ls = new CategorieAssoListdata();
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

    @FXML
    private void handle(MouseEvent event) {
            categorieA rec =tabcategorie.getSelectionModel().getSelectedItem();
       
     //id=rec.getId();
     tfnom.setText(""+rec.getNom());   
     show();
     
    }
public void delete(){
    CategorieAssoService pdao =new CategorieAssoService();
    pdao.delete(tabcategorie.getSelectionModel().getSelectedItem().getId());
    System.out.println(tabcategorie.getSelectionModel().getSelectedItem().getId());
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
        CategorieAssoService se = new CategorieAssoService();
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
        categorieA p=new categorieA(nom);
    CategorieAssoService promotiondao=new CategorieAssoService();
    
 
    JOptionPane.showMessageDialog(null, "ajouter un event   !");
            
                    
    promotiondao.insert(p);
     show();
    }
    
}
