/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Souid
 */
public class ServiceUser {
     private static ServiceUser instance;
     Connection cn = dbconnection.getInstance().getConnection();
    dbconnection cnx = dbconnection.getInstance();
    public ServiceUser(){
        
    }
    
   public static ServiceUser getInstance(){
        if(instance == null)
            instance = new ServiceUser();
        return instance;
    }
    public ObservableList<User> getUserList(){
         ObservableList<User> UserList= FXCollections.observableArrayList();
         
         String query = "SELECT * from user";
         Statement st ;
         ResultSet rs ;
         try {
             st = cn.createStatement();
             rs=st.executeQuery(query);
             User users ;
             while(rs.next())
             {
                 users=new User( rs.getString("email"),  rs.getString("num_telephone"),  rs.getString("type"),  rs.getInt("score"),  rs.getInt("nb_etoile"),rs.getString("nom"),rs.getString("prenom"),rs.getString("image"),rs.getBoolean("blocked"));
                 System.out.println(users.getNumTelephone());
                 UserList.add(users);
             }
         }catch(Exception ex )
         {
             ex.printStackTrace();
         }
         return UserList ;
     }
    public void addUser(User user) throws SQLException
    {
        
         
         
        String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
         String query = "INSERT INTO user (email,roles,password,is_verified,num_telephone,type,score,nb_etoile,nom,prenom,image,blocked) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
         PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, user.getEmail());
            st.setString(2, "ROLE_USER");
            st.setString(3, pw_hash);
            st.setInt(4,0);//isverified
            st.setString(5, user.getNumTelephone());
            st.setString(6, user.getType());
            
            st.setInt(7, 0);
            st.setInt(8,0);
            st.setString(9, user.getNom());
            st.setString(10, user.getPrenom());
            st.setString(11, user.getImage());
            st.setInt(12, 0);
              st.executeUpdate();
            System.out.println("User ajoutée");
        
    }
    public void editUser(User user) throws SQLException{
        String req = "UPDATE user SET "
                  + "email = ?,"
                    + "num_telephone = ?,"   
                    + "type= ?,"
                    + "nom = ?,"
                    + "prenom = ?,"
                    + "image = ? "
                    + " where id=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, user.getEmail());
        pre.setString(2, user.getNumTelephone().toLowerCase());
        pre.setString(3, user.getType());
        pre.setString(4, user.getNom().toLowerCase());
        pre.setString(5, user.getPrenom());
        pre.setString(6, user.getImage());
        pre.setInt(7, projetpidd.ProjetPiDD.user.getId());
        pre.executeUpdate();
        
    }
    public void BlockUser(String email) throws SQLException{
        String req = "UPDATE user SET "
                  + "blocked = ?"        
                    + " where email=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setInt(1, 1);
        pre.setString(2, email);
        pre.executeUpdate();
        
    }
    
    public void DeleteUser(String email) throws SQLException{
        String req = "DELETE FROM user "    
                    + " where email=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, email);
        pre.executeUpdate();
        
    }
      public User searchUserByEmail(String pseudo, String password) throws SQLException {
        User user = null;
      String req="SELECT (password) FROM user where (nom=? OR email=?)";
      PreparedStatement st1 = cn.prepareStatement(req);
        st1.setString(1, pseudo.toLowerCase());
        st1.setString(2, pseudo.toLowerCase());
        ResultSet rs1 = st1.executeQuery();
        while (rs1.next()){
            if(BCrypt.checkpw(password,"$2a$"+rs1.getString("password").substring(4, rs1.getString("password").length()))){
                String requete = "SELECT * FROM user where (nom=? OR email=?)";
                PreparedStatement st = cn.prepareStatement(requete);
                st.setString(1, pseudo.toLowerCase());
                st.setString(2, pseudo.toLowerCase());


                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setRoles(rs.getString("roles"));
                    user.setPassword(rs.getString("password"));
                    user.setIsVerified(rs.getBoolean("is_verified"));
                    user.setPassword(rs.getString("password"));
                    user.setNumTelephone(rs.getString("num_telephone"));
                    user.setType(rs.getString("type"));
                    user.setScore(rs.getInt("score"));
                    user.setNb_etoile(rs.getInt("nb_etoile"));
                    user.setNom(rs.getString("nom"));
                    user.setPrenom(rs.getString("prenom"));
                    user.setImage(rs.getString("image"));
                    user.setBlocked(rs.getBoolean("blocked"));
                    System.out.println("User found");

                }
            }else{
                System.out.println("no");
            }
        }
        
        return user;
    }
      
      public boolean searchUserByEmail(String email) throws SQLException {
        String req = "SELECT * FROM user WHERE email=?";
        PreparedStatement st = cn.prepareStatement(req);
        st.setString(1, email.toLowerCase());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            System.out.println("User found");
            return true;
        }
        System.out.println("User not found");
        return false;
    }
      public boolean checkBlocked(String email) throws SQLException {
        String req = "SELECT blocked FROM user WHERE email=?";
        PreparedStatement st = cn.prepareStatement(req);
        st.setString(1, email.toLowerCase());
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            
            return true;
        }
        
        return false;
    }
       public void unBlockUser(String email) throws SQLException {
          String req = "UPDATE user SET "
                  + "blocked = ?"        
                    + " where email=?";
        
        System.out.println(req);
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setInt(1, 0);
        pre.setString(2, email);
        pre.executeUpdate();
    }
      

    
}
