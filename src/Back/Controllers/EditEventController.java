/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Models.CategorieEvent;
import Back.Models.Evenement;
import Back.Services.EvenementService;
import Back.Utils.MyCnx;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class EditEventController implements Initializable {

    @FXML
    private TextField tfEventEditNom;
    @FXML
    private TextField tfEventEditLieu;
    @FXML
    private DatePicker dpEventEditDateDeb;
    @FXML
    private DatePicker dpEventEditDateFin;
    @FXML
    private TextField tfEventEditNbP;
    @FXML
    private ComboBox<CategorieEvent> cbEventEdit;
    @FXML
    private Button eventEditChoose;
    @FXML
    private ImageView eventEditImg;
    @FXML
    private Button EventEditButton;
    private File file; 
    private String lien="";
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    Connection cnx = MyCnx.getInstance().getCnx();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             List<CategorieEvent> categories = new ArrayList<>();
        String s = "select * from categorie_event";
         try {
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            CategorieEvent e = new CategorieEvent();
            e.setNom(rs.getString("nom_categ_event"));
           
            e.setId(rs.getInt("id"));
            categories.add(e);
            
        }
         } catch (SQLException ex) {
        Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         cbEventEdit.setItems(FXCollections.observableArrayList(categories));
cbEventEdit.setConverter(new StringConverter<CategorieEvent>() {
        @Override
    public String toString(CategorieEvent categorie) {
        return categorie.getNom();
    }
    @Override
    public CategorieEvent fromString(String nom) {
        return null; // Ne pas utiliser pour la conversion vers une chaîne de caractères
    }
});

    }
 public void init(Evenement e){
        tfEventEditNom.setText(e.getNom_event());
        
        tfEventEditLieu.setText(e.getLocalisation());
        //dpEventEditDateDeb.setValue(e.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        //dpEventEditDateFin.setValue(e.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
       
        tfEventEditNbP.setText(String.valueOf(e.getNb_participants()));
        eventEditImg.setImage(new Image("file:///"+String.valueOf(e.getImage_event())));
        lien = e.getImage_event();
        id = e.getId();
    }    

    @FXML
    private void UploadImage2ActionPerformed(ActionEvent event) {
    }

    @FXML
    private void ModifierEvent(ActionEvent event) throws SQLException, IOException {
    /*    
    if(ValidateEmptyForm(tfEventEditNom,tfEventEditDesc,tfEventEditLieu,dpEventEditDateDeb,dpEventEditDateFin,tfEventEditNbP,eventEditImg)
            && ValidateDateDeb(dpEventEditDateDeb) && ValidateDateFin(dpEventEditDateDeb,dpEventEditDateFin) && ValidateName(tfEventEditNom))
        {
            int category =cbEventEdit.getSelectionModel().getSelectedItem().getId();
            EvenementService se = new EvenementService();
             Evenement p = new Evenement();
            p.setNom_event(tfEventEditNom.getText());
            p.setLocalisation(tfEventEditLieu.getText());
            p.setImage_event(lien);
         p.setDate_debut( java.sql.Date.valueOf(dpEventEditDateDeb.getValue()));
                 p.setDate_fin( java.sql.Date.valueOf(dpEventEditDateFin.getValue()));
                 p.setCategoryId(category);
                 p.setId(id);
       

          
            se.modifier(p);

            JOptionPane.showMessageDialog(null, "Evènement modifié !");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageEvent.fxml"));
        AddEventController aec = loader.getController();
        Parent root = loader.load();
        EventEditButton.getScene().setRoot(root);
            
           

           
        
    }
    */
    }
    private boolean ValidateName(TextField t){
         Pattern p = Pattern.compile("[a-zA-Z]+");
         Matcher m = p.matcher(t.getText());
         if (m.find() && m.group().equals(t.getText())){
             return true;
             
         }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(t.getText()+" : nom non valide");
             alert.showAndWait();
             
             return false;
         }
     }
    
    private boolean ValidateDateDeb(DatePicker d){
         if (d.getValue().isAfter(LocalDate.now())) {
            return true;
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(" Date début non valide");
             alert.showAndWait();
             
             return false;  
         }
     }
    
    private boolean ValidateDateFin(DatePicker d, DatePicker f){
         if (f.getValue().isAfter(d.getValue())) {
            return true;
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(" Date fin non valide");
             alert.showAndWait();
             
             return false;  
         }
     }
}
