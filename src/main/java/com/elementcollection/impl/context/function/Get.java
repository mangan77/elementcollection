package com.elementcollection.impl.context.function;

import com.elementcollection.impl.context.returnvalue.ValidatableReturnValue;
import com.elementcollection.impl.context.returnvalue.WebElementsReturnValue;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
@ParametersAreNonnullByDefault
public class Get implements Function<List<WebElement>, ValidatableReturnValue<List<WebElement>>> {

    private final int index;
    private final String selectorString;

    public Get(int index, String selectorString) {
        this.index = index;
        this.selectorString = selectorString;
    }

    @Nullable
    @Override
    public ValidatableReturnValue<List<WebElement>> apply(@Nullable List<WebElement> input) {
        try {
            return new WebElementsReturnValue(Lists.<WebElement>newArrayList(input.get(index)));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("Element collection selected with \"" + selectorString + "\" returned null for index:" + index, e);
        }
    }
}
