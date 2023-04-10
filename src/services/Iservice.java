/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author Asus
 * @param <A>
 */
public interface Iservice <a>{
    public void insert(a o);
    public void delete(a o);
    public List<a> displayAll();
    public a displayById(int id);
    
    public boolean update(a os);
}
