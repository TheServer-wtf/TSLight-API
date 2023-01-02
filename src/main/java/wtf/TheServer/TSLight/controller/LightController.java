package wtf.TheServer.TSLight.controller;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

/**
 * Represents a light controller
 */
public interface LightController {
    /**
     * @return unique id of this light
     */
    @NotNull UUID getId();

    /**
     * The location of this light controller
     * @return location or {@code null} if not set
     */
    @Nullable Location getLocation();

    /**
     * Get the current state of the lights
     * @return {@code false} if the lights are turned off, {@code true} otherwise
     */
    boolean getState();

    /**
     * @return the time when this light switches off
     */
    long getStart();

    /**
     * @return the time when this light switches back on
     */
    long getEnd();

    /**
     * @return level of any {@link Material#LIGHT} 1-15
     */
    int getLevel();

    /**
     * @return set of blocks to modify
     */
    @NotNull Set<Block> getBlocks();
}
