package com.webelementcollection.mock;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

/**
 * <br> User: Mangan <br> Date: 02/11/13
 * <p/>
 * Decorator for checkable elements
 */
@ParametersAreNonnullByDefault
public class WebElementCheckableMock extends WebElementMock {

    private final WebElementMock webElementMock;

    public WebElementCheckableMock(WebElementMock webElementMock) {
        this.webElementMock = webElementMock;
    }

    @Override
    public void click() {
        setSelected(!isSelected());
    }

    @Override
    public void submit() {
        webElementMock.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        webElementMock.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        webElementMock.clear();
    }

    @Override
    public String getTagName() {
        return webElementMock.getTagName();
    }

    @Override
    public WebElementMock withTagName(String tagName) {
        return webElementMock.withTagName(tagName);
    }

    @Override
    public String getAttribute(String name) {
        return webElementMock.getAttribute(name);
    }

    @Override
    public WebElementMock withAttribute(String name, String value) {
        return webElementMock.withAttribute(name, value);
    }

    @Override
    public boolean isSelected() {
        return webElementMock.isSelected();
    }

    @Override
    public WebElementMock setSelected(boolean isSelected) {
        return webElementMock.setSelected(isSelected);
    }

    @Override
    public boolean isEnabled() {
        return webElementMock.isEnabled();
    }

    @Override
    public void setEnabled(boolean enabled) {
        webElementMock.setEnabled(enabled);
    }

    @Override
    public String getText() {
        return webElementMock.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webElementMock.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webElementMock.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return webElementMock.isDisplayed();
    }

    @Override
    public WebElementMock setDisplayed(boolean displayed) {
        return webElementMock.setDisplayed(displayed);
    }

    @Override
    public Point getLocation() {
        return webElementMock.getLocation();
    }

    @Override
    public Dimension getSize() {
        return webElementMock.getSize();
    }

    @Override
    public WebElementMock setWidth(int width) {
        return webElementMock.setWidth(width);
    }

    @Override
    public WebElementMock setHeight(int height) {
        return webElementMock.setHeight(height);
    }

    @Override
    public String getCssValue(String propertyName) {
        return webElementMock.getCssValue(propertyName);
    }

    @Override
    public WebElementMock withCssValue(String key, String value) {
        return webElementMock.withCssValue(key, value);
    }

    @Override
    public boolean wasClicked() {
        return webElementMock.wasClicked();
    }

    @Override
    public boolean wasSubmitted() {
        return webElementMock.wasSubmitted();
    }
}
