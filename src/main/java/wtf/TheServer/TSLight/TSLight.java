package wtf.TheServer.TSLight;

import org.jetbrains.annotations.NotNull;
import wtf.TheServer.TSLight.controller.LightBlock;
import wtf.TheServer.TSLight.controller.LightZone;

import java.util.Set;

/**
 * Represents the plugin API
 */
public interface TSLight {
    /**
     * @return set of active light blocks
     */
    @NotNull Set<LightBlock> getLightBlocks();

    /**
     * @return set of active light zones
     */
    @NotNull Set<LightZone> getLightZones();
}
