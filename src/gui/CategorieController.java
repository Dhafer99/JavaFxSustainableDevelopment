/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CategorieController implements Initializable {

    @FXML
    private TableView<Categorie> tabcateg;
    @FXML
    private TextField tf_nom;
    @FXML
    private TableColumn<Categorie, String> colnom;

    private CategorieListData Ls = new CategorieListData();

    private int id;
    @FXML
    private Button annonceList;

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

    public void show() {
        tabcateg.setItems(Ls.getPersons());

        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }

    @FXML
    private void Ajout(MouseEvent event) {

        String nom = tf_nom.getText();

        // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
        Categorie p = new Categorie(nom);
        CategorieService promotiondao = new CategorieService();

        JOptionPane.showMessageDialog(null, "Ajouter une categorie !");

        promotiondao.insert(p);
        show();

    }

    @FXML
    private void UpdateCat(MouseEvent event) {

        CategorieService se = new CategorieService();
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

    @FXML
    private void handle(MouseEvent event) {
        
        Categorie ann =tabcateg.getSelectionModel().getSelectedItem();
        
     id=ann.getId();
     tf_nom.setText(""+ann.getNom());   
     show();
    }

    @FXML
    private void Supp(MouseEvent event) {
        
         delete();
   tabcateg.getItems().removeAll(tabcateg.getSelectionModel().getSelectedItem());
   System.out.println(tabcateg);
    }
    
    
    public void delete(){
    CategorieService pdao =new CategorieService();
    pdao.delete(tabcateg.getSelectionModel().getSelectedItem().getId());
    System.out.println(tabcateg.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    private void goToList(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAnnonce.fxml"));
        
        Parent root = loader.load();
        AfficherAnnonceController c = loader.getController();
        annonceList.getScene().setRoot(root);
    }

}
    
    
    

