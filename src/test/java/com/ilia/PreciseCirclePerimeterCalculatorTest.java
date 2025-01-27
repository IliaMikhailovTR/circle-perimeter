package com.ilia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class PreciseCirclePerimeterCalculatorTest {

    private final PreciseCirclePerimeterCalculator calculator = new PreciseCirclePerimeterCalculator();

    @Test
    void calculate_ShouldReturnResult() {
        // given
        BigDecimal expectedResult = new BigDecimal("6.2831853071795862320");
        BigDecimal radius = new BigDecimal("1");

        // when
        BigDecimal actualResult = calculator.calculate(radius);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calculate_ShouldReturnResult_WhenRadiusIs100() {
        // given
        BigDecimal expectedResult = new BigDecimal("628.31853071795862320");
        BigDecimal radius = new BigDecimal("100");

        // when
        BigDecimal actualResult = calculator.calculate(radius);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calculate_ShouldThrowException_WhenRadiusIsNegative() {
        // given
        BigDecimal radius = new BigDecimal("-1");

        // when
        // then
        assertThatThrownBy(() -> calculator.calculate(radius))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Radius must be positive.");
    }

    @Test
    void calculate_ShouldThrowException_WhenRadiusIsZero() {
        // given
        BigDecimal radius = new BigDecimal("0");

        // when
        // then
        assertThatThrownBy(() -> calculator.calculate(radius))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Radius must be positive.");
    }
}
