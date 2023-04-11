/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Fares CHAKROUN
 */
public class Association {
      private int id;
 private String nom;
 private int  numero;
 private String mail;
 private String adresse;
 private int CodePostal;
 private String ville;   

    public Association(int id, String nom, int numero, String mail, String adresse, int CodePostal, String ville, categorieA categorie) {
        this.id = id;
        this.nom = nom;
        this.numero = numero;
        this.mail = mail;
        this.adresse = adresse;
        this.CodePostal = CodePostal;
        this.ville = ville;
        this.categorie = categorie;
    }
 private categorieA categorie;

    public Association() {
    }

    public Association(String nom, int numero, String mail, String adresse, int CodePostal, String ville, categorieA categorie) {
        this.nom = nom;
        this.numero = numero;
        this.mail = mail;
        this.adresse = adresse;
        this.CodePostal = CodePostal;
        this.ville = ville;
        this.categorie = categorie;
    }
 
 
 
    public Association(String nom, int numero, String mail, String adresse, int CodePostal, String ville) {
        this.nom = nom;
        this.numero = numero;
        this.mail = mail;
        this.adresse = adresse;
        this.CodePostal = CodePostal;
        this.ville = ville;
    }

    public Association(int id, String nom, int numero, String mail, String adresse, int CodePostal, String ville) {
        this.id = id;
        this.nom = nom;
        this.numero = numero;
        this.mail = mail;
        this.adresse = adresse;
        this.CodePostal = CodePostal;
        this.ville = ville;
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

    public categorieA getCategorie() {
        return categorie;
    }

    public void setCategorie(categorieA categorie) {
        this.categorie = categorie;
    }

}
