package org.order.antifraud.infra.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.order.antifraud.application.dto.request.NewAntifraudProcessorCommand;
import org.order.antifraud.application.dto.response.ResultAntiFraudService;
import org.order.antifraud.application.usecase.AnalyzePaymentUseCase;
import org.order.antifraud.application.usecase.VerifyAntiFraudUseCase;
import org.order.antifraud.domain.model.AntiFraud;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AntiFraudConsumer {
    private final VerifyAntiFraudUseCase verifyFraud;
    private final AnalyzePaymentUseCase analyzePayment;

    @KafkaListener(topics = "${app.kafka.newPaymentTopic}")
    public void execute(NewAntifraudProcessorCommand command) {
        AntiFraud antiFraud = verifyFraud.verify(command);
        ResultAntiFraudService result = analyzePayment.analyze(antiFraud);
    }
}
