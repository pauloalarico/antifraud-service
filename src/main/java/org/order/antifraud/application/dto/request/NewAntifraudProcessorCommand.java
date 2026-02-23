package org.order.antifraud.application.dto.request;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public record NewAntifraudProcessorCommand (
        EventExternal eventPayment,
        UUID paymentId,
        UUID correlationId,
        UUID costumerId,
        BigDecimal amount,
        MethodPayment methodPayment,
        ZonedDateTime createdAt
){
}
