package com.ilia;

import static com.ilia.BigDecimalUtils.MC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ApproximateCirclePerimeterCalculatorTest {

    private final ApproximateCirclePerimeterCalculator calculator = new ApproximateCirclePerimeterCalculator();

    @Test
    void calculate_ShouldReturnResult_WhenPolygonSidesNumIs4() {
        // given
        BigDecimal expectedResult = new BigDecimal("5.6568542494923800224", MC);
        BigDecimal radius = new BigDecimal("1");
        int polygonSidesNum = 4;

        // when
        BigDecimal actualResult = calculator.calculate(radius, polygonSidesNum);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calculate_ShouldReturnResult_WhenPolygonSidesNumIs4AndBigDecimal() {
        // given
        BigDecimal expectedResult = new BigDecimal("5.6568542494923800224", MC);
        BigDecimal radius = new BigDecimal("1");
        BigDecimal polygonSidesNum = new BigDecimal("4");

        // when
        BigDecimal actualResult = calculator.calculate(radius, polygonSidesNum);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calculate_ShouldReturnResult_WhenPolygonSidesNumIs400AndBigDecimal() {
        // given
        BigDecimal expectedResult = new BigDecimal("6.2831207109690668432", MC);
        BigDecimal radius = new BigDecimal("1");
        int polygonSidesNum = 400;

        // when
        BigDecimal actualResult = calculator.calculate(radius, polygonSidesNum);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calculate_ShouldReturnResult_WhenPolygonSidesNumIs4AndRadiusIs100() {
        // given
        BigDecimal expectedResult = new BigDecimal("565.68542494923800224", MC);
        BigDecimal radius = new BigDecimal("100");
        int polygonSidesNum = 4;

        // when
        BigDecimal actualResult = calculator.calculate(radius, polygonSidesNum);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void calculate_ShouldThrowException_WhenPolygonSidesNumIsNegative() {
        // given
        BigDecimal radius = new BigDecimal("1");
        int polygonSidesNum = -1;

        // when
        // then
        assertThatThrownBy(() -> calculator.calculate(radius, polygonSidesNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Polygon sides number must be positive and >= 4.");

    }

    @Test
    void calculate_ShouldThrowException_WhenPolygonSidesNumIsZero() {
        // given
        BigDecimal radius = new BigDecimal("1");
        int polygonSidesNum = 0;

        // when
        // then
        assertThatThrownBy(() -> calculator.calculate(radius, polygonSidesNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Polygon sides number must be positive and >= 4.");

    }

    @Test
    void calculate_ShouldThrowException_WhenPolygonSidesNumIsLessThan4() {
        // given
        BigDecimal radius = new BigDecimal("1");
        int polygonSidesNum = 3;

        // when
        // then
        assertThatThrownBy(() -> calculator.calculate(radius, polygonSidesNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Polygon sides number must be positive and >= 4.");

    }

    @Test
    void calculate_ShouldThrowException_WhenRadiusIsNegative() {
        // given
        BigDecimal radius = new BigDecimal("-1");
        int polygonSidesNum = 4;

        // when
        // then
        assertThatThrownBy(() -> calculator.calculate(radius, polygonSidesNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Radius must be positive.");

    }

    @Test
    void calculate_ShouldThrowException_WhenRadiusIsZero() {
        // given
        BigDecimal radius = new BigDecimal("-1");
        int polygonSidesNum = 4;

        // when
        // then
        assertThatThrownBy(() -> calculator.calculate(radius, polygonSidesNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Radius must be positive.");

    }
}
