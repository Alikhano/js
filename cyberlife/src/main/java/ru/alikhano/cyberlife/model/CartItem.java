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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cartItem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
