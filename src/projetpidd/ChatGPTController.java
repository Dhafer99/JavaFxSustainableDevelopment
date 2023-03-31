/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpidd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import okhttp3.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatGPTController implements Initializable {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField inputField;

    private OkHttpClient client;

    private String apiKey;

    private static final String URL = "https://api.openai.com/v1/engines/davinci-codex/completions";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = new OkHttpClient();
        apiKey = "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";
    }

    @FXML
    public void handleSendButton(ActionEvent event) throws JSONException {
        String prompt = inputField.getText().trim();
        inputField.clear();

        int maxTokens = 10;

        String jsonBody = String.format("{\"prompt\": \"%s\", \"max_tokens\": %d}", prompt, maxTokens);

        RequestBody requestBody = RequestBody.create(JSON, jsonBody);

        Request request = new Request.Builder()
                .url(URL)
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            chatArea.appendText("Bot: " + getResponseFromJson(responseBody) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getResponseFromJson(String json) throws JSONException {
      String response = "";
    try {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject choice = choices.getJSONObject(0);
        response = choice.getString("text");
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return response;
    }
}
