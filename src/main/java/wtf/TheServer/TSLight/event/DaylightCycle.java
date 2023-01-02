package wtf.TheServer.TSLight.event;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Follows the Minecraft <a href="https://minecraft.fandom.com/wiki/Daylight_cycle#24-hour_Minecraft_day">Daylight cycle</a>
 */
public enum DaylightCycle {
    /**
     * Dawn/sunrise
     */
    DAWN(23000,24000),
    /**
     * Day
     */
    DAY(0, 12000),
    /**
     * Dusk/sunset
     */
    DUSK(12000,13000),
    /**
     * Night
     */
    NIGHT(13000,23000);

    /**
     * Get the current daylight cycle based on {@code time}
     * @param time current time in ticks
     * @return current {@link DaylightCycle} or {@code null} if {@code time} is outside of the valid range
     */
    @Nullable
    public static DaylightCycle getCycle(final long time){
        List<DaylightCycle> cycles = Arrays.stream(DaylightCycle.values()).filter(d -> time >= d.getStart() && time < d.getEnd()).toList();
        try {
            return cycles.get(0);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    /**
     * Get the exact daylight cycle for this start and end time
     * @param start beginning of the cycle
     * @param end end of the cycle
     * @return the {@link DaylightCycle} or {@code null} if not found
     */
    @Nullable
    public static DaylightCycle getExactCycle(final long start, final long end){
        List<DaylightCycle> cycles = Arrays.stream(DaylightCycle.values()).filter(d -> start == d.getStart() && end == d.getEnd()).toList();
        try {
            return cycles.get(0);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    private final long start;
    private final long end;
    DaylightCycle(final long start, final long end){
        this.start = start;
        this.end = end;
    }

    /**
     * @return the start of this cycle
     */
    public long getStart() {
        return start;
    }

    /**
     * @return the end of this cycle
     */
    public long getEnd() {
        return end;
    }
}
