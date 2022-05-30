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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ConverterService {

    /*** Length conversions ***/
    public BigDecimal convertToImperialLength(BigDecimal value,
                                              LengthMetricUnit fromMetricUnit,
                                              LengthImperialUnit toLengthImperialUnit) throws Exception {
        BigDecimal result = null;
        switch(fromMetricUnit) {
            case MILLIMETERS -> {
                result = metersToImperialUnit(convertFromMetersToFeet(value.divide(BigDecimal.valueOf(1000))), toLengthImperialUnit);
            }
            case CENTIMETERS -> {
                result = metersToImperialUnit(convertFromMetersToFeet(value.divide(BigDecimal.valueOf(100))), toLengthImperialUnit);
            }
            case METER -> {
                result = metersToImperialUnit(convertFromMetersToFeet(value), toLengthImperialUnit);
            }
            case KILOMETER -> {
                result = metersToImperialUnit(convertFromMetersToFeet(value.multiply(BigDecimal.valueOf(1000))), toLengthImperialUnit);
            }
            default -> throw new Exception("convertToImperialLength - Invalid metric unit selected:" + fromMetricUnit);
        }
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal metersToImperialUnit(BigDecimal valueInFeet,
                                            LengthImperialUnit toLengthImperialUnit) throws Exception {
        switch (toLengthImperialUnit){
            case INCHES -> {
                return valueInFeet.multiply(BigDecimal.valueOf(12));
            }
            case FEET -> {
                return valueInFeet;
            }
            case YARDS -> {
                return valueInFeet.multiply(BigDecimal.valueOf(0.33333));
            }
            case MILES -> {
                return valueInFeet.multiply(BigDecimal.valueOf(0.0001894));
            }
            default -> throw new Exception("metersToImperialUnit - Invalid meters to imperial unit:" + toLengthImperialUnit);
        }
    }

    private BigDecimal convertFromMetersToFeet(BigDecimal value){
        return value.multiply(BigDecimal.valueOf(3.281));
    }

    public BigDecimal convertToMetricLength(BigDecimal value,
                                            LengthImperialUnit fromLengthImperialUnit,
                                            LengthMetricUnit toMetricUnit) throws Exception {
        BigDecimal result = null;
        switch(fromLengthImperialUnit) {
            case INCHES -> {
                result = feetToMetricUnit(convertFromFeetToMeters(value.divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP)), toMetricUnit);
            }
            case FEET -> {
                result = feetToMetricUnit(convertFromFeetToMeters(value), toMetricUnit);
            }
            case YARDS -> {
                result = feetToMetricUnit(convertFromFeetToMeters(value.divide(BigDecimal.valueOf(0.33333), RoundingMode.HALF_UP)), toMetricUnit);
            }
            case MILES -> {
                result = feetToMetricUnit(convertFromFeetToMeters(value.divide(BigDecimal.valueOf(0.0001894), RoundingMode.HALF_UP)), toMetricUnit);
            }
            default -> throw new Exception("convertToMetricLength - Invalid imperial unit selected:" + fromLengthImperialUnit);
        }
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal feetToMetricUnit(BigDecimal valueInMeters, LengthMetricUnit toMetricUnit) throws Exception {
        switch (toMetricUnit){
            case MILLIMETERS -> {
                return valueInMeters.divide(BigDecimal.valueOf(10), RoundingMode.HALF_UP);
            }
            case CENTIMETERS -> {
                return valueInMeters.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
            }
            case METER -> {
                return valueInMeters;
            }
            case KILOMETER -> {
                return valueInMeters.multiply(BigDecimal.valueOf(1000));
            }
            default -> throw new Exception("feetToMetricUnit - Invalid meters to metric unit:" + toMetricUnit);
        }
    }

    private BigDecimal convertFromFeetToMeters(BigDecimal value){
        return value.multiply(BigDecimal.valueOf(0.3048));
    }

    /*** Temperature conversion ***/

    public BigDecimal convertTemperature(BigDecimal value, WeatherType metricToConvertTo) {
        BigDecimal result = null;
        if(metricToConvertTo == WeatherType.CELCIUS) {
            result = BigDecimal.valueOf((value.doubleValue() - 32) * 5/9);
        } else {
            result = BigDecimal.valueOf((value.doubleValue() * 9)/5 + 32);

        }
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    /*** Weight conversion ***/
    public BigDecimal convertToMetricWeight(BigDecimal value,
                                            WeightImperialUnit fromWeightUnit,
                                            WeightMetricUnit toWeightUnit) throws Exception {
        BigDecimal result = null;
        switch(fromWeightUnit) {
            case OUNCE -> {
                result = ounceToMetricUnit(convertFromOunceToGram(value), toWeightUnit);
            }
            case POUND -> {
                result = ounceToMetricUnit(convertFromOunceToGram(
                        value.multiply(BigDecimal.valueOf(15.9997309))),
                        toWeightUnit);
            }
            case STONE -> {
                result = ounceToMetricUnit(convertFromOunceToGram(
                        value.multiply(BigDecimal.valueOf(223.996232))),
                        toWeightUnit);
            }
            default -> throw new Exception("convertToMetricWeight - Invalid imperial unit selected:" + fromWeightUnit);
        }
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal ounceToMetricUnit(BigDecimal valueInGrams , WeightMetricUnit toWeightUnit) throws Exception {
        switch (toWeightUnit){
            case GRAM -> {
                return valueInGrams;
            }
            case KILOGRAM -> {
                return valueInGrams.divide(BigDecimal.valueOf(1000), RoundingMode.HALF_UP);
            }
            default -> throw new Exception("ounceToMetricUnit - Invalid meters to metric unit:" + toWeightUnit);
        }
    }

    private BigDecimal convertFromOunceToGram(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(28.35));
    }

    public BigDecimal convertToImperialWeight(BigDecimal value,
                                            WeightMetricUnit fromWeightUnit,
                                            WeightImperialUnit toWeightUnit) throws Exception {
        BigDecimal result = null;
        switch(fromWeightUnit) {
            case GRAM -> {
                result = gramToImperialUnit(convertFromGramToOunce(value), toWeightUnit);
            }
            case KILOGRAM -> {
                result = gramToImperialUnit(convertFromGramToOunce(
                                value).multiply(BigDecimal.valueOf(1000)),
                        toWeightUnit);
            }
            default -> throw new Exception("convertToMetricWeight - Invalid imperial unit selected:" + fromWeightUnit);
        }
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal gramToImperialUnit(BigDecimal valueInOunces , WeightImperialUnit toWeightUnit) throws Exception {
        switch (toWeightUnit){
            case OUNCE -> {
                return valueInOunces;
            }
            case POUND -> {
                return valueInOunces.multiply(BigDecimal.valueOf(0.0625010513));
            }
            case STONE -> {
                return valueInOunces.multiply(BigDecimal.valueOf(0.00446436081));
            }
            default -> throw new Exception("ounceToMetricUnit - Invalid ounces to metric unit:" + toWeightUnit);
        }
    }

    private BigDecimal convertFromGramToOunce(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(0.0352733686));
    }

    /*** Area conversion ***/
    public BigDecimal convertToMetricArea(BigDecimal value,
                                          AreaImperialUnit fromAreaUnit,
                                          AreaMetricUnit toAreaUnit) throws Exception {
        BigDecimal result = null;
        switch(fromAreaUnit) {
            case SQUARE_INCH -> {
                result = squareInchToMetricUnit(convertFromSquareInchToSquareMeter(value), toAreaUnit);
            }
            case SQUARE_FEET -> {
                result = squareInchToMetricUnit(convertFromSquareInchToSquareMeter(
                                value.multiply(BigDecimal.valueOf(144))),
                        toAreaUnit);
            }
            case SQUARE_MILE -> {
                result = squareInchToMetricUnit(convertFromSquareInchToSquareMeter(
                                value.multiply(BigDecimal.valueOf(4014489430l))),
                        toAreaUnit);
            }
            default -> throw new Exception("convertToMetricArea - Invalid metric unit selected:" + fromAreaUnit);
        }
        return result.setScale(10, RoundingMode.HALF_UP);
    }

    private BigDecimal squareInchToMetricUnit(BigDecimal valueInSquareMeter, AreaMetricUnit toAreaUnit) throws Exception {
        switch (toAreaUnit){
            case SQUARE_METER -> {
                return valueInSquareMeter;
            }
            case SQUARE_KILOMETER -> {
                return valueInSquareMeter.multiply(BigDecimal.valueOf(1e-6));
            }
            default -> throw new Exception("squareInchToMetricUnit - Invalid square meter to imperial unit:" + toAreaUnit);
        }
    }

    private BigDecimal convertFromSquareInchToSquareMeter(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(0.00064516));
    }

    public BigDecimal convertToImperialArea(BigDecimal value,
                                            AreaMetricUnit fromAreaUnit,
                                            AreaImperialUnit toAreaUnit) throws Exception {
        BigDecimal result = null;
        switch(fromAreaUnit) {
            case SQUARE_METER -> {
                result = squareMeterToImperialUnit(convertFromSquareMeterToSquareInch(value), toAreaUnit);
            }
            case SQUARE_KILOMETER -> {
                result = squareMeterToImperialUnit(convertFromSquareMeterToSquareInch(
                                value).multiply(BigDecimal.valueOf(1e-6)),
                        toAreaUnit);
            }
            default -> throw new Exception("convertToMetricArea - Invalid metric unit selected:" + fromAreaUnit);
        }
        return result.setScale(10, RoundingMode.HALF_UP);
    }

    private BigDecimal squareMeterToImperialUnit(BigDecimal valueInSquareInch, AreaImperialUnit toAreaUnit) throws Exception {
        switch (toAreaUnit){
            case SQUARE_INCH -> {
                return valueInSquareInch;
            }
            case SQUARE_FEET -> {
                return valueInSquareInch.multiply(BigDecimal.valueOf(0.00694444));
            }
            case SQUARE_MILE -> {
                return valueInSquareInch.multiply(BigDecimal.valueOf(2.491e-10));
            }
            default -> throw new Exception("squareMeterToImperialUnit - Invalid square meter to imperial unit:" + toAreaUnit);
        }
    }

    private BigDecimal convertFromSquareMeterToSquareInch(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(1550.0031));
    }

    /*** Volume conversion ***/
    public BigDecimal convertToMetricVolume(BigDecimal value,
                                          VolumeImperialUnit fromAreaUnit,
                                          VolumeMetricUnit toAreaUnit) throws Exception {
        BigDecimal result = null;
        switch(fromAreaUnit) {
            case CUBIC_INCH -> {
                result = cubicInchToMetricUnit(convertFromCubicInchToCubicCentimeter(value), toAreaUnit);
            }
            case CUBIC_FEET -> {
                result = cubicInchToMetricUnit(convertFromCubicInchToCubicCentimeter(
                                value.multiply(BigDecimal.valueOf(1728.00063 ))),
                        toAreaUnit);
            }
            default -> throw new Exception("convertToMetricVolume - Invalid metric unit selected:" + fromAreaUnit);
        }
        return result.setScale(5, RoundingMode.HALF_UP);
    }

    private BigDecimal cubicInchToMetricUnit(BigDecimal valueInCubicCentimeter, VolumeMetricUnit toAreaUnit) throws Exception {
        switch (toAreaUnit){
            case CUBIC_CENTIMETER -> {
                return valueInCubicCentimeter;
            }
            case CUBIC_METER -> {
                return valueInCubicCentimeter.divide(BigDecimal.valueOf(1000000), RoundingMode.HALF_UP);
            }
            default -> throw new Exception("squareInchToMetricUnit - Invalid square meter to imperial unit:" + toAreaUnit);
        }
    }

    private BigDecimal convertFromCubicInchToCubicCentimeter(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(16.38706 ));
    }

    public BigDecimal convertToImperialVolume(BigDecimal value,
                                            VolumeMetricUnit fromVolumeUnit,
                                            VolumeImperialUnit toVolumeUnit) throws Exception {
        BigDecimal result = null;
        switch(fromVolumeUnit) {
            case CUBIC_CENTIMETER -> {
                result = cubicCentimeterToImperialUnit(convertFromCubicCentimeterToCubicInch(value), toVolumeUnit);
            }
            case CUBIC_METER -> {
                result = cubicCentimeterToImperialUnit(convertFromCubicCentimeterToCubicInch(
                                value).multiply(BigDecimal.valueOf(1000000)),
                        toVolumeUnit);
            }
            default -> throw new Exception("convertToMetricArea - Invalid metric unit selected:" + fromVolumeUnit);
        }
        return result.setScale(5, RoundingMode.HALF_UP);
    }

    private BigDecimal cubicCentimeterToImperialUnit(BigDecimal valueInCubicInch, VolumeImperialUnit toVolumeUnit) throws Exception {
        switch (toVolumeUnit){
            case CUBIC_INCH -> {
                return valueInCubicInch;
            }
            case CUBIC_FEET -> {
                return valueInCubicInch.divide(BigDecimal.valueOf(1728.00063), RoundingMode.HALF_UP);
            }
            default -> throw new Exception("cubicMeterToImperialUnit - Invalid cubic meter to imperial unit:" + toVolumeUnit);
        }
    }


    private BigDecimal convertFromCubicCentimeterToCubicInch(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf( 0.061023759));
    }
}
