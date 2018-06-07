/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.repo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rest.sitcom.app.entities.Sitcom;

@Repository
public interface SitcomRepository extends CrudRepository<Sitcom, Long> {
}