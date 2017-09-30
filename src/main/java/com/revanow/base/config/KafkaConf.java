package com.revanow.base.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

//@Configuration
public class KafkaConf {
	
	
	@Bean
	public DefaultKafkaProducerFactory<Object, Object> producerFactory(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bootstrap.servers", "10.1.0.14:9092,10.1.0.15:9092");
		map.put("acks", "all");
		map.put("retries", 1);
		map.put("batch.size", 16384);
		map.put("linger.ms", 10);
		map.put("buffer.memory", 33554432);
		map.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		map.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		DefaultKafkaProducerFactory<Object, Object> producerFactory = new DefaultKafkaProducerFactory<Object, Object>(map);
		return producerFactory;
	}
	
	@Bean
	public KafkaTemplate<Object,Object> kafkaTemplate(DefaultKafkaProducerFactory<Object, Object> producerFactory){
		KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<Object,Object>(producerFactory,true);
		kafkaTemplate.setDefaultTopic("datacenter");
		return kafkaTemplate;
	}
}
