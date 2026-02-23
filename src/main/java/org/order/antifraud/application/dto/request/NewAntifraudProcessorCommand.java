package org.order.antifraud.application.dto.request;

import org.order.antifraud.domain.enums.MethodPayment;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record NewAntifraudProcessorCommand (
        EventExternal eventPayment,
        String paymentId,
        String correlationId,
        String costumerId,
        BigDecimal amount,
        MethodPayment methodPayment,
        ZonedDateTime createdAt
){
}
