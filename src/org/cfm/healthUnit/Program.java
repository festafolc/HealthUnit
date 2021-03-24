package org.cfm.healthUnit;

import org.cfm.healthUnit.enums.Category;
import org.cfm.healthUnit.models.Appointment;
import org.cfm.healthUnit.models.Family;
import org.cfm.healthUnit.models.Patient;
import org.cfm.healthUnit.models.Professional;

import java.io.*;
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
                    break;
                case "LMS":
                    appointmentClass.showServiceAppointments(inputSplit[1]);
                    break;
                case "G":
                    try {
                        FileOutputStream file = new FileOutputStream("healthUnit.ser");
                        ObjectOutputStream out = new ObjectOutputStream(file);
                        out.writeObject(professionalClass);
                        out.writeObject(patientClass);
                        out.writeObject(familyClass);
                        out.writeObject(appointmentClass);
                        out.close();
                        file.close();
                        System.out.println("Health Unit saved");
                    } catch (IOException e) {
                        System.out.println("Something wrong happened");
                    }
                    break;
                case "L":
                    try {
                        FileInputStream file = new FileInputStream("healthUnit.ser");
                        ObjectInputStream in = new ObjectInputStream(file);
                        professionalClass = (Professional) in.readObject();
                        patientClass = (Patient) in.readObject();
                        familyClass = (Family) in.readObject();
                        appointmentClass = (Appointment) in.readObject();
                        in.close();
                        file.close();
                        System.out.println("Health Unit loaded");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Something went wrong");
                    }
                    break;
                default:
                    System.out.println("Invalid statement");
            }
        }
        sc.close();
    }
}
