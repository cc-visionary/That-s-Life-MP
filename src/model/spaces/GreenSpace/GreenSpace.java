package model.spaces.GreenSpace;

import model.Constants;
import model.spaces.Space;

/**
 * Represents the Green Space
 *      to be inherited by PayRaiseSpace and PayDaySpace
 */

public abstract class GreenSpace extends Space {
    public GreenSpace(String name) {
        super(Constants.GREEN_SPACE, name);
    }
}
