package org.order.antifraud.infra.persistence.repository;

import org.order.antifraud.domain.model.AntiFraud;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AntiFraudMongoRepositoryI extends MongoRepository<AntiFraud, String> {
}
