package com.elementcollection.finder;

import com.elementcollection.driver.Driver;
import com.elementcollection.element.Element;
import com.google.common.base.Function;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

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
