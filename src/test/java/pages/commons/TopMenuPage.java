package pages.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;


public class TopMenuPage extends BasePage {

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".sf-menu>li:nth-of-type(1)>a")
    private WebElement categoryWomen;

    @FindBy(css = ".submenu-container>li:nth-of-type(1)>ul>li:nth-child(2)>a")
    private WebElement subCategoryBlouses;

    @FindBy(css = "#search_query_top")
    private WebElement searchInput;


    public TopMenuPage clickOnBlouses() {
        clickOnButton(subCategoryBlouses);
        return this;
    }

    public TopMenuPage moveMouseToWomenCategory() {
        mouseHover(categoryWomen);
        return this;
    }

    public void typeInSearchSomeTextWithAction(String text) {
        typeTextWithAction(searchInput, text);
    }

    public void typeInSearchSomeTextWithActionKeyDown(String text) {
        typeTextWithActionKeyDown(searchInput, text);
    }

    public void typeInSearchSomeTextWithOutAction(String text) {
        simpleSendKeys(searchInput, text);
    }

    public boolean checkText(String text) {
        return searchInput.getAttribute("value").trim().equals(text);
    }
}
