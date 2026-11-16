import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
 * Holds the information and behaviour shared by every health professional.
 * The class is abstract, so it cannot be instantiated directly; each
 * subclass provides its own specialty through {@link #getSpecialty()}.
 */
public abstract class HealthProfessional {

    private String id;
    private String name;
    /** Fixed set of working days, e.g. "Monday", "Wednesday". */
    private List<String> workingDays;

    /**
     * Sets up the shared information and checks that it is valid.
     *
     * @param id          numeric-only professional ID
     * @param name        professional name, must not be blank
     * @param workingDays working days, must not be empty
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
     * Shorter constructor for a professional on the standard Monday-Friday roster.
     *
     * @param id   numeric-only professional ID
     * @param name professional name
     */
    protected HealthProfessional(String id, String name) {
        this(id, name, new ArrayList<>(Arrays.asList(
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday")));
    }

    /**
     * Returns the specialty label of this professional.
     *
     * @return specialty description
     */
    public abstract String getSpecialty();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the working-day list so the internal list is protected.
     *
     * @return copy of the working days
     */
    public List<String> getWorkingDays() {
        return new ArrayList<>(workingDays);
    }

    /**
     * Two professionals are equal when they share the same ID, so the manager
     * can detect a double booking regardless of the concrete type.
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
     * Builds the shared part of the details; subclasses call super.toString()
     * and append their own extra information.
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
