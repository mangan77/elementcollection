package com.elementcollection.executors.functions;

import com.elementcollection.executors.ValidatableReturnValue;
import com.elementcollection.executors.WebElementsReturnValue;
import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.elementcollection.utils.ElementUtil.*;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * <br> User: Mangan <br> Date: 20/11/13
 */
@ParametersAreNonnullByDefault
public class SetBooleanVal implements Function<List<WebElement>, ValidatableReturnValue<List<WebElement>>> {

    private final boolean value;

    public SetBooleanVal(boolean value) {
        this.value = value;
    }

    @Nullable
    @Override
    public ValidatableReturnValue<List<WebElement>> apply(@Nullable List<WebElement> input) {
        for (WebElement element : input) {
            setValue(element, value);
        }
        return new WebElementsReturnValue(input);
    }


    private void setValue(WebElement element, boolean value) {
        checkArgument(isCheckbox(element) || isRadioButton(element) || isSelectOption(element),
                "Boolean values can only be set to checkboxes, radio buttons and select options");

        if (isSelectedButShouldBeDeselected(element, value) || isNotSelectedButShouldBeSelected(element, value)) {
            element.click();
        }
    }

    private boolean isNotSelectedButShouldBeSelected(WebElement element, boolean value) {
        return !element.isSelected() && value;
    }

    private boolean isSelectedButShouldBeDeselected(WebElement element, boolean value) {
        return element.isSelected() && !value;
    }

}
