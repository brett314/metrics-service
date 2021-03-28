package org.demo.rest.exception;

public interface JsonMessageWrapper
{
    default String wrapMessage(String message)
    {
        return String.format("{\"message\":\"%s\"}", message);
    }
}
