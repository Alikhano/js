package ru.alikhano.cyberlife.DTO;

import ru.alikhano.cyberlife.model.Consciousness;

public class ConsDTO {
	
	private int consId;
	private String level;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getConsId() {
		return consId;
	}
	public void setConsId(int consId) {
		this.consId = consId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public ConsDTO() {
		
	}
	
	public ConsDTO(Consciousness cons) {
		this.consId = cons.getConsId();
		this.level = cons.getLevel();
		this.description = cons.getDescription();
	}
	

}
