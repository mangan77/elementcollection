package com.elementcollection.collection.select;

import com.elementcollection.api.SelectElement;

/**
 * <br> User: Mangan <br> Date: 02/11/13
 */
class SelectByValue extends SelectFunction {

    private final String value;

    SelectByValue(String value) {
        this.value = value;
    }

    @Override
    void doSelect(SelectElement select) {
        select.selectByValue(value);
    }
}
