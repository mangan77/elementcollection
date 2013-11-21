package com.elementcollection.impl.context.function;

import com.elementcollection.impl.context.returnvalue.ValidatableReturnValue;
import com.elementcollection.impl.context.returnvalue.WebElementsReturnValue;
import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
public class Click implements Function<List<WebElement>, ValidatableReturnValue<List<WebElement>>> {
    @Nullable
    @Override
    public ValidatableReturnValue<List<WebElement>> apply(@Nullable List<WebElement> input) {
        checkState(!input.isEmpty(), "Trying to click on non existing element.");
        for (WebElement element : input) {
            element.click();
        }
        return new WebElementsReturnValue(input);
    }
}
