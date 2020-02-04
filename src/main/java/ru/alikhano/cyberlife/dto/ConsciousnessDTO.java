package ru.alikhano.cyberlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.Consciousness;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsciousnessDTO {
	
	private int consId;
	private String level;
	private String description;

	public ConsciousnessDTO(Consciousness cons) {
		this.consId = cons.getConsId();
		this.level = cons.getLevel();
		this.description = cons.getDescription();
	}
}
