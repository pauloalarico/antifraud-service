package org.order.antifraud.domain.enums;

public enum RiskFraud {
    LOW,
    MEDIUM,
    HIGH,
    FRAUD;


    public static RiskFraud convertScoreToRisk(int score) {
        if (score >= 90) {
            return FRAUD;
        }

        if (score >= 65) {
            return HIGH;
        }

        if (score >= 45) {
            return MEDIUM;
        }

        return LOW;
    }
}
