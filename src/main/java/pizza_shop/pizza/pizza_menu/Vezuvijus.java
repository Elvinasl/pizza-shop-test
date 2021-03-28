package pizza_shop.pizza.pizza_menu;

import pizza_shop.pizza.Pizza;
import pizza_shop.pizza.PizzaSize;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Vezuvijus extends Pizza {

    private DayOfWeek dayOfSupply = DayOfWeek.SATURDAY;

    private static final BigDecimal SMALL_PRICE = BigDecimal.valueOf(4.5);
    private static final BigDecimal MEDIUM_PRICE = BigDecimal.valueOf(5.5);
    private static final BigDecimal LARGE_PRICE = BigDecimal.valueOf(7.5);

    public Vezuvijus(PizzaSize pizzaSize) {
        super(pizzaSize, SMALL_PRICE, MEDIUM_PRICE, LARGE_PRICE);
    }

    public Vezuvijus() {
        super(null, SMALL_PRICE, MEDIUM_PRICE, LARGE_PRICE);
    }

    @Override
    public boolean needsSupply(LocalDate serviceDate) {
        // returns true if given date is saturday
        return serviceDate.getDayOfWeek().equals(dayOfSupply);
    }

    @Override
    public boolean needsSupply() {
        // true if today is saturday
        return LocalDate.now().getDayOfWeek().equals(dayOfSupply);
    }
}
