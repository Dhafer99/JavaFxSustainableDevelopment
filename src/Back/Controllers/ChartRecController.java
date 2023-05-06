/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Utils.MyCnx;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fares CHAKROUN
 */
public class ChartRecController implements Initializable {

    @FXML
    private PieChart pieChart;
    private ObservableList<PieChart.Data> pieChartData;
    private Stage primaryStage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            pieChartData = FXCollections.observableArrayList();
            Map<String, Integer> data = loadData();
            for (String category : data.keySet()) {
                int count = data.get(category);
                pieChartData.add(new PieChart.Data(category, count));
            }
            
            // Create the pie chart
           
            pieChart.setData(pieChartData);
          
        } catch (SQLException ex) {
            Logger.getLogger(ChartRecController.class.getName()).log(Level.SEVERE, null, ex);
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
}
