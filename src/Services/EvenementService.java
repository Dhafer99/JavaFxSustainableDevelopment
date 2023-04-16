/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Evenement;
import Utils.MyCnx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fares CHAKROUN
 */
public class EvenementService implements IServiceEvent<Evenement> {
Connection cnx = MyCnx.getInstance().getCnx();
    @Override
    public void ajouter(Evenement t) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  String qry ="INSERT INTO `evenement`(`nom_event`, `localisation`, `date_debut`, `date_fin`, `nb_participants`,`image_event`,`categorie_id`) VALUES ('"+t.getNom_event()+"','"+t.getLocalisation()+"','"+t.getDate_debut()+"','"+t.getDate_fin()+"', '"+t.getNb_participants()+"','"+t.getImage_event()+"','"+t.getCategoryId()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }

    @Override
    public List<Evenement> afficher() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    List<Evenement> Events = new ArrayList<>();
        String s = "select * from evenement";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Evenement e = new Evenement();
            e.setNom_event(rs.getString("nom_event"));
            e.setLocalisation(rs.getString("localisation"));
            e.setDate_debut(rs.getDate("date_debut"));
             e.setDate_fin(rs.getDate("date_fin"));
             e.setImage_event(rs.getString("image_event"));
           e.setCategoryId(rs.getInt("categorie_id"));
            e.setId(rs.getInt("id"));
            Events.add(e);
            
        }
        return Events;
    
    }

    @Override
    public void modifier(Evenement t) throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    try {
            String requete = "UPDATE evenement SET nom_event = ?,localisation = ?,date_debut = ?,date_fin = ?,image_event = ?,nb_participants = ? ,categorie_id = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(8, t.getId());
            pst.setString(1, t.getNom_event());
            pst.setString(2, t.getLocalisation());
            pst.setDate(3, new java.sql.Date(t.getDate_debut().getTime()));
            pst.setDate(4, new java.sql.Date(t.getDate_fin().getTime()));
            pst.setString(5, t.getImage_event());
            pst.setInt(6, t.getNb_participants());
            pst.setInt(7, t.getCategoryId());
            
            
            pst.executeUpdate();
            System.out.println("Event modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    @Override
    public boolean supprimer(Evenement t) throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from evenement where id = ? ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok; 
    
    }
    
    
 public List<Evenement> recupererById(int id) throws SQLException {
        String req = "select * from evenement where id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
         List<Evenement> activitees = new ArrayList<>();
          while (rs.next()) {
            Evenement p = new Evenement();
            p.setId(rs.getInt("id"));
            p.setNom_event(rs.getString("nom_event"));
            p.setLocalisation(rs.getString("localisation"));
            p.setImage_event(rs.getString("image_event"));
            p.setDate_debut(rs.getDate("date_debut"));
            p.setDate_fin(rs.getDate("date_fin"));
            p.setNb_participants(rs.getInt("nb_participants"));

            activitees.add(p);
        }
        return activitees;
    }   
}
