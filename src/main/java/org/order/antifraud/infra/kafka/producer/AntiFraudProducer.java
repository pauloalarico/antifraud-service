package org.order.antifraud.infra.kafka.producer;

import org.order.antifraud.application.dto.response.ResultAntiFraudService;

public interface AntiFraudProducer {
    void send (ResultAntiFraudService result);
}
