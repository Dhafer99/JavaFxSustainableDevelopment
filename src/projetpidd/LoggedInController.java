/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidd;

import Model.User;
import Services.ServiceUser;
import Services.dbconnection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
/**
 * FXML Controller class
 *
 * @author Souid
 */
public class LoggedInController implements Initializable {

    ProjetPiDD m ;
    @FXML
    private Label label_welcome;
    @FXML
    private Button button_logout;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> num_telephone;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private TableColumn<User, Integer> score;
    @FXML
    private TableColumn<User, Integer> nombre_etoile;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
  
   @FXML
   private ImageView profilePic;
   
    @FXML
    private Label lnom;
    @FXML
    private Label lprenom;
    @FXML
    private Label lemail;
    @FXML
    private Label lnumt;
    @FXML
    private Label letoile;
    @FXML
    private Label lscore;
    @FXML
    private Label ltype;
    @FXML
    private TextField tsearch;
    @FXML
    private TableColumn<User, Boolean> blocked;
    
    
    
    /**
     * Initializes the controller class.
     */
   @Override
      public void initialize(URL location,ResourceBundle resources)
      {
          addButtonToTable();
          showUsers();
          
          User user = projetpidd.ProjetPiDD.user;
          final String imageURI4 = new File(user.getImage()).toURI().toString();
          Image image = new Image(user.getImage());
        profilePic.setImage(image);
          System.out.println("CURRENT USER IMAGE"+user.getImage());
          addDeleteButtonToTable();
          
          ///recherche
          //recherche
           
       
      }
      
     public void showUsers(){
        ObservableList<User> list = ServiceUser.getInstance().getUserList();
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        
        type.setCellValueFactory(new PropertyValueFactory<User,String>("type"));
         num_telephone.setCellValueFactory(new PropertyValueFactory<User,String>("numTelephone"));
         nom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
         prenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
        score.setCellValueFactory(new PropertyValueFactory<User,Integer>("score"));
        nombre_etoile.setCellValueFactory(new PropertyValueFactory<User,Integer>("nb_etoile"));
        blocked.setCellValueFactory(new PropertyValueFactory<User,Boolean>("blocked"));
       


        table.setItems(list);
         FilteredList<User> filter = new FilteredList<>(list, b->true);
        tsearch.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(event -> {
            if(newValue.isEmpty() || newValue==null ) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(event.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else 
            return false;
        });
        });
        SortedList<User> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(table.comparatorProperty());
        
        table.setItems(sort);
     }
     
    
      @FXML
    private void logout() throws IOException {  
        ProjetPiDD m = new ProjetPiDD() ;
       
        m.changeScene("login.fxml");
        
    }
    @FXML
    private void toupdatescene()throws IOException {
        ProjetPiDD m = new ProjetPiDD() ;
         m.changeScene("editprofile.fxml");
        
    }
    @FXML
    private void tobot()throws IOException {
        ProjetPiDD m = new ProjetPiDD() ;
         m.changeScene("ChatBot.fxml");
        
    }
    private void toGPT()throws IOException {
        ProjetPiDD m = new ProjetPiDD() ;
         m.changeScene("ChatGPT.fxml");
        
    }
    private void addButtonToTable() {
        TableColumn<User, Void> BlockBtn = new TableColumn("Block");

        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<User, Void>() {
                    
                    
                    private final Button btn = new Button("Block");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            TableColumn<User, String> firstColumn = (TableColumn<User, String>) getTableView().getColumns().get(0);
                            String email = firstColumn.getCellData(getIndex());
                            System.out.println("selectedData: " + email);
                           try {
                                ServiceUser.getInstance().BlockUser(email);
                                ProjetPiDD m = new ProjetPiDD ();
                                m.changeScene("LoggedIn.fxml");
                            } catch (SQLException ex) {
                                Logger.getLogger(LoggedInController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(LoggedInController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        BlockBtn.setCellFactory(cellFactory);

        table.getColumns().add(BlockBtn);

    }
    private void addDeleteButtonToTable() {
        TableColumn<User, Void> BlockBtn = new TableColumn("Delete Account");

        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<User, Void>() {

                    private final Button btn = new Button("Delete Account");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            TableColumn<User, String> firstColumn = (TableColumn<User, String>) getTableView().getColumns().get(0);
                            String email = firstColumn.getCellData(getIndex());
                            System.out.println("selectedData: " + email);
                           try {
                                ServiceUser.getInstance().DeleteUser(email);
                                ProjetPiDD m = new ProjetPiDD ();
                                m.changeScene("LoggedIn.fxml");
                            } catch (SQLException ex) {
                                Logger.getLogger(LoggedInController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(LoggedInController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        BlockBtn.setCellFactory(cellFactory);

        table.getColumns().add(BlockBtn);

    }
    void searchUser(){
       String searchText = tsearch.getText();
    String sql = "SELECT email, nom, prenom, type, score, num_telephone, nb_etoile FROM user WHERE nom LIKE ? OR email LIKE ?";

    Connection cn = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    
    try {
        cn = Services.dbconnection.getInstance().getConnection();
        st = cn.prepareStatement(sql);
        st.setString(1, searchText);
        st.setString(2, searchText);
        rs = st.executeQuery();
        
        while (rs.next()) {
            lemail.setText(rs.getString("email"));
            lnom.setText(rs.getString("nom"));
            lprenom.setText(rs.getString("prenom"));
                    ltype.setText(rs.getString("type"));
                            lscore.setText(rs.getString("score"));
                                    lnumt.setText(rs.getString("num_telephone"));
                                            letoile.setText(rs.getString("nb_etoile"));
                    
            
            
            // Do something with the retrieved data (e.g. display it on screen)
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (cn != null) cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
}
