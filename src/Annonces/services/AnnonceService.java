/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.services;

import Annonces.MyBD.DataBase;
import Annonces.entities.Annonces;
//import gui.Annonce;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Annonces.MyBD.DataBase;
import Annonces.entities.Categorie;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class AnnonceService implements Iservice<Annonces> {

    private static AnnonceService instance;
    private Statement st;
    private ResultSet rs;

    public AnnonceService() {
        DataBase cs = DataBase.getInstance();
        try {
            st = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static AnnonceService getInstance() {
        if (instance == null) {
            instance = new AnnonceService();
        }
        return instance;
    }

    @Override
    public void insert(Annonces o,int userid) {
        String req = "INSERT INTO `annonces`(`id`,`description`, `image`,`categorie_id`, `date_publication`, `adresse`,`user_id`) VALUES ('" + o.getId() + "', '" + o.getDescription() + "', '" + o.getImage() + "', '" + o.getCategorie().getId() + "','" + o.getDate_publication() + "','" + o.getAdresse() + "','" + userid + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Annonces p) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "delete from annonces where id=" + p.getId();
        Annonces o = displayById(p.getId());

        if (o != null) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

    @Override
    public List<Annonces> displayAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "select * from annonces";
        // thoot ell affichage fill observalelist 

        ObservableList<Annonces> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Annonces p = new Annonces();
                p.setId(rs.getInt("id"));

                p.setDescription(rs.getString("description"));
                p.setAdresse(rs.getString("adresse"));
                p.setDate_publication(rs.getString("date_publication"));
                p.setImage(rs.getString("image"));
             
                p.setCategorie(getCatById(rs.getInt("categorie_id")));
               // System.out.println("HIHIHIHHAHAHAHAHA"+getCatById(rs.getInt("categorie_id")));
                
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    
 public Categorie getCatById(int id) throws SQLException {
    String req = "SELECT * FROM categorie WHERE id = ?";
    PreparedStatement pstmt = DataBase.getInstance().getConnection().prepareStatement(req);
    pstmt.setInt(1, id);
    ResultSet rs = pstmt.executeQuery();

    if (rs.next()) {
        Categorie categorie = new Categorie();
        categorie.setNom(rs.getString("nom"));
        categorie.setId(rs.getInt("id"));
        return categorie;
    } else {
        return null; // or throw an exception, depending on requirements
    }
}

 
    @Override
    public Annonces displayById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "select * from annonces where id =" + id;
        Annonces o = new Annonces();
        try {
            rs = st.executeQuery(req);

            rs.next();
            o.setId(rs.getInt("id"));

            o.setAdresse(rs.getString("Adresse"));
            o.setDescription(rs.getString("Description"));
            o.setDate_publication(rs.getString("date_publication"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    @Override
    public boolean update(Annonces o) {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        try {
          
            String req = "UPDATE annonces SET description=?,date_publication=?,adresse=? ,image =? WHERE id=?";
            Connection pst = DataBase.getInstance().getConnection();
            PreparedStatement pre;
            pre = pst.prepareStatement(req);

            
            pre.setString(1, o.getDescription());
            pre.setString(2,o.getDate_publication() );
             pre.setString(3,o.getAdresse() );
            //pre.setString(5, p.getImage_event());

         
             pre.setString(4, o.getImage());
                pre.setInt(5, o.getId());

            pre.executeUpdate();
            System.out.println("Annonce modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
           

    }
    
    
     @Override
    public void rating(String num_etoile,int nbetoiles,int id) throws SQLException {
        String req = "UPDATE annonces SET " + num_etoile + " = " + num_etoile + " + 1 WHERE id=?";
          Connection pst = DataBase.getInstance().getConnection();
            PreparedStatement pre;
            pre = pst.prepareStatement(req);

            
           
             pre.setInt(1, id);
             pre.executeUpdate();
               req = "UPDATE annonces SET nombre_etoiles = nombre_etoiles + ? WHERE id=?";
          
            pre = pst.prepareStatement(req);

            
           
             pre.setInt(1, nbetoiles);
            pre.setInt(2,id );
            
        

            pre.executeUpdate();
            System.out.println("Annonce modifiée !");


       
    }
    @Override
    public int getEtoile(int idAnnonce,String num_etoile) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "select "+ num_etoile+" from annonces where id =" + idAnnonce;
       
        try {
            rs = st.executeQuery(req);

            rs.next();
            return rs.getInt(num_etoile);

            
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0 ;
    }
    @Override
    public void inserRating(int idUser,int idAnnonce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "INSERT INTO `rating`(`user_id`,`annonce_id`) VALUES ('"+ idUser + "','" + idAnnonce + "' )";
       
        try {
              st.executeUpdate(req);

            

            
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
     @Override
    public boolean checkRated(int idUser,int idAnnonce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "select * from rating where user_id =" + idUser +" AND annonce_id ="+idAnnonce;
      
        try {
             System.out.println("USER ID IN RATING"+idUser);
                System.out.println("Annonce ID IN RATING"+idAnnonce);
          rs = st.executeQuery(req);
          if(rs.next())
              return true ;
          
            
            

            
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return false ;
    }
    
    

}
