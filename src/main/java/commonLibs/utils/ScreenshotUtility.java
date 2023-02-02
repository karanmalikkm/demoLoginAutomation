package commonLibs.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {
    private WebDriver driver;
    private static final String SCREENSHOT_DIRECTORY = "reports/screenshots/";
    private String screenshotFilePath;

    public ScreenshotUtility(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String filename) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOT_DIRECTORY + filename + "_" +
                    new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + ".jpeg";
            screenshotFilePath = screenshotName;
            Path screenshotPath = Paths.get(screenshotName);
            Files.createDirectories(screenshotPath.getParent());
            Files.move(screenshot.toPath(), screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getScreenshotPath() {
        return screenshotFilePath;
    }
}
