package com.elementcollection.type;

import com.elementcollection.api.TimeOut;
import com.elementcollection.api.TimeUnit;

/**
 * <br> User: Mangan <br> Date: 12/05/15
 */
public class TimeOutUsingCurrentSystemTime implements TimeOut {

    private final long endTime;

    public TimeOutUsingCurrentSystemTime(TimeUnit delay) {
        endTime = calculateEndTime(delay);
    }

    private static long calculateEndTime(TimeUnit delay) {
        return System.currentTimeMillis() + delay.inMilliseconds();
    }

    @Override
    public boolean notReached() {
        return endTime - System.currentTimeMillis() > 0;
    }

}
