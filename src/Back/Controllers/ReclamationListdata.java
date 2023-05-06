/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Models.Reclamation;
import Back.Services.ReclamationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fares CHAKROUN
 */
public class ReclamationListdata {
     private ObservableList<Reclamation> persons=FXCollections.observableArrayList();

    public ReclamationListdata() {
        ReclamationService pdao=ReclamationService.getInstance();
        persons=  (ObservableList<Reclamation>) pdao.displayAll();
        System.out.println(persons);  
    }
    
  public ObservableList<Reclamation> getPersons(){
        return persons;
    }
}
