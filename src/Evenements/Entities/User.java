/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenements.Entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 *
 * @author sarah
 */
public class User {
    
    private int id;
    private String name;
    
    private ObservableSet<Evenement> evenements = FXCollections.observableSet();

    public ObservableSet<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(ObservableSet<Evenement> evenements) {
        this.evenements = evenements;
    }
    
    public void addEvenement(Evenement evenement) {
    evenements.add(evenement);
    evenement.getUsers().add(this);
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + '}';
    }
    
    
    
}
