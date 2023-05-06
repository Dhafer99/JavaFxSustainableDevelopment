/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Models.Category_d;
import Back.Services.ServiceCategorieDon;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class CategorieDonController implements Initializable {

    @FXML
    private TextField tfname;
    @FXML
    private TableView<Category_d> tvdon;
    @FXML
    private TableColumn<Category_d, String> tvname;
    @FXML
    private TextField filtertext;
    @FXML
    private Button Menubtn;
ServiceCategorieDon ps = new ServiceCategorieDon();
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
        // TODO
         afficher();
         
         } 
    
       public void afficher(){
       try {
            // TODO
            
            List<Category_d> personnes = ps.afficher();
           System.out.println(personnes);
            ObservableList<Category_d> olp = FXCollections.observableArrayList(personnes);
            tvdon.setItems(olp);
            tvname.setCellValueFactory(new PropertyValueFactory("name_ca"));
      
              FilteredList<Category_d> filter = new FilteredList<>(olp, b->true);
        filtertext.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(event -> {
            if(newValue.isEmpty() || newValue==null ) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(event.getName_ca().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            }else 
            return false;
        });
        });
        SortedList<Category_d> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tvdon.comparatorProperty());
        
        tvdon.setItems(sort);
/*FilteredList<Don> filteredData = new FilteredList<Don>(olp, b -> true);
filtertext.textProperty().addListener((observable,oldValue,newValue)->{
filteredData.setPredicate(Don->{
if(newValue.isEmpty()||newValue== null){return true;}
String filtertext = newValue.toLowerCase();
if(Don.getNameD().toLowerCase().indexOf(filtertext))
})
});*/

        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
    
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
         Category_d don= tvdon.getSelectionModel().getSelectedItem();
       id=don.getId();
        tfname.setText(don.getName_ca()+"");
    }

    @FXML
    private void AjouterDon(ActionEvent event) {
        ServiceCategorieDon se = new ServiceCategorieDon();
            
            Category_d p = new Category_d();
            p.setName_ca(tfname.getText());
           


          
            se.ajouter(p);
           
            JOptionPane.showMessageDialog(null, "category ajouté !");
                        afficher();
    }

    @FXML
    private void ModifierEvent(ActionEvent event) throws SQLException {
         ServiceCategorieDon se = new ServiceCategorieDon();
            
            Category_d p = new Category_d();
                        p.setName_ca(tfname.getText());

p.setId(id);

          
            se.modifier(p);

            JOptionPane.showMessageDialog(null, "Category Modifié !");
            afficher();
    }

    @FXML
    private void deleteEvent(MouseEvent event) throws SQLException {
         Category_d don= tvdon.getSelectionModel().getSelectedItem();
       
ps.supprimer(don);
        afficher();
    }

     @FXML
    private void Menu(ActionEvent event) throws IOException {
     /*     FXMLLoader loader = new FXMLLoader(getClass().getResource("Operation.fxml"));
    AddUpdateController aec = loader.getController();
        Parent root = loader.load();
        Menubtn.getScene().setRoot(root);*/
    }

  
    
}
