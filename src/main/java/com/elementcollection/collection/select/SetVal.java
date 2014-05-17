package com.elementcollection.collection.select;

import com.elementcollection.element.Element;
import com.google.common.base.Function;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.elementcollection.util.ElementUtil.isSelectBox;
import static com.google.common.base.Preconditions.checkState;

/**
 * <br> User: Mangan <br> Date: 20/11/13
 */
@ParametersAreNonnullByDefault
public class SetVal implements Function<List<Element>, List<Element>> {

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
    public List<Element> apply(@Nullable List<Element> input) {
        checkState(input.size() > 0, "Trying to set text:\"" + value + "\" on empty collection.");
        for (Element element : input) {
            if (isSelectBox(element)) {
                selectFunction.apply(element);
            } else {
                setValue(element, value);
            }
        }
        return input;
    }

    private void setValue(Element element, String value) {
        if (element.isDisplayed()) {
            element.clear();
            element.sendKeys(value);
        }
    }

}
