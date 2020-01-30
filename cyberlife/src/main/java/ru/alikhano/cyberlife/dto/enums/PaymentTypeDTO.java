package ru.alikhano.cyberlife.dto.enums;

public enum PaymentTypeDTO {

    CASH("cash"),

    CREDIT_CART("credit card");

    private final String value;

    PaymentTypeDTO(String value) { this.value = value; }

    public String getValue() { return value; }
}
