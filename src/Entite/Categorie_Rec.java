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

public class Categorie_Rec {
    private int id ;
    private String nom; 

    public Categorie_Rec(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie_Rec() {
    }

    public Categorie_Rec(String nom) {
        this.nom = nom;
    }
    

    @Override
    public String toString() {
        return  nom;
    }
    
}
