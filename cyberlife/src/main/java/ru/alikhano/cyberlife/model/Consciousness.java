package ru.alikhano.cyberlife.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="consciousness")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consciousness {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="consId")
	private int consId;
	
	@Column(name="level", unique = true)
	@NotNull
	private String level;
	
	@Column(name="description")
	@NotNull
	private String description;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + consId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consciousness other = (Consciousness) obj;
		if (consId != other.consId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description)) {
			
			return false;
		}
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level)) {
			return false;
		}
		return true;
	}
}
