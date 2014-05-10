package com.elementcollection.driver;

import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;


@ParametersAreNonnullByDefault
public interface Driver {

    List<WebElement> findElements(String cssSelector);

}
