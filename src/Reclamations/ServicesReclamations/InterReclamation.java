/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamations.ServicesReclamations;

import java.util.List;

/**
 *
 * @author ghofrane
 */
public interface InterReclamation <A> { 
    public void insert(A o,int UserId);
    public void delete(int id);
    public List<A> displayAll();
    public A displayById(int id);
    
    public boolean update(A os);
    
    
    
}
