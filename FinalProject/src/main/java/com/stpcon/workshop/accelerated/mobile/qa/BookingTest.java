package com.stpcon.workshop.accelerated.mobile.qa;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;


public class BookingTest {

    private boolean RUN_ON_LOCAL_SERVER = false;
    private boolean RUN_ON_SAUCE_SERVER = true;

    AppiumTest appiumTest=new AppiumTest();
    AndroidDriver appiumDriver;
    private String lookingGood = "android:id/button1";

    @BeforeTest()
    public void testSetup() throws Exception {
        /*DesiredCapabilities capabilities = new DesiredCapabilities();

        // Platform Details
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.kayak.android");
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.kayak.android.Splash");

        if (RUN_ON_LOCAL_SERVER) {
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "06c1606b2355553c");
            // App Details
            String apkpath = "app/KAYAK-com.kayak.android-1126-v14.1.apk";
            File app = new File(apkpath);
            capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());

            appiumDriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } else if (RUN_ON_SAUCE_SERVER)	{
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0");
            capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.4.10");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
            capabilities.setCapability(MobileCapabilityType.APP,"https://www.dropbox.com/s/a9amgbvcl48p0on/KAYAK-com.kayak.android-1126-v14.1.apk?dl=1");
            appiumDriver = new AndroidDriver<MobileElement>(new URL("http://<<userid>>:<<api key>>@ondemand.saucelabs.com:80/wd/hub"), capabilities);
        }*/

        appiumTest.androidSetup("Samsung Galaxy S4 Emulator",true,"STPCon2015");
        //appiumTest.androidSetup("Samsung Galaxy S3",false,"a100c80b");
        appiumDriver= (AndroidDriver)appiumTest.driver;

        appiumDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test(enabled=false, description="")
    private void bookingFlowTest() throws IOException, InterruptedException {
        //WebElement looksGood = appiumDriver.findElement(By.id("android:id/button1"));
        //System.out.println(looksGood.toString() + " is displayed = [" + looksGood.isDisplayed() + "]");
        //new WebDriverWait(appiumDriver, 3).until(ExpectedConditions.visibilityOf(looksGood)).click();
        //looksGood.click();
        waitAndGetElement(By.id(lookingGood)).click();
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='Flights']")).click();
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='One-way']")).click();
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='FROM']")).click();
        waitAndGetElement(By.id("com.kayak.android:id/smartySearchText")).sendKeys("Los Angeles");
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='LAX : Los Angeles, CA - Los Angeles']")).click();
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='TO']")).click();
        waitAndGetElement(By.id("com.kayak.android:id/smartySearchText")).sendKeys("Toronto");
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='YYZ : Toronto, ON, Canada - Pearson Intl']")).click();

        waitAndGetElement(By.id("com.kayak.android:id/incrementTravelers")).click();

        waitAndGetElement(By.id("com.kayak.android:id/cabinClassText")).click();
        waitAndGetElement(By.xpath("//android.widget.CheckedTextView[@text='Business']")).click();

        waitAndGetElement(By.id("com.kayak.android:id/searchButton")).click();

        WebElement results = appiumDriver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.FrameLayout[2]"));
        new WebDriverWait(appiumDriver,5).until(ExpectedConditions.visibilityOf(results)).click();

        saveScreenshot("BookingScreen");
        Thread.sleep(5000);

    }

    @Test()
    public void bookingWithSwipeTest() throws InterruptedException, IOException {
        waitAndGetElement(By.id(lookingGood)).click();
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='Flights']")).click();
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='One-way']")).click();
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='FROM']")).click();
        waitAndGetElement(By.id("com.kayak.android:id/smartySearchText")).sendKeys("Los Angeles");
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='LAX : Los Angeles, CA - Los Angeles']")).click();
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='TO']")).click();
        waitAndGetElement(By.id("com.kayak.android:id/smartySearchText")).sendKeys("Toronto");
        waitAndGetElement(By.xpath("//android.widget.TextView[@text='YYZ : Toronto, ON, Canada - Pearson Intl']")).click();

        waitAndGetElement(By.id("com.kayak.android:id/incrementTravelers")).click();

        waitAndGetElement(By.id("com.kayak.android:id/cabinClassText")).click();
        waitAndGetElement(By.xpath("//android.widget.CheckedTextView[@text='Business']")).click();

        waitAndGetElement(By.id("com.kayak.android:id/searchButton")).click();

        MobileElement startElement = (MobileElement)appiumDriver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.FrameLayout[3]"));
        MobileElement endElement = (MobileElement)appiumDriver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.FrameLayout[2]"));

        appiumDriver.swipe(
                startElement.getCoordinates().onPage().getX()+20,
                startElement.getCoordinates().onPage().getY(),
                endElement.getCoordinates().onPage().getX()+20,
                endElement.getCoordinates().onPage().getY(),
                3000);

        Thread.sleep(5000);

        appiumDriver.swipe(
                endElement.getCoordinates().onPage().getX()+20,
                endElement.getCoordinates().onPage().getY(),
                startElement.getCoordinates().onPage().getX()+20,
                startElement.getCoordinates().onPage().getY(),
                3000);

        Thread.sleep(5000);

        saveScreenshot("BookingScreen");

        WebElement results = appiumDriver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.FrameLayout[2]"));
        new WebDriverWait(appiumDriver,5).until(ExpectedConditions.visibilityOf(results)).click();
    }

    private WebElement waitAndGetElement(By byLocator) {
        WebElement element = appiumDriver.findElement(byLocator);
        new WebDriverWait(appiumDriver,  5).until(ExpectedConditions.visibilityOf(element));
        System.out.println(element.toString() + " is displayed = [" + element.isDisplayed() + "]");
        Assert.assertEquals(element.isDisplayed(), true);
        return element;
    }

    private void saveScreenshot(String fileName) throws IOException {
        File screenShot = appiumDriver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File(fileName + ".png"));
    }

    @AfterTest()
    public void shutdown(){
        appiumDriver.quit();
    }
}
