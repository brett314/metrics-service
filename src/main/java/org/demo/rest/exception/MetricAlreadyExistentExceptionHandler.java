package org.demo.rest.exception;

import org.demo.metric.exception.MetricAlreadyExistentException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MetricAlreadyExistentExceptionHandler implements ExceptionMapper<MetricAlreadyExistentException>, JsonMessageWrapper
{
    @Override
    public Response toResponse(MetricAlreadyExistentException e)
    {
        return Response.status(Response.Status.CONFLICT).entity(wrapMessage(e.getMessage())).build();
    }
}
