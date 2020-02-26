package ru.alikhano.cyberlife.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.converter.OrderStatusJpaConverter;
import ru.alikhano.cyberlife.converter.PaymentStatusJpaConverter;
import ru.alikhano.cyberlife.converter.PaymentTypeJpaConverter;
import ru.alikhano.cyberlife.model.enums.OrderStatus;
import ru.alikhano.cyberlife.model.enums.PaymentStatus;
import ru.alikhano.cyberlife.model.enums.PaymentType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId")
	private Integer orderId;

	@Column(name = "paymentType")
	@Convert(converter = PaymentTypeJpaConverter.class)
	@NotNull
	private PaymentType paymentType;

	@Column(name = "paymentStatus")
	@Convert(converter = PaymentStatusJpaConverter.class)
	@NotNull
	private PaymentStatus paymentStatus;

	@Column(name = "orderStatus")
	@Convert(converter = OrderStatusJpaConverter.class)
	@NotNull
	private OrderStatus orderStatus;

	@OneToOne
	@JoinColumn(name = "customerId")
	@NotNull
	Customer customer;

	@Column(name = "orderPrice")
	@NotNull
	@Min(value=100)
	private Double orderPrice;

	@Column(name = "orderDate")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date orderDate;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<OrderItem> orderedItems = new HashSet<>(0);

}
