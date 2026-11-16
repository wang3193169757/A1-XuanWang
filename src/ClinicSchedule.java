import java.util.ArrayList;
import java.util.List;

/**
 * Part 5 - The clinic's fixed daily schedule.
 *
 * <p>The clinic runs from 08:00 to 16:00 in 30-minute slots. The valid slots
 * are generated once and kept in this class, so if the opening hours or slot
 * length ever change, only this class needs to be updated and the rest of the
 * booking system works with the new schedule automatically.</p>
 */
public class ClinicSchedule {

    /** Opening hour (24-hour clock). */
    private static final int OPEN_HOUR = 8;

    /** Closing hour (24-hour clock); the last slot starts before this time. */
    private static final int CLOSE_HOUR = 16;

    /** Length of one booking slot in minutes. */
    private static final int SLOT_LENGTH_MINUTES = 30;

    /** Valid booking slots for the day, e.g. "08:00", "08:30". */
    private static final List<String> SLOTS = buildSlots();

    /** Private constructor: this class only holds static schedule helpers. */
    private ClinicSchedule() {
    }

    /**
     * Builds the fixed list of slots between OPEN_HOUR and CLOSE_HOUR.
     *
     * @return ordered list of "HH:mm" slot labels
     */
    private static List<String> buildSlots() {
        List<String> slots = new ArrayList<>();
        for (int minutes = OPEN_HOUR * 60;
             minutes < CLOSE_HOUR * 60;
             minutes += SLOT_LENGTH_MINUTES) {
            int hour = minutes / 60;
            int minute = minutes % 60;
            slots.add(String.format("%02d:%02d", hour, minute));
        }
        return slots;
    }

    /**
     * Returns a copy of all valid slots for the day.
     *
     * @return ordered list of slot labels
     */
    public static List<String> getSlots() {
        return new ArrayList<>(SLOTS);
    }

    /**
     * Checks whether a slot label is part of the clinic schedule.
     *
     * @param slot "HH:mm" label to check
     * @return true if the slot is valid
     */
    public static boolean isValidSlot(String slot) {
        return slot != null && SLOTS.contains(slot);
    }

    /**
     * Converts a "HH:mm" slot into minutes from midnight, so slots can be
     * compared and sorted correctly.
     *
     * @param slot valid "HH:mm" label
     * @return minutes from midnight
     */
    public static int slotToMinutes(String slot) {
        String[] parts = slot.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }
}
