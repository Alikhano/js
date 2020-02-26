package ru.alikhano.cyberlife.converter;

import ru.alikhano.cyberlife.model.enums.PaymentType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class PaymentTypeJpaConverter implements AttributeConverter<PaymentType, String> {
    @Override
    public String convertToDatabaseColumn(PaymentType paymentType) {
        if (paymentType == null) {
            return null;
        }
        return paymentType.getValue();
    }

    @Override
    public PaymentType convertToEntityAttribute(String s) {
       if (s == null) {
           return null;
       }
       try {
           return PaymentType.valueOf(s);
       }
       catch (IllegalArgumentException ex) {
           return null;
       }
    }
}
