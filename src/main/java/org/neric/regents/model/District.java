package org.neric.regents.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="district")
public class District implements Serializable
{
	private static final long serialVersionUID = 1L;

	public District() {
		super();
	}

	public District(Integer id, String name, String bedsCode) {
		super();
		this.id = id;
		this.name = name;
		this.bedsCode = bedsCode;
	}

	public District(Integer id, String name, String bedsCode, Boolean visible, Boolean locked) {
		super();
		this.id = id;
		this.name = name;
		this.bedsCode = bedsCode;
		this.visible = visible;
		this.locked = locked;
	}

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "district_id", unique = true, nullable = false)
	private Integer id;
	
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;
	
	@NotEmpty
	@Column(name="name", unique=true, nullable=false)
	private String name;
	
	@Column(name="bedsCode", unique=false, nullable=true)	
	private String bedsCode;
	
	@Column(name="visible", unique=false, nullable=true)	
	private Boolean visible;
	
	@Column(name="locked", unique=false, nullable=true)	
	private Boolean locked;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	private Set<UserDistrict> userDistricts = new HashSet<UserDistrict>(0);


	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getBedsCode() {
		return bedsCode;
	}

	public void setBedsCode(String bedsCode) {
		this.bedsCode = bedsCode;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	
	
	public Set<UserDistrict> getUserDistricts()
	{
		return userDistricts;
	}

	public void setUserDistricts(Set<UserDistrict> userDistricts)
	{
		this.userDistricts = userDistricts;
	}

	@Override
	public String toString()
	{
		return "District [id=" + id + ", name=" + name + ", bedsCode=" + bedsCode + ", visible=" + visible + ", locked=" + locked + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
}
