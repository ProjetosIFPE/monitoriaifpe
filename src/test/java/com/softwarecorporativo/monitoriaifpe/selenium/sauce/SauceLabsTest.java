package com.softwarecorporativo.monitoriaifpe.selenium.sauce;

import com.saucelabs.common.SauceOnDemandAuthentication;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

import java.net.URL;
import java.util.LinkedList;

import com.saucelabs.common.SauceOnDemandSessionIdProvider;

@RunWith(ConcurrentParameterized.class)
public class SauceLabsTest implements SauceOnDemandSessionIdProvider {

    public String username = "orube";
    public String accesskey = "0b49be3a-fb29-4012-9f6f-6eb06205fd10";

    public static String seleniumURI = "@ondemand.saucelabs.com:80";

    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(username, accesskey);

    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

    @Rule
    public TestName name = new TestName();

    protected String browser;

    protected String os;

    protected String version;

    protected String deviceName;

    protected String deviceOrientation;

    protected String sessionId;

    protected WebDriver driver;

    public SauceLabsTest(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super();
        this.os = os;
        this.version = version;
        this.browser = browser;
        this.deviceName = deviceName;
        this.deviceOrientation = deviceOrientation;
    }

    @ConcurrentParameterized.Parameters
    public static LinkedList browsersStrings() {
        LinkedList browsers = new LinkedList();

        browsers.add(new String[]{"Windows 8", "50", "chrome", null, null});

        browsers.add(new String[]{"OSX 10.10", "8", "safari", null, null});

        browsers.add(new String[]{"Linux", "37", "firefox", null, null});

        browsers.add(new String[]{"Linux", "4.4", "android", "Android Emulator", "portrait"});

        browsers.add(new String[]{"Mac 10.10", "9.2", "iPhone", "iPhone 6", "portrait"});

        return browsers;
    }

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browser != null) {
            capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        }
        if (version != null) {
            capabilities.setCapability(CapabilityType.VERSION, version);
        }
        if (deviceName != null) {
            capabilities.setCapability("deviceName", deviceName);
        }
        if (deviceOrientation != null) {
            capabilities.setCapability("device-orientation", deviceOrientation);
        }

        capabilities.setCapability(CapabilityType.PLATFORM, os);

        String methodName = name.getMethodName();
        capabilities.setCapability("name", methodName);

        this.driver = new RemoteWebDriver(
                new URL("http://" + username + ":" + accesskey + seleniumURI + "/wd/hub"),
                capabilities);

        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();

        String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", this.sessionId, methodName);
        System.out.println(message);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

}
