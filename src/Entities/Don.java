/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author 21629
 */
public class Don {
    private int id;
    private String NameD;
    private int quantite;
    private String description;
    private String localisation;
    private String Image;
    private String email;
    private int Numero;
private static Stage stg ;

    public Don() {
    }

    public Don(int id, String NameD, int quantite, String description, String localisation, String Image, String email, int Numero) {
        this.id = id;
        this.NameD = NameD;
        this.quantite = quantite;
        this.description = description;
        this.localisation = localisation;
        this.Image = Image;
        this.email = email;
        this.Numero = Numero;
    }

    public Don(String NameD, int quantite, String description, String localisation, String Image, String email, int Numero) {
        this.NameD = NameD;
        this.quantite = quantite;
        this.description = description;
        this.localisation = localisation;
        this.Image = Image;
        this.email = email;
        this.Numero = Numero;
    }

    public Don(String NameD, int quantite) {
        this.NameD = NameD;
        this.quantite = quantite;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameD() {
        return NameD;
    }

    public void setNameD(String NameD) {
        this.NameD = NameD;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    @Override
    public String toString() {
        return "Don{" + "id=" + id + ", NameD=" + NameD + ", quantite=" + quantite + ", description=" + description + ", localisation=" + localisation + ", Image=" + Image + ", email=" + email + ", Numero=" + Numero + '}';
    }
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }
}
