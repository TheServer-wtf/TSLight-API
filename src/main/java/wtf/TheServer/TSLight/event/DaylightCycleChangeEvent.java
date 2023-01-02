package wtf.TheServer.TSLight.event;

import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.WorldEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Called when the daylight cycle changes in the world
 */
public class DaylightCycleChangeEvent extends WorldEvent {
    private static final HandlerList handlers = new HandlerList();
    private final DaylightCycle daylightCycle;

    public DaylightCycleChangeEvent(World world, DaylightCycle daylightCycle) {
        super(world);
        this.daylightCycle = daylightCycle;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return the new daylight cycle
     */
    public DaylightCycle getDaylightCycle() {
        return daylightCycle;
    }
}
