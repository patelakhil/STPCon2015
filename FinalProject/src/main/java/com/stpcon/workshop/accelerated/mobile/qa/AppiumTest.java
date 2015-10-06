package com.stpcon.workshop.accelerated.mobile.qa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class AppiumTest {

    public WebDriver driver;

    private String m_platformname="Android";
    private String m_deviceName="STPCon2015";
    private String m_avdNameORUDID="STPCon2015";
    private String m_udid="";
    private boolean m_isEmulator=true;
    private String m_appPath="";
    private String m_appPackageName="";
    private String m_port="2500";

    private boolean m_Run_On_Sauce=true;
    private final String Sauce_User_Name="akhilkumar";
    private final String Sauce_Access_Key="058410be-f752-4f47-9a26-261482fbb7b3";

    public void setUp() throws Exception {

        /*String appium_url = "appium -p 2500 --full-reset";
        Process p;
        String line = " ";
        BufferedReader reader;

        if((System.getProperty("os.name").toLowerCase().contains("mac"))){
            p = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "ps aux | grep 2500"});
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = reader.readLine())!= null) {
                String PID = line.split("\\s+")[1];
                p = Runtime.getRuntime().exec("kill -9 "+PID);
            }
        }else if(System.getProperty("os.name").toLowerCase().contains("window")){
            p = Runtime.getRuntime().exec("Taskkill /IM appium.exe /F");
            p = Runtime.getRuntime().exec("Taskkill /IM node.exe /F");
            p = Runtime.getRuntime().exec("Taskkill /IM cmd.exe /F");
        }

        if(System.getProperty("os.name").toLowerCase().contains("mac")){
            String filename = System.getProperty("user.dir")+"/src/test/resources/script/appium.sh";
            FileWriter filewriter = new FileWriter(filename);
            BufferedWriter bufferwriter = new BufferedWriter(filewriter);
            bufferwriter.write(appium_url);
            bufferwriter.close();
        }

        if(System.getProperty("os.name").toLowerCase().contains("mac")){
            p = Runtime.getRuntime().exec("chmod u+x "+System.getProperty("user.dir")+"/src/test/resources/script/appium.sh");
            Thread.sleep(2000L);
            p = Runtime.getRuntime().exec("/usr/bin/open -a Terminal "+System.getProperty("user.dir")+"/src/test/resources/script/appium.sh");
            Thread.sleep(5000L);
        }else if(System.getProperty("os.name").toLowerCase().contains("window")){
            p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \""+appium_url);
            Thread.sleep(5000L);
        } */

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, this.m_platformname);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, this.m_deviceName);

        if(!m_Run_On_Sauce) {
            String avdORUdidCapability = "UDID";

            if (this.m_isEmulator && this.m_platformname.equalsIgnoreCase("android")) {

                avdORUdidCapability = "avd";
            }

            capabilities.setCapability(avdORUdidCapability, this.m_avdNameORUDID);
        }

        capabilities.setCapability(MobileCapabilityType.APP, this.m_appPath);
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, m_appPackageName);

        String remoteAddress="http://127.0.0.1:" + this.m_port + "/wd/hub";

        if (m_Run_On_Sauce) {
            //http://SAUCE_USERNAME:SAUCE_ACCESS_KEY@ondemand.saucelabs.com:80/wd/hub
            remoteAddress="http://" + Sauce_User_Name + ":" + Sauce_Access_Key + "@ondemand.saucelabs.com:80/wd/hub";
            capabilities.setCapability("app-package", m_appPackageName);
            if (m_platformname.equalsIgnoreCase("android")) {
                capabilities.setCapability("app-activity", "com.kayak.android.Splash");
            }
            else{
                //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"8.0");
                //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"");
            }

        }

        if (m_platformname.equalsIgnoreCase("android")) {
            driver = new AndroidDriver(new URL(remoteAddress), capabilities);
        }
        else{
            driver = new IOSDriver(new URL(remoteAddress), capabilities);
        }

        /*driver = new AppiumDriver(new URL(remoteAddress), capabilities) {
            public MobileElement scrollTo(String s) {
                return null;
            }

            public MobileElement scrollToExact(String s) {
                return null;
            }
        };*/

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


        Thread.sleep(3000L);

    }

    public void androidSetup(String deviceName, boolean isEmulator, String avdNameORUDID) throws Exception{
        this.m_deviceName=deviceName;
        this.m_isEmulator=isEmulator;
        this.m_avdNameORUDID=avdNameORUDID;

        this.m_platformname="Android";
        if (m_Run_On_Sauce){
            this.m_appPath="sauce-storage:KAYAK-com.kayak.android-1126-v14.1.apk";
        }
        else {
            this.m_appPath=System.getProperty("user.dir")+"/src/test/resources/app/KAYAK-com.kayak.android-1126-v14.1.apk";
        }


        this.m_appPackageName="com.kayak.android";
        this.m_port="2500";

        this.setUp();
    }

    public void iOSetup(String deviceName, boolean isSimulator, String udid) throws Exception{
        this.m_deviceName=deviceName;
        this.m_isEmulator=isSimulator;
        this.m_avdNameORUDID=udid;

        this.m_platformname="iOS";
        if (m_Run_On_Sauce){
            this.m_appPath="sauce-storage:UICatalog.zip";
        }
        else {
            this.m_appPath=System.getProperty("user.dir")+"/src/test/resources/app/UICatalog.zip";
        }

        this.m_appPackageName="";
        this.m_port="2600";

        this.setUp();
    }

    public void tearDown() {
        driver.quit();
    }



}
