elementcollection
=================
ElementCollection is a JQuery inspired framework for working with Selenium. It is heavily dependent on CSS selectors.

#Basic usage
The first thing to do is to use a ElementCollectionFinder to get hold of an ElementCollection.

```java
ElementCollectionFinder finder = ElementCollectionFinders.fromWebDriver(driver.getDriver());
```
This will give you a ElementCollectionFinder to use for finding a collection

```java
ElementCollection collection = finder.find(".class");
```
The code above tries to find all elements with CSS class "class" and returns an ElementCollection containing them. An ElementCollection has a couple of methods other then `find()` to help you interact with the DOM.

Some examples
```java
collection.click(); // Clicks all elements in the collection
collection.find("yourCSSSelector"); // Finds elements in the collection matching the CSS selector
collection.attr("name"); // Reads the attribute of the first element in the collection and returns its value
```
Have a look at ElementCollection for more methods.

#Handling timing issues

Working with DOM manipulation often means you encounter a lot of problems regarding timing. Elements might take a long time to load and elements might be generated in the DOM after user interaction. To handle timing issues ElementCollections offer a number of convenience methods

```java
finder.within(TimeUnit.secs(2)).find("div");
```
`within()` will cause the following `find()` method to try to find all div elements multiple times within the 2 seconds specified, before failing. A succesful "find" will return as soon as possible, not waiting until the 2 seconds have past.

```java
finder.wait(TimeUnit.millis(10)).click();
```
`wait()` will cause the execution of `click()` to be delayed for 10 milliseconds before trying to click the elements. 