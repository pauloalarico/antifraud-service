package org.order.antifraud.infra.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.order.antifraud.domain.model.AntiFraud;
import org.order.antifraud.domain.repository.AntiFraudRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AntiFraudMongoRepositoryImpl implements AntiFraudRepository {
    private final AntiFraudMongoRepositoryI repository;

    @Override
    public AntiFraud save(AntiFraud antiFraud) {
        return repository.save(antiFraud);
    }
}
