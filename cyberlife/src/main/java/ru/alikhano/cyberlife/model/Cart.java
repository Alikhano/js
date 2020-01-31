package ru.alikhano.cyberlife.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cart")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cartId")
	private Integer cartId;
	
	@Column(name="grandTotal")
	private Double grandTotal;
	
	@OneToMany(mappedBy="cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CartItem> items = new HashSet<>();

}
