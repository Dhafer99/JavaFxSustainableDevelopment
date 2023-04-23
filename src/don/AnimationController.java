/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author 21629
 */
public class AnimationController implements Initializable {
    private Stage primaryStage;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-001.jpg"))),
            new KeyFrame(Duration.seconds(1), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-002.jpg"))),
            new KeyFrame(Duration.seconds(2), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-003.jpg"))),
            new KeyFrame(Duration.seconds(3), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-004.jpg"))),
            new KeyFrame(Duration.seconds(4), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-005.jpg"))),
            new KeyFrame(Duration.seconds(5), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-006.jpg"))),
            new KeyFrame(Duration.seconds(6), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-007.jpg"))),
            new KeyFrame(Duration.seconds(7), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-008.jpg"))),
            new KeyFrame(Duration.seconds(8), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-009.jpg"))),
            new KeyFrame(Duration.seconds(9), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-010.jpg"))),
            new KeyFrame(Duration.seconds(10), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-011.jpg"))),
            new KeyFrame(Duration.seconds(11), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-012.jpg"))),
            new KeyFrame(Duration.seconds(13), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-013.jpg"))),
            new KeyFrame(Duration.seconds(14), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-014.jpg"))),
            new KeyFrame(Duration.seconds(15), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-015.jpg"))),
            new KeyFrame(Duration.seconds(16), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-017.jpg"))),
            new KeyFrame(Duration.seconds(17), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-018.jpg"))),
            new KeyFrame(Duration.seconds(18), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-019.jpg"))),
            new KeyFrame(Duration.seconds(19), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-020.jpg"))),
            new KeyFrame(Duration.seconds(20), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-021.jpg"))),
            new KeyFrame(Duration.seconds(21), new KeyValue(img.imageProperty(), new Image("animation/ezgif-frame-022.jpg"))));
        
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("smaple2.fxml"));
                    StackPane root = loader.load();
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AnimationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        timeline.play();
    }
    }    
    

