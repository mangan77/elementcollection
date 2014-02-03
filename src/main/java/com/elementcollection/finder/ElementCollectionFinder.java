package com.elementcollection.finder;

import com.elementcollection.collection.ElementCollection;
import com.elementcollection.type.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Interface for finding elements
 *
 * @author Magnus Granander
 * @since 0.1.0
 */
@ParametersAreNonnullByDefault
public interface ElementCollectionFinder {

    /**
     * Will try to find a collection of elements using the CSS selector. If no elements are found, the returned
     * {@code ElementCollection} will be empty.
     *
     * @param cssSelector CSS selector that defines what elements to include in the collection
     * @return A new {@code ElementCollection}
     */
    @Nonnull
    ElementCollection find(String cssSelector);

    /**
     * Enables the {@code ElementCollectionFinder} to retry for a period of time, when trying to find elements.
     *
     * @param delay Time period length
     * @return A new {@code ElementCollectionFinder}
     * @throws java.lang.RuntimeException if a timeout occurs and an exception was thrown by {@see ElementCollectionFinder#find}
     */
    @Nonnull
    ElementCollectionFinder within(TimeUnit delay);

    /**
     * Delays execution for finding elements. Performs a wait before next method is executed.
     *
     * @param delay Delay length
     * @return A new {@code ElementCollectionFinder}
     * @since 0.1.2
     */
    @Nonnull
    ElementCollectionFinder wait(TimeUnit delay);
}
