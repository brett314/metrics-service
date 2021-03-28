package org.demo.statistic;


import org.demo.metric.exception.UnsupportedStatisticException;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

@ApplicationScoped
public class StatisticFactory
{
    public Statistic getStatistic(String statisticName)
    {
        switch (statisticName)
        {
            case "mean":
                return emptySetSafeStatistic(values ->
                        values.stream()
                        .mapToDouble(Double::doubleValue)
                        .summaryStatistics()
                        .getAverage());

            case "min":
                return emptySetSafeStatistic(values ->
                        values.stream()
                        .mapToDouble(Double::doubleValue)
                        .summaryStatistics()
                        .getMin());

            case "max":
                return emptySetSafeStatistic(values ->
                        values.stream()
                        .mapToDouble(Double::doubleValue)
                        .summaryStatistics()
                        .getMax());

            case "median":
                return emptySetSafeStatistic(values ->
                {
                    DoubleStream sortedValues = values.stream()
                            .mapToDouble(Double::doubleValue)
                            .sorted();
                    double median = values.size() %2 == 0?
                            sortedValues.skip(values.size()/2-1).limit(2).average().getAsDouble():
                            sortedValues.skip(values.size()/2).findFirst().getAsDouble();

                    return median;
                });

            default:
                throw new UnsupportedStatisticException(statisticName);
        }
    }

    private Statistic emptySetSafeStatistic(Statistic internalStatistic)
    {
        return values ->
        {
            if (values.isEmpty())
            {
                return Double.NaN;
            }
            return internalStatistic.figure(values);
        };
    }
}
