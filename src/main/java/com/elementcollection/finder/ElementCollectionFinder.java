package com.elementcollection.finder;

import com.elementcollection.collection.ElementCollection;
import com.elementcollection.type.TimeUnit;

/**
 * <br> User: must <br> Date: 2012-11-28
 */
public interface ElementCollectionFinder {

    ElementCollection find(String cssSelector);

    ElementCollectionFinder within(TimeUnit delay);

    ElementCollectionFinder wait(TimeUnit delay);
}
