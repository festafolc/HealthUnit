package org.cfm.healthUnit.models;

import org.cfm.healthUnit.interfaces.Registrable;

import java.util.ArrayList;

public class Family {

    private ArrayList<Family> families = new ArrayList<>();
    private String familyName;

    public Family() {
    }

    public Family(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void register(String familyName) {
        if(families.size() == 0) {
            Family family = new Family(familyName);
            families.add(family);
            System.out.println("Family registered with success");
        } else {
            for (Family f : families) {
                if(f.getFamilyName().equals(familyName)) {
                    System.out.println("Family already exists");
                } else {
                    Family family = new Family(familyName);
                    families.add(family);
                    System.out.println("Family registered with success2");
                }
                break;
            }
        }
    }
}
