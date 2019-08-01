package com.bmw.sm.service;

import org.springframework.http.ResponseEntity;

public interface ConsumeApp {
	
	ResponseEntity<Object> ConsumeAppHealth(String appName);
	
	ResponseEntity<Object> ConsumeAppMetric(String appName, String metric);

}
