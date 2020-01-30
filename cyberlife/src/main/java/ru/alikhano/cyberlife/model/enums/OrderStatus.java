package ru.alikhano.cyberlife.model.enums;

public enum OrderStatus {

    AWAITS_DELIVERY("awaits delivery"),

    AWAITS_PICKUP("awaits pickup"),

    RECEIVED("received");

    private final String value;

    OrderStatus(String value) { this.value = value; }

    public String getValue() { return value; }
}
