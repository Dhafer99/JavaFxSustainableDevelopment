/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamations.Views;
import Reclamations.Entite.Reclamation;
import Reclamations.Entite.Categorie_Rec;
import Reclamations.ServicesReclamations.CategorieRecService;
import Reclamations.ServicesReclamations.ReclamationService;
import Reclamations.Views.SampleController;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ghofrane
 */
public class AjoutModifController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private TextField tfdescription;
    private TextField tfemail;
    private TextField tfnumero;
    @FXML
    private Button Insert;
    @FXML
    private Button ImageAddChoose;
    @FXML
    private ImageView eventAddImg;
    @FXML
    private Button updateB;
    @FXML
    private ComboBox<Categorie_Rec> combo;
    private String lien="";
    @FXML
    private DatePicker tfDate;
Reclamation d = new Reclamation();
private int id;
private Parent fxml;
    @FXML
    private Button retour;
    @FXML
    private TextField text;
    @FXML
    private ImageView pdp;
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
       pdp.setImage(new Image(projetpidd.ProjetPiDD.user.getImage()));
            CategorieRecService se = new CategorieRecService();
            List<Categorie_Rec> personnes = se.displayAll();
            combo.setItems(FXCollections.observableArrayList(personnes));
            combo.setConverter(new StringConverter<Categorie_Rec>() {
                @Override
                public String toString(Categorie_Rec categorie) {
                    return categorie.getNom();
                }
                @Override
                public Categorie_Rec fromString(String nom) {
                    return null; // Ne pas utiliser pour la conversion vers une chaîne de caractères
                }
            });   
    }    
private boolean ValidateEmptyForm(  TextField motif_de_reclamation
){
         if ( motif_de_reclamation.getText().equals("")  
                 ) {
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
    @FXML
    private void AjouterDon(ActionEvent event) throws IOException, AWTException {
        if(ValidateEmptyForm(tfdescription)){

        Categorie_Rec Catrec; // instance
        Categorie_Rec nom =  combo.getValue(); // te5ou ell valeur mill combo box 
        CategorieRecService cdao = new CategorieRecService(); // instance service categorie 
        Catrec = cdao.getOneByName(nom.toString()); 
        LocalDate currentdate = LocalDate.now();
        java.sql.Date data_reclamation = java.sql.Date.valueOf(currentdate);
        System.out.println(currentdate);
        //java.sql.Date data_reclamation = java.sql.Date.valueOf(tfDate.getValue());
        String etat = "En Cours";
        String motif_de_reclamation = tfdescription.getText();
        
        String num_telephone =projetpidd.ProjetPiDD.user.getNumTelephone();
        String email = projetpidd.ProjetPiDD.user.getEmail();
        String image = lien;
         if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
       JOptionPane.showMessageDialog(null, "Symbole or caracter is missing  !");
         } if (!num_telephone.matches("\\d{8}")) {
   JOptionPane.showMessageDialog(null, "Must contain 8 numbers !");
         }else {
        // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
        Reclamation p = new Reclamation(data_reclamation, etat, motif_de_reclamation, image, Integer.parseInt(num_telephone), email,Catrec);
        ReclamationService promotiondao = new ReclamationService();
        promotiondao.insert(p);
         JOptionPane.showMessageDialog(null, "Reclamation ajoutée !");
         if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }

        // Get the system tray instance
        SystemTray tray = SystemTray.getSystemTray();

        // Create a tray icon
        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ghofr\\Desktop\\Java\\GestionReclamation\\src\\image\\icon.png"));
        trayIcon.setToolTip("Notification ");

        // Add a listener for when the user clicks on the tray icon
        

        // Add the tray icon to the system tray
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added");
            return;
        }

        // Display a notification
        trayIcon.displayMessage("Notification", "Reclamation ajoutée dans la categorie "+p.getCategorie_rec().getNom(), TrayIcon.MessageType.INFO);
    }
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("Front.fxml"));
Parent root = loader.load();

AnchorPane myVBox = (AnchorPane) root.lookup("#route");

FXMLLoader includedLoader = new FXMLLoader(getClass().getResource("DonF.fxml"));
Node myAnchorPane = includedLoader.load();

myVBox.getChildren().add(myAnchorPane);

FrontController frontController = loader.getController();
DonFController donFController = includedLoader.getController();

donFController.setFrontController(frontController);
      
        Insert.getScene().setRoot(root);
         }
        
    
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
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage image = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);
            eventAddImg.setImage(imagee);
             PauseTransition pause = new PauseTransition(Duration.seconds(8));
        
        // set the action to be performed when the pause is finished
        pause.setOnFinished(Event -> {
            // your code to be executed after 5 seconds
eventAddImg.setImage(null);
        System.out.println("8 seconds have passed");
        });
        
        // start the pause
        pause.play();
        
        // your code to be executed immediately
        System.out.println("Waiting for 8 seconds...");
    
            eventAddImg.setFitWidth(200);
            eventAddImg.setFitHeight(200);
            eventAddImg.scaleXProperty();
            eventAddImg.scaleYProperty();
            eventAddImg.setSmooth(true);
            eventAddImg.setCache(true);                           

       
            // save image to PNG file
            this.lien=UUID.randomUUID().toString();
            File f=new File("src\\uploads\\" + this.lien + ".png");
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
                       
      
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
    }
public void receiveObject(Reclamation d) {
        this.d=d;
        id=d.getId();
        Date date = d.getData_reclamation();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // replace with your date format
String dateString = formatter.format(date);
Instant instant = Instant.parse(dateString + "T00:00:00Z");
LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
tfDate.setValue(localDate);
        tfdescription.setText(d.getMotif_de_reclamation());
        tfemail.setText(d.getEmail());
        tfnumero.setText(Integer.toString(d.getNum_telephone()));
   eventAddImg.setImage(new Image("file:src\\uploads\\"+d.getImage()+".png"));
   combo.setValue(d.categorie_rec_id);
    }
    @FXML
    private void ModifierEvent(ActionEvent event) {
        
       try {
            ReclamationService se = new ReclamationService();
            Reclamation p = new Reclamation();
            p.setData_reclamation(java.sql.Date.valueOf(tfDate.getValue()));
            p.setImage(lien);
            p.setEtat("En Cours");
            p.setMotif_de_reclamation(tfdescription.getText());
            p.setEmail(tfemail.getText());
            p.setNum_telephone(Integer.valueOf(tfnumero.getText()));
            p.setId(id);
            
            
            se.update(p);

            JOptionPane.showMessageDialog(null, "Reclamation modifiée !");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Front.fxml"));
Parent root = loader.load();

AnchorPane myVBox = (AnchorPane) root.lookup("#route");

FXMLLoader includedLoader = new FXMLLoader(getClass().getResource("DonF.fxml"));
Node myAnchorPane = includedLoader.load();

myVBox.getChildren().add(myAnchorPane);

FrontController frontController = loader.getController();
DonFController donFController = includedLoader.getController();

donFController.setFrontController(frontController);
            updateB.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjoutModifController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Front.fxml"));
 Parent root = loader.load();
        retour.getScene().setRoot(root);
    FrontController controller = loader.getController();
    }

    
    
}
