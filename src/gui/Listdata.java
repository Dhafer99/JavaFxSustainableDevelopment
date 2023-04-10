
package gui;

import entities.Annonces;
import services.AnnonceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Listdata {
    private ObservableList<Annonces> persons=FXCollections.observableArrayList();

    public Listdata() {
        AnnonceService pdao=AnnonceService.getInstance();
        persons=  (ObservableList<Annonces>) pdao.displayAll();
        System.out.println(persons);  
    }
    
  public ObservableList<Annonces> getPersons(){
        return persons;
    }
}
