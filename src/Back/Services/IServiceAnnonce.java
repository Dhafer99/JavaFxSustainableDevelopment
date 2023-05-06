/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Services;

import Back.Models.Annonces;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Fares CHAKROUN
 */
public interface IServiceAnnonce <a> {
    public void insert(a o);
    public void delete(a p);
    public List<a> displayAll();
    public a displayById(int id);
    public boolean rating(Annonces o) throws SQLException ;
    
    public boolean update(a os);
    
    
    
}
