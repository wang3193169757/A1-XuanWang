import java.util.List;

/**
 * Part 2 - A nurse.
 *
 * <p>OOP concept: INHERITANCE + POLYMORPHISM.</p>
 * A nurse extends {@link HealthProfessional}, records the ward they work on,
 * and provides a nurse-specific action (taking a blood sample).
 */
public class Nurse extends HealthProfessional {

    /** Ward the nurse is assigned to, e.g. "Emergency", "Ward 3B". */
    private String ward;

    /**
     * Full constructor.
     *
     * @param id          numeric-only ID
     * @param name        nurse name
     * @param workingDays roster days
     * @param ward        assigned ward
     */
    public Nurse(String id, String name, List<String> workingDays, String ward) {
        super(id, name, workingDays);
        if (ward == null || ward.isBlank()) {
            throw new IllegalArgumentException("Ward must not be blank.");
        }
        this.ward = ward;
    }

    /**
     * Shorter constructor using the standard Monday-Friday roster.
     *
     * @param id   numeric-only ID
     * @param name nurse name
     * @param ward assigned ward
     */
    public Nurse(String id, String name, String ward) {
        super(id, name);
        if (ward == null || ward.isBlank()) {
            throw new IllegalArgumentException("Ward must not be blank.");
        }
        this.ward = ward;
    }

    @Override
    public String getSpecialty() {
        return "Nurse";
    }

    public String getWard() {
        return ward;
    }

    /**
     * Nurse-specific behaviour: takes a blood sample from a patient.
     *
     * @param patient patient being sampled
     * @return description of the blood sample
     */
    public String takeBloodSample(Patient patient) {
        return getName() + " (nurse, " + ward + ") took a blood sample from "
                + patient.getName() + ".";
    }

    @Override
    public String toString() {
        return super.toString() + " | Ward: " + ward;
    }
}
