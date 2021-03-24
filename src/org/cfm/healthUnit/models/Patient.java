package org.cfm.healthUnit.models;

import org.cfm.healthUnit.enums.AgeRange;
import org.cfm.healthUnit.interfaces.Registrable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Patient extends Person implements Registrable, Serializable {

    private AgeRange ageRange;
    private String familyName;
    private ArrayList<Patient> patients = new ArrayList<>();

    public Patient() {
        super();
    }

    public Patient(String name, AgeRange ageRange) {
        super();
        this.name = name;
        this.ageRange = ageRange;
        this.familyName = "";
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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Override
    public void register(String name, String ageRange) {
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

    public Patient findPatient(String name) {
        for(Patient p : patients) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public void showPatients() {
        if(patients.size() == 0) {
            System.out.println("No patients registered");
        } else {
            patients.sort(Comparator.comparing(Patient::getAgeRange).thenComparing(Patient::getFamilyName).thenComparing(Patient::getName));
            for(Patient patient : patients) {
                if(!patient.getFamilyName().isEmpty()){
                    System.out.println(patient.getFamilyName() + " " + patient.getAgeRange() + " " + patient.getName());
                } else {
                    System.out.println(patient.getAgeRange() + " " + patient.getName());
                }
            }
        }
    }
}
