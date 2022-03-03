package com.poc.microservices.activityLogger.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.poc.microservices.activityLogger.model.Logs;
import com.poc.microservices.activityLogger.repositories.logsModelRepository;

@Component
public class KafkaListenerClass {

	@Autowired private logsModelRepository repo;
	@Autowired(required=false) private Logs logModel;
	
	//This listens on a topic
	@KafkaListener(topics="PM_activityLogs", groupId="logger")
	void listener(String log) {
		logModel.setLog(log);
		repo.save(logModel);
	}
}
