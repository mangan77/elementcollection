package com.elementcollection.collection.select;

import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.elementcollection.api.Element;
import com.elementcollection.util.Checks;
import com.elementcollection.util.Function;

import static com.elementcollection.util.ElementUtil.isSelectBox;

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
        Checks.checkState(input.size() > 0, "Trying to set text:\"" + value + "\" on empty collection.");
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
