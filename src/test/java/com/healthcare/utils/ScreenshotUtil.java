package com.healthcare.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {

        String timestamp =
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        String dir =
                System.getProperty("user.dir")
                        + "/test-output/screenshots/";

        new File(dir).mkdirs();

        String path = dir + testName + "_" + timestamp + ".png";

        try {

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(src, new File(path));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "screenshots/" + testName + "_" + timestamp + ".png";
    }
}

