/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Evenement;
import Service.EvenementService;
import Gui.EditEventController;
import Gui.AddEventController;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JOptionPane;
import utils.MyDB;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import com.itextpdf.text.Font;

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
    private TableColumn<Evenement, Date> DateDebTv;
    @FXML
    private TableColumn<Evenement, Date> DateFinTv;
    @FXML
    private TableColumn<Evenement, Integer> DateTv;
    @FXML
    private TableColumn<Evenement, Button> pdf;
    @FXML
    private Label welcomeLb;
        @FXML

    private Label eventShowNom;
        
        @FXML

    private ImageView eventShowImg;
     @FXML
    private Button btnEventAdd;
      @FXML
    private Button btnEventDelete;
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
    @FXML
    private TableColumn<Evenement, Button> delete;
    @FXML
    private Button modifier;
    @FXML
    private Button btnSearch;
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
        }
        //modifier.setVisible(false);
        modifier.setDisable(true);
        
        
        

    }    
        
    
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


   
     public void pdf() {
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

                            try {
                                 PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Event.pdf"));
} catch (FileNotFoundException ex) {
    Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
} catch (DocumentException ex) {
    Logger.getLogger(AffichageEventController.class.getName()).log(Level.SEVERE, null, ex);
}

// Open the Document
                            document.open();
                            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
Font bodyFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);


                            try {
                                 // Generate the QR code image
                            String eventData = selectedEvenement.toString();
                            Image qrCodeImage = generateQRCode(eventData);

                            // Convert the QR code image to a com.itextpdf.text.Image object
ByteArrayOutputStream baos = new ByteArrayOutputStream();
ImageIO.write(SwingFXUtils.fromFXImage(qrCodeImage, null), "png", baos);
com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(baos.toByteArray());   
pdfImage.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
Paragraph title = new Paragraph(selectedEvenement.getNom_event(), titleFont);
title.setAlignment(Element.ALIGN_CENTER);
document.add(title);
                            // Add the com.itextpdf.text.Image object to the PDF document
                            
                               
Paragraph date_deb = new Paragraph(selectedEvenement.getDate_debut().toString(), bodyFont);
Paragraph adresse = new Paragraph(selectedEvenement.getLocalisation().toString(), bodyFont);

date_deb.setAlignment(Element.ALIGN_LEFT);
adresse.setAlignment(Element.ALIGN_LEFT);
// Add the table to the Document


// Créer un nouveau paragraphe avec le texte souhaité et la police personnalisée
Paragraph p = new Paragraph("Soyez le bienvenue le");

// Ajouter le paragraphe au document
document.add(p);
document.add(date_deb);
Paragraph a = new Paragraph("L'adresse: ");
document.add(a);
document.add(adresse);
Paragraph q = new Paragraph("Scannez pour plus d'informations: ");
pdfImage.scaleAbsolute(100f, 100f);
document.add(pdfImage);

a.setAlignment(Element.ALIGN_LEFT);
q.setAlignment(Element.ALIGN_LEFT);

// Close the Document


                            
                               
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
                        setGraphic(b);
                    }
                }
            };

        });

    }

    
}
