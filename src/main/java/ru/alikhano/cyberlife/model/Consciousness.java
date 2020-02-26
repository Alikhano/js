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
	private Integer consId;
	
	@Column(name="level", unique = true)
	@NotNull
	private String level;
	
	@Column(name="description")
	@NotNull
	private String description;

}
