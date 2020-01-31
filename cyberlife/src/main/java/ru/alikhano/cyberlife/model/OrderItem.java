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
@Table(name="orderItem")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderItemId")
	private Integer orderItemId;
	
	@Column(name="orderQuantity")
	@NotNull
	@Min(value=1)
	private Integer orderQuantity;
	
	@Column(name="orderTotal")
	@NotNull
	@Min(value=100)
	private Double orderTotal;
	
	@ManyToOne
	@JoinColumn(name="productId")
	@NotNull
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	@NotNull
	private Orders order;

}
