/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Services;

import Back.Models.Don;
import Back.Utils.MyCnx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fares CHAKROUN
 */
public class ServiceDon implements IService<Don> {
Connection cnx = MyCnx.getInstance().getCnx();
    @Override
    public void ajouter(Don t) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
 String qry ="INSERT INTO `don`(`name_d`, `quantite`, `description`, `localisation`, `category_d_id`, `image`,`email`,`numero`) VALUES ('"+t.getNameD()+"','"+t.getQuantite()+"','"+t.getDescription()+"','"+t.getLocalisation()+"','"+t.getCategory_d_id()+"', '"+t.getImage()+"','"+t.getEmail()+"','"+t.getNumero()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    
    }

    @Override
    public List<Don> afficher() throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
             p.setImage(rs.getString("image"));
             p.setEmail(rs.getString("email"));
             p.setNumero(rs.getInt("numero"));
            p.setId(rs.getInt("id"));
            Don.add(p);
            
        }
        return Don;
    
    }

    @Override
    public void modifier(Don t) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        }
        } 
    

    @Override
    public boolean supprimer(Don t) throws SQLException {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    }
    

