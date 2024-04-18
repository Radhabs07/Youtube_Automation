package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
   
    //@BeforeMethod
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    //@AfterMethod
    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    @Test
   
    public void testCase01() throws InterruptedException {
      System.out.println("Start Test case: testCase01");
      SoftAssert softassert = new SoftAssert();
  
      driver.get("https://www.youtube.com/");
      Thread.sleep(2000);
      
      String expectedUrl = "https://www.youtube.com/";
      String actualUrl = driver.getCurrentUrl();
      
      System.out.println("Expected URL: " + expectedUrl);
      System.out.println("Actual URL: " + actualUrl);
      
      softassert.assertEquals(actualUrl, expectedUrl, "URL Assertion Failed: Incorrect URL");

      driver.findElement(By.linkText("About")).click();

     // Wait for some time to ensure the page loads completely
    try {
      Thread.sleep(2000); // 2 seconds
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Print the message on the screen
    WebElement message1 = driver.findElement(By.xpath("//p[@class='lb-font-display-3 lb-font-color-text-primary'][1]"));
    String messageText1 = message1.getText();

    WebElement message2 = driver.findElement(By.xpath("//p[@class='lb-font-display-3 lb-font-color-text-primary'][2]"));
    String messageText2 = message2.getText();

    System.out.println("Message on the screen: " + messageText1);
    System.out.println("Message on the screen: " + messageText2);

    softassert.assertAll();
    System.out.println("end Test case: testCase01");     
  } 


    
      @Test
      public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");

        driver.navigate().back();
        Thread.sleep(3000);

        SoftAssert softAssert = new SoftAssert();
          
          // Navigate to the "Films" tab
         WebElement movies = driver.findElement(By.xpath("//yt-formatted-string[text()='Movies']"));
         movies.click();
          
          // Wait for the page to load completely
          Thread.sleep(2000);
          
          // Scroll to the extreme right in the "Top Selling" section
          WebElement topSellingSection = driver.findElement(By.xpath("(//ytd-button-renderer[@class='style-scope yt-horizontal-list-renderer arrow'])[2]"));
          // JavascriptExecutor js = (JavascriptExecutor) driver;
          // js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth", topSellingSection);
          
          while (topSellingSection.isDisplayed()) {
            
              topSellingSection.click();
              Thread.sleep(1000);
          }

               
          // Verify if the movie is marked as "A" for Mature
          WebElement matureMovie = driver.findElement(By.xpath("(//p[text()='A'])[3]"));

          String ratingMovieText = matureMovie.getText();
          softAssert.assertEquals(ratingMovieText,"A");

          System.out.println("verified the rating-"+ ratingMovieText);

          
          // Verify if the movie genre is either "Comedy" or "Animation"
          WebElement movieGenre = driver.findElement(By.xpath("(//span[contains(text(),'Comedy')])[3]"));

          String GenreMovieText = movieGenre.getText();
          softAssert.assertTrue(GenreMovieText.contains("Comedy"),"Unverified Genre");

          System.out.println("verified the GenreMovie-"+ GenreMovieText);

    
          softAssert.assertAll();
          System.out.println("End Test case: testCase02");
      }
  }


           


    



