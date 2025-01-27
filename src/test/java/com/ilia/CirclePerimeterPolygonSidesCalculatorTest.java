package com.ilia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class CirclePerimeterPolygonSidesCalculatorTest {

    @Test
    void calculatePolygonSidesNum_ShouldReturnResult() {
        // given
        CirclePerimeterPolygonSidesCalculator calculator = new CirclePerimeterPolygonSidesCalculator();
        int expectedResult = 322226;
        BigDecimal radius = new BigDecimal("1");

        // when
        int actualResult = calculator.calculatePolygonSidesNum(radius, 10);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calculatePolygonSidesNum_ShouldReturnResult_WhenPrecisionIsBigger() {
        // given
        CirclePerimeterPolygonSidesCalculator calculator = new CirclePerimeterPolygonSidesCalculator();
        int expectedResult = 11;
        BigDecimal radius = new BigDecimal("1");

        // when
        int actualResult = calculator.calculatePolygonSidesNum(radius, 1);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calculatePolygonSidesNum_ShouldThrowException_WhenIterationsThresholdIsReached() {
        // given
        CirclePerimeterPolygonSidesCalculator calculator = new CirclePerimeterPolygonSidesCalculator();
        calculator.ITERATIONS_THRESHOLD = 2;
        BigDecimal radius = new BigDecimal("1");

        // when
        // then
        assertThatThrownBy(() -> calculator.calculatePolygonSidesNum(radius, 10))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Unable to calculate the approximate perimeter with the provided precision.");
    }

    @Test
    void calculatePolygonSidesNum_ShouldThrowException_WhenRadiusIsNegative() {
        // given
        CirclePerimeterPolygonSidesCalculator calculator = new CirclePerimeterPolygonSidesCalculator();
        BigDecimal radius = new BigDecimal("-1");

        // when
        // then
        assertThatThrownBy(() -> calculator.calculatePolygonSidesNum(radius, 10))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Radius must be positive.");
    }

    @Test
    void calculatePolygonSidesNum_ShouldThrowException_WhenRadiusIsZero() {
        // given
        CirclePerimeterPolygonSidesCalculator calculator = new CirclePerimeterPolygonSidesCalculator();
        BigDecimal radius = new BigDecimal("0");

        // when
        // then
        assertThatThrownBy(() -> calculator.calculatePolygonSidesNum(radius, 10))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Radius must be positive.");
    }

    @Test
    void calculatePolygonSidesNum_ShouldThrowException_WhenPrecisionIsZero() {
        // given
        CirclePerimeterPolygonSidesCalculator calculator = new CirclePerimeterPolygonSidesCalculator();
        BigDecimal radius = new BigDecimal("1");

        // when
        // then
        assertThatThrownBy(() -> calculator.calculatePolygonSidesNum(radius, 0))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Error precision must be positive.");
    }

    @Test
    void calculatePolygonSidesNum_ShouldThrowException_WhenPrecisionIsNegative() {
        // given
        CirclePerimeterPolygonSidesCalculator calculator = new CirclePerimeterPolygonSidesCalculator();
        BigDecimal radius = new BigDecimal("1");

        // when
        // then
        assertThatThrownBy(() -> calculator.calculatePolygonSidesNum(radius, -10))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Error precision must be positive.");
    }
}
