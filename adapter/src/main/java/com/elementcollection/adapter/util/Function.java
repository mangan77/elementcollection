package com.elementcollection.adapter.util;

import javax.annotation.Nullable;

/**
 * <pre>
 * User: Mangan
 * Date: 11/11/14
 * </pre>
 */
interface Function<F, T> {

    @Nullable
    T apply(@Nullable F input);


}
