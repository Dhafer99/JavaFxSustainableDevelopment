/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Models.Association;
import Back.Models.categorieA;
import Back.Services.ServiceAssociation;
import Back.Services.serviceCategorieAssociation;
import Back.Utils.MyCnx;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;
/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class EditAssociationController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfnumero;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfcode_postal;
    @FXML
    private TextField tfville;
    @FXML
    private TableView<Association> tabAssociation;
    @FXML
    private TableColumn<Association, String> colnom;
    @FXML
    private TableColumn<Association,Integer > colnumero;
    @FXML
    private TableColumn<Association, String> colmail;
    @FXML
    private TableColumn<Association, String> coladresse;
    @FXML
    private TableColumn<Association, Integer> colcode_postal;
    @FXML
    private TableColumn<Association, String> colville;
    @FXML
    private TextField filtertext;
    @FXML
    private ImageView eventAddImg;
    @FXML
    private Button ImageAddChoose;
    @FXML
    private ComboBox<categorieA> combo;
ServiceAssociation ps = new ServiceAssociation();
private File file; 

    private String lien="";
    private final ObservableList<Association> eventList = FXCollections.observableArrayList();
      private int id;
       public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private Association d;
    public static final String ACCOUNT_SID = "AC7baee01e459dc347a9e9f0a9b8f744c5";
  public static final String AUTH_TOKEN = "89da9ac629f6cf7374346a905c91de41";
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
        try {
            serviceCategorieAssociation se = new serviceCategorieAssociation();
            List<categorieA> personnes = se.afficher();
            combo.setItems(FXCollections.observableArrayList(personnes));
            combo.setConverter(new StringConverter<categorieA>() {
                @Override
                public String toString(categorieA categorie) {
                    return categorie.getNom();
                }
                @Override
                public categorieA fromString(String nom) {
                    return null; // Ne pas utiliser pour la conversion vers une chaîne de caractères
                }
            });     } catch (SQLException ex) { 
         //   Logger.getLogger(EditAssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         // tri 
         
    }    
public void afficher(){
       try {
            // TODO
            
            List<Association> personnes = ps.afficher();
           
           System.out.println(personnes);
            ObservableList<Association> olp = FXCollections.observableArrayList(personnes);
            tabAssociation.setItems(olp);
            
            colnom.setCellValueFactory(new PropertyValueFactory("nom"));
            colnumero.setCellValueFactory(new PropertyValueFactory("numero"));
            colmail.setCellValueFactory(new PropertyValueFactory("mail"));
             coladresse.setCellValueFactory(new PropertyValueFactory("adresse"));
             colcode_postal.setCellValueFactory(new PropertyValueFactory("CodePostal"));
              colville.setCellValueFactory(new PropertyValueFactory("ville"));
              FilteredList<Association> filter = new FilteredList<>(olp, b->true);
        filtertext.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(event -> {
            if(newValue.isEmpty() || newValue==null ) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(event.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else if (event.getMail().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }else 
            return false;
        });
        });
        SortedList<Association> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tabAssociation.comparatorProperty());
        
        tabAssociation.setItems(sort);


        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
       
    }
    @FXML
    private void handle(MouseEvent event) {
        Association Association = tabAssociation.getSelectionModel().getSelectedItem();
        id = Association.getId();
        
     tfnom.setText(Association.getNom()+"");
     
     tfnumero.setText(Integer.toString(Association.getNumero()));
     tfmail.setText(Association.getMail()+"");
      tfadresse.setText(Association.getAdresse()+"");
     tfcode_postal.setText(Integer.toString(Association.getCodePostal()));
   
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
        
        System.out.println("Waiting for 8 seconds...");
    
            eventAddImg.setFitWidth(200);
            eventAddImg.setFitHeight(200);
            eventAddImg.scaleXProperty();
            eventAddImg.scaleYProperty();
            eventAddImg.setSmooth(true);
            eventAddImg.setCache(true);                           

        try {
            // save image to PNG file
            this.lien=UUID.randomUUID().toString();
            File f=new File("http://localhost/public/" + this.lien );
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
                       
        } catch (IOException ex) {
            Logger.getLogger(EditAssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        
        
    }

    @FXML
    private void Ajouterassociation(ActionEvent event) {
        
          int categorie =combo.getSelectionModel().getSelectedItem().getId();
            
        
           ServiceAssociation se = new ServiceAssociation();
            
            Association p = new Association();
          
            p.setNom(tfnom.getText());
            p.setNumero(Integer.valueOf(tfnumero.getText()));
            p.setMail(tfmail.getText());
            p.setAdresse(tfadresse.getText());
            p.setCodePostal(Integer.valueOf(tfcode_postal.getText()));
            p.setVille(tfville.getText());
            p.setImage(lien);
          p.setCategorie(categorie);
          
            se.ajouter(p);
                JOptionPane.showMessageDialog(null, "Association ajouté !");
              
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21698773438"),
        new PhoneNumber("+12764098996"), 
        "a New ASSOCIATION  has been added  named  "+p.getNom()+"visitez notre site web"+"www.Genereux.com").create();
        
            afficher();
        
        
    }

    @FXML
    private void ModifierEvent(ActionEvent event) throws SQLException {
         ServiceAssociation se = new ServiceAssociation();
             Association p = new Association();
          
              p.setNom(tfnom.getText());
            p.setNumero(Integer.valueOf(tfnumero.getText()));
            p.setMail(tfmail.getText());
            p.setAdresse(tfadresse.getText());
            p.setCodePostal(Integer.valueOf(tfcode_postal.getText()));
            p.setVille(tfville.getText());
            p.setImage(lien);
            p.setId(id);

            se.modifier(p);

            JOptionPane.showMessageDialog(null, "Association modifié !");
             afficher();
    }

    @FXML
    private void deleteEvent(MouseEvent event) throws SQLException {
          Association Association= tabAssociation.getSelectionModel().getSelectedItem();
       
ps.supprimer(Association);
        afficher();
        
    }

    @FXML
    private void Empty(ActionEvent event) {
        id=0;
        tfnom.setText("");
        tfnumero.setText("");
        tfmail.setText("");
        tfadresse.setText("");
        tfcode_postal.setText("");
        tfville.setText("");
        eventAddImg.setImage(null);
        
        
    }

// hediii ell btn
    @FXML
    private void importexcel(ActionEvent event) {
        importFromExcel();
    }
    //importFromExcel();
    
    
     public void exportToExcel() {
        ServiceAssociation sr=new ServiceAssociation();
    try {
        // Création du fichier Excel
        File file = new File("AssociationT2.xls");
        WritableWorkbook workbook = Workbook.createWorkbook(file);

        // Création de la feuille de calcul
        WritableSheet sheet = workbook.createSheet("Association", 0);

        // Ajout des en-têtes de colonnes
        //sheet.addCell(new Label(0, 0, "ID"));
        sheet.addCell(new Label(0, 0, "Nom de l'association"));
        sheet.addCell(new Label(1, 0, "Numero "));
        sheet.addCell(new Label(2, 0, "mail "));
        sheet.addCell(new Label(3, 0, "adresse "));
        sheet.addCell(new Label(4, 0, "code_postal "));
        sheet.addCell(new Label(5, 0, "ville"));
        sheet.addCell(new Label(5, 0, "image"));

        // Récupération des données depuis la base de données
       
        Connection conn ;
           conn = MyCnx.getInstance().getCnx();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Nom, Numero,mail,adresse,code_postal,ville,image  FROM association ");

        // Ajout des données dans la feuille de calcul
        int row = 1;
        while (rs.next()) {
            //sheet.addCell(new jxl.write.Number(0, row, rs.getInt("id")));

            //sheet.addCell(new Label(0, row, rs.getString("coachings_id")));
           sheet.addCell(new Label(0, row, rs.getString("nom")));
           sheet.addCell(new Label(1, row, rs.getString("Numero")));
           sheet.addCell(new Label(2, row, rs.getString("mail")));
           sheet.addCell(new Label(3, row, rs.getString("adresse")));
           sheet.addCell(new Label(4, row, rs.getString("code_postal")));
           sheet.addCell(new Label(5, row, rs.getString("ville")));
           sheet.addCell(new Label(6, row, rs.getString("image")));

          
            row++;
        }

        // Fermeture de la connexion à la base de données
       

        // Écriture du fichier Excel
        workbook.write();
        workbook.close();
        System.out.println("Données exportées avec succès vers le fichier AssociationT2.xls !");
    } catch (Exception e) {
        System.out.println("Erreur lors de l'export des données vers le fichier Excel : " + e.getMessage());
    }
}
    
    
   public void importFromExcel() {
    Connection conn = null;
    try {
        // Show the file chooser dialog to select the Excel file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sélectionner le fichier Excel à importer");
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Get the Excel file
            Workbook workbook = Workbook.getWorkbook(file);

            // Get the worksheet
            Sheet sheet = workbook.getSheet(0);

            // Get the database connection
            conn = MyCnx.getInstance().getCnx();

            // Retrieve data from each row and insert into the database
            String query = "INSERT INTO association (nom, numero, mail, adresse, code_postal, ville,image) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                for (int i = 1; i < sheet.getRows(); i++) {
                    String nom = sheet.getCell(0, i).getContents();
                    String numero = sheet.getCell(1, i).getContents();
                    String mail = sheet.getCell(2, i).getContents();
                    String adresse = sheet.getCell(3, i).getContents();
                    String code_postal = sheet.getCell(4, i).getContents();
                    String ville = sheet.getCell(5, i).getContents(); // Fix index to get correct column
                    String image = sheet.getCell(6, i).getContents();
                    // Set values for prepared statement
                    pstmt.setString(1, nom);
                    pstmt.setString(2, numero);
                    pstmt.setString(3, mail);
                    pstmt.setString(4, adresse);
                    pstmt.setString(5, code_postal);
                    pstmt.setString(6, ville);
                    pstmt.setString(7, image);

                    pstmt.executeUpdate();
                }
            }

            System.out.println("Données importées avec succès depuis le fichier " + file.getName() + " !");
        }

    } catch (Exception e) {
        System.out.println("Erreur lors de l'import des données depuis le fichier Excel : " + e.getMessage());
    }
}

    @FXML
    private void Exportexcel(ActionEvent event) {
         exportToExcel();
    }
    
}
