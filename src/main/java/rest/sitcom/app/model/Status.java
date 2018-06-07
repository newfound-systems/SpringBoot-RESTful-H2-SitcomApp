/**
 * Copyright NEWFOUND SYSTEMS to Present
 * All Rights Reserved
 */
package rest.sitcom.app.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "status")
@XmlType(propOrder = { "result", "message" })
public class Status implements Serializable {

	private static final long serialVersionUID = -6431262331525302267L;

	private boolean result = false;
	private String message = "Fail";

	/**
	 * Constructor
	 */
	public Status() {
	}

	/**
	 * @param result
	 * @param message
	 */
	public Status(boolean result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Status [result=" + result + "]";
	}
}
