/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Services;

import Back.Models.Annonces;
import Back.Models.Categorie;
import Back.Utils.MyCnx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fares CHAKROUN
 */
public class ServiceAnnonce implements IServiceAnnonce<Annonces>{
private static ServiceAnnonce instance;
    private Statement st;
    private ResultSet rs;

    public ServiceAnnonce() {
        //DataBase cs = DataBase.getInstance();
         MyCnx cs=MyCnx.getInstance();
        try {
           // st = cs.getConnection().createStatement();
           st  = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ServiceAnnonce getInstance() {
        if (instance == null) {
            instance = new ServiceAnnonce();
        }
        return instance;
    }
    @Override
    public void insert(Annonces o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String req = "INSERT INTO `annonces`(`id`,`description`, `image`,`categorie_id`, date_publication, adresse) VALUES ('" + o.getId() + "', '" + o.getDescription() + "', '" + o.getImage() + "', '" + o.getCategorie().getId() + "','" + o.getDate_publication() + "','" + o.getAdresse() + "' )";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Annonces p) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String req = "delete from annonces where id=" + p.getId();
        Annonces o = displayById(p.getId());

        if (o != null) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(ServiceAnnonce.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServiceAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public Categorie getCatById(int id) throws SQLException {
    String req = "SELECT * FROM categorie WHERE id = ?";
    PreparedStatement pstmt = MyCnx.getInstance().getCnx().prepareStatement(req);
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
            Logger.getLogger(ServiceAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    @Override
    public boolean rating(Annonces o) throws SQLException {
 String req = "UPDATE annonces SET nombre_etoiles=? WHERE id=?";
          Connection pst = MyCnx.getInstance().getCnx();
            PreparedStatement pre;
            pre = pst.prepareStatement(req);

            
            pre.setInt(1, o.getNombre_etoiles());
            pre.setInt(2,o.getId() );
            
        

            pre.executeUpdate();
            System.out.println("Annonce modifiée !");


        return false;
    }
    
    
    
    

    @Override
    public boolean update(Annonces o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
          
            String req = "UPDATE annonces SET description=?,date_publication=?,adresse=? ,image =? WHERE id=?";
            Connection pst = MyCnx.getInstance().getCnx();
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
    
}
