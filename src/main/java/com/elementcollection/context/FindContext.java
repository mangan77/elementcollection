package com.elementcollection.context;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.elementcollection.api.Element;
import com.elementcollection.util.Function;

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
