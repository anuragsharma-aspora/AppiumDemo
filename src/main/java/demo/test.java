package demo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class test {

    public static void main(String[] args) throws MalformedURLException {
        AppiumDriver driver;

        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setPlatformName("Android");
        uiAutomator2Options.setCapability("appium:newCommandTimeout", 300);  // in seconds
        uiAutomator2Options.setCapability("appium:launchTimeout", 900000);  // in milliseconds
        uiAutomator2Options.setCapability("appium:automationName", AutomationName.ANDROID_UIAUTOMATOR2);
        uiAutomator2Options.setCapability("appium:appPackage","tech.vance.app.debug");
        uiAutomator2Options.setCapability("appium:appActivity","tech.vance.app.ui.activity.MainActivity");


        String sessionUrl = System.getenv("SESSION_URL");

        // Local fallback
        //So your tests also work locally:
        /*String sessionUrl = System.getenv().getOrDefault(
                "SESSION_URL",
                "http://127.0.0.1:4723/wd/hub"
        );*/



        if (sessionUrl == null || sessionUrl.isEmpty()) {
            throw new RuntimeException("SESSION_URL is not set. Did the NativeBridge session start?");
        }

        driver = new AndroidDriver(
                URI.create(sessionUrl).toURL(),
                uiAutomator2Options
        );


        driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,\"tv_country_name\") and @text=\"UAE\"]")).click();

        driver.findElement(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"cl_content\")]")).click();
        driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,\"tv_title\")]")).click();
        driver.findElement(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"btn_one\")]"));
        driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,\"et_input_layout\")]")).clear();

        driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,\"et_input_layout\")]")).sendKeys("555000444");

        driver.findElement(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"btn_verify_phone\")]")).click();

        driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,\"otp_input_view\")]")).sendKeys("001234");

        try{Thread.sleep(10000);}catch(Exception e){}

    }
}
