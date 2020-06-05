package Silicube;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class euribor6mHistory {
	public WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		
		//Setting up the webdriver and launching the bowser
				
				
				
		//Validating the number of input provided
		if(args==null || args.length==0) {
			System.out.println("Please provide start date and end date");
		}
		if(args.length==1) {
			System.out.println("Please end date");
		}
		if(args.length>2) {
			System.out.println("Please provide only two arguments");
		}
		euribor6mHistory Obj= new euribor6mHistory();
		Obj.initialsetup();
		Thread.sleep(2000L);
		
		//Method for getting the 6 months eurobar rates
		if(args.length==2) {
			Obj.createReport(args[0], args[1]);
			
			Obj.browserQuit();
		}
	}
	
	private  void initialsetup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver1\\chromedriver_win32 (1)\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000L);
		driver.get("https://www.euribor-rates.eu/en/current-euribor-rates/3/euribor-rate-6-months/");
		
	}

	public void createReport(String startDateString, String endDateString) {
		try {
			Thread.sleep(2000L);
		
		Date startDate = getValidatedDate(startDateString);
		if(startDate==null) {
			System.out.println("Start date invalid");
		}
		Date endDate = getValidatedDate(endDateString);
		if(endDate==null) {
			System.out.println("End date invalid");
		}
		
		//Populate euribor rate from html sorce
		WebElement ByMonthTable =driver.findElement(By.xpath("//div[@class='col-12 col-lg-4 mb-3 mb-lg-0'][2]"));
	     
		//Strong the row values of  ByMonthTable to  euribor List
	     List<WebElement> euribor=ByMonthTable.findElements(By.xpath("//div[@class='col-12 col-lg-4 mb-3 mb-lg-0'][2]//tr"));
			
			//Iterating the value using loop
		
		for(int i=0;i<euribor.size();i++)

		{	
		  String tablevalue= euribor.get(i).getText();
		  //Splitting the text into date and rate
		  String[] newdate1 = tablevalue.split("-");
		  //Removing the white space
		  String dateEntry = newdate1[0].replaceAll("\\s", "");
		  String rateEntry = newdate1[1].replaceAll("\\s","");
		 //validating the Currentdate
		  Date GivenDate = getValidatedDate(dateEntry);
		  
		  {
			if((startDate.before(GivenDate) || compareDates(startDate,GivenDate))
					&& (endDate.after(GivenDate) || compareDates(endDate,GivenDate))) {
				System.out.println(getFormatedDate(GivenDate) +" "+rateEntry);
			}
		}}} 
		
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean compareDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
	    Calendar cal2 = Calendar.getInstance();
	    cal1.setTime(date1);
	    cal2.setTime(date2);
	    boolean sameMonthAndYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
	                      cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		return sameMonthAndYear;
	
	}
	private static String getFormatedDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		String stringDate = formatter.format(date);
		return stringDate;
	}
	
	private static Date getValidatedDate(String stringDate) {
		Date date =null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy");
		try {
			date = formatter.parse(stringDate);
		} catch (ParseException e) {
			System.out.println("Invalid date format");
		}
		return date;
	

}
	 public void browserQuit(){
		   
		   driver.quit();
		   
	    }

}


