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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fares CHAKROUN
 */


public class categorieService implements INcategorie<categorieA>{
private static categorieService instance;
    private Statement st;
    private ResultSet rs;
       public categorieService(){
     MyCnx cs=MyCnx.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAssociation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static categorieService getInstance(){
        if(instance==null) 
            instance=new categorieService();
        return instance;
    }
    @Override
    public void insert(categorieA o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     String req = "INSERT INTO categorie(`id`, `nom`) VALUES  ('" + o.getId() + "', '" + o.getNom()  + "')";
         
         try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAssociation.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    
    }

    @Override
    public void delete(int id) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String req="delete from categorie where id="+id;
       categorieA p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAssociation.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    
    }

    @Override
    public List<categorieA> displayAll() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     String req="select * from categorie";
        ObservableList<categorieA> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                categorieA p=new categorieA();
                p.setId(rs.getInt(1));
              //  p.setPourcentage(rs.getFloat("pourcentage"));
               // p.setStart_date(rs.getDate("start_date"));
               // p.setEnd_date(rs.getDate("end_date"));
              //  p.setCategorie(rs.getObject(req, Categorie));
              p.setNom(rs.getString("nom"));
           
              
                list.add(p);
            }          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAssociation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }

    @Override
    public categorieA displayById(int id) {
       
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     String req="select * from categorie where id ="+id;
          categorieA p=new categorieA();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
               p.setId(rs.getInt(1));
               // p.setPourcentage(rs.getFloat("pourcentage"));
               // p.setStart_date(rs.getDate("start_date"));
                //p.setEnd_date(rs.getDate("end_date"));
                p.setNom(rs.getString("nom"));
             
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAssociation.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p; 
    
    }

       @Override
        public categorieA getOneByName(String nom) {
            String req = "SELECT * FROM categorie WHERE nom = '" + nom + "'";
    categorieA categorie = null;
    try {
        rs = st.executeQuery(req);
        if (rs.next()) {
            categorie = new categorieA();
            categorie.setId(rs.getInt("id"));
            categorie.setNom(rs.getString("name"));
          //  categorie.setDescription(rs.getString("description"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(categorieService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return categorie;
    }
    
    
    
    @Override
    public boolean update(categorieA p) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  
         try {
            String req = "UPDATE categorie SET nom=? WHERE id=?";
            Connection pst = MyCnx.getInstance().getCnx();
            PreparedStatement pre;
            pre = pst.prepareStatement(req);
            pre.setString(1, p.getNom());
             pre.setInt(2, p.getId());
            
            
            
            pre.executeUpdate();
            System.out.println("Categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
        return false;
    
    }
    
}
