package org.order.antifraud.application.usecase;

import lombok.RequiredArgsConstructor;
import org.order.antifraud.application.dto.request.NewAntifraudProcessorCommand;
import org.order.antifraud.application.dto.response.ResultAntiFraudService;
import org.order.antifraud.domain.service.PaymentApprovalPolicy;
import org.order.antifraud.domain.service.ScoreCalculator;
import org.order.antifraud.domain.enums.RiskFraud;
import org.order.antifraud.domain.model.AntiFraud;
import org.order.antifraud.infra.mapper.AntiFraudMapper;
import org.order.antifraud.infra.persistence.repository.AntiFraudMongoRepositoryI;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class VerifyAntiFraudUseCase {

    private final AntiFraudMongoRepositoryI repository;
    private final AntiFraudMapper mapper;

    public ResultAntiFraudService verify(NewAntifraudProcessorCommand command) {
        ZonedDateTime dateTimeTransaction = command.createdAt();
        var antiFraud = AntiFraud.create(
                command.correlationId(),
                command.costumerId(),
                command.amount(),
                command.methodPayment()
        );

        RiskFraud riskFraud = ScoreCalculator.calculate(command.amount(), command.methodPayment(), dateTimeTransaction.getHour());
        antiFraud.classifyRisk(riskFraud);

        if (PaymentApprovalPolicy.reproveTest(riskFraud, command.methodPayment())) {
            antiFraud.reprove();
        } else {
            antiFraud.approve();
        }
        repository.save(antiFraud);
        return mapper.toResult(antiFraud);
    }
}
