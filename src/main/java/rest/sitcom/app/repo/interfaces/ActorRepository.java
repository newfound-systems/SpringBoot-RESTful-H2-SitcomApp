/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.repo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rest.sitcom.app.entities.Actor;

@Repository
@Transactional(readOnly = true)
public interface ActorRepository extends CrudRepository<Actor, Long> {

	/**
	 * Find All
	 * 
	 * @param code
	 * @return
	 */
	@Query(value = " SELECT o FROM Actor o ", nativeQuery = false)
	List<Actor> findAll();

	/**
	 * Find Actor
	 * 
	 * @param actor_id
	 * @return
	 */
	@Query(value = " SELECT o FROM Actor o WHERE o.sitcom_id = ?1 AND o.id = ?2 ", nativeQuery = false)
	Actor findOne(@Param("sitcom_id") Long sitcom_id, @Param("actor_id") Long actor_id);

	/**
	 * Delete Actor
	 * 
	 * @param sitcom_id
	 * @param actor_id
	 */
	@Modifying
	@Transactional
	@Query(value = " DELETE FROM actor o WHERE o.sitcom_id = ?1 AND o.id = ?2 ", nativeQuery = true)
	void delete(@Param("sitcom_id") Long sitcom_id, @Param("actor_id") Long actor_id);
	
	/**
	 * Find Actor linked to a Sitcom
	 * 
	 * @param sitcom_id
	 * @return
	 */
	@Query(value = " SELECT o FROM Actor o WHERE o.sitcom_id = ?1 ", nativeQuery = false)
	List<Actor> findSitcomActors(@Param("sitcom_id") Long sitcom_id);
	
	/**
	 * Find Actor by First Name
	 * 
	 * @param actor_fname
	 * @return
	 */
	@Query(value = " SELECT o FROM Actor o WHERE o.actor_fname = ?1 ", nativeQuery = false)
	List<Actor> findActorByFirstName(@Param("actor_fname") String actor_fname);
}