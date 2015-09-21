package com.elementcollection.context;

import com.elementcollection.api.Element;
import com.google.common.base.Function;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 09/12/13
 */
@ParametersAreNonnullByDefault
class FindRightNow implements FindContext {

    @Override
    public List<Element> find(String cssSelector, Function<String, List<Element>> findFunction) {
        return findFunction.apply(cssSelector);
    }

}
