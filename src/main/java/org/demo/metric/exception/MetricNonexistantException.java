package org.demo.metric.exception;

public class MetricNonexistantException extends RuntimeException
{
    private final String metricName;

    public MetricNonexistantException(String metricName)
    {
        this.metricName = metricName;
    }

    @Override
    public String getMessage()
    {
        return "Metric not found: " + metricName;
    }
}
