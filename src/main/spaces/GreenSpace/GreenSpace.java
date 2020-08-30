package main.spaces.GreenSpace;

import main.spaces.Space;

/**
 * GreenSpace Class
 *      to be inherited by PayRaiseSpace and PayDaySpace
 * @see PayRaiseSpace
 * @see PayDaySpace
 */

public abstract class GreenSpace extends Space {
    public GreenSpace(String name) {
        super("Green Space", name);
    }
}
