package com.elementcollection.collection.select;

import javax.annotation.Nullable;

import com.elementcollection.api.Element;
import com.elementcollection.api.SelectElement;
import com.elementcollection.util.Function;

/**
 * <pre>
 * User: Mangan
 * Date: 4/5/13
 * </pre>
 */
abstract class SelectFunction implements Function<Element, Boolean> {

    @Nullable
    @Override
    public Boolean apply(@Nullable Element element) {
        doSelect(element.asSelect());
        return true;
    }

    abstract void doSelect(SelectElement select);

}
