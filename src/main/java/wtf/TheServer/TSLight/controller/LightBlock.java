package wtf.TheServer.TSLight.controller;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Lightable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Represents a single light block
 */
public class LightBlock implements LightController {
    static {
        ValidLightBlocks = Arrays.stream(Material.values()).filter(m->(m.isBlock() && m.createBlockData() instanceof Lightable) || m.equals(Material.LIGHT)).toList();
    }

    /**
     * List of valid light emitting blocks that can be toggled
     */
    public static final List<Material> ValidLightBlocks;

    /**
     * @param material the block material to check
     * @return {@code true} if the given {@code material} is a valid light emitting block
     */
    public static boolean isValid(@NotNull Material material){
        return ValidLightBlocks.contains(material);
    }

    /**
     * @return String representation of {@link #ValidLightBlocks}
     */
    public static String validBlocks(){
        return "- "+ValidLightBlocks.stream().map(Enum::toString).collect(Collectors.joining("\n"+"- "));
    }

    private final UUID id;
    private Location location = null;

    /**
     * Initialize a new, empty light block
     */
    public LightBlock(){
        this.id = UUID.randomUUID();
    }

    /**
     * Initialize new light block with the given uid and location
     * @param id unique id
     * @param location block location
     * @throws IllegalArgumentException see {@link #setLocation(Location)}
     */
    public LightBlock(@NotNull UUID id, @NotNull Location location)
            throws IllegalArgumentException
    {
        this.id = id;
        setLocation(location);
    }

    @Override
    @NotNull
    public UUID getId() {
        return id;
    }

    @Override
    @Nullable
    public Location getLocation() {
        return location;
    }

    /**
     * Update the location of this block
     * @param location the new location
     * @throws IllegalArgumentException If the world or the block at this location is invalid
     */
    public void setLocation(@NotNull Location location) throws IllegalArgumentException {
        if(!location.isWorldLoaded())
            throw new IllegalArgumentException("Location must have a valid world");
        if(!isValid(location.getBlock().getType()))
            throw new IllegalArgumentException("Given type is not a valid light block");
        this.location = location;
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
        if(isValid(location.getBlock().getType()))
            blocks.add(location.getBlock());
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
