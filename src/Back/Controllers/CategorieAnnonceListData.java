/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Models.Categorie;
import Back.Services.ServiceCategorieAnnonce;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fares CHAKROUN
 */
public class CategorieAnnonceListData {
  private ObservableList<Categorie> persons=FXCollections.observableArrayList();

    public CategorieAnnonceListData() {
        ServiceCategorieAnnonce pdao=ServiceCategorieAnnonce.getInstance();
        persons=  (ObservableList<Categorie>) pdao.displayAll();
        System.out.println(persons);  
    }
    
  public ObservableList<Categorie> getPersons(){
        return persons;
    }
}
