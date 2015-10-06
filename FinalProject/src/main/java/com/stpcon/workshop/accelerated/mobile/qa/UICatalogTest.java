package com.stpcon.workshop.accelerated.mobile.qa;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;


public class UICatalogTest {

    IOSDriver appiumDriver;

    @Test(description="")
    public void testUICatalog() throws Exception {
        /*File appDir = new File("app");
        File app = new File(appDir, "UICatalog.zip");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.3");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());*/

        AppiumTest appiumTest=new AppiumTest();

        //appiumTest.iOSetup("iPhone 6 Plus",true,"");  //6216238ca6943f6616ea7c9ef31ee2c5f65d1924
        appiumTest.iOSetup("iPhone 6 Plus",false,"6216238ca6943f6616ea7c9ef31ee2c5f65d1924");
        appiumDriver = (IOSDriver)appiumTest.driver;

        List<WebElement> pickerViewList = appiumDriver.findElementsByClassName("UIAStaticText");
        Iterator iterator = pickerViewList.iterator();
        while(iterator.hasNext()) {
            WebElement element = (WebElement) iterator.next();
            System.out.println(element.getText());
        }
    }
}

