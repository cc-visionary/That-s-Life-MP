package main.cards.SalaryCard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import main.Constants;
import main.cards.Card;
import main.utilities.StringUtil;

/**
 * Represents the Salary Card
 *      which contains Salary and SalaryTaxDue. Salary can be increased along with SalaryTaxDue.
 */

final public class SalaryCard extends Card {
    private double salary, tax;
    private int payRaise = 0;
    public SalaryCard(double salary) {
        super(Constants.SALARY_CARD, "Salary Card contains salary and tax due.", "/images/Cards/Salary Card.png");

        this.salary = salary;
        this.tax = salary * 0.1; // the tax due is 10% of the salary
    }

    /**
     * Increases the salary by a parameter amount and increments the payraise
     * @param amount amount to increase the salary
     */
    public void increaseSalary(double amount) {
        salary += amount;
        tax = salary * 0.1; // the tax due is 10% of the salary
        payRaise++;
    }

    /**
     * Returns the value of the salary
     * @return salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Returns the value of the tax
     * @return tax
     */
    public double getTax() {
        return tax;
    }

    /**
     * Returns the amount of pay raise
     * @return the number of pay raise which was done
     */
    public int getPayRaise() {
        return payRaise;
    }


    /**
     * Formats the Card into an image with corresponding texts
     * @return StackPane containing the formatted image
     */
    @Override
    public StackPane displayCard() {
        StackPane stackPane = new StackPane();

        ImageView cardImage = new ImageView(getImageURL());
        cardImage.setFitHeight(Screen.getPrimary().getBounds().getMaxY() / 2);
        cardImage.setFitWidth(Screen.getPrimary().getBounds().getMaxX() / 5);

        VBox formattedBox = new VBox();

        VBox descriptionBox = new VBox();
        for(String string : StringUtil.splitStringLength(getDescription(), 40)) {
            Label description = new Label(string);
            descriptionBox.getChildren().add(description);
        }
        descriptionBox.setAlignment(Pos.CENTER);

        VBox nameBox = new VBox();
        Label cardName = new Label(getType());
        Label salary = new Label("Salary: $" + getSalary());
        Label tax = new Label("Tax: $" + getTax());
        Label payRaise = new Label("Pay Raise: " + getPayRaise());
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setTranslateY(100);
        nameBox.getChildren().addAll(cardName, salary, tax, payRaise);

        formattedBox.getChildren().addAll(descriptionBox, nameBox);
        formattedBox.setAlignment(Pos.CENTER);

        stackPane.getChildren().addAll(cardImage, formattedBox);
        formattedBox.setAlignment(Pos.CENTER);

        return stackPane;
    }

    @Override
    public String toString() {
        return String.format("SalaryCard{salary=%.2f,tax=%.2f,payRaise=%d}", salary, tax, payRaise);
    }
}
