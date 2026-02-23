package org.order.antifraud.domain.model;

import org.order.antifraud.domain.enums.MethodPayment;
import org.order.antifraud.domain.enums.RiskFraud;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "${apps.}")
public class AntiFraud {
    @Id
    private String id;
    private String correlationId;
    private String consumerId;
    private BigDecimal amount;
    private MethodPayment method;
    private RiskFraud riskFraud;

    public static AntiFraud create(String correlationId, String consumerId, BigDecimal amount, MethodPayment method) {
        AntiFraud antiFraud = new AntiFraud();
        antiFraud.correlationId = correlationId;
        antiFraud.consumerId = consumerId;
        antiFraud.amount = amount;
        antiFraud.method = method;
        antiFraud.riskFraud = RiskFraud.LOW;
        return antiFraud;
    }

    public void highRisk() {
        this.riskFraud = RiskFraud.HIGH;
    }

    public void lowRisk() {
        this.riskFraud = RiskFraud.LOW;
    }

    public void setAsFraud() {
        this.riskFraud = RiskFraud.FRAUD;
    }
}
