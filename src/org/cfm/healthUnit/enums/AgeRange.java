package org.cfm.healthUnit.enums;

public enum AgeRange {
    YOUNG("Young"),
    ADULT("Adult"),
    OLD("Old");

    private final String ageRange;

    AgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getAgeRange() {
        return ageRange;
    }

    @Override
    public String toString() {
        return this.ageRange;
    }
}
