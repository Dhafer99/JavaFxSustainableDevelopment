/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Models.Reclamation;
import Back.Services.ReclamationService;
import java.net.URL;
import java.time.LocalDate;
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

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tab_reclamation;
    @FXML
    private TableColumn<Reclamation, ?> col_datereclamation;
    @FXML
    private TableColumn<Reclamation, String> col_etat;
    @FXML
    private TableColumn<Reclamation, String> col_motifdereclamation;
    @FXML
    private TableColumn<Reclamation, String> col_numtelephone;
    @FXML
    private TableColumn<Reclamation, String> col_email;
    @FXML
    private TableColumn<?, ?> col_categ;
    private TextField tf_etat;
    @FXML
    private TextField tf_motifdereclamation;
    @FXML
    private TextField tf_numtelephone;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField Search;
 private ReclamationListdata Ls = new ReclamationListdata();
  ReclamationService ps = new ReclamationService();
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
    public void show(){
       tab_reclamation.setItems(Ls.getPersons());
        col_datereclamation.setCellValueFactory(new PropertyValueFactory<>("data_reclamation"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        col_motifdereclamation.setCellValueFactory(new PropertyValueFactory<>("motif_de_reclamation"));
        col_numtelephone.setCellValueFactory(new PropertyValueFactory<>("num_telephone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));   
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         show();
       List<Reclamation> personnes = ps.displayAll();
       ObservableList<Reclamation> olp = FXCollections.observableArrayList(personnes);
       //search//
        FilteredList<Reclamation> filter = new FilteredList<>(olp, b->true);
        Search.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(event -> {
            if(newValue.isEmpty() || newValue==null ) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(event.getMotif_de_reclamation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else if (event.getEmail().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }else 
            return false;
        });
        });
        SortedList<Reclamation> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tab_reclamation.comparatorProperty());
        
        tab_reclamation.setItems(sort);
    }    
  public void delete(){
    ReclamationService pdao =new ReclamationService();
    pdao.delete(tab_reclamation.getSelectionModel().getSelectedItem().getId());
    System.out.println(tab_reclamation.getSelectionModel().getSelectedItem().getId());
    }
    @FXML
    private void handle(MouseEvent event) {
         Reclamation rec =tab_reclamation.getSelectionModel().getSelectedItem();
  
   
    
     id=rec.getId();
    // tf_etat.setText(""+rec.getEtat());   
     tf_motifdereclamation.setText(""+rec.getMotif_de_reclamation());   
     tf_numtelephone.setText(""+rec.getNum_telephone());
     tf_email.setText(""+rec.getEmail());
    }

    @FXML
    private void supp(MouseEvent event) {
         delete();
   tab_reclamation.getItems().removeAll(tab_reclamation.getSelectionModel().getSelectedItem());
   System.out.println(tab_reclamation);  
     show();
    }

    @FXML
    private void modif(MouseEvent event) {
        ReclamationService se = new ReclamationService();
             Reclamation p = new Reclamation();
             LocalDate currentdate = LocalDate.now();
        java.sql.Date data_reclamation = java.sql.Date.valueOf(currentdate);
        System.out.println(currentdate);
            p.setId(id);
            p.setData_reclamation(data_reclamation);
            p.setEtat("En Cours");
            p.setMotif_de_reclamation(tf_motifdereclamation.getText());
            //p.setImage_event(lien);
            p.setNum_telephone(Integer.valueOf(tf_numtelephone.getText()));
            p.setEmail(tf_email.getText());
            
            System.out.println(""+p);
           
            


          
            if (se.update(p))
            {
                JOptionPane.showMessageDialog(null, "Reclamation failed !");
            }

            JOptionPane.showMessageDialog(null, "Reclamation modifié !");
            
            //tab_reclamation.refresh();
            show();

        
    }
    
}
