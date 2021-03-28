package org.demo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.demo.metric.SimpleValue;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class MetricStatisticsResourceIT implements MetricsITSuite
{

    @Test
    public void when_requesting_statistic_mean_then_it_returns_the_correct_mean()
    {
        givenMetricPosted("meanmetrics");
        givenMetricAdded("meanmetrics", 10.5);
        givenMetricAdded("meanmetrics", 20.75);
        givenMetricAdded("meanmetrics", 0.25);

        given()
                .when().get("/metrics/meanmetrics/statistics/mean")
                .then()
                .body("value", is(10.5F))
                .statusCode(200);
    }

    @Test
    public void when_requesting_statistic_median_on_odd_count_then_it_returns_the_correct_median()
    {
        givenMetricPosted("medianoddmetrics");
        givenMetricAdded("medianoddmetrics", 10.5);
        givenMetricAdded("medianoddmetrics", 23.75);
        givenMetricAdded("medianoddmetrics", 1.25);


        given()
                .when().get("/metrics/medianoddmetrics/statistics/median")
                .then()
                .body("value", is(10.5F))
                .statusCode(200);
    }

    @Test
    public void when_requesting_statistic_median_on_even_count_then_it_returns_the_correct_median()
    {
        givenMetricPosted("medianevenmetrics");
        givenMetricAdded("medianevenmetrics", 10.5);
        givenMetricAdded("medianevenmetrics", 23.75);
        givenMetricAdded("medianevenmetrics", 1.25);
        givenMetricAdded("medianevenmetrics", 12.5);


        given()
                .when().get("/metrics/medianevenmetrics/statistics/median")
                .then()
                .body("value", is(11.5F))
                .statusCode(200);
    }

    @Test
    public void when_requesting_statistic_mean_on_zeros_then_it_returns_the_correct_mean()
    {
        givenMetricPosted("meanzeros");
        givenMetricAdded("meanzeros", 0);
        givenMetricAdded("meanzeros", 0);
        givenMetricAdded("meanzeros", 0);


        given()
                .when().get("/metrics/meanzeros/statistics/mean")
                .then()
                .body("value", is(0F))
                .statusCode(200);
    }

    @Test
    public void when_requesting_statistic_min_then_it_returns_the_correct_min()
    {
        givenMetricPosted("minmetrics");
        givenMetricAdded("minmetrics", 10.5);
        givenMetricAdded("minmetrics", 20.75);
        givenMetricAdded("minmetrics", 0.25);


        given()
                .when().get("/metrics/minmetrics/statistics/min")
                .then()
                .body("value", is(0.25F))
                .statusCode(200);
    }

    @Test
    public void when_requesting_statistic_max_then_it_returns_the_correct_max()
    {
        givenMetricPosted("maxmetrics");
        givenMetricAdded("maxmetrics", 10.5);
        givenMetricAdded("maxmetrics", 20.75);
        givenMetricAdded("maxmetrics", 0.25);


        given()
                .when().get("/metrics/maxmetrics/statistics/max")
                .then()
                .body("value", is(20.75F))
                .statusCode(200);
    }

    @Test
    public void when_requesting_unknown_statistic_on_existing_metrics_then_it_returns_404()
    {
        givenMetricPosted("notvalidstatistic");
        givenMetricAdded("notvalidstatistic", 10.5);


        given()
                .when().get("/metrics/notvalidstatistic/statistics/unknown-statistic")
                .then()
                .statusCode(404);
    }

    @Test
    public void when_requesting_statistic_on_nonexisting_metrics_then_it_returns_404()
    {
        given()
                .when().get("/metrics/nonexistantmetricforstatistic/statistics/max")
                .then()
                .statusCode(404);
    }
}
