import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * PROG2004 - Assessment 1: Health service appointment system.
 *
 * Author:          Xuan Wang
 * Student ID:      252529340
 * GitHub repo:     <PASTE YOUR GITHUB REPOSITORY LINK HERE>
 * Explanation video: <PASTE YOUR SHORT VIDEO LINK HERE>
 *
 * GenAI declaration (complete and keep before submission):
 * [ ] I did not use any Generative AI tool for this submission.
 * [ ] I used a Generative AI tool for: <describe the parts, e.g. checking syntax /
 *       drafting comments>, and I have read, understood and can explain every line
 *       of code in this project.
 *
 * -------------------------------------------------------------------------
 * Part 1 - Modelling a generic health professional.
 *
 * <p>OOP concept: ABSTRACTION + ENCAPSULATION.</p>
 * This class is abstract: it captures the information and behaviour that are
 * common to every health professional, but a "plain" professional with no
 * specialty makes no sense, so this class cannot be instantiated directly.
 * Every concrete type (GP, Specialist, Surgeon, Nurse) must provide its own
 * specialty label by implementing the abstract method {@link #getSpecialty()}.
 *
 * <p>All fields are private and only reached through getters, and the
 * constructor validates the incoming data, so an object can never be created
 * in an incomplete or invalid state.</p>
 */
public abstract class HealthProfessional {

    /** Numeric-only identifier for the professional. */
    private String id;

    /** Full name of the professional. */
    private String name;

    /** Fixed set of working days, e.g. "Monday", "Wednesday", "Friday". */
    private List<String> workingDays;

    /**
     * Full constructor. Sets up the shared information and validates it.
     *
     * @param id          numeric-only professional ID
     * @param name        professional name (must not be blank)
     * @param workingDays list of working days (must not be empty)
     */
    protected HealthProfessional(String id, String name, List<String> workingDays) {
        if (id == null || !id.matches("\\d+")) {
            throw new IllegalArgumentException("Professional ID must contain digits only.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Professional name must not be blank.");
        }
        if (workingDays == null || workingDays.isEmpty()) {
            throw new IllegalArgumentException("Working days must not be empty.");
        }

        this.id = id;
        this.name = name;
        // Defensive copy: the inner list cannot be changed from outside.
        this.workingDays = new ArrayList<>(workingDays);
    }

    /**
     * Overloaded constructor: a professional who works the standard
     * Monday-Friday roster. Subclasses can reuse this shorter form.
     *
     * @param id   numeric-only professional ID
     * @param name professional name
     */
    protected HealthProfessional(String id, String name) {
        this(id, name, new ArrayList<>(Arrays.asList(
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday")));
    }

    /**
     * Returns the specialty label of this professional, e.g.
     * "General Practitioner". Implemented by every concrete subclass.
     *
     * @return specialty description
     */
    public abstract String getSpecialty();

    // --- Getters ---------------------------------------------------------

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the working-day list so the internal list is
     * protected from external modification.
     *
     * @return copy of the working days
     */
    public List<String> getWorkingDays() {
        return new ArrayList<>(workingDays);
    }

    // --- Shared behaviour -------------------------------------------------

    /**
     * Two professionals are considered equal when they share the same ID.
     * This lets the appointment manager detect a double booking by the same
     * professional, regardless of which concrete type they are.
     *
     * @param o object to compare
     * @return true if both professionals have the same ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional that = (HealthProfessional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Builds the shared part of the professional's details. Subclasses call
     * {@code super.toString()} and append their own extra information.
     *
     * @return readable summary of the shared fields
     */
    @Override
    public String toString() {
        return "ID: " + id
                + " | Name: " + name
                + " | Specialty: " + getSpecialty()
                + " | Working days: " + String.join(", ", workingDays);
    }
}
