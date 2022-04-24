package com.poc.microservices.logger.listener;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.poc.microservices.logger.models.Logs;
import com.poc.microservices.logger.repositories.LogRepository;

@Component
public class KafkaListenerClass {

	@Autowired private LogRepository repo;
	Logger logger = LoggerFactory.getLogger(getClass());
    
	//This listens on a topic
	@KafkaListener(topics="PM_activityLogs", groupId="groupId")
	void listener(String log) {
		
		Logs logModel = new Logs();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		logModel.setTimestamp(LocalDateTime.now().format(myFormatObj));
		logModel.setLog(log);
		repo.save(logModel);
		
	    logger.info(log);  
	}
}
