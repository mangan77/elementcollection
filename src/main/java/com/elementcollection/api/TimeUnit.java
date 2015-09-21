package com.elementcollection.api;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Class defining time units
 *
 * @author Magnus Granander
 * @since 0.1.0
 */
@ParametersAreNonnullByDefault
public interface TimeUnit {

    /**
     * @return {@code TimeUnit} in milliseconds
     */
    @Nonnull
    public int inMilliseconds();

}
