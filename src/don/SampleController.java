/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import Service.ServiceDon;
import java.util.List;
import Entities.Don;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author 21629
 */
public class SampleController implements Initializable {

    @FXML
    private GridPane citiesGrid;
ServiceDon s= new ServiceDon();
    @FXML
    private Label NbDon;
    @FXML
    private VBox ChangeMe;
    @FXML
    private ImageView AddBtn;
    private Parent fxml;
    @FXML
    private ScrollPane Change;
    @FXML
    private Button addBtn;
    @FXML
    private Button filter;
    @FXML
    private TextField Text;
    @FXML
    private Button Menubtn;
    private ObservableList<Don> originalDonList;
private ObservableList<Don> filteredDonList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
        // Get the list of Don objects
        originalDonList = FXCollections.observableArrayList(s.afficher());
        filteredDonList = FXCollections.observableArrayList(originalDonList);

        // Set the number of Don objects label
        NbDon.setText(originalDonList.size() + " Don Disponible");

        // Populate the GridPane with the card views
        updateUI(filteredDonList);

        // Register an event listener on the search field
        Text.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                filterDonList(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }catch (SQLException ex) {
        Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
    }
        /* try {
        List<Don> personnes = s.afficher();
        ObservableList<Don> olp = FXCollections.observableArrayList(personnes);
        NbDon.setText(String.valueOf(personnes.size())+" Don Disponible");
        int column=0;
        int row = 1;
        for (Don d :personnes ){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("CardView.fxml"));
        Pane pane = fxmlLoader.load();
        CardViewController cardViewController = fxmlLoader.getController();
        cardViewController.setData(d);
        CardViewController controller = fxmlLoader.getController();
        controller.receiveObject(d);
        if(column == 4){
        column = 0;
        ++row;
        }
        citiesGrid.add(pane, column++, row);
        GridPane.setMargin(pane,new Insets(20,20,20,20));
        }
        } catch (SQLException ex) {
        Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {          
        Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }*/           

     
           /* try {
            List<Don> personnes = s.afficher();
            ObservableList<Don> olp = FXCollections.observableArrayList(personnes);
            NbDon.setText(String.valueOf(personnes.size())+" Don Disponible");
            int column=0;
            int row = 1;
            for (Don d :personnes ){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("CardView.fxml"));
            
            Pane pane = fxmlLoader.load();
            
            CardViewController cardViewController = fxmlLoader.getController();
            cardViewController.setData(d);
            
            CardViewController controller = fxmlLoader.getController();
            controller.receiveObject(d);
            if(column == 4){
            column = 0;
            ++row;
            }
            citiesGrid.add(pane, column++, row);
            GridPane.setMargin(pane,new Insets(20,20,20,20));
            
            
            }
            } catch (SQLException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
     

        

    }
    private void filterDonList(String query) throws SQLException {
    filteredDonList.clear();
    for (Don d : originalDonList) {
        if (d.getNameD().toLowerCase().contains(query.toLowerCase())) {
            filteredDonList.add(d);
        }
    }
    // Update the UI with the filtered list
    updateUI(filteredDonList);
}

private void updateUI(ObservableList<Don> donList) throws SQLException {
    /*citiesGrid.getChildren().clear();
    int column = 0;
    int row = 1;
    for (Don d : donList) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("CardView.fxml"));

        try {
            Pane pane = fxmlLoader.load();
            CardViewController cardViewController = fxmlLoader.getController();
            cardViewController.setData(d);
CardViewController controller = fxmlLoader.getController();
            controller.receiveObject(d);
            if (column == 4) {
                column = 0;
                ++row;
            }
            citiesGrid.add(pane, column++, row);
            GridPane.setMargin(pane, new Insets(20, 20, 20, 20));

        } catch (IOException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
@FXML
private void AddNew(ActionEvent event) throws IOException{

         FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUpdate.fxml"));
    AddUpdateController aec = loader.getController();
        Parent root = loader.load();
        addBtn.getScene().setRoot(root);
  
}

    @FXML
    private void filter(ActionEvent event) throws SQLException, IOException {
        List<Don> personnes = s.afficher();

// sort the list by quantity in descending order
Collections.sort(personnes, new Comparator<Don>() {
    @Override
    public int compare(Don d1, Don d2) {
        return Integer.compare(d2.getQuantite(), d1.getQuantite());
    }
});

NbDon.setText(String.valueOf(personnes.size())+" Don Disponible");

// display the sorted Don objects in the CardView
int column = 0;
int row = 1;
for (Don d : personnes) {
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("CardView.fxml"));
    Pane pane = fxmlLoader.load();
    CardViewController cardViewController = fxmlLoader.getController();
    cardViewController.setData(d);
     CardViewController controller = fxmlLoader.getController();
    controller.receiveObject(d);
    if (column == 3) {
        column = 0;
        ++row;
    }
    citiesGrid.add(pane, column++, row);
    GridPane.setMargin(pane,new Insets(20,20,20,20));
}

    }

    @FXML
    private void Menu(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Operation.fxml"));
    AddUpdateController aec = loader.getController();
        Parent root = loader.load();
        Menubtn.getScene().setRoot(root);
    }*/
   
}  
}
