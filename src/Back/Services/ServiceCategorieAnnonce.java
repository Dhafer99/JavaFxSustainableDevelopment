/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Services;

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
public class ServiceCategorieAnnonce implements INCategorieAnnonce<Categorie>{

    private static ServiceCategorieAnnonce instance;
    private Statement st;
    private ResultSet rs;
       public ServiceCategorieAnnonce(){
   //  DataBase cs=DataBase.getInstance();
    MyCnx cs=MyCnx.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ServiceCategorieAnnonce getInstance(){
        if(instance==null) 
            instance=new ServiceCategorieAnnonce();
        return instance;
    }
    
    
    
    
    @Override
    public void insert(Categorie o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String req = "INSERT INTO categorie(id, nom) VALUES  ('" + o.getId()+ "', '" + o.getNom()  + "')";
         
         try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void delete(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String req="delete from categorie where id="+id;
       Categorie p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    
    
    }

    @Override
    public List<Categorie> displayAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String req="select * from categorie";
        ObservableList<Categorie> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Categorie p=new Categorie();
                p.setId(rs.getInt(1));
        
              p.setNom(rs.getString("nom"));
           
              
                list.add(p);
            }          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }

    @Override
    public Categorie displayById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   String req="select * from categorie where id ="+id;
          Categorie p=new Categorie();
        try {
            rs=st.executeQuery(req);
           
            rs.next();
               p.setId(rs.getInt(1));
             
                p.setNom(rs.getString("nom"));
             
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    
    
    }

    @Override
    public Categorie getOneByName(String nom) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String req = "SELECT * FROM categorie WHERE nom = '" + nom + "'";
    Categorie categorie = null;
    try {
        rs = st.executeQuery(req);
        if (rs.next()) {
            categorie = new Categorie();
            categorie.setId(rs.getInt("id"));
            categorie.setNom(rs.getString("nom"));
          
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCategorieAnnonce.class.getName()).log(Level.SEVERE, null, ex);
    }
    return categorie;
    
    
    }

    @Override
    public boolean update(Categorie p) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    try {
            String req = "UPDATE categorie SET nom=? WHERE id=?";
          //  MyCnx cs=MyCnx.getInstance();
            Connection pst = MyCnx.getInstance().getCnx();
            PreparedStatement pre;
            pre = pst.prepareStatement(req);
            
            
            pre.setString(1, p.getNom());
            
             pre.setInt(2, p.getId());
            
            
            
            pre.executeUpdate();
            System.out.println("categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
        return false;
    
    }

    
    
}
