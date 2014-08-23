package com.elementcollection.collection;


import com.elementcollection.finder.ElementCollectionFinder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * Interface for a collection of elements
 *
 * @author Magnus Granander
 * @since 0.1.0
 */
@ParametersAreNonnullByDefault
public interface ElementCollection extends ElementCollectionFinder {

    /**
     * Clicks all elements in collection
     *
     * @return New {@code ElementCollection}
     */
    @Nonnull
    ElementCollection click();

    /**
     * Submits all elements in collection
     *
     * @return New {@code ElementCollection}
     */
    @Nonnull
    ElementCollection submit();

    /**
     * Get a new {@code ElementCollection} containing the element at the specified position in the collection.
     *
     * @return New {@code ElementCollection}
     * @throws IllegalStateException if {@code ElementCollection} is empty
     */
    @Nonnull
    ElementCollection get(int index);

    /**
     * Get a new {@code ElementCollection} containing the first element in the collection.
     *
     * @return New {@code ElementCollection}
     * @throws IllegalStateException if {@code ElementCollection} is empty
     */
    @Nonnull
    ElementCollection first();

    /**
     * Get a new {@code ElementCollection} containing the last element in the collection.
     *
     * @return New {@code ElementCollection}
     * @throws IllegalStateException if {@code ElementCollection} is empty
     */
    @Nonnull
    ElementCollection last();

    /**
     * Sets a {@code String} value to all elements in {@code ElementCollection}.
     *
     * @return New {@code ElementCollection}
     * @throws IllegalStateException if {@code ElementCollection} is empty
     */
    @Nonnull
    ElementCollection val(String value);

    /**
     * Sets a {@code boolean} value to all elements in {@code ElementCollection}.
     *
     * @return New {@code ElementCollection}
     * @throws IllegalStateException if {@code ElementCollection} is empty
     */
    @Nonnull
    ElementCollection val(boolean value);

    /**
     * Get all elements in a collection as new separated {@code ElementCollection}. If the {@code ElementCollection}
     * is empty, an empty {@code List} will be returned.
     *
     * @return All elements in collection as a {@code List} of new {@code ElementCollection}
     */
    @Nonnull
    List<ElementCollection> getElements();


    /**
     * Sets an {@code int} value to all elements in {@code ElementCollection}.
     *
     * @return New {@code ElementCollection}
     * @throws IllegalStateException if {@code ElementCollection} is empty
     */
    @Nonnull
    ElementCollection val(int value);

    /**
     * Returns the value of the first element in the collection. Returns null if the collection is empty.
     *
     * @return Value of first element in collection, or {@code null} if collection is empty.
     */
    @Nullable
    String val();

    /**
     * Returns the value of the first element's specified attribute if it's set. Will return null if the collection
     * is empty or if the element has no value set for the specified attribute
     * *
     *
     * @return Value of attribute for the first element in the collection, or {@code null} if collection is empty or
     * attribute is not set.
     */
    @Nullable
    String attr(String name);

    /**
     * Gives number of elements in the current collection
     *
     * @return Number of elements in the current collection
     */
    int length();

    @Nonnull
    ElementCollection valByIndex(int index);

    /**
     * Returns true if the collection is not empty AND if all elements in it are visible.
     *
     * @return True if the collection is not empty AND if all elements in it are visible.
     */
    boolean isDisplayed();

    @Nonnull
    ElementCollection valByVisibleText(String text);

    /**
     * True if collection does not contain any elements
     *
     * @return True if collection contains no elements, false otherwise
     * @since 0.2.0
     */
    boolean isEmpty();

    /**
     * True if collection has at least one element
     *
     * @return True if collection has at least one element, false if it's empty
     * @since 0.2.0
     */
    boolean hasElements();

    /**
     * Returns true if the collection is not empty AND if all elements has the specified CSS class.
     *
     * @param cssClass CSS class to look for
     * @return True if the collection is not empty AND if all elements has the specified CSS class.
     */
    boolean hasClass(String cssClass);
}
