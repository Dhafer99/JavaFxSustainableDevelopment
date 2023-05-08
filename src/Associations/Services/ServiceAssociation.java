/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Associations.Services;

import Associations.Models.Association;
import Associations.Utils.MyCnx;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fares CHAKROUN
 */
public class ServiceAssociation implements InServiceAssociation<Association>{
Connection cnx = MyCnx.getInstance().getCnx();
    @Override
    public void ajouter(Association t, int userid) {
      // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   //  String qry ="INSERT INTO `don`(`name_d`, `quantite`, `description`, `localisation`, `image`,`email`,`numero`) VALUES ('"+t.getNameD()+"','"+t.getQuantite()+"','"+t.getDescription()+"','"+t.getLocalisation()+"', '"+t.getImage()+"','"+t.getEmail()+"','"+t.getNumero()+"')";
    //String qry = "INSERT INTO `association`(`categorie_id`, `nom`, `numero`, `mail`, `adresse`, `code_postal`, `ville`, `image`) VALUES  ('" + t.getCategorie()  + "', '" + t.getNom() + "', '" + t.getNumero() + "', '" + t.getMail() + "', '" + t.getAdresse() + "', '" + t.getCodePostal() +"', '" + t.getVille() +"', '"  + t.getImage()+ "')";
            //String qry = "INSERT INTO association(`id`,`categorie_id`, `nom`, `numero`, `mail`, `adresse`, `code_postal`, `ville`, `image`) VALUES  ('" + t.getId()+ "', '" + t.getCategorie()  + "', '" + t.getNom() + "', '" + t.getNumero() + "', '" + t.getMail() + "', '" + t.getAdresse() + "', '" + t.getCodePostal() +"', '" + t.getVille() + t.getImage()+ "')";
String qry = "INSERT INTO `association`(`user_id`,`categorie_id`, `nom`, `numero`, `mail`, `adresse`, `code_postal`, `ville`, `image`) VALUES ('"+userid+"','" + t.getCategorie() + "', '" + t.getNom() + "', '" + t.getNumero() + "', '" + t.getMail() + "', '" + t.getAdresse() + "', '" + t.getCodePostal() + "', '" + t.getVille() + "', '" + t.getImage() + "')";

//          INSERT INTO `association`(`id`, `categorie_id`, `nom`, `numero`, `mail`, `adresse`, `code_postal`, `ville`, `image`)

   try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    
    }

    @Override
    public List<Association> afficher() throws SQLException {
        //hrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     List<Association> Association = new ArrayList<>();
        String s="select * from association";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Association p = new Association();
           // p.setNameD(rs.getString("name_d"));
           // p.setQuantite(rs.getInt("quantite"));
           // p.setDescription(rs.getString("description"));
            // p.setLocalisation(rs.getString("localisation"));
            // p.setImage(rs.getString("image"));
            // p.setEmail(rs.getString("email"));
            // p.setNumero(rs.getInt("numero"));
           // p.setId(rs.getInt("id"));
            //Don.add(p);
            
          //  p.setCategorie(rs.getString("categorie"));
         // p.setId(rs.getInt("id"));
             p.setId(rs.getInt(1));
             
              p.setNom(rs.getString("nom"));
              p.setNumero(rs.getInt("numero"));
              p.setMail(rs.getString("mail"));
              p.setAdresse(rs.getString("adresse"));
              p.setCodePostal(rs.getInt("code_postal"));
              p.setVille(rs.getString("ville"));
               p.setImage(rs.getString("image"));
                Association.add(p);
             //   p.setCategorie(rs.getString("categorie"));
        }
        return Association;
    
    }

    @Override
    public void modifier(Association t) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {
            String requete = "UPDATE association SET nom= ?,numero= ?,mail= ?,adresse= ?,code_postal= ?,ville= ?,image= ? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(8, t.getId());
            pst.setString(1, t.getNom());
            pst.setInt(2,t.getNumero());
            pst.setString(3, t.getMail());
            pst.setString(4, t.getAdresse());
            pst.setInt(5, t.getCodePostal());
            pst.setString(6, t.getVille());
            pst.setString(7, t.getVille());
            
            
            pst.executeUpdate();
            System.out.println("association modifiée !");
            System.out.println(""+t.getId());

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    
    }

    @Override
    public boolean supprimer(Association t) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from association where id = ? ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;  
    }

public List<Association> ordredbynom() throws SQLException {
      List<Association> Association = new ArrayList<>();
        String s="select * from association order by nom  ";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Association p = new Association();
          
            
      
             p.setId(rs.getInt(1));
             
              p.setNom(rs.getString("nom"));
              p.setNumero(rs.getInt("numero"));
              p.setMail(rs.getString("mail"));
              p.setAdresse(rs.getString("adresse"));
              p.setCodePostal(rs.getInt("code_postal"));
              p.setVille(rs.getString("ville"));
               p.setImage(rs.getString("image"));
                Association.add(p);
         
        }
        return Association;  
       
    }
    
    public ObservableList<Association> ordredbymail() throws SQLException {
    //  ObservableList<Association> Association = new ObservableList<>();
     ObservableList<Association> list=FXCollections.observableArrayList();  
        String s="select * from association order by mail  ";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Association p = new Association();
          
            
      
             p.setId(rs.getInt(1));
             
              p.setNom(rs.getString("nom"));
              p.setNumero(rs.getInt("numero"));
              p.setMail(rs.getString("mail"));
              p.setAdresse(rs.getString("adresse"));
              p.setCodePostal(rs.getInt("code_postal"));
              p.setVille(rs.getString("ville"));
               p.setImage(rs.getString("image"));
                list.add(p);
         
        }
        return list;  
       
    }
    public boolean checkUser(int userId,int RecId) throws SQLException {
        String req = "SELECT * FROM association WHERE user_id=? AND id=?";
        Connection pst = Reclamations.utiles.MyCnx.getInstance().getCnx();
        PreparedStatement pr =  pst.prepareStatement(req);
        pr.setInt(1, userId);
        pr.setInt(2, RecId);
         System.out.println("aaa"+userId);
         System.out.println("bbb"+RecId);
        ResultSet ts = pr.executeQuery();
        if (ts.next()) {
           
           return true ;
        }
        
       return false ;
    }
    
    
}
