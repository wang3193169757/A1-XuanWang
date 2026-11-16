/**
 * Patient details: name and contact mobile number, kept in their own class
 * so the data is encapsulated and reusable.
 */
public class Patient {

    private String name;
    private String mobile;

    /**
     * @param name   patient name, must not be blank
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
