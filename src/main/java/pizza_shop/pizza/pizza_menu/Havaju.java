package pizza_shop.pizza.pizza_menu;

import pizza_shop.pizza.Pizza;
import pizza_shop.pizza.PizzaSize;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Hawaii implementation of the pizza
 * This pizza needs supply on Wednesday
 */
public class Havaju extends Pizza {

    private DayOfWeek dayOfSupply = DayOfWeek.WEDNESDAY;

    private static final BigDecimal SMALL_PRICE = BigDecimal.valueOf(4.5);
    private static final BigDecimal MEDIUM_PRICE = BigDecimal.valueOf(5.5);
    private static final BigDecimal LARGE_PRICE = BigDecimal.valueOf(8.5);

    public Havaju(PizzaSize pizzaSize) {
        super(pizzaSize, SMALL_PRICE, MEDIUM_PRICE, LARGE_PRICE);
    }

    public Havaju() {
        super(null, SMALL_PRICE, MEDIUM_PRICE, LARGE_PRICE);
    }

    @Override
    public boolean needsSupply(LocalDate serviceDate) {
        // returns true if given date is monday
        return serviceDate.getDayOfWeek().equals(dayOfSupply);
    }

    @Override
    public boolean needsSupply() {
        // true if today is monday
        return LocalDate.now().getDayOfWeek().equals(dayOfSupply);
    }
}
