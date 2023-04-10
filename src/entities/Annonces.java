/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Annonces {
     private int id;
    private String adresse,description,image;
            private Date date_publication;
    
 
    
      public Annonces() {
    }

   /* public Annonces(String adresse, String description) {
        this.adresse = adresse;
        this.description = description;
    }*/
      
    
      
      
    public Annonces(int id, String description, String image, Date date_publication, String adresse) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.date_publication = date_publication;
        this.adresse = adresse;
        //this.nb_etoiles = nb_etoiles;
    }

    
public Annonces(String description, String image, Date date_publication, String adresse) {
       
        this.description = description;
        this.image = image;
        this.date_publication = date_publication;
        this.adresse = adresse;
        //this.nb_etoiles = nb_etoiles;
    }
 
   public Annonces(String description, String image, Date date_publication, String adresse, int nb_etoiles) {
        this.description = description;
        this.image = image;
        this.date_publication = date_publication;
        this.adresse = adresse;
       // this.nb_etoiles = nb_etoiles;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate_Publication() {
        return date_publication;
    }

    public void setDate_Publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
   /* public int getNb_etoiles() {
        return nb_etoiles;
    }

    public void setNb_etoiles(int nb_etoiles) {
        this.nb_etoiles = nb_etoiles;
    }*/

    @Override
    public String toString() {
        return "Coin{" + "id=" + id + ", Description=" + description + ", Image=" + image + ", Date_publication=" + date_publication + ", Adresse=" + adresse +'}';
    }
      
}
