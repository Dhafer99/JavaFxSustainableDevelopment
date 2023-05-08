/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.dashbord;

import Back.Controllers.ChartRecController;
import Back.Controllers.ChartRefresher;
import Back.Services.ServiceDon;
import Back.Utils.MyCnx;
import don.CategoryController;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import projetpidd.ProjetPiDD;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class UIController implements Initializable {

    @FXML
    private AnchorPane root;
private Parent fxml;
 private MediaPlayer mediaPlayer;
  private MediaPlayer mediaPlayer1;
    @FXML
    private PieChart azizpie;
    @FXML
    private PieChart gofipie;
     private ObservableList<PieChart.Data> pieChartData;
    @FXML
    private MediaView mediaview2;
    @FXML
    private MediaView mediaview1;
    @FXML
    private MediaView mediaview;
    @FXML
    private Button Profile;
    @FXML
    private ImageView imageUser;
    @FXML
    private Label nomUser;
    
      private Configuration configuration ;
    
    private Thread recognitionThread;
    
    private LiveSpeechRecognizer recognizer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        imageUser.setFitWidth(50); // set the width of the image view
imageUser.setFitHeight(50); // set the height of the image view

// create a circle clip with a radius equal to half the width or height of the image view, whichever is smaller
Circle clip = new Circle();
clip.setCenterX(imageUser.getFitWidth() / 2);
clip.setCenterY(imageUser.getFitHeight() / 2);
clip.setRadius(Math.min(imageUser.getFitWidth(), imageUser.getFitHeight()) / 2);

imageUser.setClip(clip);
     imageUser.setImage(new Image(projetpidd.ProjetPiDD.user.getImage()));
     nomUser.setText(projetpidd.ProjetPiDD.user.getNom());
        mediaview.setFitWidth(220);
mediaview.setFitHeight(500);
mediaview1.setFitWidth(220);
mediaview1.setFitHeight(500);
mediaview2.setFitWidth(190);
mediaview2.setFitHeight(200);

//mediaview3.setFitWidth(100);
//mediaview3.setFitHeight(200);
try {
    Media media = new Media("file:///C:/Users/Souid/Desktop/javafxapp/ProjetPiDD/src/img/cc.mp4");
    Media media1 = new Media("file:///C:/Users/Souid/Desktop/javafxapp/ProjetPiDD/src/img/l.mp4");
    Media media2 = new Media("file:///C:/Users/Souid/Desktop/javafxapp/ProjetPiDD/src/img/f.mp4");
    //Media media3 = new Media("file:///D:/rf.mp4");
    
   
    
    
    
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
    MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
    //MediaPlayer mediaPlayer3 = new MediaPlayer(media3);
    mediaview.setMediaPlayer(mediaPlayer);
    mediaview1.setMediaPlayer(mediaPlayer1);
    mediaview2.setMediaPlayer(mediaPlayer2);
   // mediaview3.setMediaPlayer(mediaPlayer3);
    
    
    
    
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
/*Duration totalDuration3 = mediaPlayer3.getTotalDuration();
if (totalDuration3.isUnknown() || totalDuration3.lessThanOrEqualTo(Duration.ZERO)) {
    totalDuration3 = Duration.seconds(6); // Change 60 to the duration of your longest video
}*/

Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), e -> {
        
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer1.seek(Duration.ZERO);
        mediaPlayer2.seek(Duration.ZERO);
        
        mediaPlayer.play();
        mediaPlayer1.play();
        mediaPlayer2.play();
       // mediaPlayer3.play();
    }), new KeyFrame(totalDuration));

timeline.setCycleCount(Timeline.INDEFINITE);
timeline.play();

} catch (Exception ex) {
    ex.printStackTrace();
}
        // chart
      try {
            ServiceDon se = new ServiceDon();
            
            List<Back.Models.Don> donations = se.getAllDonations();
            
            int totalQuantity = donations.stream().mapToInt(Back.Models.Don::getQuantite).sum();
            
            for (Back.Models.Don don : donations) {
                String name = don.getNameD();
                int quantity = don.getQuantite();
                double percentage = ((double) quantity / totalQuantity) * 100;
                
                String label = String.format("%s (%d, %.2f%%)", name, quantity, percentage);
                PieChart.Data data = new PieChart.Data(label, quantity / (double) percentage);
                azizpie.getData().add(data);
            }   } catch (SQLException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pieChartData = FXCollections.observableArrayList();
            Map<String, Integer> data = loadData();
            for (String category : data.keySet()) {
                int count = data.get(category);
                pieChartData.add(new PieChart.Data(category, count));
            }
            
            // Create the pie chart
           
            gofipie.setData(pieChartData);
          
          
        } catch (SQLException ex) {
            Logger.getLogger(ChartRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }    

    @FXML
    private void Reclamation(MouseEvent event) {
        
        try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/HomeReclamation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
        
    }

    @FXML
    private void association(MouseEvent event) {
        
         try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/HomeAssociation.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
        
    }

    @FXML
    private void Annonces(MouseEvent event) {
          try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/HomeAnnonces.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void Don(MouseEvent event) {
         try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/HomeDon.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void event(MouseEvent event) {
        
         try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/HomeEvenement.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    private void user(MouseEvent event) throws IOException {
        //Integration m = new Integration();
        //m.changeScene("/Views/LoggedIn.fxml");
          try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/LoggedIn.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void utilisateur(MouseEvent event) {
        
        
          try {
           fxml = FXMLLoader.load(getClass().getResource("/Back/Views/LoggedIn.fxml"));
           root.getChildren().removeAll();
           root.getChildren().setAll(fxml);
        } catch (IOException ex) {
        }
    }
     private Map<String, Integer> loadData() throws SQLException {
        // TODO: Load the data from the Reclamation and categorie_rec tables in your database

        Map<String, Integer> data = new HashMap<>();
        String sql = "SELECT c.nom AS category, COUNT(*) AS count "
                + "FROM reclamation r "
                + "INNER JOIN categorie_rec c ON r.categorie_rec_id = c.id "
                + "GROUP BY c.nom";
        Connection cnx = MyCnx.getInstance().getCnx();
        PreparedStatement stmt = cnx.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String category = rs.getString("category");
            int count = rs.getInt("count");
            data.put(category, count);
        }
        rs.close();
        stmt.close();

        return data;
    }

    @FXML
    private void goToProfile(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/projetpidd/UserProfile.fxml"));
        CategoryController aec = loader.getController();
        Parent root = loader.load();
        Profile.getScene().setRoot(root);  
    }

    @FXML
     public void startListening() throws URISyntaxException, IOException {
         
            Task<Void> task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            
            try {
            configuration = new Configuration();
            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
            configuration.setDictionaryPath("/Users/Souid/Desktop/javafxapp/voicereco/src/voiceres/7190.dic");
            configuration.setLanguageModelPath("/Users/Souid/Desktop/javafxapp/voicereco/src/voiceres/7190.lm");

            recognizer = new LiveSpeechRecognizer(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
            
            
            recognizer.startRecognition(true);

            SpeechResult result;
            while ((result = recognizer.getResult()) != null) {
                String transcription = result.getHypothesis();
                System.out.println("++++++++++++++++++++++++command :" + transcription);

                if (transcription.equalsIgnoreCase("open user interface")) {
                  ProjetPiDD v = new ProjetPiDD();
                  v.changeScene("/projetpidd/UserProfile.fxml");
                }
                if (transcription.equalsIgnoreCase("open admin interface")) {
                  ProjetPiDD v = new ProjetPiDD();
                  v.changeScene("/Back/Views/UI.fxml");
                }
                 if (transcription.equalsIgnoreCase("open valorant")) {
                 String command = "C:\\Games\\Phasmophobia.v0.8.0.8\\Phasmophobia.v0.8.0.8\\Launcher.exe"; // full path to the exe file
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec(command);
                }
            }

            return null;
        }
    };

    recognitionThread = new Thread(task);
    recognitionThread.setDaemon(true);
    recognitionThread.start();
           
            
        
    }
}
