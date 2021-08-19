package twc.Regression.Driver;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.allure.annotations.Attachment;

import ru.yandex.qatools.allure.annotations.Step;
import twc.Regression.ReadDataFromFile.read_xml_data_into_buffer;

public class Drivers extends read_Property_File_Info {

	@SuppressWarnings("rawtypes")
	protected static AppiumDriver Ad ;
	public static WebDriver driver = null;
	public static StringBuffer sb = new StringBuffer();;
	
	@Step("{0}")
	public static void logStep(String stepName) {
	}
	@Attachment("Screenshot")
    public static byte[] attachScreen() {
        logStep("Taking screenshot");
        return (((TakesScreenshot)Ad).getScreenshotAs(OutputType.BYTES));
    }
	
	static {
		try {
			read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
			xml_data_into_buffer.read_xml_file_into_buffer_string("normal");
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}
		
	}
}
