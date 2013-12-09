package com.elementcollection;

import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * <pre>
 * User: Mangan
 * Date: 09/12/13
 * </pre>
 */
public interface FindContext {
    List<WebElement> find(String cssSelector, Function<String, List<WebElement>> findFunction);
}
