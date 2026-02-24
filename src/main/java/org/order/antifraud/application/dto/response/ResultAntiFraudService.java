package org.order.antifraud.application.dto.response;

import org.order.antifraud.domain.enums.MethodPayment;
import org.order.antifraud.domain.enums.PaymentStatus;
import org.order.antifraud.domain.enums.RiskFraud;

import java.math.BigDecimal;

public record ResultAntiFraudService(
        PaymentStatus paymentStatus,
        String id,
        String correlationId,
        String consumerId,
        BigDecimal amount,
        MethodPayment method,
        RiskFraud riskFraud
) {
}
