/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.categorieA;
import java.util.List;

/**
 *
 * @author Fares CHAKROUN
 */
public interface InCategorieAsso<C> {
     public void insert(C o);
    public void delete(int id);
    public List<C> displayAll();
    public C displayById(int id);
     public categorieA getOneByName(String nom);
    public boolean update(C p);
}
