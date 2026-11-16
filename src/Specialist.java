import java.util.List;

/**
 * Part 2 - A specialist (for example a cardiologist, paediatrician or
 * radiologist).
 *
 * <p>OOP concept: INHERITANCE + POLYMORPHISM.</p>
 * The class extends {@link HealthProfessional} and adds its own sub-specialty
 * field, while its {@link #getSpecialty()} and {@link #toString()} override the
 * inherited behaviour so the specialist responds in its own way.
 */
public class Specialist extends HealthProfessional {

    /** Medical sub-specialty, e.g. "Cardiology", "Paediatrics". */
    private String subSpecialty;

    /**
     * Full constructor.
     *
     * @param id           numeric-only ID
     * @param name         specialist name
     * @param workingDays  roster days
     * @param subSpecialty medical sub-specialty
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
     * Specialist-specific behaviour: reviews a referred patient.
     *
     * @param patient referred patient
     * @return description of the specialist review
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
