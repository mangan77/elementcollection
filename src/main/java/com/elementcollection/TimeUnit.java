package com.elementcollection;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * <br> User: Mangan <br> Date: 03/12/13
 */
@ParametersAreNonnullByDefault
public abstract class TimeUnit {

    public abstract int inMilliseconds();

    public static TimeUnit secs(final int seconds) {
        return new TimeUnit() {
            @Override
            public int inMilliseconds() {
                return seconds * 1000;
            }
        };
    }

    public static TimeUnit millis(final int milliseconds) {
        return new TimeUnit() {
            @Override
            public int inMilliseconds() {
                return milliseconds;
            }
        };
    }


}
