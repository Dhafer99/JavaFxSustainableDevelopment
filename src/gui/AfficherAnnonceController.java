
package gui;

import entities.Annonces;
import services.AnnonceService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
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
    private Listdata Ls = new Listdata();
    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_adresse;
    //image
    @FXML
    private DatePicker picker_date;
    private int id;

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
       tab_annonce.setItems(Ls.getPersons());
        col_datepublication.setCellValueFactory(new PropertyValueFactory<>("date_publication"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
       // image
      
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
            p.setId(id);
            p.setDate_Publication( java.sql.Date.valueOf(picker_date.getValue()));
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
