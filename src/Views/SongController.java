package Views;

import Entite.Reclamation;
import ServicesReclamations.ReclamationService;
import Views.AjoutModifController;
import Views.SampleController;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import utiles.MyCnx;

/**
 */
public class SongController {
 private WebView webView;
    private WebEngine webEngine;
Reclamation d = new Reclamation();
private Reclamation don;
    @FXML
    private Button BtnUpdate;
    @FXML
    private Button DeleteBtn;
    ReclamationService ps = new ReclamationService();
    @FXML
    private Label categ;
     HashMap<String, String> categoryUnits = new HashMap<>();
    @FXML
    private ImageView img;
    @FXML
    private Label Date;
    @FXML
    private Label etat;
    @FXML
    private Label motif;
    @FXML
    private Label numero;
    @FXML
    private Label email;
    @FXML
    private Button Sol;
//private Stage primaryStage;
     public void setData(Reclamation don) throws SQLException{
        /*categoryUnits.put("Appareil","Obj");
        categoryUnits.put("Argent", "D");
        categoryUnits.put("Nourriture", "Kg");
        categoryUnits.put("Habillement", "Piece");
        categoryUnits.put("Sang", "L");
        categoryUnits.put("Livres", "Livre");
        categoryUnits.put("Jouet", "Jouet");*/
     Date.setText(don.getData_reclamation().toString());
        etat.setText(don.getEtat());
        motif.setText(don.getMotif_de_reclamation());
        numero.setText(Integer.toString(don.getNum_telephone()));
        email.setText(don.getEmail());
   img.setImage(new Image("file:src\\uploads\\"+don.getImage()+".png"));

        
        if (don.getCategorie_rec().getId()== 0) {
    categ.setText("Catégorie non définie");
    System.out.println("err");
    return;
}

// Exécution de la requête SQL pour récupérer le nom de catégorie
Connection cnx = MyCnx.getInstance().getCnx();
PreparedStatement ps = cnx.prepareStatement("SELECT nom FROM categorie_rec WHERE id = ?");
ps.setInt(1, don.getCategorie_rec().getId());
ResultSet rs = ps.executeQuery();
if (rs.next()) {
    String nomCategorie = rs.getString(1);
           
if (nomCategorie != null) {
    categ.setText(nomCategorie);
}
else{System.out.println("Can't");}
} else {
    categ.setText("Catégorie introuvable");
}
   
   }
@FXML
    private void ToUpdate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutModif.fxml"));
 Parent root = loader.load();
        BtnUpdate.getScene().setRoot(root);
    AjoutModifController controller = loader.getController();
    controller.receiveObject(d);
   
    }
     public void receiveObject(Reclamation d) {
        this.d=d;
        don=d;
    }
@FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
    System.out.println(don);
        ps.delete(don.getId());
         FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        Controller aec = loader.getController();
        Parent root = loader.load();
        DeleteBtn.getScene().setRoot(root);
    }   

   @FXML
private void Solution(ActionEvent event) {
    Stage primaryStage = new Stage();
    VBox root = new VBox();
    root.setSpacing(10);
    root.setPadding(new Insets(10, 10, 10, 10));

    // Get the question to search for
    String question = don.getMotif_de_reclamation();
    System.out.println(question);

    // Create a new WebView and WebEngine
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();

    // Load the URL into the web engine
    if (!question.isEmpty()) {
        String url = "https://www.google.com/search?q=" + question;
        System.out.println("Loading URL: " + url);
        webEngine.load(url);
    }

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
        if (newState == Worker.State.SUCCEEDED) {
            String title = webEngine.getTitle();
            if (!title.startsWith("https://www.google.com/search")) {
                displayAnswer(title);
            }
        }
    });

    root.getChildren().addAll(webView);

    Scene scene = new Scene(root, 800, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle(don.getCategorie_rec().getNom());
    primaryStage.show();
}


    private void displayAnswer(String answer) {
        Stage stage = new Stage();

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10));

        Label answerLabel = new Label("Answer:");
        TextArea answerText = new TextArea(answer);
        answerText.setEditable(false);
        answerText.setPrefRowCount(5);
        answerText.setWrapText(true);

        root.getChildren().addAll(answerLabel, answerText);

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Solution");
        stage.show();
    }
}

