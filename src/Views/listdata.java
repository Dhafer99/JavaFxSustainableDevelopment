/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Association;
import ServiceAssociation.AssociationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fares CHAKROUN
 */
public class listdata {

   private ObservableList<Association> persons=FXCollections.observableArrayList();

    public listdata() {
        AssociationService pdao=AssociationService.getInstance();
        persons=  (ObservableList<Association>) pdao.displayAll();
        System.out.println(persons);  
    }
    
  public ObservableList<Association> getPersons(){
        return persons;
    }
    
}
