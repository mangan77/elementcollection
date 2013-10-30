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

    public static class SelectByValue extends SelectFunction {

        private final String value;

        public SelectByValue(String value) {
            this.value = value;
        }

        @Override
        void doSelect(final Select select) {
            select.selectByValue(value);
        }
    }

    public static class SelectByIndex extends SelectFunction {
        private final int index;

        public SelectByIndex(int index) {
            this.index = index;
        }

        @Override
        void doSelect(final Select select) {
            select.selectByIndex(index);
        }

    }

    public static class SelectByVisibleText extends SelectFunction {
        private final String text;

        public SelectByVisibleText(String text) {
            this.text = text;
        }

        @Override
        void doSelect(final Select select) {
            select.selectByVisibleText(text);
        }

    }

}
