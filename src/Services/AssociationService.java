/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Association;
import Utils.MyCnx;

import static java.awt.event.PaintEvent.UPDATE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.runtime.Debug.id;
import static jdk.nashorn.internal.runtime.PropertyDescriptor.SET;

/**
 *
 * @author Fares CHAKROUN
 */
public class AssociationService implements InAssociation<Association> {

    private static AssociationService instance;
    private Statement st;
    private ResultSet rs;
    
    // thel cnx m3a ell base
    public AssociationService(){
     MyCnx cs=MyCnx.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AssociationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // connection singletion instance unique 
    public static AssociationService getInstance(){
        if(instance==null) 
            instance=new AssociationService();
        return instance;
    }
    @Override
    public void insert(Association o) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    // INSERT INTO `association`(`id`, `categorie_id`, `nom`, `numero`, `mail`, `adresse`, `code_postal`, `ville`, `image`) VALUES 
    String req = "INSERT INTO association(`id`,`categorie_id`, `nom`, `numero`, `mail`, `adresse`, `code_postal`, `ville`) VALUES  ('" + o.getId()+ "', '" + o.getCategorie().getId()  + "', '" + o.getNom() + "', '" + o.getNumero() + "', '" + o.getMail() + "', '" + o.getAdresse() + "', '" + o.getCodePostal() +"', '" + o.getVille() + "')";
         
         try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(AssociationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    
    }

    @Override
    public void delete(int id) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  
    String req="delete from association where id="+id;
        Association p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(AssociationService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    
  
    
    
    
    
    
    }

    @Override
    public List<Association> displayAll() {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     String req="select * from association";
     // thoot ell affichage fill observalelist 
    
        ObservableList<Association> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Association p=new Association();
                p.setId(rs.getInt(1));
             
              p.setNom(rs.getString("nom"));
              p.setNumero(rs.getInt("numero"));
              p.setMail(rs.getString("mail"));
              p.setAdresse(rs.getString("adresse"));
              p.setCodePostal(rs.getInt("code_postal"));
              p.setVille(rs.getString("ville"));
              
                list.add(p);
            }          
        } catch (SQLException ex) {
            Logger.getLogger(AssociationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
  
    }

    @Override
    public Association displayById(int id) {
      String req="select * from association where id ="+id;
           Association p=new Association();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
               p.setId(rs.getInt(1));
               // p.setPourcentage(rs.getFloat("pourcentage"));
               // p.setStart_date(rs.getDate("start_date"));
                //p.setEnd_date(rs.getDate("end_date"));
                p.setNom(rs.getString("nom"));
              p.setNumero(rs.getInt("numero"));
              p.setMail(rs.getString("mail"));
              p.setAdresse(rs.getString("adresse"));
              p.setCodePostal(rs.getInt("CodePostal"));
              p.setVille(rs.getString("ville"));
                
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(AssociationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p; 
//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Association p) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    // String qry = "UPDATE pormotion SET produit_id_id = '"+p.getProduit().getId()+"', start_date = '"+p.getStart_date()+"', end_date = '"+p.getEnd_date()+"', pourcentage = '"+p.getPourcentage()+"'id_categ_id = '"+p.getCategorie()+"' WHERE id = "+p.getId();
     // String qry = "UPDATE association SET id="+p.getId()+",nom='"+p.getNom()+"',numero='"+p.getNumero()+"',mail='"+p.getMail()+"',adresse='"+p.getAdresse()+"',code_postal='"+p.getCodePostal()+"',ville='"+p.getVille()+"' WHERE id = "+p.getId()";
  //  String qry = "UPDATE association SET nom="+p.getNom()+",numero="+p.getNumero()+",mail="+p.getMail()+",adresse="+p.getAdresse()+",code_postal="+p.getCodePostal()+",ville="+p.getVille()+" WHERE mail = "+p.getMail();
    Connection cn = MyCnx.getInstance().getCnx();
        String req = "UPDATE association SET "
                + "nom = ?,"
                + " numero = ?,"
                + "mail = ?,"
                + "adresse = ?,"
                + "code_postal = ?,"
                + "ville = ?"
               
                +" where mail=?";

        PreparedStatement pre;
        try {
            pre = cn.prepareStatement(req);
            
             pre.setString(1, p.getNom());
        pre.setInt(2, p.getNumero());
        pre.setString(3, p.getMail());
        pre.setString(4, p.getAdresse());
        pre.setInt(5, p.getCodePostal());
        pre.setString(6, p.getVille());
        pre.setString(7, p.getMail());
        pre.executeUpdate();
        return true ;
        } catch (SQLException ex) {
            Logger.getLogger(AssociationService.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    return false ;
}
    
    
      public static List<Association> rechercher(List<Association> listc,String nomclient)
   {
       return (List<Association>) listc.stream()
        .filter(a -> a.getMail().equalsIgnoreCase(nomclient)).collect(Collectors.toList());
       
       
   }
    
}
    
    
    
    
    

