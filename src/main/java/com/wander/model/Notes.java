package com.wander.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author mrsagar
 *
 */

@Entity
@Table(name = "notes")
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", nullable = false)
	@NotEmpty(message = "Please provide a title")
	private String title;

	@Column(name = "description", nullable = false)
	@NotEmpty(message = "Please provide a description")
	private String description;

	@Column(name="created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Column(name="updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@Column(name="is_deleted")
	private boolean isDeleted = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
