package org.demo;

import io.restassured.http.ContentType;
import org.demo.metric.SimpleValue;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

public interface MetricsITSuite
{

    default void givenMetricPosted(String metricName)
    {
        given()
                .when().post("/metrics/" + metricName)
                .then()
                .statusCode(201);
    }

    default void givenMetricAdded(String metricName, double metricValue)
    {
        SimpleValue simpleValue = new SimpleValue(metricValue);
        given().contentType(ContentType.JSON)
                .body(simpleValue)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post("/metrics/" + metricName + "/value")
                .then()
                .statusCode(204);
    }
}
