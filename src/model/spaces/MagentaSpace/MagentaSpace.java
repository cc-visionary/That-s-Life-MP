package model.spaces.MagentaSpace;

import model.Constants;
import model.spaces.Space;

/**
 * Represents the Magenta Space
 *      which is inherited by all other classes for Magenta Space.
 */

public abstract class MagentaSpace extends Space {
    public MagentaSpace(String name) {
        super(Constants.MAGENTA_SPACE, name);
    }
}
