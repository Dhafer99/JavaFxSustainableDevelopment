/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donneur.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class AddressToLatLng {
    public static JsonObject getLatLongFromAddress(String address) throws IOException {
    String url = "https://nominatim.openstreetmap.org/search?q=" 
        + URLEncoder.encode(address, "UTF-8") + "&format=json"; 
    
    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
    conn.setRequestMethod("GET");
    
    int responseCode = conn.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {
        InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
        JsonObject response = JsonParser.parseReader(inputStreamReader).getAsJsonArray().get(0).getAsJsonObject();
        return response;
    } else {
        throw new RuntimeException("Error response code: " + responseCode);
    }
}
    

}

