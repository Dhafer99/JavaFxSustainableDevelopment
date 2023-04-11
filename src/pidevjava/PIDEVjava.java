/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;
import javafx.scene.image.Image;
import Models.Association;
import Models.categorieA;
import ServiceAssociation.AssociationService;
import ServiceAssociation.categorieService;
import java.awt.Graphics;
//import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Fares CHAKROUN
 */
public class PIDEVjava extends Application {
    
    private Stage primaryStage;
    private Parent parentPage;
   
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Genereux Dashboard");
        
        
        
       // parentPage = FXMLLoader.load(getClass().getResource("/Views/AjoutAssociation.fxml"));
   //  parentPage = FXMLLoader.load(getClass().getResource("/Views/sample.fxml"));
     parentPage = FXMLLoader.load(getClass().getResource("/Views/UI.fxml"));
     
        Scene scene = new Scene(parentPage);
       // Image icon = new Image(getClass().getResourceAsStream("..\image\Capture d’écran 2023-03-25 005418.png"));
       // this.primaryStage.getIcons().add(icon);
        
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Genereux Dashbord");
        this.primaryStage.getIcons().add(new Image("/image/c1.png"));
       
        this.primaryStage.show();}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);

   /*Association p =new Association();
    p.setId(55);
    p.setNom("khalil");
p.setNumero(54146901);
p.setMail("khalil");
p.setAdresse("khalil");
p.setCodePostal(2026);
   p.setVille("sidi bou");
   AssociationService promotiondao =new AssociationService();
   promotiondao.insert(p);*/
  /*categorieA p=new categorieA();
  p.setId(15);
  p.setNom("syrine");
  categorieService cs=new categorieService();*/
 // cs.update(p);

  
    
 
   
     }   
} 
   
    

