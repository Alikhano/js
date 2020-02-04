package ru.alikhano.cyberlife.dto.enums;

public enum OrderStatusDTO {

    AWAITS_DELIVERY("awaits delivery"),

    AWAITS_PICKUP("awaits pickup"),

    RECEIVED("received");

    private final String value;

    OrderStatusDTO(String value) { this.value = value; }

    public String getValue() { return value; }
}
