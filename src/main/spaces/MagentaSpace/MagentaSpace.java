package main.spaces.MagentaSpace;

import main.spaces.Space;

/**
 * MagentaSpace Class
 *      Inherited by all other classes for Magenta Space.
 * @see BuyAHouseSpace
 * @see CollegeCareerChoiceSpace
 * @see GetMarriedSpace
 * @see GraduationSpace
 * @see HaveBabySpace
 * @see WhichPathSpace
 */

public abstract class MagentaSpace extends Space {
    public MagentaSpace(String name) {
        super("Magenta Space", name);
    }
}
