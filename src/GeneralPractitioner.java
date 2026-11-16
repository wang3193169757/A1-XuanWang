import java.util.List;

/**
 * A general practitioner (GP). Reuses the shared fields and behaviour from
 * {@link HealthProfessional} and adds the GP-only consultation length.
 */
public class GeneralPractitioner extends HealthProfessional {

    /** Standard consultation length in minutes. */
    private int consultationMinutes;

    /**
     * @param id                  numeric-only ID
     * @param name                GP name
     * @param workingDays         roster days
     * @param consultationMinutes consultation length in minutes, must be positive
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
     * @param consultationMinutes consultation length in minutes
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
     * Prescribes medication to a patient.
     *
     * @param patient    patient receiving the medication
     * @param medication prescribed medication name
     * @return description of the prescription
     */
    public String prescribe(Patient patient, String medication) {
        return getName() + " (" + getSpecialty() + ") prescribed "
                + medication + " to " + patient.getName() + ".";
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Consultation length: " + consultationMinutes + " min";
    }
}
