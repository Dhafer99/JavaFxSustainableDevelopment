/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Controllers;

import Back.Models.Categorie_Rec;
import Back.Services.CategorieReclamationsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fares CHAKROUN
 */
public class CategorieReclamationListdata{
    private ObservableList<Categorie_Rec> persons=FXCollections.observableArrayList();

    public CategorieReclamationListdata() {
        CategorieReclamationsService pdao=CategorieReclamationsService.getInstance();
        persons=  (ObservableList<Categorie_Rec>) pdao.displayAll();
        System.out.println(persons);  
    }
    
  public ObservableList<Categorie_Rec> getPersons(){
        return persons;
    }
}
