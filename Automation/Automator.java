package Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class Automator extends VideoSources{
    WebDriver driver;
    private final boolean RUN_HEADLESS = true;
    private final boolean RUN_EXTENSIONS = false;
    private final String ADBLOCK_LOC = "/Users/quibbleh4ck/Desktop/abp.crx";
    private final String POPUPBLOCK_LOC = "/Users/quibbleh4ck/Desktop/pub.crx";
    private final String REDIRECTBLOCK_LOC = "/Users/quibbleh4ck/Desktop/rrd.crx";
    private final String CHROME_DRIVER_LOC = "/Users/quibbleh4ck/Downloads/chromed";

    public Automator() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOC);
        ChromeOptions options = new ChromeOptions();
        Map<String, String> mobileEmulation = new HashMap<>();

        // Force mobile usage
        mobileEmulation.put("deviceName", "Nexus 5");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        // Set file dl location on disk
        String downloadFilepath = "/Users/quibbleh4ck/Desktop/Music";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

        // Set the Chrome preferences
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        options.setExperimentalOption("prefs", chromePrefs);

        if (RUN_HEADLESS) {
            options.addArguments("headless");
            options.addArguments("--headless", "--disable-gpu", "--no-sandbox");
        }

        if (RUN_EXTENSIONS) {
            options.addExtensions(new File(ADBLOCK_LOC));
            options.addExtensions(new File(POPUPBLOCK_LOC));
            options.addExtensions(new File(REDIRECTBLOCK_LOC));
        }

        // Set preferred options
        this.driver = new ChromeDriver(options);

        // Initialize downloader
        this.driver.get(MyFreeMP3());
    }


    public void SwitchTabs() {
        String originalHandle = driver.getWindowHandle();

        for(String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
    }

    /**
     * Submits conversion request to video convert and download
     * @param songName
     */
    public void RequestSong(String songName) throws IOException, InterruptedException {

        // Safe check to gain tabular focus on site
        SwitchTabs();

        String songURL = null;

        WebElement searchBox = driver.findElement(By.xpath("//INPUT[@id='query']"));
        searchBox.sendKeys(songName);

        WebElement searchBtn = driver.findElement(By.xpath("//SPAN[@class='glyphicon glyphicon-search']"));
        searchBtn.click();

        Thread.sleep(2000);

        // Finds relevant links on page
        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
        for (int i = 1; i<links.size(); i++)
        {
            if (links.get(i).getText().equalsIgnoreCase("Download")) {
                System.out.println(links.get(i).getAttribute("href"));
                songURL = links.get(i).getAttribute("href");
                break;
            }
        }

        if (songURL != null) {
            URL domain = new URL(songURL);
            File fileLoc = new File("/Users/quibbleh4ck/Desktop/Music/" + songName + ".mp3");
            org.apache.commons.io.FileUtils.copyURLToFile(domain, fileLoc);
        }
    }
}
