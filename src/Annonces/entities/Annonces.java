/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.entities;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Annonces {
     private int id;
    private String adresse,description,image;
            private String date_publication;
            private int nombre_etoiles; 
 public Categorie categorie_id ;    
 
    
      public Annonces() {
    }

   /* public Annonces(String adresse, String description) {
        this.adresse = adresse;
        this.description = description;
    }*/

    public Annonces(int id, String adresse, String description, String image, String date_publication, Categorie categorie_id) {
        this.id = id;
        this.adresse = adresse;
        this.description = description;
        this.image = image;
        this.date_publication = date_publication;
        this.categorie_id = categorie_id;
    }

    public int getNombre_etoiles() {
        return nombre_etoiles;
    }

    public void setNombre_etoiles(int nombre_etoiles) {
        this.nombre_etoiles = nombre_etoiles;
    }
    
    
    

    public Annonces(String adresse, String description, String image, String date_publication, Categorie categorie_id) {
        this.adresse = adresse;
        this.description = description;
        this.image = image;
        this.date_publication = date_publication;
        this.categorie_id = categorie_id;
    }
      
    
      
      

 
   /*public Annonces(String description, String image, Date date_publication, String adresse, int nb_etoiles) {
        this.description = description;
        this.image = image;
        this.date_publication = date_publication;
        this.adresse = adresse;
       // this.nb_etoiles = nb_etoiles;
    }*/

    

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

    public String getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(String date_publication) {
        this.date_publication = date_publication;
    }

    

    public Categorie getCategorie() {
        return categorie_id;
    }

    public void setCategorie(Categorie categorie_id) {
        this.categorie_id = categorie_id;
    }

    @Override
    public String toString() {
        return "Annonces{" + "id=" + id + ", adresse=" + adresse + ", description=" + description + ", image=" + image + ", date_publication=" + date_publication + ", categorie_id=" + categorie_id + '}';
    }

    
    
    
  
      
}
