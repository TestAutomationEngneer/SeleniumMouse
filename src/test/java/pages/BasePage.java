package pages;

import configuration.WebListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public abstract class BasePage {
    Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WebDriverWait webDriverwait;
    private WebListener webListener = new WebListener();
    private Actions action;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.webDriverwait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(System.getProperty("wait"))));
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
    }


    public void mouseHover(WebElement webElement) {
        waitForWebElementToBeVisable(webElement);
        highLighterMethod(driver, webElement);
        action.moveToElement(webElement).perform();
        logger.info("MouseHover on webelement: {}", webElement.getText());
    }

    public void mouseClick(WebElement webElement) {
        String webElementText = webElement.getText();
        highLighterMethod(driver, webElement);
        action.click(webElement);

        logger.info("MouseClick on webelement: {}", webElementText);
    }

    public void waitForWebElementToBeClickable(WebElement webElement) {
        logger.info("Start waiting for WebElement to be clickable- Timeout set to {} seconds", System.getProperty("wait"));
        webDriverwait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitForWebElementToBeVisable(WebElement webElement) {
        logger.info("Start waiting for WebElement to be visible- Timeout set to {} seconds", System.getProperty("wait"));
        webDriverwait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void clickOnButton(WebElement webElement) {
        String webElementText = webElement.getText();
        waitForWebElementToBeClickable(webElement);
        highLighterMethod(driver, webElement);
        webElement.click();
        logger.info("Clicked on webelement: {}", webElementText);
    }

    public void sendKeysToObject(WebElement webElement, String text) {
        waitForWebElementToBeVisable(webElement);
        webElement.clear();
        webElement.sendKeys(text);
        logger.info("Typed text '{}' to webelement: {}", text, webElement.getAttribute("class"));
    }

    public String getTextFromObject(WebElement webElement) {
        waitForWebElementToBeVisable(webElement);
        String webElementText = webElement.getText();
        logger.info("Displayed text from webelement: {}", webElementText);
        return webElementText;
    }

    public void typeTextWithAction(WebElement webElement, String text) {
        waitForWebElementToBeVisable(webElement);
        highLighterMethod(driver, webElement);
        webElement.clear();
        action.click(webElement);
        action.sendKeys(webElement, text).perform(); //nie działa
        logger.info("Typed text '{}' to webelement: {}", text, webElement.getAttribute("class"));
    }

    public void typeTextWithActionKeyDown(WebElement webElement, String text) {
        waitForWebElementToBeVisable(webElement);
        highLighterMethod(driver, webElement);
        webElement.clear();
        action.click(webElement);
        action.keyDown(Keys.LEFT_SHIFT).sendKeys(webElement, text).perform();
        logger.info("Typed text '{}' to webelement: {}", text, webElement.getAttribute("class"));
    }

    public void simpleSendKeys(WebElement webElement, String text) {
        waitForWebElementToBeVisable(webElement);
        highLighterMethod(driver, webElement);
        webElement.clear();
        webElement.sendKeys(text);
        logger.info("Typed text '{}' to webelement: {}", text, webElement.getAttribute("class"));
    }

    public void highLighterMethod(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        };
    }


}
