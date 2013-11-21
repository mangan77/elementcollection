package com.elementcollection.impl.context.function;

import com.elementcollection.impl.context.returnvalue.BooleanReturnValue;
import com.elementcollection.impl.context.returnvalue.ValidatableReturnValue;
import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 21/11/13
 */
@ParametersAreNonnullByDefault
public class IsDisplayed implements Function<List<WebElement>, ValidatableReturnValue<Boolean>> {
    @Nullable
    @Override
    public ValidatableReturnValue<Boolean> apply(@Nullable List<WebElement> input) {
        if (input.isEmpty()) {
            return new BooleanReturnValue(false);
        }
        for (WebElement webElement : input) {
            if (!webElement.isDisplayed()) {
                return new BooleanReturnValue(false);
            }
        }
        return new BooleanReturnValue(true);
    }
}
