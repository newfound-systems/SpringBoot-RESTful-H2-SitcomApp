/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
/**
 * 
 */
package rest.sitcom.app.client;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;

import rest.sitcom.app.entities.Sitcom;

public abstract class SitcomRestClientBase {

	public final static String USER = "dhl";
	public final static String PASSWORD = "dhl";
	
	public final static String encodedLogin = new String(Base64.encode((USER + ":" + PASSWORD).getBytes()));

	public final static String BASE_URL = "http://localhost:8080/sitcom";
	public final static String FIND_ALL = "/find/all";
	
	/**
	 * Get Http Information from ResponseEntity
	 * 
	 * @param <T>
	 */
	public <T> void getHttpResponseInfo(ResponseEntity<T> responseEntity) {
		MediaType contentType = responseEntity.getHeaders().getContentType();
		HttpStatus statusCode = responseEntity.getStatusCode();

		System.out.println("ContentType: " + contentType);
		System.out.println("StatusCode: " + statusCode);

		for (String c : responseEntity.getHeaders().get("Set-Cookie")) {
			System.out.println("Cookie: " + c);
		}
		@SuppressWarnings("unchecked")
		List<Sitcom> sitcoms = (List<Sitcom>) responseEntity.getBody();
		for (Sitcom sitcom : sitcoms) {
			System.out.println(sitcom);
		}
	}

	/**
	 * Basic Headers
	 * 
	 * @return
	 */
	public HttpHeaders basicAuthHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + encodedLogin);
		return headers;
	}
}