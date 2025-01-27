package com.ilia;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BigDecimalUtils {

    public static final MathContext MC = new MathContext(20, RoundingMode.UP);
    public static final BigDecimal PI = new BigDecimal(Math.PI, MC);
}
