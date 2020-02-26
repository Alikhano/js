package ru.alikhano.cyberlife.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="itemId")
	private Integer itemId;
	
	@Column(name="quantity")
	@NotNull
	@Min(value=1)
	private Integer quantity;
	
	@Column(name="totalPrice")
	@NotNull
	@Min(value=1)
	private Double totalPrice;
	
	@ManyToOne
	@JoinColumn(name="productId")
	@NotNull
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="cartId")
	@NotNull
	private Cart cart;

}
