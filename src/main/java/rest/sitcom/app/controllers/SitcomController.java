/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.controllers;

import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.util.Lists;

import rest.sitcom.app.entities.Sitcom;
import rest.sitcom.app.model.SitcomComprehensive;
import rest.sitcom.app.model.Status;
import rest.sitcom.app.repo.interfaces.ActorRepository;
import rest.sitcom.app.repo.interfaces.SitcomRepository;
import rest.sitcom.app.repo.services.ActorRepositoryService;

@RestController
@RequestMapping("/sitcom")
public class SitcomController {

	private static final Logger logger = LogManager.getLogger(SitcomController.class);

	@Autowired
	SitcomRepository sitcomRepository;

	@Autowired
	ActorRepository actorRepository;

	@Autowired
	ActorRepositoryService actorRepositoryService;

	/**
	 * Find Sitcom(s)
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/find/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Sitcom>> findAll(@AuthenticationPrincipal final UserDetails user) {
		@SuppressWarnings("unchecked")
		List<Sitcom> sitcoms = IteratorUtils.toList(sitcomRepository.findAll().iterator());
		return new ResponseEntity<List<Sitcom>>(sitcoms, HttpStatus.OK);
	}

	/**
	 * Find Sitcom
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/find/id/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Sitcom> findOne(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("id") Long id) {
		return new ResponseEntity<Sitcom>(sitcomRepository.findOne(id), HttpStatus.OK);
	}

	/**
	 * Find Sitcom by Name
	 * 
	 * @param sitcom_name
	 * @return
	 */
	@RequestMapping(value = "/find/name/{sitcom_name}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Sitcom> findByName(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("sitcom_name") String sitcom_name) {
		return new ResponseEntity<Sitcom>(sitcomRepository.findByName(sitcom_name), HttpStatus.OK);
	}

	/**
	 * Find Sitcom by Name MatchAny
	 * 
	 * @param sitcom_name
	 * @return
	 */
	@RequestMapping(value = "/find/name/matchAny/{sitcom_name}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Sitcom>> findByNameMatchAny(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("sitcom_name") String sitcom_name) {
		return new ResponseEntity<List<Sitcom>>(sitcomRepository.findByNameMatchAny(sitcom_name), HttpStatus.OK);
	}

	/**
	 * Add Sitcom
	 * 
	 * @param user
	 * @param newReqStudent
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Status> add(@AuthenticationPrincipal final UserDetails user, @RequestBody Sitcom sitcom) {
		Status status = new Status();
		try {
			/**
			 * Save
			 */
			sitcomRepository.save(sitcom);
			status = new Status(true, HttpStatus.CREATED.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.CREATED);
	}

	/**
	 * Delete Actor linked to a Sitcom
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/id/{sitcom_id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Status> delete(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("sitcom_id") Long sitcom_id) {
		Status status = new Status();
		try {
			/**
			 * Delete Actors
			 */
			actorRepositoryService.deleteSitcomActors(sitcom_id);
			/**
			 * Delete Sitcom
			 */
			sitcomRepository.delete(sitcom_id);
			status = new Status(true, "Deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	/**
	 * Find Sitcom Comprehensive
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/find/comprehensive/id/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SitcomComprehensive> findComprehensive(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("id") Long id) {
		SitcomComprehensive comprehensive = new SitcomComprehensive();

		Sitcom sitcom = sitcomRepository.findOne(id);
		List<Sitcom> sitcoms = Lists.newArrayList();
		sitcoms.add(sitcom);

		comprehensive.setSitcoms(sitcoms);
		comprehensive.setActors(actorRepository.findSitcomActors(id));

		return new ResponseEntity<SitcomComprehensive>(comprehensive, HttpStatus.OK);
	}
}