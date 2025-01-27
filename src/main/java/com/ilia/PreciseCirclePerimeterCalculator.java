package com.ilia;

import static com.ilia.BigDecimalUtils.MC;
import static com.ilia.BigDecimalUtils.PI;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PreciseCirclePerimeterCalculator {

    /**
     * Calculates the exact perimeter of a circle using the formula:
     * <pre>
     * perimeter = 2 * π * radius
     * </pre>
     *
     * @param radius the radius of the circle; must be a positive {@link BigDecimal}.
     * @return the exact perimeter of the circle as a {@link BigDecimal}.
     * @throws IllegalArgumentException if the radius is null or non-positive.
     */
    public BigDecimal calculate(BigDecimal radius) {
        if (radius == null || radius.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Radius must be positive.");
        }
        return new BigDecimal(2)
                .multiply(PI, MC)
                .multiply(radius, MC);
    }
}
