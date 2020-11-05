package com.smart.tx;

import java.math.BigDecimal;

/**
 * java
 */
public class TestMain {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("2.00");
        BigDecimal bigDecimal1 = new BigDecimal("2.0");
        System.out.println(bigDecimal.compareTo(bigDecimal1) == 0);

    }
}
