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
	private Integer categoryId;
	
	@Column(name="categoryType", unique = true)
	@NotNull
	private String categoryType;

}
