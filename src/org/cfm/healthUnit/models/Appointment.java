package org.cfm.healthUnit.models;

import org.cfm.healthUnit.enums.Category;
import org.cfm.healthUnit.enums.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Appointment implements Serializable {

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

    public void newAppointment(Patient patient, Professional professionalClass) {
        if (patient != null) {
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
                            Professional professional = professionalClass.checkProfessional(Category.valueOf(professionalInput[0].toUpperCase()), professionalInput[1]);
                            if (professional != null) {
                                appointments.add(new Appointment(patient, professional, Service.valueOf(input.toUpperCase())));
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
                            Professional professional = professionalClass.checkProfessional(Category.valueOf(professionalInput[0].toUpperCase()), professionalInput[1]);
                            if (professional != null) {
                                if (checkSequenceService(patient) == null) {
                                    System.out.println("Invalid sequence");
                                } else if (checkSequenceService(patient).equals(Service.CONSULTATION)) {
                                    appointments.add(new Appointment(patient, professional, Service.valueOf(input.toUpperCase())));
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
                            Professional professional = professionalClass.checkProfessional(Category.valueOf(professionalInput[0].toUpperCase()), professionalInput[1]);
                            if (professional != null) {
                                if ((checkSequenceService(patient) == null)) {
                                    appointments.add(new Appointment(patient, professional, Service.valueOf(input.toUpperCase())));
                                    System.out.println("Appointment created");
                                } else if ((!checkSequenceService(patient).equals(Service.SURGERY))) {
                                    appointments.add(new Appointment(patient, professional, Service.valueOf(input.toUpperCase())));
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


    public void cancelAppointment(Patient patient) {
        if(patient != null) {
            if(checkSequenceService(patient) != null) {
                appointments.removeIf(appointment -> appointment.getPatient().equals(patient));
                System.out.println("Appointments cancelled successfully");
            } else {
                System.out.println("No appointments for the patient");
            }
        } else {
            System.out.println("Patient does not exist");
        }
    }

    public void showPatientAppointments(Patient patient) {
        if(patient != null) {
            if(checkSequenceService(patient) != null) {
                List<Appointment> sequencePatient = new ArrayList<>();
                for (Appointment appointment : appointments) {
                    if (appointment.getPatient().equals(patient)) {
                        sequencePatient.add(appointment);
                    }
                }
                sequencePatient.sort(Comparator.comparing(Appointment::getService));
                for(Appointment sequence : sequencePatient) {
                    System.out.println(sequence.getService() + " " + sequence.getProfessional().getCategory() + " " + sequence.getProfessional().getName());
                }
            } else {
                System.out.println("No appointments for the patient");
            }
        } else {
            System.out.println("Patient does not exist");
        }
    }

    public void showFamilyAppointments(Family family) {
        if(family != null) {
            List<Appointment> familyAppointments = new ArrayList<>();
            for (Appointment appointment : appointments) {
                if(appointment.getPatient().getFamilyName().equals(family.getFamilyName())) {
                    familyAppointments.add(appointment);
                }
            }
            if (familyAppointments.size() > 0) {
                familyAppointments.sort(Comparator.comparing(Appointment::getService));
                for(Appointment appointment : familyAppointments) {
                    System.out.println(appointment.getPatient().getName() + " " + appointment.getService() + " " + appointment.getProfessional().getCategory() + " " + appointment.getProfessional().getName());
                }
            } else {
                System.out.println("Family without appointments");
            }
        } else {
            System.out.println("Family does not exist");
        }
    }

    public void showProfessionalAppointments(Professional professional) {
        if (professional != null) {
            List<Appointment> professionalAppointments = new ArrayList<>();
            for (Appointment appointment : appointments) {
                if((appointment.getProfessional().getCategory().equals(professional.getCategory())) && appointment.getProfessional().getName().equals(professional.getName())) {
                    professionalAppointments.add(appointment);
                }
            }
            if (professionalAppointments.size() > 0) {
                professionalAppointments.sort(Comparator.comparing(Appointment::getService));
                for (Appointment appointment : professionalAppointments) {
                    System.out.println(appointment.getService() + " " + appointment.getPatient().getName());
                }
            } else {
                System.out.println("Professional has not appointments");
            }
        } else {
            System.out.println("Professional does not exist");
        }
    }

    public void showServiceAppointments(String service) {
        if(service.equalsIgnoreCase("CONSULTATION") ||
                service.equalsIgnoreCase("SURGERY") ||
                service.equalsIgnoreCase("NURSING")) {
            List<Appointment> serviceAppointments = new ArrayList<>();
            for (Appointment appointment : appointments) {
                if (appointment.getService().name().equalsIgnoreCase(service)) {
                    serviceAppointments.add(appointment);
                }
            }
            if(serviceAppointments.size() > 0) {
                serviceAppointments.sort(Comparator.comparing(Appointment::getService));
                for (Appointment appointment : serviceAppointments) {
                    System.out.println(appointment.getProfessional().getCategory() + " " + appointment.getProfessional().getName() + " " + appointment.getPatient().getName());
                }
            } else {
                System.out.println("Service without appointments");
            }
        } else {
            System.out.println("Service does not exist");
        }
    }
}
