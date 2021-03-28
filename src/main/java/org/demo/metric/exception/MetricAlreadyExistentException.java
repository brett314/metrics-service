package org.demo.metric.exception;

public class MetricAlreadyExistentException extends RuntimeException
{
    private final String metricName;

    public MetricAlreadyExistentException(String metricName)
    {
        this.metricName = metricName;
    }

    @Override
    public String getMessage()
    {
        return "Metric already created: " + metricName;
    }
}
