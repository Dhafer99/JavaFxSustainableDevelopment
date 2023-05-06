/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Associations.Services;

import Associations.Models.categorieA;
import java.util.List;

/**
 *
 * @author Fares CHAKROUN
 */
public interface INcategorie<C> {
        public void insert(C o);
    public void delete(int id);
    public List<C> displayAll();
    public C displayById(int id);
     public categorieA getOneByName(String nom);
    public boolean update(C os);
}
