package com.elementcollection.context;

import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <pre>
 * User: Mangan
 * Date: 09/12/13
 * </pre>
 */
@ParametersAreNonnullByDefault
public interface FindContext {

    @Nonnull
    List<WebElement> find(String cssSelector, Function<String, List<WebElement>> findFunction);
}
