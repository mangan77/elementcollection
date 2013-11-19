package com.elementcollection.executors.functions;

import com.elementcollection.executors.ValidatableReturnValue;
import com.elementcollection.executors.WebElementsReturnValue;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
@ParametersAreNonnullByDefault
public class First implements Function<List<WebElement>, ValidatableReturnValue<List<WebElement>>> {
    @Nullable
    @Override
    public ValidatableReturnValue<List<WebElement>> apply(@Nullable List<WebElement> input) {
        try {
            Preconditions.checkArgument(input.size() > 0);
            return new WebElementsReturnValue(Lists.newArrayList(Iterables.getFirst(input, null)));
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Element collection is empty", e);
        }
    }
}
