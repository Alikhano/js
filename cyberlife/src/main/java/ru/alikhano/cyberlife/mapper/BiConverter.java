package ru.alikhano.cyberlife.mapper;

public interface BiConverter<IN, OUT> {

    OUT forward(IN source);

    IN backward (OUT source);

}
