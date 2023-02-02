package commonLibs.implementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class CommonDriver {

    private final WebDriver driver;
    private int pageLoadTimeout;
    private int elementDetectionTimeout;

    public WebDriver getDriver() {
        return driver;
    }

    public void setPageLoadTimeout(int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public void setElementDetectionTimeout(int elementDetectionTimeout) {
        this.elementDetectionTimeout = elementDetectionTimeout;
    }

    public CommonDriver(String browserType) {
        String currentWorkingDirectory = System.getProperty("user.dir");
        pageLoadTimeout = 10;
        elementDetectionTimeout = 10;
        if(browserType.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", currentWorkingDirectory +"/src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver", currentWorkingDirectory +"/src/main/resources/msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Invalid Browser Type: " + browserType);
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    public void navigateToUrl(String url){
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);
        driver.get(url);
    }

    public void closeBrowser(){
        driver.quit();
    }

    public String getTitleOfPage(){
        return driver.getTitle();
    }
}
