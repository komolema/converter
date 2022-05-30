package com.converter.converter.service;


import com.converter.converter.service.area.AreaImperialUnit;
import com.converter.converter.service.area.AreaMetricUnit;
import com.converter.converter.service.length.LengthImperialUnit;
import com.converter.converter.service.length.LengthMetricUnit;
import com.converter.converter.service.temperature.WeatherType;
import com.converter.converter.service.volume.VolumeImperialUnit;
import com.converter.converter.service.volume.VolumeMetricUnit;
import com.converter.converter.service.weight.WeightImperialUnit;
import com.converter.converter.service.weight.WeightMetricUnit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConverterServiceUnitTest {

    private ConverterService converterService = new ConverterService();

    @Test
    public void testConvertFromMeterToFeet() throws Exception {

        BigDecimal conversionValue = BigDecimal.valueOf(3.281);
        BigDecimal result = converterService.convertToImperialLength(BigDecimal.valueOf(1), LengthMetricUnit.METER, LengthImperialUnit.FEET);
        assertTrue(Objects.equals(result, BigDecimal.valueOf(3.281).setScale(2, RoundingMode.HALF_UP)));

        BigDecimal result2 = converterService.convertToImperialLength(BigDecimal.valueOf(3), LengthMetricUnit.METER, LengthImperialUnit.FEET);
        assertTrue(Objects.equals(result2, conversionValue.multiply(BigDecimal.valueOf(3)).setScale(2, RoundingMode.HALF_UP)));

    }

    @Test
    public void testConvertFromMeterToInches() throws Exception {

        BigDecimal result = converterService.convertToImperialLength(BigDecimal.valueOf(1), LengthMetricUnit.METER, LengthImperialUnit.INCHES);
        assertTrue(Objects.equals(result, BigDecimal.valueOf(3.281 * 12).setScale(2, RoundingMode.HALF_UP)));

        BigDecimal result2 = converterService.convertToImperialLength(BigDecimal.valueOf(3), LengthMetricUnit.METER, LengthImperialUnit.INCHES);
        assertTrue(Objects.equals(result2, BigDecimal.valueOf(3.281 * 12 * 3).setScale(2, RoundingMode.HALF_UP)));

    }

    @Test
    public void testConvertFromMeterToYards() throws Exception {

        BigDecimal result = converterService.convertToImperialLength(BigDecimal.valueOf(1), LengthMetricUnit.METER, LengthImperialUnit.YARDS);
        assertTrue(Objects.equals(result, BigDecimal.valueOf(3.281 * 0.33333).setScale(2, RoundingMode.HALF_UP)));

        BigDecimal result2 = converterService.convertToImperialLength(BigDecimal.valueOf(3), LengthMetricUnit.METER, LengthImperialUnit.YARDS);
        assertTrue(Objects.equals(result2, BigDecimal.valueOf(3.281 * 0.33333 * 3 ).setScale(2, RoundingMode.HALF_UP)));

    }

    @Test
    public void testConvertFromMeterToMiles() throws Exception {

        BigDecimal conversionValue = BigDecimal.valueOf(3.281);
        BigDecimal result = converterService.convertToImperialLength(BigDecimal.valueOf(1), LengthMetricUnit.METER, LengthImperialUnit.MILES);
        assertTrue(Objects.equals(result, BigDecimal.valueOf(3.281 * 0.0001894).setScale(2, RoundingMode.HALF_UP)));

        BigDecimal result2 = converterService.convertToImperialLength(BigDecimal.valueOf(3), LengthMetricUnit.METER, LengthImperialUnit.MILES);
        assertTrue(Objects.equals(result2, BigDecimal.valueOf(3.281 * 0.0001894 * 3).setScale(2, RoundingMode.HALF_UP)));

    }

    //cross pair tests
    @Test
    public void testConvertFromCentimeterToInches() throws Exception {
        BigDecimal result = converterService.convertToImperialLength(BigDecimal.valueOf(1), LengthMetricUnit.CENTIMETERS, LengthImperialUnit.INCHES);
        assertTrue(Objects.equals(result, BigDecimal.valueOf((3.281/100) * 12).setScale(2, RoundingMode.HALF_UP)));

        BigDecimal result2 = converterService.convertToImperialLength(BigDecimal.valueOf(3), LengthMetricUnit.CENTIMETERS, LengthImperialUnit.INCHES);
        assertTrue(Objects.equals(result2, BigDecimal.valueOf((3.281/100)  * 12 * 3).setScale(2, RoundingMode.HALF_UP)));
    }

    @Test
    public void testConvertFromInchesToMillimeter() throws Exception {
        BigDecimal result = converterService.convertToMetricLength(BigDecimal.valueOf(1), LengthImperialUnit.INCHES, LengthMetricUnit.MILLIMETERS);
        assertTrue(Objects.equals(result, BigDecimal.valueOf(0.3048 / 1000).setScale(2, RoundingMode.HALF_UP)));

        BigDecimal result2 = converterService.convertToMetricLength(BigDecimal.valueOf(3), LengthImperialUnit.INCHES, LengthMetricUnit.MILLIMETERS);
        assertTrue(Objects.equals(result2, BigDecimal.valueOf(0.3048  / 1000 * 3).setScale(2, RoundingMode.HALF_UP)));
    }

    /*** Temperature tests ***/
    @Test
    public void testConvertCelciusToFahrenheit() throws Exception {
        BigDecimal result = converterService.convertTemperature(BigDecimal.valueOf(1), WeatherType.FAHRENHEIT);

        assertTrue(result.setScale(2, RoundingMode.HALF_UP).equals(
                BigDecimal
                        .valueOf(33.7999)
                        .setScale(2, RoundingMode.HALF_UP))
        );
    }

    @Test
    public void testConvertFahrenheitToCelcius() throws Exception {
        BigDecimal result = converterService.convertTemperature(BigDecimal.valueOf(1), WeatherType.CELCIUS);

        assertTrue(result.setScale(2, RoundingMode.HALF_UP).equals(
                BigDecimal
                        .valueOf(-17.2222)
                        .setScale(2, RoundingMode.HALF_UP))
        );
    }

    /*** Weight tests ***/
    @Test
    public void testConvertGramToOunce() throws Exception {
        BigDecimal result = converterService.convertToImperialWeight(
                BigDecimal.valueOf(1),
                WeightMetricUnit.GRAM,
                WeightImperialUnit.OUNCE);

        assertTrue(result.equals(BigDecimal.valueOf(0.0352733686).setScale(2, RoundingMode.HALF_UP)));
    }

    @Test
    public void testConvertKilogramToOunce() throws Exception {
        BigDecimal result = converterService.convertToImperialWeight(
                BigDecimal.valueOf(1),
                WeightMetricUnit.KILOGRAM,
                WeightImperialUnit.OUNCE);

        assertTrue(result.equals(BigDecimal.valueOf(35.2733686).setScale(2, RoundingMode.HALF_UP)));
    }

    @Test
    public void testConvertGramToStone() throws Exception {
        BigDecimal result = converterService.convertToImperialWeight(
                BigDecimal.valueOf(1),
                WeightMetricUnit.GRAM,
                WeightImperialUnit.STONE);

        assertTrue(result.equals(BigDecimal.valueOf(0.000157473044 ).setScale(2, RoundingMode.HALF_UP)));
    }

    @Test
    public void testConvertStoneToGram() throws Exception {
        BigDecimal result = converterService.convertToMetricWeight(
                BigDecimal.valueOf(1),
                WeightImperialUnit.STONE,
                WeightMetricUnit.GRAM);

        assertTrue(result.equals(BigDecimal.valueOf(6350.29318 ).setScale(2, RoundingMode.HALF_UP)));
    }

    @Test
    public void testConvertStoneToKilogram() throws Exception {
        BigDecimal result = converterService.convertToMetricWeight(
                BigDecimal.valueOf(1),
                WeightImperialUnit.STONE,
                WeightMetricUnit.KILOGRAM);

        assertTrue(result.equals(BigDecimal.valueOf(6.35029318 ).setScale(2, RoundingMode.HALF_UP)));
    }

    /*** Area test ***/

    @Test
    public void testConvertSquareMeterToSquareFeet() throws Exception {
        BigDecimal result = converterService.convertToImperialArea(
                BigDecimal.valueOf(10),
                AreaMetricUnit.SQUARE_METER,
                AreaImperialUnit.SQUARE_FEET);
        assertTrue(result.equals(BigDecimal.valueOf(107.6390352776)));
    }

    @Test
    public void testConvertSquareFeetToSquareMeter() throws Exception {
        BigDecimal result = converterService.convertToMetricArea(
                BigDecimal.valueOf(1000),
                AreaImperialUnit.SQUARE_FEET,
                AreaMetricUnit.SQUARE_METER);

        assertTrue(result.equals(BigDecimal.valueOf(92.9030400000).setScale(10, RoundingMode.HALF_UP)));
    }

    /*** Volume tests ***/

    @Test
    public void testConvertCubicFeetToCubicMeter() throws Exception {
        BigDecimal result = converterService.convertToMetricVolume(
                BigDecimal.valueOf(100),
                VolumeImperialUnit.CUBIC_FEET,
                VolumeMetricUnit.CUBIC_METER);

        assertTrue(result.equals(BigDecimal.valueOf( 2.831685).setScale(5, RoundingMode.HALF_UP)));
    }

    @Test
    public void testConvertCubicInchToCubicCentimeter() throws Exception {
        BigDecimal result = converterService.convertToMetricVolume(
                BigDecimal.valueOf(100),
                VolumeImperialUnit.CUBIC_INCH,
                VolumeMetricUnit.CUBIC_CENTIMETER);

        assertTrue(result.equals(BigDecimal.valueOf( 1638.706).setScale(5, RoundingMode.HALF_UP)));
    }

    @Test
    public void testConvertCubicMeterToCubicInch() throws Exception {
        BigDecimal result = converterService.convertToImperialVolume(
                BigDecimal.valueOf(100),
                VolumeMetricUnit.CUBIC_METER,
                VolumeImperialUnit.CUBIC_INCH);

        assertTrue(result.equals(BigDecimal.valueOf( 6102375.9).setScale(5, RoundingMode.HALF_UP)));
    }


}
