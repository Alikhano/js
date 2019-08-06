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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderItemId")
	private int orderItemId;
	
	@Column(name="orderQuantity")
	@NotNull
	@Min(value=1)
	private int orderQuantity;
	
	@Column(name="orderTotal")
	@NotNull
	@Min(value=100)
	private double orderTotal;
	
	@ManyToOne
	@JoinColumn(name="productId")
	@NotNull
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	@NotNull
	private Orders order;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderItemId;
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
		OrderItem other = (OrderItem) obj;
		if (orderItemId != other.orderItemId)
			return false;
		return true;
	}
}
