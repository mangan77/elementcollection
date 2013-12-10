package com.elementcollection.collection.select;

import org.openqa.selenium.support.ui.Select;

/**
 * <br> User: Mangan <br> Date: 02/11/13
 */
class SelectByValue extends SelectFunction {

    private final String value;

    SelectByValue(String value) {
        this.value = value;
    }

    @Override
    void doSelect(final Select select) {
        select.selectByValue(value);
    }
}
