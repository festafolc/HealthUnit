package org.cfm.healthUnit.enums;

public enum Category {
    MEDICINE("Medicine"),
    NURSING("Nursing"),
    AUXILIARY("Auxiliary");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return this.category;
    }
}
