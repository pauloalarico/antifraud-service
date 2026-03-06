package org.order.antifraud.domain.service;

import org.order.antifraud.domain.enums.MethodPayment;
import org.order.antifraud.domain.enums.RiskFraud;

public final class PaymentApprovalPolicy {

    public static boolean reproveTest(RiskFraud risk, MethodPayment methodPayment) {
        return switch (risk) {
            case FRAUD -> true;
            case HIGH -> methodPayment == MethodPayment.CREDIT_CARD || methodPayment == MethodPayment.BILLING;
            case MEDIUM -> methodPayment == MethodPayment.BILLING;
            case LOW -> false;
        };
    }

}
