package com.elementcollection.impl.context.function.select;

import org.openqa.selenium.support.ui.Select;

/**
 * <br> User: Mangan <br> Date: 02/11/13
 */
public class SelectByValue extends SelectFunction {

    private final String value;

    public SelectByValue(String value) {
        this.value = value;
    }

    @Override
    void doSelect(final Select select) {
        select.selectByValue(value);
    }
}
