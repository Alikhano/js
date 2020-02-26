package ru.alikhano.cyberlife.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
	
	@Id
	@Column(name="productId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer productId;
	
	@Column(name="model", unique=true)
	@NotNull
	private String model;
	
	@Column(name="description")
	@NotNull
	private String description;
	
	@Column(name="units")
	@NotNull
	@Min(value=1)
	private Integer unitsInStock;

	@Column(name="price")
	@NotNull
	@Min(value=100)
	private Double price;
	
	@Lob
	@Column(name="image")
	private byte[] image;

	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="consId")
	private Consciousness cons;
	
	@OneToMany(mappedBy="product")
	@JsonIgnore
	private Set<CartItem> cartItems;

}
