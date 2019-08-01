package com.bmw.sm.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bmw.sm.model.Status;

@Service
public class ConsumeRegistryImpl implements ConsumeRegistry {
	

	@Override
	public ResponseEntity<Object> ConsumeRegistryApps(String registryUrl) {
		
		String apiUrl = registryUrl + "eureka/apps";
		
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		ResponseEntity<Object> response = client.exchange(apiUrl,HttpMethod.GET, new HttpEntity<Object>(headers), Object.class);
		
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public ResponseEntity<Object> ConsumeUpdateStatus(String registryUrl, String appId, String instId, Status value) {
		
		String apiUrl = registryUrl + "eureka/apps/" + appId + "/" + instId + "/" +"status?value=" + value;
		
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		ResponseEntity<Object> response = client.exchange(apiUrl,HttpMethod.PUT, new HttpEntity<Object>(headers), Object.class);
		
		// TODO Auto-generated method stub
		return response;
	}
	
	
	

}

