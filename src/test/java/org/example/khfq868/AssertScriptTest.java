package org.example.khfq868;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class AssertScriptTest {
    WebDriver driver;
    final String EXPECTED_FIRST = "Введение в Python";
    final String EXPECTED_LAST = "Цикл while и его применение в Python";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    void makeClickAction(WebDriver driver, WebElement target) throws InterruptedException {
        if(target == null){
            throw new IllegalArgumentException("Target element is null");
        }
        Actions confirm = new Actions(driver);
        confirm.moveToElement(target).click().build().perform();
        Thread.sleep(4000);
    }

    @Test
    void titleContainsTest() {
        try {
            driver.get("https://ask42.us/");
            Thread.sleep(4000);

//            Wait<WebDriver> wait = new FluentWait<>(driver)
//                    .withTimeout(Duration.ofSeconds(5))
//                    .pollingEvery(Duration.ofSeconds(1))
//                    .ignoreAll(List.of(NoSuchElementException.class, IllegalArgumentException.class));


//            WebElement co2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Согласен']")));
            //обходим куки
            WebElement cookie = driver.findElement(By.xpath("//button[text()='Согласен']"));
            makeClickAction(driver, cookie);
//            makeClickAction(driver, co2);




            //баннер
//            WebElement pythonCourse = wait.until(
//                    ExpectedConditions.elementToBeClickable(
//                            By.xpath(String.format(
//                                    "//a[@class='box__link' and .//h3[text()='%s']]", EXPECTED_FIRST)))
//            );
            WebElement pythonCourse = driver.findElement(By.xpath(String.format("//a[@class='box__link' and .//h3[text()='%s']]", EXPECTED_FIRST)));
//            WebElement pythonCourse2 = null;
            assertNotNull(pythonCourse, "Course link is not found");
            makeClickAction(driver, pythonCourse);


//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//a[contains(text(), '%s')]",EXPECTED_LAST))));



            //статья
//            WebElement pythonWhileLoop = wait.until(new Function<WebDriver, WebElement>() {
//                @Override
//                public WebElement apply(WebDriver webDriver) {
//                    return driver.findElement(By.xpath(String.format("//a[contains(text(), '%s')]",EXPECTED_LAST)));
//                }
//            });
            WebElement pythonWhileLoop = driver.findElement(By.xpath(String.format("//a[contains(text(), '%s')]",EXPECTED_LAST)));
            assertNotNull(pythonWhileLoop, "Article link is not found");
            makeClickAction(driver, pythonWhileLoop);

            //проверка
            String actual = driver.getTitle();
            assertNotNull(actual, "Title should not be null");
            assertTrue(actual.contains(EXPECTED_LAST), "Title does not contain the expected text");

        } catch (IllegalArgumentException e){
            System.out.print("Null: " + e.getMessage());
            fail("IllegalArgumentException detected -> null element");
            throw e;
        } catch (InterruptedException e){
            System.out.print(e.getMessage());
            fail("InterruptedException -> thread sleep problem ");
        } catch (Exception e){
            System.out.print(e.getMessage());
            fail("Other Exception happens");
        }
    }
}
