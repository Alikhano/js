package ru.alikhano.cyberlife.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cartItem")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="itemId")
	private int itemId;
	
	@Column(name="quantity")
	@NotNull
	@Min(value=1)
	private int quantity;
	
	@Column(name="totalPrice")
	@NotNull
	@Min(value=1)
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name="productId")
	@NotNull
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="cartId")
	@NotNull
	private Cart cart;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemId;
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
		CartItem other = (CartItem) obj;
		if (itemId != other.itemId)
			return false;
		return true;
	}

	public CartItem(int itemId, @NotNull @Min(1) int quantity, @NotNull @Min(1) double totalPrice,
			@NotNull Product product, @NotNull Cart cart) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.product = product;
		this.cart = cart;
	}

	public CartItem() {
	
	}

}
