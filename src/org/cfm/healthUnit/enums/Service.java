package org.cfm.healthUnit.enums;

public enum Service {
    CONSULTATION("Consultation"),
    SURGERY("Surgery"),
    NURSING("Nursing");

    private final String service;


    Service(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }

    @Override
    public String toString() {
        return service;
    }
}
