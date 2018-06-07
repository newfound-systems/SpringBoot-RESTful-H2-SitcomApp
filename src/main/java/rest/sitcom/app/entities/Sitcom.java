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
@Table(name = "sitcom")
public class Sitcom implements Serializable {

	private static final long serialVersionUID = 292813837894653090L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(optional = false)
	@Column(nullable = false)
	private String sitcom_name;

	public Sitcom() {
		super();
	}

	/**
	 * @param sitcom_name
	 */
	public Sitcom(String sitcom_name) {
		super();
		this.sitcom_name = sitcom_name;
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
	 * @return the sitcom_name
	 */
	public String getSitcom_name() {
		return sitcom_name;
	}

	/**
	 * @param sitcom_name
	 *            the sitcom_name to set
	 */
	public void setSitcom_name(String sitcom_name) {
		this.sitcom_name = sitcom_name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sitcom [id=" + id + ", sitcom_name=" + sitcom_name + "]";
	}
}
