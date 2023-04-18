/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;

import Entite.Reclamation;
import ServicesReclamations.ReclamationService;
import Views.AjoutModifController;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utiles.MyCnx;

/**
 * FXML Controller class
 *
 * @author ghofrane
 */
public class CardViewController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label Email;
    @FXML
    private Label Numero;
    @FXML
    private Label etat;
    @FXML
    private Label Motif;
    @FXML
    private Label categ;
    @FXML
    private Button DeleteBtn;
    @FXML
    private Button BtnUpdate;

    Reclamation d ;
    ReclamationService ps = new ReclamationService();
    private Reclamation rec;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Reclamation reclamation) throws SQLException{
     etat.setText(reclamation.getEtat());
     Motif.setText(reclamation.getMotif_de_reclamation());
        Email.setText(reclamation.getEmail());
        Numero.setText(Integer.toString(reclamation.getNum_telephone()));
        img.setImage(new Image("file:src\\uploads\\"+reclamation.getImage()+".png"));
        System.out.println(reclamation.getCategorie_rec());
         if (reclamation.getCategorie_rec().getId()== 0) {
    categ.setText("Catégorie non définie");
    System.out.println("err");
    return;
}

// Exécution de la requête SQL pour récupérer le nom de catégorie
Connection cnx = MyCnx.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT nom FROM categorie_rec WHERE id = ?");
ps.setInt(1,reclamation.getCategorie_rec().getId());
ResultSet rs = ps.executeQuery();
if (rs.next()) {
    String nomCategorie = rs.getString(1);
          
    categ.setText(nomCategorie);
}

 else {
    categ.setText("Catégorie introuvable");
}
        
    }

    @FXML
    private void toUpdate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AjoutModif.fxml"));
 Parent root = loader.load();
        BtnUpdate.getScene().setRoot(root);
    AjoutModifController controller = loader.getController();
    controller.receiveObject(d);
   
    }
     public void receiveObject(Reclamation d) {
        this.d=d;
        rec=d ;
    }
    @FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
        ps.delete(rec.getId());
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
        trayIcon.displayMessage("Notification", "Reclamation Deleted in "+rec.getCategorie_rec().getNom()+" categorie", TrayIcon.MessageType.INFO);
    
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/sample.fxml"));
        SampleController aec = loader.getController();
        Parent root = loader.load();
        DeleteBtn.getScene().setRoot(root);
    }
    
}
