import java.util.List;

/**
 * Part 2 - A general practitioner (GP).
 *
 * <p>OOP concept: INHERITANCE.</p>
 * A GP reuses every shared field and behaviour from
 * {@link HealthProfessional} through {@code extends}, and only adds the
 * information and functions that are unique to a GP.
 */
public class GeneralPractitioner extends HealthProfessional {

    /** Standard consultation length in minutes for this GP. */
    private int consultationMinutes;

    /**
     * Full constructor.
     *
     * @param id                   numeric-only ID
     * @param name                 GP name
     * @param workingDays          roster days
     * @param consultationMinutes  standard consultation length (minutes)
     */
    public GeneralPractitioner(String id, String name, List<String> workingDays,
                               int consultationMinutes) {
        super(id, name, workingDays);
        if (consultationMinutes <= 0) {
            throw new IllegalArgumentException("Consultation minutes must be positive.");
        }
        this.consultationMinutes = consultationMinutes;
    }

    /**
     * Shorter constructor using the standard Monday-Friday roster.
     *
     * @param id                  numeric-only ID
     * @param name                GP name
     * @param consultationMinutes standard consultation length (minutes)
     */
    public GeneralPractitioner(String id, String name, int consultationMinutes) {
        super(id, name);
        if (consultationMinutes <= 0) {
            throw new IllegalArgumentException("Consultation minutes must be positive.");
        }
        this.consultationMinutes = consultationMinutes;
    }

    @Override
    public String getSpecialty() {
        return "General Practitioner";
    }

    public int getConsultationMinutes() {
        return consultationMinutes;
    }

    /**
     * GP-specific behaviour: a GP can prescribe medication to a patient.
     *
     * @param patient   the patient receiving the medication
     * @param medication prescribed medication name
     * @return description of the prescription
     */
    public String prescribe(Patient patient, String medication) {
        return getName() + " (" + getSpecialty() + ") prescribed "
                + medication + " to " + patient.getName() + ".";
    }

    /**
     * Reuses the shared summary from the parent class and appends the GP-only
     * field. This is a form of runtime polymorphism: printing a GP through a
     * common reference still shows the GP's own details.
     *
     * @return full GP details
     */
    @Override
    public String toString() {
        return super.toString()
                + " | Consultation length: " + consultationMinutes + " min";
    }
}
