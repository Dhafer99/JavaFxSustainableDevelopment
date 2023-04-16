/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.categorieA;
import Services.CategorieAssoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fares CHAKROUN
 */
public class CategorieAssoListdata {
  private ObservableList<categorieA> persons1=FXCollections.observableArrayList();
  public CategorieAssoListdata() {
        CategorieAssoService pdao=CategorieAssoService.getInstance();
        persons1=  (ObservableList<categorieA>) pdao.displayAll();
        System.out.println(persons1);  
    }
    
  public ObservableList<categorieA> getPersons(){
        return persons1;
    }  
}
