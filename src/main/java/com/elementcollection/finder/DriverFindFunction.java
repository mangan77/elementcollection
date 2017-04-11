package com.elementcollection.finder;

import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.elementcollection.api.Driver;
import com.elementcollection.api.Element;
import com.elementcollection.util.Function;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
class DriverFindFunction implements Function<String, List<Element>> {

    private final Driver driver;

    DriverFindFunction(Driver driver) {
        this.driver = driver;
    }

    @Nullable
    @Override
    public List<Element> apply(@Nullable String input) {
        return driver.findElements(input);
    }
}
