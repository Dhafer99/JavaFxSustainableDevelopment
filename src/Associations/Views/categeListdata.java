/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Associations.Views;

/**
 *
 * @author Fares CHAKROUN
 */


import Associations.Models.Association;
import Associations.Models.categorieA;
import Associations.Services.categorieService;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class categeListdata {
   private ObservableList<categorieA> persons1=FXCollections.observableArrayList();
  public categeListdata() {
        categorieService pdao=categorieService.getInstance();
        persons1=  (ObservableList<categorieA>) pdao.displayAll();
        System.out.println(persons1);  
    }
    
  public ObservableList<categorieA> getPersons(){
        return persons1;
    }
       
    
    
}
