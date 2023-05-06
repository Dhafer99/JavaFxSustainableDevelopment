/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Models.Categorie;
import Back.Services.ServiceCategorieAnnonce;
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
public class CategorieAnnonceController implements Initializable {

    @FXML
    private TableView<Categorie> tabcateg;
    @FXML
    private TableColumn<Categorie, String> colnom;
    @FXML
    private TextField tf_nom;
    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private CategorieAnnonceListData Ls = new CategorieAnnonceListData();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show();
    }    
    public void show() {
        tabcateg.setItems(Ls.getPersons());

        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }

    @FXML
    private void Modifier(MouseEvent event) {
        
        ServiceCategorieAnnonce se = new ServiceCategorieAnnonce();
        Categorie p = new Categorie();
        p.setId(id);
        p.setNom(tf_nom.getText());

        System.out.println("" + p);

        if (se.update(p)) {
            JOptionPane.showMessageDialog(null, "Failed !");
        }

        JOptionPane.showMessageDialog(null, "Categorie modifiée !");

        show();
        
        
        
        
    }
public void delete(){
    ServiceCategorieAnnonce pdao =new ServiceCategorieAnnonce();
    pdao.delete(tabcateg.getSelectionModel().getSelectedItem().getId());
    System.out.println(tabcateg.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    private void Supprimer(MouseEvent event) {
        delete();
   tabcateg.getItems().removeAll(tabcateg.getSelectionModel().getSelectedItem());
   System.out.println(tabcateg);
    }

    @FXML
    private void ajouter(MouseEvent event) {
        
        String nom = tf_nom.getText();

        // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
        Categorie p = new Categorie(nom);
        ServiceCategorieAnnonce promotiondao = new ServiceCategorieAnnonce();

        JOptionPane.showMessageDialog(null, "Ajouter une categorie !");

        promotiondao.insert(p);
        show();
        
        
    }

    @FXML
    private void handle(MouseEvent event) {
        
        Categorie ann =tabcateg.getSelectionModel().getSelectedItem();
        
     id=ann.getId();
     tf_nom.setText(""+ann.getNom());   
     show();
    }


}
