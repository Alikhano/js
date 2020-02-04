package ru.alikhano.cyberlife.model.enums;

public enum PaymentType {

    CASH("cash"),

    CREDIT_CART("credit card");

    private final String value;

    PaymentType(String value) { this.value = value; }

    public String getValue() { return value; }

}
