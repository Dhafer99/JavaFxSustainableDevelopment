/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Associations.Services;

import Associations.Models.Association;
import Associations.Models.categorieA;
import Associations.Utils.MyCnx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fares CHAKROUN
 */
public class serviceCategorieAssociation implements InServiceAssociation<categorieA>{
     private ResultSet rs;
     private Statement st;
Connection cnx = MyCnx.getInstance().getCnx();

    @Override
    public void ajouter(categorieA t,int userid) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
     String qry ="INSERT INTO `categorie`(`nom`) VALUES ('"+t.getNom()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    
    }

    @Override
    public List<categorieA> afficher() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    List<categorieA> categorieA = new ArrayList<>();
        String s = "select * from categorie";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            categorieA p = new categorieA();
            p.setNom(rs.getString("nom"));
          
            p.setId(rs.getInt("id"));
            categorieA.add(p);
            
        }
        return categorieA;
    
    
    }

    @Override
    public void modifier(categorieA t) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
            String requete = "UPDATE categorie SET nom= ? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());
            pst.setString(1, t.getNom());
           
            
            
            pst.executeUpdate();
            System.out.println("Category modifiée !");
            System.out.println(""+t.getId());

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
    }

    @Override
    public boolean supprimer(categorieA t) throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
     boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from categorie where id = ? ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;
    
    }
   public List<categorieA> ordredbynom() {
         String req="select * from categorie order by nom";
        ObservableList<categorieA> list=FXCollections.observableArrayList();       
        
        try {
             Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(req);
          //  rs=st.executeQuery(req);
            while(rs.next()){
                
                categorieA p=new categorieA();
                p.setId(rs.getInt(1));
               // p.getNom(rs.gettring("nom"));;
                   p.setNom(rs.getString("nom"));
               
                
               // CategorieDao catgdao= new CategorieDao();
               // CategoriePromotion c= catgdao.displayById(rs.getInt("id_categ_id"));
               // p.setCategorie(c);
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(serviceCategorieAssociation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; //To change body of generated methods, choose Tools | Templates.
    }
}
    