package ru.alikhano.cyberlife.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cartId")
	private int cartId;
	
	@Column(name="grandTotal")
	private double grandTotal;
	
	@OneToMany(mappedBy="cart")
	private Set<CartItem> items = new HashSet<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
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
		Cart other = (Cart) obj;
		if (cartId != other.cartId)
			return false;
		return true;
	}

}
