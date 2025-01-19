package org.example.presentation.records;

// used in the add medical case select
public record PatientDisplay(
        Long id, String fullName
) {
    @Override
    public String toString() {
        return fullName;
    }
}
