/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Models;

/**
 *
 * @author Fares CHAKROUN
 */
public class Association {
 private int id;
 private  int  categorie;   
 private String nom;
 private int  numero;
 private String mail;
 private String adresse;
 private int CodePostal;
 private String ville;   
  private String image ;
 public Association() {
    }

    public Association(int id, int categorie, String nom, int numero, String mail, String adresse, int CodePostal, String ville, String image) {
        this.id = id;
        this.categorie = categorie;
        this.nom = nom;
        this.numero = numero;
        this.mail = mail;
        this.adresse = adresse;
        this.CodePostal = CodePostal;
        this.ville = ville;
        this.image = image;
    }

    public Association(int categorie, String nom, int numero, String mail, String adresse, int CodePostal, String ville, String image) {
        this.categorie = categorie;
        this.nom = nom;
        this.numero = numero;
        this.mail = mail;
        this.adresse = adresse;
        this.CodePostal = CodePostal;
        this.ville = ville;
        this.image = image;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(int CodePostal) {
        this.CodePostal = CodePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

  
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
