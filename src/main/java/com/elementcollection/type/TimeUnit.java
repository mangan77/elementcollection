package com.elementcollection.type;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * <br> User: Mangan <br> Date: 03/12/13
 */
@ParametersAreNonnullByDefault
public abstract class TimeUnit {

    @Nonnull
    public abstract int inMilliseconds();

    @Nonnull
    public static TimeUnit secs(final int seconds) {
        return new TimeUnit() {
            @Override
            public int inMilliseconds() {
                return seconds * 1000;
            }
        };
    }

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
