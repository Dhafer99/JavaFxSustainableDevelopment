/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donneur.Service;

import Donneur.Entities.Category_d;
import Donneur.Entities.Don;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Donneur.utils.db_connect;

/**
 *
 * @author 21629
 */
public class ServiceCategory implements IService<Category_d> {
Connection cnx = db_connect.getInstance().getCnx();
    @Override
    public void ajouter(Category_d t,int giverid) {
 String qry ="INSERT INTO `category_d`(`name_ca`) VALUES ('"+t.getName_ca()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }

  @Override
    public List<Category_d> afficher() throws SQLException {
        List<Category_d> Don = new ArrayList<>();
        String s = "select * from category_d";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Category_d p = new Category_d();
            p.setName_ca(rs.getString("name_ca"));
          
            p.setId(rs.getInt("id"));
            Don.add(p);
            
        }
        return Don;
        
    }

   @Override
    public void modifier(Category_d t) {
try {
            String requete = "UPDATE category_d SET name_ca= ? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());
            pst.setString(1, t.getName_ca());
           
            
            
            pst.executeUpdate();
            System.out.println("Category modifiée !");
            System.out.println(""+t.getId());

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
       public boolean supprimer(Category_d t) throws SQLException {
    boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from category_d where id = ? ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;  
    }

    @Override
    public List<Category_d> getAllDonations() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     @Override
   public List<Category_d> getDonationForReceiver(int sessionId) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   public void insertClaim(int donId,int Iddonneur,int idReceveur,int quantite_total,int quantite_a_recevoir){
       
   }
    
  
    
}
