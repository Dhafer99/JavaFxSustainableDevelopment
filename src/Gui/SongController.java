package Gui;

import Entities.Evenement;
import Entities.Participation;
import Entities.User;
import static Gui.AddEventController.ACCOUNT_SID;
import static Gui.AddEventController.AUTH_TOKEN;
//import Service.AddressToLatLng;
//import static Service.AddressToLatLng.getLatLongFromAddress;
import Service.EvenementService;
import com.ctc.wstx.shaded.msv_core.reader.Controller;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//import Entities.Evenement.AddUpdateEController;
//import Entities.Evenement.SampleController;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import utils.MyDB;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Optional;
import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;


/**
 */
public class SongController implements Initializable {
     public static final String ACCOUNT_SID = "ACf491e8b69c540e8680a9ce7e54a18d94";
    public static final String AUTH_TOKEN = "31c2b47957386a90396c6636d3c6676a";
    
   @FXML
    private ImageView img;
   @FXML
    private Label EventName;
   @FXML
    private Label Adresse;
   @FXML
    private Label Date_deb;
   @FXML
    private Label Date_fin;
   @FXML
    private Label  
           
ShowCategory;
  @FXML
    private Button BtnUpdate;
    @FXML
    private Button DeleteBtn;
    @FXML
    private Button participer;
    @FXML
    private Button annuler;
    @FXML
    private Button pdf;
    EvenementService ps = new EvenementService();
     Evenement d = new Evenement();
private Evenement Evenement;
   // public static Boolean test = false;

private Evenement ev;
 private String nom1 ;
    
 
 
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          pdf.setVisible(false); 
                    annuler.setVisible(false); 

    //participer.setVisible(true);
        
       /*        try {
              Connection cnx = MyDB.getInstance().getCnx();
HashMap<Integer, Integer> Events = new HashMap<>();

PreparedStatement ps1 = cnx.prepareStatement("SELECT * FROM `user_evenement` ");
ResultSet rs1 = ps1.executeQuery();

while (rs1.next()) {
    int eventId = rs1.getInt("evenement_id");
    int userId = rs1.getInt("user_id");
    Events.put(eventId, userId);
}


if (Events.containsKey(d.getId()) && Events.containsValue(2)) {
    BtnUpdate.setVisible(true);
    DeleteBtn.setVisible(true);
} else {
    BtnUpdate.setVisible(false);
    DeleteBtn.setVisible(false);
}



 

              
     } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }          
*/
    }  
 

        public void setData(Evenement event) throws SQLException{
     EventName.setText(event.getNom_event());
        Adresse.setText(event.getLocalisation());
        Date_deb.setText(String.valueOf(event.getDate_debut()));
        Date_fin.setText(String.valueOf(event.getDate_fin()));
   img.setImage(new Image("file:src\\uploads\\"+event.getImage_event()+".png"));
  
   if (event.getCategoryId() == 0) {
    ShowCategory.setText("Catégorie non définie");
    System.out.println("err");
    return;
}

// Exécution de la requête SQL pour récupérer le nom de catégorie
Connection cnx = MyDB.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT nom_categ_event FROM categorie WHERE id = ?");
ps.setInt(1, event.getCategoryId());
ResultSet rs = ps.executeQuery();
if (rs.next()) {
    String nomCategorie = rs.getString(1);
    ShowCategory.setText(nomCategorie);
} else {
    ShowCategory.setText("Catégorie introuvable");
}
   
   
    }
@FXML
    private void ToUpdate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdate.fxml"));
 Parent root = loader.load();
        BtnUpdate.getScene().setRoot(root);
    AddUpdateEController controller = loader.getController();
    controller.receiveObject(d);
   
    }
     public void receiveObject(Evenement d) {
        this.d=d;
        Evenement=d;
    }
    @FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
    
    
        Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de vouloir supprimer cet événement ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        System.out.println(Evenement);
    //test = true;
        ps.supprimer(Evenement);  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        Controller aec = loader.getController();
        Parent root = loader.load();
        DeleteBtn.getScene().setRoot(root);
    } 
    }
  
  
@FXML
    private void test(ActionEvent event) throws SQLException {
        
       User user = new User();
       Evenement e = new Evenement();
              Connection cnx = MyDB.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT name FROM user WHERE id = 1");

ResultSet rs = ps.executeQuery();
if(rs.next()){
     nom1 = rs.getString(1);
        // Evenement e = EventsTv.getSelectionModel().getSelectedItem();
          
             
             String nb ="UPDATE evenement set  nb_participants= ? where id = ? ";
 PreparedStatement pst = cnx.prepareStatement(nb);
            pst.setInt(1, d.getNb_participants()+1);
            pst.setInt(2, d.getId());
 pst.executeUpdate();
 System.out.println("bla bla " + nom1);
}
 
String query = "INSERT INTO evenement_user (evenement_id, user_id) VALUES (?, ?)";
PreparedStatement statement = cnx.prepareStatement(query);
statement.setInt(1, d.getId());
statement.setInt(2, 1);
int rowsInserted = statement.executeUpdate();  



HashMap<Integer,Integer> Events = new HashMap<>();

PreparedStatement ps1 = cnx.prepareStatement("SELECT * FROM `evenement_user` ");

ResultSet rs1 = ps1.executeQuery();
if(rs1.next()){
    Participation p = new Participation();
   
     
     Events.put(rs1.getInt("evenement_id"),rs1.getInt("user_id"));
     
   

}
 
    if(Events.containsKey(d.getId()) && Events.containsValue(1))
    {
        participer.setVisible(false);
         PauseTransition pause = new PauseTransition(Duration.seconds(3));
        
        // set the action to be performed when the pause is finished
        pause.setOnFinished(Event -> {
            // your code to be executed after 5 seconds

        pdf.setVisible(true);
        
        
        
        System.out.println("3 seconds have passed");
        });
         
          pause.play();
          Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Impression de ticket");
        alert.setHeaderText(null);
        alert.setContentText("Vous pouvez maintenant imprimer votre ticket !");
        alert.showAndWait();
        
        PauseTransition pause1 = new PauseTransition(Duration.seconds(2));
        
        // set the action to be performed when the pause is finished
        pause1.setOnFinished(Event -> {
            // your code to be executed after 5 seconds
annuler.setVisible(true);
       
        
        
        
        System.out.println("3 seconds have passed");
        });
           pause1.play();
             Alert alert1 = new Alert(AlertType.INFORMATION);
        alert1.setTitle("Annulation");
        alert1.setHeaderText(null);
        alert1.setContentText("Vous pouvez annuler votre participation a tout moment");
        alert1.showAndWait();
        
        
    }
    System.out.println(Events);

 /* Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21626318708"),
        new PhoneNumber("+15856394545"), 
        nom1 +" a participé à l'evnement " + d.getNom_event()).create();*/
                    

 }
    
    @FXML
    private void test2(ActionEvent event) throws SQLException {
        
       User user = new User();
       Evenement e = new Evenement();
              Connection cnx = MyDB.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT name FROM user WHERE id = 1");

ResultSet rs = ps.executeQuery();
if(rs.next()){
     nom1 = rs.getString(1);
        // Evenement e = EventsTv.getSelectionModel().getSelectedItem();
          
             
             String nb ="UPDATE evenement set  nb_participants= ? where id = ? ";
 PreparedStatement pst = cnx.prepareStatement(nb);
            pst.setInt(1, d.getNb_participants()-1);
            pst.setInt(2, d.getId());
 pst.executeUpdate();
 System.out.println("bla bla " + nom1);
}
 
String query = "DELETE FROM evenement_user where evenement_id = ?";
PreparedStatement statement = cnx.prepareStatement(query);
statement.setInt(1, d.getId());

int rowsInserted = statement.executeUpdate();  




HashMap<Integer,Integer> Events = new HashMap<>();

PreparedStatement ps1 = cnx.prepareStatement("SELECT * FROM `evenement_user` ");

ResultSet rs1 = ps1.executeQuery();
if(rs1.next()){
    Participation p = new Participation();
   
     
     Events.put(rs1.getInt("evenement_id"),rs1.getInt("user_id"));

}
 
  
        participer.setVisible(true);
        annuler.setVisible(false);
        pdf.setVisible(false);
        
        
         /*Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Impression de ticket");
        alert.setHeaderText(null);
        alert.setContentText("Vous pouvez maintenant imprimer votre ticket !");
        alert.showAndWait();
        */
        
        
    }
   // System.out.println(Events);

  /*Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21626318708"),
        new PhoneNumber("+15856394545"), 
        nom1 +" a annuler la participation de l'evnement " + d.getNom_event()).create();*/
                    

 
    
    
      public void qrcode() throws SQLException {

//Evenement data = EventsTv.getSelectionModel().getSelectedItem();
        

        // Create a button
        // Set an event handler for the button
        // Convert the data to a string
        StringBuilder stringBuilder = new StringBuilder();
       
            stringBuilder.append(d);
            System.out.println(d);
            stringBuilder.append("\n");
        
        String dataString = stringBuilder.toString().trim();

        // Generate the QR code image
        Image qrCodeImage = generateQRCode(dataString);

        // Display the QR code image
        ImageView imageView = new ImageView(qrCodeImage);
        VBox vbox = new VBox(imageView);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    private Image generateQRCode(String data) {
        try {
            // Create a QR code writer
            QRCodeWriter writer = new QRCodeWriter();

            // Create a BitMatrix from the data and set the size
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 200, 200);

            // Create a BufferedImage from the BitMatrix
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Create a JavaFX Image from the BufferedImage
            return SwingFXUtils.toFXImage(image, null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

@FXML
public void pdf() throws FileNotFoundException, DocumentException, WriterException, IOException, SQLException {
    // Get the selected Event object from the TableView
    // Event selectedEvent = (Event) getTableView().getItems().get(getIndex());

    // Create a new Document
    Document document = new Document();
   document.setPageSize(PageSize.A4);
    // Create a PdfWriter instance
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Event.pdf"));
  Connection cnx = MyDB.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT name FROM user WHERE id = 1");

ResultSet rs = ps.executeQuery();
if(rs.next()){
     nom1 = rs.getString(1);
}
    // Set the event handler for the pdf button
    pdf.setOnAction((event) -> {
        try {
            
            System.out.println("Generating PDF...");
            // Open the Document
            document.open();

            // Set the font styles
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
            Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL, BaseColor.BLACK);
            Font bodyFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

            // Load the background image
            String imageEP = "file:src/uploads/" + d.getImage_event() + ".png";
            com.itextpdf.text.Image imageE = com.itextpdf.text.Image.getInstance(imageEP);

            // Set the absolute position of the background image
           

            // Get the PdfContentByte instance
            imageE.scaleAbsolute(100f, 100f);
imageE.setAlignment(Element.ALIGN_LEFT);
            // Add the background image to the canvas
            

            String bgImagePath = "C:\\Users\\sarah\\OneDrive\\Documents\\Pidev\\GestionEvent\\src\\Gui\\bg.png";
com.itextpdf.text.Image bgImage = com.itextpdf.text.Image.getInstance(bgImagePath);

// Get the PdfContentByte instance
PdfContentByte canvas = writer.getDirectContentUnder();

// Add the background image to the canvas
bgImage.scaleToFit(document.getPageSize());
bgImage.setAbsolutePosition(0, 0);
canvas.addImage(bgImage);
            
            
            
          

          // Add the logo to the document
            String logoImagePath = "C:\\Users\\sarah\\OneDrive\\Documents\\Pidev\\GestionEvent\\src\\Gui\\logoequipe.png";
            com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(logoImagePath);
            logo.setAlignment(Element.ALIGN_LEFT);
            logo.scaleAbsolute(80f, 80f);
            document.add(logo);

            // Add a header to the document
            Paragraph header = new Paragraph("TICKET D'ÉVÉNEMENT", titleFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            // Add the event name and date to the document
            Paragraph eventName = new Paragraph(d.getNom_event(), subtitleFont);
            eventName.setAlignment(Element.ALIGN_CENTER);
            document.add(eventName);

            Paragraph eventDate = new Paragraph(d.getDate_debut().toString(), bodyFont);
    eventDate.setAlignment(Element.ALIGN_CENTER);
    document.add(eventDate);
Paragraph es = new Paragraph("    ");
document.add(es);
// Add the event location to the document
    Paragraph user = new Paragraph("Votre Nom: " + nom1, subtitleFont);
    user.setAlignment(Element.ALIGN_LEFT);
    document.add(user);
    // Add the event location to the document
    Paragraph eventLocationTitle = new Paragraph("Lieu de l'événement: " +d.getLocalisation(), subtitleFont);
    eventLocationTitle.setAlignment(Element.ALIGN_LEFT);
    document.add(eventLocationTitle);

    
    document.add(imageE);
    Paragraph scan = new Paragraph("Scannez pour plus d'informations ! ");
    // Add the QR code to the document
 // Generate the QR code image
                            String eventData = d.toString();
                            Image qrCodeImage = generateQRCode(eventData);

                            // Convert the QR code image to a com.itextpdf.text.Image object
ByteArrayOutputStream baos = new ByteArrayOutputStream();
ImageIO.write(SwingFXUtils.fromFXImage(qrCodeImage, null), "png", baos);
com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(baos.toByteArray());  
pdfImage.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
pdfImage.scaleAbsolute(100f, 100f);
document.add(scan);
    document.add(pdfImage);
    

    // Add a footer to the document
    Paragraph footer = new Paragraph("Merci d'avoir participé à cet événement.", bodyFont);
    footer.setAlignment(Element.ALIGN_CENTER);
    footer.setSpacingBefore(20f);
    document.add(footer);
String imagePath1 = "C:\\Users\\sarah\\OneDrive\\Documents\\Pidev\\GestionEvent\\src\\Gui\\Signature.png";
com.itextpdf.text.Image sig = com.itextpdf.text.Image.getInstance(imagePath1);
sig.setAlignment(Element.ALIGN_RIGHT);
sig.scaleAbsolute(60f, 60f);
document.add(es);
//document.add(es);

document.add(sig);
 Paragraph id = new Paragraph("Numero Ticket: " +d.getId());
    id.setAlignment(Element.ALIGN_LEFT);
    id.setSpacingBefore(10f);
    document.add(id);

    // Set the background color of the document
  /*PdfContentByte c = writer.getDirectContent();
    Rectangle rect = new Rectangle(document.getPageSize());
    rect.setBackgroundColor(new BaseColor(214, 214, 214));
    c.rectangle(rect);*/

// Create a new Rectangle object
           
            
            
            

                            
                               
                            } catch (DocumentException ex) {
                                Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
                            }

// Close the Document
                            document.close();

                            if (Desktop.isDesktopSupported()) {
                                Desktop desktop = Desktop.getDesktop();
                                try {
                                    desktop.open(new File("Event.pdf"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        });
                        
                        
                      
                    }

    @FXML
    private void claim(ActionEvent event) {
    }

    @FXML
    private void map(ActionEvent event) {
    }

   
}

