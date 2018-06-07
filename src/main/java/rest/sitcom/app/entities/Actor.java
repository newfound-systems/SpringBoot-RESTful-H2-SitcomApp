/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actor")
public class Actor implements Serializable {

	private static final long serialVersionUID = 292813837894653090L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(optional = false)
	@Column(nullable = false)
	private Long sitcom_id;

	@Basic(optional = false)
	@Column(nullable = false)
	private String actor_fname;

	@Basic(optional = false)
	@Column(nullable = false)
	private String actor_lname;

	/**
	 * @param sitcom_id
	 * @param actor_fname
	 * @param actor_lname
	 */
	public Actor(Long sitcom_id, String actor_fname, String actor_lname) {
		super();
		this.sitcom_id = sitcom_id;
		this.actor_fname = actor_fname;
		this.actor_lname = actor_lname;
	}

	public Actor() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the sitcom_id
	 */
	public Long getSitcom_id() {
		return sitcom_id;
	}

	/**
	 * @param sitcom_id
	 *            the sitcom_id to set
	 */
	public void setSitcom_id(Long sitcom_id) {
		this.sitcom_id = sitcom_id;
	}

	/**
	 * @return the actor_fname
	 */
	public String getActor_fname() {
		return actor_fname;
	}

	/**
	 * @param actor_fname
	 *            the actor_fname to set
	 */
	public void setActor_fname(String actor_fname) {
		this.actor_fname = actor_fname;
	}

	/**
	 * @return the actor_lname
	 */
	public String getActor_lname() {
		return actor_lname;
	}

	/**
	 * @param actor_lname
	 *            the actor_lname to set
	 */
	public void setActor_lname(String actor_lname) {
		this.actor_lname = actor_lname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Actor [id=" + id + ", sitcom_id=" + sitcom_id + ", actor_fname=" + actor_fname + ", actor_lname="
				+ actor_lname + "]";
	}
}
