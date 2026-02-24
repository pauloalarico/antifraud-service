package org.order.antifraud.infra.mapper;

import org.order.antifraud.application.dto.response.ResultAntiFraudService;
import org.order.antifraud.domain.model.AntiFraud;
import org.springframework.stereotype.Component;

@Component
public class AntiFraudMapper {

    public ResultAntiFraudService toResult(AntiFraud antiFraud) {
        return new ResultAntiFraudService(
                antiFraud.getPaymentStatus(),
                antiFraud.getId(),
                antiFraud.getCorrelationId(),
                antiFraud.getConsumerId(),
                antiFraud.getAmount(),
                antiFraud.getMethod(),
                antiFraud.getRiskFraud()
        );
    }
}
