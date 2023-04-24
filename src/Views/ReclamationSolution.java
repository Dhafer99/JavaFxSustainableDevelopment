/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
/*
import com.theokanning.openai.gpt3.Gpt3;
import com.theokanning.openai.gpt3.models.CompletionRequest;
import com.theokanning.openai.gpt3.models.CompletionResponse;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.concurrent.ExecutionException;

public class ReclamationSolution extends Application {

    private Gpt3 gpt3;
    private String apiKey = "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";
    private String engine = "davinci";

    @Override
    public void start(Stage primaryStage) {
        // create a TextArea for the reclamation input
        TextArea reclamationInput = new TextArea();
        reclamationInput.setPromptText("Enter your reclamation here");

        // create a Button to generate the solution
        Button generateSolutionButton = new Button("Generate Solution");
        generateSolutionButton.setOnAction(event -> {
            String reclamation = reclamationInput.getText();
            String solution = generateSolution(reclamation);
            showSolution(solution);
        });

        // create a VBox to hold the controls
        VBox root = new VBox(10);
        root.getChildren().addAll(reclamationInput, generateSolutionButton);

        // set up the scene and show the stage
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String generateSolution(String reclamation) {
        // generate the solution using the GPT-3 API
        CompletionRequest request = CompletionRequest.builder()
                .prompt(reclamation)
                .maxTokens(100)
                .temperature(0.5)
                .build();
        CompletionResponse response = gpt3.complete(engine, request).get();
        return response.getChoices().get(0).getText();
    }

    private void showSolution(String solution) {
        // create a new window to display the solution
        Stage solutionStage = new Stage();
        solutionStage.setTitle("Solution");

        // create a Label to display the solution text
        Label solutionLabel = new Label(solution);

        // create a VBox to hold the Label
        VBox root = new VBox(10);
        root.getChildren().add(solutionLabel);

        // set up the scene and show the stage
        Scene scene = new Scene(root, 400, 400);
        solutionStage.setScene(scene);
        solutionStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        // initialize the GPT-3 API client
        gpt3 = new Gpt3(apiKey);
        super.init();
    }

    @Override
    public void stop() throws Exception {
        // shut down the GPT-3 API client
        gpt3.close();
        super.stop();
    }
}*/



