package com.elementcollection.collection.select;

import com.elementcollection.element.SelectElement;

/**
 * <br> User: Mangan <br> Date: 02/11/13
 */
class SelectByIndex extends SelectFunction {
    private final int index;

    SelectByIndex(int index) {
        this.index = index;
    }

    @Override
    void doSelect(final SelectElement select) {
        select.selectByIndex(index);
    }

}
