/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.client;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import rest.sitcom.app.entities.Sitcom;

public class SitcomRestClient extends SitcomRestClientBase {

	/**
	 * Find All
	 */
	private void findAll() {
		String url = BASE_URL + FIND_ALL;

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = basicAuthHeaders();

		HttpEntity<List<Sitcom>> requestEntity = new HttpEntity<List<Sitcom>>(headers);

		System.out.println("Sending request url: " + url);
		ResponseEntity<List<Sitcom>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<Sitcom>>() {
				});
		System.out.println("Got response: " + new DateTime());
		/**
		 * Parse Response
		 */
		getHttpResponseInfo(responseEntity);
	}

	public static void main(String[] args) {
		new SitcomRestClient().findAll();
	}
}