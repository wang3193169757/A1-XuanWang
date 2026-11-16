import java.util.List;

/**
 * A medical specialist, such as a cardiologist or paediatrician. Extends
 * {@link HealthProfessional} and records the medical sub-specialty.
 */
public class Specialist extends HealthProfessional {

    /** Medical sub-specialty, e.g. "Cardiology". */
    private String subSpecialty;

    /**
     * @param id           numeric-only ID
     * @param name         specialist name
     * @param workingDays  roster days
     * @param subSpecialty medical sub-specialty, must not be blank
     */
    public Specialist(String id, String name, List<String> workingDays,
                      String subSpecialty) {
        super(id, name, workingDays);
        if (subSpecialty == null || subSpecialty.isBlank()) {
            throw new IllegalArgumentException("Sub-specialty must not be blank.");
        }
        this.subSpecialty = subSpecialty;
    }

    /**
     * Shorter constructor using the standard Monday-Friday roster.
     *
     * @param id           numeric-only ID
     * @param name         specialist name
     * @param subSpecialty medical sub-specialty
     */
    public Specialist(String id, String name, String subSpecialty) {
        super(id, name);
        if (subSpecialty == null || subSpecialty.isBlank()) {
            throw new IllegalArgumentException("Sub-specialty must not be blank.");
        }
        this.subSpecialty = subSpecialty;
    }

    @Override
    public String getSpecialty() {
        return "Specialist (" + subSpecialty + ")";
    }

    public String getSubSpecialty() {
        return subSpecialty;
    }

    /**
     * Reviews a referred patient.
     *
     * @param patient referred patient
     * @return description of the review
     */
    public String reviewPatient(Patient patient) {
        return getName() + ", " + subSpecialty + " specialist, reviewed "
                + patient.getName() + ".";
    }

    @Override
    public String toString() {
        return super.toString() + " | Sub-specialty: " + subSpecialty;
    }
}
