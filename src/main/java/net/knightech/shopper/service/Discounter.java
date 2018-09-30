package net.knightech.shopper.service;

import java.math.BigDecimal;

@FunctionalInterface
public interface Discounter {

    String THREE_FOR_TWO = "three4two";
    String THIRTY_PERCENT_OFF = "thirtyPercentOff";

    BigDecimal applyDiscount(BigDecimal amount, Integer number);

    static Discounter three4two(){
        return (amount, number) -> {

            int numberOfDiscounts = number/3;

            int chargeFor = number - numberOfDiscounts;

            return amount.multiply(BigDecimal.valueOf(chargeFor));
        };
    }

    static Discounter thirtyPercentOff(){
        return (amount, number) -> amount.subtract(amount.multiply(BigDecimal.valueOf(0.30))).multiply(BigDecimal.valueOf(number));
    }
}
