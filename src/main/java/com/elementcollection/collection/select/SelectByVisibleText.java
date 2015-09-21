package com.elementcollection.collection.select;

import com.elementcollection.api.SelectElement;

/**
 * <br> User: Mangan <br> Date: 02/11/13
 */
class SelectByVisibleText extends SelectFunction {
    private final String text;

    SelectByVisibleText(String text) {
        this.text = text;
    }

    @Override
    void doSelect(SelectElement select) {
        select.selectByVisibleText(text);
    }

}
