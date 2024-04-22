package demo;

import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
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


          @Test
          public void testCase03() throws InterruptedException {
          System.out.println("Start Test case: testCase03");

          driver.navigate().back();
          Thread.sleep(3000);
          SoftAssert softAssert = new SoftAssert();

          WebElement musicTab = driver.findElement(By.xpath("(//yt-formatted-string[text()='Music'])[1]"));
          musicTab.click();
          Thread.sleep(2000); // Wait for page to load
 
         // Scroll to the extreme right in the first section
         WebElement firstSection = driver.findElement(By.xpath("(//ytd-button-renderer[@class='style-scope yt-horizontal-list-renderer arrow'])[4]"));
         Thread.sleep(2000);
        //  JavascriptExecutor js = (JavascriptExecutor) driver;
        //  js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth", firstSection);

        while (firstSection.isDisplayed()) {
            
          firstSection.click();
          Thread.sleep(1000);
      }
         
 
         // Get the name of the playlist
         WebElement playlistName = driver.findElement(By.xpath("(//h3[contains(text(),'Bollywood Dance')])[1]"));
         String playlistTitle = playlistName.getText();
         System.out.println("Playlist Name: " + playlistTitle);
 
         // Get the number of tracks listed
         WebElement tracksList = driver.findElement(By.xpath("(//p[@id='video-count-text' and contains(text(),'50 tracks')])[4]"));
         String trackListTxt = tracksList.getText();

        
         // Soft Assert on whether the number of tracks listed is less than or equal to 50
         softAssert.assertEquals(trackListTxt , "50 tracks");
 
         softAssert.assertAll();
         System.out.println("End Test case: testCase03");
    }
    
        @Test
        public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");

        driver.navigate().back();
        Thread.sleep(3000);
        SoftAssert softAssert = new SoftAssert();
        double result = 0;

        
        WebElement newsbutton=driver.findElement(By.xpath("(//yt-formatted-string[contains(text(),'News')])[1]"));
        newsbutton.click();

        String newslistName = newsbutton.getText();
        System.out.println("newsbutton: " + newslistName);
        Thread.sleep(2000);

        WebElement first_Title=driver.findElement(By.xpath("(//yt-formatted-string[@id='home-content-text'])[1]"));
        Thread.sleep(2000);
        String firstOption=  first_Title.getText();
        System.out.println("Headline1: " + firstOption );

        WebElement second_Title = driver.findElement(By.xpath("(//yt-formatted-string[@id='home-content-text'])[2]"));
        String secondOption =  second_Title.getText();
        System.out.println("Headline2: " + secondOption );

        WebElement third_Title = driver.findElement(By.xpath("(//yt-formatted-string[@id='home-content-text'])[3]"));
        String thirdOption = third_Title.getText();
        System.out.println("Headline3: " + thirdOption );

        Thread.sleep(2000);
        WebElement firstLikeCountElement = driver.findElement(By.xpath("(//span[@id='vote-count-middle'])[1]"));
        String firstLikeCountText = firstLikeCountElement.getText();
        

        // if (firstLikeCountText.contains("k")) {
        //   result = Double.parseDouble(firstLikeCountText.replace("k", "")) * 1000;
        //  }
        System.out.println("Likes for the first headline: " + firstLikeCountText);

        WebElement secondLikeCountElement = driver.findElement(By.xpath("(//span[@id='vote-count-middle'])[2]"));
        String secondLikeCountText = secondLikeCountElement.getText();

        // if (secondLikeCountText.contains("k")) {
        //   result = Double.parseDouble(secondLikeCountText.replace("k", "")) * 1000;
        //  }
        System.out.println("Likes for the second headline: " + secondLikeCountText);

      
        WebElement thirdLikeCountElement = driver.findElement(By.xpath("(//span[@id='vote-count-middle'])[3]"));
        String thirdLikeCountText = thirdLikeCountElement.getText();

        // if (thirdLikeCountText.contains("K")) {
        // result = Double.parseDouble(thirdLikeCountText.replace("K", "").replace(",", "")) * 1000;

        // }

       int likes;
       if (thirdLikeCountText.contains("K")) {
       double likesValue = Double.parseDouble(thirdLikeCountText.replace("K", "").replace(",", "")) * 1000;
       likes = (int) likesValue;
       } else {
       likes = Integer.parseInt(thirdLikeCountText.replace(",", ""));
       }

        System.out.println("Likes for the third headline: " + thirdLikeCountText);
        
         int LikeCount1 = Integer.parseInt(firstLikeCountText);
         int LikeCount2 = Integer.parseInt(secondLikeCountText);
        // int LikeCount3 = Integer.parseInt(thirdLikeCountText);

        int resultCount= LikeCount1 + LikeCount2 + likes;

        System.out.println("Total like Counts : "  + resultCount);
        softAssert.assertAll();

        System.out.println("End Test case: testCase04");
        
      
    }
    
      
        @Test(enabled = true, dataProvider = "excelData")
        public void testCase05(String Search_Keyword) throws InterruptedException {
    
          try {
                System.out.println("Start Test case: testCase06");
    
            // Navigate to Previous Page
            driver.navigate().back();
    
            Thread.sleep(3000);
    
            WebElement searchBox = driver.findElement(By.xpath("//input[@id='search']"));
    
            SeleniumWrapper.clickAction(searchBox, driver);
    
            SeleniumWrapper.enterText(searchBox, Search_Keyword);
    
            WebElement searchBoxClick = driver.findElement(By.id("search-icon-legacy"));
    
            SeleniumWrapper.clickAction(searchBoxClick, driver);
    
            //Get a List of Views using List of WebElement
            List<WebElement> viewsList = driver.findElements(By.xpath("//span[contains(text(), 'views')]"));
    
            // Declare the viewCount initially as 0
            int actual_count = 0;
            // Declare a Empty String for the View Count
            String getViewCount = "";
    
            for (WebElement viewCount : viewsList) {
                String viewcountString = viewCount.getText();
    
                //If viewCount Contains 'M views', remove 'M views'
                if (viewcountString.contains("M views")) {
                    getViewCount = viewcountString.replace("M views", "");
                }
                //If viewCount Contains 'K views', remove 'K views'
                if (viewcountString.contains("K views")) {
                    getViewCount = viewcountString.replace("K views", "");
                }
                // String getViewCount = viewcountString.replace("M views", "");
                float numericviewCount = Float.parseFloat(getViewCount);
                actual_count += numericviewCount;
    
                if (actual_count >= 10000000) { // Checking if the total count reaches 10 million
                    actual_count = 10000000; // Cap the count to 10 million
                    break; // Exit the loop since the target has been reached
                }
            }
    
            // Print Total View Count in Terminal
            System.out.println("Actual Count:" + actual_count);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            System.out.println("end Test case: testCase05");
        }

        @DataProvider(name = "excelData")
        public Object[][] searchData() {
            return new Object[][]{
                    {"Movies"},
                    {"Music"},
                    {"Games"},
                    {"India"},
                    {"UK"}
             
        
      };
    }
  }