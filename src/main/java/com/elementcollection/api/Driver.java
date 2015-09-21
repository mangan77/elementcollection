package com.elementcollection.api;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;


@ParametersAreNonnullByDefault
public interface Driver {

    List<Element> findElements(String cssSelector);

}
