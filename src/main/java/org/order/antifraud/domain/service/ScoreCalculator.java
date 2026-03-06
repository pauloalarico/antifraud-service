package org.order.antifraud.domain.service;

import org.order.antifraud.domain.enums.MethodPayment;
import org.order.antifraud.domain.enums.RiskFraud;

import java.math.BigDecimal;

public final class ScoreCalculator {

    private static final BigDecimal HIGH_AMOUNT = BigDecimal.valueOf(1000);
    private static final BigDecimal MID_AMOUNT = BigDecimal.valueOf(400);
    private static final Integer START_HOUR_TIME = 22;
    private static final Integer END_HOUR_TIME = 6;

    public static RiskFraud calculate (BigDecimal amount, MethodPayment method, Integer hourOfPayment) {
        int score = 0;
        score += scoreOfMethod(method);
        score += scoreOfAmount(amount);
        score += scoreOfTime(hourOfPayment);
        return RiskFraud.convertScoreToRisk(score);
    }

    private static int scoreOfMethod(MethodPayment method) {
        return switch (method) {
            case CREDIT_CARD -> 10;
            case BILLING -> 20;
            default -> 0;
        };
    }

    private static int scoreOfAmount(BigDecimal amount) {
        if(amount.compareTo(HIGH_AMOUNT) > 0) {
            return 40;
        } else if (amount.compareTo(MID_AMOUNT) > 0) {
            return 10;
        }
        return 0;
    }

    private static int scoreOfTime(Integer hour) {
        return (hour >= END_HOUR_TIME || hour < START_HOUR_TIME) ? 40 : 0;
    }

}
