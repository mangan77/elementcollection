package com.elementcollection.functions.select;

import org.openqa.selenium.support.ui.Select;

/**
 * <br> User: Mangan <br> Date: 02/11/13
 */
public class SelectByIndex extends SelectFunction {
    private final int index;

    public SelectByIndex(int index) {
        this.index = index;
    }

    @Override
    void doSelect(final Select select) {
        select.selectByIndex(index);
    }

}
