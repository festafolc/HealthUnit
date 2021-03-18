package org.cfm.healthUnit.models;

import org.cfm.healthUnit.enums.Category;
import org.cfm.healthUnit.interfaces.Listable;
import org.cfm.healthUnit.interfaces.Registrable;

import java.util.ArrayList;
import java.util.Collections;

public class Professional extends Person implements Registrable, Listable, Comparable {

    private ArrayList<Professional> professionals = new ArrayList<>();
    private Category category;

    public Professional() {
        super();
    }

    public Professional(Category category, String name) {
        super();
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Professional> getProfessionals() {
        return professionals;
    }

    public void setProfessionals(ArrayList<Professional> professionals) {
        this.professionals = professionals;
    }

    public boolean checkProfessional(Category category, String name) {
        for (Professional professional : professionals) {
            if(professional.getCategory() == category && professional.getName() == name) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void register(String category, String name) {
        try {
            boolean found = false;
            if (!category.equalsIgnoreCase("MEDICINE") &&
                    !category.equalsIgnoreCase("NURSING") &&
                    !category.equalsIgnoreCase("AUXILIARY")) {
                System.out.println("Category does not exist");
            } else {
                for(Professional professional : professionals) {
                    if(professional.getCategory().equals(Category.valueOf(category.toUpperCase())) && professional.getName().equals(name)) {
                        found = true;
                        System.out.println("Professional already exists");
                        break;
                    }
                }
                if(!found) {
                    Professional p = new Professional(Category.valueOf(category.toUpperCase()), name);
                    professionals.add(p);
                    System.out.println("Professional registered with success");
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void recorrerLista() {
        Collections.sort(professionals);
        for (Professional professional : professionals) {
            System.out.println(professional);
        }
    }

    @Override
    public String toString() {
        return name + " " + category;
    }

    @Override
    public int compareTo(Object o) {
        Professional p = (Professional) o;
        return this.name.compareTo(p.name);
    }
}
