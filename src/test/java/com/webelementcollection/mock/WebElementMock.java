package com.webelementcollection.mock;

import com.google.common.collect.Maps;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

/**
 * <br> User: Mangan <br> Date: 02/11/13
 */ //*
public class WebElementMock implements WebElement {

    private int numClicked;
    private int numSubmitted;
    private String text;
    private String tagName;
    private int width;
    private int height;
    private Map<String, String> cssValues;
    private Map<String, String> attributes;
    private boolean isSelected;
    private boolean isEnabled;
    private boolean isDisplayed;

    public WebElementMock() {
        text = "";
        width = 0;
        height = 0;
        cssValues = Maps.newHashMap();
        attributes = Maps.newHashMap();
        numClicked = 0;
        numSubmitted = 0;
    }

    @Override
    public void click() {
        numClicked++;
    }

    public boolean wasClicked() {
        return numClicked > 0;
    }

    @Override
    public void submit() {
        numSubmitted++;
    }

    public boolean wasSubmitted() {
        return numSubmitted > 0;
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        String tmp = "";
        for (CharSequence charSequence : keysToSend) {
            tmp += charSequence.toString();
        }
        text = tmp;
    }

    @Override
    public void clear() {
        text = "";
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    public WebElementMock withTagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    @Override
    public String getAttribute(String name) {
        return attributes.get(name);
    }

    public WebElementMock withAttribute(String name, String value) {
        attributes.put(name, value);
        return this;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public WebElementMock setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        return this;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public List<WebElement> findElements(By by) {
        throw new RuntimeException("Not impl");
    }

    @Override
    public WebElement findElement(By by) {
        throw new RuntimeException("Not impl");
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }

    public WebElementMock setDisplayed(boolean displayed) {
        isDisplayed = displayed;
        return this;
    }

    @Override
    public Point getLocation() {
        throw new RuntimeException("Not impl");
    }

    @Override
    public Dimension getSize() {
        return new Dimension(width, height);
    }

    public WebElementMock setWidth(int width) {
        this.width = width;
        return this;
    }

    public WebElementMock setHeight(int height) {
        this.height = height;
        return this;
    }

    @Override
    public String getCssValue(String propertyName) {
        return cssValues.get(propertyName);
    }

    public WebElementMock withCssValue(String key, String value) {
        cssValues.put(key, value);
        return this;
    }

    public WebElementMock asCheckable() {
        return new WebElementCheckableMock(this);
    }
}
