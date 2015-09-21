package com.elementcollection.context;

import com.elementcollection.api.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * <br> User: Mangan <br> Date: 10/12/13
 */
@ParametersAreNonnullByDefault
public final class FindContexts {

    private FindContexts() {
    }

    @Nonnull
    public static FindContext delayed(TimeUnit delay) {
        return new FindDelayed(delay);
    }

    @Nonnull
    public static FindContext immediate() {
        return new FindRightNow();
    }

    @Nonnull
    public static FindContext waiting(TimeUnit delay) {
        return new WaitToFind(delay);
    }

    public static FindContext visibility(TimeUnit delay) {
        return new WaitForVisibility(delay);
    }
}
