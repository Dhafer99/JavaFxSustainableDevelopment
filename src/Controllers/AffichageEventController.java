/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Evenement;
import Services.EvenementService;
import Utils.MyCnx;
import java.net.URL;
import java.sql.Connection;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
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
    @FXML
    private TableColumn<Evenement, Button> delete;
    @FXML
    private Button modifier;
    @FXML
    private Button CategBT;
    
    @FXML
    private Button FrontBT;
    @FXML
    private Button qrcode;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            List<Evenement> personnes = ps.afficher();
            ObservableList<Evenement> olp = FXCollections.observableArrayList(personnes);
             
            
          nomTv.setCellValueFactory(new PropertyValueFactory("nom_event"));
            LieuTv.setCellValueFactory(new PropertyValueFactory("localisation"));
            //DateDebTv.setCellValueFactory(new PropertyValueFactory("date_debut"));
             //DateFinTv.setCellValueFactory(new PropertyValueFactory("date_fin"));
             this.deleteEvent();
            // this.pdf();
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
    private void GoToAddEvent(ActionEvent event) {
    }

    @FXML
    private void GoToEditEvent(ActionEvent event) {
    }

    @FXML
    private void GoToCategories(ActionEvent event) {
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageCategE.fxml"));
        AffichageCategEController aec = loader.getController();
        Parent root = loader.load();
        CategBT.getScene().setRoot(root);
        */
    }

    @FXML
    private void qrcode(ActionEvent event) {
    }

    @FXML
    private void GoToFront(ActionEvent event) {
    }

    @FXML
    private void showSelectedEvent(MouseEvent event) throws SQLException {
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
Connection cnx = MyCnx .getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT nom_categ_event FROM categorie_event WHERE id = ?");
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
        public void updateTable() throws SQLException {
    ObservableList<Evenement> list = FXCollections.observableArrayList(ps.afficher());
    EventsTv.setItems(list);
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
    
    
    
}
