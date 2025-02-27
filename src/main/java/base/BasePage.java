package base;

import org.apache.commons.io.FileUtils;
import org.apache.commons.codec.binary.Base64; // Correct Base64 import
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class BasePage {
    public static String getScreenshot(String imageName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File f = ts.getScreenshotAs(OutputType.FILE);

        // Ensure screenshot directory exists
        File screenshotDir = new File("./screenshot/");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        String filePath = "./screenshot/" + imageName;
        FileUtils.copyFile(f, new File(filePath));
        return filePath;
    }

    public static String converting_Base64(String screenshotPath) throws IOException {
        byte[] file = FileUtils.readFileToByteArray(new File(screenshotPath));
        return Base64.encodeBase64String(file); // Correct Base64 encoding
    }
}
