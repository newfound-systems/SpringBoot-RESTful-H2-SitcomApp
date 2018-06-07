/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.model;

import java.util.List;

import rest.sitcom.app.entities.Actor;
import rest.sitcom.app.entities.Sitcom;

public class SitcomComprehensive {

	private List<Sitcom> sitcoms;
	private List<Actor> actors;

	/**
	 * @return the sitcoms
	 */
	public List<Sitcom> getSitcoms() {
		return sitcoms;
	}

	/**
	 * @param sitcoms
	 *            the sitcoms to set
	 */
	public void setSitcoms(List<Sitcom> sitcoms) {
		this.sitcoms = sitcoms;
	}

	/**
	 * @return the actors
	 */
	public List<Actor> getActors() {
		return actors;
	}

	/**
	 * @param actors
	 *            the actors to set
	 */
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SitcomComprehensive [sitcoms=" + sitcoms + ", actors=" + actors + "]";
	}
}
