import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PROG2004 - Assessment 1: Health service appointment system.
 *
 * Author:          Xuan Wang
 * Student ID:      25259340
 * GitHub repo:     https://github.com/wang3193169757/A1-XuanWang
 * Explanation video: https://scuonline-my.sharepoint.com/:v:/g/personal/x_wang_127_student_scu_edu_au/IQDKWHSZehwVQ7Iu9MnPXcoYARUx25MSZiWxleD1QBt5w1A?e=oe62sW
 *
 * GenAI declaration:
 * [x] I used a Generative AI tool within the permitted scope to check the
 *     completeness of my code and to help correct errors. I have read,
 *     understood and can explain every line of code in this project.
 */

/**
 * End-to-end demonstration: builds the professional team, stores them through
 * the common {@link HealthProfessional} type, then books, filters, cancels
 * and lists appointments. Both normal and error cases are exercised.
 */
public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("=== PROG2004 Assessment 1 - Health service "
                + "appointment system ===\n");

        // Create the professional team.
        GeneralPractitioner gp1 = new GeneralPractitioner(
                "1001", "Dr Alice Walker",
                Arrays.asList("Monday", "Wednesday", "Friday"), 15);
        GeneralPractitioner gp2 = new GeneralPractitioner(
                "1002", "Dr Ben Chen",
                Arrays.asList("Tuesday", "Thursday"), 20);
        GeneralPractitioner gp3 = new GeneralPractitioner(
                "1003", "Dr Carol Wu", 15);

        Specialist specialist = new Specialist(
                "2001", "Dr David Liu",
                Arrays.asList("Monday", "Wednesday"), "Cardiology");
        Surgeon surgeon = new Surgeon(
                "3001", "Dr Eva Zhang",
                Arrays.asList("Tuesday", "Thursday", "Friday"), "Orthopaedics");
        Nurse nurse = new Nurse("4001", "Fiona Li", "Ward 3B");

        // All subtypes held in one list of the common parent type.
        List<HealthProfessional> staff = new ArrayList<>();
        staff.add(gp1);
        staff.add(gp2);
        staff.add(gp3);
        staff.add(specialist);
        staff.add(surgeon);
        staff.add(nurse);

        System.out.println("--- Part 3: Health professionals on staff ---");
        for (HealthProfessional professional : staff) {
            // Each object prints its own details through polymorphism.
            System.out.println(professional);
        }
        System.out.println();

        // Appointment simulation.
        Patient john = new Patient("John Doe", "0412345678");
        Patient mary = new Patient("Mary Smith", "0412987654");
        Patient bob = new Patient("Bob Brown", "0432109876");
        Patient sarah = new Patient("Sarah King", "0456781234");

        AppointmentManager manager = new AppointmentManager();

        System.out.println("--- Booking appointments (normal cases) ---");
        manager.addAppointment(new Appointment(john, gp1, "09:00"));
        manager.addAppointment(new Appointment(mary, gp1, "09:30"));
        manager.addAppointment(new Appointment(bob, specialist, "09:00"));
        manager.addAppointment(new Appointment(sarah, nurse, "10:00"));

        // Error case 1: the same professional is booked again in the same slot.
        System.out.println("\n--- Error case 1: double booking rejected ---");
        manager.addAppointment(new Appointment(bob, gp1, "09:00"));

        // Error case 2: a slot outside the clinic schedule cannot be created.
        System.out.println("\n--- Error case 2: invalid slot rejected ---");
        try {
            manager.addAppointment(new Appointment(john, gp2, "17:00"));
        } catch (IllegalArgumentException ex) {
            System.out.println("Booking rejected: " + ex.getMessage());
        }

        // Professional-specific actions show each subtype's own behaviour.
        System.out.println("\n--- Professional-specific actions ---");
        System.out.println(gp1.prescribe(john, "Amoxicillin"));
        System.out.println(specialist.reviewPatient(bob));
        System.out.println(surgeon.prepareOperation(sarah));
        System.out.println(nurse.takeBloodSample(sarah));

        // Filtering: by professional ID, and by patient mobile number. Each
        // filtered subset is returned already sorted in time order.
        System.out.println("\n--- Filtering appointments ---");
        manager.printAppointments(
                manager.filterByProfessionalId("1001"),
                "Appointments for professional 1001");
        manager.printAppointments(
                manager.filterByPatientMobile("0432109876"),
                "Appointments for patient 0432109876");

        // Cancellation matched by patient, slot and professional.
        System.out.println("\n--- Cancellation (patient + slot + professional) ---");
        manager.cancelAppointment(mary, "09:30", gp1);

        // Cancellation matched by professional ID and time slot.
        System.out.println("\n--- Cancellation (professional ID + slot) ---");
        manager.cancelAppointmentByProfessionalId("2001", "09:00");

        // Cancellation matched by patient mobile number.
        System.out.println("\n--- Cancellation (patient mobile number) ---");
        manager.cancelAppointmentByPatientMobile("0456781234");

        // Error case 3: cancelling an appointment that does not exist.
        System.out.println("\n--- Error case 3: cancel a non-existent appointment ---");
        manager.cancelAppointmentByProfessionalId("9999", "09:00");
        manager.cancelAppointmentByPatientMobile("0400000000");

        manager.listAppointments();
    }
}
