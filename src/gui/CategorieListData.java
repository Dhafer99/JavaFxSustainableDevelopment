/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.CategorieService;

/**
 *
 * @author Asus
 */

public class CategorieListData {
    
    private ObservableList<Categorie> persons=FXCollections.observableArrayList();

    public CategorieListData() {
        CategorieService pdao=CategorieService.getInstance();
        persons=  (ObservableList<Categorie>) pdao.displayAll();
        System.out.println(persons);  
    }
    
  public ObservableList<Categorie> getPersons(){
        return persons;
    }
    }
