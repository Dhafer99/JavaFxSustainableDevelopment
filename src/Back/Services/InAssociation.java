/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Services;

import Back.Models.categorieA;
import java.util.List;

/**
 *
 * @author Fares CHAKROUN
 */
public interface InAssociation<A> {
    
      public void insert(A o);
    public void delete(int id);
    public List<A> displayAll();
    public A displayById(int id);
   
    public boolean update(A os);
    
}
