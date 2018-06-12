/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.repo.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rest.sitcom.app.entities.Sitcom;

@Repository
public interface SitcomRepository extends CrudRepository<Sitcom, Long> {

	/**
	 * Find by Name
	 * 
	 * @param sitcom_name
	 * @return
	 */
	Sitcom findByName(String sitcom_name);

	/**
	 * Find by Name MatchAny
	 * 
	 * @param sitcom_name
	 * @return
	 */
	List<Sitcom> findByNameMatchAny(String sitcom_name);
}