package com.elementcollection.finder;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
class WebDriverFindFunction implements Function<String, List<WebElement>> {

    private final WebDriver webDriver;

    WebDriverFindFunction(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Nullable
    @Override
    public List<WebElement> apply(@Nullable String input) {
        return webDriver.findElements(By.cssSelector(input));
    }
}
