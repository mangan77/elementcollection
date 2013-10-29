package com.webelementcollection;

import com.google.common.base.Function;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import javax.annotation.Nullable;

/**
 * <pre>
 * User: Mangan
 * Date: 4/5/13
 * </pre>
 */
abstract class SelectFunction implements Function<WebElement, Boolean> {

    @Nullable
    @Override
    public Boolean apply(@Nullable final WebElement element) {
        doSelect(new Select(element));
        return true;
    }

    abstract void doSelect(final Select select);

}
