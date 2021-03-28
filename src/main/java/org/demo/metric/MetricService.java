package org.demo.metric;

import org.demo.statistic.Statistic;

public interface MetricService
{
    void addMetric(String metricName, double value);
    void createMetric(String serviceName);
    double figureStatistic(String metricName, Statistic statistic);
}
