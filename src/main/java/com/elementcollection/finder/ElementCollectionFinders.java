package com.elementcollection.finder;

import com.elementcollection.driver.Driver;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * <br> User: Mangan <br> Date: 10/12/13
 */
@ParametersAreNonnullByDefault
public final class ElementCollectionFinders {

    private ElementCollectionFinders() {
    }

    @Nonnull
    public static ElementCollectionFinder create(Driver driver) {
        return new ElementCollectionFinderImpl(driver);
    }

}
