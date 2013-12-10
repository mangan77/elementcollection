package com.elementcollection.context;

import com.elementcollection.type.TimeUnit;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * <br> User: Mangan <br> Date: 10/12/13
 */
@ParametersAreNonnullByDefault
public final class FindContexts {

    private FindContexts() {
    }

    public static FindContext delayed(TimeUnit delay) {
        return new FindDelayed(delay);
    }

    public static FindContext immediate() {
        return new FindRightNow();
    }
}
