package com.elementcollection.type;

import javax.annotation.Nonnull;

public class TimeUnits {

    public TimeUnits() {
    }

    /**
     * Creates a {@code TimeUnit} object from seconds.
     *
     * @param seconds Seconds
     * @return New {@code TimeUnit}
     */
    @Nonnull
    public static TimeUnit secs(final int seconds) {
        return new TimeUnit() {
            @Override
            public int inMilliseconds() {
                return seconds * 1000;
            }
        };
    }

    /**
     * Creates a {@code TimeUnit} object from milliseconds.
     *
     * @param milliseconds Milliseconds
     * @return New {@code TimeUnit}
     */
    @Nonnull
    public static TimeUnit millis(final int milliseconds) {
        return new TimeUnit() {
            @Override
            public int inMilliseconds() {
                return milliseconds;
            }
        };
    }
}
