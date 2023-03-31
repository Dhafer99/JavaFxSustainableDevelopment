/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetpidd;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author Souid
 */
public class ChatBotController implements Initializable{
      @FXML
    private Label lblTitle;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnSend;

    @FXML
    private Label lblOutput;

     @Override
    public void initialize(URL url, ResourceBundle rb){
        // Set button action
        btnSend.setOnAction(event -> {
            String input = txtInput.getText();
            System.out.println("input"+input);
            String response = getResponse(input);
            System.out.println(response);
            lblOutput.setText(response);
            txtInput.clear();
        });
    }
    
    private static final String[] RESPONSES = {
            "Hello",
            "How are you?",
            "Nice to meet you!",
            "What can I help you with today?",
            "I'm sorry, I don't understand. Can you please rephrase your question?"
    };

   

    private String getResponse(String input) {
        // Loop through responses and return a random one

        for (String response : RESPONSES) {
            if (input.toLowerCase().contains("salut ?")) {
                return "salut comment je peux t aider aujourd'hui ?";
            }
            if (input.toLowerCase().contains("dd") || input.toLowerCase().contains("developpement durable") || input.toLowerCase().contains("durable") ) {
                return "Le développement durable est une conception du développement qui s'inscrit dans une perspective de long terme et en intégrant les contraintes environnementales et sociales à l'économie";
            }
            if (input.toLowerCase().contains(input.toLowerCase())) {
                return response;
            }
            if (input.toLowerCase().contains(input.toLowerCase())) {
                return response;
            }
        }
        return RESPONSES[RESPONSES.length];
    }
}
