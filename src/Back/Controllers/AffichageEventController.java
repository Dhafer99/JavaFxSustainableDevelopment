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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class AffichageEventController implements Initializable {

    @FXML
    private TableView<Evenement> EventsTv;
    @FXML
    private TableColumn<Evenement, String> nomTv;
    @FXML
    private TableColumn<Evenement, String> LieuTv;
     @FXML
    private TableColumn<Evenement, Date> DateDebTv;
      @FXML
    private TableColumn<Evenement, Date> DateFinTv;
    
        
        @FXML

    private ImageView eventShowImg;
      @FXML
    private TextField eventSearch;
      @FXML
    private Label eventShowDateDeb;
    @FXML
    private Label eventShowDateFin;
      
        


    // instance database Service 
    EvenementService ps = new EvenementService();
    @FXML
    private TableColumn<Evenement, Button> delete;
    @FXML
    private TextField tfEventEditNom;
    @FXML
    private TextField tfEventEditLieu;
    private ImageView eventEditImg;
    private ImageView
    eventAddImg1;
    @FXML
    private Button EventEditButton;
   
    
    @FXML
    private DatePicker dpEventEditDateDeb;
    @FXML
    private DatePicker dpEventEditDateFin;
    private TextField tfEventEditNbP;
    @FXML
    private ComboBox<CategorieEvent> cbEventEdit;
  
    private File file; 
    private String lien="";
    private int id;
    @FXML
    private ImageView eventShowImg1;

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
        try {
            // TODO
            List<Evenement> personnes = ps.afficher();
            ObservableList<Evenement> olp = FXCollections.observableArrayList(personnes);
             System.out.println(personnes);
            
          nomTv.setCellValueFactory(new PropertyValueFactory("nom_event"));
            LieuTv.setCellValueFactory(new PropertyValueFactory("localisation"));
            DateDebTv.setCellValueFactory(new PropertyValueFactory("date_debut"));
             DateFinTv.setCellValueFactory(new PropertyValueFactory("date_fin"));
             this.deleteEvent();
            // this.pdf();
             EventsTv.setItems(olp);
             
              //search//
        FilteredList<Evenement> filter = new FilteredList<>(olp, b->true);
        eventSearch.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(event -> {
            if(newValue.isEmpty() || newValue==null ) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(event.getNom_event().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else if (event.getLocalisation().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }else 
            return false;
        });
        });
        SortedList<Evenement> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(EventsTv.comparatorProperty());
        
        EventsTv.setItems(sort);
        
            
             
            //this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
      
    }    


    @FXML
    private void showSelectedEvent(MouseEvent event) throws SQLException {
     
        
        
    }
        public void updateTable() throws SQLException {
    ObservableList<Evenement> list = FXCollections.observableArrayList(ps.afficher());
    EventsTv.setItems(list);
}
    public void deleteEvent() {
        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("supprimer");
                        b.setOnAction((event) -> {
                            try {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression");
                            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet événement ?");
                            

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                if (ps.supprimer(EventsTv.getItems().get(getIndex()))) {
                                    updateTable();
                                }
                            }
                            
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });

    }


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
            File f=new File("http://localhost/public/" + this.lien );
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
    private void handleMouseAction(MouseEvent event) {
        
       Evenement don= EventsTv.getSelectionModel().getSelectedItem();
       id=don.getId();
        tfEventEditNom.setText(don.getNom_event()+"");
       
        tfEventEditLieu.setText(don.getLocalisation());
        
   eventAddImg1.setImage(new Image("file:http://localhost/public/"+don.getImage_event()));

    // Add the imageView to your scene or layout

    }

    @FXML
    private void ModifierEvent(ActionEvent event) throws SQLException, IOException {
         if(ValidateEmptyForm(tfEventEditNom,tfEventEditLieu,dpEventEditDateDeb,dpEventEditDateFin,tfEventEditNbP,eventEditImg)
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
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Back/Views/affichageEvent.fxml"));
        AddEventController aec = loader.getController();
        Parent root = loader.load();
        EventEditButton.getScene().setRoot(root);
            
           

           
        
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
      private boolean ValidateEmptyForm(TextField nom, TextField lieu, DatePicker d, DatePicker f, TextField tfEventEditNbP1, ImageView eventEditImg1){
         if (nom.getText().equals("")  || lieu.getText().equals("") || 
                 d.getValue()==null || f.getValue()==null  ) {
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
    }
    
    
    

