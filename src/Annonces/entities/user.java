/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonces.entities;

import java.util.List;

/**
 *
 * @author Asus
 */
public class user {
    
    private String username;
    private List<String> categories;
    
    public user(String username, List<String> categories) {
        this.username = username;

        this.categories = categories;
    }
    
    // Getters et Setters pour les champs de la classe User
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
   
    
    public List<String> getCategories() {
        return categories;
    }
    
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
