import java.util.List;

/**
 * Part 2 - A surgeon.
 *
 * <p>OOP concept: INHERITANCE.</p>
 * A surgeon extends {@link HealthProfessional} and records the surgical area
 * they work in, together with a surgeon-specific action.
 */
public class Surgeon extends HealthProfessional {

    /** Surgical area, e.g. "Orthopaedics", "General Surgery". */
    private String surgeryArea;

    /**
     * Full constructor.
     *
     * @param id          numeric-only ID
     * @param name        surgeon name
     * @param workingDays roster days
     * @param surgeryArea surgical area
     */
    public Surgeon(String id, String name, List<String> workingDays,
                   String surgeryArea) {
        super(id, name, workingDays);
        if (surgeryArea == null || surgeryArea.isBlank()) {
            throw new IllegalArgumentException("Surgery area must not be blank.");
        }
        this.surgeryArea = surgeryArea;
    }

    /**
     * Shorter constructor using the standard Monday-Friday roster.
     *
     * @param id          numeric-only ID
     * @param name        surgeon name
     * @param surgeryArea surgical area
     */
    public Surgeon(String id, String name, String surgeryArea) {
        super(id, name);
        if (surgeryArea == null || surgeryArea.isBlank()) {
            throw new IllegalArgumentException("Surgery area must not be blank.");
        }
        this.surgeryArea = surgeryArea;
    }

    @Override
    public String getSpecialty() {
        return "Surgeon (" + surgeryArea + ")";
    }

    public String getSurgeryArea() {
        return surgeryArea;
    }

    /**
     * Surgeon-specific behaviour: prepares an operating list for a patient.
     *
     * @param patient patient to be prepared
     * @return description of the surgical preparation
     */
    public String prepareOperation(Patient patient) {
        return getName() + " (" + surgeryArea + ") prepared "
                + patient.getName() + " for surgery.";
    }

    @Override
    public String toString() {
        return super.toString() + " | Surgery area: " + surgeryArea;
    }
}

