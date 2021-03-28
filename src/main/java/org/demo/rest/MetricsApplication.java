package org.demo.rest;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title="Metrics API",
                version = "1.0.1",
                contact = @Contact(
                        name = "Metrics API Support",
                        url = "http://exampleurl.com/contact",
                        email = "techsupport@example.com")
        )
)
public class MetricsApplication extends Application
{
}
