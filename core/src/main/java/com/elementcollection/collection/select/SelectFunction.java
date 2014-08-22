package com.elementcollection.collection.select;

import com.elementcollection.element.Element;
import com.elementcollection.element.SelectElement;
import com.google.common.base.Function;

import javax.annotation.Nullable;

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
