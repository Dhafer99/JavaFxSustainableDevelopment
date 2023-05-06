/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import Annonces.entities.Annonces;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 * @param <A>
 */
public interface Iservice <a>{
    public void insert(Annonces o,int userid) ;
    public void delete(a o);
    public List<a> displayAll();
    public a displayById(int id);
     public void rating(String num_etoile,int nbetoiles,int id) throws SQLException ;
     public int getEtoile(int idAnnonce,String num_etoile) ;
    public boolean update(a os);
   public void inserRating(int idUser,int idAnnonce) ;
    public boolean checkRated(int idUser,int idAnnonce) throws SQLException ;
}
