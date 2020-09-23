package model.Spaces.GreenSpace;

import model.Constants;
import model.Spaces.Space;

/**
 * Represents the Green Space
 *      to be inherited by PayRaiseSpace and PayDaySpace
 */

public abstract class GreenSpace extends Space {
    public GreenSpace(String name) {
        super(Constants.GREEN_SPACE, name);
    }
}
