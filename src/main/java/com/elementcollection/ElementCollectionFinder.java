package com.elementcollection;

/**
 * <br> User: must <br> Date: 2012-11-28
 */
public interface ElementCollectionFinder {

    ElementCollection find(String cssSelector);

    ElementCollectionFinder within(TimeUnit delay);
}
