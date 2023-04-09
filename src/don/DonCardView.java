package don;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Service.ServiceDon;
import Entities.Don;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DonCardView extends ListView<Don> {

    public DonCardView() {
         

    }

    DonCardView(List<Don> personnes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Custom ListCell for displaying Don objects
    private class DonListCell extends ListCell<Don> {
        
        private HBox hbox;
        private ImageView imageView;
        private VBox vBox;
        private Label nameLabel;
        private Label descriptionLabel;
        private Label quantiteLabel;
        private Label localisationLabel;
        private Label emailLabel;
        private Label numeroLabel;

        public DonListCell() {
            super();
            
            imageView = new ImageView();
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            
            nameLabel = new Label();
            nameLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            
            descriptionLabel = new Label();
            descriptionLabel.setStyle("-fx-font-size: 14px;");
            
            quantiteLabel = new Label();
            quantiteLabel.setStyle("-fx-font-size: 14px;");
            
            localisationLabel = new Label();
            localisationLabel.setStyle("-fx-font-size: 14px;");
            
            emailLabel = new Label();
            emailLabel.setStyle("-fx-font-size: 14px;");
            
            numeroLabel = new Label();
            numeroLabel.setStyle("-fx-font-size: 14px;");
            
            vBox = new VBox(nameLabel, descriptionLabel, quantiteLabel, localisationLabel, emailLabel, numeroLabel);
            vBox.setSpacing(5);
            
            hbox = new HBox(imageView, vBox);
            hbox.setSpacing(10);
        }

        @Override
        protected void updateItem(Don don, boolean empty) {
            super.updateItem(don, empty);
            if (empty || don == null) {
                setGraphic(null);
            } else {
                nameLabel.setText(don.getNameD());
                descriptionLabel.setText(don.getDescription());
                quantiteLabel.setText("Quantité: " + don.getQuantite());
                localisationLabel.setText("Localisation: " + don.getLocalisation());
                emailLabel.setText("Email: " + don.getEmail());
                numeroLabel.setText("Numéro: " + don.getNumero());
                imageView.setImage(new Image("file:src\\uploads\\" + don.getImage() + ".png"));
                
                Tooltip tooltip = new Tooltip(don.getDescription());
                nameLabel.setTooltip(tooltip);
                
                setGraphic(hbox);
            }
        }
    }
}
