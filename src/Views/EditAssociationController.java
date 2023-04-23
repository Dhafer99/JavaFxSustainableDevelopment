package Views;

import static Controllers.AjoutAssociationController.ACCOUNT_SID;
import static Controllers.AjoutAssociationController.AUTH_TOKEN;
import Models.Association;
import ServiceAssociation.AssociationService;
import Utils.MyCnx;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javax.swing.JOptionPane;

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
    private TableColumn<Association, Integer> colnumero;
    @FXML
    private TableColumn<Association, String> colmail;
    @FXML
    private TableColumn<Association, String> coladresse;
    @FXML
    private TableColumn<Association, Integer> colcode_postal;
    @FXML
    private TableColumn<Association, String> colville;
private listdata Ls = new listdata();
    //private TextField searchField;
    
    public static final String ACCOUNT_SID = "AC7baee01e459dc347a9e9f0a9b8f744c5";
  public static final String AUTH_TOKEN = "9c4d3175f524029937ea448f92ae988e";
    @FXML
    private TextField search;
    
     private Parent fxml;
    @FXML
    private AnchorPane root;
    
    AssociationService ps = new AssociationService();
    @FXML
    private Button nf;
    /**
     * Initializes the controller class.
     */

@Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         affiche();
        List<Association> personnes = ps.displayAll();
        ObservableList<Association> olp = FXCollections.observableArrayList(personnes);
        //search//
        FilteredList<Association> filter = new FilteredList<>(olp, b->true);
        search.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(event -> {
            if(newValue.isEmpty() || newValue==null ) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(event.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;

            
            }else 
            return false;
        });
        });
        SortedList<Association> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tabAssociation.comparatorProperty());
        
        tabAssociation.setItems(sort);
      
    }    
 public void affiche(){
     
    
       tabAssociation.setItems(Ls.getPersons());
       
      //  colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colnumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        coladresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colcode_postal.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
        colville.setCellValueFactory(new PropertyValueFactory<>("ville"));
       
   }
    
    @FXML
    private void handle(MouseEvent event) {
        
            
    Association asso=tabAssociation.getSelectionModel().getSelectedItem();
  
   // pourcentage.setText(""+promo.getPourcentage());
   // produit.setText(""+promo.getProduit().getId());
  //  categorie.setText(""+promo.getCategorie());
    //
     tfnom.setText(""+asso.getNom());   
     tfnumero.setText(""+asso.getNumero());   
     tfmail.setText(""+asso.getMail());   
     tfadresse.setText(""+asso.getAdresse());
     tfcode_postal.setText(""+asso.getCodePostal());
     tfville.setText(""+asso.getVille());
    }
 
    private void Update(MouseEvent event) {
       Association association = new Association(tfnom.getText(),Integer.parseInt(tfnumero.getText()),tfmail.getText(),tfadresse.getText(),Integer.parseInt(tfcode_postal.getText()),tfville.getText()); 
       AssociationService AssociationService = new AssociationService();
       AssociationService.update(association);
        System.out.println("updatee");
      //tabAssociation.refresh();
      // nom, numero, mail,  adresse,  CodePostal,  ville
       affiche();
       tabAssociation.refresh();
     affiche();
       /* try {
           fxml = FXMLLoader.load(getClass().getResource("/Views/EditAssociation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }*/
       
       
       
}
     public void delete(){
    AssociationService pdao =new AssociationService();
    pdao.delete(tabAssociation.getSelectionModel().getSelectedItem().getId());
    System.out.println(tabAssociation.getSelectionModel().getSelectedItem().getId());
    affiche();
    }

    @FXML
    private void supprimer1(MouseEvent event) {
        
           delete();
   tabAssociation.getItems().removeAll(tabAssociation.getSelectionModel().getSelectedItem());
   System.out.println(tabAssociation); 
        affiche();
    }

    @FXML
    private void Update1(MouseEvent event) {
         Association association = new Association(tfnom.getText(),Integer.parseInt(tfnumero.getText()),tfmail.getText(),tfadresse.getText(),Integer.parseInt(tfcode_postal.getText()),tfville.getText()); 
       AssociationService AssociationService = new AssociationService();
       affiche();
       AssociationService.update(association);
       tabAssociation.refresh();
      
       
    }



    @FXML
    private void Ajout(MouseEvent event) {
        
         String nom=tfnom.getText();
    String numero=tfnumero.getText();
    String mail=tfmail.getText();
    String adresse=tfadresse.getText();
    String code_postal=tfcode_postal.getText();
    String ville=tfville.getText();
    
    
   // public Promotion(int id, Date start_date, Date end_date, float pourcentage, Categorie categorie, Produit prodtuit)
    Association p=new Association(nom,Integer.parseInt(numero),mail,adresse,Integer.parseInt(code_postal),ville);
    AssociationService promotiondao=new AssociationService();
    
    
    JOptionPane.showMessageDialog(null, "ajouter un event   !");
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(new PhoneNumber("+21698773438"),
        new PhoneNumber("++12764098996"), 
        "une nouvelle association est ajouter  ").create();
                    
                    
    promotiondao.insert(p);
        
        
    }

    @FXML
    private void Excel(MouseEvent event) throws IOException {
           Writer writer = null;
                AssociationService sv = new AssociationService();
                ObservableList<Association> list = (ObservableList<Association>) sv.displayAll();
         try {
            //badel path fichier excel
            //C:\Users\Fares CHAKROUN\Desktop\PIDEVjava
            File file = new File("C:\\Users\\Fares CHAKROUN\\Desktop\\PIDEVjava\\Association.csv");
            writer = new BufferedWriter(new FileWriter(file));
            
            for (Association ev : list) {

                String text = ev.getNom()+" | " +ev.getNumero()+ " | " + ev.getMail()+ " | "+ev.getCodePostal()+" | "+ev.getVille()+ "\n";
                System.out.println(text);
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            writer.flush();
             writer.close();
             Alert alert= new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("excel");
        alert.setHeaderText(null);
        alert.setContentText("!!!excel exported!!!");
        alert.showAndWait();
        }
    }

    @FXML
    private void PDF(MouseEvent event) {
    }

    @FXML
    private void Imprimer(MouseEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
         printNode(tabAssociation);
    }
public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);
        
        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();
                
            }
        }
        node.getTransforms().remove(scale);
        
    }
    @FXML
    private void notif(MouseEvent event) {
    }
      
        }