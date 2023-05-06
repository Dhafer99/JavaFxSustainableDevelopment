/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back.Models;

/**
 *
 * @author 21629
 */
public class Category_d {
    private int id ;
    private String name_ca;

    public Category_d() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_ca() {
        return name_ca;
    }

    public void setName_ca(String name_ca) {
        this.name_ca = name_ca;
    }

    public Category_d(String name_ca) {
        this.name_ca = name_ca;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name_ca=" + name_ca + '}';
    }

    public Category_d(int id, String name_ca) {
        this.id = id;
        this.name_ca = name_ca;
    }
    
}
