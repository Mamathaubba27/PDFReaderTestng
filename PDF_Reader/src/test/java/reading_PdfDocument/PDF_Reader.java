package reading_PdfDocument;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PDF_Reader {
	
	WebDriver driver;
	
	@BeforeTest
	public void testSetUp()
	{
		/*	System.setProperty("webdriver.chrome.driver","C:\\Users\\Ravee\\Desktop\\selenium_java_maven\\PDF_Reader\\Drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("file:///C:/Users/Ravee/Desktop/Uploading%20Documents%20Format.pdf"); */
	 System.setProperty("webdriver.gecko.driver","C:\\Users\\Ravee\\Desktop\\selenium_java_maven\\PDF_Reader\\Drivers\\geckodriver.exe");
	 driver = new FirefoxDriver();
     driver.get("file:///C:/Users/Ravee/Desktop/Uploading%20Documents%20Format.pdf"); 
	// driver.get("http://www.axmag.com/download/pdfurl-guide.pdf");
	 
	}
	
	
	
	@Test
	public void readPDFTest() throws IOException
	{

		String currenturl = driver.getCurrentUrl();
		System.out.println(currenturl);
		  
		URL url = new URL(currenturl);
		InputStream is = url.openStream();
		BufferedInputStream filetoparse = new BufferedInputStream(is);
		PDDocument doc = null;
		doc = PDDocument.load(filetoparse); 
	//	PDDocument doc = PDDocument.load(url.openStream());
		
		String pdfcontent = new PDFTextStripper().getText(doc);
		
	      File fs = new File("E:\\PDFToText.txt");
	      FileWriter writer = new FileWriter(fs);
	      writer.write(pdfcontent);
	      writer.flush();
	      System.out.println("File copied successfully.......");
		//System.out.println(pdfcontent);
		
	      doc.close();
		
	}
	
  @AfterTest
    public void tearDown()
    {
  	  
		driver.quit();
    }
	
	

}
