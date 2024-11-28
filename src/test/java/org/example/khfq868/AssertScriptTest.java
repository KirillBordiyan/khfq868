package org.example.khfq868;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
        Actions confirm = new Actions(driver);
        confirm.moveToElement(target).click().build().perform();
        Thread.sleep(4000);
    }

    @Test
    void titleContainsTest() {
        try {
            driver.get("https://ask42.us/");
            Thread.sleep(4000);


            WebElement cookie = driver.findElement(By.xpath("//button[text()='Согласен']"));
            makeClickAction(driver, cookie);


            WebElement pythonCourse = driver.findElement(By.xpath(String.format("//a[@class='box__link' and .//h3[text()='%s']]", EXPECTED_FIRST)));
            assertNotNull(pythonCourse, "Course link is not found");
            makeClickAction(driver, pythonCourse);


            WebElement pythonWhileLoop = driver.findElement(By.xpath(String.format("//a[contains(text(), '%s')]", EXPECTED_LAST)));
            assertNotNull(pythonWhileLoop, "Article link is not found");
            makeClickAction(driver, pythonWhileLoop);


            String actual = driver.getTitle();
            assertNotNull(actual, "Title should not be null");
            assertTrue(actual.contains(EXPECTED_LAST), "Title does not contain the expected text");

        } catch (InterruptedException e) {
            fail(e.getClass().getSimpleName());
        } catch (Exception e) {
            fail("Exception happens -> " + e.getClass().getSimpleName() + "\n->" + e.getMessage() + "\n");
        }
    }
}
