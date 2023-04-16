/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Categorie_Rec;
import Utils.MyCnx;
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
public class CategorieReclamationsService implements InCategorieReclamations<Categorie_Rec>{
 private static CategorieReclamationsService instance;
    private Statement st;
    private ResultSet rs;
    public CategorieReclamationsService(){
     MyCnx cs=MyCnx.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CategorieReclamationsService getInstance(){
        if(instance==null) 
            instance=new CategorieReclamationsService();
        return instance;
    }
    @Override
    public void insert(Categorie_Rec o) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     String req = "INSERT INTO `categorie_rec`(`id`, `nom`) VALUES ('" + o.getId() + "', '" + o.getNom() + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieReclamationsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void delete(int id) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     String req="delete from categorie_rec where id="+id;
        Categorie_Rec p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CategorieReclamationsService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    
    }

    @Override
    public List<Categorie_Rec> displayAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     String req="select * from categorie_rec";
        ObservableList<Categorie_Rec> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Categorie_Rec p = new Categorie_Rec();
                p.setId(rs.getInt(1));
              p.setNom(rs.getString("nom"));
              
              
                list.add(p);
            }          
        } catch (SQLException ex) {
            Logger.getLogger(CategorieReclamationsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
    }

    @Override
    public Categorie_Rec displayById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   String req="select * from categorie_rec where id ="+id;
           Categorie_Rec p=new Categorie_Rec();
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
            Logger.getLogger(CategorieReclamationsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;    
    
    }

    @Override
    public boolean update(Categorie_Rec p) {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     try {
            String req = "UPDATE categorie_rec SET nom=? WHERE id=?";
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

    @Override
    public Categorie_Rec getOneByName(String nom) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 String req = "SELECT * FROM categorie_rec WHERE nom = '" + nom + "'";
     Categorie_Rec categorie = null;
    try {
        rs = st.executeQuery(req);
        if (rs.next()) {
            categorie = new Categorie_Rec();
            categorie.setId(rs.getInt("id"));
            categorie.setNom(rs.getString("nom"));
          //  categorie.setDescription(rs.getString("description"));
        }
    
} catch (SQLException ex) {
        Logger.getLogger(CategorieReclamationsService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return categorie;
    }
}
