import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class testMultipleThreads {

    @Test (priority=1, invocationCount = 5)
    public void loadTestThisWebsite() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        System.out.println("Page Title is " + driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }

    @Test(priority = 1, invocationCount = 10, threadPoolSize = 3)
    public void testThreadPools() {
        System.out.printf("Thread Id : %s%n", Thread.currentThread().getId());
    }

    @Test(priority = 2, invocationCount = 1000, threadPoolSize = 5)
    public void loadTest() {

        System.out.printf("%n[START] Thread Id : %s is started!",
                Thread.currentThread().getId());
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.facebook.com");
        //your action
        driver.manage().window().maximize();
        driver.findElement(By.name("firstname")).sendKeys("first_name_automated_user");
        driver.findElement(By.name("lastname")).sendKeys("last_name_automated_user");
        driver.findElement(By.name("reg_email__")).sendKeys("email@automated_user.com");
        driver.findElement(By.name("reg_passwd__")).sendKeys("password_automated");
        Select day = new Select(driver.findElement(By.name("birthday_day")));
        day.selectByIndex(15);
        driver.findElement(By.name("birthday_year")).sendKeys("1989");
        driver.findElement(By.name("birthday_month")).sendKeys("Ιούνιος");
        driver.findElement(By.name("websubmit")).click();
        WebElement rdBtn_Exp = driver.findElement(By.id("u_0_a"));
        System.out.printf("%n[END] Thread Id : %s",
                Thread.currentThread().getId());
        driver.quit();

    }
}
