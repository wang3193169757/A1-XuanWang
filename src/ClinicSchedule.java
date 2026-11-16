import java.util.ArrayList;
import java.util.List;

/**
 * The clinic's fixed daily schedule: 08:00 to 16:00 in 30-minute slots.
 * If the opening hours or slot length change, only this class needs updating.
 */
public class ClinicSchedule {

    private static final int OPEN_HOUR = 8;
    private static final int CLOSE_HOUR = 16;
    private static final int SLOT_LENGTH_MINUTES = 30;

    /** Valid booking slots for the day, e.g. "08:00", "08:30". */
    private static final List<String> SLOTS = buildSlots();

    private ClinicSchedule() {
    }

    /**
     * Builds the ordered list of slots between OPEN_HOUR and CLOSE_HOUR.
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
     * @return a copy of all valid slots for the day
     */
    public static List<String> getSlots() {
        return new ArrayList<>(SLOTS);
    }

    /**
     * @param slot "HH:mm" label to check
     * @return true if the slot is part of the clinic schedule
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
