/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Fares CHAKROUN
 */
public interface InServiceAssociation<T> {
   public void ajouter(T t);
    public List<T> afficher() throws SQLException;
    public void modifier (T t)throws SQLException;
    public boolean supprimer(T t)throws SQLException; 
 
}
