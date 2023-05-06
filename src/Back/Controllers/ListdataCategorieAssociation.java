/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Models.categorieA;
import Back.Services.AssociationCategorieService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fares CHAKROUN
 */
public class ListdataCategorieAssociation {
  private ObservableList<categorieA> persons1=FXCollections.observableArrayList();
  public ListdataCategorieAssociation() {
        AssociationCategorieService pdao=AssociationCategorieService.getInstance();
        persons1=  (ObservableList<categorieA>) pdao.displayAll();
        System.out.println(persons1);  
    }
    
  public ObservableList<categorieA> getPersons(){
        return persons1;
    }
       
       
}
