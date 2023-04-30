/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author 21629
 */
public class FrontController implements Initializable {

    @FXML
    private AnchorPane route;
    @FXML
    private TextField text;
    @FXML
    private MediaView mediaView1;
    @FXML
    private MediaView mediaView2;
    @FXML
    private MediaView mediaView3;
private Parent fxml;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mediaView1.setFitWidth(220);
mediaView1.setFitHeight(500);
mediaView2.setFitWidth(220);
mediaView2.setFitHeight(500);
mediaView3.setFitWidth(190);
mediaView3.setFitHeight(200);
try {
    String filePath = "C:\\Users\\ghofr\\Desktop\\Java\\GestionReclamation\\src\\img\\f.mp4";
String uriString = "file:///" + filePath.replace("\\", "/");
    Media media = new Media(uriString);
     String filePath1 = "C:\\Users\\ghofr\\Desktop\\Java\\GestionReclamation\\src\\img\\l.mp4";
String l = "file:///" + filePath1.replace("\\", "/");
    Media media1 = new Media(l);
    String filePath2 = "C:\\Users\\ghofr\\Desktop\\Java\\GestionReclamation\\src\\img\\cc.mp4";
String cc = "file:///" + filePath2.replace("\\", "/");
    Media media2 = new Media(cc);
   // Media media1 = new Media("file:src\\img\\l.mp4");
    //Media media2 = new Media("file:src\\img\\cc.mp4");

    
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
    MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
    mediaView1.setMediaPlayer(mediaPlayer);
    mediaView2.setMediaPlayer(mediaPlayer1);
    mediaView3.setMediaPlayer(mediaPlayer2);
    // Create a Timeline object to synchronize the playback of both videos
    Duration totalDuration = mediaPlayer1.getTotalDuration();
if (totalDuration.isUnknown() || totalDuration.lessThanOrEqualTo(Duration.ZERO)) {
    totalDuration = Duration.seconds(6); // Change 60 to the duration of your longest video
}    
Duration totalDuration1 = mediaPlayer.getTotalDuration();
if (totalDuration1.isUnknown() || totalDuration1.lessThanOrEqualTo(Duration.ZERO)) {
    totalDuration1 = Duration.seconds(6); // Change 60 to the duration of your longest video
}
Duration totalDuration2 = mediaPlayer2.getTotalDuration();
if (totalDuration2.isUnknown() || totalDuration2.lessThanOrEqualTo(Duration.ZERO)) {
    totalDuration2 = Duration.seconds(6); // Change 60 to the duration of your longest video
}

Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), e -> {
        
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer1.seek(Duration.ZERO);
        mediaPlayer2.seek(Duration.ZERO);
        
        mediaPlayer.play();
        mediaPlayer1.play();
        mediaPlayer2.play();
    }), new KeyFrame(totalDuration));

timeline.setCycleCount(Timeline.INDEFINITE);
timeline.play();

} catch (Exception ex) {
    ex.printStackTrace();
}

    }    

    @FXML
    private void Don(MouseEvent event) {
        try {
           fxml = FXMLLoader.load(getClass().getResource("DonF.fxml"));
           route.getChildren().removeAll();
           route.getChildren().setAll(fxml);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
      /*public  void DonG() {
        try {
           fxml = FXMLLoader.load(getClass().getResource("DonF.fxml"));
           route.getChildren().removeAll();
           route.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }*/

    
    
    
       
    
}
