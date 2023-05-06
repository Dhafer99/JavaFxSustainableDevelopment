/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Models;

/**
 *
 * @author sarah
 */
public class CategorieEvent {
     private int id;
    private String nom;

    public CategorieEvent(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public CategorieEvent(String nom) {
        this.nom = nom;
    }

    public CategorieEvent() {
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

    @Override
    public String toString() {
        return "CategorieEvent{" + "id=" + id + ", nom=" + nom + '}';
    }
    
    
}
