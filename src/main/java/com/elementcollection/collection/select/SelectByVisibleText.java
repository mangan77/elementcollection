package com.elementcollection.collection.select;

import org.openqa.selenium.support.ui.Select;

/**
 * <br> User: Mangan <br> Date: 02/11/13
 */
class SelectByVisibleText extends SelectFunction {
    private final String text;

    SelectByVisibleText(String text) {
        this.text = text;
    }

    @Override
    void doSelect(final Select select) {
        select.selectByVisibleText(text);
    }

}
