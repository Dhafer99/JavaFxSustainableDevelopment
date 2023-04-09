/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Reclamation;
import ServicesReclamations.ReclamationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ghofrane
 */
public class Listdata {
    private ObservableList<Reclamation> persons=FXCollections.observableArrayList();

    public Listdata() {
        ReclamationService pdao=ReclamationService.getInstance();
        persons=  (ObservableList<Reclamation>) pdao.displayAll();
        System.out.println(persons);  
    }
    
  public ObservableList<Reclamation> getPersons(){
        return persons;
    }
}
