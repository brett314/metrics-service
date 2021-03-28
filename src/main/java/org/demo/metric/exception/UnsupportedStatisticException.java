package org.demo.metric.exception;

public class UnsupportedStatisticException extends UnsupportedOperationException
{
    private final String unsupportedStatisticName;

    public UnsupportedStatisticException(String unsupportedStatisticName)
    {
        this.unsupportedStatisticName = unsupportedStatisticName;
    }

    @Override
    public String getMessage()
    {
        return "Statistic not supported: " + unsupportedStatisticName;
    }
}
