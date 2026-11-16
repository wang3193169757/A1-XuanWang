import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Part 5 - Manages the collection of the day's appointments.
 *
 * <p>An {@link ArrayList} stores the appointments. The manager can add an
 * appointment (rejecting a double booking for the same professional in the
 * same slot), filter appointments by professional ID or patient mobile
 * number, sort any full or filtered list in time order, and cancel an
 * appointment. Every operation gives clear feedback when it succeeds or
 * fails. The manager works with the abstract {@link HealthProfessional}
 * type, so it accepts any concrete professional type and types added later.</p>
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
     * Returns the appointments for the professional with the given ID,
     * ordered from earliest to latest.
     *
     * @param professionalId numeric professional ID to match
     * @return sorted list of matching appointments (may be empty)
     */
    public List<Appointment> filterByProfessionalId(String professionalId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)) {
                result.add(appointment);
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * Returns the appointments booked by the patient with the given mobile
     * number, ordered from earliest to latest.
     *
     * @param mobile patient mobile number to match
     * @return sorted list of matching appointments (may be empty)
     */
    public List<Appointment> filterByPatientMobile(String mobile) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobile().equals(mobile)) {
                result.add(appointment);
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * Cancels the appointment matching the given patient, slot and
     * professional.
     *
     * @param patient      patient holding the appointment
     * @param timeSlot     booked slot
     * @param professional booked professional
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
     * Cancels the appointment for the given professional ID and time slot.
     *
     * @param professionalId numeric professional ID
     * @param timeSlot       booked slot
     * @return true if an appointment was removed, false if none matched
     */
    public boolean cancelAppointmentByProfessionalId(String professionalId,
                                                     String timeSlot) {
        for (Appointment existing : appointments) {
            boolean sameProfessional =
                    existing.getProfessional().getId().equals(professionalId);
            boolean sameSlot = existing.getTimeSlot().equals(timeSlot);

            if (sameProfessional && sameSlot) {
                appointments.remove(existing);
                System.out.println("Appointment cancelled: " + existing);
                return true;
            }
        }

        System.out.println("Cancellation failed: no appointment found for "
                + "professional " + professionalId + " at " + timeSlot + ".");
        return false;
    }

    /**
     * Cancels every appointment held by the patient with the given mobile
     * number.
     *
     * @param mobile patient mobile number
     * @return number of appointments cancelled (0 if none matched)
     */
    public int cancelAppointmentByPatientMobile(String mobile) {
        List<Appointment> kept = new ArrayList<>();
        int removed = 0;

        for (Appointment existing : appointments) {
            if (existing.getPatient().getMobile().equals(mobile)) {
                removed++;
                System.out.println("Appointment cancelled: " + existing);
            } else {
                kept.add(existing);
            }
        }

        appointments = kept;

        if (removed == 0) {
            System.out.println("Cancellation failed: no appointment found for "
                    + "mobile " + mobile + ".");
        }
        return removed;
    }

    /**
     * Prints a list of appointments under a heading. The list is printed in
     * the order it is given; filter methods already return a time-ordered
     * subset.
     *
     * @param subset  appointments to print
     * @param heading title shown above the list
     */
    public void printAppointments(List<Appointment> subset, String heading) {
        System.out.println("\n--- " + heading + " ---");
        if (subset.isEmpty()) {
            System.out.println("No matching appointments.");
            return;
        }
        for (Appointment appointment : subset) {
            System.out.println(appointment);
        }
        System.out.println("-----------------------------------------");
    }

    /**
     * Prints every appointment in time order (earliest first). A copy of the
     * list is sorted so the internal collection is left untouched.
     */
    public void listAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("\nNo appointments for today.");
            return;
        }

        List<Appointment> ordered = new ArrayList<>(appointments);
        Collections.sort(ordered);
        printAppointments(ordered, "Today's appointments (time order)");
    }
}
