/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.repo.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.sitcom.app.entities.Actor;
import rest.sitcom.app.repo.interfaces.ActorRepository;

@Service
public class ActorRepositoryService {

	private static final Logger logger = LogManager.getLogger(ActorRepositoryService.class);

	private ActorRepository actorRepository;

	/**
	 * Inject
	 * 
	 * @param actorRepository
	 */
	@Autowired
	public ActorRepositoryService(ActorRepository actorRepository) {
		super();
		this.actorRepository = actorRepository;
	}

	/**
	 * Save
	 * 
	 * @param o
	 * @return
	 */
	public Actor save(Actor actor) {
		return actorRepository.save(actor);
	}

	/**
	 * Find All Actors
	 * 
	 * @return
	 */
	public List<Actor> findAll() {
		return actorRepository.findAll();
	}

	/**
	 * Find Actor associate with a Sitcom
	 * 
	 * @param sitcom_id
	 * @param actor_id
	 * @return
	 */
	public Actor findOne(Long sitcom_id, Long actor_id) {
		return actorRepository.findOne(sitcom_id, actor_id);
	}

	/**
	 * Delete Actor associated with a Sitcom
	 * 
	 * @param sitcom_id
	 * @param actor_id
	 */
	public boolean delete(Long sitcom_id, Long actor_id) {
		actorRepository.delete(sitcom_id, actor_id);
		return true;
	}

	/**
	 * Delete Sitcom
	 * 
	 * @param sitcom_id
	 */
	public void deleteSitcomActors(Long sitcom_id) {
		for (Actor actor : actorRepository.findSitcomActors(sitcom_id)) {
			logger.info("Deleting Actor {0} from Sitcom {1}", actor.getSitcom_id(), actor.getId());
			/**
			 * Delete Actor
			 */
			actorRepository.delete(actor.getSitcom_id(), actor.getId());
		}
	}
}