/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class WikipediaSearch extends Application {

    private static final String WIKIPEDIA_API_URL =
            "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro&explaintext&titles=";

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create a TextField to input the search term
        TextField searchField = new TextField();
        searchField.setPromptText("Enter a search term");

        // Create a Button to initiate the search
        Button searchButton = new Button("Search");
        searchButton.setDefaultButton(true);

        // Create an HBox to hold the TextField and Button
        HBox inputBox = new HBox();
        inputBox.getChildren().addAll(searchField, searchButton);
        inputBox.setHgrow(searchField, Priority.ALWAYS);

        // Create a TextArea to display the response
        TextArea responseTextArea = new TextArea();
        responseTextArea.setEditable(false);

        // Create a BorderPane to hold the input and output nodes
        BorderPane root = new BorderPane();
        root.setTop(inputBox);
        root.setCenter(responseTextArea);

        // Create a Scene with the BorderPane as the root node
        Scene scene = new Scene(root, 600, 400);

        // Set the title of the window
        primaryStage.setTitle("Wikipedia Search");

        // Set the Scene of the window
        primaryStage.setScene(scene);

        // Show the window
        primaryStage.show();

        // Set an action for the search button
        searchButton.setOnAction(event -> {
            String searchTerm = searchField.getText();
            String response = "";
            try {
                response = searchWikipedia(searchTerm);
            } catch (Exception e) {
                response = "Error: " + e.getMessage();
            }
            responseTextArea.setText(response);
        });
    }

    private String searchWikipedia(String searchTerm) throws Exception {

        // Encode the search term for use in the URL
        String encodedSearchTerm = URLEncoder.encode(searchTerm, "UTF-8");

        // Construct the URL for the Wikipedia API request
        String urlStr = WIKIPEDIA_API_URL + encodedSearchTerm;

        // Create a URL object from the string
        URL url = new URL(urlStr);

        // Create a BufferedReader to read the response from the API
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        // Read the response into a StringBuilder
        StringBuilder responseBuilder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseBuilder.append(inputLine);
        }
        in.close();

        // Convert the response to a JSON object
        JSONObject responseJson = new JSONObject(responseBuilder.toString());

        // Extract the page information from the JSON object
        JSONObject pagesJson = responseJson.getJSONObject("query").getJSONObject("pages");
        String pageId = pagesJson.keys().next();
        JSONObject pageJson = pagesJson.getJSONObject(pageId);
        String extract = pageJson.getString("extract");

        // Return the extract as the response
        return extract;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

