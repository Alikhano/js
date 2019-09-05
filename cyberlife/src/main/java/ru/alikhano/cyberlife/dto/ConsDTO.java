package ru.alikhano.cyberlife.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Named;
import ru.alikhano.cyberlife.model.Consciousness;

@Getter
@Setter
@NoArgsConstructor
public class ConsDTO {
	
	private int consId;
	private String level;
	private String description;

	public ConsDTO(Consciousness cons) {
		this.consId = cons.getConsId();
		this.level = cons.getLevel();
		this.description = cons.getDescription();
	}
}
