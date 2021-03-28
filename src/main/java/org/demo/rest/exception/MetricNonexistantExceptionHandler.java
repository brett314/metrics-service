package org.demo.rest.exception;

import org.demo.metric.exception.MetricNonexistantException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MetricNonexistantExceptionHandler implements ExceptionMapper<MetricNonexistantException>, JsonMessageWrapper
{
    @Override
    public Response toResponse(MetricNonexistantException e)
    {
        return Response.status(Response.Status.NOT_FOUND).entity(wrapMessage(e.getMessage())).build();
    }
}
