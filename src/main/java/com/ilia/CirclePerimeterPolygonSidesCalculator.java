package com.ilia;

import static com.ilia.BigDecimalUtils.MC;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CirclePerimeterPolygonSidesCalculator {

    private static final int INITIAL_POLYGON_SIDES_NUM = 4;

    private final ApproximateCirclePerimeterCalculator approximateCalculator;
    private final PreciseCirclePerimeterCalculator preciseCalculator;
    int ITERATIONS_THRESHOLD = 400000;

    public CirclePerimeterPolygonSidesCalculator() {
        this.approximateCalculator = new ApproximateCirclePerimeterCalculator();
        this.preciseCalculator = new PreciseCirclePerimeterCalculator();
    }

    /**
     * Calculates the minimum number of sides for a regular polygon inscribed in a circle required to approximate
     * the perimeter of the circle with a given precision.
     *
     * <p>
     * The method compares the precise perimeter of the circle, calculated using the formula 2 * π * radius,
     * with the approximate perimeter calculated using a regular polygon with an increasing number of sides.
     * The calculation stops when the absolute error between the precise and approximate perimeters
     * is less than the error precision.
     * </p>
     *
     * @param radius         the radius of the circle; must be a positive {@link BigDecimal}.
     * @param errorPrecision the number of decimal places for precision (e.g., 5 for 10⁵); must be a positive integer.
     * @return the minimum number of sides required for the polygon to approximate the circle's perimeter within the given precision.
     * @throws IllegalArgumentException if the radius is null, non-positive, or if the error precision is non-positive.
     * @throws RuntimeException         if the method exceeds the maximum number of iterations (`ITERATIONS_THRESHOLD`)
     *                                  without finding the required precision.
     */
    public int calculatePolygonSidesNum(BigDecimal radius, int errorPrecision) {
        if (radius == null || radius.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Radius must be positive.");
        }
        if (errorPrecision <= 0) {
            throw new IllegalArgumentException("Error precision must be positive.");
        }
        log.info("Starting the calculation. Precision: 10^{}.", errorPrecision);
        BigDecimal errorPrecisionBigDecimal = new BigDecimal("1E-" + errorPrecision, MC);
        BigDecimal precisePerimeter = preciseCalculator.calculate(radius);
        log.info("The precise perimeter value: {}.", precisePerimeter);

        int polygonSidesNum = INITIAL_POLYGON_SIDES_NUM;

        Instant start = Instant.now();
        while (polygonSidesNum < ITERATIONS_THRESHOLD) {
            BigDecimal approximatePerimeter = approximateCalculator.calculate(radius, polygonSidesNum);

            BigDecimal error = precisePerimeter
                    .subtract(approximatePerimeter, MC)
                    .abs(MC);
            if (error.compareTo(errorPrecisionBigDecimal) < 0) {
                log.info("""
                                 The approximate perimeter found!
                                 Precise value:     {}
                                 Approximate value: {}
                                 Calculation error: {}
                                 Precision:         {}
                                 A polygon with {} sides was used.""",
                         precisePerimeter,
                         approximatePerimeter,
                         error.toPlainString(),
                         errorPrecisionBigDecimal.toPlainString(),
                         polygonSidesNum);
                Instant finish = Instant.now();
                log.info("Calculation time: {}ms", Duration.between(start, finish).toMillis());
                return polygonSidesNum;
            }

            polygonSidesNum++;
        }

        throw new RuntimeException("Unable to calculate the approximate perimeter with the provided precision.");
    }
}
