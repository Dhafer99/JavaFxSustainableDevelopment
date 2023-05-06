/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Services.ServiceDon;
import Back.Utils.MyCnx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.util.Duration;
   
/**
 *
 * @author Fares CHAKROUN
 */
public class ChartRefresher {
    private Timeline timeline;
    private PieChart azizpie;
    private PieChart gofipie;
    private ObservableList<PieChart.Data> pieChartData;
    public ChartRefresher(PieChart azizpie, PieChart gofipie) {
        this.azizpie = azizpie;
        this.gofipie = gofipie;
    }
    
    public void start() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(8), event -> {
            refreshCharts();
        }));
        timeline.play();
    }
    
    public void stop() {
        timeline.stop();
    }
    
    private void refreshCharts() {
        try {
            ServiceDon se = new ServiceDon();
            
            List<Back.Models.Don> donations = se.getAllDonations();
            
            int totalQuantity = donations.stream().mapToInt(Back.Models.Don::getQuantite).sum();
            
            Platform.runLater(() -> {
                azizpie.getData().clear();
                
                for (Back.Models.Don don : donations) {
                    String name = don.getNameD();
                    int quantity = don.getQuantite();
                    double percentage = ((double) quantity / totalQuantity) * 100;
                    
                    String label = String.format("%s (%d, %.2f%%)", name, quantity, percentage);
                    PieChart.Data data = new PieChart.Data(label, quantity / (double) percentage);
                    azizpie.getData().add(data);
                }
            });
            
            pieChartData = FXCollections.observableArrayList();
            Map<String, Integer> data = loadData();
            for (String category : data.keySet()) {
                int count = data.get(category);
                pieChartData.add(new PieChart.Data(category, count));
            }
            
            Platform.runLater(() -> {
                gofipie.setData(pieChartData);
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(ChartRefresher.class.getName()).log(Level.SEVERE, null, ex);
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
