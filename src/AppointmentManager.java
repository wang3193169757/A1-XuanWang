import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Part 5 - Manages the collection of the day's appointments.
 *
 * <p>An {@link ArrayList} stores the appointments. The manager:
 * <ul>
 *     <li>rejects any booking that would double-book the same professional in
 *         the same slot;</li>
 *     <li>gives clear feedback when an operation succeeds or fails;</li>
 *     <li>cancels an existing appointment and reports if it cannot be
 *         found;</li>
 *     <li>lists all appointments in time order.</li>
 * </ul>
 * The manager works with the abstract {@link HealthProfessional} type, so it
 * accepts any concrete professional type and future types added later.</p>
 */
public class AppointmentManager {

    /** All appointments held for the current day. */
    private List<Appointment> appointments = new ArrayList<>();

    /**
     * Attempts to add an appointment. A professional cannot be booked twice in
     * the same slot.
     *
     * @param appointment appointment to add
     * @return true if it was added, false if it was rejected
     */
    public boolean addAppointment(Appointment appointment) {
        if (appointment == null) {
            System.out.println("Booking failed: no appointment supplied.");
            return false;
        }

        for (Appointment existing : appointments) {
            boolean sameProfessional =
                    existing.getProfessional()
                            .equals(appointment.getProfessional());
            boolean sameSlot = existing.getTimeSlot().equals(appointment.getTimeSlot());
            if (sameProfessional && sameSlot) {
                System.out.println("Booking rejected: "
                        + appointment.getProfessional().getName()
                        + " is already booked at " + appointment.getTimeSlot() + ".");
                return false;
            }
        }

        appointments.add(appointment);
        System.out.println("Booking confirmed: " + appointment);
        return true;
    }

    /**
     * Cancels the appointment matching the given patient, slot and
     * professional.
     *
     * @param patient      patient holding the appointment
     * @param timeSlot     booked slot
     * @param professional  booked professional
     * @return true if an appointment was removed, false if none matched
     */
    public boolean cancelAppointment(Patient patient, String timeSlot,
                                    HealthProfessional professional) {
        for (Appointment existing : appointments) {
            boolean samePatient = existing.getPatient().getName().equals(patient.getName())
                    && existing.getPatient().getMobile().equals(patient.getMobile());
            boolean sameSlot = existing.getTimeSlot().equals(timeSlot);
            boolean sameProfessional = existing.getProfessional().equals(professional);

            if (samePatient && sameSlot && sameProfessional) {
                appointments.remove(existing);
                System.out.println("Appointment cancelled: " + existing);
                return true;
            }
        }

        System.out.println("Cancellation failed: no appointment found for "
                + patient.getName() + " at " + timeSlot + " with "
                + professional.getName() + ".");
        return false;
    }

    /**
     * Prints every appointment in time order (earliest first). A copy of the
     * list is sorted so the internal collection is left untouched.
     */
    public void listAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments for today.");
            return;
        }

        List<Appointment> ordered = new ArrayList<>(appointments);
        Collections.sort(ordered);

        System.out.println("\n--- Today's appointments (time order) ---");
        for (Appointment appointment : ordered) {
            System.out.println(appointment);
        }
        System.out.println("-----------------------------------------\n");
    }
}
