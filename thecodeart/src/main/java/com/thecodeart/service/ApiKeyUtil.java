package com.thecodeart.service;

public class ApiKeyUtil {

	private static final String EXPECTED_API_KEY = "thecodeart-api-key";

	public boolean isValidApiKey(String apiKey) {
		return apiKey != null && apiKey.equals(EXPECTED_API_KEY);
	}
}
