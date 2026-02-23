package org.order.antifraud.domain.repository;

import org.order.antifraud.domain.model.AntiFraud;

public interface AntiFraudRepository {
    AntiFraud save(AntiFraud antiFraud);
}
