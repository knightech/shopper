package net.knightech.shopper.service;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscounterTest {

    @Test
    public void three4two() {

        BigDecimal bigDecimal = Discounter.three4two().applyDiscount(BigDecimal.valueOf(0.85), 5);

        assertThat(bigDecimal.doubleValue()).isEqualTo(3.4);
    }

    @Test
    public void thirtyPercentOff() {

        BigDecimal bigDecimal = Discounter.thirtyPercentOff().applyDiscount(BigDecimal.valueOf(85.00), 1);

        assertThat(bigDecimal.doubleValue()).isEqualTo(59.5);

    }
}