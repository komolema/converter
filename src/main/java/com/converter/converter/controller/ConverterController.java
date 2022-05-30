package com.converter.converter.controller;

import com.converter.converter.service.ConverterService;
import com.converter.converter.service.area.AreaImperialUnit;
import com.converter.converter.service.area.AreaMetricUnit;
import com.converter.converter.service.length.LengthImperialUnit;
import com.converter.converter.service.length.LengthMetricUnit;
import com.converter.converter.service.temperature.WeatherType;
import com.converter.converter.service.volume.VolumeImperialUnit;
import com.converter.converter.service.volume.VolumeMetricUnit;
import com.converter.converter.service.weight.WeightImperialUnit;
import com.converter.converter.service.weight.WeightMetricUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/convert")
public class ConverterController {

    @Autowired
    private ConverterService converterService;

    @GetMapping(path = "/length", produces= MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse lengthConversion(@RequestParam("convertTo") ConverterType convertTo,
                                        @RequestParam("value") BigDecimal value,
                                        @RequestParam(value = "metricUnitType", required = false) LengthMetricUnit metricUnitType,
                                        @RequestParam(value = "imperialUnitType", required = false) LengthImperialUnit imperialUnitType) {

        if(metricUnitType == null){
            metricUnitType = LengthMetricUnit.METER;
        }

        if(imperialUnitType == null){
            imperialUnitType = LengthImperialUnit.FEET;
        }

        ApiHeader header = null;
        ApiBody body = null;
        ApiError error = null;
        ApiResponse response;
        try {
            if (convertTo == ConverterType.IMPERIAL) {
                header = new ApiHeader(ConverterType.METRIC, convertTo);
                BigDecimal result = converterService.convertToImperialLength(value, metricUnitType, imperialUnitType);
                body = new ApiBody<>(result, metricUnitType, imperialUnitType);
            } else {
                header = new ApiHeader(ConverterType.IMPERIAL, convertTo);
                BigDecimal result = converterService.convertToMetricLength(value, imperialUnitType, metricUnitType);
                body = new ApiBody<>(result, imperialUnitType, metricUnitType);
            }
        } catch(Exception e){
            error = new ApiError(e.getMessage());
        } finally {
            response =  new ApiResponse(header, body, error);
        }

        return response;
    }

    @GetMapping(path = "/temperature", produces= MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse temperatureConversion(@RequestParam("convertTo") ConverterType convertTo,
                                             @RequestParam("value") BigDecimal value){
        ApiHeader header = null;
        ApiBody body = null;
        ApiError error = null;
        ApiResponse response;
        try {
            if (convertTo == ConverterType.IMPERIAL) {
                header = new ApiHeader(ConverterType.METRIC, convertTo);
                BigDecimal result = converterService.convertTemperature(value, WeatherType.FAHRENHEIT);
                body = new ApiBody<>(result, WeatherType.CELCIUS, WeatherType.FAHRENHEIT);
            } else {
                header = new ApiHeader(ConverterType.IMPERIAL, convertTo);
                BigDecimal result = converterService.convertTemperature(value, WeatherType.CELCIUS);
                body = new ApiBody<>(result, WeatherType.FAHRENHEIT, WeatherType.CELCIUS);
            }
        } catch(Exception e){
            error = new ApiError(e.getMessage());
        } finally {
            response =  new ApiResponse(header, body, error);
        }

        return response;
    }

    @GetMapping(path = "/weight", produces= MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse lengthConversion(@RequestParam("convertTo") ConverterType convertTo,
                                        @RequestParam("value") BigDecimal value,
                                        @RequestParam(value = "metricUnitType", required = false) WeightMetricUnit metricUnitType,
                                        @RequestParam(value = "imperialUnitType", required = false) WeightImperialUnit imperialUnitType) {

        if(metricUnitType == null){
            metricUnitType = WeightMetricUnit.KILOGRAM;
        }

        if(imperialUnitType == null){
            imperialUnitType = WeightImperialUnit.POUND;
        }

        ApiHeader header = null;
        ApiBody body = null;
        ApiError error = null;
        ApiResponse response;
        try {
            if (convertTo == ConverterType.IMPERIAL) {
                header = new ApiHeader(ConverterType.METRIC, convertTo);
                BigDecimal result = converterService.convertToImperialWeight(value, metricUnitType, imperialUnitType);
                body = new ApiBody<>(result, metricUnitType, imperialUnitType);
            } else {
                header = new ApiHeader(ConverterType.IMPERIAL, convertTo);
                BigDecimal result = converterService.convertToMetricWeight(value, imperialUnitType, metricUnitType);
                body = new ApiBody<>(result, imperialUnitType, metricUnitType);
            }
        } catch(Exception e){
            error = new ApiError(e.getMessage());
        } finally {
            response =  new ApiResponse(header, body, error);
        }

        return response;
    }

    @GetMapping(path = "/area", produces= MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse lengthConversion(@RequestParam("convertTo") ConverterType convertTo,
                                        @RequestParam("value") BigDecimal value,
                                        @RequestParam(value = "metricUnitType", required = false) AreaMetricUnit metricUnitType,
                                        @RequestParam(value = "imperialUnitType", required = false) AreaImperialUnit imperialUnitType) {

        if(metricUnitType == null){
            metricUnitType = AreaMetricUnit.SQUARE_METER;
        }

        if(imperialUnitType == null){
            imperialUnitType = AreaImperialUnit.SQUARE_FEET;
        }

        ApiHeader header = null;
        ApiBody body = null;
        ApiError error = null;
        ApiResponse response;
        try {
            if (convertTo == ConverterType.IMPERIAL) {
                header = new ApiHeader(ConverterType.METRIC, convertTo);
                BigDecimal result = converterService.convertToImperialArea(value, metricUnitType, imperialUnitType);
                body = new ApiBody<>(result, metricUnitType, imperialUnitType);
            } else {
                header = new ApiHeader(ConverterType.IMPERIAL, convertTo);
                BigDecimal result = converterService.convertToMetricArea(value, imperialUnitType, metricUnitType);
                body = new ApiBody<>(result, imperialUnitType, metricUnitType);
            }
        } catch(Exception e){
            error = new ApiError(e.getMessage());
        } finally {
            response =  new ApiResponse(header, body, error);
        }

        return response;
    }

    @GetMapping(path = "/volume", produces= MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse lengthConversion(@RequestParam("convertTo") ConverterType convertTo,
                                        @RequestParam("value") BigDecimal value,
                                        @RequestParam(value = "metricUnitType", required = false) VolumeMetricUnit metricUnitType,
                                        @RequestParam(value = "imperialUnitType", required = false) VolumeImperialUnit imperialUnitType) {

        if(metricUnitType == null){
            metricUnitType = VolumeMetricUnit.CUBIC_CENTIMETER;
        }

        if(imperialUnitType == null){
            imperialUnitType = VolumeImperialUnit.CUBIC_INCH;
        }

        ApiHeader header = null;
        ApiBody body = null;
        ApiError error = null;
        ApiResponse response;
        try {
            if (convertTo == ConverterType.IMPERIAL) {
                header = new ApiHeader(ConverterType.METRIC, convertTo);
                BigDecimal result = converterService.convertToImperialVolume(value, metricUnitType, imperialUnitType);
                body = new ApiBody<>(result, metricUnitType, imperialUnitType);
            } else {
                header = new ApiHeader(ConverterType.IMPERIAL, convertTo);
                BigDecimal result = converterService.convertToMetricVolume(value, imperialUnitType, metricUnitType);
                body = new ApiBody<>(result, imperialUnitType, metricUnitType);
            }
        } catch(Exception e){
            error = new ApiError(e.getMessage());
        } finally {
            response =  new ApiResponse(header, body, error);
        }

        return response;
    }


}
