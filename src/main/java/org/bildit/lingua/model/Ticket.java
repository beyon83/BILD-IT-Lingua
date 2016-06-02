package org.bildit.lingua.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bildit.lingua.common.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * 
 * Ticket model
 * 
 * @author Mladen Todorovic
 * 
 * */

@Entity
@Table(name = "tickets")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Ticket extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Please select category")
	private String category;
	
	@NotBlank(message="Please enter some text in domestic language")
	private String textDomestic;
	
	@NotBlank(message="Please enter some text in foreign language")
	private String textForeign;
	
	@Temporal(TemporalType.DATE)
	private Date deactivated;
	
	@Column(columnDefinition = "BIT", length = 1)
	private boolean edited;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="ticket_votes_up", 
	   		   joinColumns=@JoinColumn(name="ticket_id"),
	   		   inverseJoinColumns=@JoinColumn(name="vote_id"))
	List<Vote> votesUp = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="ticket_votes_down", 
	   		   joinColumns=@JoinColumn(name="ticket_id"),
	   		   inverseJoinColumns=@JoinColumn(name="vote_id"))
	List<Vote> votesDown = new ArrayList<>();
	
	private String dateCreated;
	
	public String getTextDomestic() {
		return textDomestic;
	}

	public void setTextDomestic(String textDomestic) {
		this.textDomestic = textDomestic;
	}

	public String getTextForeign() {
		return textForeign;
	}

	public void setTextForeign(String textForeign) {
		this.textForeign = textForeign;
	}
	
	public Date getDeactivated() {
		return deactivated;
	}

	public void setDeactivated(Date deactivated) {
		this.deactivated = deactivated;
	}

	public boolean isEdited() {
		return edited;
	}

	public void setEdited(boolean edited) {
		this.edited = edited;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<Vote> getVotesUp() {
		return votesUp;
	}

	public List<Vote> getVotesDown() {
		return votesDown;
	}
	
}
