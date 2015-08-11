package cmc.ps.model;

import java.io.Serializable;

//import org.springframework.validation.annotation.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

//import cmc.ps.validation.OwnerConstraint;


/**
 * The persistent class for the Owner database table.
 * 
 */
@Entity
@Table(name="Owner", schema="PropertyStructure")
@NamedQuery(name="Owner.findAll", query="SELECT o FROM Owner o")
//@OwnerConstraint
public class Owner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false)
	@Range(min=1, max=100)
	private Integer property = new Integer(1);

	//bi-directional many-to-one association to LegalEntity
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name="LeId", nullable=false)
	@NotNull
	private LegalEntity legalEntity1;

	//bi-directional many-to-one association to LegalEntity
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name="LeOwnerId")
	private LegalEntity legalEntity2;

	//bi-directional many-to-one association to PhysicalPerson
	@ManyToOne//(cascade = {CascadeType.ALL})
	@JoinColumn(name="PhPersonOwnerId")
	//@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private PhysicalPerson physicalPerson;
	
	@Transient
	private Boolean isNormal;

	@AssertTrue
	public Boolean isNormal() {
		return true; 
		//return (legalEntity2 != null && physicalPerson == null && legalEntity1.getId().compareTo(legalEntity2.getId()) != 0) 
		//		|| (legalEntity2 == null && physicalPerson != null);
	}
	
	public Owner() {
	}
	
	public Owner(int property,  LegalEntity legalEntity1, LegalEntity legalEntity2, PhysicalPerson physicalPerson) {
		this.id = 0;
		this.property = property;
		this.legalEntity1 = legalEntity1;
		this.legalEntity2 = legalEntity2;
		this.physicalPerson = physicalPerson;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getProperty() {
		return this.property;
	}

	public void setProperty(int property) {
		this.property = property;
	}

	public LegalEntity getLegalEntity1() {
		return this.legalEntity1;
	}

	public void setLegalEntity1(LegalEntity legalEntity1) {
		this.legalEntity1 = legalEntity1;
	}

	public LegalEntity getLegalEntity2() {
		return this.legalEntity2;
	}

	public void setLegalEntity2(LegalEntity legalEntity2) {
		this.legalEntity2 = legalEntity2;
	}

	public PhysicalPerson getPhysicalPerson() {
		return this.physicalPerson;
	}

	public void setPhysicalPerson(PhysicalPerson physicalPerson) {
		this.physicalPerson = physicalPerson;
	}

}