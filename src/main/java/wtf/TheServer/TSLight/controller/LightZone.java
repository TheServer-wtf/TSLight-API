package wtf.TheServer.TSLight.controller;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a collection of light blocks
 */
public class LightZone implements LightController {
    private final UUID id;
    private Location min = null;
    private Location max = null;
    private Location center = null;

    /**
     * Initialize a new, empty light zone
     */
    public LightZone(){
        this.id = UUID.randomUUID();
    }

    /**
     * Initialize new light zone with the given uid and bounding locations
     * @param id unique id
     * @param min lower point
     * @param max upper point
     * @throws IllegalArgumentException see {@link #setZone(Location, Location)}
     */
    public LightZone(@NotNull UUID id, @NotNull Location min, @NotNull Location max)
            throws IllegalArgumentException
    {
        this.id = id;
        setZone(min,max);
    }

    @Override
    @NotNull
    public UUID getId() {
        return id;
    }

    /**
     * Center location of this light controller
     * @return location or {@code null} if not set
     */
    @Override
    public @Nullable Location getLocation() {
        return center;
    }

    /**
     * Update center location of this light controller
     * @param center new location
     * @throws IllegalStateException if the zone location is not yet set
     * @throws IllegalArgumentException if the world of the new location does not match the zone world
     */
    public void setCenter(Location center) throws IllegalStateException, IllegalArgumentException {
        if(min == null || max == null)
            throw new IllegalStateException("Min and max location not yet set");
        if(min.getWorld().getUID() != center.getWorld().getUID())
            throw new IllegalArgumentException("The center location world does not match the zone world");
        this.center = center;
    }

    /**
     * @return the lower point of the zone
     */
    @Nullable
    public Location getMin() {
        return min;
    }

    /**
     * @return the upper point of the zone
     */
    @Nullable
    public Location getMax() {
        return max;
    }

    /**
     * Update the location of this zone
     * @param min lower point
     * @param max upper point
     * @throws IllegalArgumentException if a point is in an invalid world or the two worlds don't match
     */
    public void setZone(@NotNull Location min, @NotNull Location max) throws IllegalArgumentException {
        if(!min.isWorldLoaded() || !max.isWorldLoaded())
            throw new IllegalArgumentException("Zone must be in a valid world");
        if(min.getWorld().getUID() != max.getWorld().getUID())
            throw new IllegalArgumentException("The min and max world does not match");
        this.min = min;
        this.max = max;
    }

    private boolean state = false;

    /**
     * Update the state of this light
     * @param state new state
     */
    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public boolean getState() {
        return state;
    }

    private long start = -1;
    private long end = -1;

    /**
     * Update turn off time
     * @param start new time
     */
    public void setStart(long start) {
        this.start = start;
    }

    @Override
    public long getStart() {
        return start;
    }

    /**
     * Update turn on time
     * @param end new time
     */
    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public long getEnd() {
        return end;
    }

    private int level = -1;

    /**
     * Update light level
     * @param level new level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    @NotNull
    public Set<Block> getBlocks() {
        Set<Block> blocks = new HashSet<>();
        World w = min.getWorld();
        for (int x = min.getBlockX(); x<=max.getBlockX(); x++)
        {
            for (int y = min.getBlockY(); y<=max.getBlockY(); y++)
            {
                for (int z = min.getBlockZ(); z<=max.getBlockZ(); z++)
                {
                    Block b = w.getBlockAt(x,y,z);
                    if(!LightBlock.isValid(b.getType())){
                        continue;
                    }
                    blocks.add(b);
                }
            }
        }
        return blocks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LightController other = (LightController) obj;
        return this.getId() == other.getId();
    }
}
