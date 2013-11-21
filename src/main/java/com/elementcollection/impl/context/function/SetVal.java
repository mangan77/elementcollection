package com.elementcollection.impl.context.function;

import com.elementcollection.impl.context.function.select.SelectByIndex;
import com.elementcollection.impl.context.function.select.SelectByValue;
import com.elementcollection.impl.context.function.select.SelectByVisibleText;
import com.elementcollection.impl.context.function.select.SelectFunction;
import com.elementcollection.impl.context.returnvalue.ValidatableReturnValue;
import com.elementcollection.impl.context.returnvalue.WebElementsReturnValue;
import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.elementcollection.impl.util.ElementUtil.isSelectBox;
import static com.google.common.base.Preconditions.checkState;

/**
 * <br> User: Mangan <br> Date: 20/11/13
 */
@ParametersAreNonnullByDefault
public class SetVal implements Function<List<WebElement>, ValidatableReturnValue<List<WebElement>>> {

    private final String value;
    private final SelectFunction selectFunction;

    private SetVal(String value, SelectFunction selectFunction) {
        this.value = value;
        this.selectFunction = selectFunction;
    }

    public static SetVal forIndex(int index) {
        return new SetVal(String.valueOf(index), new SelectByIndex(index));
    }

    public static SetVal forVisibleValue(String text) {
        return new SetVal(text, new SelectByVisibleText(text));
    }

    public static SetVal forValue(String value) {
        return new SetVal(value, new SelectByValue(value));
    }

    @Nullable
    @Override
    public ValidatableReturnValue<List<WebElement>> apply(@Nullable List<WebElement> input) {
        checkState(input.size() > 0, "Trying to set text:\"" + value + "\" on empty collection.");
        for (WebElement element : input) {
            if (isSelectBox(element)) {
                selectFunction.apply(element);
            } else {
                setValue(element, value);
            }
        }
        return new WebElementsReturnValue(input);
    }

    private void setValue(WebElement element, String value) {
        if (element.isDisplayed()) {
            element.clear();
            element.sendKeys(value);
        }
    }

}
