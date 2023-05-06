/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package don;

import Donneur.Service.ServiceDon;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import Donneur.Entities.Don ;

/**
 * FXML Controller class
 *
 * @author 21629
 */
public class ChartController implements Initializable {

    @FXML
    private PieChart pieChart;
    @FXML
    private Button returnBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceDon se = new ServiceDon();
            
            List<Don> donations = se.getAllDonations();
            
            int totalQuantity = donations.stream().mapToInt(Don::getQuantite).sum();
            
            for (Don don : donations) {
                String name = don.getNameD();
                int quantity = don.getQuantite();
                double percentage = ((double) quantity / totalQuantity) * 100;
                
                String label = String.format("%s (%d, %.2f%%)", name, quantity, percentage);
                PieChart.Data data = new PieChart.Data(label, quantity / (double) percentage);
                pieChart.getData().add(data);
            }   } catch (SQLException ex) {
            Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    @FXML
    private void returnA (ActionEvent event) throws IOException{
  FXMLLoader loader = new FXMLLoader(getClass().getResource("Operation.fxml"));
        ChartController aec = loader.getController();
        Parent root = loader.load();
        returnBtn.getScene().setRoot(root);
}
    
}
