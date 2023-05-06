/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Associations.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class WebController implements Initializable {

    @FXML
    private WebView WebView;
    @FXML
    private TextField textfield;
    @FXML
    private Button zoombt;
    @FXML
    private TextArea ZoomText;
public WebEngine engine;
private WebHistory history;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        engine = WebView.getEngine();
    }    

    @FXML
    private void loadpage(ActionEvent event) {
    engine.load("http://"+textfield.getText());
     // engine.load("http://127.0.0.1:8000/association/choix");
      ZoomText.visibleProperty().set(false);
    }

    @FXML
    private void page(MouseEvent event) {
                   engine.load("http://127.0.0.1:8000/association/choix");

    }

    @FXML
    private void google(MouseEvent event) {
    engine.load("https://www.google.com");
    }

    @FXML
    private void refresh(ActionEvent event) {
    
       engine.reload();    
        ZoomText.visibleProperty().set(false);
    }

    @FXML
    private void zooIn(ActionEvent event) {
    WebView.setZoom(1.25);
                
                          WebView.setZoom(WebView.getZoom()+0.25); 
    }

    @FXML
    private void zoomOut(ActionEvent event) {
    ZoomText.visibleProperty().set(false);
          if(WebView.getZoom()> 0.50){
              WebView.setZoom(WebView.getZoom()-0.25);
              
          
          }else{
              ZoomText.visibleProperty().set(true);
              
          
          }
    }

    @FXML
    private void historique(ActionEvent event) {
     history = engine.getHistory();
       ObservableList<WebHistory.Entry > entries = history.getEntries();
       for(WebHistory.Entry entry : entries){
           System.out.println(entry);
           
       }
       
        
    }
    }
    

