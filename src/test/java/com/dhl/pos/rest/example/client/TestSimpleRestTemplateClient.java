/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package com.dhl.pos.rest.example.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

public class TestSimpleRestTemplateClient {

	final static String USER = "dhl";
	final static String PASSWORD = "dhl";

	/**
	 * Get Http Information from ResponseEntity
	 * 
	 * @param <T>
	 */
	private <T> void getHttpResponseInfo(ResponseEntity<T> responseEntity) {
		MediaType contentType = responseEntity.getHeaders().getContentType();
		HttpStatus statusCode = responseEntity.getStatusCode();

		System.out.println("ContentType: " + contentType);
		System.out.println("StatusCode: " + statusCode);

		String jsonResponseString = (String) responseEntity.getBody();
		for (String c : responseEntity.getHeaders().get("Set-Cookie")) {
			System.out.println("Cookie: " + c);
		}
		System.out.println(jsonResponseString);
	}

	final static String encodedLogin = new String(Base64.encode((USER + ":" + PASSWORD).getBytes()));

	/**
	 * Create HttpClient for Rest Template
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private HttpClient createHttpClient(String username, String password) {
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
		provider.setCredentials(AuthScope.ANY, credentials);

		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		return client;
	}

	/**
	 * Create
	 * 
	 * @param httpClient
	 * @return
	 */
	private ClientHttpRequestFactory createRequestFactory(HttpClient httpClient) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setHttpClient(httpClient);
		return clientHttpRequestFactory;
	}

	/**
	 * Basic Headers
	 * 
	 * @return
	 */
	private HttpHeaders basicAuthHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + encodedLogin);
		return headers;
	}

	/**
	 * Find Customer
	 */
	private void findCustomer(String requestUrl) {

		// HttpClient httpClient = createHttpClient(USER, PASSWORD);
		// ClientHttpRequestFactory requestFactory =
		// createRequestFactory(httpClient);
		// RestTemplate restTemplate = new RestTemplate(requestFactory);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = basicAuthHeaders();

		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		System.out.println("Sending request.." + new DateTime());
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<String>() {
				});
		System.out.println("Got response: " + new DateTime());
		/**
		 * Parse Response
		 */
		getHttpResponseInfo(responseEntity);
	}

	final static String FIND_CUSTOMER_IN_UAT = "http://2.253.233.133:8080/POSRest/v1/search/customer/101000100006";
	final static String FIND_CUSTOMER_IN_DEV = "http://localhost:9080/v1/search/customer/101000100006";

	public static void main(String[] args) {
		new TestSimpleRestTemplateClient().findCustomer(FIND_CUSTOMER_IN_UAT);
	}
}