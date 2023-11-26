import base.TestBase;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebElement;
import pages.commons.ProductBoxPage;
import pages.commons.ProductDetailsPopUpPage;
import pages.commons.TopMenuPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class MouseOperationsTest extends TestBase {
    @Test
    @Tag("regression")
    void hoverCategory(){
        TopMenuPage topMenuPage = new TopMenuPage(webdriver);
        ProductBoxPage productBox = new ProductBoxPage(webdriver);

        topMenuPage.
                moveMouseToWomenCategory().
                clickOnBlouses();
        productBox.
                moveMouseToProductImage();

            assertThat(productBox.blouseLabelIsVisible("BLOUSES"), equalTo(true));
        }

    @Test
    @Tag("regression")
    void typeText(){
        TopMenuPage topMenuPage = new TopMenuPage(webdriver);
       topMenuPage.typeInSearchSomeTextWithAction("Darek"); // działa
//       topMenuPage.typeInSearchSomeTextWithOutAction("Darek"); // działa



        assertThat(topMenuPage.checkText("Darek"), equalTo(true));
    }

    @Test
    @Tag("regression")
    void typeTextWithKeyDown(){
        TopMenuPage topMenuPage = new TopMenuPage(webdriver);
        topMenuPage.typeInSearchSomeTextWithActionKeyDown("p"); // działa




        assertThat(topMenuPage.checkText("P"), equalTo(true));
    }


    }

