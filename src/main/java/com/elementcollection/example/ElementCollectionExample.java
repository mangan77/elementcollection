package com.elementcollection.example;

import com.elementcollection.adapters.WebDriverAdapter;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * <br> User: Mangan <br> Date: 20/11/13
 */
@ParametersAreNonnullByDefault
public class ElementCollectionExample {

    public static void main(String[] args) {
        final WebDriverAdapter webDriverAdapter = new WebDriverAdapter(new FirefoxDriver());
        try {
            webDriverAdapter.get("https://bitbucket.org");
            webDriverAdapter.find("ul.aui-nav").find("li > a").first().click();
        } finally {
            webDriverAdapter.close();
        }
    }


}
