/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author sarah
 */
public class Evenement {
    
     private int id;
        private  String Nom_event;
        private String localisation, image_event;
        private Date Date_debut;
        private Date Date_fin;
        private int nb_participants;
        private int categoryId;

    public Evenement() {
    }

        
    public Evenement(int id, String Nom_event, String localisation, String image_event, Date Date_debut, Date Date_fin, int nb_participants) {
        this.id = id;
        this.Nom_event = Nom_event;
        this.localisation = localisation;
        this.image_event = image_event;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.nb_participants = nb_participants;
    }

    public Evenement(String Nom_event, String localisation, String image_event, Date Date_debut, Date Date_fin, int nb_participants) {
        this.Nom_event = Nom_event;
        this.localisation = localisation;
        this.image_event = image_event;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.nb_participants = nb_participants;
    }

    public Evenement(String Nom_event, String localisation, String image_event) {
        this.Nom_event = Nom_event;
        this.localisation = localisation;
        this.image_event = image_event;
    }

    public Evenement(int id, String Nom_event, String localisation, String image_event, Date Date_debut, Date Date_fin, int nb_participants, int categoryId) {
        this.id = id;
        this.Nom_event = Nom_event;
        this.localisation = localisation;
        this.image_event = image_event;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.nb_participants = nb_participants;
        this.categoryId = categoryId;
    }

    public Evenement(String Nom_event, String localisation, String image_event, Date Date_debut, Date Date_fin, int nb_participants, int categoryId) {
        this.Nom_event = Nom_event;
        this.localisation = localisation;
        this.image_event = image_event;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.nb_participants = nb_participants;
        this.categoryId = categoryId;
    }
    

    public Evenement(String text, String text0, String text1, float parseFloat, String lien, int parseInt, java.util.Date date, java.util.Date from, java.util.Date from0, String enabled, int uid, int category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(String text, String text0, String text1, float parseFloat, String lien, int parseInt, java.util.Date date, java.util.Date from, java.util.Date from0, String enabled, int uid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(String text, String text0, String text1, String lien, int parseInt, java.util.Date date, java.util.Date from, java.util.Date from0, String enabled, int uid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(String text, String text0, String lien, int parseInt, java.util.Date date, java.util.Date from, java.util.Date from0, String enabled, int uid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(int id, String Nom_event, String localisation, String image_event, Date Date_debut, Date Date_fin) {
        this.id = id;
        this.Nom_event = Nom_event;
        this.localisation = localisation;
        this.image_event = image_event;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
    }

    public Evenement(int id, String text, String text0, String lien, int parseInt, java.util.Date date, java.util.Date from, java.util.Date from0, String enabled, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(int aInt, String string, String string0, Date date, Date date0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(String text, String text0, String lien, int parseInt, java.util.Date date, java.util.Date from, java.util.Date from0, String enabled, int uid, int category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_event() {
        return Nom_event;
    }

    public void setNom_event(String Nom_event) {
        this.Nom_event = Nom_event;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getImage_event() {
        return image_event;
    }

    public void setImage_event(String image_event) {
        this.image_event = image_event;
    }

    public Date getDate_debut() {
        return Date_debut;
    }

    public void setDate_debut(Date Date_debut) {
        this.Date_debut = Date_debut;
    }

    public Date getDate_fin() {
        return Date_fin;
    }

    public void setDate_fin(Date Date_fin) {
        this.Date_fin = Date_fin;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", Nom_event=" + Nom_event + ", localisation=" + localisation + ", image_event=" + image_event + ", Date_debut=" + Date_debut + ", Date_fin=" + Date_fin + ", nb_participants=" + nb_participants + ", categoryId=" + categoryId + '}';
    }

   

    public void setDate_debut(DatePicker dpEventAddDateDeb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
        
    
}
