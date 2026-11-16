/**
 * One appointment links a {@link Patient} to a chosen
 * {@link HealthProfessional} for one clinic slot. It depends only on the
 * abstract parent type, so it works with any professional subtype.
 * {@link Comparable} lets the appointments be sorted from earliest to latest.
 */
public class Appointment implements Comparable<Appointment> {

    private Patient patient;
    private HealthProfessional professional;
    private String timeSlot;

    /**
     * Checks that every required detail is present before creating the appointment.
     *
     * @param patient      booking patient, must not be null
     * @param professional chosen professional, must not be null
     * @param timeSlot     valid clinic slot, e.g. "09:00"
     */
    public Appointment(Patient patient, HealthProfessional professional,
                       String timeSlot) {
        if (patient == null) {
            throw new IllegalArgumentException("Appointment needs a patient.");
        }
        if (professional == null) {
            throw new IllegalArgumentException(
                    "Appointment needs a health professional.");
        }
        if (!ClinicSchedule.isValidSlot(timeSlot)) {
            throw new IllegalArgumentException(
                    "Time slot \"" + timeSlot + "\" is not in the clinic schedule.");
        }
        this.patient = patient;
        this.professional = professional;
        this.timeSlot = timeSlot;
    }

    public Patient getPatient() {
        return patient;
    }

    public HealthProfessional getProfessional() {
        return professional;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * Orders by slot, earliest first; ties are broken by professional ID
     * to keep the result stable.
     *
     * @param other the appointment to compare against
     * @return negative, zero or positive as in {@link Comparable}
     */
    @Override
    public int compareTo(Appointment other) {
        int bySlot = Integer.compare(
                ClinicSchedule.slotToMinutes(this.timeSlot),
                ClinicSchedule.slotToMinutes(other.timeSlot));
        if (bySlot != 0) {
            return bySlot;
        }
        return this.professional.getId().compareTo(other.professional.getId());
    }

    @Override
    public String toString() {
        return "Appointment | Time: " + timeSlot
                + " | Patient: " + patient
                + " | Professional: " + professional;
    }
}
