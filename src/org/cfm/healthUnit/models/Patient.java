package org.cfm.healthUnit.models;

import org.cfm.healthUnit.enums.AgeRange;
import org.cfm.healthUnit.interfaces.Registrable;

import java.util.ArrayList;

public class Patient extends Person implements Registrable {

    private AgeRange ageRange;
    private ArrayList<Patient> patients = new ArrayList<>();

    public Patient() {
        super();
    }

    public Patient(String name, AgeRange ageRange) {
        super();
        this.name = name;
        this.ageRange = ageRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }


    @Override
    public void register(String name, String ageRange) { // Solucionar la condición de si él nombre existe no se puede añadir
        boolean found = false;
        if (!ageRange.equalsIgnoreCase("YOUNG") && !ageRange.equalsIgnoreCase("ADULT") && !ageRange.equalsIgnoreCase("OLD")) {
            System.out.println("Age range does not exist");
        } else {
            for(Patient p : patients) {
                if(p.getName().equals(name)) {
                    found = true;
                    System.out.println("Patient already exists");
                    break;
                }
            }
            if(!found) {
                Patient patient = new Patient(name, AgeRange.valueOf(ageRange.toUpperCase()));
                patients.add(patient);
                System.out.println("Patient registered with success");
            }
        }
    }
}
