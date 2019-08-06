package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.dto.ConsDTO;
import ru.alikhano.cyberlife.model.Consciousness;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ConsciousnessMapper {
	
	ConsDTO consToConsDTO(Consciousness cons);
	Consciousness consDTOtoCons(ConsDTO consDTO);


}
