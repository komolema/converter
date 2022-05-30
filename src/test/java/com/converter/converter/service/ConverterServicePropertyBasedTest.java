package com.converter.converter.service;

import com.converter.converter.service.length.LengthImperialUnit;
import com.converter.converter.service.length.LengthMetricUnit;
import com.converter.converter.service.temperature.WeatherType;
import net.jqwik.api.*;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConverterServicePropertyBasedTest {
    private ConverterService converterService = new ConverterService();

    private BigDecimal imperialToMetricLengthConversionRate = BigDecimal.valueOf(0.3048);

    @Property
    public void conversionFromMeterToFeet(@ForAll("positiveNumberMeters") BigDecimal value) throws Exception {
        BigDecimal result = converterService.convertToImperialLength(value, LengthMetricUnit.METER, LengthImperialUnit.FEET);
        Assertions.assertThat(result).isEqualTo(value.multiply(BigDecimal.valueOf(3.281)).setScale(2, RoundingMode.HALF_UP));
    }

    @Property
    public void conversionFromMeterToInches(@ForAll("positiveNumberMeters") BigDecimal value) throws Exception {
        BigDecimal result = converterService.convertToImperialLength(value, LengthMetricUnit.METER, LengthImperialUnit.INCHES);
        Assertions.assertThat(result).isEqualTo(value.multiply(BigDecimal.valueOf(3.281 * 12)).setScale(2, RoundingMode.HALF_UP));
    }

    @Property
    public void conversionFromMeterToYards(@ForAll("positiveNumberMeters") BigDecimal value) throws Exception {
        BigDecimal result = converterService.convertToImperialLength(value, LengthMetricUnit.METER, LengthImperialUnit.YARDS);
        Assertions.assertThat(result).isEqualTo(value.multiply(BigDecimal.valueOf(3.281 * 0.33333)).setScale(2, RoundingMode.HALF_UP));
    }

    @Property
    public void conversionFromMeterToMiles(@ForAll("positiveNumberMeters") BigDecimal value) throws Exception {
        BigDecimal result = converterService.convertToImperialLength(value, LengthMetricUnit.METER, LengthImperialUnit.MILES);
        Assertions.assertThat(result).isEqualTo(value.multiply(BigDecimal.valueOf(3.281 * 0.0001894)).setScale(2, RoundingMode.HALF_UP));
    }

    @Property
    public void conversionFromFeetToMilliMeter(@ForAll("positiveNumberMeters") BigDecimal value) throws Exception {
        BigDecimal result = converterService.convertToMetricLength(value, LengthImperialUnit.FEET, LengthMetricUnit.MILLIMETERS);
        Assertions.assertThat(result).isEqualTo(value.multiply(
                imperialToMetricLengthConversionRate.divide(BigDecimal.valueOf(10)))
                .setScale(2, RoundingMode.HALF_UP));
    }

    @Property
    public void conversionFromFeetToCentimeter(@ForAll("positiveNumberMeters") BigDecimal value) throws Exception {
        BigDecimal result = converterService.convertToMetricLength(value, LengthImperialUnit.FEET, LengthMetricUnit.CENTIMETERS);
        Assertions.assertThat(result).isEqualTo(value.multiply(
                imperialToMetricLengthConversionRate.divide(BigDecimal.valueOf(100)))
                .setScale(2, RoundingMode.HALF_UP));
    }

    @Property
    public void conversionFromFeetToMeter(@ForAll("positiveNumberMeters") BigDecimal value) throws Exception {
        BigDecimal result = converterService.convertToMetricLength(value, LengthImperialUnit.FEET, LengthMetricUnit.METER);
        Assertions.assertThat(result).isEqualTo(
                value.multiply(imperialToMetricLengthConversionRate).setScale(2, RoundingMode.HALF_UP));
    }

    @Property
    public void conversionFromFeetToKiltometer(@ForAll("positiveNumberMeters") BigDecimal value) throws Exception {
        BigDecimal result = converterService.convertToMetricLength(value, LengthImperialUnit.FEET, LengthMetricUnit.KILOMETER);
        Assertions.assertThat(result).isEqualTo(value.multiply(
                imperialToMetricLengthConversionRate
                        .multiply(BigDecimal.valueOf(1000))).setScale(2, RoundingMode.HALF_UP));
    }

    @Provide
    Arbitrary<BigDecimal> positiveNumberMeters() {
        return Arbitraries.bigDecimals().between(BigDecimal.valueOf(1.0), BigDecimal.valueOf(99999999));
    }

    /***Temperature property test ***/

    @Property
    public void conversionFromCelciusToFahrenheit(@ForAll("boundedTemperatureValues") BigDecimal value) {
        BigDecimal result = converterService.convertTemperature(value, WeatherType.FAHRENHEIT);

        Assertions.assertThat(result).isEqualTo(BigDecimal.valueOf((value.doubleValue() * 9)/5 + 32)
                .setScale(2, RoundingMode.HALF_UP));
    }

    @Property
    public void conversionFromFahrenheitCelcius(@ForAll("boundedTemperatureValues") BigDecimal value) {
        BigDecimal result = converterService.convertTemperature(value, WeatherType.CELCIUS);

        Assertions.assertThat(result).isEqualTo(BigDecimal.valueOf((value.doubleValue() - 32) * 5/9)
                .setScale(2, RoundingMode.HALF_UP));
    }

    @Provide
    Arbitrary<BigDecimal> boundedTemperatureValues() {
        return Arbitraries.bigDecimals().between(BigDecimal.valueOf(1.0), BigDecimal.valueOf(99999999));
    }
}
