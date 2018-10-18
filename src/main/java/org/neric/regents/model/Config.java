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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="config")
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;

	public Config() {
		super();
	}

	public Config(String id, String uuid, String href, String description, String data) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.href = href;
		this.description = description;
		this.data = data;
	}

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "uuid", unique=true, nullable = false)
	private String uuid;
	
	@NotEmpty
	@Column(name="href", unique=true, nullable=false)
	private String href;
	
	@NotEmpty
	@Column(name="description", unique=true, nullable=false)
	private String description;
	
	@NotEmpty
	@Column(name="data", unique=true, nullable=false)
	private String data;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((href == null) ? 0 : href.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Config other = (Config) obj;
		if (data == null) {
			if (other.data != null) return false;
		}
		else if (!data.equals(other.data)) return false;
		if (description == null) {
			if (other.description != null) return false;
		}
		else if (!description.equals(other.description)) return false;
		if (href == null) {
			if (other.href != null) return false;
		}
		else if (!href.equals(other.href)) return false;
		if (id == null) {
			if (other.id != null) return false;
		}
		else if (!id.equals(other.id)) return false;
		if (uuid == null) {
			if (other.uuid != null) return false;
		}
		else if (!uuid.equals(other.uuid)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "Config [id=" + id + ", uuid=" + uuid + ", href=" + href + ", description=" + description + ", data=" + data + "]";
	}
}
