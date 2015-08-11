package cmc.ps.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;


/**
 * The persistent class for the History database table.
 * 
 */
@Entity
@Table(name="History", schema="PropertyStructure")
@NamedQuery(name="History.findAll", query="SELECT h FROM History h")
public class History implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	@NotNull
	@Past
	private Date changeDate;

	@Column(length=1000)
	@Size(min=0, max=1000)
	private String miscellaneous;

	@Column(nullable=false, length=300)
	@Size(min=1, max=300)
	@NotBlank
	private String oldBusinessName;

	//bi-directional many-to-one association to LegalEntity
	@ManyToOne
	@JoinColumn(name="LeId", nullable=false)
	@NotNull
	private LegalEntity legalEntity;

	public History() {
		
	}
	
	public History(Date changeDate, String miscellaneous, String oldBusinessName, 
			LegalEntity legalEntity) {
		
		this.id = 0;
		this.changeDate = (changeDate == null) ? new Date() : changeDate;
		this.miscellaneous = miscellaneous;
		this.oldBusinessName = oldBusinessName;
		this.legalEntity = legalEntity;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getChangeDate() {
		return this.changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getMiscellaneous() {
		return this.miscellaneous;
	}

	public void setMiscellaneous(String miscellaneous) {
		this.miscellaneous = miscellaneous;
	}

	public String getOldBusinessName() {
		return this.oldBusinessName;
	}

	public void setOldBusinessName(String oldBusinessName) {
		this.oldBusinessName = oldBusinessName;
	}

	public LegalEntity getLegalEntity() {
		return this.legalEntity;
	}

	public void setLegalEntity(LegalEntity legalEntity) {
		this.legalEntity = legalEntity;
	}

}