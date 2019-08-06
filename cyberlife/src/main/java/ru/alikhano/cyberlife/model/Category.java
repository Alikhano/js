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
@Table(name="category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="categoryId")
	private int categoryId;
	
	@Column(name="categoryType", unique = true)
	@NotNull
	private String categoryType;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + ((categoryType == null) ? 0 : categoryType.hashCode());
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
		Category other = (Category) obj;
		if (categoryId != other.categoryId)
			return false;
		if (categoryType == null) {
			if (other.categoryType != null)
				return false;
		} else if (!categoryType.equals(other.categoryType)) {
			return false;
		}
			
		return true;
	}
}
