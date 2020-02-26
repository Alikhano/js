package ru.alikhano.cyberlife.dto.enums;

public enum PaymentStatusDTO {

    PAID("paid"),

    UNPAID("unpaid");

    private final String value;

    PaymentStatusDTO(String value) { this.value = value; }

    public String getValue() { return value; }
}
