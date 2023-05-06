/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamations.ServicesReclamations;

/**
 *
 * @author ghofrane
 */

import Reclamations.Entite.Categorie_Rec;
import java.util.List;


public interface InterCategorie <Cat>{
     public void insert(Cat o);
    public void delete(int id);
    public List<Cat> displayAll();
    public Cat displayById(int id);
    public boolean update(Cat os);
    public Categorie_Rec getOneByName(String nom);
}
