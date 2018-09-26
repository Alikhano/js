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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@Column(name="productId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productId;
	
	@Column(name="model", unique=true)
	@NotNull
	private String model;
	
	@Column(name="description")
	@NotNull
	private String description;
	
	@Column(name="units")
	@NotNull
	private int unitsInStock;

	@Column(name="price")
	@NotNull
	private double price;
	
	@Lob
	@Column(name="image")
	private byte[] image;

	@ManyToOne
	@JoinColumn(name="catId")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="consId")
	private Consciousness cons;
	
	@OneToMany(mappedBy="product")
	@JsonIgnore
	private Set<CartItem> cartItems;
	
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Consciousness getCons() {
		return cons;
	}

	public void setCons(Consciousness cons) {
		this.cons = cons;
	}
	
	

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + productId;
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
		Product other = (Product) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (productId != other.productId)
			return false;
		return true;
	}
	
	public Product() {
		
	}

	public Product(int productId, @NotNull String model, @NotNull String description, @NotNull int unitsInStock,
			@NotNull double price, Category category, Consciousness cons) {
		super();
		this.productId = productId;
		this.model = model;
		this.description = description;
		this.unitsInStock = unitsInStock;
		this.price = price;
		this.category = category;
		this.cons = cons;
	}
	
	

	
	
	

}
