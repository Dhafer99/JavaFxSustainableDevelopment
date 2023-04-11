/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entite.Categorie_Rec;
import Entite.Reclamation;
import ServicesReclamations.CategorieRecService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ghofrane
 */
public class CategorieListdata {
    private ObservableList<Categorie_Rec> persons=FXCollections.observableArrayList();

    public CategorieListdata() {
        CategorieRecService pdao=CategorieRecService.getInstance();
        persons=  (ObservableList<Categorie_Rec>) pdao.displayAll();
        System.out.println(persons);  
    }
    
  public ObservableList<Categorie_Rec> getPersons(){
        return persons;
    }
    
}
