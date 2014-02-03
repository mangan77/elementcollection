package com.elementcollection.type;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Class defining time units
 *
 * @author Magnus Granander
 * @since 0.1.0
 */
@ParametersAreNonnullByDefault
public abstract class TimeUnit {

    /**
     * @return {@code TimeUnit} in milliseconds
     */
    @Nonnull
    public abstract int inMilliseconds();

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
