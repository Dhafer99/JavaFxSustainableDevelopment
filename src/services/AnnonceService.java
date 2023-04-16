/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import MyBD.DataBase;
import entities.Annonces;
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
import MyBD.DataBase;

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
    public void insert(Annonces o) {
        String req = "INSERT INTO `annonces`(`id`,`description`, `image`,`categorie_id`, `date_publication`, `adresse`) VALUES ('" + o.getId() + "', '" + o.getDescription() + "', '" + o.getImage() + "', '" + o.getCategorie().getId() + "','" + o.getDate_publication() + "','" + o.getAdresse() + "' )";
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
               // p.setId(rs.getInt(1));

                p.setDescription(rs.getString("description"));
                p.setAdresse(rs.getString("adresse"));
                p.setDate_publication(rs.getString("date_publication"));
                p.setImage(rs.getString("image"));
               
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public Annonces displayById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "select * from annonce where id =" + id;
        Annonces o = new Annonces();
        try {
            rs = st.executeQuery(req);

            rs.next();
            o.setId(rs.getInt(1));

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
          
            String req = "UPDATE annonces SET description=?,date_publication=?,adresse=? WHERE id=?";
            Connection pst = DataBase.getInstance().getConnection();
            PreparedStatement pre;
            pre = pst.prepareStatement(req);

            
            pre.setString(1, o.getDescription());
            pre.setString(2, o.getAdresse());
             pre.setString(3, o.getDate_publication());
            //pre.setString(5, p.getImage_event());

            pre.setInt(4, o.getId());

            pre.executeUpdate();
            System.out.println("Annonce modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
        
        
        
        
        

    }

}
