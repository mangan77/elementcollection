package com.elementcollection.finder;

import com.elementcollection.collection.ElementCollection;
import com.elementcollection.type.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * <br> User: must <br> Date: 2012-11-28
 */
@ParametersAreNonnullByDefault
public interface ElementCollectionFinder {

    @Nonnull
    ElementCollection find(String cssSelector);

    @Nonnull
    ElementCollectionFinder within(TimeUnit delay);

    @Nonnull
    ElementCollectionFinder wait(TimeUnit delay);
}
