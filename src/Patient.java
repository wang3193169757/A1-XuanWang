/**
 * Part 4 - A patient.
 *
 * <p>Patient details (name and mobile number) are kept in their own small
 * class instead of being stored as loose strings inside an appointment. This
 * keeps the patient data encapsulated and reusable.</p>
 */
public class Patient {

    /** Patient full name. */
    private String name;

    /** Contact mobile number (digits only). */
    private String mobile;

    /**
     * Constructs a patient and validates the contact details.
     *
     * @param name   patient name (must not be blank)
     * @param mobile mobile number: digits only, 8 to 15 characters
     */
    public Patient(String name, String mobile) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Patient name must not be blank.");
        }
        if (mobile == null || !mobile.matches("\\d{8,15}")) {
            throw new IllegalArgumentException(
                    "Mobile number must contain 8-15 digits.");
        }
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return name + " (" + mobile + ")";
    }
}
