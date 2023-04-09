/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author ghofrane
 */
import java.sql.Date;

public class Reclamation {
    private int id;
    public Date data_reclamation; 
    private String etat;
    private String motif_de_reclamation;
    private String image;
    public int num_telephone;
    public String email;

    public Reclamation(int id, Date data_reclamation, String etat, String motif_de_reclamation, String image, int num_telephone, String email) {
        this.id = id;
        this.data_reclamation = data_reclamation;
        this.etat = etat;
        this.motif_de_reclamation = motif_de_reclamation;
        this.image = image;
        this.num_telephone = num_telephone;
        this.email = email;
    }

    public Reclamation() {
    }

    public Reclamation(Date data_reclamation, String etat, String motif_de_reclamation, String image, int num_telephone, String email) {
        this.data_reclamation = data_reclamation;
        this.etat = etat;
        this.motif_de_reclamation = motif_de_reclamation;
        this.image = image;
        this.num_telephone = num_telephone;
        this.email = email;
    }
    

    public int getId() {
        return id;
    }

    public Date getData_reclamation() {
        return data_reclamation;
    }

    public String getEtat() {
        return etat;
    }

    public String getMotif_de_reclamation() {
        return motif_de_reclamation;
    }

    public String getImage() {
        return image;
    }

    public int getNum_telephone() {
        return num_telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData_reclamation(Date data_reclamation) {
        this.data_reclamation = data_reclamation;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setMotif_de_reclamation(String motif_de_reclamation) {
        this.motif_de_reclamation = motif_de_reclamation;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNum_telephone(int num_telephone) {
        this.num_telephone = num_telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", data_reclamation=" + data_reclamation + ", etat=" + etat + ", motif_de_reclamation=" + motif_de_reclamation + ", image=" + image + ", num_telephone=" + num_telephone + ", email=" + email + '}';
    }
    
    
    
}
