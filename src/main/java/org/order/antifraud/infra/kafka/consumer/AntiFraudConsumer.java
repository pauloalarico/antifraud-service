package org.order.antifraud.infra.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.order.antifraud.application.dto.request.NewAntifraudProcessorCommand;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AntiFraudConsumer {


    @KafkaListener(topics = "${app.kafka.newPaymentTopic}")
    public void execute(NewAntifraudProcessorCommand command) {

    }
}
