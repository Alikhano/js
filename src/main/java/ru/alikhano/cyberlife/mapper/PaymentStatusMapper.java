package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import ru.alikhano.cyberlife.dto.enums.PaymentStatusDTO;
import ru.alikhano.cyberlife.model.enums.PaymentStatus;

@Mapper(componentModel="spring")
public interface PaymentStatusMapper extends BiConverter<PaymentStatus, PaymentStatusDTO> {
}
