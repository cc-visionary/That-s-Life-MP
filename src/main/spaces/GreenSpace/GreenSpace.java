package main.spaces.GreenSpace;

import main.spaces.Space;

/**
 * Represents the Green Space
 *      to be inherited by PayRaiseSpace and PayDaySpace
 * @see PayRaiseSpace
 * @see PayDaySpace
 */

public abstract class GreenSpace extends Space {
    public GreenSpace(String name) {
        super("Green Space", name);
    }
}
