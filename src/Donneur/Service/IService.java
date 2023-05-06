/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donneur.Service;
import Donneur.Entities.Don;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author 21629
 */
public interface IService<T> {
    public void ajouter(T t,int giverid) ;
    public List<T> afficher() throws SQLException;
    public void modifier (T t)throws SQLException;
    public boolean supprimer(T t)throws SQLException; 
    public List<T> getAllDonations()throws SQLException;
    public List<T> getDonationForReceiver(int sessionId) throws SQLException ;
    public void insertClaim(int donId,int Iddonneur,int idReceveur,int quantite_total,int quantite_a_recevoir);
}
