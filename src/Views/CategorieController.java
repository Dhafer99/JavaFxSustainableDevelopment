/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Categorie_Rec;
import ServicesReclamations.CategorieRecService;
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
 * @author ghofrane
 */
public class CategorieController implements Initializable {
    
    
    @FXML
    private TableView<Categorie_Rec> tabcateg;
    @FXML
    private TableColumn<Categorie_Rec, String> colnom;
    @FXML
    private TextField tf_nom;
    private CategorieListdata Ls = new CategorieListdata();
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show();
    }    
    public void show(){
       tabcateg.setItems(Ls.getPersons());
      
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom")); 
    }
    
    @FXML
    private void Ajout(MouseEvent event) {
        String nom=tf_nom.getText();
 
    
    
   // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
    Categorie_Rec p=new Categorie_Rec(nom);
    CategorieRecService promotiondao=new CategorieRecService();
    
    
    JOptionPane.showMessageDialog(null, "ajouter une categorie   !");
            
                    
    promotiondao.insert(p);
     show();
    }

    public void delete(){
    CategorieRecService pdao =new CategorieRecService();
    pdao.delete(tabcateg.getSelectionModel().getSelectedItem().getId());
    System.out.println(tabcateg.getSelectionModel().getSelectedItem().getId());
    }
    @FXML
    private void Supp(MouseEvent event) {
        delete();
   tabcateg.getItems().removeAll(tabcateg.getSelectionModel().getSelectedItem());
   System.out.println(tabcateg);
    }

    
    @FXML
    private void handle(MouseEvent event) {
        Categorie_Rec rec =tabcateg.getSelectionModel().getSelectedItem();
        
     id=rec.getId();
     tf_nom.setText(""+rec.getNom());   
     show();
     
    }
    
    @FXML
    private void UpdateCat(MouseEvent event) {
        CategorieRecService se = new CategorieRecService();
             Categorie_Rec p = new Categorie_Rec();
            p.setId(id);
            p.setNom(tf_nom.getText());
            
            
            System.out.println(""+p);
           
          
            if (se.update(p))
            {
                JOptionPane.showMessageDialog(null, "Categorie failed !");
            }

            JOptionPane.showMessageDialog(null, "Categorie modifiée !");
            
            //tab_reclamation.refresh();
            show();

    }
    
}
