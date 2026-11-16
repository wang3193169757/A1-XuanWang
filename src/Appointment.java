/**
 * Part 4 - A single appointment.
 *
 * <p>An appointment links one {@link Patient} to any type of
 * {@link HealthProfessional} for one clinic slot. Because the appointment
 * depends only on the abstract parent type, it works with GP, Specialist,
 * Surgeon and Nurse objects alike.</p>
 *
 * <p>The constructor rejects missing or invalid data, so an appointment can
 * never exist in an incomplete state. {@link Comparable} lets a whole
 * collection of appointments be sorted from earliest to latest.</p>
 */
public class Appointment implements Comparable<Appointment> {

    /** The patient who booked. */
    private Patient patient;

    /** The chosen professional (any concrete subtype). */
    private HealthProfessional professional;

    /** Booking slot in "HH:mm" format, from {@link ClinicSchedule}. */
    private String timeSlot;

    /**
     * Constructs an appointment after validating every required detail.
     *
     * @param patient      booking patient (must not be null)
     * @param professional chosen professional (must not be null)
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
     * Orders appointments by slot, earliest first. If two appointments share
     * the same slot, they are ordered by professional ID to keep the result
     * stable.
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

    /**
     * Full appointment summary, including the professional's own details.
     *
     * @return readable appointment description
     */
    @Override
    public String toString() {
        return "Appointment | Time: " + timeSlot
                + " | Patient: " + patient
                + " | Professional: " + professional;
    }
}

