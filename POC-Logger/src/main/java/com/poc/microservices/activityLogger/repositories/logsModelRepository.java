package com.poc.microservices.activityLogger.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.poc.microservices.activityLogger.model.Logs;

@Repository
public interface logsModelRepository extends MongoRepository<Logs, String>{

}
