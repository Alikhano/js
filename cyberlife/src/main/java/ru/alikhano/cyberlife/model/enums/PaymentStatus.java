package ru.alikhano.cyberlife.model.enums;

public enum PaymentStatus {

    PAID("paid"),

    UNPAID("unpaid");

    private final String value;

    PaymentStatus(String value) { this.value = value; }

    public String getValue() { return value; }
}
