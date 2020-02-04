package ru.alikhano.cyberlife.converter;

import ru.alikhano.cyberlife.model.enums.PaymentStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PaymentStatusJpaConverter implements AttributeConverter<PaymentStatus, String> {
    @Override
    public String convertToDatabaseColumn(PaymentStatus paymentStatus) {
        if (paymentStatus == null) {
            return null;
        }
        return paymentStatus.getValue();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            return PaymentStatus.valueOf(s);
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
