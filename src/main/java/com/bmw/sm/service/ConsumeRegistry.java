package com.bmw.sm.service;

import org.springframework.http.ResponseEntity;

import com.bmw.sm.model.Status;

public interface ConsumeRegistry {
	
	ResponseEntity<Object> ConsumeRegistryApps(String url);
	
	ResponseEntity<Object> ConsumeUpdateStatus(String url, String appId, String instId, Status value);


}
