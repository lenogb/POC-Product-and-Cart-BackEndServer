package com.poc.microservices.logger.utilss;


import java.text.SimpleDateFormat;
import java.util.Date;

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
	@Autowired(required=false) private Logs logModel;
	
	private final Logger logIn = LoggerFactory.getLogger(KafkaListenerClass.class);
	
	//This listens on a topic
	@KafkaListener(topics="PM_activityLogs", groupId="groupId")
	void listener(String log) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy hh.mm aa");
		String toLog= dateFormat.format(new Date())+log;
		logIn.info(toLog);
		logModel.setTimestamp(dateFormat.format(new Date()));
		logModel.setLog(log);
		repo.save(logModel);
	}
}
