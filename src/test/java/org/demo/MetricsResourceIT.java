package org.demo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.demo.metric.SimpleValue;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class MetricsResourceIT implements MetricsITSuite
{

    @Test
    public void when_successfully_creating_new_initial_metric_then_it_returns_200()
    {
        given()
                .when().post("/metrics/createnew")
                .then()
                .statusCode(201);
    }

    @Test
    public void when_positive_metric_successfully_added_then_it_returns_204()
    {
        givenMetricPosted("successaddmetric");


        SimpleValue metricValue = new SimpleValue(123.456f);
        given().contentType(ContentType.JSON)
                .body(metricValue)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post("/metrics/successaddmetric/value")
                .then()
                .statusCode(204);
    }

    @Test
    public void when_negative_metric_successfully_added_then_it_returns_204()
    {
        givenMetricPosted("negativemetric");

        SimpleValue metricValue = new SimpleValue(-123.456f);
        given().contentType(ContentType.JSON)
                .body(metricValue)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post("/metrics/negativemetric/value")
                .then()
                .statusCode(204);
    }

    @Test
    public void when_adding_metric_to_nonexistant_then_throw_404()
    {
        SimpleValue metricValue = new SimpleValue(123.456f);
        given().contentType(ContentType.JSON)
                .body(metricValue)
                .when().post("/metrics/nonexistantadd/value")
                .then()
                .statusCode(404);
    }

    @Test
    public void when_posting_already_created_metric_then_return_409()
    {
        givenMetricPosted("duplicateattempt");


        given()
                .when().post("/metrics/duplicateattempt")
                .then()
                .statusCode(409);
    }

    @Test
    public void when_adding_multiple_metrics_then_it_processes_all_with_204()
    {
        givenMetricPosted("multiplegoodmetrics");


        SimpleValue metricValue = new SimpleValue(123.456f);
        given().contentType(ContentType.JSON)
                .body(metricValue)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post("/metrics/multiplegoodmetrics/value")
                .then()
                .statusCode(204);
        given().contentType(ContentType.JSON)
                .body(metricValue)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post("/metrics/multiplegoodmetrics/value")
                .then()
                .statusCode(204);
        given().contentType(ContentType.JSON)
                .body(metricValue)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post("/metrics/multiplegoodmetrics/value")
                .then()
                .statusCode(204);
    }

    @Test
    public void when_adding_invalid_metric_value_then_it_returns_400()
    {
        givenMetricPosted("badmetricvalue");


        given().contentType(ContentType.JSON)
                .body("{\"value\":3,3,3")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post("/metrics/badmetricvalue/value")
                .then()
                .statusCode(400);
    }

    @Test
    public void when_adding_blank_metric_value_then_it_returns_400()
    {
        givenMetricPosted("badblankmetricvalue");


        given().contentType(ContentType.JSON)
                .body("{\"value\":")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post("/metrics/badblankmetricvalue/value")
                .then()
                .statusCode(400);
    }

    @Test
    public void when_adding_null_metric_value_then_it_returns_400()
    {
        givenMetricPosted("badnullmetricvalue");


        given().contentType(ContentType.JSON)
                .body("{\"value\": null")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post("/metrics/badnullmetricvalue/value")
                .then()
                .statusCode(400);
    }

    @Test
    // If delete is ever allowed, ensure proper synchronization to guard against a deletes
    public void when_delete_method_on_metrics_then_return_405()
    {

        given()
                .when().delete("/metrics/anything")
                .then()
                .statusCode(405);
    }
}