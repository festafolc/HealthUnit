package org.cfm.healthUnit.models;

import org.cfm.healthUnit.enums.Category;
import org.cfm.healthUnit.enums.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Appointment {

    private Patient patient;
    private Professional professional;
    private Service service;
    private List<Appointment> appointments = new ArrayList<>();

    public Appointment() {
    }

    public Appointment(Patient patient, Professional professional, Service service) {
        this.patient = patient;
        this.professional = professional;
        this.service = service;
    }

    public Patient getPatient() {
        return patient;
    }

    public Professional getProfessional() {
        return professional;
    }

    public Service getService() {
        return service;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void newAppointment(Patient name, Professional professionalClass) {
        if (name != null) {
            Scanner sc = new Scanner(System.in);
            String[] professionalInput;
            while (true) {
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    break;
                }
                switch (input) {
                    case "Consultation":
                        String input2 = sc.nextLine();
                        professionalInput = input2.split(" ");
                        if (professionalInput[0].equalsIgnoreCase("MEDICINE")) {
                            if (professionalClass.checkProfessional(Category.valueOf(professionalInput[0].toUpperCase()), professionalInput[1])) {
                                appointments.add(new Appointment(name, professional, Service.valueOf(input.toUpperCase())));
                                System.out.println("Appointment created");
                            } else {
                                System.out.println("Professional does not exist");
                            }
                        } else {
                            System.out.println("Category does not exist");
                        }
                        break;
                    case "Surgery":
                        input2 = sc.nextLine();
                        professionalInput = input2.split(" ");
                        if (professionalInput[0].equalsIgnoreCase("MEDICINE") || professionalInput[0].equalsIgnoreCase("NURSING") || professionalInput[0].equalsIgnoreCase("AUXILIARY")) {
                            if (professionalClass.checkProfessional(Category.valueOf(professionalInput[0].toUpperCase()), professionalInput[1])) {
                                if (checkSequenceService(name) == null) {
                                    System.out.println("Invalid sequence");
                                } else if (checkSequenceService(name).equals(Service.CONSULTATION)) {
                                    appointments.add(new Appointment(name, professional, Service.valueOf(input.toUpperCase())));
                                    System.out.println("Appointment created");
                                } else {
                                    System.out.println("Invalid sequence");
                                }
                            } else {
                                System.out.println("Professional does not exist");
                            }
                        } else {
                            System.out.println("Category does not exist");
                        }
                        break;
                    case "Nursing":
                        input2 = sc.nextLine();
                        professionalInput = input2.split(" ");
                        if (professionalInput[0].equalsIgnoreCase("NURSING") || professionalInput[0].equalsIgnoreCase("AUXILIARY")) {
                            if (professionalClass.checkProfessional(Category.valueOf(professionalInput[0].toUpperCase()), professionalInput[1])) {//   ( (professional.getCategory().equals(Category.NURSING) && professional.getName().equals(professionalInput[1])) || (professional.getCategory().equals(Category.AUXILIARY) && professional.getName().equals(professionalInput[1]))) {
                                if ((checkSequenceService(name) == null)) {
                                    appointments.add(new Appointment(name, professional, Service.valueOf(input.toUpperCase())));
                                    System.out.println("Appointment created");
                                } else if ((!checkSequenceService(name).equals(Service.SURGERY))) {
                                    appointments.add(new Appointment(name, professional, Service.valueOf(input.toUpperCase())));
                                    System.out.println("Appointment created");
                                } else {
                                    System.out.println("Invalid sequence");
                                }
                            } else {
                                System.out.println("Professional does not exist");
                            }
                        } else {
                            System.out.println("Category does not exist");
                        }
                        break;
                    default:
                        System.out.println("Service does not exist");
                }
            }
        } else {
            System.out.println("Patient does not exist");
        }
    }

    public Service checkSequenceService(Patient patient) {
        List<Appointment> sequencePatient = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().equals(patient)) {
                sequencePatient.add(appointment);
            }
        }
        if (sequencePatient.size() == 0) {
            return null;
        } else {
            Appointment lastAppointment = sequencePatient.get(sequencePatient.size() - 1);
            return lastAppointment.getService();
        }
    }
}
