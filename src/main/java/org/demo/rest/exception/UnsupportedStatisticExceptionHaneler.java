package org.demo.rest.exception;

import org.demo.metric.exception.UnsupportedStatisticException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnsupportedStatisticExceptionHaneler implements ExceptionMapper<UnsupportedStatisticException>, JsonMessageWrapper
{
    @Override
    public Response toResponse(UnsupportedStatisticException e)
    {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), wrapMessage(e.getMessage())).build();
    }
}
