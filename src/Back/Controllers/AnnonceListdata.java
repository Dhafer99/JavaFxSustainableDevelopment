/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Models.Annonces;
import Back.Services.ServiceAnnonce;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fares CHAKROUN
 */
public class AnnonceListdata {
 
    private ObservableList<Annonces> persons=FXCollections.observableArrayList();

    public AnnonceListdata() {
        ServiceAnnonce pdao=ServiceAnnonce.getInstance();
        persons=  (ObservableList<Annonces>) pdao.displayAll();
        System.out.println(persons);  
    }
    
  public ObservableList<Annonces> getPersons(){
        return persons;
    }

}
