/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import Annonces.entities.Categorie;
import java.util.List;


/**
 *
 * @author Asus
 */
public interface INCategorie <C>{
    public void insert(C o);
    public void delete(int id);
    public List<C> displayAll();
    public C displayById(int id);
     public Categorie getOneByName(String nom);
    public boolean update(C os);
    
}
