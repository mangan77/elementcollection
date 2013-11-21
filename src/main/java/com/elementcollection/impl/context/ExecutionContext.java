package com.elementcollection.impl.context;

import com.elementcollection.impl.context.returnvalue.ValidatableReturnValue;
import com.google.common.base.Function;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * <br> User: Mangan <br> Date: 19/11/13
 */
public class ExecutionContext {

    public <T> T execute(Function<List<WebElement>, ValidatableReturnValue<T>> operation, List<WebElement> webElements) {
        return operation.apply(webElements).getReturnValue();
    }

}
