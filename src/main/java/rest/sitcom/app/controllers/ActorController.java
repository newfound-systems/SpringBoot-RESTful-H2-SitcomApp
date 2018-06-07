/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.controllers;

import java.util.List;

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

import rest.sitcom.app.entities.Actor;
import rest.sitcom.app.entities.Sitcom;
import rest.sitcom.app.model.SitcomComprehensive;
import rest.sitcom.app.model.Status;
import rest.sitcom.app.repo.interfaces.ActorRepository;
import rest.sitcom.app.repo.interfaces.SitcomRepository;
import rest.sitcom.app.repo.services.ActorRepositoryService;

@RestController
@RequestMapping("/actor")
public class ActorController {

	private static final Logger logger = LogManager.getLogger(ActorController.class);

	@Autowired
	SitcomRepository sitcomRepository;

	@Autowired
	ActorRepository actorRepository;

	@Autowired
	ActorRepositoryService actorRepositoryService;

	/**
	 * Find All Actors(s)
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/find/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Actor>> findAll(@AuthenticationPrincipal final UserDetails user) {
		logger.info("Finding all Actor in all Sitcoms...");
		return new ResponseEntity<List<Actor>>(actorRepositoryService.findAll(), HttpStatus.OK);
	}

	/**
	 * Find Actor
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/find/{sitcom_id}/actor/{actor_id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Actor> findOne(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("sitcom_id") Long sitcom_id, @PathVariable("actor_id") Long actor_id) {
		logger.info("Finding Sitcom id {0} Actor {1}", sitcom_id, actor_id);
		return new ResponseEntity<Actor>(actorRepositoryService.findOne(sitcom_id, actor_id), HttpStatus.OK);
	}

	/**
	 * Delete Actor linked to a Sitcom
	 * 
	 * @param user
	 * @param sitcom_id
	 * @param actor_id
	 * @return
	 */
	@RequestMapping(value = "/delete/{sitcom_id}/actor/{actor_id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Status> delete(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("sitcom_id") Long sitcom_id, @PathVariable("actor_id") Long actor_id) {
		Status status = new Status();
		try {
			logger.info("Deleting Sitcom id {0} Actor {1}", sitcom_id, actor_id);
			/**
			 * Delete
			 */
			actorRepositoryService.delete(sitcom_id, actor_id);
			status = new Status(true, "Deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.ACCEPTED);
	}

	/**
	 * Add
	 * 
	 * @param user
	 * @param actor
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Status> add(@AuthenticationPrincipal final UserDetails user, @RequestBody Actor actor) {
		Status status = new Status();
		try {
			logger.info("Adding new Actor {0}", actor);
			actorRepositoryService.save(actor);
			status = new Status(true, HttpStatus.CREATED.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.CREATED);
	}

	/**
	 * Find Sitcom Comprehensive
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/find/multi/fname/{fname}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SitcomComprehensive> findComprehensive(@AuthenticationPrincipal final UserDetails user,
			@PathVariable("fname") String actor_fname) {
	
		logger.info("Finding Actor in Multiple Sitcom Actor FirstName {0}", actor_fname);
		SitcomComprehensive comprehensive = new SitcomComprehensive();

		List<Actor> actors = actorRepository.findActorByFirstName(actor_fname);
		
		List<Sitcom> sitcoms = Lists.newArrayList();
		Sitcom sitcom = null;
		for (Actor o : actors) {
			sitcom = sitcomRepository.findOne(o.getSitcom_id());
			sitcoms.add(sitcom);
		}
		comprehensive.setSitcoms(sitcoms);
		comprehensive.setActors(actors);

		return new ResponseEntity<SitcomComprehensive>(comprehensive, HttpStatus.OK);
	}
}