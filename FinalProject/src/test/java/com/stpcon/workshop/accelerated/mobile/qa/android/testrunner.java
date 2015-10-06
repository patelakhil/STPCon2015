package com.stpcon.workshop.accelerated.mobile.qa.android;

import com.stpcon.workshop.accelerated.mobile.qa.AppiumTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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
    AndroidDriver driver;

    @BeforeTest
    public void setup(){
        appiumTest=new AppiumTest();

        try {
            appiumTest.androidSetup("Google Nexus 7 HD Emulator",true,"STPCon2015");  //a100c80b
            //appiumTest.androidSetup("Samsung Galaxy S3",false,"a100c80b");
            driver=(AndroidDriver) appiumTest.driver;
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


