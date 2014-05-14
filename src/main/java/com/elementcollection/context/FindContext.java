package com.elementcollection.context;

import com.elementcollection.element.Element;
import com.google.common.base.Function;

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
    List<Element> find(String cssSelector, Function<String, List<Element>> findFunction);
}
