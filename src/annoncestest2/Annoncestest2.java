/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annoncestest2;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author Asus
 */
public class Annoncestest2 extends Application {
    private static Stage STG;
   // private Parent parentPage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.STG = primaryStage;
        primaryStage.setTitle("Hello World");
        
//      Parent parentPage = FXMLLoader.load(getClass().getResource("/gui/sample2.fxml"));
  // Parent parentPage = FXMLLoader.load(getClass().getResource("/gui/AfficherAnnonce.fxml"));
     // Parent parentPage = FXMLLoader.load(getClass().getResource("/gui/categorie.fxml"));
      Parent parentPage = FXMLLoader.load(getClass().getResource("/gui/Front.fxml"));
     
     
        Scene scene = new Scene(parentPage);
        primaryStage.setScene(scene);
        primaryStage.show();}
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
