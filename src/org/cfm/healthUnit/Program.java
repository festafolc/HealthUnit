package org.cfm.healthUnit;

import org.cfm.healthUnit.enums.Category;
import org.cfm.healthUnit.enums.Service;
import org.cfm.healthUnit.models.Appointment;
import org.cfm.healthUnit.models.Family;
import org.cfm.healthUnit.models.Patient;
import org.cfm.healthUnit.models.Professional;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Professional professionalClass = new Professional();
        Patient patientClass = new Patient();
        Family familyClass = new Family();
        Appointment appointmentClass = new Appointment();

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
                    familyClass.showFamilies();
                    break;
                case "MF":
                    familyClass.showPatientFamily(familyClass.findFamily(inputSplit[1]));
                    break;
                case "MC":
                    appointmentClass.newAppointment(patientClass.findPatient(inputSplit[1]), professionalClass);
                    break;
                case "CC":
                    appointmentClass.cancelAppointment(patientClass.findPatient(inputSplit[1]));
                    break;
                case "LCU":
                    appointmentClass.showPatientAppointments(patientClass.findPatient(inputSplit[1]));
                    break;
                case "LCF":
                    appointmentClass.showFamilyAppointments(familyClass.findFamily(inputSplit[1]));
                    break;
                case "LSP":
                    appointmentClass.showProfessionalAppointments(professionalClass.checkProfessional(Category.valueOf(inputSplit[1].toUpperCase()), inputSplit[2]));
                    //appointmentClass.showProfessionalAppointments(Category.valueOf(inputSplit[1].toUpperCase()), professionalClass.findProfessional(inputSplit[2]));
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
    }
}
