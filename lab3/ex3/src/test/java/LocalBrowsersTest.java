import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

@ExtendWith(SeleniumJupiter.class)
class LocalBrowsersTest {

    ChromeDriver driver;
    PhantomJSDriver phantomDriver;
    HtmlUnitDriver htmlUnitDriver;

    public LocalBrowsersTest(ChromeDriver driver, PhantomJSDriver phantomDriver, HtmlUnitDriver htmlUnitDriver) {
        this.driver = driver;
        this.phantomDriver = phantomDriver;
        this.htmlUnitDriver = htmlUnitDriver;

    }


    @Test
    void testGlobalChrome() {
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(driver.getTitle(),
                containsString("JUnit 5 extension for Selenium"));
    }

    @Test
    void testHeadlessBrowser_PhantomJS() {
        phantomDriver.get("https://bonigarcia.github.io/selenium-jupiter/%22");
        assertNotNull(phantomDriver.getPageSource());
    }

    @Test
    void testHeadlessBrowser_HTMLUnit() {
        htmlUnitDriver.get("https://bonigarcia.github.io/selenium-jupiter/");
        assertThat(htmlUnitDriver.getTitle(), containsString("JUnit 5 extension for Selenium"));
    }




}