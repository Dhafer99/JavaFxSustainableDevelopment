/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenements.Service;

import Evenements.Entities.CategorieEvent;
import Evenements.Entities.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Evenements.utils.MyDB;



/**
 *
 * @author sarah
 */
public class CategorieEventService implements IServiceCategorieEvent<CategorieEvent> {
Connection cnx = MyDB.getInstance().getCnx();
    @Override
    public void ajouter(CategorieEvent t) {
         String qry ="INSERT INTO `categorie_event`(`nom_categ_event`) VALUES ('"+t.getNom()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }

    @Override
    public List<CategorieEvent> afficher() throws SQLException {
        List<CategorieEvent> categorie = new ArrayList<>();
        String s = "select * from categorie_event";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            CategorieEvent e = new CategorieEvent();
            e.setNom(rs.getString("nom_categ_event"));
            e.setId(rs.getInt("id"));
            categorie.add(e);
            
        }
        return categorie;
    }

    @Override
    public void modifier(CategorieEvent t) throws SQLException {
       try {
            String requete = "UPDATE categorie_event SET nom_categ_event = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());
            pst.setString(1, t.getNom());
           
            
            
            pst.executeUpdate();
            System.out.println("categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }

    @Override
    public boolean supprimer(CategorieEvent t) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from categorie_event where id = ? ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;  
    }
 
    
}
