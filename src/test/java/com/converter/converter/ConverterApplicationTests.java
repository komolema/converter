package com.converter.converter;

import com.converter.converter.controller.ApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = ConverterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConverterApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testConvertWeatherCelciusToFahrenheit() {

        apiTest("/temperature?convertTo=METRIC&value=1", "-17.22");
    }

    @Test
    public void testConvertMeterToFeet() {

        apiTest("/length?convertTo=IMPERIAL&value=10", "32.81");
    }

    @Test
    public void testConvertKilogramToPound() {
        apiTest("/weight?convertTo=IMPERIAL&value=10", "22.05");
    }

    @Test
    public void testConvertPoundToKilogram() {
        apiTest("/weight?convertTo=METRIC&value=1", "0.45");
    }

    @Test
    public void testConvertGramToStone() {
        apiTest(
                "/weight?convertTo=IMPERIAL&value=10000&metricUnitType=GRAM&imperialUnitType=STONE",
                "1.57");
    }

    @Test
    public void testConvertCentimeterToFeet() {
        apiTest(
                "/length?convertTo=IMPERIAL&value=1000&metricUnitType=CENTIMETERS&imperialUnitType=FEET",
                "32.81");
    }

    @Test
    public void testConvertSquareMeterToSquareFeet() {
        apiTest(
                "/area?convertTo=IMPERIAL&value=100&metricUnitType=SQUARE_METER&imperialUnitType=SQUARE_FEET",
                "1076.3903527764");
    }

    @Test
    public void testConvertCubicMeterToCubicFeet() {
        apiTest(
                "/volume?convertTo=IMPERIAL&value=100&metricUnitType=CUBIC_METER&imperialUnitType=CUBIC_FEET",
                "3531.46625");
    }

    private void apiTest(String apiPath, String expectedResultStr) {
        String apiUrl = serverUrl(apiPath);
        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(apiUrl, ApiResponse.class);
        Assertions.assertTrue(response.getBody() != null);

        ApiResponse apiResponse = response.getBody();
        Assertions.assertTrue(apiResponse.getHeader() != null);
        Assertions.assertTrue(apiResponse.getBody() != null);
        Assertions.assertTrue(apiResponse.getError() == null);
        Assertions.assertTrue(apiResponse.getBody().getConvertedValue().toString().equals(expectedResultStr));
    }

    private String serverUrl(String apiPath){
        return "http://localhost:" + port + "/api/convert" + apiPath;
    }

}
