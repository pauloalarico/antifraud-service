package org.order.antifraud.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "${apps.}")
public class AntiFraud {
    @Id
    private String id;
    private String correlationId;
    private String consumerId;
    private BigDecimal amount;

}
