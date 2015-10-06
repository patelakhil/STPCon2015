package com.stpcon.workshop.accelerated.mobile.qa.ios;

import com.stpcon.workshop.accelerated.mobile.qa.AppiumTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by TheTechnocrat on 9/30/15.
 */

// From command prompt
//mvn clean test -Dcucumber.options="--tags @Testing5 --glue com.vijay ./src/test/resources/featureFiles/"
//@CucumberOptions(features = "classpath:featureFiles/",tags = "@Testing5", glue = { "com.vijay" }, plugin = { "pretty:STDOUT" }, dryRun = false)

public class testrunner {

    AppiumTest appiumTest;
    AppiumDriver driver;

    @BeforeTest
    public void setup(){
        appiumTest=new AppiumTest();

        try {
            appiumTest.iOSetup("iPhone 6 Plus",true,"");
            driver=(AppiumDriver)appiumTest.driver;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in setup:" + e.getMessage());
        }
    }

    @Test
    public void waitOnly(){
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void teardown(){
        appiumTest.tearDown();
    }
}


