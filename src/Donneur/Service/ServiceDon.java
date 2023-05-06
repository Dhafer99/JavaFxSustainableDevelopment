/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donneur.Service;

/**
 *
 * @author 21629
 */
import Donneur.Entities.Don;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Donneur.utils.db_connect;
import Model.User;
/**
 *
 * @author 21629
 */
public class ServiceDon implements IService<Don> {
Connection cnx = db_connect.getInstance().getCnx();
    @Override
    public void ajouter(Don t,int giverid) {
 String qry ="INSERT INTO `don`(`user_id`,`name_d`, `quantite`, `description`, `localisation`, `category_d_id`, `image`,`email`,`numero`) VALUES ('"+giverid+"','"+t.getNameD()+"','"+t.getQuantite()+"','"+t.getDescription()+"','"+t.getLocalisation()+"','"+t.getCategory_d_id()+"', '"+t.getImage()+"','"+t.getEmail()+"','"+t.getNumero()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }

    @Override
    public List<Don> afficher() throws SQLException {
        List<Don> Don = new ArrayList<>();
        String s = "select * from don";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Don p = new Don();
            p.setNameD(rs.getString("name_d"));
            p.setQuantite(rs.getInt("quantite"));
            p.setDescription(rs.getString("description"));
             p.setLocalisation(rs.getString("localisation"));
                                       p.setCategory_d_id(rs.getInt("category_d_id"));

             p.setImage(rs.getString("image"));
             p.setEmail(rs.getString("email"));
             p.setNumero(rs.getInt("numero"));
            p.setId(rs.getInt("id"));
            Don.add(p);
            
        }
        return Don;
    }

   @Override
    public void modifier(Don t) {
try {
            String requete = "UPDATE don SET name_d= ?,quantite= ?,description= ?,localisation= ?,image= ?,email= ?,numero= ? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(8, t.getId());
            pst.setString(1, t.getNameD());
            pst.setInt(2,t.getQuantite());
            pst.setString(3, t.getDescription());
            pst.setString(4, t.getLocalisation());
            pst.setString(5, t.getImage());
            pst.setString(6, t.getEmail());
            pst.setInt(7, t.getNumero());
            
            
            pst.executeUpdate();
            System.out.println("Don modifiée !");
            System.out.println(""+t.getId());

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
       public boolean supprimer(Don t) throws SQLException {
    boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from don where id = ? ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;  
    }
        @Override
    public List<Don> getAllDonations() throws SQLException {
        List<Don> donations = new ArrayList<>();

        // Se connecter à la base de données
       

            // Créer une requête SQL pour obtenir les données de la base de données
            String sql = "SELECT name_d, quantite, SUM(quantite) AS total FROM don GROUP BY name_d";
Statement st = cnx.createStatement();
            // Exécuter la requête SQL et obtenir les résultats
            ResultSet rs = st.executeQuery(sql);

            // Ajouter des données à la liste de dons
            while (rs.next()) {

                String name = rs.getString("name_d");
                int quantité = rs.getInt("quantite");
                int total = rs.getInt("total");

                donations.add(new Don(name, quantité));
            }

       

        return donations;
    }
    public List<Don> getDonationForReceiver(int sessionId) throws SQLException {
     List<Don> donations = new ArrayList<>();

    // Prepare the SQL statement to retrieve donations by receiver ID
    String sql = "SELECT * from claim where receiver_id=?";
    PreparedStatement pstmt = cnx.prepareStatement(sql);
    pstmt.setInt(1, sessionId);
    //pstmt.setInt(2, sessionId);
    
    // Execute the SQL statement and retrieve the results
    ResultSet rs = pstmt.executeQuery();
    if (rs.next()) {
        Don d = DonById(rs.getInt("donation_id"));
        d.setGivername(UserNameById(rs.getInt("donner_id")));
        d.setQuantite(rs.getInt("received_quantity"));
        
        System.out.println(d);
        donations.add(d);
    }

    
    
    return donations;
}
    public int searchDonById(int donId) throws SQLException {
        String req = "SELECT user_id FROM don WHERE id=?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, donId);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
           
            return rs.getInt("user_id");
        }
        
        return 0 ;
    }
@Override
    public void insertClaim(int donId,int Iddonneur,int idReceveur,int quantite_total,int quantite_a_recevoir) {
 String qry ="INSERT INTO `claim`(`donation_id`,`donner_id`, `receiver_id`, `total_quantity`, `received_quantity`) VALUES ('"+donId+"','"+Iddonneur+"','"+idReceveur+"','"+quantite_total+"','"+quantite_a_recevoir+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }
    
   public String UserNameById(int idUser) throws SQLException
   {
        String req = "SELECT nom FROM user WHERE id=?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, idUser);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
           
            return rs.getString("nom");
        }
        
        return "" ;
   }
   public Don DonById(int idDon) throws SQLException
   {
        String req = "SELECT * FROM don WHERE id=?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, idDon);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
           Don d = new Don();
            d.setNameD( rs.getString("name_d"));
            d.setDescription(rs.getString("description"));
            d.setLocalisation(rs.getString("localisation"));
           
            return d ;
        }
        
        return null ;
   }
    
    
    
}
