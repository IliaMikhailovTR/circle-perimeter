package com.ilia;

import java.math.BigDecimal;

public class Main {

    private static final int ERROR_PRECISION = 10;

    public static void main(String[] args) {
        CirclePerimeterPolygonSidesCalculator comparator = new CirclePerimeterPolygonSidesCalculator();
        comparator.calculatePolygonSidesNum(new BigDecimal("1"), ERROR_PRECISION);
    }
}