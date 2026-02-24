package org.order.antifraud.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.antifraud.application.dto.request.NewAntifraudProcessorCommand;
import org.order.antifraud.domain.enums.MethodPayment;
import org.order.antifraud.domain.model.AntiFraud;
import org.order.antifraud.infra.persistence.repository.AntiFraudMongoRepositoryI;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class VerifyAntiFraudUseCase {
    private final static BigDecimal thousand = BigDecimal.valueOf(1000.00);
    private final static BigDecimal hundred = BigDecimal.valueOf(100.00);
    private final AntiFraudMongoRepositoryI repository;

    public AntiFraud verify(NewAntifraudProcessorCommand command) {
        String correlationId = command.correlationId();
        String consumerId = command.costumerId();
        BigDecimal amount = command.amount();
        MethodPayment method = command.methodPayment();
        ZonedDateTime dateTimeTransaction = command.createdAt();
        var antiFraud = AntiFraud.create(correlationId, consumerId, amount, method);

        int score = 0;

        if(method == MethodPayment.CREDIT_CARD) {
           score += 10;
        }

        if(method == MethodPayment.BILLING) {
            score += 20;
        }
        var hourTime = dateTimeTransaction.getHour();
        if(hourTime < 6 || hourTime > 22 ) {
            score += 40;
        }

        if(amount.compareTo(thousand) > 0) {
            score += 40;
        }

        if (amount.compareTo(hundred) > 0) {
            score += 10;
        }

        if(score < 40) {
            antiFraud.lowRisk();
        }

        if(score >= 40 && score < 65) {
            antiFraud.mediumRisk();
        }

        if(score >= 65 && score < 90) {
            antiFraud.highRisk();
        }

        if (score > 90) {
            antiFraud.setAsFraud();
        }
        return repository.save(antiFraud);
    }
}
