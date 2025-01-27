package com.ilia;

import static com.ilia.BigDecimalUtils.MC;
import static com.ilia.BigDecimalUtils.PI;

import java.math.BigDecimal;

import ch.obermuhlner.math.big.BigDecimalMath;

public class ApproximateCirclePerimeterCalculator {

    private final BigDecimal circleRad = PI.multiply(new BigDecimal(2), MC);

    /**
     * Calculates the approximate perimeter of a circle using a regular polygon with the specified number of sides.
     *
     * @param radius          the radius of the circle; must be a positive {@link BigDecimal}.
     * @param polygonSidesNum the number of sides of the regular polygon; must be a positive integer greater or equal to 4.
     * @return the approximate perimeter of the circle as a {@link BigDecimal}.
     * @throws IllegalArgumentException if the radius is non-positive or if the number of polygon sides is less than 4.
     */
    public BigDecimal calculate(BigDecimal radius, int polygonSidesNum) {
        return calculate(radius, new BigDecimal(polygonSidesNum));
    }

    /**
     * Calculates the approximate perimeter of a circle using a regular polygon with the specified number of sides.
     *
     * @param radius          the radius of the circle; must be a positive {@link BigDecimal}.
     * @param polygonSidesNum the number of sides of the regular polygon as a {@link BigDecimal}; greater or equal to 4.
     * @return the approximate perimeter of the circle as a {@link BigDecimal}.
     * @throws IllegalArgumentException if the radius is non-positive or if the number of polygon sides is less than 4.
     */
    public BigDecimal calculate(BigDecimal radius, BigDecimal polygonSidesNum) {
        if (radius == null || radius.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Radius must be positive.");
        }
        if (polygonSidesNum == null || polygonSidesNum.compareTo(new BigDecimal(4)) < 0) {
            throw new IllegalArgumentException("Polygon sides number must be positive and >= 4.");
        }
        BigDecimal segmentSideLength = calculatePolygonSegmentSideLength(radius, polygonSidesNum);
        return segmentSideLength.multiply(polygonSidesNum, MC);
    }

    /**
     * Calculates the length of a single side of a regular polygon inscribed in the circle.
     * <p>
     * The formula used is the <strong>Law of cosines</strong>:
     * <img src="doc-files/pictures/triangle_law_of_cosines.png">
     * <pre>
     * c² = √(a² + b² - 2*a*b*cos(γ))
     * </pre>
     *
     * @param radius          the radius of the circle; must be a positive {@link BigDecimal}.
     * @param polygonSidesNum the number of sides of the polygon; must be greater than or equal to 4.
     * @return the length of one side of the polygon as a {@link BigDecimal}.
     * @throws IllegalArgumentException if the radius or polygonSidesNum are invalid.
     */
    private BigDecimal calculatePolygonSegmentSideLength(BigDecimal radius, BigDecimal polygonSidesNum) {
        BigDecimal segmentRad = circleRad.divide(polygonSidesNum, MC);

        BigDecimal radiusPowSquare = radius.pow(2);
        BigDecimal cosPart = new BigDecimal(2)
                .multiply(radius, MC)
                .multiply(radius, MC)
                .multiply(BigDecimalMath.cos(segmentRad, MC).abs(MC), MC);
        return BigDecimalMath.sqrt(radiusPowSquare
                                           .add(radiusPowSquare, MC)
                                           .subtract(cosPart, MC), MC);
    }
}
