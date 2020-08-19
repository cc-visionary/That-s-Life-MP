package resources.spaces;

import resources.utilities.StringUtil;

public class Space {
    private String type, user;

    public Space(String type) {
        this.type = type;
    }

    /**
     * Returns the value of the type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of the user
     * @param user
     */
    public void setUser(String user) {
        if (user.equals("P1") || user.equals("P2") || user.equals("P3")) {
            this.user = user;
        } else {
            System.out.println("Invalid USER ID.");
        }
    }

    /**
     * Returns the value of the user ID
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * Display the space into a 3(height) x 6(width) unit
     */
    public void displaySpace() {
        final int length = 5;
        final String user = getUser() != null ? "(" + getUser() + ")" : "";
        System.out.println("╭─────╮");
        System.out.println("│" + StringUtil.centerString(getType().charAt(0) + user, length) + "│");
        System.out.println("╰─────╯");
    }
}
