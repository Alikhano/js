package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import ru.alikhano.cyberlife.dto.enums.PaymentTypeDTO;
import ru.alikhano.cyberlife.model.enums.PaymentType;

@Mapper(componentModel="spring")
public interface PaymentTypeMapper extends BiConverter<PaymentType, PaymentTypeDTO> {
}
