/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidd;

import Model.User;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Souid
 */
public class ProjetPiDD extends Application {
    
    private static Stage stg ;
    public static User user=null ;
     @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;
       // primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("LoggedIn.fxml"));
        primaryStage.setTitle("Falcon");
        primaryStage.setScene(new Scene(root, 600*2, 400*2));
        primaryStage.show();
    }
 public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
