/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.Reclamation;
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
 * @author ghofrane
 */
public class ReclamationService implements InterReclamation <Reclamation> {
    private static ReclamationService instance;
    private Statement st;
    private ResultSet rs;
    public ReclamationService(){
     MyCnx cs=MyCnx.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ReclamationService getInstance(){
        if(instance==null) 
            instance=new ReclamationService();
        return instance;
    }
    @Override
    public void insert(Reclamation o) {
         String req = "INSERT INTO `reclamation`(`id`, `data_reclamation`, `etat`, `categorie_rec_id`, `motif_de_reclamation`,`image`, `num_telephone`, `email`) VALUES ('" + o.getId() + "', '" + o.getData_reclamation() + "', '" + o.getEtat() + "', '" + o.getCategorie_rec().getId() + "','" + o.getMotif_de_reclamation() + "','"+ o.getImage() +"', '" + o.getNum_telephone() + "', '" + o.getEmail() + "')";
         try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req="delete from reclamation where id="+id;
        Reclamation p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    
    }

    @Override
    public List<Reclamation> displayAll() {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      String req="select * from reclamation";
        ObservableList<Reclamation> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Reclamation p = new Reclamation();
                p.setId(rs.getInt(1));
              //  p.setPourcentage(rs.getFloat("pourcentage"));
               // p.setStart_date(rs.getDate("start_date"));
               // p.setEnd_date(rs.getDate("end_date"));
              //  p.setCategorie(rs.getObject(req, Categorie));
              p.setData_reclamation(rs.getDate("data_reclamation"));
              p.setEtat(rs.getString("etat"));
              p.setMotif_de_reclamation(rs.getString("motif_de_reclamation"));
              p.setNum_telephone(rs.getInt("num_telephone"));
              p.setEmail(rs.getString("email"));
              
                list.add(p);
            }          
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Reclamation displayById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req="select * from reclamation where id ="+id;
           Reclamation p=new Reclamation();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
               p.setId(rs.getInt(1));
               // p.setPourcentage(rs.getFloat("pourcentage"));
               // p.setStart_date(rs.getDate("start_date"));
                //p.setEnd_date(rs.getDate("end_date"));
                p.setData_reclamation(rs.getDate("data_reclamation"));
              p.setEtat(rs.getString("etat"));
              p.setMotif_de_reclamation(rs.getString("motif_de_reclamation"));
              p.setNum_telephone(rs.getInt("num_telephone"));
              p.setEmail(rs.getString("email"));
                
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;    
    }
    

    @Override
    public boolean update(Reclamation p) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    // String qry = "UPDATE pormotion SET produit_id_id = '"+p.getProduit().getId()+"', start_date = '"+p.getStart_date()+"', end_date = '"+p.getEnd_date()+"', pourcentage = '"+p.getPourcentage()+"'id_categ_id = '"+p.getCategorie()+"' WHERE id = "+p.getId();
     // String qry = "UPDATE association SET id="+p.getId()+",nom='"+p.getNom()+"',numero='"+p.getNumero()+"',mail='"+p.getMail()+"',adresse='"+p.getAdresse()+"',code_postal='"+p.getCodePostal()+"',ville='"+p.getVille()+"' WHERE id = "+p.getId()";
  //  String qry = "UPDATE association SET nom="+p.getNom()+",numero="+p.getNumero()+",mail="+p.getMail()+",adresse="+p.getAdresse()+",code_postal="+p.getCodePostal()+",ville="+p.getVille()+" WHERE mail = "+p.getMail();
  /* Connection cn = MyCnx.getInstance().getCnx();
        String qry = "UPDATE reclamation SET etat = '"+p.getEtat()+"', motif_de_reclamation= '"+p.getMotif_de_reclamation()+"', num_telephone= '"+p.getNum_telephone()+"', email= '"+p.getEmail()+"' WHERE id = "+p.getId();

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
    }
        return false;*/
  
  
  // String qry = "UPDATE pormotion SET produit_id_id = '"+p.getProduit().getId()+"', start_date = '"+p.getStart_date()+"', end_date = '"+p.getEnd_date()+"', pourcentage = '"+p.getPourcentage()+"'id_categ_id = '"+p.getCategorie()+"' WHERE id = "+p.getId();
     // String qry = "UPDATE association SET id="+p.getId()+",nom='"+p.getNom()+"',numero='"+p.getNumero()+"',mail='"+p.getMail()+"',adresse='"+p.getAdresse()+"',code_postal='"+p.getCodePostal()+"',ville='"+p.getVille()+"' WHERE id = "+p.getId()";
  //  String qry = "UPDATE association SET nom="+p.getNom()+",numero="+p.getNumero()+",mail="+p.getMail()+",adresse="+p.getAdresse()+",code_postal="+p.getCodePostal()+",ville="+p.getVille()+" WHERE mail = "+p.getMail();
    try {
            String req = "UPDATE reclamation SET data_reclamation=?,etat=?,motif_de_reclamation=?,num_telephone=?,email=? WHERE id=?";
            Connection pst = MyCnx.getInstance().getCnx();
            PreparedStatement pre;
            pre = pst.prepareStatement(req);
            
            pre.setDate(1, new java.sql.Date(p.getData_reclamation().getTime()));
            pre.setString(2, p.getEtat());
            pre.setString(3, p.getMotif_de_reclamation());
            //pre.setString(5, p.getImage_event());
            pre.setInt(4, p.getNum_telephone());
            pre.setString(5, p.getEmail());
             pre.setInt(6, p.getId());
            
            
            
            pre.executeUpdate();
            System.out.println("Reclamation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
        return false;
    
    }
    
}
