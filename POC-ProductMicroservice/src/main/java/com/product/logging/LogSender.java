package com.product.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class LogSender {

    @Autowired KafkaTemplate<String, String> kafkaTemplate;
    
    String topic=	"PM_activityLogs";
    
    public void logThis(String message) {
    	kafkaTemplate.send(
				topic,
				message);
    }
}
