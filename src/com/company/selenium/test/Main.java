package com.company.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    private static WebDriver driver;
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Naxxo\\Desktop\\Files\\Selenium-ChromeDriver\\chromedriver.exe");

        int repetitions = getUserInput();

        in.nextLine(); // made so not to skip a statement
        driver = getDriverWithProfile();

        // getFreeDictionaryWordOfTheDay(); // if you want to run both methods, comment the driver.quit in the method below.

        voteStrawPoll(repetitions);
        in.close();
    }

    private static ChromeDriver getDriverWithProfile() {
        boolean flag;

        System.out.println("Setup of browser profile\nDo you want to use your Chrome profile? (y/n)");
        String answer = in.nextLine();
        flag = "y".equals(answer);
        return flag ? new ChromeDriver(Stream.of(new ChromeOptions()).peek(e -> e.addArguments("user-data-dir=" + "C:\\Users\\Naxxo\\AppData\\Local\\Google\\Chrome\\User Data")).findFirst().get()) : new ChromeDriver();
    }

    @SuppressWarnings("unused")
    private static void getFreeDictionaryWordOfTheDay() {
        driver.get("http://www.thefreedictionary.com/");
        WebElement wordOfTheDay = driver.findElement(By.xpath("//*[@id=\"Content_CA_WOD_0_DataZone\"]/h3/a"));
        System.out.println(wordOfTheDay.getText());
        wordOfTheDay.click();
        driver.quit();
    }

    private static void voteStrawPoll(int repetitions) {
        while (repetitions >= 0) {
            driver.get("http://www.strawpoll.me/11648596");
            WebElement dbCheckbox = driver.findElement(By.xpath("//*[@id=\"field-options-databases\"]"));
            dbCheckbox.click();
            WebElement voteBtn = driver.findElement(By.cssSelector("body > main > form > footer > button:nth-child(1)"));
            voteBtn.sendKeys(Keys.ENTER);
            repetitions -= 1;
        }
        driver.quit();
    }

    private static int getUserInput() {
        System.out.println("Please enter how much times you want to repeat the action:");
        return in.nextInt();
    }
}