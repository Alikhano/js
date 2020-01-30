package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.dto.ConsciousnessDTO;
import ru.alikhano.cyberlife.model.Consciousness;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ConsciousnessMapper extends BiConverter<Consciousness, ConsciousnessDTO> {

}
