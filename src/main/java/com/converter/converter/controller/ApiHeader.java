package com.converter.converter.controller;

public class ApiHeader {
    private ConverterType conversionFrom;
    private ConverterType conversionTo;

    public ApiHeader(ConverterType conversionFrom, ConverterType conversionTo){
        this.conversionFrom = conversionFrom;
        this.conversionTo = conversionTo;
    }

    public ConverterType getConversionFrom() {
        return conversionFrom;
    }

    public void setConversionFrom(ConverterType conversionFrom) {
        this.conversionFrom = conversionFrom;
    }

    public ConverterType getConversionTo() {
        return conversionTo;
    }

    public void setConversionTo(ConverterType conversionTo) {
        this.conversionTo = conversionTo;
    }
}
