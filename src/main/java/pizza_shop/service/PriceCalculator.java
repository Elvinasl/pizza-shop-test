package pizza_shop.service;

import pizza_shop.exception.EmptyOrderException;
import pizza_shop.exception.PizzaSizeNotSupportedException;
import pizza_shop.pizza.Pizza;
import pizza_shop.pizza.PizzaPriceComparator;
import pizza_shop.pizza.PizzaSize;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PriceCalculator {

    private static final BigDecimal STUDENT_DISCOUNT = BigDecimal.ONE;
    private static final int MIN_PIZZA_FOR_FREE = 4;

    public BigDecimal calculatePrice(Pizza pizza) {
        return calculateRegularPrice(pizza);
    }

    public BigDecimal getOrderPrice(List<Pizza> pizzaList, boolean isStudentDiscount) {
        if (pizzaList.isEmpty()) {
            throw new EmptyOrderException();
        }
        if (pizzaList.size() >= MIN_PIZZA_FOR_FREE) {
            return getFreePizzaOrderPrice(pizzaList, isStudentDiscount);
        }
        return getRegularOrderPrice(pizzaList, isStudentDiscount);
    }

    private BigDecimal getFreePizzaOrderPrice(List<Pizza> pizzaList, boolean isStudentDiscount) {
        // cheapest pizza for free
        List<Pizza> pizzaWithoutCheapest = new ArrayList<>(pizzaList);
        pizzaWithoutCheapest.sort(new PizzaPriceComparator());
        pizzaWithoutCheapest.remove(pizzaWithoutCheapest.get(0));
        return getOrderPrice(pizzaWithoutCheapest, isStudentDiscount);
    }

    private BigDecimal getRegularOrderPrice(List<Pizza> pizzaList, boolean isStudentDiscount) {
        return pizzaList.stream()
                .map(p -> calculateFinalPrice(p, isStudentDiscount))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateRegularPrice(Pizza pizza) {
        if (pizza.getSize() == null) {
            throw new PizzaSizeNotSupportedException();
        }
        if (pizza.getSize().equals(PizzaSize.SMALL)) return pizza.getSmallPrice();
        if (pizza.getSize().equals(PizzaSize.MEDIUM)) return pizza.getMediumPrice();
        if (pizza.getSize().equals(PizzaSize.LARGE)) return pizza.getLargePrice();
        throw new PizzaSizeNotSupportedException();
    }

    private BigDecimal calculateFinalPrice(Pizza pizza, boolean isStudentDiscount) {
        BigDecimal regularPrice = pizza.getPrice();
        if (isStudentDiscount) {
            return regularPrice.subtract(STUDENT_DISCOUNT);
        }
        return regularPrice;
    }
}