package org.cfm.healthUnit;

import org.cfm.healthUnit.models.Family;
import org.cfm.healthUnit.models.Patient;
import org.cfm.healthUnit.models.Professional;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Professional professionalClass = new Professional();
        Patient patientClass = new Patient();
        Family familyClass = new Family();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.isEmpty()) {
                break;
            }
            String[] inputSplit = input.split(" ");
            switch (inputSplit[0]) {
                case "RP":
                    professionalClass.register(inputSplit[1], inputSplit[2]);
                    break;
                case "RU":
                    patientClass.register(inputSplit[1], inputSplit[2]);
                    break;
                case "RF":
                    familyClass.register(inputSplit[1]);
                    break;
                case "AF":
                    familyClass.associatePatientToFamily(patientClass.findPatient(inputSplit[1]), familyClass.findFamily(inputSplit[2]));
                    break;
                case "DF":
                    familyClass.disassociatePatientToFamily(patientClass.findPatient(inputSplit[1]));
                    break;
                case "LP":
                    professionalClass.showProfessionals();
                    break;
                case "LU":
                    patientClass.showPatients();
                    break;
                case "LF":
                    //TODO
                    break;
                case "MF":
                    //TODO
                    break;
                case "MC":
                    //TODO
                    break;
                case "CC":
                    //TODO
                    break;
                case "LCU":
                    //TODO
                    break;
                case "LCF":
                    //TODO
                    break;
                case "LSP":
                    //TODO
                    break;
                case "LMS":
                    //TODO
                    break;
                case "G":
                    //TODO
                    break;
                case "L":
                    //TODO
                    break;
                default:
                    System.out.println("Invalid statement");
            }
        }

        sc.close();
        System.out.println("fin");


    }
}
