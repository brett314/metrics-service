package org.demo.metric;

import org.demo.metric.exception.MetricAlreadyExistentException;
import org.demo.metric.exception.MetricNonexistantException;
import org.demo.statistic.Statistic;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class BasicMetricService implements MetricService
{
    private final ConcurrentHashMap<String, List<Double>> metrics;

    BasicMetricService()
    {
        metrics = new ConcurrentHashMap<>();
    }

    @Override
    public void addMetric(String metricName, double value)
    {
        if (!metrics.containsKey(metricName))
        {
            throw new MetricNonexistantException(metricName);
        }

        List<Double> values = metrics.get(metricName);
        synchronized (values)
        {
            values.add(value);
        }
    }

    @Override
    public void createMetric(String metricName)
    {
        if (metrics.containsKey(metricName))
        {
            throw new MetricAlreadyExistentException(metricName);
        }

        metrics.put(metricName, Collections.synchronizedList(new ArrayList<>()));
    }

    @Override
    public double figureStatistic(String metricName, Statistic statistic)
    {
        List<Double> values = metrics.get(metricName);
        if (values == null)
        {
            throw new MetricNonexistantException(metricName);
        }

        synchronized (values)
        {
            return statistic.figure(values);
        }
    }
}
