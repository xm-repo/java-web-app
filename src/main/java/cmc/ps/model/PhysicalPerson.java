package cmc.ps.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


/**
 * The persistent class for the PhysicalPerson database table.
 * 
 */
@Entity
@Table(name="PhysicalPerson", schema="PropertyStructure")
@NamedQuery(name="PhysicalPerson.findAll", query="SELECT p FROM PhysicalPerson p")
public class PhysicalPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=1000)
	@Size(min=0, max=1000)
	private String biography;

	@Column(length=300)
	@Size(min=0, max=300)
	private String contacts;

	@Column(nullable=false, length=300)
	@NotBlank
	@Size(min=5, max=300)
	private String fullName;

	//bi-directional many-to-one association to Owner
	@OneToMany(mappedBy="physicalPerson")//, cascade = {CascadeType.ALL})
	//@Cascade(org.hibernate.annotations.CascadeType.ALL)
	//@Valid
	private List<Owner> owners = new ArrayList<Owner>();

	public PhysicalPerson() {
	}
	
	public PhysicalPerson(String fullName, String contacts, String biography) {
		
		this(fullName, contacts, biography, null);		
	}
	
	public PhysicalPerson(String fullName, String contacts, String biography, List<Owner> owners) {
		this.id = 0;
		this.fullName = fullName;
		this.contacts = contacts;
		this.biography = biography;
		this.owners = (owners != null) ? owners : new ArrayList<Owner>();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBiography() {
		return this.biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Owner> getOwners() {
		return this.owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

	public Owner addOwner(Owner owner) {
		
		this.owners.add(owner);
		owner.setPhysicalPerson(this);

		return owner;
	}

	public Owner removeOwner(Owner owner) {
		
		this.owners.remove(owner);
		owner.setPhysicalPerson(null);
		
		return owner;
	}

}