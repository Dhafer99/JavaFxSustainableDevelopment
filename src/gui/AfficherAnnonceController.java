
package gui;

import entities.Annonces;
import services.AnnonceService;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;



public class AfficherAnnonceController implements Initializable {
    
    @FXML
    private TableView<Annonces> tab_annonce;
    @FXML
    private TableColumn<Annonces,?> col_datepublication;
    @FXML
    private TableColumn<Annonces, String> col_adresse;
    @FXML
    private TableColumn<Annonces, String> col_image;
    @FXML
    private TableColumn<Annonces, String> col_description;
    
    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_adresse;
    @FXML
    private TextField recherche;
    //image
    @FXML
    private DatePicker picker_date;
    private int id;
    AnnonceService ps = new AnnonceService();
    private Listdata Ls = new Listdata();
    

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
        
        affiche();
       tab_annonce.setItems(Ls.getPersons());
       col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
       // col_datepublication.setCellValueFactory(new PropertyValueFactory<>("date_publication"));
         col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        List<Annonces> personnes = ps.displayAll();
       ObservableList<Annonces> olp = FXCollections.observableArrayList(personnes);
       
       //recherche
        FilteredList<Annonces> filter = new FilteredList<>(olp, b->true);
        recherche.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(event -> {
            if(newValue.isEmpty() || newValue==null ) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(event.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else 
            return false;
        });
        });
        SortedList<Annonces> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tab_annonce.comparatorProperty());
        
        tab_annonce.setItems(sort);
       
       // image
      
    }   
    
    public void affiche(){
     
    
       
       
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_datepublication.setCellValueFactory(new PropertyValueFactory<>("date_publication"));
        tab_annonce.setItems(Ls.getPersons());
       
   }
    
    public void delete(){
    AnnonceService pdao =new AnnonceService();
    pdao.delete(tab_annonce.getSelectionModel().getSelectedItem());
    System.out.println(tab_annonce.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    private void supp(MouseEvent event) {
       delete();
   tab_annonce.getItems().removeAll(tab_annonce.getSelectionModel().getSelectedItem());
   System.out.println(tab_annonce);  
    }

    @FXML
    private void handle(MouseEvent event) {
        Annonces rec =tab_annonce.getSelectionModel().getSelectedItem();
  
   
    
     id=rec.getId();
     tf_description.setText(""+rec.getDescription());   
     tf_adresse.setText(""+rec.getAdresse());   
     //image
    }

    @FXML
    private void modif(MouseEvent event) {
        AnnonceService se = new AnnonceService();
             Annonces p = new Annonces();
             p=tab_annonce.getSelectionModel().getSelectedItem();
            p.setId(id);
            p.setDate_publication( java.sql.Date.valueOf(picker_date.getValue()));
            p.setDescription(tf_description.getText());
            p.setAdresse(tf_adresse.getText());
            //p.setImage_event(lien);
           
            
            System.out.println(""+p);
           
            


          
            if (se.update(p))
            {
                JOptionPane.showMessageDialog(null, "Annonce failed !");
            }

            JOptionPane.showMessageDialog(null, "Annonce modifiée !");
            
            tab_annonce.refresh();

     
      
              
    }
    
}
