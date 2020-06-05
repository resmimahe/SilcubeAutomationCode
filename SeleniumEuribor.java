package Silicube;





import java.text.ParseException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumEuribor {
	
	public  WebDriver driver;
	
	  


	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub
		
		
		
		//String NewStratDate ="01/02/2019";
       // String NewEndDate= "03/02/2020";
		
		SeleniumEuribor Obj1= new SeleniumEuribor();
		Obj1.browserInitiation();
		Obj1.getEuroBor(args[0], args[1]);
		Obj1.browserQuit();
		
	}
	
	 public void browserInitiation(){
		   System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver1\\chromedriver_win32 (1)\\chromedriver.exe");
		   driver = new ChromeDriver();
		   driver.manage().window().maximize();
		   driver.get("https://www.euribor-rates.eu/en/current-euribor-rates/3/euribor-rate-6-months/");
	    }
	 
	
	 
	private  void getEuroBor(String StartDate, String EndDate) throws InterruptedException, ParseException
	{
		
		WebElement ByMonthTable =driver.findElement(By.xpath("//div[@class='col-12 col-lg-4 mb-3 mb-lg-0'][2]"));
		Thread.sleep(10000L);
		
		List<WebElement> euribor=ByMonthTable.findElements(By.xpath("//div[@class='col-12 col-lg-4 mb-3 mb-lg-0'][2]//tr"));
		
		Thread.sleep(2000L);
		
		System.out.println("6 month history of euribore rates");
		
		for(int i=0;i<euribor.size();i++)

		{
			
			
		  String tablevalue= euribor.get(i).getText();
		 

		String[] newdate1 = tablevalue.split("-");
		String dateEntry = newdate1[0].replaceAll("\\s", "");
		  String rateEntry = newdate1[1].replaceAll("\\s","");;
		  
		  
		for(int j=0;j<newdate1.length;j++) {
			
		}
		
		String FirstEntry = newdate1[0].replaceAll("\\s", ""); 
	       
        //Extracting the month and year of row date
		
		  int firstIndex = FirstEntry.indexOf("/");
		
		 int lastIndex = FirstEntry.lastIndexOf("/");
			String Givenmonth = FirstEntry.substring(0, firstIndex);
			int k= Givenmonth.length();
			if(k<=1)
			{
				String x="0";
				Givenmonth=x +Givenmonth;
						}
			
			String year = FirstEntry.substring(lastIndex+1, FirstEntry.length());
			
			String CurrentValue = year+ Givenmonth;
			
			int CurrentData = Integer.parseInt(CurrentValue);
			
			//Extracting the month and year of end date
			int firstIndex1 = EndDate.indexOf("/");
			int lastIndex1 = EndDate.lastIndexOf("/");
			
			String EndMonth = EndDate.substring(0, firstIndex1);
			
			String Endyear = EndDate.substring(lastIndex1+1, EndDate.length());
			String EndValue = Endyear+ EndMonth;
			int EndData = Integer.parseInt(EndValue);
			
			//Extracting the month and year of row date
			int firstIndex2 = StartDate.indexOf("/");
			int lastIndex2 = StartDate.lastIndexOf("/");
			
			String StartMonth = StartDate.substring(0, firstIndex2);

			
			String YearStart = StartDate.substring(lastIndex2+1, StartDate.length());
			
			String StartValue = YearStart+ StartMonth;
			
			int StartData = Integer.parseInt(StartValue);
		//Getting the range of rates within the given start and end date	
			
		if(StartData<=CurrentData && EndData>= CurrentData)
{
			
		
	 System.out.println(tablevalue);
	
	 
	}	
	}	
	}
	 public void browserQuit(){
		   
		   driver.quit();
		   
	    }
	
	
	
}
