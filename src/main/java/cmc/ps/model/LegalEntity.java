package cmc.ps.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the LegalEntity database table.
 * 
 */
@Entity
@Table(name="LegalEntity", schema="PropertyStructure")
@NamedQuery(name="LegalEntity.findAll", query="SELECT l FROM LegalEntity l")
public class LegalEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=300)
	@NotBlank
	@Size(min=2, max=300)
	private String businessName;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	@NotNull
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date foundationDate = new Date();

	@Column(nullable=false)
	@Type(type="org.hibernate.type.NumericBooleanType")
	@NotNull
	private Boolean isLe = true;

	@Column(length=1000)
	@Size(min=0, max=1000)
	private String miscellaneous;

	//bi-directional many-to-one association to Business
	@OneToMany(mappedBy="legalEntity1")
	@Valid
	private List<Business> businesses1 = new ArrayList<Business>();

	//bi-directional many-to-one association to Business
	@OneToMany(mappedBy="legalEntity2")
	@Valid
	private List<Business> businesses2 = new ArrayList<Business>();

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="legalEntity")
	@Valid
	private List<History> histories = new ArrayList<History>();

	//bi-directional many-to-one association to Owner
	@OneToMany(mappedBy="legalEntity1")//, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@Valid
	//@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Owner> owners1 = new ArrayList<Owner>();

	//bi-directional many-to-one association to Owner
	@OneToMany(mappedBy="legalEntity2")
	@Valid
	private List<Owner> owners2 = new ArrayList<Owner>();

	public LegalEntity() {		
	}
	
	public LegalEntity(String businessName, Date foundationDate, Boolean isLe, String miscellaneous) {
		
		this(businessName, foundationDate, isLe, miscellaneous, null, null, null, null, null);
	}
	
	public LegalEntity(String businessName, Date foundationDate, Boolean isLe, String miscellaneous, 
			List<Business> businesses1, List<Business> businesses2, List<History> histories, 
			List<Owner> owners1, List<Owner> owners2) {
		
		this.id = 0;
		this.businessName = businessName;
		this.foundationDate = (foundationDate == null) ? new Date() : foundationDate;
		this.isLe = isLe;
		this.miscellaneous = miscellaneous; 
		this.businesses1 = (businesses1 != null) ? businesses1 : new ArrayList<Business>();
		this.businesses2 = (businesses2 != null) ? businesses2 : new ArrayList<Business>();
		this.histories = (histories != null) ? histories : new ArrayList<History>();
		this.owners1 = (owners1 != null) ? owners1 : new ArrayList<Owner>();
		this.owners2 = (owners2 != null) ? owners2 : new ArrayList<Owner>();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Date getFoundationDate() {
		return this.foundationDate;
	}

	public void setFoundationDate(Date foundationDate) {
		this.foundationDate = foundationDate;
	}

	public Boolean getIsLe() {
		return this.isLe;
	}

	public void setIsLe(Boolean isLe) {
		this.isLe = isLe;
	}

	public String getMiscellaneous() {
		return this.miscellaneous;
	}

	public void setMiscellaneous(String miscellaneous) {
		this.miscellaneous = miscellaneous;
	}

	public List<Business> getBusinesses1() {
		return this.businesses1;
	}

	public void setBusinesses1(List<Business> businesses1) {
		this.businesses1 = businesses1;
	}

	public Business addBusinesses1(Business businesses1) {
		getBusinesses1().add(businesses1);
		businesses1.setLegalEntity1(this);

		return businesses1;
	}

	public Business removeBusinesses1(Business businesses1) {
		getBusinesses1().remove(businesses1);
		businesses1.setLegalEntity1(null);

		return businesses1;
	}

	public List<Business> getBusinesses2() {
		return this.businesses2;
	}

	public void setBusinesses2(List<Business> businesses2) {
		this.businesses2 = businesses2;
	}

	public Business addBusinesses2(Business businesses2) {
		getBusinesses2().add(businesses2);
		businesses2.setLegalEntity2(this);

		return businesses2;
	}

	public Business removeBusinesses2(Business businesses2) {
		getBusinesses2().remove(businesses2);
		businesses2.setLegalEntity2(null);

		return businesses2;
	}

	public List<History> getHistories() {
		return this.histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public History addHistory(History history) {
		getHistories().add(history);
		history.setLegalEntity(this);

		return history;
	}

	public History removeHistory(History history) {
		getHistories().remove(history);
		history.setLegalEntity(null);

		return history;
	}

	public List<Owner> getOwners1() {
		return this.owners1;
	}

	public void setOwners1(List<Owner> owners1) {
		this.owners1 = owners1;
	}

	public Owner addOwners1(Owner owners1) {
		getOwners1().add(owners1);
		owners1.setLegalEntity1(this);

		return owners1;
	}

	public Owner removeOwners1(Owner owners1) {
		getOwners1().remove(owners1);
		owners1.setLegalEntity1(null);

		return owners1;
	}

	public List<Owner> getOwners2() {
		return this.owners2;
	}

	public void setOwners2(List<Owner> owners2) {
		this.owners2 = owners2;
	}

	public Owner addOwners2(Owner owners2) {
		getOwners2().add(owners2);
		owners2.setLegalEntity2(this);

		return owners2;
	}

	public Owner removeOwners2(Owner owners2) {
		getOwners2().remove(owners2);
		owners2.setLegalEntity2(null);

		return owners2;
	}

}