/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Evenement;
import Entities.User;
import Service.EvenementService;
import Gui.EditEventController;
import Gui.AddEventController;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;

import java.awt.image.BufferedImage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import utils.MyDB;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import java.awt.Desktop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.apache.commons.io.IOUtils.writer;




/**
 * FXML Controller class
 *
 * @author sarah
 */
public class AffichageEventController implements Initializable {

    @FXML
    private TableView<Evenement> EventsTv;
    @FXML
    private TableColumn<Evenement, String> nomTv;
    @FXML
    private TableColumn<Evenement, String> LieuTv;
    @FXML
    private TableColumn<Evenement, Button> pdf;
     @FXML
    private Button participer;
        @FXML

    private Label eventShowNom;
        
        @FXML

    private ImageView eventShowImg;
     @FXML
    private Button btnEventAdd;
      @FXML
    private TextField eventSearch;
      @FXML
    private Label eventShowDateDeb;
    @FXML
    private Label eventShowDateFin;
    @FXML
    private Label eventShowCategory;
      
        


    // instance database Service 
    EvenementService ps = new EvenementService();
    
    Evenement evenement = new Evenement();
    int idUtilisateurParticipant = getIdUtilisateurParticipant(evenement);
    
    @FXML
    private TableColumn<Evenement, Button> delete;
    @FXML
    private Button modifier;
    @FXML
    private Button CategBT;
    
    @FXML
    private Button FrontBT;
 
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            List<Evenement> personnes = ps.afficher();
            ObservableList<Evenement> olp = FXCollections.observableArrayList(personnes);
             
            
          nomTv.setCellValueFactory(new PropertyValueFactory("nom_event"));
            LieuTv.setCellValueFactory(new PropertyValueFactory("localisation"));
            //DateDebTv.setCellValueFactory(new PropertyValueFactory("date_debut"));
             //DateFinTv.setCellValueFactory(new PropertyValueFactory("date_fin"));
             this.deleteEvent();
             this.pdf();
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriterException ex) {
            Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //modifier.setVisible(false);
        modifier.setDisable(true);
        
        
        

    }    
          public int getIdUtilisateurParticipant(Evenement evenement) {
    for (User user : evenement.getUsers()) {
        if (evenement.getUsers().contains(user)) {
            return user.getId();
        }
    }
    return -1; // Retourne -1 si aucun utilisateur n'a participé
}
          /*
        @FXML
          public void participer(User user , Evenement e) {
              user.setId(1);
    
    e.getUsers().add(user);
   e.participer();
    System.out.println("L'utilisateur avec l'ID " + idUtilisateurParticipant + " a participé à l'événement " + e.getNom_event());
    // Autres actions à exécuter lors de la participation de l'utilisateur à l'événement
}*/
          
          
        @FXML
    private void GoToCategories(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageCategE.fxml"));
        AffichageCategEController aec = loader.getController();
        Parent root = loader.load();
        CategBT.getScene().setRoot(root);
        

        
    }
    
    

   @FXML
    private void GoToAddEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
        AddEventController aec = loader.getController();
        Parent root = loader.load();
        btnEventAdd.getScene().setRoot(root);
        
        
    }
    
    
    @FXML
    private void GoToFront(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        
        Parent root = loader.load();
        SampleController c = loader.getController();
        FrontBT.getScene().setRoot(root);
          
    }
    
    
  
    @FXML
    private void GoToEditEvent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editEvent.fxml"));
        
        Parent root = loader.load();
        modifier.getScene().setRoot(root);
        
        EditEventController eec = loader.getController();
        Evenement e = EventsTv.getSelectionModel().getSelectedItem();
        
        eec.init(e);  
        
    }
    /*
    @FXML
       public void participerEvent() {
    
participer.setOnAction(event -> {
     System.out.println("tss");
     Evenement e = EventsTv.getSelectionModel().getSelectedItem();
        

    User user = new User();
    user.setId(1);
    participer(user,e);
});

    }
    */
 
 
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
     
   
     
     public void updateTable() throws SQLException {
    ObservableList<Evenement> list = FXCollections.observableArrayList(ps.afficher());
    EventsTv.setItems(list);
}
    
     
      @FXML
    void showSelectedEvent(MouseEvent event) throws SQLException {
        Evenement e = EventsTv.getSelectionModel().getSelectedItem();
     
        if (e != null) {
            
            eventShowNom.setText(String.valueOf(e.getNom_event()));
           // eventShowDatePub.setText("Publié par : "+ String.valueOf(e.getUserId())+ " le " + String.valueOf(e.getDatePub()));
            eventShowDateDeb.setText(String.valueOf(e.getDate_debut()));
           eventShowDateFin.setText(String.valueOf(e.getDate_fin()));
            //eventShowDesc.setText(String.valueOf(e.getDescription()));
            
          /* Connection cnx = MyDB.getInstance().getCnx();
            PreparedStatement ps;
            ps = cnx.prepareStatement("SELECT nom_categ_event FROM categorie WHERE id = ?");
            ps.setString(1, String.valueOf(e.getCategoryId()));
            ResultSet rs = ps.executeQuery();
            rs.next();
            eventShowCategory.setText(rs.getString(1));*/
      
if (e.getCategoryId() == 0) {
    eventShowCategory.setText("Catégorie non définie");
    System.out.println("err");
    return;
}

// Exécution de la requête SQL pour récupérer le nom de catégorie
Connection cnx = MyDB.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT nom_categ_event FROM categorie WHERE id = ?");
ps.setInt(1, e.getCategoryId());
ResultSet rs = ps.executeQuery();
if (rs.next()) {
    String nomCategorie = rs.getString(1);
    eventShowCategory.setText(nomCategorie);
} else {
    eventShowCategory.setText("Catégorie introuvable");
}
         
            
        //    eventShowCategory.setVisible(true);
            
            System.out.println("file:src/uploads/"+e.getImage_event()+".png");
            eventShowImg.setImage(new Image("file:src/uploads/"+e.getImage_event()+".png"));
             //modifier.setVisible(true);
        modifier.setDisable(false);
           // if(e.getUserId() == uid)
            {
               // btnEventDelete.setVisible(true);
                //btnEventDelete.setDisable(false);
               // btnEventEdit.setVisible(true);
               // btnEventEdit.setDisable(false);
         //   }else{
               /* btnEventDelete.setVisible(false);
                btnEventDelete.setDisable(true);
                btnEventEdit.setVisible(false);
                btnEventEdit.setDisable(true);*/
           // }
            
            //EventPieChart.setVisible(false);

        }
    }
    }
    
     public void qrcode() throws SQLException {

Evenement data = EventsTv.getSelectionModel().getSelectedItem();
        

        // Create a button
        // Set an event handler for the button
        // Convert the data to a string
        StringBuilder stringBuilder = new StringBuilder();
       
            stringBuilder.append(data);
            System.out.println(data);
            stringBuilder.append("\n");
        
        String dataString = stringBuilder.toString().trim();

        // Generate the QR code image
        Image qrCodeImage = generateQRCode(dataString, 100, 100);

        // Display the QR code image
        ImageView imageView = new ImageView(qrCodeImage);
        VBox vbox = new VBox(imageView);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    private Image generateQRCode(String data, int par, int par1) {
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


   
     public void pdf() throws FileNotFoundException, DocumentException, WriterException, IOException {
        pdf.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("imprimer");
                       
                        
                        b.setOnAction((event) -> {
                            System.out.println("gggg");
                            // Get the selected Activite object from the TableView
                            Evenement selectedEvenement = (Evenement) getTableView().getItems().get(getIndex());
                            // Create a new Document
                            Document document = new Document();

                        

// Open the Document
                            document.open();
                        // Set the font styles
    Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
    Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
    Font bodyFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

 PdfWriter writer = null;
                            try {
                                writer = PdfWriter.getInstance(document, new FileOutputStream("Event.pdf"));
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (DocumentException ex) {
                                Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            try {
                                  document.setPageSize(PageSize.A6);
    document.setMargins(10, 10, 10, 10);

    // Add a header to the document
    Paragraph header = new Paragraph("TICKET D'ÉVÉNEMENT", titleFont);
    header.setAlignment(Element.ALIGN_CENTER);
    document.add(header);

    // Add the event name and date to the document
    Paragraph eventName = new Paragraph(selectedEvenement.getNom_event(), subtitleFont);
    eventName.setAlignment(Element.ALIGN_CENTER);
    document.add(eventName);

    Paragraph eventDate = new Paragraph(selectedEvenement.getDate_debut().toString(), bodyFont);
    eventDate.setAlignment(Element.ALIGN_CENTER);
    document.add(eventDate);

    // Add the event location to the document
    Paragraph eventLocationTitle = new Paragraph("Lieu de l'événement:", subtitleFont);
    eventLocationTitle.setAlignment(Element.ALIGN_LEFT);
    document.add(eventLocationTitle);

    Paragraph eventLocation = new Paragraph(selectedEvenement.getLocalisation().toString(), bodyFont);
    eventLocation.setAlignment(Element.ALIGN_LEFT);
    document.add(eventLocation);

    // Add the QR code to the document
        com.itextpdf.text.Image qrCodeImage = generateQRCodeImage(selectedEvenement.toString(), 100, 100);
    
    document.add(qrCodeImage);
    

    // Add a footer to the document
    Paragraph footer = new Paragraph("Merci d'avoir participé à cet événement.", bodyFont);
    footer.setAlignment(Element.ALIGN_CENTER);
    footer.setSpacingBefore(20f);
    document.add(footer);

    // Set the background color of the document
    PdfContentByte canvas = writer.getDirectContent();
    Rectangle rect = new Rectangle(document.getPageSize());
    rect.setBackgroundColor(new BaseColor(214, 214, 214));
    canvas.rectangle(rect);



                            
                               
                            } catch (DocumentException ex) {
                                Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (WriterException ex) {
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
                        setGraphic(b);
                    }
                }
            };

        });

       
     
       
       
       
       
       
       
       
       
    }
     public com.itextpdf.text.Image generateQRCodeImage(String data, int width, int height) throws WriterException, IOException, BadElementException {
    QRCodeWriter writer = new QRCodeWriter();
    BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height);

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            image.setRGB(x, y, (int) (bitMatrix.get(x, y) ? Color.BLACK.getBrightness(): Color.WHITE.getBrightness()));
        }
    }
com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(image, null) ;
    return pdfImage ;

}

    @FXML
    private void test(ActionEvent event) throws SQLException {
        
       
              Connection cnx = MyDB.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT name FROM user WHERE id = 2");

ResultSet rs = ps.executeQuery();
if(rs.next()){
    String nom = rs.getString(1);
         Evenement e = EventsTv.getSelectionModel().getSelectedItem();
          
             
             String nb ="UPDATE evenement set  nb_participants= ? where id = ? ";
 PreparedStatement pst = cnx.prepareStatement(nb);
            pst.setInt(1, e.getNb_participants()+1);
            pst.setInt(2, e.getId());
 pst.executeUpdate();
 
String query = "INSERT INTO evenement_user (evenement_id, user_id) VALUES (?, ?)";
PreparedStatement statement = cnx.prepareStatement(query);
statement.setInt(1, e.getId());
statement.setInt(2, 2);
int rowsInserted = statement.executeUpdate();  



System.out.println("bla bla " + nom);
             
    
}

 
        
    }

    
}
