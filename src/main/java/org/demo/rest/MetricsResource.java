package org.demo.rest;

import org.demo.metric.MetricService;
import org.demo.metric.SimpleValue;
import org.demo.statistic.Statistic;
import org.demo.statistic.StatisticFactory;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/metrics")
@Tag(name = "Metrics", description = "Create, add, and get statistics")
public class MetricsResource
{
    @Inject MetricService metricService;
    @Inject StatisticFactory statisticFactory;
    @Context UriInfo uriInfo;


    @POST
    @Path("{name}")
    @Operation(summary = "Create a metric", description = "Creates a new metric to add values to")
    @APIResponse(responseCode = "201", description = "The metric is created and available to have values added.")
    @APIResponse(responseCode = "409", description = "The metric is already created. No change on server.")
    public Response metric(@PathParam("name") String name)
    {
        metricService.createMetric(name);

        return Response.created(
                uriInfo.getBaseUriBuilder().path("/metrics/" + name).build()
               ).build();
    }

    @POST
    @Path("/{name}/value")
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "204", description = "The value was added to the metric.  No content returned.")
    public void addValue(@PathParam("name") String name, SimpleValue metricValue)
    {
        double value = metricValue.getValue();
        metricService.addMetric(name, value);
    }

    @GET
    @Path("{metricName}/statistics/{statisticName}")
    @APIResponse(responseCode = "200", description = "The requested statistic is returned.")
    @APIResponse(responseCode = "404", description = "The requested resource is not found.  See message to determine which resource.")
    public SimpleValue get(@PathParam("metricName") String metricName, @PathParam("statisticName") StatisticType statisticName)
    {
        Statistic statistic = statisticFactory.getStatistic(statisticName.name());
        double statisticValue = metricService.figureStatistic(metricName, statistic);

        return new SimpleValue(statisticValue);
    }
}
