/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.CategorieEvent;
import Entities.Evenement;
import Service.EvenementService;

//import com.twilio.rest.lookups.v1.PhoneNumber;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import utils.MyDB;

/**
 *
 * @author sarah
 */
public class AddEventController implements Initializable {
     public static final String ACCOUNT_SID = "ACf491e8b69c540e8680a9ce7e54a18d94";
    public static final String AUTH_TOKEN = "31c2b47957386a90396c6636d3c6676a";
    
    @FXML
    private Label label;
     @FXML
    private TextField tfEventAddNom;
    @FXML
    private TextArea tfEventAddDesc;
    @FXML
    private TextField tfEventAddLieu;
    @FXML
    private DatePicker dpEventAddDateDeb;
    @FXML
    private DatePicker dpEventAddDateFin;
    @FXML
    private TextField tfEventAddPrix;
    @FXML
    private TextField tfEventAddNbP;
    @FXML
    private Button EventAddButton;
    @FXML
    private ImageView eventAddImg;
    @FXML
    private Button showEventsBT;
    @FXML
    private Button eventAddChoose;
    private File file; 
    private String lien="";
    @FXML
    private ComboBox<CategorieEvent> cbEventAdd;
    @FXML
    private Button EventAddCancel;
      private int uid=4;
      private int id;
      private Evenement d;
      
        Connection cnx = MyDB.getInstance().getCnx();
      @Override
    public void initialize(URL url, ResourceBundle rb) {
      LocalDate dateDeb = dpEventAddDateDeb.getValue();
       LocalDate dateFin = dpEventAddDateFin.getValue();
       /* String  query = "SELECT * from categorie";
          Statement st ;
          ResultSet rs;
          ObservableList<CategorieEvent> categories = FXCollections.observableArrayList();

        try {
            st=MyDB.getInstance().getCnx().createStatement();
            rs=st.executeQuery(query);

            while(rs.next()) 
            {
                categories.add(new CategorieEvent(rs.getString("id")));
                categories.add(new CategorieEvent(rs.getString("nom_categ_event")));
            }   
        } catch (SQLException ex) {
        Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       List<CategorieEvent> categories = new ArrayList<>();
        String s = "select * from categorie";
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
        
         cbEventAdd.setItems(FXCollections.observableArrayList(categories));
cbEventAdd.setConverter(new StringConverter<CategorieEvent>() {
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
    
    public void receiveObject(Evenement d) {
          int category =cbEventAdd.getSelectionModel().getSelectedItem().getId();
        this.d=d;
        id=d.getId();
        
        
            EvenementService se = new EvenementService();
            
           Evenement p = new Evenement();
            p.setNom_event(tfEventAddNom.getText());
            p.setLocalisation(tfEventAddLieu.getText());
            p.setImage_event(lien);
         p.setDate_debut( java.sql.Date.valueOf(dpEventAddDateDeb.getValue()));
                 p.setDate_fin( java.sql.Date.valueOf(dpEventAddDateFin.getValue()));
                 p.setCategoryId(category);
        
   eventAddImg.setImage(new Image("file:src\\uploads\\"+d.getImage_event()+".png"));
    }
      
    @FXML
    
    private void UploadImageActionPerformed(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage image = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);
            eventAddImg.setImage(imagee);
            eventAddImg.setFitWidth(200);
            eventAddImg.setFitHeight(200);
            eventAddImg.scaleXProperty();
            eventAddImg.scaleYProperty();
            eventAddImg.setSmooth(true);
            eventAddImg.setCache(true);                           

        try {
            // save image to PNG file
            this.lien=UUID.randomUUID().toString();
            File f=new File("src\\uploads\\" + this.lien + ".png");
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
                       
        } catch (IOException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
    }

    @FXML
    private void AjouterEvent(ActionEvent event) throws IOException {
        
        if(ValidateEmptyForm(tfEventAddNom,tfEventAddLieu,dpEventAddDateDeb,dpEventAddDateFin,tfEventAddNbP,eventAddImg)
            && ValidateDateDeb(dpEventAddDateDeb) && ValidateDateFin(dpEventAddDateDeb,dpEventAddDateFin) && ValidateName(tfEventAddNom))
        {
            int category =cbEventAdd.getSelectionModel().getSelectedItem().getId();
        
            EvenementService se = new EvenementService();
            
           Evenement p = new Evenement();
            p.setNom_event(tfEventAddNom.getText());
            p.setLocalisation(tfEventAddLieu.getText());
            p.setImage_event(lien);
         p.setDate_debut( java.sql.Date.valueOf(dpEventAddDateDeb.getValue()));
                 p.setDate_fin( java.sql.Date.valueOf(dpEventAddDateFin.getValue()));
                 p.setCategoryId(category);
                System.out.println(cbEventAdd);
                 
                


          
            se.ajouter(p);
            JOptionPane.showMessageDialog(null, "Evènement ajouté !");
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageEvent.fxml"));
        AddEventController aec = loader.getController();
        Parent root = loader.load();
        EventAddButton.getScene().setRoot(root);
            
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21626318708"),
        new PhoneNumber("+15856394545"), 
        "un evenement est ajouté").create();
           
    
    }
    }
            

     
           
           
           

           
    
        @FXML

    public void showEvents (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageEvent.fxml"));
        AddEventController aec = loader.getController();
        Parent root = loader.load();
        showEventsBT.getScene().setRoot(root);
    }
        
    
    
    
    private boolean ValidateEmptyForm(TextField nom, TextField lieu, DatePicker d, DatePicker f, TextField nbp, ImageView img){
         if (nom.getText().equals("")  || lieu.getText().equals("") || 
                 d.getValue()==null || f.getValue()==null || nbp.getText().equals("") || img.getImage()==null ) {
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
    
