package org.example.khfq868;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    void makeClickActionTest(WebDriver driver, WebElement target) throws InterruptedException, NullPointerException {
        if(target == null){
            throw new NullPointerException("Target element is null");
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

            //обходим куки
            WebElement cookie = driver.findElement(By.xpath("//button[text()='Согласен']"));
            makeClickActionTest(driver, cookie);

            //баннер
            WebElement pythonCourse = driver.findElement(By.xpath(String.format("//a[@class='box__link' and .//h3[text()='%s']]", EXPECTED_FIRST)));
            WebElement pythonCourse2 = null;
            makeClickActionTest(driver, pythonCourse2);

            //статья
            WebElement pythonWhileLoop = driver.findElement(By.xpath(String.format("//a[contains(text(), '%s')]",EXPECTED_LAST)));
            makeClickActionTest(driver, pythonWhileLoop);

            //проверка
            String actual = driver.getTitle();
            assert actual != null;
            assertTrue(actual.contains(EXPECTED_LAST));

        } catch (InterruptedException e){
            System.out.print("Click actions troubles: " + e.getMessage());
        } catch (NullPointerException e){
            System.out.print("Null: " + e.getMessage());
        } catch (Exception e){
            System.out.print(e.getMessage());
        }
    }
}
