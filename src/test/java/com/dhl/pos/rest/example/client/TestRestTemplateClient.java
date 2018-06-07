/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package com.dhl.pos.rest.example.client;

import java.nio.charset.Charset;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
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

public class TestRestTemplateClient {

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
		/**
		 * Using JDK8
		 */
		// responseEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);

		System.out.println(jsonResponseString);
	}

	final static String encodedLogin = new String(Base64.encode((USER + ":" + PASSWORD).getBytes()));

	/**
	 * Basic Headers
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private HttpHeaders basicAuthHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + encodedLogin);
		return headers;
	}

	/**
	 * Get Authorization
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private HttpHeaders basicAuthHeaders(String username, String password) {
		return new HttpHeaders() {
			private static final long serialVersionUID = 3964635694709716852L;
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

	/**
	 * Find Customer
	 */
	private void findCustomer(String requestUrl) {
		RestTemplate restTemplate = null;
		/**
		 * Plain RestTemplate
		 */
		restTemplate = new RestTemplate();
		/**
		 * Using via HttpClient
		 */
		// restTemplate = new RestTemplate(getClientHttpRequestFactory());
		HttpHeaders headers = basicAuthHeaders(USER, PASSWORD);

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

	/**
	 * Using HttpClient with RestTemplate
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		int timeout = 15000;
		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
				.setSocketTimeout(timeout).setAuthenticationEnabled(true).build();
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		return new HttpComponentsClientHttpRequestFactory(client);
	}

	/**
	 * @param restTemplate
	 * @param requestUrl
	 */
	@SuppressWarnings("unused")
	private void getHeadForHeaders(RestTemplate restTemplate, String requestUrl) {
		HttpHeaders headers = restTemplate.headForHeaders(requestUrl);
		System.out.println(headers);
	}

	final static String FIND_CUSTOMER_IN_UAT = "http://2.253.233.133:8080/POSRest/v1/search/customer/101000100006";
	final static String FIND_CUSTOMER_IN_DEV = "http://localhost:9080/v1/search/customer/101000100006";

	public static void main(String[] args) {
		new TestRestTemplateClient().findCustomer(FIND_CUSTOMER_IN_UAT);
	}
}