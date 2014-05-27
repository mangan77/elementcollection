package com.elementcollection.driver;

import com.elementcollection.element.Element;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;


@ParametersAreNonnullByDefault
public interface Driver {

    List<Element> findElements(String cssSelector);

}
