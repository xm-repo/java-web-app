package cmc.ps.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * The persistent class for the Business database table.
 * 
 */
@Entity
@Table(name="Business", schema="PropertyStructure")
@NamedQuery(name="Business.findAll", query="SELECT b FROM Business b")
public class Business implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=1000)
	@Size(min=0, max=1000)
	private String miscellaneous;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	@NotNull
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date purchaseDate = new Date();

	//bi-directional many-to-one association to LegalEntity
	@ManyToOne
	@JoinColumn(name="ObjectLeId", nullable=false)
	@NotNull
	private LegalEntity legalEntity1;

	//bi-directional many-to-one association to LegalEntity
	@ManyToOne
	@JoinColumn(name="SubjectLeId", nullable=false)
	@NotNull
	private LegalEntity legalEntity2;

	public Business() {
		
	}
	
	@AssertTrue
	public Boolean isNormal() {
		return (legalEntity1.getId().compareTo(legalEntity2.getId()) != 0);
	}
	
	public Business(String miscellaneous, Date purchaseDate, LegalEntity legalEntity1, LegalEntity legalEntity2) {
		
		this.id = 0;
		this.miscellaneous = miscellaneous;
		this.purchaseDate = (purchaseDate == null) ? new Date() : purchaseDate;
		this.legalEntity1 = legalEntity1;
		this.legalEntity2 = legalEntity2;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMiscellaneous() {
		return this.miscellaneous;
	}

	public void setMiscellaneous(String miscellaneous) {
		this.miscellaneous = miscellaneous;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
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

}