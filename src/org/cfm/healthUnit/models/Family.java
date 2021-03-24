package org.cfm.healthUnit.models;

import java.io.Serializable;
import java.util.*;

public class Family implements Serializable {

    private final ArrayList<Family> families = new ArrayList<>();
    private final Map<Patient, Family> familyMembers = new HashMap<>();
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
                    System.out.println("Family registered with success");
                }
                break;
            }
        }
    }

    public Family findFamily(String familyName) {
        for(Family f : families) {
            if(f.getFamilyName().equals(familyName)) {
                return f;
            }
        }
        return null;
    }

    public void associatePatientToFamily(Patient patientName, Family familyName) {
        boolean found = false;
        if(familyName == null) {
            System.out.println("Family does not exist");
        } else if (patientName == null) {
            System.out.println("Patient does not exist");
        } else {
            if((familyName.getFamilyName() != null) && (patientName.getName() != null)) {
                Set<Patient> patients = familyMembers.keySet();
                for (Patient patient : patients) {
                    if (patient.getName().equals(patientName.getName())){
                        found = true;
                        System.out.println("Patient is already associated");
                        break;
                    }
                }
            }
            if(!found) {
                familyMembers.put(patientName, familyName);
                patientName.setFamilyName(familyName.getFamilyName());
                System.out.println("Patient associated to family");
            }
        }
    }

    public void disassociatePatientToFamily(Patient patientName) {
        if(patientName == null) {
            System.out.println("Patient does not exist");
        } else if (familyMembers.get(patientName) == null) {
            System.out.println("Patient does not belong to family");
        } else {
            familyMembers.remove(patientName);
            System.out.println("Patient disassociated to family");
        }
    }

    public void showFamilies() {
        if(families.size() == 0) {
            System.out.println("No families registered");
        } else {
            families.sort(Comparator.comparing(Family::getFamilyName));
            for(Family family : families) {
                System.out.println(family.getFamilyName());
            }
        }
    }

    public void showPatientFamily(Family familyName) {
        if(familyName == null) {
            System.out.println("Family does not exist");
        } else {
            ArrayList<Patient> fullFamily = new ArrayList<>();
            for (Map.Entry<Patient, Family> member : familyMembers.entrySet()) {
                if (member.getValue().equals(familyName)) {
                    fullFamily.add(member.getKey());
                }
            }
            fullFamily.sort(Comparator.comparing(Patient::getAgeRange).thenComparing(Patient::getName));
            for (Patient member : fullFamily) {
                System.out.println(member.getAgeRange() + " " + member.getName());
            }
        }
    }
}
