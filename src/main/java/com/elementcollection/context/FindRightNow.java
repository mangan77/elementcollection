package com.elementcollection.context;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import com.elementcollection.api.Element;
import com.elementcollection.util.Function;

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
