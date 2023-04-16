/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Categorie_Rec;
import java.util.List;

/**
 *
 * @author Fares CHAKROUN
 */
public interface InCategorieReclamations<Cat> {
     public void insert(Cat o);
    public void delete(int id);
    public List<Cat> displayAll();
    public Cat displayById(int id);
    public boolean update(Cat p);
    public Categorie_Rec getOneByName(String nom);
}
