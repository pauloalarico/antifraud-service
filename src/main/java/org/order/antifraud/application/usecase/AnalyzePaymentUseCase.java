package org.order.antifraud.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.antifraud.application.dto.response.ResultAntiFraudService;
import org.order.antifraud.domain.enums.MethodPayment;
import org.order.antifraud.domain.enums.RiskFraud;
import org.order.antifraud.domain.model.AntiFraud;
import org.order.antifraud.infra.mapper.AntiFraudMapper;
import org.order.antifraud.infra.persistence.repository.AntiFraudMongoRepositoryI;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyzePaymentUseCase {
    private final AntiFraudMongoRepositoryI repository;
    private final AntiFraudMapper mapper;

    public ResultAntiFraudService analyze(AntiFraud antiFraud) {
        RiskFraud riskFraud = antiFraud.getRiskFraud();
        MethodPayment methodPayment = antiFraud.getMethod();
        antiFraud.approve();

        if(riskFraud == RiskFraud.HIGH && methodPayment == MethodPayment.CREDIT_CARD) {
            antiFraud.reprove();
        }

        if(riskFraud == RiskFraud.HIGH && methodPayment == MethodPayment.BILLING) {
            antiFraud.reprove();
        }

        if (riskFraud == RiskFraud.MEDIUM && methodPayment == MethodPayment.BILLING) {
            antiFraud.reprove();
        }

        if (riskFraud == RiskFraud.FRAUD) {
            antiFraud.reprove();
        }

        repository.save(antiFraud);
        return mapper.toResult(antiFraud);
    }
}
