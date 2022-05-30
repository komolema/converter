package com.converter.converter.controller;

public class ApiBody<V, F, T> {
    private V convertedValue;
    private F fromConversion;
    private T toConversion;

    public ApiBody(V value, F fromConversion, T toConversion){
        this.convertedValue = value;
        this.fromConversion = fromConversion;
        this.toConversion = toConversion;
    }

    public V getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(V convertedValue) {
        this.convertedValue = convertedValue;
    }

    public F getFromConversion() {
        return fromConversion;
    }

    public void setFromConversion(F fromConversion) {
        this.fromConversion = fromConversion;
    }

    public T getToConversion() {
        return toConversion;
    }

    public void setToConversion(T toConversion) {
        this.toConversion = toConversion;
    }
}
