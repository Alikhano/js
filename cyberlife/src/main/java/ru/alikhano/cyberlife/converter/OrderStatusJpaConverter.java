package ru.alikhano.cyberlife.converter;

import ru.alikhano.cyberlife.model.enums.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OrderStatusJpaConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        if (orderStatus == null) {
            return null;
        }
        return orderStatus.getValue();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            return OrderStatus.valueOf(s);
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
