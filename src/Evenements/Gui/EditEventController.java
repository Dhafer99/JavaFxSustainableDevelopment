/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenements.Gui;

import Evenements.Entities.Evenement;
import Evenements.Entities.CategorieEvent;
import Evenements.Service.EvenementService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import Evenements.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class EditEventController implements Initializable {
   @FXML
    private TextField tfEventEditNom;
    @FXML
    private TextArea tfEventEditDesc;
    @FXML
    private TextField tfEventEditLieu;
    @FXML
    private DatePicker dpEventEditDateDeb;
    @FXML
    private DatePicker dpEventEditDateFin;
    
    @FXML
    private TextField tfEventEditNbP;
    @FXML
    private Button showEventsBT;
    @FXML
    private Button EventEditButton;
    @FXML
    private ImageView eventEditImg;
    @FXML
    private Button eventEditChoose;
    private File file; 
    private String lien="";
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @FXML
    private ComboBox<CategorieEvent> cbEventEdit;
    @FXML
    private Button EventEditCancel;
   Connection cnx = MyDB.getInstance().getCnx();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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


        ///////number validator//////////
        
       
        ////////price validator//////////
        
       /* UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {

            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {

                if (t.isReplaced()) 
                    if(t.getText().matches("[^0-9]"))
                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
                
                if (t.isAdded()) {
                    if (t.getControlText().contains(".")) {
                        if (t.getText().matches("[^0-9]")) {
                            t.setText("");
                        }
                    } else if (t.getText().matches("[^0-9.]")) {
                        t.setText("");
                    }
                }
                
                return t;
            }
        };
        tfEventEditPrix.setTextFormatter(new TextFormatter<>(filter));
    }    */
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
    private void ModifierEvent(ActionEvent event) throws IOException {
        
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
    }

    @FXML
    private void UploadImage2ActionPerformed(ActionEvent event) {
        
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
            eventEditImg.setImage(imagee);
            eventEditImg.setFitWidth(200);
            eventEditImg.setFitHeight(200);
            eventEditImg.scaleXProperty();
            eventEditImg.scaleYProperty();
            eventEditImg.setSmooth(true);
            eventEditImg.setCache(true);                           

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
    public void showEvents (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageEvent.fxml"));
        AddEventController aec = loader.getController();
        Parent root = loader.load();
        showEventsBT.getScene().setRoot(root);
    }
    
    
     private boolean ValidateEmptyForm(TextField nom, TextArea tfEventEditDesc1, TextField lieu, DatePicker d, DatePicker f, TextField nbp, ImageView img){
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
