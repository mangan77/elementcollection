package com.elementcollection.impl.context.function;

import com.elementcollection.impl.context.returnvalue.StringReturnValue;
import com.elementcollection.impl.context.returnvalue.ValidatableReturnValue;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
@ParametersAreNonnullByDefault
public class GetVal implements Function<List<WebElement>, ValidatableReturnValue<String>> {
    @Nullable
    @Override
    public ValidatableReturnValue<String> apply(@Nullable List<WebElement> input) {
        final WebElement webElement = Iterables.getFirst(input, null);
        return new StringReturnValue(webElement != null ? webElement.getText() : null);
    }
}
