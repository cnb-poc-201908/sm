package com.bmw.sm.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumeAppImpl implements ConsumeApp {

	@Override
	public ResponseEntity<Object> ConsumeAppHealth(String appName) {

		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		String apiUrl = "http://" + appName + "/actuator/health";
		ResponseEntity<Object> response = client.exchange(apiUrl, HttpMethod.GET, new HttpEntity<Object>(headers),
				Object.class);
		return response;

		// TODO Auto-generated method stub
	}

	@Override
	public ResponseEntity<Object> ConsumeAppMetric(String appName, String metric) {
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		String apiUrl = "http://" + appName + "/actuator/metrics/" + metric;
		ResponseEntity<Object> response = client.exchange(apiUrl, HttpMethod.GET, new HttpEntity<Object>(headers),
				Object.class);
		return response;

		// TODO Auto-generated method stub
	}

}
