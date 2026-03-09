package org.order.antifraud.infra.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.order.antifraud.application.dto.response.ResultAntiFraudService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AntiFraudProducerI implements AntiFraudProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.kafka.producer.topic}")
    private String antiFraudTopic;

    @Override
    public void send(ResultAntiFraudService result) {
        kafkaTemplate.send(antiFraudTopic, result.correlationId(), result)
                .whenComplete((resultAnti, e) -> {
                    if (e != null) {
                        log.error("Failed to publish the message for the exception: {}, correlation: {}", e.getMessage(), result.correlationId());
                        return;
                    }

                    log.debug("AntiFraud processor sent for correlation id: {}", result.correlationId());
                }) ;
    }

}
