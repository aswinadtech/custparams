package twc.Regression.General;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.naming.spi.ObjectFactoryBuilder;
import javax.net.ssl.HostnameVerifier;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.ComparisonFailure;
import ru.yandex.qatools.allure.annotations.Title;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

//import com.Genaral.readExcelValues;
//import com.weather.excel.ExcelData;
//import com.weather.excel.Write_result;

import twc.Regression.General.DeviceStatus;
import twc.Regression.HandleWithAppium.AppiumFunctions;
import twc.Regression.HandleWithCharles.CharlesFunctions;
import twc.Regression.General.Functions;

import twc.Regression.Driver.Drivers;
import twc.Regression.ReadDataFromFile.read_excel_data;
import twc.Regression.ReadDataFromFile.read_xml_data_into_buffer;
import twc.Regression.ReadDataFromFile.write_excel_data;
import twc.Regression.utils.ReadExcelData;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
public class Functions extends Drivers{

	public static String currentday1="wed1";
	public static List<String> listOf_b_Params = new ArrayList<String>();
	public static int aaxcallsSize = 0;
	public static int aaxbidErrorCount = 0;
	public static boolean isaaxgampadcallexists = false;
	public static boolean nextGenIMadDisplayed = false;
	public static String videoIUValue = null;
	public static int aaxcallsResponseSize = 0;
	static int startY;
	static int endY;
	MobileElement skiModule=null;
	MobileElement AllergyModule=null;
	static WebElement coldFluModule =null;
	public static ArrayList<String> aaxSlots = new ArrayList<String>();
	
	public static List<String> listOf_criteo_Params = new ArrayList<String>();
	public static int criteoparamErrorCount = 0;
	public static String[] homescreenfeedad;
	public static String[] Deatailpagead;
	public static String adType;
	  public static final int maxTimeout = 60;
	  public static int criteocallsSize = 0;
	//  public static String videoIUValue = null;
		public static String iuId = null;
		public static int criteocallsResponseSize = 0;
		public static List<String> customParamsList = new ArrayList<String>();
		public static int criteogampadcallcount = 0;
	public static void validate_API_Call_With_PubAds_Call(String excel_sheet_name) throws Exception{

		String apicall_results=null;
		String pubadscall_results=null;

		Map<String, String> api_call_results = read_API_Call_Data(excel_sheet_name);
		Map<String, String> pubads_call_results = read_Pub_Ad_Call_Data(excel_sheet_name);
		//System.out.println(api_call_results);
		//System.out.println(pubads_call_results);
		if(api_call_results.keySet().size() == 1){

			for (String key : api_call_results.keySet()) {
				//System.out.println("key: " + key + " value: " + api_call_results.get(key));
				apicall_results = api_call_results.get(key).toString().replace("[", "").replace("]", "");
				//System.out.println(apicall_results);
			}
			for (String pubkey : pubads_call_results.keySet()) {
				//System.out.println("key: " + pubkey + " value: " + pubads_call_results.get(pubkey));
				pubadscall_results = pubads_call_results.get(pubkey).toString().replace("[", "").replace("]", "");
				//System.out.println(pubadscall_results);
			}

			String[] pubadsresults = pubadscall_results.split(",");
			for(int i=0;i<pubadsresults.length;i++){
				if(apicall_results.contains(pubadsresults[i])){
					System.out.println("Matched With "+ pubadscall_results +" :::: " + pubadsresults[i]);
				}
				else{
					System.out.println("Does Not Matched With "+ pubadscall_results +" :::: " + pubadsresults[i]);
				}
			}

		}
		else{

			for (String key : api_call_results.keySet()) {
				//System.out.println("key: " + key + " value: " + api_call_results.get(key));
				apicall_results = api_call_results.get(key).toString().replace("[", "").replace("]", "");
				//ystem.out.println(apicall_results);
			}
			for (String pubkey : pubads_call_results.keySet()) {
				//System.out.println("key: " + pubkey + " value: " + pubads_call_results.get(pubkey));
				pubadscall_results = pubads_call_results.get(pubkey).toString().replace("[", "").replace("]", "");
				//System.out.println(pubadscall_results);

				String[] pubadsresults = pubadscall_results.split(",");
				//////////////////////////////////////////
				for(int i=0;i<pubadsresults.length;i++){
					if(!pubads_call_results.get(pubkey).equals("nl")){
						if(api_call_results.get(pubkey).contains(pubadsresults[i])){
							System.out.println("Matched With "+ pubads_call_results.get(pubkey) +" :::: " + pubadsresults[i]);
						}
						else{
							System.out.println("Does Not Matched With "+ pubads_call_results.get(pubkey) +" :::: " + pubadsresults[i]);
							Assert.fail("Does Not Matched With "+ pubads_call_results.get(pubkey) +" :::: " + pubadsresults[i]);
						}
					}
					else{
						System.out.println("Getting nl value for "+pubkey+" from pubads call");
					}
				}
			}
		}
	}
	public static Map<String , String>  read_API_Call_Data(String excel_sheet_name) throws Exception{
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_map_results = new HashMap<String, String>();
		ArrayList<String> expected_Values_List = new ArrayList<String>();

		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);

		String validateValues = exceldata[11][Cap];
		String[] validate_Values = validateValues.split(",");


		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[2][Cap]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[3][Cap]));

		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+7,required_info.indexOf(exceldata[5][Cap]));
		String expectedValues = expected_data.toString();

		if(validate_Values.length == 1){

			if(expected_data.toString().contains(exceldata[11][Cap])){

				String expecteddata = expected_data.substring(expected_data.indexOf("[")+1,expected_data.indexOf("]")-1);
				System.out.println("Expected Data ::"+expecteddata);

				String[] expecteddata_into_arrays = expecteddata.split("},");
				String[] expectedValue = null;
				for(String dataKeys:expecteddata_into_arrays)
				{

					expectedValue =dataKeys.split(",");

					for(String ExpectedValuesKey:expectedValue)
					{
						if(ExpectedValuesKey.contains(exceldata[12][Cap]))
						{
							String replaceWith = ExpectedValuesKey.toString().replace("{", "").trim();

							String[] contentkey = replaceWith.toString().split(",");
							String expected_key = contentkey[0].replaceAll("^\"|\"$","");
							String[] contentvalue = expected_key.split(":");
							String expected_results =contentvalue[1].replaceFirst("^\"|\"$","");
							expected_Values_List.add(expected_results);
							if(expected_key.contains(""))
							{
								Assert.assertNotNull(expected_key);
							}
						}
					}
				}
			}
			expected_map_results.put(exceldata[12][Cap], expected_Values_List.toString());
		}
		else{

			String validateSecondValues = exceldata[12][Cap];
			String[] validate_Second_Values = validateSecondValues.split(",");
			List<String> fgeo_res = new ArrayList<String>();
			List<String> faud_res = new ArrayList<String>();

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(expectedValues);
			JSONObject jsonObject = (JSONObject) obj;

			JSONArray fgeoval = (JSONArray) jsonObject.get(validate_Values[0]);
			for(int i=0;i< fgeoval.size();i++){

				JSONObject filter = (JSONObject) fgeoval.get(i);
				if(filter.containsKey(validate_Second_Values[0])){
					fgeo_res.add(filter.get(validate_Second_Values[0]).toString());
				}
			}

			JSONArray faudval = (JSONArray) jsonObject.get(validate_Values[1]);
			for(int i=0;i< faudval.size();i++){

				JSONObject filter = (JSONObject) faudval.get(i);
				if(filter.containsKey(validate_Second_Values[1])){
					faud_res.add(filter.get(validate_Second_Values[1]).toString());
				}
			}

			expected_map_results.put("fgeo", fgeo_res.toString());
			expected_map_results.put("faud", faud_res.toString());
		}
		return expected_map_results;
	}

	public static Map<String , String> read_Pub_Ad_Call_Data(String excel_sheet_name) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_results = new HashMap<String, String>();

		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);

		String validateValues = exceldata[16][Cap];
		String[] validate_Values = validateValues.split(",");

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[17][Cap]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[7][Cap]));

		required_info= required_info.toString().replaceAll(exceldata[8][Cap], "=");
		required_info= required_info.toString().replaceAll(exceldata[9][Cap], "&");
		required_info= required_info.toString().replaceAll(exceldata[10][Cap], ",");

		required_info = required_info.substring(required_info.indexOf(exceldata[14][Cap]),required_info.indexOf(exceldata[15][Cap]));


		String pubad_cust_params_data = required_info.toString();

		String[] pubadvalue = pubad_cust_params_data.split(exceldata[13][Cap]);

		for(String pubadkey:pubadvalue){

			String[] key = pubadkey.split("=");

			for(int i=0;i<validate_Values.length;i++){	

				if(key[0].equals(validate_Values[i])){
					expected_results.put(validate_Values[i], key[1].toString());
				}
			}
		}
		return expected_results;
	}

	public static void clean_App_Launch(String excel_sheet_name) throws Exception{
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();


		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		String feedVal=exceldata[3][Cap].toString().trim();

		System.out.println("Feeds Val are :"+feedVal.trim());

		int feedcount=Integer.parseInt(feedVal);

		for(int Feed=0;Feed<=feedcount;Feed++){

			String pubadcal;

			if(Feed==0){
				pubadcal = sb.toString().substring(sb.toString().lastIndexOf(exceldata[1][Cap]));

				if(pubadcal.toString().contains(exceldata[1][Cap])){
					System.out.println("BB Ad call is pressent");
				}else{
					System.out.println("BB Ad call not presented");
					Assert.fail("BB Ad call not presented");
				}

			}
			else
			{
				String feedcall = exceldata[2][Cap]+Feed;

				pubadcal = sb.toString().substring(sb.toString().lastIndexOf(exceldata[2][Cap]+Feed));
				if(pubadcal.contains(feedcall)){
					System.out.println("Feed_"+Feed +" Ad call is pressent");
				}else{
					System.out.println("Feed_"+Feed +" Ad call is not pressent");
					Assert.fail();
				}

			}
		}
	}

	public static void bb_call_validation(String excel_sheet_name) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[17][Cap]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[17][Cap]));

		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[14][Cap]),required_info.indexOf(exceldata[15][Cap]));
		String expectedValues = expected_data.toString();

		System.out.println("BB Call Value is : "+expectedValues);
		if(expectedValues.contains(exceldata[17][Cap])){
			System.out.println("BB Call generated");
		}
		else{
			System.out.println("BB Call not generated");
			Assert.fail("BB Call not generated ");
		}
	}

	@SuppressWarnings("unchecked")
	public static void thirdParty_beacons_validation(String excel_sheet_name) throws Exception{
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Thread.sleep(4000);
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[2][Cap]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[2][Cap]));
		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[2][Cap]),required_info.indexOf(exceldata[3][Cap]));
		String expectedValues = expected_data.toString();

		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		String[] keypairs = expectedValues.split(exceldata[4][Cap]);

		for (String keyvalue : keypairs)
		{
			String[] key_value = keyvalue.split(exceldata[5][Cap]);
			map.put(key_value[0], key_value[1]);
		}

		if(!empty(map.get(exceldata[6][Cap])) && !empty(map.get(exceldata[7][Cap])) && !empty(map.get(exceldata[8][Cap]))){
			System.out.println(exceldata[6][Cap]+" Value is "+map.get(exceldata[6][Cap]));
			System.out.println(exceldata[7][Cap]+" Value is "+map.get(exceldata[7][Cap]));
			System.out.println(exceldata[8][Cap]+" Value is "+map.get(exceldata[8][Cap]));
		}
		else{
			System.out.println(exceldata[1][Cap] +" not available.");
			Assert.fail(exceldata[1][Cap] +" not available.");
		}

	}
	private static boolean empty(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	//	public static String get_pub_ad_call(int feed) throws Exception{
	//		
	//		DeviceStatus device_status = new DeviceStatus();
	//		int Cap = device_status.Device_Status();
	//		
	//		String expectedValues =null;
	//		String[][] exceldata = read_excel_data.exceldataread("AllFeeds");
	//		
	////		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	////		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//		
	//		for(int i=0;i<=10;i++){
	//		if(sb.toString().contains(exceldata[17][Cap]+feed)){
	//			
	//		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[17][Cap]+feed));
	//		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[7][Cap]));
	//		required_info= required_info.toString().replaceAll(exceldata[8][Cap], "=");
	//		required_info= required_info.toString().replaceAll(exceldata[9][Cap], "&");
	//		required_info= required_info.toString().replaceAll(exceldata[10][Cap], ",");
	//		
	//		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[14][Cap]),required_info.indexOf(exceldata[15][Cap]));
	//		expectedValues = expected_data.toString();
	//		}
	//		}
	//		return expectedValues;
	//	}
	//	

	public static String get_pub_ad_call(int feed) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		String expectedValues =null;
		String[][] exceldata = read_excel_data.exceldataread("AllFeeds");
		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//xml_data_into_buffer.read_xml_file_into_buffer_string("normal");
		//for(int i=0;i<=10;i++){
		String pubad = null;
		if(adType.equalsIgnoreCase("DetailsPages")) {
			pubad = exceldata[18][Cap].toString();
			pubad=pubad+Deatailpagead[feed];
		}else if(adType.equalsIgnoreCase("HomeScreen")){
			pubad = exceldata[17][Cap].toString();
			pubad=pubad+homescreenfeedad[feed];
		}
		if(sb.toString().contains(pubad)){

			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(pubad));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[7][Cap]));
			required_info= required_info.toString().replaceAll(exceldata[8][Cap], "=");
			required_info= required_info.toString().replaceAll(exceldata[9][Cap], "&");
			required_info= required_info.toString().replaceAll(exceldata[10][Cap], ",");

			String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[14][Cap]),required_info.indexOf(exceldata[15][Cap]));
			expectedValues = expected_data.toString();
		}
		//	}
		return expectedValues;
	}



	public static void validate_CXTG_values(String excel_sheet_name) throws Exception{

		Map<String, String> cxtg_res = get_wfxtriggers_call(excel_sheet_name);
		Map<String, String> pubad_res = null;
		List<String> cxtg_not_match = new ArrayList<String>();
		String finalval=null;
		boolean isExceptionOccered = false;
		Set<String> keys = cxtg_res.keySet();
		for (String key : keys) {
			pubad_res = get_pubad_call_by_zip(excel_sheet_name,"zip%3D"+key);
			finalval = cxtg_res.get(key).substring(1, cxtg_res.get(key).length() -1);
			System.out.println("CXTG Zip:::"+key+" CXTG Value :::"+finalval);
			System.out.println("Pub Zip:::"+pubad_res.get("zip")+" CXTG Value :::"+pubad_res.get("cxtg"));
			try {
				Assert.assertEquals(pubad_res.get("cxtg"),finalval);
			} catch (ComparisonFailure e) {
				System.out.println(key + " Doesn't Match");
				cxtg_not_match.add(key);
				isExceptionOccered= true;
			}
			if(isExceptionOccered){
				System.out.println(cxtg_not_match);
				Assert.fail(cxtg_not_match + " are not matched");
			}
		}
	}

	public static Map<String, String> get_wfxtriggers_call(String excel_sheet_name) throws Exception{

		Map<String , String> wfxtriggers_values = new HashMap<String, String>();
		String wxtgValues="";

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);

		String jsonValues = exceldata[11][Cap];
		String[] json_Values = jsonValues.split(",");

		String validateValues = exceldata[16][Cap];
		String[] validate_Values = validateValues.split(",");

		/* --- Start JSON Parser for wfxtg Values --- */


		//			read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//			String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[2][Cap]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[3][Cap]));

		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+7,required_info.indexOf(exceldata[5][Cap]));
		wxtgValues = expected_data.toString();

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(wxtgValues);
		JSONObject jsonObject = (JSONObject) obj;
		JSONObject wfxtgval = (JSONObject) jsonObject.get(json_Values[0]);
		JSONArray scatterSegsVal = (JSONArray) wfxtgval.get(json_Values[1]); 

		/* --- Start For Loop Main JSON Parser --- */
		for(int i=0;i< scatterSegsVal.size();i++){

			JSONObject zcsVal = (JSONObject) scatterSegsVal.get(i);
			/* --- Start Key Pair Contains ZCS --- */
			if(zcsVal.containsKey(exceldata[12][Cap])){
				JSONArray jsonArray = (JSONArray) zcsVal.get(exceldata[12][Cap]);
				/* --- Start ZCS contains multipul ZIP Values --- */
				for(int j=0;j< jsonArray.size();j++){
					JSONObject zipval = (JSONObject) jsonArray.get(j);
					/* --- Start Key Pair Contains ZIP --- */
					if(zipval.containsKey(validate_Values[0])){
						wfxtriggers_values.put(zipval.get(validate_Values[0]).toString(), zipval.get(validate_Values[1]).toString());
					}/* --- End Key Pair Contains ZIP --- */

				}/* --- End ZCS contains multipul ZIP Values --- */

			}/* --- End Key Pair Contains ZCS --- */

		}/* --- End For Loop Main JSON Parser --- */
		return wfxtriggers_values;
	}

	public static Map<String, String> get_pubad_call_by_zip(String excel_sheet_name,String Zip) throws Exception{

		Map<String , String> cxtg_values = new HashMap<String, String>();
		String cxtgValues="";

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);

		String validateValues = exceldata[16][Cap];
		String[] validate_Values = validateValues.split(",");
		/* --- Start JSON Parser for wfxtg Values --- */
		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(Zip));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(Zip));
		String expected_data = required_info.toString().substring(required_info.indexOf(Zip),required_info.indexOf(exceldata[15][Cap]));
		expected_data= expected_data.toString().replaceAll(exceldata[8][Cap], "=");
		expected_data= expected_data.toString().replaceAll(exceldata[9][Cap], "&");
		expected_data= expected_data.toString().replaceAll(exceldata[10][Cap], ",");
		cxtgValues = expected_data.toString();

		String[] arrays = cxtgValues.split("&");
		for(String keys : arrays){
			if(keys.contains("=")){
				String[] key = keys.split("=");
				if(key[0].equals(validate_Values[0])){
					cxtg_values.put(key[0], key[1]);
				}
				if(key[0].equals(validate_Values[1])){
					cxtg_values.put(key[0], key[1]);
				}
			}
		}
		return cxtg_values;
	}

	public static void verifySavedAddressList() throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		/* --- Start For Android Device --- */
		if(Cap == 2){
			String[][] addressdata = read_excel_data.exceldataread("AddressPage");

			WebDriverWait wait4 = new WebDriverWait(Ad, 10);
			wait4.until(ExpectedConditions.presenceOfElementLocated(By.id(addressdata[4][Cap])));

			//Root Location Element
			Ad.findElementById(addressdata[4][Cap]).click();

			WebDriverWait wait5 = new WebDriverWait(Ad, 20);
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.id(addressdata[6][Cap])));

			//List Location Element
			@SuppressWarnings("unchecked")
			List<MobileElement> loclist = Ad.findElements(By.id(addressdata[6][Cap]));

			int loc_size = loclist.size() -1;

			String loc_length = Integer.toString(loc_size);

			System.out.println("Total Saved Address List :::::" + loc_length);

			Thread.sleep(2000);

			System.out.println("Start Select Address List");

			String firsteleXpath = addressdata[5][Cap];
			String[] parts = firsteleXpath.split("Count");
			/* --- Start For Loop For Location Click --- */
			for(int i=2;i<= loclist.size();i++){

				String element = null;

				try {

					element = parts[0]+i+parts[1];

					MobileElement ele = (MobileElement) Ad.findElementByXPath(element);
					System.out.println("For This Location ====>"+ele.getText());

					WebDriverWait wait9 = new WebDriverWait(Ad, 20);
					wait9.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));

					Ad.findElementByXPath(element).click();

					WebDriverWait wait10 = new WebDriverWait(Ad, 20);
					wait10.until(ExpectedConditions.presenceOfElementLocated(By.id(addressdata[4][Cap])));

					Ad.findElementById(addressdata[4][Cap]).click();
				} catch (Exception e) {

					System.out.println(element+" is not found in the location list");
				}
			}/* --- End For Loop For Location Click --- */

			Thread.sleep(8000);

			WebDriverWait wait12 = new WebDriverWait(Ad, 10);
			wait12.until(ExpectedConditions.presenceOfElementLocated(By.xpath(parts[0]+1+parts[1])));

			Ad.findElementByXPath(parts[0]+1+parts[1]).click();
		}/* --- End For Android Device --- */
		System.out.println("End Select Address List");
	}

	public static void CleanLaunch_launch(String excel_sheet_name) throws Exception
	{
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);

		for(int i=1;i<=2 ;i++){
			Thread.sleep(2000);
			Swipe();
			Thread.sleep(2000);
		}

		int MAX_SWIPES = 10;

		for (int j = 0; j < MAX_SWIPES; j++) {

			MobileElement module = null;

			try {

				WebDriverWait wait0 = new WebDriverWait(Ad, 10);
				wait0.until(ExpectedConditions.visibilityOf(Ad.findElementByXPath(exceldata[1][Cap])));
				module = (MobileElement) Ad.findElementByXPath(exceldata[1][Cap]);


			} catch (Exception e) {
				// System.out.println(e);
			}


			if (module!=null && module.isDisplayed()) {
				System.out.println("Last module is present");
				Swipe();
				break;
			} 
			else {
				System.out.println("Last module is NOT present,scrolling down");
				Swipe();
			}
		}
	}

	public static void verify_Vedio_Module_Click_On_Forecast_Video(String excel_sheet_name) throws Exception{

		System.out.println("Searching for Video module");
		Thread.sleep(5000);
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		int swipe = 4;
		//Integer.parseInt(exceldata[2][Cap]);

		for(int i=1;i<=1 ;i++){
			Swipe();
			Thread.sleep(1000);
		}

		int MAX_SWIPES = 5;

		for (int i = 0; i<MAX_SWIPES; i++) {

			MobileElement video = null;

			try {
				Ad.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				//WebDriverWait wait0 = new WebDriverWait(Ad, 10);
				//wait0.until(ExpectedConditions.visibilityOf(Ad.findElementById(exceldata[5][Cap])));
				video = (MobileElement) Ad.findElementById(exceldata[5][Cap]);

			} catch (Exception e) {
				// System.out.println("Exception message :: "+e);	
			}

			if(video!=null && video.isDisplayed())
			{  
				System.out.println("Video module is present ");
				Ad.findElementById(exceldata[5][Cap]).click();
				Thread.sleep(5000);
				Ad.findElementByClassName(exceldata[6][Cap]).click();
				Thread.sleep(2000);
				break;
			}else
			{
				System.out.println("Video module is NOT present and scrolling down");
				Swipe();
			}
		}
	}








	/*public static void Swipe(){
		Dimension dimensions = Ad.manage().window().getSize();
		Double startY1 = dimensions.getHeight() * 0.7;  
		startY = startY1.intValue();
		Double endY1 = (double) (dimensions.getHeight()/40);  //  dimensions.getHeight()  0.2;  == 512.0
		endY = endY1.intValue();
		Ad.swipe(0, startY, 0, endY,2000);
	}*/
	public static void Swipe() throws Exception{
		Dimension dimensions = Ad.manage().window().getSize();//throwing exception

		Double startY1 = dimensions.getHeight() * 0.8;  
		startY = startY1.intValue();
		Double endY1 = (double) (dimensions.getHeight()/40);  //  dimensions.getHeight()  0.2;  == 512.0
		endY = endY1.intValue();
		Thread.sleep(5000);
		Ad.swipe(0, startY, 0, endY,2000);
		//Ad.swipe(startx, starty, endx, endy, duration);

	}

	public static Map<String , String> read_Video_Pub_Ad_Call_Data(String excel_sheet_name) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_results = new HashMap<String, String>();

		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);

		String validateValues = exceldata[16][Cap];
		String[] validate_Values = validateValues.split(",");

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		try {

			if(sb.toString().contains(exceldata[17][Cap])){
				String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[17][Cap]));
				String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[7][Cap]));

				required_info= required_info.toString().replaceAll(exceldata[8][Cap], "=");
				required_info= required_info.toString().replaceAll(exceldata[9][Cap], "&");
				required_info= required_info.toString().replaceAll(exceldata[10][Cap], ",");
				required_info = required_info.substring(required_info.indexOf(exceldata[14][Cap]),required_info.indexOf(exceldata[15][Cap]));
				String pubad_cust_params_data = required_info.toString();
				String[] pubadvalue = pubad_cust_params_data.split(exceldata[13][Cap]);

				for(String pubadkey:pubadvalue){

					String[] key = pubadkey.split("=");
					for(int i=0;i<validate_Values.length;i++){	

						if(key[0].equals(validate_Values[i])){
							expected_results.put(validate_Values[i], key[1].toString());
						}
					}
				}
				expected_results.put("iu",exceldata[17][Cap]);
			}
		} catch (Exception e) {
			System.out.println("daily deatils Pub Ad Call Not Generated. Ex : "+exceldata[17][Cap]);
			Assert.fail("daily deatils Pub Ad Call Not Generated. Ex : "+exceldata[17][Cap]);
		}

		return expected_results;
	}
	
	public static Map<String , String> read_Video_Pub_Ad_Call_request(String excel_sheet_name) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_results = new HashMap<String, String>();

		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);

		String validateValues = exceldata[16][Cap];
		String[] validate_Values = validateValues.split(",");

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		try {

			if(sb.toString().contains(exceldata[17][Cap])){
				String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[17][Cap]));
				String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[7][Cap]));

				required_info= required_info.toString().replaceAll(exceldata[8][Cap], "/");
			required_info= required_info.toString().replaceAll(exceldata[9][Cap], ":");
		//		required_info= required_info.toString().replaceAll(exceldata[10][Cap], ",");
				required_info = required_info.substring(required_info.indexOf(exceldata[14][Cap]),required_info.indexOf(exceldata[15][Cap]));
				String pubad_cust_params_data = required_info.toString();
				String[] pubadvalue = pubad_cust_params_data.split(exceldata[13][Cap]);

				for(String pubadkey:pubadvalue){

					String[] key = pubadkey.split("=");
					for(int i=0;i<validate_Values.length;i++){	

						if(key[0].equals(validate_Values[i])){
							expected_results.put(validate_Values[i], key[1].toString());
						}
					}
				}
				expected_results.put("iu",exceldata[17][Cap]);
			}
		} catch (Exception e) {
			System.out.println("Video Pub Ad Call Not Generated. Ex : "+exceldata[17][Cap]);
			Assert.fail("Video Pub Ad Call Not Generated. Ex : "+exceldata[17][Cap]);
		}

		return expected_results;
	}


	public static void verify_Road_Conditions(String excel_sheet_name) throws Exception{

		System.out.println("Searching for Road Conditions");

		Thread.sleep(5000);
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		int swipe = Integer.parseInt(exceldata[2][Cap]);

		for(int i=1;i<=swipe ;i++){
			Swipe();
			Thread.sleep(1000);
		}

		int MAX_SWIPES = 5;

		for (int i = 0; i<MAX_SWIPES; i++) {

			MobileElement roads = null;

			try {
				WebDriverWait wait0 = new WebDriverWait(Ad, 10);
				wait0.until(ExpectedConditions.visibilityOf(Ad.findElementByName(exceldata[1][Cap])));
				roads = (MobileElement) Ad.findElementByName(exceldata[1][Cap]);


			} catch (Exception e) {
				// System.out.println("Exception message :: "+e);	
			}

			if(roads!=null && roads.isDisplayed())
			{  
				System.out.println("Road Conditions module is present ");
				Ad.findElementByName(exceldata[1][Cap]).click();
				Thread.sleep(2000);
				if(Ad.findElementByXPath(exceldata[5][Cap]).isDisplayed()){
					Thread.sleep(2000);
					Ad.findElementByXPath(exceldata[5][Cap]).click();
				}
				Thread.sleep(2000);
				Ad.findElementByClassName(exceldata[3][Cap]).click();
				Thread.sleep(2000);
				break;
			}else
			{
				System.out.println("Road Conditions is NOT present and scrolling down");
				Swipe();
			}
		}
	}

	
	
	public static void get_feed1() throws Exception {
		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		System.out.println("Verifying iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call");
		logStep("Verifying iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call");
		//System.out.println("Slot Name is  : "+slotID);
		if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1")) {
		System.out.println("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call was trigred");
		logStep("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call was trigred");
		}
		if(!sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1")) {
			System.out.println("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call was not trigred");
			logStep("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call was not trigred");
			Assert.fail("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call was not trigred");
			
			}
	}

	public static Map<String , String> ddi_validation(String excel_sheet_name) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_results = new HashMap<String, String>();

		Thread.sleep(4000);
		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);
		@SuppressWarnings("unused")
		Map<String, String> map = new HashMap<String, String>();

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		try {

			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[15][Cap]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[15][Cap]));
			String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[13][Cap]),required_info.indexOf(exceldata[14][Cap]));
			expected_data= expected_data.toString().replaceAll(exceldata[7][Cap], "=");
			expected_data= expected_data.toString().replaceAll(exceldata[8][Cap], "&");
			expected_data= expected_data.toString().replaceAll(exceldata[9][Cap], ",");

			String expectedValues = expected_data.toString();

			String[] keypairs = expectedValues.split(exceldata[12][Cap]);

			for (String keyvalue : keypairs)
			{
				if(keyvalue.contains("=")){
					String[] key_value = keyvalue.split(exceldata[11][Cap]);
					if(key_value[0].contains(exceldata[16][Cap])){
						expected_results.put(key_value[0], key_value[1]);
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(exceldata[15][Cap] + " Value Not Generated");
			Assert.fail(exceldata[15][Cap] + " Value Not Generated");
		}

		return expected_results;

	}
	public static void SwipeUp_Counter_lifestyle_coldflumodule() throws Exception{
		System.out.println("Searching for cold&flumodule  ");

		//int swipeup = Counter;

		for(int i=1;i<=7 ;i++){

			Swipe();

			Boolean b=verifyElement(By.id("com.weather.Weather:id/combo_item_container"));
			//Ad.findElementByName("HEALTH & ACTIVITIES").getAttribute("")
			//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
			//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
			if(b==true)
			{
				try {
					Ad.findElementById("com.weather.Weather:id/combo_item_container").click();
					Thread.sleep(1000);
					for(int j=1;j<=3 ;j++){

						Swipe();
					}
				}
				catch(Exception e)
				{					
					Ad.findElementById("Ccom.weather.Weather:id/combo_module_arrow_text").click();
					Thread.sleep(4000);
					for(int j=1;j<=3 ;j++){
						Swipe();
					}
				}

				//AppiumFunctions.Check_Lifestyle_Module_ad();
				//Ad.findElementByClassName("android.widget.ImageButton").click();
				Thread.sleep(5000);				
				break;
			}
			else
			{
				System.out.println("Module is not present scroll down");
			}



		}
	}
	public static void SwipeUp_Counter_lifestyle_allergymodule() throws Exception{
		System.out.println("Searching for cold&flumodule  ");
		Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout"));
		//Ad.findElementByName("HEALTH & ACTIVITIES").getAttribute("")
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		if(b==true)
		{
			try {
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
				for(int j=1;j<=3 ;j++){

					Swipe();
				}

			}
			catch(Exception e)
			{					
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
			}
		}


		else
		{
			System.out.println("Module is not present scroll down");
		}


	}

	public static void SwipeUp_Counter_lifestyle_boatBeachmodule() throws Exception{
		System.out.println("Searching for Boat&beach module  ");
		Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout"));

		//Ad.findElementByName("HEALTH & ACTIVITIES").getAttribute("")
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		if(b==true)
		{
			try {
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
				for(int j=1;j<=3 ;j++){

					Swipe();
				}
			}
			catch(Exception e)
			{					
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
			}
		}


		else
		{
			System.out.println("Module is not present scroll down");
		}


	}
	public static void SwipeUp_Counter_lifestyle_goRunmodule() throws Exception{
		System.out.println("Searching for GoRun module  ");
		Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout"));
		//Ad.findElementByName("HEALTH & ACTIVITIES").getAttribute("")
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		if(b==true)
		{
			try {
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
				for(int j=1;j<=3 ;j++){

					Swipe();
				}
			}
			catch(Exception e)
			{					
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
			}
		}


		else
		{
			System.out.println("Module is not present scroll down");
		}


	}
	public static void SwipeUp_Counter_lifestyle_skimodule() throws Exception{
		System.out.println("Searching for cold&flumodule  ");
		Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout"));
		//Ad.findElementByName("HEALTH & ACTIVITIES").getAttribute("")
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		if(b==true)
		{
			try {
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
			}
			catch(Exception e)
			{					
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
			}
		}


		else
		{
			System.out.println("Module is not present scroll down");
		}


	}

	public static void Clickon_Back_Button() throws Exception 
	{
		try{
			logStep("Clicking on backbuton");
			Ad.findElementByClassName("android.widget.ImageButton").click();
			Thread.sleep(3000);
		}
		catch(Exception e )
		{
			logStep("Clicking on backbuton");
			Ad.findElementByClassName("android.widget.ImageButton").click();
			Thread.sleep(3000);
		}
	}

	public static void SwipeUp_Counter_hourly_submodules() throws Exception{

		//int swipeup = Counter;

		for(int i=1;i<=7 ;i++){

			Swipe();


			Boolean b=verifyElement(By.id("com.weather.Weather:id/hourly_more"));
			if(b==true)
			{
				logStep("Hourly page is presented on the screen");		
				Ad.findElementById("com.weather.Weather:id/hourly_more").click();
				logStep("clicked the hourly page link");
				Ad.findElementByClassName("android.widget.ImageButton").click();
				Thread.sleep(5000);


				break;
			}
			else
			{
				System.out.println("Module is not present scroll down");
			}



		}
	}
	public static void SwipeUp_Counter_Daily_submodule() throws Exception{

		//int swipeup = Counter;

		for(int i=1;i<=7 ;i++){

			Swipe();

			Boolean b=verifyElement(By.id("com.weather.Weather:id/daily_more"));
			if(b==true)
			{
				logStep("Daily page is presented on the screen");
				Ad.findElementById("com.weather.Weather:id/daily_more").click();
				logStep("clicked the Daily page link");
				Ad.findElementByClassName("android.widget.ImageButton").click();
				Thread.sleep(5000);				
				break;
			}
			else
			{
				System.out.println("Module is not present scroll down");
			}


		}
	}

	public static void SwipeUp_Counter_Maps_submodule() throws Exception{

		//int swipeup = Counter;

		for(int i=1;i<=7 ;i++){

			//Swipe();

			Boolean b=verifyElement(By.id("com.weather.Weather:id/map_module_title"));

			if(b==true)
			{
				logStep("Maps page is presented on the screen");
				try
				{

					Ad.findElementById("com.weather.Weather:id/map_module_thumbnail").click();
					logStep("clicked the Daily page link");
				}
				catch(Exception e)
				{
					Ad.findElementById("com.weather.Weather:id/map_module_more").click();
					logStep("clicked the Daily page link");
				}
				Ad.findElementByClassName("android.widget.ImageButton").click();
				Thread.sleep(5000);				
				break;
			}
			else
			{
				System.out.println("Module is not present scroll down");
			}



		}
	}
	public static void SwipeUp_Counter_news_submodules() throws Exception{

		//int swipeup = Counter;

		for(int i=1;i<=12 ;i++){

			Swipe();


			Boolean b=verifyElement(By.id("com.weather.Weather:id/news_title"));
			if(b==true)
			{
				Ad.findElementById("com.weather.Weather:id/news_grid_item_0").click();
				Thread.sleep(5000);
				//Ad.findElementByClassName("android.widget.ImageButton").click();
				//Thread.sleep(5000);
				break;
			}
			else
			{
				System.out.println("Module is not present scroll down");
			}



		}
	}

	public static void Change_to_Test_Mode(String excel_sheet_name) throws Exception{

		logStep("Make Ads As Test From Test Mode Settings In Order To Get BB Ad Call");
		logStep("TestMode Settings: 1) Click On Menu Button 2) Click On Settings 3) Click On About This App 4) Click 10 Times On App Version 5) TestMode Setting Enabled 6) Click On TestMode Settings 7) Click On Ads");


		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		String[][] exceldata = read_excel_data.exceldataread(excel_sheet_name);

		WebDriverWait wait = new WebDriverWait(Ad, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className(exceldata[2][Cap])));//settings button

		MobileElement menu = (MobileElement) Ad.findElement(By.className(exceldata[2][Cap]));
		menu.click();
		System.out.println("clicking on Menu option");
		Thread.sleep(4000);
		try {
			Ad.findElementByName(exceldata[5][Cap]).click(); 
		}
		catch(Exception e)
		{
			List<MobileElement> sett=	Ad.findElementsById("com.weather.Weather:id/design_menu_item_text");
			sett.get(1).click();
		}
		System.out.println("clicking on settings option");
		Thread.sleep(4000);
		try {
			//Ad.findElementByName(exceldata[6][Cap]).click();;//about this app
			Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[6]/android.widget.RelativeLayout/android.widget.TextView").click();
		}

		catch(Exception e)
		{
			List<MobileElement> aboutelem = Ad.findElementsById("android:id/title");
			System.out.println("elements in setting page"+aboutelem);
			aboutelem.get(1).click();
		}
		//aboutThisAPP.click();
		System.out.println("clicking on about this app option");
		System.out.println("tapping continously to get test mode option");	
		for (int i=1; i<=8; i++){
			Ad.findElementById(exceldata[18][Cap]).click();
		}
		Thread.sleep(4000);
		try {
			Ad.findElementByName(exceldata[19][Cap]).click();
		}
		catch(Exception e)
		{
			Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[2]").click();
		}
		System.out.println("clicking on test mode settings");	


		try {
			Ad.findElementByName("Airlock").click();
		}
		catch(Exception e)
		{
			Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[7]/android.widget.RelativeLayout/android.widget.TextView").click();
		}
		try {
			Ad.findElementByName("User Groups").click();
		}
		catch(Exception e)
		{
			Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[1]").click();
		}
		Ad.findElementById("com.weather.Weather:id/search_bar").sendKeys("11089");
		Thread.sleep(4000);
		try {
			Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.CheckedTextView[1]").click();
		}
		catch(Exception e)
		{
			Ad.findElementById("android:id/text1").click();
		}
		Thread.sleep(5000);
		///hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.CheckedTextView[1]
	}
	public static void Kill_launch() throws Exception{
		try{
		
			Ad.closeApp();		
			Ad.launchApp();
			After_launch();
		}catch(Exception e){

			try {

				Ad.closeApp();		
				Ad.launchApp();
				After_launch();
			}
			catch(Exception e1) {
				
			}
		}
	}

	public static void After_launch(){
		try{
			
        	AppiumFunctions.clickONNext();
        	AppiumFunctions.ClickonIUnderstand();
        	AppiumFunctions.clickOnAllow();
		}catch(Exception e){
			
		}

	}
	public static void scroll_onelement_to_otherelement() throws Exception{
		TouchAction touchAction = new TouchAction(Ad);

		MobileElement ColdFlu=(MobileElement) Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout");
		MobileElement BoatBeach=(MobileElement) Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout");
		Point ColdFluPoint = ColdFlu.getLocation();
		Point BoatBeachPoint =BoatBeach.getLocation();

		int ColdFluX=ColdFluPoint.getX();
		int ColdFluY=ColdFluPoint.getY();
		ColdFluX=ColdFluX+40;
		int BoatBeachX=BoatBeachPoint.getX();
		int BoatBeachY=BoatBeachPoint.getY();

		Ad.swipe(BoatBeachX, BoatBeachY, ColdFluX, ColdFluY, 4000);


	}
	public static void scroll_onelement_to_otherelement1() throws Exception{


		MobileElement ColdFlu=(MobileElement) Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout");
		MobileElement BoatBeach=(MobileElement) Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout");
		Point ColdFluPoint = ColdFlu.getLocation();
		Point BoatBeachPoint =BoatBeach.getLocation();

		int ColdFluX=ColdFluPoint.getX();
		int ColdFluY=ColdFluPoint.getY();
		ColdFluX=ColdFluX+40;
		int BoatBeachX=BoatBeachPoint.getX();
		int BoatBeachY=BoatBeachPoint.getY();

		Ad.swipe(BoatBeachX, BoatBeachY, ColdFluX, ColdFluY, 4000);


	}
	public static void get_aaxcals() throws Exception {
		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		xml_data_into_buffer.read_xml_file_into_buffer_string("aax");
		String slotID =null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM");  
		LocalDateTime now = LocalDateTime.now();  
		String date = dtf.format(now);
		System.out.println("date is : "+date);
		//Write_result wrResult2 = new Write_result();
		write_excel_data wrResult2= new write_excel_data();
		read_excel_data.exceldataread_Custom_Parameters("aaxCals","Ad Slots_Android");
		try {

			for(int j=1;j<=read_excel_data.rowCount;j++) {
				wrResult2.writeResult("Ad Slots_Android","-",j,9);
			}
			for(String aaxSlot : aaxSlots) {
				//				aaxSlot=aaxSlot.toString().trim().replaceAll("<body><![CDATA[", "");
				//				aaxSlot=aaxSlot.toString().trim().replaceAll("]]></body></request>", "");
				aaxSlot = aaxSlot.toString().substring(aaxSlot.indexOf("[CDATA")+7,aaxSlot.indexOf("]]></body></request>"));
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(new String(aaxSlot.toString()));
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray jArray = (JSONArray) jsonObject.get("slots");
				jsonObject =(JSONObject) jArray.get(0);
				slotID=jsonObject.get("slot").toString();
				//System.out.println("Slot Name is  : "+slotID);

				//readExcelValues.excelValues("AdUnits","Ad Slots_iPhone");
				String[][] exceldata = read_excel_data.exceldataread_Custom_Parameters("aaxCals", "Ad Slots_Android");

				for(int i=1;i<=read_excel_data.rowCount;i++) {
					if(slotID.equals(exceldata[i][8].toString().trim())) {
						System.out.println("slot id mached from for"+exceldata[i][5] +" is : "+exceldata[i][8] + "----"+slotID);
						wrResult2.writeResult("Ad Slots_Android","Passed"+date,i,9);

						break;
					}else {
						if(i==read_excel_data.rowCount) {
							System.out.println("Slot Id not Matched "+slotID );

						}
					}
				}

			}

		}catch(Exception e) {

		}
	}

	public static void SwipeUp_Counter_lifestyle_skidetailßmodule() throws Exception{
		System.out.println("Searching for GoRun module  ");
		Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout"));
		//Ad.findElementByName("HEALTH & ACTIVITIES").getAttribute("")
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		if(b==true)
		{
			try {
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
			}
			catch(Exception e)
			{					
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
			}
		}


		else
		{
			System.out.println("Module is not present scroll down");
		}


	}

	public static void SwipeUp_Counter_lifestyle_skiresortsmodule() throws Exception{
		System.out.println("Searching for GoRun module  ");
		Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout"));
		//Ad.findElementByName("HEALTH & ACTIVITIES").getAttribute("")
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		//Boolean b=verifyElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView"));
		if(b==true)
		{
			try {
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
			}
			catch(Exception e)
			{					
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout").click();
				Thread.sleep(5000);	
			}
		}


		else
		{
			System.out.println("Module is not present scroll down");
		}


	}
	
	public static void selectCurrentLocationOnPushNotifications()  {
		try {
			if(Ad.findElementById("android:id/button1").isDisplayed()) {
		if(Ad.findElementById("android:id/button1").getText().contains("SELECT LOCATION"));
		Ad.findElementById("android:id/button1").click();
			}
		
		}
		catch(Exception e) {
			
					Ad.findElementById("com.weather.Weather:id/checkbox").click();
					System.out.println("enabled the checkbox");
				}
			
	}
	public static void clickOnNotificationsBellIcon() throws Exception  {
		System.out.println("Clicking alerts notification Icon");
		logStep("Clicking alerts notification Icon");
		try {
			Ad.findElementByAccessibilityId("Go to Alerts and Notifications").click();
			Thread.sleep(5000);
		}
		catch(Exception e) {
			Ad.findElementById("com.weather.Weather:id/notifications_icon").click();
			Thread.sleep(5000);
		}
	}
	
	public static void clickOnManage() throws Exception  {
		System.out.println("Clicking on Manage button");
		logStep("Clicking on Manage button");
		try {
			Ad.findElementByAccessibilityId("Manage").click();
			Thread.sleep(5000);
		}
		catch(Exception e) {
			Ad.findElementByAccessibilityId("Manage").click();
			Thread.sleep(5000);
		}
	}
	public static void enableSignificantweatherforecast_Notifications()  throws Exception{
		 clickOnNotificationsBellIcon();
		 clickOnManage();
			Thread.sleep(10000);
			
			Ad.findElementById("com.weather.Weather:id/my_alerts_layout_0").click();
			//Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.ScrollView/android.widget.LinearLayout/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.TextView[1]").click();
			Thread.sleep(15000);
		/*	List<WebElement> notifications;
			

				Thread.sleep(5000);
				try {
					notifications=Ad.findElementsById("com.weather.Weather:id/my_alerts_layout_0");
				}
				catch(Exception e) {
					notifications=Ad.findElementsById("com.weather.Weather:id/my_alerts_layout_0");
				}
			
		for(WebElement weather:notifications) {	
			Thread.sleep(6000);
			if(weather.getText().equalsIgnoreCase("Significant weather forecast")) {
				Thread.sleep(6000);
				weather.click();*/
			   enbleAlertSwitch();
			selectCurrentLocationOnPushNotifications();
			clickBackButtonAlerts();
			clickBackButtonAlerts();
			}			
			
	

	
	
	
	public static void enable_Alert_Notifications(String Notification)  throws Exception{
		 clickOnNotificationsBellIcon();
		 clickOnManage();
			Thread.sleep(2000);
		//	Ad.findElementById("com.weather.Weather:id/my_alerts_layout_3").click();
			//Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.ScrollView[3]/android.widget.LinearLayout/android.widget.ListView/android.widget.RelativeLayout[4]/android.widget.TextView[1]").click();
			Thread.sleep(6000);
			List<WebElement> notifications;
			
				Thread.sleep(5000);
				notifications=Ad.findElementsById("android.widget.TextView");
		for(WebElement weather:notifications) {	
			Thread.sleep(6000);
			if(weather.getText().equalsIgnoreCase(Notification)) {
				Thread.sleep(6000);
				//System.out.println(weather.getAttribute("text"));
				weather.click();
				Thread.sleep(6000);
				enbleAlertSwitch();
				clickBackButtonAlerts();
				clickBackButtonAlerts();
			}			
		}
	}
	
	public static void enable_Alert_Notifications_bn(String Notification)  throws Exception{
		 clickOnNotificationsBellIcon();
		 clickOnManage();
			Thread.sleep(6000);
			//Ad.findElementById("com.weather.Weather:id/my_alerts_layout_5").click();
			Ad.findElementByXPath("\"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.ScrollView[4]/android.widget.LinearLayout/android.widget.ListView/android.widget.RelativeLayout[6]/android.widget.TextView[1]").click();
			Thread.sleep(6000);
			/*List<WebElement> notifications;
			
				Thread.sleep(5000);
				notifications=Ad.findElementsById("com.weather.Weather:id/my_alerts_layout_5");
		for(WebElement weather:notifications) {	
			Thread.sleep(6000);
			if(weather.getText().equalsIgnoreCase(Notification)) {
				Thread.sleep(6000);
				//System.out.println(weather.getAttribute("text"));
				weather.click();
				Thread.sleep(6000);*/
				enbleAlertSwitch();
				clickBackButtonAlerts();
				clickBackButtonAlerts();
			}			
			
	
	
	public static void enable_Alert_Notifications_rtr(String Notification)  throws Exception{
		 clickOnNotificationsBellIcon();
		 clickOnManage();
			Thread.sleep(6000);
	//Ad.findElementById("com.weather.Weather:id/my_alerts_layout_2").click();
			Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.ScrollView[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.RelativeLayout[3]/android.widget.TextView[1]").click();
			Thread.sleep(6000);
			/*Thread.sleep(2000);
			List<WebElement> notifications;
			
				Thread.sleep(5000);
				notifications=Ad.findElementsById("com.weather.Weather:id/my_alerts_layout_2");
		for(WebElement weather:notifications) {	
			Thread.sleep(6000);
			if(weather.getText().equalsIgnoreCase(Notification)) {
				Thread.sleep(6000);
				//System.out.println(weather.getAttribute("text"));
				weather.click();*/
				Thread.sleep(6000);
				enbleAlertSwitch();
				clickBackButtonAlerts();
				clickBackButtonAlerts();
			}			
			
	
	public static void push() throws Exception {
		// selectCurrentLocationOnPushNotifications();
	
		
			clickBackButtonAlerts();
		
	}
	
	
	public static void enablePushalerts()  throws Exception{
		
		//clicking menu button
		//click the view more
				try {
				Ad.findElementByAccessibilityId("Go to Alerts and Notifications").click();
				Thread.sleep(2000);
				}
				catch(Exception e) {
					Ad.findElementById("com.weather.Weather:id/notifications_icon").click();
					Thread.sleep(3000);
				}
				//click manage button
				
					Ad.findElementByAccessibilityId("Manage").click();
					Thread.sleep(2000);
					
			//click significant forecast alert
					try {
						Ad.findElementById("com.weather.Weather:id/my_alerts_layout_0").click();
						Thread.sleep(2000);
						}
						catch(Exception e) {
							
								List<WebElement> airlock=Ad.findElementsByClassName("android.widget.RelativeLayout");
								airlock.get(0).click();
								Thread.sleep(3000);
						}		
					enbleAlertSwitch();
					
					
					Ad.findElementById("android:id/button1").click();
					
					Thread.sleep(3000);
					
					
				/*	try {
						Ad.findElementById("com.weather.Weather:id/checkbox").click();
						Thread.sleep(2000);
						}
						catch(Exception e) {
							
								Ad.findElementByClassName("android.widget.TextView").click();
								Thread.sleep(3000);
						}	*/
					clickBackButtonAlerts();
					//enable lighting alerts
					try {
						Ad.findElementById("com.weather.Weather:id/my_alerts_layout_3").click();
						Thread.sleep(2000);
						}
						catch(Exception e) {
							
								List<WebElement> airlock=Ad.findElementsByClassName("android.widget.RelativeLayout");
								airlock.get(3).click();
								Thread.sleep(3000);
						}	
					enbleAlertSwitch();
					clickBackButtonAlerts();
					
					//enable daily rain &Snow  alerts
					try {
						Ad.findElementById("com.weather.Weather:id/my_alerts_layout_6").click();
						Thread.sleep(2000);
						}
						catch(Exception e) {
							
								List<WebElement> airlock=Ad.findElementsByClassName("android.widget.RelativeLayout");
								airlock.get(6).click();
								Thread.sleep(3000);
						}	
					enbleAlertSwitch();
					Ad.findElementById("com.weather.Weather:id/checkbox").click();
					Thread.sleep(3000);
					
					clickBackButtonAlerts();
					Thread.sleep(3000);
					//enable daily pollen alert
					try {
						Ad.findElementById("com.weather.Weather:id/my_alerts_layout_7").click();
						Thread.sleep(2000);
						}
						catch(Exception e) {
							
								List<WebElement> airlock=Ad.findElementsByClassName("android.widget.RelativeLayout");
								airlock.get(7).click();
								Thread.sleep(3000);
						}	
					enbleAlertSwitch();
					
					clickBackButtonAlerts();
					clickBackButtonAlerts();

	}

public static void enbleAlertSwitch() throws Exception {
	
	Thread.sleep(15000);
	String on_off=Ad.findElementById("com.weather.Weather:id/alert_switch").getText();
	if(on_off.contains("Off OFF")) {
		Ad.findElementById("com.weather.Weather:id/alert_switch").click();
		Thread.sleep(10000);
	}
	if(on_off.contains("On ON")) {
		
		Thread.sleep(3000);
	}
	
}

public static void clickBackButtonAlerts() throws Exception {
	//enble alert swtich
	try {
	Ad.findElementByAccessibilityId("Navigate up").click(); 
		Thread.sleep(3000);
		
	}
	catch(Exception e) {
		Ad.findElementByClassName("android.widget.ImageButton").click();
		Thread.sleep(3000);
	}
}


//clicking alerts
public static void clickAelrtsadwd() throws Exception {
	
	//click the view more
			try {
			Ad.findElementByAccessibilityId("View More").click();
			Thread.sleep(2000);
			}
			catch(Exception e) {
				Ad.findElementById("com.weather.Weather:id/more_icon").click();
				Thread.sleep(3000);
			}
}


public static void clickbreakingnewsAlert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(3).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(3).click();
		Thread.sleep(3000);
	}
}

public static void clickrealtimerainAlert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(4).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(4).click();
		Thread.sleep(3000);
	}
}
public static void clickrealtimelightningAlert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(5).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(5).click();
		Thread.sleep(3000);
	}	
}
public static void clickheavyrainfallAlert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(6).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(6).click();
		Thread.sleep(3000);
	}	
}
public static void clickthunderstormAlert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(7).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(7).click();
		Thread.sleep(3000);
	}	
}

public static void clickhightheatAlert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(8).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(8).click();
		Thread.sleep(3000);
	}	
}

public static void clickhighwindAlert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(9).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(9).click();
		Thread.sleep(3000);
	}	
}
public static void clickdensefogAlert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(10).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(10).click();
		Thread.sleep(3000);
	}	
}
public static void clickverycoldlert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(11).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(11).click();
		Thread.sleep(3000);
	}	
}

public static void clickheavysnowfalllert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(12).click();
	
	}
	catch(Exception e) {
		Thread.sleep(3000);
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(12).click();
		Thread.sleep(3000);
	}	
}

public static void clickicealert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(13).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(13).click();
		Thread.sleep(3000);
	}	
}

public static void clickwinterbreakingalert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(12).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(12).click();
		Thread.sleep(3000);
	}	
}

public static void clickfluxtomorrowalert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(13).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(13).click();
		Thread.sleep(3000);
	}	
}
public static void clickfluxtodayalert() throws Exception {
	try {
		List<WebElement> airlock=Ad.findElementsById("android:id/text1");
		airlock.get(14).click();
		Thread.sleep(3000);
	
	}
	catch(Exception e) {
		List<WebElement> airlock=Ad.findElementsByClassName("android.widget.TextView");
		airlock.get(14).click();
		Thread.sleep(3000);
	}	
}

public static void swipefornotification() {

	try {
	TouchAction ta=new TouchAction(Ad);
	Ad.swipe(688, 52, 642, 1579,2000);	
	}
	catch(Exception e) {
		try {
			TouchAction ta=new TouchAction(Ad);
			Ad.swipe(688, 52, 642, 1579,2000);	
		}
		catch(Exception e1) {
			try {
				TouchAction ta=new TouchAction(Ad);
				Ad.swipe(688, 52, 642, 1579,2000);		
			}catch(Exception e4) {
				
			}
		}
	}
}


public static void click_BN() throws Exception {
Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[4]").click();
	System.out.println("breaking news alert was clicked");
	logStep("breaking news alert was clicked");
	Thread.sleep(5000);

}


public static void click_RTR() throws Exception {
	Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[5]").click();
	System.out.println("real time rain alert was clicked");
	logStep("real time rain alert was clicked");
	Thread.sleep(5000);
	
}

public static void click_thunderstorm() throws Exception {
	
	Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[8]").click();
	System.out.println("thunderstorm alert  alert was clicked");
	logStep("thunderstorm alert  alert was clicked");
	Thread.sleep(5000);
}

public static void click_severe() throws Exception {

	Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]").click();
	Thread.sleep(3000);
	System.out.println("severe alert  was clicked");
	logStep("severe alert  was clicked");

}
public static void clickOnRequiredPushNotification(String notification) throws Exception {

try {
	
	List<WebElement> notifications=Ad.findElementsById("android:id/text1");
	Thread.sleep(5000);
	for(WebElement ChooseAlertTypel:notifications) {
		Thread.sleep(5000);
		if(ChooseAlertTypel.getAttribute("text").equalsIgnoreCase(notification)) {
			Thread.sleep(5000);
			try {
				System.out.println(notification +" was clicked");
				logStep(notification +" was clicked");
			ChooseAlertTypel.click();
			ChooseAlertTypel.click();
			ChooseAlertTypel.click();
			}catch(Exception e5) {
				//System.out.println(notification +" was clicked");
				//logStep(notification +" was clicked");

			}
	
	//	Thread.sleep(5000);
		break;
}
	}
}
	catch(Exception e){
		
	
		}
	}




public static void  finding_BreakingNews_iu_value() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";

	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = read_xml_data_into_buffer.read_xml_file_into_buffer_string();
if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fbreaking")) {
	System.out.println("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fbreaking ad call was trigred");
	logStep("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fbreaking ad call was trigred ");
}
if(!sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fbreaking")) {
System.out.println("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fbreaking ad call was not trigred");
logStep("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fbreaking ad call was not trigred");
Assert.fail("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fbreaking ad call was not trigred");
}

}


public static void get_Interstitial_aaxcal_Hourly() throws Exception {
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	System.out.println("Verifying amazon \"slotId\": \"2adb145c-0f90-44e9-852a-fa757c870db1\"  for db_display/interstitial/hourly");
	logStep("Verifying amazon \"slotId\": \"2adb145c-0f90-44e9-852a-fa757c870db1\"   for db_display/interstitial/hourly");
	//System.out.println("Slot Name is  : "+slotID);
	if(sb.contains("2adb145c-0f90-44e9-852a-fa757c870db1")) {
	System.out.println("2adb145c-0f90-44e9-852a-fa757c870db1 is trigred for aax  call db_display/interstitial/hourly");
	logStep("2adb145c-0f90-44e9-852a-fa757c870db1 is trigred for aax  call db_display/interstitial/hourly");
	}
	if(!sb.contains("2adb145c-0f90-44e9-852a-fa757c870db1")) {
		System.out.println("slotID :: 2adb145c-0f90-44e9-852a-fa757c870db1 is not trigred for aax call db_display/interstitial/hourly");
		logStep("slotID ::  2adb145c-0f90-44e9-852a-fa757c870db1is not trigred for aax call db_display/interstitial/hourly");
		Assert.fail("slotID ::  2adb145c-0f90-44e9-852a-fa757c870db1 is not trigred for aax call db_display/interstitial/hourly");
		
		}
}




public static void get_Interstitial_aaxcal_map_details() throws Exception {
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	System.out.println("Verifying amazon \"slotId\": \"14c21e29-45dd-43e3-b1f4-60376e220445\"  for db_display/interstitial/maps");
	logStep("Verifying amazon \"slot Id\": \"14c21e29-45dd-43e3-b1f4-60376e220445\"  for db_display/interstitial/maps");
	//System.out.println("Slot Name is  : "+slotID);
	if(sb.contains("14c21e29-45dd-43e3-b1f4-60376e220445")) {
	System.out.println("14c21e29-45dd-43e3-b1f4-60376e220445 is trigred for aax  call db_display/interstitial/maps");
	logStep("14c21e29-45dd-43e3-b1f4-60376e220445 is trigred for aax  call db_display/interstitial/maps");
	}
	if(!sb.contains("14c21e29-45dd-43e3-b1f4-60376e220445")) {
		System.out.println("slotId 14c21e29-45dd-43e3-b1f4-60376e220445 is not trigred for aax call db_display/interstitial/maps");
		logStep("slotId 14c21e29-45dd-43e3-b1f4-60376e220445 is not trigred for aax call db_display/interstitial/maps");
		Assert.fail("slotId 14c21e29-45dd-43e3-b1f4-60376e220445 is not trigred for aax call db_display/interstitial/maps");
		}
}

public static void get_Intertstitial_aaxcal_video_details() throws Exception {
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	System.out.println("Verifying amazon \"slotId\": \"ed8162e5-450a-415f-86c4-76a9fd40208c\"  for app_android_us/interstitial/video");
	logStep("Verifying amazon \"slotId\": \"ed8162e5-450a-415f-86c4-76a9fd40208c\"   for app_android_us/interstitial/video");
	//System.out.println("Slot Name is  : "+slotID);
	if(sb.contains("ed8162e5-450a-415f-86c4-76a9fd40208c")) {
	System.out.println("ed8162e5-450a-415f-86c4-76a9fd40208c is trigred for aax  call  app_android_us/interstitial/video");
	logStep("ed8162e5-450a-415f-86c4-76a9fd40208c is trigred for aax  call app_android_us/interstitial/video");
	}
	if(!sb.contains("ed8162e5-450a-415f-86c4-76a9fd40208c")) {
		System.out.println("slotId ed8162e5-450a-415f-86c4-76a9fd40208c is not trigred for aax call app_android_us/interstitial/video");
		logStep("slotId ed8162e5-450a-415f-86c4-76a9fd40208c is not trigred for aax call app_android_us/interstitial/video");
		Assert.fail("slotId ed8162e5-450a-415f-86c4-76a9fd40208c is not trigred for aax call app_android_us/interstitial/video");
		}
}


public static void get_Intertstitial_aaxcal_Daily1() throws Exception {	
read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
System.out.println("Verifying amazon \"slot Id\": \"652177e9-d888-45de-a3c8-4270316faf87\"  for daily details");
logStep("Verifying amazon \"slot Id\": \"652177e9-d888-45de-a3c8-4270316faf87\"   for  daily details\"");
if(sb.contains("652177e9-d888-45de-a3c8-4270316faf87")) {
System.out.println("652177e9-d888-45de-a3c8-4270316faf87eace is trigred for aax  call for daily details");
logStep("652177e9-d888-45de-a3c8-4270316faf87 is trigred for aax  call  for daily details");
}
if(!sb.contains("652177e9-d888-45de-a3c8-4270316faf87")) {
	System.out.println("slotID 652177e9-d888-45de-a3c8-4270316faf87 is not trigred for for daily details");
	logStep("slotID 652177e9-d888-45de-a3c8-4270316faf87 is not trigred  for daily details");
	Assert.fail("slotID 652177e9-d888-45de-a3c8-4270316faf87 is not trigred for daily details");
	}
}

public static void  VerifyBNAlert() throws Exception{
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fbreaking")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fbreaking"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("alert%3D"),required_info.indexOf("%26atfid"));			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String Alert=expectedValues.replaceAll("%3D", "=");
			
			if(expectedValues.contains("breaking")) {
				logStep("Breaking news push notification alert cust param value is "     +Alert);
				System.out.println("Breaking news push notification alert cust param value is "     +Alert);
			}
			else {
				System.out.println("Breaking news push notification alert cust param value is"      + Alert);
				logStep("Breaking news push notification alert cust param value is"      + Alert);
				Assert.fail("Beaking news push notification alert cust param value is"      + Alert);
			}
			//System.out.println(expectedValues);
			
		}
	
}


public static String read_xml_file_into_buffer_string()throws Exception{
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	
	String[][] paths = read_excel_data.exceldataread("Paths");
	String xml_file_path=null;
	File folder = new File(paths[4][Cap]);
	File[] listOfFiles = folder.listFiles();
	String Filename = null;
	for (File file : listOfFiles) {
		if (file.isFile()) {
			Filename = file.getName();
			xml_file_path = paths[4][Cap]+Filename;
			System.out.println("XML File Name is : "+Filename);
		}
	}
	
	StringBuilder sb=null;
	
	try {
		File xmlFile = new File(xml_file_path); 
		Reader fileReader = new FileReader(xmlFile); 
		BufferedReader bufReader = new BufferedReader(fileReader); 
		sb = new StringBuilder(); 
		String line = bufReader.readLine(); 
		while( (line=bufReader.readLine()) != null)
		{ 
			sb.append(line).append("\n"); 
		} 
		bufReader.close();
	} catch (Exception e) {
		System.out.println("No Data Found in XML File");
	}
	return sb.toString();
	
}

public static Boolean verifyElement(By by) {
	try {
		// Get the element using the Unique identifier of the element
		Ad.findElement(by);
	} catch (NoSuchElementException e) {
		// Return false if element is not found
		return false;
	}  catch (Exception e) {
		return false;
	}
	//Return true if element is found
	return true;
}


public static void clickAlerts() throws Exception{
	
	//cliking View more Button		
 	clickOnviewMore();
 	//cliking on aboutthisapp
 	clickOnAboutthisapp();	
 	 clickOnVersionnumber();
 	clickOntestmodesettings() ;
 	
 	clickOnAlerts();
 
 	
}

public static void clickOnviewMore() {
Functions.verifyElement(ByAccessibilityId("View More"));
	try {
	System.out.println("Clicking on View More");
	logStep("Clicking on View More");
	new WebDriverWait(Ad, Functions.maxTimeout).until(ExpectedConditions.elementToBeClickable(Ad.findElementByAccessibilityId("View More")));
	Ad.findElementByAccessibilityId("View More").click();
	//Thread.sleep(5000);
	}
	catch(Exception e) {
		try {
			new WebDriverWait(Ad, Functions.maxTimeout).until(ExpectedConditions.elementToBeClickable(Ad.findElementByAccessibilityId("View More")));
			Ad.findElementByAccessibilityId("View More").click();
		}
		catch(Exception e1) {
			
		}
	}
}

public static void clickOnAboutthisapp() throws Exception {
//	Functions.verifyElement(ByAccessibilityId("About this App"));
	try {
	System.out.println("Clicking on About this App");
	logStep("Clicking on About this App");
	new WebDriverWait(Ad, Functions.maxTimeout).until(ExpectedConditions.elementToBeClickable(Ad.findElementByAccessibilityId("About this App")));
	Ad.findElementByAccessibilityId("About this App").click();
	//About this App
//	Thread.sleep(5000);
	}
	catch(Exception e) {	
		new WebDriverWait(Ad, Functions.maxTimeout).until(ExpectedConditions.elementToBeClickable(Ad.findElementById("com.weather.Weather:id/item_about")));
		Ad.findElementById("com.weather.Weather:id/item_about").click();
	Thread.sleep(5000);
	}
}



public static void clickOnVersionnumber() throws Exception {
try {
	//Thread.sleep(15000);
	Functions.verifyElement(By.id("com.weather.Weather:id/test_mode_settings"));
		//Thread.sleep(15000);
		System.out.println("Clicking on test mode settings");
		logStep("Clicking on test mode settings");
		//Thread.sleep(15000);
		new WebDriverWait(Ad, Functions.maxTimeout).until(ExpectedConditions.elementToBeClickable(Ad.findElementById("com.weather.Weather:id/test_mode_settings")));
		Ad.findElementById("com.weather.Weather:id/test_mode_settings").click();
		//Thread.sleep(5000);		
}
catch(Exception e) {
System.out.println("Clicking on BuildNumber till test mode settings option is displaying");
logStep("Clicking on BuildNumber till test mode settings option is displaying");	
for(int i=1;i<10;i++) {
	 //Thread.sleep(7000);
	 new WebDriverWait(Ad, Functions.maxTimeout).until(ExpectedConditions.elementToBeClickable(Ad.findElementById("com.weather.Weather:id/about_version")));
	Ad.findElementById("com.weather.Weather:id/about_version").click();
	 //Thread.sleep(6000);
}
}
}

public static void clickOntestmodesettings() throws Exception {
	try {
		Functions.verifyElement(By.id("com.weather.Weather:id/test_mode_settings"));
		if(Ad.findElementById("com.weather.Weather:id/test_mode_settings").isDisplayed())
			//Thread.sleep(5000);
			System.out.println("Clicking on test mode settings");
			logStep("Clicking on test mode settings");
		//Thread.sleep(5000);
			new WebDriverWait(Ad, Functions.maxTimeout).until(ExpectedConditions.elementToBeClickable(Ad.findElementById("com.weather.Weather:id/test_mode_settings")));
			Ad.findElementById("com.weather.Weather:id/test_mode_settings").click();
		}

	catch(Exception e) {
		
	}
}



	public static void verifyingdailydetailiu() throws Exception {
		String expected_data = null;
		String today=null;
		String day1=null;
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
//		logStep("Verifying  SOD custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fcard%2Fradar ad call");
		
		today=Ad.findElementById("com.weather.Weather:id/daily_details_day_title").getText();
		String days=today.replace(today, today+1);
		//System.out.println("day from the UI is  " +day);
		//logStep("day from the UI is  " +day);
		String currentday1=days.toLowerCase();
		
		System.out.println("Checking iu from charles data");
		logStep("Checking iu from charles data");
		logStep("iu value should not be null");
		System.out.println("iu value should not be null");
		
		if(sb.toString().contains("iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fdetails%2F10day_")) {
			
				try {			
				String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fdetails%2F10day_"));
		//		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("&amp"));
				 expected_data = Read_API_Call_Data.toString().substring(Read_API_Call_Data.indexOf("iu"),Read_API_Call_Data.indexOf("&correlator"));
				System.out.println("Charles data value is "+expected_data);
				logStep("Charles data value is "+expected_data);			
					}
				catch(Exception e) {
					
				}
			}
		else {
			System.out.println("iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fdetails%2F10day_"+currentday1 +" was not trigered");
			logStep("iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fdetails%2F10day_"+currentday1 +" was not trigered");
			Assert.fail("daily details ad call was not trigred");
		}

		System.out.println("retriving the day from the UI");
		logStep("retriving the day from the UI");

		System.out.println("day from the UI is " +currentday1);
		logStep("day from the UI is " +currentday1);
			
			
			System.out.println("Verifying the chales data is matched with UI");
			logStep("Verifying the chales data is matched with UI");
			try {
				if(expected_data.contains(currentday1)) {
					System.out.println(expected_data+" is matched with " +currentday1);
					logStep(expected_data+" is matched with "+ currentday1);
				}
			}
			catch(Exception e) {
				System.out.println(expected_data+ " is not matched with " +currentday1);
				logStep(expected_data+" is not matched with " +currentday1);
				Assert.fail(expected_data+" is not matched with " +currentday1);
			}
		
	}


public static void validate_BG_adcall_IDD() throws Exception {
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("idd");
	if(sb.contains(exceldata[7][1])) {
		System.out.println(exceldata[7][1]+" Background  call was generated for IDD");
		logStep(exceldata[7][1]+" Background call was generated for IDD");
	}	
	
	

		if(!sb.contains(exceldata[7][1])) {
			System.out.println(exceldata[7][1]+" Background call is not  generated for IDD");
		logStep(exceldata[7][1]+" Background call is not  generated for IDD");
		Assert.fail(exceldata[7][1]+" Background call is not  generated for IDD");
	}
}


public static void validate_BG_adcall_NextGenIM() throws Exception {
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	if(sb.contains(exceldata[7][1])) {
		System.out.println(exceldata[7][1]+" Background  call was generated for NextGenIM ad call");
		logStep(exceldata[7][1]+" Background call was generated for NexrGenIM ad call");
	}	
	
		if(!sb.contains(exceldata[7][1])) {
			System.out.println(exceldata[7][1]+" Background call is not  generated for NextGenIM ad call");
		logStep(exceldata[7][1]+" Background call is not  generated for NextGenIM ad call");
		Assert.fail(exceldata[7][1]+" Background call is not  generated for NextGenIM ad call");
	}
}
	

public static void validate_BG_adcall_NextGenIM_video() throws Exception {
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	if(sb.contains(exceldata[20][1])) {
		System.out.println(exceldata[20][1]+" Background  call was generated for NextGenIM ad call");
		logStep(exceldata[20][1]+" Background call was generated for NexrGenIM ad call");
	}	
	
		if(!sb.contains(exceldata[20][1])) {
			System.out.println(exceldata[20][1]+" Background call is not  generated for NextGenIM ad call");
		logStep(exceldata[20][1]+" Background call is not  generated for NextGenIM ad call");
		Assert.fail(exceldata[20][1]+" Background call is not  generated for NextGenIM ad call");
	}
}

public static void validate_BG_adcall_IntegratedIM() throws Exception {
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("dailydeatils");
	if(sb.contains(exceldata[7][1])) {
		System.out.println(exceldata[7][1]+" Background call was generated for IntegratedIM Im ad call");
		logStep(exceldata[7][1]+" Background call was generated for IntegratedIM Im ad call");
	}	

		if(!sb.contains(exceldata[7][1]))
			System.out.println(exceldata[7][1]+" Background call is not generated for NextGen Im ad call");
		logStep(exceldata[7][1]+" Background call is not generated for NextGen Im ad call");
		Assert.fail(exceldata[7][1]+" Background call is not generated for NextGen Im ad call");
	}


public static Map<String, String> Verify_watsonFlucard_iu() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	
	String[][] exceldata=read_excel_data.exceldataread("FLUWM");
	if(sb.toString().contains(exceldata[1][1])){
		System.out.println(exceldata[1][1] + " call was  trigred");
		logStep(exceldata[1][1] + " call was  trigred");
}
if(!sb.contains(exceldata[1][1])) {
	System.out.println(exceldata[1][11] + " call was not   trigred");
 logStep(exceldata[1][1] + "  call was not   trigred");
 Assert.fail(exceldata[1][1] + " call was not   trigred");
}
return wfxtriggers_values;
}
public static void validate_Size_WMFlu() throws Exception {
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("FLUWM");
	if(sb.toString().contains(exceldata[1][1])){
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[1][1]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[2][1]));
		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[2][1]),required_info.indexOf(exceldata[5][1]));
		String expectedValues = expected_data.toString();
		String WMFLUCardsize=expectedValues.replaceAll(exceldata[4][1], "");
		if(WMFLUCardsize.equalsIgnoreCase(exceldata[3][1])) {
			System.out.println("WM Flu card ad call size is:::"  + WMFLUCardsize);
			logStep("WM Flu card ad call size is:::"  + WMFLUCardsize);
		}
		else {
			System.out.println("WM Flu card ad call size is not matched with"     + exceldata[3][1]);
			logStep("WM Flu card ad call size is not matched with"     + exceldata[3][1]);
			Assert.fail("WM Flu card ad call size is not matched with"+ exceldata[3][1]);
		}
		System.out.println(expectedValues);
		
	}
	}

public static void watson_adcall_response() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("Watsonmoment");
	 
	if(sb.contains(exceldata[8][1])) {
		//bgEvent:'adBg'
		System.out.println("got the response for watson card ad call");
	}
	if(!sb.contains(exceldata[8][1]))	
	 {
		System.out.println("did't  get the response for watson card ad cal");
	}
	 
	 }
public static void validate_FG_adcall_IntegratedIM() throws Exception {
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("idd");
	if(sb.contains(exceldata[6][1])) {		
		System.out.println(exceldata[6][1]+ " Foreground call was generated for IntegratedIM Im ad call");		
		logStep(exceldata[6][1]+ " Foreground call was generated for IntegratedIM Im ad call");
	}
	
	if(!sb.contains(exceldata[6][1])) {
		System.out.println(exceldata[6][1]+" Foreground call is not generated for IntegratedIM Im ad call");
		logStep(exceldata[6][1]+" Foreground call is not generated for IntegratedIM Im ad call");
		Assert.fail(exceldata[6][1]+"Foreground cal is not  trigred");
	}
	}


public static void validate_FG_adcall_IDD() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("idd");
	if(sb.contains(exceldata[6][1])) {		
		System.out.println(exceldata[6][1]+ " Foreground call was generated for IDD ad call");		
		logStep(exceldata[6][1]+ " Foreground call was generated for IDD  ad call");
	}
	
	if(!sb.contains(exceldata[6][1])) {
		System.out.println(exceldata[6][1]+" Foreground call is not generated for IDD ad call");
		Assert.fail(exceldata[6][1]+"Foreground call is not generated for IDD ad cal");
	}
	}

public static void validate_FG_adcall_NextImad() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	if(sb.contains(exceldata[6][1])) {		
		System.out.println(exceldata[6][1]+ " Foreground call was generated for NextGenIm ad call");		
		logStep(exceldata[6][1]+ " Foreground call was generated for NextGenIm  ad call");
	}
	
	if(!sb.contains(exceldata[6][1])) {
		System.out.println(exceldata[6][1]+" Foreground call is not generated for NextGenIm ad call");
		logStep(exceldata[6][1]+" Foreground call is not generated for NextGenIm ad call");
		Assert.fail(exceldata[6][1]+"Foreground call is not generated for NextGenIm ad cal");
	}
	}

public static void validate_FG_adcall_NextImad_video() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	if(sb.contains(exceldata[19][1])) {		
		System.out.println(exceldata[19][1]+ " Foreground call was generated for NextGenIm ad call");		
		logStep(exceldata[19][1]+ " Foreground call was generated for NextGenIm  ad call");
	}
	
	if(!sb.contains(exceldata[19][1])) {
		System.out.println(exceldata[19][1]+" Foreground call is not generated for NextGenIm ad call");
		logStep(exceldata[19][1]+" Foreground call is not generated for NextGenIm ad call");
		Assert.fail(exceldata[19][1]+"Foreground call is not generated for NextGenIm ad cal");
	}
	}


public static void validate_Size_dailydetails_integratedad() throws Exception {
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("idd");

	if(sb.toString().contains(exceldata[1][1])){
		//System.out.println(exceldata[1][3]);
		System.out.println(exceldata[1][1]);
		System.out.println(exceldata[2][1]);
		System.out.println(exceldata[3][1]);
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[3][1]));
String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[3][1]));
		
		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[3][1]),required_info.indexOf(exceldata[22][1]));
		String expectedValues = expected_data.toString();
		System.out.println(expectedValues);
		   String val[]=expected_data.split("&");
		      System.out.println("Size  is  " + val[0]);
				logStep("Size of theis " + val[0]);
		String iddsize=val[0].replaceAll("%7C", "|");
		if(iddsize.contains(exceldata[4][1])) {
			System.out.println("daily details integrated ad call size is matched with::: "  + iddsize);
		}
		else {
			System.out.println("daily details integrated ad call size is not matched with "     + exceldata[3][1]);
			Assert.fail("daily details integrated  is not matched with "+ exceldata[3][1]);
		}
	}
}
public static void clickOnAlertNotificatons( String pushNotifications) throws Exception {
	
	//clicking on Airlock
	try {
		Thread.sleep(5000);

System.out.println("Clicking push alert notification alert on device");
logStep("Clicking push alert notification alert on device");
Thread.sleep(5000);
List<WebElement> all;
try {
	  all=Ad.findElementsById("android:id/title");
	Thread.sleep(5000);
}
catch(Exception e) {
 all=Ad.findElementsById("android:id/inbox_text0");
		Thread.sleep(5000);
}
	 for(WebElement Airlock:all) {
		if( Airlock.getAttribute("text").equalsIgnoreCase(pushNotifications)) {
			System.out.println(pushNotifications +" alert is generated on the screen");
			 logStep(pushNotifications +" alert is generated on the screen");
			// System.out.println(Airlock.getAttribute("text"));
			new WebDriverWait(Ad, Functions.maxTimeout).until(ExpectedConditions.elementToBeClickable(Airlock));
			Airlock.click();
	 Thread.sleep(5000);
			 break;
		 }
		else{
			System.out.println(pushNotifications +" push notification is not generated on the device");
			 logStep(pushNotifications +" push notification is not generated on the device");
			 Assert.fail(pushNotifications +"push notification is not generated on the device");
			 
		}
	 }
	}
	catch(Exception e) {
		Thread.sleep(5000);
		System.out.println(pushNotifications +" alert is not generated");
		 logStep(pushNotifications +" alert is not generated");
	
}
}



public static void clickOnAlertType( String AlertType) throws Exception {
	
	//clicking on Airlock
	try {
		Thread.sleep(5000);

System.out.println("Clicking on required alert type");
logStep("Clicking on required alert type");
Thread.sleep(5000);	
	// List<WebElement> all=Ad.findElementsById("android:id/text1");
 List<WebElement> all=Ad.findElementsByClassName("android.widget.TextView");

	Thread.sleep(5000);
	 for(WebElement Airlock:all) {
		if( Airlock.getText().equalsIgnoreCase(AlertType)) {
			new WebDriverWait(Ad, Functions.maxTimeout).until(ExpectedConditions.elementToBeClickable(Airlock));
			Airlock.click();
			System.out.println("Clicked the "+ AlertType);
			logStep("Clicked the "+ AlertType);
	 Thread.sleep(5000);
			 break;
		 }
	 }
	}
	catch(Exception e) {
		System.out.println("need to click");
		Thread.sleep(10000);
}
}


public static void clickOnAlerts() throws Exception {
	
	//clicking on Airlock
	try {
		Thread.sleep(5000);
System.out.println("Clicking on Alerts");
logStep("Clicking on Alerts");
WebElement all=(WebElement) Ad.findElementsById("android:id/title").get(4);
all.click();
Thread.sleep(5000);
	}
	catch(Exception e) {
		Thread.sleep(5000);
		 List<WebElement> all=Ad.findElementsById("android:id/title");
		Thread.sleep(5000);
		 for(WebElement Airlock:all) {
			if( Airlock.getAttribute("text").equalsIgnoreCase("Alerts")) {
				new WebDriverWait(Ad, Functions.maxTimeout).until(ExpectedConditions.elementToBeClickable(Airlock));
				Airlock.click();
		 Thread.sleep(5000);
				 break;
			 }
		 }
 }
}


public static void clickOnRTRnotification() {
	
	String text=Ad.findElementById("android:id/title").getText();
	if(text.contains("Real-time")) {
		System.out.println(text +"alert generated");
		Ad.findElementById("android:id/title").click();
	}
	else{
		System.out.println(text+"alert is not generated");
		Assert.fail(text+"alert is not generated");
	}
}
public static Map<String, String> finding_Radar_Map_card_iu_value() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";

	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps")) {
	System.out.println("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps call was trigred");
	logStep("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps call was trigred");
}
if(!sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps")) {
System.out.println("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps call was not  trigred");
logStep("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps call was not  trigred");
Assert.fail("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps call was not trigred");
}
return wfxtriggers_values;
}
public static void  VerifyRTRAlert() throws Exception{
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("alert%3D"),required_info.indexOf("%26atfid"));			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String Alert=expectedValues.replaceAll("%3D", "=");
			
			if(expectedValues.contains("real")) {
				System.out.println("real time rain  push notification alert cust param value is "     +Alert);
				logStep("real time rain  push notification alert cust param value is "     +Alert);
			}
			else {
				System.out.println("real time rain  push notification alert cust param value is "      + Alert);
				logStep("real time rain  push notification alert cust param value is "      + Alert);
				Assert.fail("real time rain  push notification alert cust param value is "      + Alert);
			}
			//System.out.println(expectedValues);
			
		}
}

public static Map<String, String> finding_hourly_details_card_iu_value() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";

	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly")) {
	System.out.println("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly ad call was trigred");
	logStep("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly ad call was trigred");
}
if(!sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly")) {
System.out.println("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly ad call was not  trigred");
logStep("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly ad call was not  trigred");
Assert.fail("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly ad call was not  trigred");
}
return wfxtriggers_values;

}

public static void  Verifyheavyrainfallalert() throws Exception{
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().indexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("alert%3D"),required_info.indexOf("%26atfid"));			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String Alert=expectedValues.replaceAll("%3D", "=");
			
			if(expectedValues.contains("heavyrain")) {
				System.out.println("heavy rain fall push notification alert cust param value is "     +Alert);
				logStep("heavy rain fall push notification alert cust param value is "     +Alert);
			}
			else {
				System.out.println("heavy rain fall push notification alert cust param value is "      + Alert);
				logStep("heavy rain fall  push notification alert cust param value is "      + Alert);
				Assert.fail("heavy rain fall push notification alert cust param value is "      + Alert);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void  Verifythunderstormalert() throws Exception{
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("alert%3D"),required_info.indexOf("%26atfid"));			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String Alert=expectedValues.replaceAll("%3D", "=");
			
			if(expectedValues.contains("thunderstm")) {
				System.out.println("thunderstorm push notification alert cust param value is "     +Alert);
				logStep("thunderstorm push notification alert cust param value is "     +Alert);
			}
			else {
				System.out.println("thunderstorm push notification alert cust param value is "      + Alert);
				Assert.fail("thunderstorm push notification alert cust param value is "      + Alert);
			}
			//System.out.println(expectedValues);
			
		}
}


public static void validate_pos_Cust_param_WM_Flu() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("FLUWM");
		if(sb.toString().contains(exceldata[1][1])){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[1][1]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("pos%3D"),required_info.indexOf("%26ref%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String pos=expectedValues.replaceAll("%3D", "=");
			
			if(pos.contains("app_sl")) {
				System.out.println("pos cust param value  is " +pos);
				logStep("pos cust param value  is " +pos);
			}
			else {
				System.out.println("pos cust param value is not matched with"     + pos);
				logStep("pos cust param value is not matched with"     + pos);
				Assert.fail("pos cust param value is not matched with"     + pos);
			}
			//System.out.println(expectedValues);
			
		}
}

public static Map<String, String> Verify_watsonAllergycard_iu() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	
	String[][] exceldata=read_excel_data.exceldataread("AllergyWM");
	if(sb.toString().contains(exceldata[1][1])){
		System.out.println(exceldata[1][1] + " call was  trigred");
}
if(!sb.contains(exceldata[1][1])) {
	System.out.println(exceldata[1][1] + " call was not   trigred");
 logStep(exceldata[1][1] + " call was not   trigred");
 Assert.fail(exceldata[1][1] + " call was not   trigred");
}
return wfxtriggers_values;
}
public static Map<String, String> finding_daily_details_card_iu_value() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	String expected_data = null;
	String today = null;
	try {
    today=Ad.findElementById("com.weather.Weather:id/daily_details_day_title").getText();
	}catch(Exception e) {
		
	}
	String days=today.replace(today, today+1);
	//System.out.println("day from the UI is  " +day);
	//logStep("day from the UI is  " +day);
	String currentday1=days.toLowerCase();
	DeviceStatus device_status = new DeviceStatus();
	
	
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2F10day_")) {
		
			try {			
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2F10day_"));
	//		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("&amp"));
			 expected_data = Read_API_Call_Data.toString().substring(Read_API_Call_Data.indexOf("iu"),Read_API_Call_Data.indexOf("&correlator"));
			System.out.println("Charles data value is "+expected_data);
			logStep("Charles data value is "+expected_data);			
				}
			catch(Exception e) {
				
			}
		}
	else {
		System.out.println("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2F10day_"+currentday1 +" was not trigered");
		logStep("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2F10day_"+currentday1 +" was not trigered");
		Assert.fail("daily details ad call was not trigred");
	}

return wfxtriggers_values;
}
public static void validate_Size_WMAllergy() throws Exception {
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("AllergyWM");
	if(sb.toString().contains(exceldata[1][1])){
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[1][1]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[2][1]));
		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[2][1]),required_info.indexOf(exceldata[5][1]));
		String expectedValues = expected_data.toString();
		String WMAllergySize=expectedValues.replaceAll(exceldata[4][1], "");
		if(WMAllergySize.equalsIgnoreCase(exceldata[3][1])) {
			System.out.println(" ad call size is:::"  + WMAllergySize);
		}
		else {
			System.out.println("ad call size is not matched with"     + exceldata[3][1]);
			Assert.fail("ad call size is not matched with"+ exceldata[3][1]);
		}
		System.out.println(expectedValues);
		
	}
	}

public static void validate_pos_Cust_param_WM_Allergy() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("AllergyWM");
		if(sb.toString().contains(exceldata[1][1])){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[1][1]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("pos%3D"),required_info.indexOf("%26ref%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String pos=expectedValues.replaceAll("%3D", "=");
			
			if(pos.contains("app_sl")) {
				System.out.println("pos cust param value  is " +pos);
				logStep("pos cust param value  is " +pos);
			}
			else {
				System.out.println("pos cust param value is not matched with"     + pos);
				logStep("pos cust param value is not matched with"     + pos);
				Assert.fail("pos cust param value is not matched withh"     + pos);
			}
			//System.out.println(expectedValues);
			
		}
}
public static void  Verifyheavysnowfallalert() throws Exception{
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2F10day")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2F10day"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("alert%3D"),required_info.indexOf("%26atfid"));			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String Alert=expectedValues.replaceAll("%3D", "=");
			
			if(expectedValues.contains("heavysnow")) {
				System.out.println("heavy snowfall push notification alert cust param value is "     +Alert);
				logStep("heavy snowfall push notification alert cust param value is "     +Alert);
			}
			else {
				System.out.println("heavy snowfall cold push notification alert cust param value is "      + Alert);
				logStep("heavy snowfall  push notification alert cust param value is "      + Alert);
				Assert.fail("heavy snowfall push notification alert cust param value is "      + Alert);
			}
			//System.out.println(expectedValues);
			
		}
}

public static Map<String, String> findind_alertsiu_value() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";

	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Falerts")) {
	System.out.println("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Falerts ad call was trigred");
	logStep("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Falerts ad call was trigred");
}
if(!sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Falerts")) {
System.out.println("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Falerts ad call was not  trigred");
logStep("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Falerts ad call was not  trigred");
Assert.fail("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Falerts ad call was not  trigred");
}
return wfxtriggers_values;
}


public static void  VerifysevereAlert() throws Exception{
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Falerts")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Falerts"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("alert%3D"),required_info.indexOf("%26atfid"));			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String Alert=expectedValues.replaceAll("%3D", "=");
			
			if(expectedValues.contains("severe")) {
				System.out.println("Severe push notification alert cust param value is "     +Alert);
				logStep("Severe push notification alert cust param value is "     +Alert);
			}
			else {
				System.out.println("Severe push notification alert cust param value is"      + Alert);
				logStep("Severe push notification alert cust param value is"      + Alert);
				Assert.fail("Severe push notification alert cust param value is"      + Alert);
			}
			//System.out.println(expectedValues);
			
		}
	
}


public static void  Verifyicealert() throws Exception{
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2F10day")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2F10day"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("alert%3D"),required_info.indexOf("%26atfid"));			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String Alert=expectedValues.replaceAll("%3D", "=");
			
			if(expectedValues.contains("ice")) {
				System.out.println("ice push notification alert cust param value is "     +Alert);
				logStep("ice push notification alert cust param value is "     +Alert);
			}
			else {
				System.out.println("ice push notification alert cust param value is "      + Alert);
				logStep("ice push notification alert cust param value is "      + Alert);
				Assert.fail("ice push notification alert cust param value is "      + Alert);
			}
			//System.out.println(expectedValues);
			
		}
}
public static void  VerifyWBNAlert() throws Exception{
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fbreaking")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fbreaking"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("alert%3D"),required_info.indexOf("%26atfid"));			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String Alert=expectedValues.replaceAll("%3D", "=");
			
			if(expectedValues.contains("breaking")) {
				System.out.println("Winter Breaking news push notification alert cust param value is "     +Alert);
				logStep("Winter Breaking news push notification alert cust param value is "     +Alert);
			}
			else {
				System.out.println("Winter Breaking news push notification alert cust param value is"      + Alert);
				logStep("Winter Breaking news push notification alert cust param value is"      + Alert);
				Assert.fail("Winter Beaking news push notification alert cust param value is"      + Alert);
			}
			//System.out.println(expectedValues);
			
		}
	
}

public static Map<String, String> Verify_weekend_iu() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	
	String[][] exceldata=read_excel_data.exceldataread("WeekendWM");
	if(sb.toString().contains(exceldata[1][1])){
		System.out.println(exceldata[1][1] + " call was  trigred");
}
if(!sb.contains(exceldata[1][1])) {
	System.out.println(exceldata[1][1] + " call was not   trigred");
 logStep(exceldata[1][1] + " call was not   trigred");
 Assert.fail(exceldata[1][1] + " call was not   trigred");
}
return wfxtriggers_values;
}

public static void validate_Size_weeekend() throws Exception {
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("WeekendWM");
	if(sb.toString().contains(exceldata[1][1])){
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[1][1]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[2][1]));
		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[2][1]),required_info.indexOf(exceldata[5][1]));
		String expectedValues = expected_data.toString();
		String adsize=expectedValues.replaceAll(exceldata[4][1], "");
		if(adsize.equalsIgnoreCase(exceldata[3][1])) {
			System.out.println(" ad call size is:::"  + adsize);
		}
		else {
			System.out.println("ad call size is not matched with"     + exceldata[3][1]);
			Assert.fail("ad call size is not matched with"+ exceldata[3][1]);
		}
		System.out.println(expectedValues);
		
	}
	}
public static Map<String, String> Verify_Week_Ahead_card_iu() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	
	String[][] exceldata=read_excel_data.exceldataread("Watsonmoment");
	if(sb.toString().contains(exceldata[1][1])){
		System.out.println(exceldata[1][1] + " call was  trigred");
}
if(!sb.contains(exceldata[1][1])) {
	System.out.println(exceldata[1][1] + " call was not   trigred");
 logStep(exceldata[1][1] + " call was not   trigred");
 Assert.fail(exceldata[1][1] + " call was not   trigred");
}
return wfxtriggers_values;
}
public static void validate_Size_weeekahead() throws Exception {
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("Watsonmomentweekahead");
	if(sb.toString().contains(exceldata[1][1])){
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[1][1]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[2][1]));
		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[2][1]),required_info.indexOf(exceldata[5][1]));
		String expectedValues = expected_data.toString();
		String marqueeadsize=expectedValues.replaceAll(exceldata[4][1], "");
		if(marqueeadsize.equalsIgnoreCase(exceldata[3][1])) {
			System.out.println(" ad call size is:::"  + marqueeadsize);
		}
		else {
			System.out.println("ad call size is not matched with"     + exceldata[3][1]);
			Assert.fail("ad call size is not matched with"+ exceldata[3][1]);
		}
		System.out.println(expectedValues);
		
	}
	}
public static void validate_pos_Cust_param_WM_WeekAhead() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("Watsonmomentweekahead");
		if(sb.toString().contains(exceldata[1][20])){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[1][20]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("pos%3D"),required_info.indexOf("%26tmp%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String pos=expectedValues.replaceAll("%3D", "=");
			
			if(pos.contains("app_sl")) {
				System.out.println("pos cust param value  is " +pos);
				logStep("pos cust param value  is " +pos);
			}
			else {
				System.out.println("pos cust param value is not matched with"     + pos);
				logStep("pos cust param value is not matched with"     + pos);
				Assert.fail("pos cust param value is not matched withh"     + pos);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_pos_Cust_param_WM_Weekend() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("WeekendWM");
		if(sb.toString().contains(exceldata[1][1])){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[8][1]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("pos%3D"),required_info.indexOf("%26ref%3D\""));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String pos=expectedValues.replaceAll("%3D", "=");
			
			if(pos.contains("app_sl")) {
				System.out.println("pos cust param value  is " +pos);
				logStep("pos cust param value  is " +pos);
			}
			else {
				System.out.println("pos cust param value is not matched with"     + pos);
				logStep("pos cust param value is not matched with"     + pos);
				Assert.fail("pos cust param value is not matched withh"     + pos);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void  VerifyRTLAlert() throws Exception{
		
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
			if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps")){
				String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps"));
				String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
				String expected_data = required_info.toString().substring(required_info.indexOf("alert%3D"),required_info.indexOf("%26atfid"));			
				//6sod%3Dno%
				String expectedValues = expected_data.toString();
				String Alert=expectedValues.replaceAll("%3D", "=");
				
				if(expectedValues.contains("lightning")) {
					System.out.println("real time lightning  push notification alert cust param value is "     +Alert);
					logStep("real time lightning  push notification alert cust param value is "     +Alert);
				}
				else {
					System.out.println("real time lightning  push notification alert cust param value is "      + Alert);
					logStep("real time lightning  push notification alert cust param value is "      + Alert);
					Assert.fail("real time lightning  push notification alert cust param value is "      + Alert);
				}
				//System.out.println(expectedValues);
				
			}
	}
public static void clickNotification(String notification) throws Exception {
	
	String text=Ad.findElementById("android:id/title").getText();
		Thread.sleep(5000);
			if(text.contains(notification)) {
			Ad.findElementById("android:id/title").click();
				Thread.sleep(3000);
			}
		}
		
private static By ByAccessibilityId(String string) {
	// TODO Auto-generated method stub
	return null;
}

public static void tapping() {
	Ad.tap(1, 763, 2296, 3000);
}

public static  void Verify_Privacy_Card_onScreen() throws Exception{
	Thread.sleep(8000);
	String Module = null;
	logStep("Scroll the app till Privay card");
	System.out.println("Scroll the app till Privay card");
	for(int i=0;i<20;i++)
	{
		try {
		AppiumFunctions.SwipeUp_Counter(i);
	}
		
		catch(Exception e) {
		
		}
	}		
	}


//Swipe based on counter  //by naresh
	public static void Swipe_Conter(int Counter) throws Exception{

		int swipe = Counter;

		for(int i=1;i<=swipe ;i++){
AppiumFunctions.Swipe_feed();
			Thread.sleep(2000);
		}
	
}
	

public static  void selecting_opt_out_mode() throws Exception{
		
		//Clicking privacy arrow button
		/*System.out.println("Clicking privacy arrow button");
		logStep("Clicking privacy arrow button");
	    Ad.findElementById("com.weather.Weather:id/privacy_card_personal_info_container").click();*/
	    Thread.sleep(8000);
		Swipe_Conter(10);
		 Thread.sleep(10000);
		 TouchAction ta=new TouchAction(Ad);
		 ta.tap(480, 1369).perform();
		//Selecting  Opt out  mode option in privacy card
		System.out.println("Selecting  Opt out  mode option in privacy card");
		logStep("Selecting  Opt out  mode option in privacy card");
		 Thread.sleep(3000);		
	}


public static void SwipeUp_Counter_video_maps_feedcards(int Counter) throws Exception{
	int swipeup = Counter;
//System.out.println("swipeup");
	for(int i=1;i<=swipeup ;i++){
		AppiumFunctions.Swipe_feed();
		String ModuleName;
		try {
		if(Ad.findElementById("com.weather.Weather:id/header_title").isDisplayed()) {
			try {
 ModuleName=Ad.findElementById("com.weather.Weather:id/header_title").getAttribute("text");
			}
catch(Exception e) {
 ModuleName=Ad.findElementById("com.weather.Weather:id/header_title").getText();
}
	System.out.println(ModuleName.toString() +" feed card is presented on the screen");
	
	if(ModuleName.toString().contains("Top Stories") ||ModuleName.toString().contains("Low Stories") || ModuleName.toString().contains("Videos")){
		
		AppiumFunctions.clickOnVideoElement();
	AppiumFunctions.clickOnBackArrowElement();
		}
if(ModuleName.toString().contains("Maps") ||ModuleName.toString().contains("Thunderstorms possible") || ModuleName.toString().contains("Thunderstorms ending") || ModuleName.toString().contains("Thunderstorms starts")||ModuleName.toString().contains("Dry conditions")) {
AppiumFunctions.clickOnRadarMaps();
	AppiumFunctions.clickOnBackArrowElement();
	i=50;
	break;
	
}
		}
		}
		catch(Exception e) {
			try {
			Swipe();
			Ad.findElementById("com.weather.Weather:id/header_title").isDisplayed();
			}
			catch(Exception e1) {
				Swipe();
			}
	
			
		}
			}	
}

public static void Verify_video_ad_call_Optoutmode( )throws Exception{
	  read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
		System.out.println("Verifying   iu=%2F7646%2Fapp_android_us%2Fvideo  ad call");
		logStep("Verifying iu=%2F7646%2Fapp_android_us%2Fvideo ad calll");
	if(sb.contains("%2F7646%2Fapp_android_us%2Fvideo")) {
	System.out.println("/7646/app_android_us/video call was trigred");
	logStep("/7646/app_android_us/video call was trigred");
	}

	if(!sb.contains("%2F7646%2Fapp_android_us%2Fvideo")) {
	System.out.println("/7646/app_android_us/video call was not trigred");
	logStep("/7646/app_android_us/video call was not trigred");
	Assert.fail("/7646/app_android_us/video call was not trigred");

	}

	}

public static void verifying_feedcalls(int i) throws Exception {


	String expected_data = null;
	String today=null;
	String day1=null;
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("feedcards");
//	logStep("Verifying  SOD custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fcard%2Fradar ad call");
	//String feed="iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_";
	String feed=exceldata[1][1];
	       logStep("checking for " +feed+i);
			System.out.println("checking for "  +feed+i);
			logStep("Verifying iu value should't be nl");
			System.out.println("Verifying iu value should't be nl");
	if(i!=1) {
		if(sb.contains(feed+i) &&  !(feed+i).isEmpty() && !(feed+i).contains("nl") ) {			
		System.out.println(feed+i +" call was trigred");
		logStep(feed+i +" call was trigred");
		
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(feed+i));
		//		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("&amp"));
				 expected_data = Read_API_Call_Data.toString().substring(Read_API_Call_Data.indexOf("iu"),Read_API_Call_Data.indexOf("&correlator="));
				String val[]=expected_data.split("&");
		
				System.out.println("Size of the "+feed+i+" is  " + val[1]);
				logStep("Size of the "+feed+i+" is " + val[1]);
		//	System.out.println("Charles data value is "+expected_data);
		//	logStep("Charles data value is "+expected_data);			
		
		}	else{
			System.out.println(feed+i +"call was not trigred");
			logStep(feed+i +" call was not trigred");
		     Assert.fail(feed+i + " call was not trigred");
			}
		}
	
	 if(i==1) {
		if(sb.contains(feed+i) &&  !(feed+i).isEmpty()) {			
			System.out.println(feed+i +" call was trigred");
			logStep(feed+i +" call was trigred");
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("sz=320x50%7C320x100"));
			
			//		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("&amp"));
					 expected_data = Read_API_Call_Data.toString().substring(Read_API_Call_Data.indexOf("sz"),Read_API_Call_Data.indexOf("&correlator="));
					 expected_data= expected_data .replace("%7C", "|");
			      String val[]=expected_data.split("&");
			      System.out.println("Size of the "+feed+i+" is  " + val[0]);
					logStep("Size of the "+feed+i+" is " + val[0]);
			
			 
		}
		
	 
		else {
			System.out.println(feed+i +"call was not trigred");
			logStep(feed+i +" call was not trigred");
		     Assert.fail(feed+i + " call was not trigred");
		}
	 }
	
	
}
public static void Verifying_detail_gampadcalls_Optoutmode() throws Exception{
	
read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//CharlesFunctions.ExportSession();
	System.out.println("Verifying  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call");
	logStep("Verifying iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad calll");
if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps")) {
	System.out.println("iu=/7646/app_android_us/db_display/details/maps call was trigred");
	logStep("iu=/7646/app_android_us/db_display/details/maps call was trigred");
}
if(!sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps")) {
	System.out.println("iu=/7646/app_android_us/db_display/details/maps call was not trigred");
	logStep("iu=/7646/app_android_us/db_display/details/maps call was not trigred");
Assert.fail("iu=/7646/app_android_us/db_display/details/maps call was not trigred");
}
}



public static Map<String, String> validating_bcp_privacy_Optoutmode_scenarion() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	logStep("Verifying for  https://bcp.crwdcntrl.net api url");
	if(sb.contains("bcp.crwdcntrl.net")) {
	logStep("https://bcp.crwdcntrl.net/ url was trigred");
	System.out.println("https://bcp.crwdcntrl.net/ url was trigred");
	Assert.fail("https://bcp.crwdcntrl.net/ url was trigred");
	
}
if(!sb.contains("bcp.crwdcntrl.net")) {
	logStep("https://bcp.crwdcntrl.net/ url was not trigred");
System.out.println("https://bcp.crwdcntrl.net/ url was not trigred");

}
return wfxtriggers_values;
}
	
public static Map<String, String> validating_adcrw_privacy_Optoutmode_scenarion()  throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	//https://ad.crwdcntrl.net
	logStep("Verifying  https://ad.crwdcntrl.net api url");
	if(sb.contains("ad.crwdcntrl.net")) {
		System.out.println("https://ad.crwdcntrl.net/ url was trigred");
		logStep("https://ad.crwdcntrl.net/ url was trigred");
		Assert.fail("https://ad.crwdcntrl.net/ url was trigred");
	
}
if(!sb.contains("ad.crwdcntrl.net")) {
	logStep("https://ad.crwdcntrl.net/ url was  not trigred");
	System.out.println("https://ad.crwdcntrl.net/ url was  not trigred");
}
return wfxtriggers_values;
}


public static Map<String, String> validating_Fatualcall_privacy_Optoutmode_scenarion() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
logStep("Verifying https://location.wfxtriggers.com api url");
	if(sb.contains("location.wfxtriggers.com")) {
		logStep("https://location.wfxtriggers.com url was  trigred");
		System.out.println("https://location.wfxtriggers.com url was  trigred");
		Assert.fail("https://location.wfxtriggers.com url was  trigred");
		}

if(!sb.contains("location.wfxtriggers.com")) {
	logStep("https://location.wfxtriggers.com url was not trigred");
System.out.println("https://location.wfxtriggers.com url was not trigred");

}
return wfxtriggers_values;
}
public static Map<String, String> validating_aax_privacy_Optoutmode_scenarion() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	if(sb.contains("aax")) {
		System.out.println("amazon aax calls  was trigred");
		Assert.fail("amazon aax calls  was trigred");
		}

if(!sb.contains("aax")) {
System.out.println("amazon aax calls was not trigred");

}
return wfxtriggers_values;
}
public static void validating_aax_privacy_Optoutmode_scenario() throws Exception{

	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	logStep("Verifying for amazon aax calls");
	//System.out.println("Slot Name is  : "+slotID);
	if(sb.contains("752a96eb-3198-4991-b572-17ec04883b6c")) {
	System.out.println("amazon aax slot id's are  trigreed for privacy Optoutmode scenario");
	logStep("amazon aax slot id's are  trigreed for privacy Optoutmode scenario");
	Assert.fail("amazon aax slot id's are  trigreed for privacy Optoutmode scenario");
	}
	if(!sb.contains("752a96eb-3198-4991-b572-17ec04883b6c")) {
	logStep("amazon aax slot id's are  not trigreed for privacy Optoutmode scenario");
	System.out.println("amazon aax slot id's are  not trigreed for privacy Optoutmode scenario");
		
		}
	

}
public static Map<String, String> finding_Homescreen_marquee_iu_value() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";

	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	logStep("checking for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call");
	System.out.println("checking for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call");
if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee")) {
	System.out.println("iu=/7646/app_android_us/db_display/home_screen/marquee call was trigred");
	logStep("iu=/7646/app_android_us/db_display/home_screen/marquee call was trigred");
}
if(!sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee")) {

	System.out.println("iu=/7646/app_android_us/db_display/home_screen/marquee call was not trigred");
	logStep("iu=/7646/app_android_us/db_display/home_screen/marquee call was not trigred");
    Assert.fail("iu=/7646/app_android_us/db_display/home_screen/marquee call was not trigred");
}
return wfxtriggers_values;
}

public static void  finding_Homescreen_marquee_iu() throws Exception{

	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	logStep("checking for iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call");
	System.out.println("checking for iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call");
if(sb.contains("iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fhome_screen%2Fmarquee")) {
	System.out.println("iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call was trigred");
	logStep("iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call was trigred");
}
if(!sb.contains("iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fhome_screen%2Fmarquee")) {

	System.out.println("iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call was not trigred");
	logStep("iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call was not trigred");
    Assert.fail("iu=%2F7646%2Ftest_app_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call was not trigred");
}
}



public static void validate_SOD_Cust_param_homescreenHourly_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying  SOD custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("sod%3D"),required_info.indexOf("%26tmp%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String SOD=expectedValues.replaceAll("%3D", "=");
			
			if(SOD.contains("no")) {
				System.out.println("SOD cust param value for   iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly  is " +SOD);
				logStep("SOD cust param value for   iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%%2Fhourly  is " +SOD);
			}
			else {
				System.out.println("SOD cust param value for   iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly   is not matched with"     +SOD);
				logStep("SOD cust param value for   iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly  is not matched with"     +SOD);
				Assert.fail("SOD cust param value for   iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly  is not matched with"     +SOD);
			}
			//System.out.println(expectedValues);
			
		}
}


public static void validate_SOD_Cust_param_homescreen_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying  SOD custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("sod%3D"),required_info.indexOf("%26tmp%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String SOD=expectedValues.replaceAll("%3D", "=");
			
			if(SOD.contains("no")) {
				System.out.println("SOD cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 is " +SOD);
				logStep("SOD cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 is " +SOD);
			}
			else {
				System.out.println("SOD cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 is not matched with"     +SOD);
				logStep("SOD cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 is not matched with"     +SOD);
				Assert.fail("SOD cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 is not matched with"     +SOD);
			}
			//System.out.println(expectedValues);
			
		}
}



public static void validate_SOD_Cust_param_homescreenmarquee_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying  SOD custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("sod%3D"),required_info.indexOf("%26tmp%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String SOD=expectedValues.replaceAll("%3D", "=");
			
			if(SOD.contains("no")) {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is " +SOD);
				logStep("SOD cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is " +SOD);
			}
			else {
				System.out.println("SOD cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is not matched with"     +SOD);
				logStep("SOD cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is not matched with"     +SOD);
				Assert.fail("SOD cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is not matched with"     +SOD);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_IM_Cust_param_homescreenmarquee() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	logStep("Verifying  IM  custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("im%3D"),required_info.indexOf("%26kw%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String im=expectedValues.replaceAll("%3D", "=");
			
			if(im.contains("y")) {
				System.out.println("IM cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is " +im);
				logStep("IM cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is " +im);
			}
			else {
				System.out.println("IM cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is not matched with"     +im);
				logStep("IM cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is not matched with"     +im);
				Assert.fail("IM cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is not matched with"     +im);
			}
			//System.out.println(expectedValues);
			
		}
}


public static void validate_SlotName_Cust_param_homescreenmarquee() throws Exception {	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	logStep("Verifying SlotName custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("slotName%3D"),required_info.indexOf("%26fdynght%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String slotName=expectedValues.replaceAll("%3D", "=");
			
			if(slotName.contains("weather.next_gen_im")) {
				System.out.println("SlotName cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is " +slotName);
				logStep("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is " +slotName);
			}
			else {
				System.out.println("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is not matched with"     +slotName);
				logStep("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is not matched with"     +slotName);
				Assert.fail("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee is not matched with"     +slotName);
			}
			//System.out.println(expectedValues);
			
		}
}



public static void validate_SlotName_Cust_param_homescreenhourly() throws Exception {	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	logStep("Verifying SlotName custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("slotName%3D"),required_info.indexOf("%26fdynght%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String slotName=expectedValues.replaceAll("%3D", "=");
			
			if(slotName.contains("weather.feed1")) {
				System.out.println("SlotName cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly is " +slotName);
				logStep("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly is " +slotName);
			}
			else {
				System.out.println("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly is not matched with"     +slotName);
				logStep("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly is not matched with"     +slotName);
				Assert.fail("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly is not matched with"     +slotName);
			}
			//System.out.println(expectedValues);
			
		}
}


public static void validate_SlotName_Cust_param_feed1() throws Exception {	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	logStep("Verifying SlotName custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("slotName%3D"),required_info.indexOf("%26fdynght%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String slotName=expectedValues.replaceAll("%3D", "=");
			
			if(slotName.contains("weather.feed2")) {
				System.out.println("SlotName cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1  is " +slotName);
				logStep("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1  is " +slotName);
			}
			else {
				System.out.println("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1  is not matched with"     +slotName);
				logStep("SlotName cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 is not matched with"     +slotName);
				Assert.fail("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1  is not matched with"     +slotName);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_SlotName_Cust_param_hourlydetails() throws Exception {	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	logStep("Verifying SlotName custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly  ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("slotName%3D"),required_info.indexOf("%26fdynght%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String slotName=expectedValues.replaceAll("%3D", "=");
			
			if(slotName.contains("weather.hourly")) {
				System.out.println("SlotName cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly  is " +slotName);
				logStep("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly  is " +slotName);
			}
			else {
				System.out.println("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly  is not matched with"     +slotName);
				logStep("SlotName cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly  is not matched with"     +slotName);
				Assert.fail("SlotName cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fhourly is not matched with"     +slotName);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_SOD_Cust_param_feed_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying  SOD custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fcard%2Fradar ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fcard%2Fradar")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fcard%2Fradar"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("sod%3D"),required_info.indexOf("%26tmp%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String SOD=expectedValues.replaceAll("%3D", "=");
			
			if(SOD.contains("no")) {
				System.out.println("SOD cust param value for Feed call is " +SOD);
				logStep("SOD cust param value for Feed call is " +SOD);
			}
			else {
				System.out.println("SOD cust param value for Feed call is not matched with"     + SOD);
				logStep("SOD cust param value for Feed call is not matched with"     + SOD);
				Assert.fail("SOD cust param value for Feed call is not matched with"     + SOD);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_SOD_Cust_param_deatiledfeed_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
//	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying  SOD custum param for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("sod%3D"),required_info.indexOf("%26tmp%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String SOD=expectedValues.replaceAll("%3D", "=");
			
			if(SOD.contains("no")) {
				System.out.println("SOD cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is " +SOD);
				logStep("SOD cust param value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is " +SOD);
			}
			else {
				System.out.println("SOD cust param value for deatiled  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is not matched with"     + SOD);
				logStep("SOD cust param value for deatiled  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is not matched with"     + SOD);
				Assert.fail("SOD cust param value for deatiled  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is not matched with"     + SOD);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_SOD_Cust_param_video_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying  SOD custum param for iu=%2F7646%2Fapp_android_us%2Fvideo ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fvideo")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fvideo"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("sod%3D"),required_info.indexOf("&amp"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String SOD=expectedValues.replaceAll("%3D", "=");
			
			if(SOD.contains("no")) {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is " +SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is " +SOD);
			}
			else {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is not matched with"     + SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is not matched with"     + SOD);
				Assert.fail("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is not matched with"     + SOD);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_RDP_homescreen_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying RDP keyword in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1  ad call url");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1"));
		
		if(sb.contains("rdp=1")){
			System.out.println("RDP value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call is " +"1");	
			logStep("RDP value for feed_1 ad call is " +"1");
			}
		else {
				System.out.println("RDP  value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call is not matched with"     +"1");
				logStep("RDP  value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call is not matched with"     +"1");
				Assert.fail("RDP  value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call is not matched with"     +"1");
			}
			
		}
}

public static void validate_RDP_homescreenmarquee_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying RDP keyword in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee  ad call url");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee"));
		
		if(sb.contains("rdp=1")){
			System.out.println("RDP value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call is " +"1");	
			logStep("RDP value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee  ad call is " +"1");
			}
		else {
				System.out.println("RDP  value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call is not matched with"     +"1");
				logStep("RDP  value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call is not matched with"     +"1");
				Assert.fail("RDP  value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call is not matched with"     +"1");
			}
			
		}
}


public static void validate_RDP_homescrenhourly_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying RDP keyword in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly  ad call url");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly"));
		
		if	(sb.contains("rdp=1")){
			System.out.println("RDP value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly ad call is " +"1");	
			logStep("RDP value for feed_1 ad call is " +"1");
			}
		else {
				System.out.println("RDP  value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly ad call is not matched with"     +"1");
				logStep("RDP  value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly ad call is not matched with"     +"1");
				Assert.fail("RDP  value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly ad call is not matched with"     +"1");
			}
			
		}
}


public static void validate_RDP_feed_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying RDP keyword in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fcard%2Fradar  ad call url");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fcard%2Fradar")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fcard%2Fradar"));
		
		if	(Read_API_Call_Data.contains("rdp=1")){
			System.out.println("RDP value for feed ad call is " +"1");	
			logStep("RDP value for feed ad call is " +"1");
			}
		else {
				System.out.println("RDP  value for feed ad call is not matched with"     +"1");
				logStep("RDP  value for feed ad call is not matched with"     +"1");
				Assert.fail("RDP for feed ad call call  is not matched with"     +"1");
			}
			
		}
}

public static void validate_RDP_detailed_feed_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying RDP keyword in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps  ad call url");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps"));
		
		if(sb.contains("rdp")){
			System.out.println("RDP value for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is " +"1");	
			logStep("RDP value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is " +"1");
			}
		else {
				System.out.println("RDP  value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is not matched with"     +"1");
				logStep("RDP  value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is not matched with"     +"1");
				Assert.fail("RDP for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call  is not matched with"     +"1");
			}
			
		}
}

public static void validate_RDP_video_ad_Optoutmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying RDP keyword in iu=%2F7646%2Fapp_android_us%2Fvideo  ad call url");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fvideo")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fvideo"));
		
		if	(sb.contains("rdp=1")){
			System.out.println("RDP value for iu=%2F7646%2Fapp_android_us%2Fvideo  ad call is " +"1");	
			logStep("RDP value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is " +"1");
			}
		else {
				System.out.println("RDP  value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is not matched with"     +"1");
				logStep("RDP  value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is not matched with"     +"1");
				Assert.fail("RDP for iu=%2F7646%2Fapp_android_us%2Fvideo ad call  is not matched with"     +"1");
			}
			
		}
}
public static void finding_Homescreen_iu_value() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";

	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	System.out.println("Verifying for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly ad call");
	logStep("Verofying for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly ad call");
if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly")) 
{
	System.out.println("iu=/7646/app_android_us/db_display/home_screen/hourly call was trigred");
	logStep("7646/app_android_us/db_display/home_screen/hourly call was trigred");
}
if(!sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly")) {
	System.out.println("iu=/7646/app_android_us/db_display/home_screen/hourly call was not trigred");
	logStep("iu=/7646/app_android_us/db_display/home_screen/hourly call was not trigred");
    Assert.fail("iu=/7646/app_android_us/db_display/home_screen/hourly call was not trigred");
}
}

public static  void selecting_opt_in_mode() throws Exception{
	
	   Thread.sleep(8000);
			Swipe_Conter(10);
	 Thread.sleep(30000);
	  //Selecting  Opt out  mode option in privacy card
		System.out.println("Selecting  Opt in  mode option in privacy card");
		logStep("Selecting  Opt in  mode option in privacy card");
	  TouchAction ta=new TouchAction(Ad);
	 ta.tap(347, 1070).perform();
	 Ad.findElementById("com.weather.Weather:id/popup_positive_button").click();
	    Thread.sleep(10000);
   //Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.widget.ListView/android.view.View[2]").click();	
}

public static Map<String, String> validating_bcp_privacy_Optinmode_scenarion() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	logStep("Verifying for https://bcp.crwdcntrl.net url ");
	if(sb.contains("bcp.crwdcntrl.net")) {
		System.out.println("https://bcp.crwdcntrl.net/ url was trigred");
		logStep("https://bcp.crwdcntrl.net/ url was trigred");

}
if(!sb.contains("bcp.crwdcntrl.net")) {
	System.out.println("https://bcp.crwdcntrl.net/ url was not trigred");
	logStep("https://bcp.crwdcntrl.net/ url was not trigred");
	Assert.fail("https://bcp.crwdcntrl.net/ url was not trigred");


}
return wfxtriggers_values;
}
	
public static Map<String, String> validating_adcrw_privacy_Optinmode_scenarion()  throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	//https://ad.crwdcntrl.net
	logStep("Verifying for https://ad.crwdcntrl.net  url ");
	if(sb.contains("ad.crwdcntrl.net")) {
		System.out.println("https://ad.crwdcntrl.net url was trigred");
		logStep("https://ad.crwdcntrl.net/ url was trigred");
}
if(!sb.contains("ad.crwdcntrl.net")) {
	System.out.println("https://ad.crwdcntrl.net/ url was not trigred");
	logStep("https://ad.crwdcntrl.net/ url was not trigred");
	Assert.fail("https://ad.crwdcntrl.net/ url was not trigred");
	
}
return wfxtriggers_values;
}
public static Map<String, String> validating_Fatualcall_privacy_Optinmode_scenarion() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	logStep("Verifying forlocation.wfxtriggers.com url ");
	if(sb.contains("location.wfxtriggers.com")) {
		System.out.println("https://location.wfxtriggers.com url was trigred");
		logStep("https://location.wfxtriggers.com url was trigred");
		}

if(!sb.contains("location.wfxtriggers.com")) {
	System.out.println("https://location.wfxtriggers.com url was not trigred");
	logStep("https://location.wfxtriggers.com url was not trigred");
	Assert.fail("https://location.wfxtriggers.com url was not trigred");


}
return wfxtriggers_values;
}
public static Map<String, String> validating_aax_privacy_Optinmode_scenarion() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	if(sb.contains("aax")) {
		System.out.println("amazon aax calls was trigred");
		}

if(!sb.contains("aax")) {
	System.out.println("amazon aax calls  was not trigred");
	Assert.fail("amazon aax calls  were not trigred");


}
return wfxtriggers_values;
}
public static void validating_aax_privacy_Optinmode_scenario() throws Exception{

	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	logStep("Verifying for amazon aax calls");
	//System.out.println("Slot Name is  : "+slotID);
	if(sb.contains("aax")) {
	System.out.println("amazon aax slot id's are  trigreed for privacy Optinmode scenario");
	logStep("amazon aax slot id's are  trigreed for privacy Optinmode scenario");
	
	}
	if(!sb.contains("869c843c-7cf8-47ae-b6ed-088057e4bc8a")) {
		System.out.println("amazon aax slot id's  are  not trigreed for privacy Optinmode scenario");
		logStep("amazon aax slot id's  are  not trigreed for privacy Optinmode scenario");
		Assert.fail("amazon aax slot id's  are  not trigreed for privacy Optinmode scenario");
		
		}
	

}
public static void validate_SOD_Cust_param_homescreen_Optinmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying the  SOD custum parameter in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("sod%3D"),required_info.indexOf("%26tmp%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String SOD=expectedValues.replaceAll("%3D", "=");
			
			if(SOD.contains("yes")) {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call is " +SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call is " +SOD);
			}
			else {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call is not matched with"     +SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call is not matched with"     +SOD);
				Assert.fail("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call is not matched with"     +SOD);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_SOD_Cust_param_homescreenhourly_Optinmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying the  SOD custum parameter in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("sod%3D"),required_info.indexOf("%26tmp%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String SOD=expectedValues.replaceAll("%3D", "=");
			
			if(SOD.contains("yes")) {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly call is " +SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly call is " +SOD);
			}
			else {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly call is not matched with"     +SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly call is not matched with"     +SOD);
				Assert.fail("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly call is not matched with"     +SOD);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_SOD_Cust_param_feed_Optinmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying the  SOD custum parameter in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("sod%3D"),required_info.indexOf("%26tmp%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String SOD=expectedValues.replaceAll("%3D", "=");
			
			if(SOD.contains("yes")) {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 call is " +SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 call is " +SOD);
			}
			else {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 call is not matched with"     + SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 call is not matched with"     + SOD);
				Assert.fail("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 call is not matched with"     + SOD);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_SOD_Cust_param_deatiledfeed_Optinmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying the  SOD custum parameter in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("sod%3D"),required_info.indexOf("%26tmp%3D"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String SOD=expectedValues.replaceAll("%3D", "=");
			
			if(SOD.contains("yes")) {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is " +SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is " +SOD);
			}
			else {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is not matched with"     + SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call is not matched with"     + SOD);
				Assert.fail("SOD cust param value for deatiled iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps call is not matched with"     + SOD);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_SOD_Cust_param_video_Optinmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying the  SOD custum parameter in iu=%2F7646%2Fapp_android_us%2Fvideo call");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fvideo")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fvideo"));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("cust_params="));
			String expected_data = required_info.toString().substring(required_info.indexOf("sod%3D"),required_info.indexOf("&amp"));
			
			//6sod%3Dno%
			String expectedValues = expected_data.toString();
			String SOD=expectedValues.replaceAll("%3D", "=");
			
			if(SOD.contains("yes")) {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is " +SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is " +SOD);
			}
			else {
				System.out.println("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is not matched with"     + SOD);
				logStep("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is not matched with"     + SOD);
				Assert.fail("SOD cust param value for iu=%2F7646%2Fapp_android_us%2Fvideo ad call is not matched with"     + SOD);
			}
			//System.out.println(expectedValues);
			
		}
}

public static void validate_RDP_homescreen_Optinmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying RDP keyword in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee ad call url");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee"));
		
		if	(sb.contains("rdp=1")){
			System.out.println("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call url");	
			logStep("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call url");
			Assert.fail("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call url");
			}
		else {
			System.out.println("RDP key word is not preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call url");
          logStep("RDP key word is not preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee call url");
			}
			
		}
}

public static void validate_RDP_homescreenhourly_Optinmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying RDP keyword in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly ad call url");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly"));
		
		if(sb.contains("rdp=1")){
			System.out.println("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly call url");	
			logStep("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly call url");
			Assert.fail("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly call url");
			}
		else {
			System.out.println("RDP key word is not preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly call url");
          logStep("RDP key word is not preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fhourly call url");
			}
			
		}
}


public static void validate_RDP_feed_Optinmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying RDP keyword in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1  ad call url");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1"));
		
		if(sb.contains("rdp=1")){
			System.out.println("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call url");	
			logStep("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call url");
			Assert.fail("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call url");
			}
		else {
				System.out.println("RDP key word is not preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call url");
				logStep("RDP key word is not preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Ffeed%2Ffeed_1 ad call url");
			
			}
			
		}
}

public static void validate_RDP_detailed_feed_Optinmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying RDP keyword in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps  ad call url");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps"));
		
		if(sb.contains("rdp=1")){
			System.out.println("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call url");	
			logStep("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call url");
			Assert.fail("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call url");
			}
		else {
			System.out.println("RDP key word is not preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call url");
			logStep("RDP key word is not preseted in iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fdetails%2Fmaps ad call url");
			}
			
		}
}

public static void wiatfor5secindetails() throws Exception {
	System.out.println("wait for 5 sec in details page");
	Thread.sleep(20000);
}

public static Map<String, String> Verify_hourly_detailpage_interstitial_adcall() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	System.out.println("checking for hourly  details interstitial ad call");
	Drivers.logStep("checking for hourly  details interstitial ad call");
	if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2Fhourly")) {
	System.out.println("iu=/7646/app_android_us/db_display/interstitial/hourly call was trigred");
	Drivers.logStep("iu=/7646/app_android_us/db_display/interstitial/hourly call was trigred");
	System.out.println("continue further validations");
	logStep("continue further validations");
}
if(!sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2Fhourly")) {
System.out.println("iu=/7646/app_android_us/db_display/interstitial/hourlycall was not trigred");
Drivers.logStep("iu=/7646/app_android_us/db_display/interstitial/hourly call was not trigred");
Assert.fail("iu=/7646/app_android_us/db_display/interstitial/hourly call was not trigred");
System.out.println("no need for further validations");
}

return wfxtriggers_values;
}
public static void click_home_element() throws Exception
{
	System.out.println("clicking the homescreen");
	logStep("clicking the homescreen");
try {
	List<WebElement> ele=Ad.findElementsById("com.weather.Weather:id/icon");
	ele.get(2).click();
	Thread.sleep(2000);
//Ad.findElementsById("com.weather.Weather:id/icon").get(2).click();
}
catch(Exception e) {
	Ad.findElementByAccessibilityId("Personalized home screen").click();
	Thread.sleep(2000);
}
}
public static void handleInterstailads() throws Exception {
	System.out.println("checking interstitial ad presented or not on the device");
	try {
	if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[8]").isDisplayed())
	{
		System.out.println("Intersitial ad was dispalyed on the screen");
	}
		
	}
	catch(Exception e1) {
		try {
			if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[6]").isDisplayed())
			{
				System.out.println("Intersitial ad was dispalyed on the screen");
			
			}}
		catch(Exception e2) {
			try {
				if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.view.View/android.view.View[5]/android.view.View").isDisplayed())
				{
					System.out.println("Intersitial ad was dispalyed on the screen");
				}}
			catch(Exception e3) {
				try {
					if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[5]/android.view.View").isDisplayed())
					{
						System.out.println("Intersitial ad was dispalyed on the screen");
					}}
				catch(Exception e5) {
					try {
						if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[5]/android.view.View").isDisplayed())
						{
							System.out.println("Intersitial ad was dispalyed on the screen");
						}}
					catch(Exception e8) {
						try {
							if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View[2]/android.view.View[4]/android.view.View").isDisplayed())
							{
								System.out.println("Intersitial ad was dispalyed on the screen");
							}}
						catch(Exception e9) {
							try {
								if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[5]").isDisplayed())
								{
									System.out.println("Intersitial ad was dispalyed on the screen");
								}}
							catch(Exception e10) {
								try {
									if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[1]").isDisplayed())
									{
										System.out.println("Intersitial ad was dispalyed on the screen");
									}}
								catch(Exception e12) {
									try {
										if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[8]/android.view.View/android.view.View").isDisplayed())
										{
											System.out.println("Intersitial ad was dispalyed on the screen");
										}}
									catch(Exception e14) {
										try {
											if(Ad.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Interstitial close button\"]").isDisplayed())
											{
												System.out.println("Intersitial ad was dispalyed on the screen");
											}}
										catch(Exception e15) {
					System.out.println("Intersitial ad was not dispalyed on the screen");
					System.out.println("no need to go same details page and check interstitial ad call");
					Assert.fail("Intersitial ad was not dispalyed on the screen");
										}
									}
								}
								}
						}
					}
				}
			}
		}
	}
	

	
}

public static void handleInterstailadss() throws Exception {
	attachScreen();
	System.out.println("checking interstitial ad presented or not on the device");
	logStep("checking interstitial ad presented or not on the device");
	Thread.sleep(3000);
	logStep("Intersitial ad was dispalyed on the screen");
	System.out.println("Intersitial ad was dispalyed on the screen");
}

public static void closeInterstailadss() throws Exception {
	System.out.println("closing the interstial ad on screen");
logStep("closing the interstial ad on screen");
Thread.sleep(3000);
System.out.println("closed the interstial ad on screen");
logStep("closed the interstial ad on screen");
attachScreen();

}


public static void closeInterstailads() throws Exception {
	
	  System.out.println("close the interstial ad on screen");
		try {
			if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[8]").isDisplayed())
			{
				Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[8]").click();
			}
			}
			catch(Exception e1) {
				try {
					if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[6]").isDisplayed())
					{
						Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[6]").click();
					}}
				catch(Exception e2) {
					try {
						if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.view.View/android.view.View[5]/android.view.View").isDisplayed())
						{
							Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View[3]/android.view.View/android.view.View[5]/android.view.View").click();
						}}
					catch(Exception e3) {
						try {
							if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[5]/android.view.View").isDisplayed())
							{
								Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[5]/android.view.View").click();
							}}
						catch(Exception e5) {
							try {
								if(Ad.findElementByAccessibilityId("Interstitial close button").isDisplayed())
								{
									Ad.findElementByAccessibilityId("Interstitial close button").click();
								}}
							catch(Exception e6) {
								try {
									if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View[2]/android.view.View[4]/android.view.View").isDisplayed())
									{
										Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View[2]/android.view.View[4]/android.view.View").click();
									}}
								catch(Exception e9) {
									try {
										if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[5]").isDisplayed())
										{
											Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[5]").click();
										}}
									catch(Exception e11) {
										try {
											if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[1]").isDisplayed())
											{
												Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[1]").click();
											}}
										catch(Exception e12) {
											try {
												if(Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[8]/android.view.View/android.view.View").isDisplayed())
												{
													Ad.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[8]/android.view.View/android.view.View").click();
												}}
											catch(Exception e13) {
												try {
													if(Ad.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Interstitial close button\"]").isDisplayed())
													{
														Ad.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Interstitial close button\"]").click();
													}}
												catch(Exception e14) {
													try {
													
															Ad.findElementByAccessibilityId("Interstitial close button").click();
														}
													catch(Exception e15) {
							System.out.println("Intersitial ad was not dispalyed on the screen");
													}
												}
											}
										}
									}
								}
							}
						}
					}
				
				}
				}

	}

public static void settheTimer() throws Exception {
	logStep("current system time");
	System.out.println("current system time");
	long millis=System. currentTimeMillis();
	java. util. Date date=new java. util. Date(millis);
	//logStep(date);
	System. out. println(date);
	logStep("wait for 3 minutes for getting agian interstitial ad call");
	System.out.println("wait for 3 minutes for getting again interstitial ad call");
	Thread.sleep(180000);
	logStep("completed the 3 minutes need to go same details");
	System.out.println("completed the 3 minutes need to go same details");
	long millis1=System. currentTimeMillis();
	java. util. Date date1=new java. util. Date(millis1);
	//logStep(date1);
	System. out. println(date1);
    logStep("current system time");
	System.out.println("current system time");
	logStep("current system time");
}

public static void settheTimerr() throws Exception {
	logStep("current system time");
	System.out.println("current system time");
	long millis=System. currentTimeMillis();
	java. util. Date date=new java. util. Date(millis);
	//logStep(date);
	System. out. println(date);
	logStep("wait for 1 minute for getting agian interstitial ad call");
	System.out.println("wait for 1 minute for getting again interstitial ad call");
	Thread.sleep(60000);
	logStep("completed the 1 minute need to go same details");
	System.out.println("completed the 1 minute need to go same details");
	long millis1=System. currentTimeMillis();
	java. util. Date date1=new java. util. Date(millis1);
	//logStep(date1);
	System. out. println(date1);
    logStep("current system time");
	System.out.println("current system time");
	logStep("current system time");
}

public static Map<String, String> Verify_daily_detailpage_interstitial_adcall() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2F10day")) {
	System.out.println("iu=/7646/app_android_us/db_display/interstitial/10day call was trigred");
	System.out.println("continue further validations");
	logStep("continue further validations");
}
if(!sb.contains("%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2F10day")) {
System.out.println("iu=/7646/app_android_us/db_display/interstitial/10day call was not trigred");
System.out.println("no need to go further validations");
logStep("no need for further validations");

Assert.fail("iu=/7646/app_android_us/db_display/interstitial/10day call was not trigred");
}
return wfxtriggers_values;
}
public static Map<String, String> Verify_daily_detailpage_interstitial_adcall1() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2F10day")) {
	System.out.println("/7646/app_android_us/db_display/interstitial/10day call was trigred");
}
if(!sb.contains("%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2F10day")) {
System.out.println("/7646/app_android_us/db_display/interstitial/10day call was not trigred");
Assert.fail("/7646/app_android_us/db_display/interstitial/10day call was not trigred");
}
return wfxtriggers_values;
}
public static Map<String, String> Verify_hourly_detailpage_interstitial_adcall1() throws Exception{

	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	System.out.println("chekcking for hourly  details interstitial ad call");
	Drivers.logStep("chekcking for hourly  details interstitial ad call");
	if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2Fhourly")) {
	System.out.println("/7646/app_android_us/db_display/interstitial/hourly call was trigred");
	Drivers.logStep("/7646/app_android_us/db_display/interstitial/hourly call was trigred");

}
if(!sb.contains("%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2Fhourly")) {
System.out.println("/7646/app_android_us/db_display/interstitial/hourly call was not trigred");
Drivers.logStep("/7646/app_android_us/db_display/interstitial/hourly call was not trigred");
Assert.fail("/7646/app_android_us/db_display/interstitial/hourly call was not trigred");

}

return wfxtriggers_values;
}

public static void validate_RDP_video_ad_Optinmode() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	logStep("Verifying RDP keyword in iu=%2F7646%2Fapp_android_us%2Fvideo  ad call url");
		if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fvideo")){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fvideo"));
		
		if	(sb.contains("rdp=1")){
			System.out.println("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fvideo ad call url");	
			logStep("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fvideo ad call url");
			Assert.fail("RDP key word preseted in iu=%2F7646%2Fapp_android_us%2Fvideo ad call url");
			}
		else {
			System.out.println("RDP key word preseted in not  presented in iu=%2F7646%2Fapp_android_us%2Fvideo call url");	
			logStep("RDP key word preseted in not presented iu=%2F7646%2Fapp_android_us%2Fvideo call url");
			}
			
		}
}
public static void Verify_maps_detailpage_interstitial_adcall() throws Exception{
System.out.println("checking for maps details interstitial ads");
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2Fmaps")) {
	System.out.println("iu=/7646/app_android_us/db_display/interstitial/maps call was trigred");
	logStep("iu=/7646/app_android_us/db_display/interstitial/maps call was trigred");
	System.out.println("continue for further validations");
	logStep("continue for further validations");
	
}
if(!sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2Fmaps")) {
System.out.println("iu=/7646/app_android_us/db_display/interstitial/maps call was not trigred");
logStep("iu=/7646/app_android_us/db_display/interstitial/maps call was not trigred");
System.out.println("no need to do further validations");
logStep("no need to do further validations");
Assert.fail("iu=/7646/app_android_us/db_display/interstitial/maps call was not trigred");

}

}

public static void Verify_maps_detailpage_interstitial_adcall1() throws Exception{
System.out.println("checking for maps details interstitial ads");
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();	
	if(sb.contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2Fmaps")) {
	System.out.println("/7646/app_android_us/db_display/interstitial/maps call was trigred");
	logStep("/7646/app_android_us/db_display/interstitial/maps call was trigred");
	
	
}
if(!sb.contains("%2F7646%2Fapp_android_us%2Fdb_display%2Finterstitial%2Fmaps")) {
System.out.println("/7646/app_android_us/db_display/interstitial/maps call was not trigred");
Assert.fail("/7646/app_android_us/db_display/interstitial/maps call was not trigred");
logStep("/7646/app_android_us/db_display/interstitial/maps call was not trigred");
}

}
public static void Verify_video_detailpage_interstitial_adcall() throws Exception{
    System.out.println("Checking for video interstitial ad call");
    logStep("Checking for video interstitial ad call");
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	
	//iu=%2F7646%2Fapp_android_us%2Fvideo
	///7646/app_android_us/interstitial/video
	if(sb.contains("iu=%2F7646%2Fapp_android_us%2Finterstitial%2Fvideo")) {
	System.out.println("/7646/app_android_us/interstitial/video call was trigred");
	logStep("/7646/app_android_us/interstitial/video call was trigred");
	System.out.println("continue further validations");
	logStep("continue further validations");
	
	
}
if(!sb.contains("=%2F7646%2Fapp_android_us%2Finterstitial%2Fvideo")) {
System.out.println("/7646/app_android_us/interstitial/video call was not trigred");
Assert.fail("/7646/app_android_us/interstitial/video call was not trigred");
logStep("/7646/app_android_us/interstitial/video call was not trigred");
System.out.println("no need for further validations");
logStep("no need for further validations");
}

}
public static void click_video_interstitial() throws Exception {
	Ad.findElementByAccessibilityId("Isaias").click();
	Thread.sleep(3000);
}
public static void Verify_video_detailpage_interstitial_adcall1() throws Exception{
    System.out.println("Checking for video interstitial ad call");
    logStep("Checking for video interstitial ad call");
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	
	//iu=%2F7646%2Fapp_android_us%2Fvideo
	///7646/app_android_us/interstitial/video
	if(sb.contains("iu=%2F7646%2Fapp_android_us%2Finterstitial%2Fvideo")) {
	System.out.println("iu=/7646/app_android_us/interstitial/video call was trigred");
	logStep("iu=/7646/app_android_us/interstitial/video call was trigred");	
}
if(!sb.contains("iu=%2F7646%2Fapp_android_us%2Finterstitial%2Fvideo")) {
System.out.println("iu=/7646/app_android_us/interstitial/video call was not trigred");
logStep("iu=/7646/app_android_us/interstitial/video call was not trigred");
Assert.fail("iu=/7646/app_android_us/interstitial/video call was not trigred");

}

}

public static void dailydetailsintegrated_adcall_response() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("idd");
	 
	if(sb.contains(exceldata[8][1])) {
		//bgEvent:'adBg'
		System.out.println("got the response" + exceldata[8][1] +" for daily details integrated  ad call");	
		logStep("got the response" + exceldata[8][1] +" for daily details integrated  ad call");
		//Check_marquee_ad();
	}
	if(!sb.contains(exceldata[8][1]))
	 {
		System.out.println(exceldata[8][1]);
		System.out.println("did't get  the response" + exceldata[8][1] +"  for daily details integrated  ad call");	
		logStep("did't get  the response" + exceldata[8][1] +"  for daily details integrated  ad call");
Assert.fail("did't get  the response" + exceldata[8][1] +"  for daily details integrated  ad call");
		
	
	}
	 
	 }

public static void NextGenIm_adcall_response() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	 
	if(sb.contains(exceldata[8][1])) {
		//bgEvent:'adBg'
		System.out.println("got the response " + exceldata[8][1] +" for NextGenIM  ad call");	
		logStep("got the response " + exceldata[8][1] +"  for NextGenIM  ad call");	
		//Check_marquee_ad();
	}
	if(!sb.contains(exceldata[8][1]))
	 {
		System.out.println(exceldata[8][1]);
		System.out.println("did't get  the response " + exceldata[8][1] +"   for NextGenIM  ad calll");	
		logStep("did't get  the response " + exceldata[8][1] +"  for NextGenIM  ad call");
Assert.fail("did't get  the response " + exceldata[8][1] +"   for NextGenIM  ad callaqR67");
		
	
	}
	 
	 }

public static void dailydetailsintegratedVideo_adcall_response() throws Exception {
	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("Integratedfeedcard");
	 
	if(sb.contains(exceldata[19][1])) {
		//bgEvent:'adBg'
		System.out.println("got the response for daily details integrated  ad call");
	//	Check_marquee_ad();
	}
	if(!sb.contains(exceldata[19][1]))	
	 {
		System.out.println("did't  get the response for daily details integrated  ad calll");

		try {
		//Check_marquee_ad();
		}
		finally {
	    Assert.fail("did't the response for marquee ad call");
		}
	}
	 
	 }

public static void integratedfeed_adcall_response() throws Exception {	
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("Integratedfeedcard");
	 
	if(sb.contains(exceldata[8][1])) {
		//bgEvent:'adBg'
		System.out.println("got the response for integrated feed card ad call");
		Check_integratedfeedad_ad();
	}
	if(!sb.contains(exceldata[8][1]))	
	 {
		System.out.println("did't  the response for integrated feed  card ad call");

		try {
			Check_integratedfeedad_ad();
		}
		finally {
	    Assert.fail("did't the response for  Integrated Feed Card  ad call");
		}
	}
	 
	 }

public static void Check_integratedfeedad_ad() throws Exception
{  
WebElement feedad=null;
try{
System.out.println("Checking for Integrated feed card on UI");
logStep("Checking for Integrated feed card on UI");
feedad=Ad.findElementByClassName("android.webkit.WebView");
Thread.sleep(5000);
if(feedad.isDisplayed())
{
System.out.println("Integrated Feed Card ad present on UI");
logStep("Integrated Feed Card ad present on UI");
AppiumFunctions.ScreenShot("IntegratedFeedCard ad","Passed");
attachScreen();
System.out.println("took the passed Integrated Feed Card ad screen shot");
}    
}
catch(Exception e)
{	
AppiumFunctions.ScreenShot("IntegratedFeedCard ad","Failed");
attachScreen();
System.out.println("took the failed Integrated Feed Card ad  screen shot");
logStep("took the failed Integrated Feed Card ad  screen shot");
Assert.fail("Integrated Feed Card ad is not presenon UI");
}	
} 

public static void Check_idd() throws Exception
{  
WebElement feedad=null;
try{
System.out.println("Checking for IDD ad on UI");
logStep("Checking for IDD ad on UI");
feedad=Ad.findElementByClassName("android.webkit.WebView");
Thread.sleep(5000);
if(feedad.isDisplayed())
{
System.out.println("IDD ad diaplyed on the screen");
logStep("IDD ad diaplyed on the screen");
AppiumFunctions.ScreenShot("M ad","Passed");
attachScreen();
System.out.println("took the passed IDD ad screen shot");
logStep("took the passed IDD ad screen shot");
}    
}
catch(Exception e)
{	
AppiumFunctions.ScreenShot("IDD ad","Failed");
attachScreen();
System.out.println("took the failed IM ad screen shot");
Assert.fail("IM` ad is not present");
}	
} 
public static void validate_BG_adcall_Integratedfeedcard()throws Exception {
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("Integratedfeedcard");
	if(sb.contains(exceldata[7][1])) {
		System.out.println(exceldata[7][1]+" Background call was generated for Integrated Feed Card ad");
		logStep(exceldata[7][1]+" Background call was generated for Integrated Feed Card ad ");
	}	

		if(!sb.contains(exceldata[7][1]))
			System.out.println(exceldata[7][1]+" Background call is not generated for Integrated Feed Card ad");
		logStep(exceldata[7][1]+" Background call is not generated for  Integrated Feed Card ad");
		Assert.fail(exceldata[7][1]+" Background call is not generated for  Integrated Feed Card ad");
	}
public static void validate_BG_adcall_Integratedfeedcardvideo()throws Exception {
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("Integratedfeedcard");
	if(sb.contains(exceldata[19][1])) {
		System.out.println(exceldata[7][1]+" Background call was generated for  Integrated Feed Card Video");
		logStep(exceldata[19][1]+" Background call was generated for  Integrated Feed Card Video ");
	}	

		if(!sb.contains(exceldata[19][1]))
			System.out.println(exceldata[19][1]+" Background call is not generated for Integrated Feed Card ad");
		logStep(exceldata[19][1]+" Background call is not generated for  Integrated Feed Card Video");
		Assert.fail(exceldata[19][1]+" Background call is not generated for   Integrated Feed Card Video");
	}
public static void validate_FG_adcall_Integratedfeedcard() throws Exception {
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("Integratedfeedcard");
	if(sb.contains(exceldata[6][1])) {		
		System.out.println(exceldata[6][1]+ " Foreground call was generated for Integrated Feed Card  ad");		
		logStep(exceldata[6][1]+ " Foreground call was generated for Integrated Feed Card  ad");
	}
	
	if(!sb.contains(exceldata[6][1])) {
		System.out.println(exceldata[6][1]+" Foreground call is not generated for Integrated Feed Card ad");
		Assert.fail(exceldata[6][1]+" Foreground call is not generated for Integrated Feed Card ad");
	}
	}

public static void validate_FG_adcall_Integratedfeedcardvideo() throws Exception {
	Map<String , String> wfxtriggers_values = new HashMap<String, String>();
	String wxtgValues="";
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("Integratedfeedcard");
	if(sb.contains(exceldata[20][1])) {		
		System.out.println(exceldata[20][1]+ " Foreground call was generated for  Integrated Feed Card Video");		
		logStep(exceldata[20][1]+ " Foreground call was generated for Integrated Feed Card Video");
	}
	
	if(!sb.contains(exceldata[20][1])) {
		System.out.println(exceldata[20][1]+" Foreground call is not generated for Integrated Feed Card Video");
		Assert.fail(exceldata[20][1]+" Foreground call is not generated for Integrated Feed Card Video");
	}
	}

/*public static void validate_a3p_marquee() throws Exception {
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("Watsonmomentweekahead");
	if(sb.toString().contains("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee")){
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf("iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fhome_screen%2Fmarquee"));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf("a3p"));
		//String expected_data = required_info.toString().substring(required_info.indexOf("a3p"),required_info.indexOf());
		//String expectedValues = expected_data.toString();
		//String marqueeadsize=expectedValues.replaceAll(exceldata[4][1], "");
		if(marqueeadsize.equalsIgnoreCase(exceldata[3][1])) {
			System.out.println(" ad call size is:::"  + marqueeadsize);
		}
		else {
			System.out.println("ad call size is not matched with"     + exceldata[3][1]);
			Assert.fail("ad call size is not matched with"+ exceldata[3][1]);
		}
		System.out.println(expectedValues);
		
	}
	}*/


public static void validate_Size_integratedfeedCardad() throws Exception {
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("Integratedfeedcard");
	if(sb.toString().contains(exceldata[1][1])){
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[1][1]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[2][1]));
		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[2][1]),required_info.indexOf(exceldata[5][1]));
		String expectedValues = expected_data.toString();
		String Integratedcard=expectedValues.replaceAll(exceldata[4][1], "");
		if(Integratedcard.equalsIgnoreCase(exceldata[3][1])) {
			System.out.println("Integrated feed card ad call size is:::"  + Integratedcard);
		}
		else {
			System.out.println("Integrated feed card ad call size is not matched with"     + exceldata[3][1]);
			Assert.fail("\"Integrated feed card ad call size is not matched with"+ exceldata[3][1]);
		}
		System.out.println(expectedValues);
		
	}
	}

public static void validate_Size_iNextGenIM() throws Exception {
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
	String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();
	String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
	if(sb.toString().contains(exceldata[1][1])){
		String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[1][1]));
		String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[2][1]));
		String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[2][1]),required_info.indexOf(exceldata[5][1]));
		String expectedValues = expected_data.toString();
		String NextGenIm=expectedValues.replaceAll(exceldata[4][1], "");
		if(NextGenIm.equalsIgnoreCase(exceldata[3][1])) {
			System.out.println("NextGenIM ad call  size is:::"  + NextGenIm);
			logStep("NextGenIm ad call  size is:::"  + NextGenIm);
		}
		else {
			System.out.println("NextGenIM ad call size is not matched with"     + exceldata[3][1]);
			logStep("NextGenIM ad call size is not matched with"     + exceldata[3][1]);
			Assert.fail("NextGenIM ad call size is not matched with"     + exceldata[3][1]);
		}
		System.out.println(expectedValues);
		
	}
	}




public static void validate_aax_bid_value_with_gampad_bid_value( String sheetName,boolean clearList) throws Exception {
	//readExcelValues.excelValues(excelName, sheetName);
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	String[][] data = read_excel_data.exceldataread(sheetName);
	String slotID =data[12][Cap];
	// String feedCall = readExcelValues.data[18][Cap];
	String feedCall = null;
	// "iu=%2F7646%2Fapp_iphone_us%2Fdb_display%2Fhome_screen%2Ftoday";
	if (sheetName.equalsIgnoreCase("PreRollVideo")) {
		feedCall = videoIUValue;
	} 
	else if(sheetName.equalsIgnoreCase("News(details)")) {
		feedCall = return_iu_value_of_Feedcall( sheetName);
	}
	else {
		feedCall = data[11][Cap];
	}

	boolean testpass = false;
	String cust_param = "amzn_b";

	if (sheetName.contains("PreRollVideo")) {
		cust_param = "amzn_vid";
	}

	get_amazon_bid_values_from_aaxCalls(slotID, clearList);

	if (aaxcallsSize == 0) {
		System.out.println("amazon aax " + sheetName
				+ " call is not generated in current session, so skipping the bid value verification");
		logStep("amazon aax " + sheetName
				+ " call is not generated in current session, so skipping the bid value verification");

	} else if (aaxbidErrorCount == aaxcallsResponseSize) {
		System.out.println("amazon aax " + sheetName
				+ " call response contains error i.e. bidding is not happened in current session, so skipping the bid value verification");
		logStep("amazon aax " + sheetName
				+ " call response contains error i.e. bidding is not happened in current session, so skipping the bid value verification");

	} else if (nextGenIMadDisplayed && sheetName.equalsIgnoreCase("Pulltorefresh")) {
		/*
		 * There may be chances that gampad call might not generated.. for ex: when IM
		 * ad displayed on home screen, then homescreen today call doesnt generate
		 * 
		 */
		System.out
				.println("Since IM Ad displayed on App Launch, Homescreen Today call bid id validation is skipped");
		logStep("Since IM Ad displayed on App Launch, Homescreen Today call bid id validation is skipped");
	} else {
		get_amazon_bid_values_from_gampadCalls(feedCall, cust_param);
		/*
		 * below checks whether the gampad call exists or not before validating for
		 * amazon bid value..
		 */
		if (!isaaxgampadcallexists) {
			System.out.println("Ad Call :" + feedCall + " not found in charles session, hence Custom Parameter: "
					+ cust_param + " validation failed");
			logStep("Ad Call :" + feedCall + " not found in charles session, hence Custom Parameter: " + cust_param
					+ " validation failed");
			Assert.fail("Ad Call :" + feedCall + " not found in charles session, hence Custom Parameter: "
					+ cust_param + " validation failed");
		} else if(customParamsList.size() == 0) {
			System.out.println("Ad Call :" + feedCall + " not contains the Custom Parameter: "+cust_param+", hence Custom Parameter: "
					+ cust_param + " validation failed");
			logStep("Ad Call :" + feedCall + " not contains the Custom Parameter: "+cust_param+", hence Custom Parameter: "
					+ cust_param + " validation failed");
			Assert.fail("Ad Call :" + feedCall + " not contains the Custom Parameter: "+cust_param+", hence Custom Parameter: "
					+ cust_param + " validation failed");
		}else {
			for (int i = 0; i < listOf_b_Params.size(); i++) {
				for (int j = 0; j < customParamsList.size(); j++) {
					if (listOf_b_Params.get(i).equalsIgnoreCase(customParamsList.get(j))) {
						testpass = true;
						System.out.println("amazon aax " + sheetName
								+ " call bid value is matched with corresponding gampad call bid value");
						logStep("amazon aax " + sheetName
								+ " call bid value is matched with corresponding gampad call bid value");
						break;

					}

				}
				if (testpass == true) {
					break;
				}
			}
		}
		if (testpass == false) {
			System.out.println("amazon aax " + sheetName
					+ " call bid value is not matched with corresponding gampad call bid value");
			logStep("amazon aax " + sheetName
					+ " call bid value is not matched with corresponding gampad call bid value");
			Assert.fail("amazon aax " + sheetName
					+ " call bid value is not matched with corresponding gampad call bid value");
		}

	}
}


public static String return_iu_value_of_Feedcall(String sheetName) throws Exception {
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	String[][] data = read_excel_data.exceldataread(sheetName);
	boolean adCallFound = false;
	videoIUValue = null;
	CharlesFunctions.outfile = new File(System.getProperty("user.dir") + "/myoutputFile.xml");
	// Read the content form file
	File fXmlFile = new File(CharlesFunctions.outfile.getName());

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	dbFactory.setValidating(false);
	dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
	// dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/validation", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	Document doc = dBuilder.parse(fXmlFile);
	// Getting the transaction element by passing xpath expression
	NodeList nodeList = doc.getElementsByTagName("transaction");
	String xpathExpression = "charles-session/transaction/@query";
	List<String> getQueryList = evaluateXPath(doc, xpathExpression);

	//String iuId = null;
	iuId = null;
	String iuValue = null;

	String tempCustmParam = null;
	for (String qry : getQueryList) {
		if (qry.contains("iu=")) {
			adCallFound = true;
			tempCustmParam = getNonCustomParamBy_iu_value(qry, "iu");
			// if (!"".equals(tempCustmParam))
			// customParamsList.add(getCustomParamsBy_iu_value(qry));
			break;
		}
	}
	try {
		iuValue = tempCustmParam.replace("/", "%2F");
		iuValue = "iu=" + iuValue;
		if (sheetName.equalsIgnoreCase("PreRollVideo")) {
			videoIUValue = iuValue;
			iuId = iuValue;
		} else {
			iuId = iuValue;
		}
	} catch (Exception e) {
		System.out.println("There is an exception while framing iu value");
		logStep("There is an exception while framing iu value");
	}

	if (!adCallFound) {
		System.out.println("Ad Call not found in charles session");
		logStep("Ad Call not found in charles session");
		//Assert.fail("Ad Call not found in charles session");
	} else if (iuValue == null || iuValue.isEmpty()) {
		System.out.println("Ad Call not found/no value in ad call");
		logStep("Ad Call not found/no value in ad call");
		//Assert.fail("Ad Call not found/no value in ad call");
	} else {
		System.out.println("Ad Call " + iuId + " found in charles session");
		logStep("Ad Call " + iuId + " found in charles session");
	}
	return iuId;

}



//get b value from gampad calls of XML File and store to list
	public static void get_amazon_bid_values_from_gampadCalls(String feedCall, String cust_param) throws Exception {
		// readExcelValues.excelValues(excelName, sheetName);

		// Read the content form file
		File fXmlFile = new File(CharlesFunctions.outfile.getName());
		customParamsList.clear();
		isaaxgampadcallexists = false;

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(false);
		dbFactory.setNamespaceAware(true);
		dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
		dbFactory.setFeature("http://xml.org/sax/features/validation", false);
		dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
		dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(fXmlFile);
		// Getting the transaction element by passing xpath expression
		// NodeList nodeList = doc.getElementsByTagName("transaction");
		String xpathExpression = "charles-session/transaction/@query";
		List<String> getQueryList = evaluateXPath(doc, xpathExpression);

		// Getting custom_params amzn_b values
		// List<String> customParamsList = new ArrayList<String>();

		// String iuId =
		// "iu=%2F7646%2Fapp_iphone_us%2Fdb_display%2Fhome_screen%2Ftoday";
		// String iuId = "iu=%2F7646%2Fapp_iphone_us%2Fdb_display%2Fcard%2Fdaily";

		for (String qry : getQueryList) {
			if (qry.contains(feedCall)) {
				isaaxgampadcallexists = true;
				String tempCustmParam = getCustomParamBy_iu_value(qry, cust_param);
				if (!"".equals(tempCustmParam)) {
					customParamsList.add(getCustomParamBy_iu_value(qry, cust_param));
				} else {

				}

			}
		}
		if (!isaaxgampadcallexists) {
			System.out.println("Corresponding gampad call " + feedCall + " is not generated..");
			logStep("Corresponding gampad call " + feedCall + " is not generated..");
		} else {
			System.out.println(customParamsList);
			logStep(customParamsList.toString());
		}

	}

	// this retrives amazon bid values of specific call from amazon calls and add to
		// list
		public static void load_amazon_bid_values_from_aaxCalls(String sheetName, boolean clearList)
				throws Exception {
			DeviceStatus device_status = new DeviceStatus();
			int Cap = device_status.Device_Status();
			String[][] data = read_excel_data.exceldataread(sheetName);
			String slotID =data[12][Cap];
			get_amazon_bid_values_from_aaxCalls(slotID, clearList);
		}
		
		public static void NavigatetoNews(String ModuleName)
				throws Exception {
			
		}
public static void get_amazon_bid_values_from_aaxCalls(String slotID, boolean clearList) throws Exception {

	// readExcelValues.excelValues(excelName, sheetName);

	// Read the content form file
	File fXmlFile = new File(CharlesFunctions.outfile.getName());
	if (clearList) {
		listOf_b_Params.clear();
		aaxcallsSize = 0;
	}

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	dbFactory.setValidating(false);
	dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
	dbFactory.setFeature("http://xml.org/sax/features/validation", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	Document doc = dBuilder.parse(fXmlFile);
	// Getting the transaction element by passing xpath expression
	NodeList nodeList = doc.getElementsByTagName("transaction");

	// Read JSONs and get b value
	// List<String> jsonBValuesList = new ArrayList<String>();

	// String slotId = readExcelValues.data[21][Cap];

	// String slotId = "c4dd8ec4-e40c-4a63-ae81-8f756793ac5e";

	boolean flag = false;
	List<String> istofRequestBodies = new ArrayList<String>();
	List<String> istofResponseBodies = new ArrayList<String>();
	// List<String> listOf_b_Params = new ArrayList<String>();

	for (int i = 0; i < nodeList.getLength(); i++) {
		if (nodeList.item(i) instanceof Node) {
			Node node = nodeList.item(i);
			if (node.hasChildNodes()) {
				NodeList nl = node.getChildNodes();
				for (int j = 0; j < nl.getLength(); j++) {
					Node innernode = nl.item(j);
					if (innernode != null) {
						if (innernode.getNodeName().equals("request")) {
							if (innernode.hasChildNodes()) {
								NodeList n2 = innernode.getChildNodes();
								for (int k = 0; k < n2.getLength(); k++) {
									Node innernode2 = n2.item(k);
									if (innernode2 != null) {
										if (innernode2.getNodeType() == Node.ELEMENT_NODE) {
											Element eElement = (Element) innernode2;
											if (eElement.getNodeName().equals("body")) {
												String content = eElement.getTextContent();
												if (content.contains(slotID)) {
													flag = true;
													aaxcallsSize++;
													istofRequestBodies.add(content);
													// System.out.println("request body "+content);
												}
											}
										}
									}
								}
							}
						}

						if (flag) {
							if (innernode.getNodeName().equals("response")) {
								// System.out.println(innernode.getNodeName());
								if (innernode.hasChildNodes()) {
									NodeList n2 = innernode.getChildNodes();
									for (int k = 0; k < n2.getLength(); k++) {
										Node innernode2 = n2.item(k);
										if (innernode2 != null) {
											if (innernode2.getNodeType() == Node.ELEMENT_NODE) {
												Element eElement = (Element) innernode2;
												if (eElement.getNodeName().equals("body")) {
													String content = eElement.getTextContent();
													istofResponseBodies.add(content);
													String tempBparam = get_b_value_inJsonResponseBody(content);
													if (!"".contentEquals(tempBparam)) {
														listOf_b_Params.add(tempBparam);
													}
													// System.out.println("response body "+content);
												}
											}
										}
									}
								}
							}

						}

					}
				}
			}
		}
		flag = false;
	}
	System.out.println(listOf_b_Params);
	logStep(listOf_b_Params.toString());

	aaxcallsResponseSize = listOf_b_Params.size();
	aaxbidErrorCount = 0;
	for (String b : listOf_b_Params) {
		System.out.println(" b values from JSON-----------> " + b);
		if (b.contentEquals("error")) {
			aaxbidErrorCount++;
		}
	}
	System.out.println("aaxcalls Size is: " + aaxcallsSize);
	System.out.println("aaxcallsResponse Size is: " + aaxcallsResponseSize);
	System.out.println("aaxbidErrorCount size is: " + aaxbidErrorCount);

}
private static String get_b_value_inJsonResponseBody(String qryValue) {
	String b_paramValue = "";
	JSONParser parser = new JSONParser();
	try {
		JSONObject json = (JSONObject) parser.parse(qryValue);
		Object obj = checkKey(json, "b");
		if (obj != null) {
			b_paramValue = obj.toString();
		} else {
			// inorder to not to disturb the existing method structure, when there is no
			// bidding happens i.e. response contains error, returning error explicitly
			b_paramValue = "error";
		}
	} catch (ParseException e) {
		// inorder to not to disturb the existing method structure, when there is no
		// bidding happens i.e. response contains error, returning error explicitly
		b_paramValue = "error";
		e.printStackTrace();
	}

	return b_paramValue;
}

////*** Criteo test Methods***///




public static void verifyCriteo_inapp_v2_Call(String sheetName) throws Exception {
	
	String[][] data = read_excel_data.exceldataread("Criteo");
	
	//readExcelValues.excelValues( sheetName);
	String host = data[2][1];
	System.out.println(host);
	
	String path = data[3][1];
	System.out.println(path);
	boolean flag = verifyAPICalWithHostandPath(host, path);
	if (flag) {
		System.out.println(host + path + " call is present in Charles session");
		logStep(host + path + " call is present in Charles session");

	} else {
		System.out.println(host + path + " call is not present in Charles session");
		logStep(host + path + " call is not present in Charles session");

		Assert.fail(host + path + " call is not present in Charles session");

	}
}


public static void verifyCriteo_config_app_Call(String sheetName) throws Exception {
String[][] data = read_excel_data.exceldataread("Criteo");
	
	//readExcelValues.excelValues( sheetName);
	String host = data[2][1];
	System.out.println(host);
	String path = data[4][1];
	System.out.println(path);
	
	/*readExcelValues.excelValues(excelName, sheetName);
	String host = readExcelValues.data[2][Cap];
	String path = readExcelValues.data[4][Cap];*/
	boolean flag = verifyAPICalWithHostandPath(host, path);
	if (flag) {
		System.out.println(host + path + " call is present in Charles session");
		logStep(host + path + " call is present in Charles session");

	} else {
		System.out.println(host + path + " call is not present in Charles session");
		logStep(host + path + " call is not present in Charles session");

		Assert.fail(host + path + " call is not present in Charles session");

	}
}


public static void validate_Criteo_SDK_config_app_call_parameter(String sheetName,
		String cust_param, String expected) throws Exception {
String[][] data = read_excel_data.exceldataread("Criteo");
	
	//readExcelValues.excelValues( sheetName);
	String host = data[2][1];
	String path = data[4][1];
	/*readExcelValues.excelValues(excelName, sheetName);
	String host = readExcelValues.data[2][Cap];
	String path = readExcelValues.data[4][Cap];*/

	boolean flag = verifyAPICalWithHostandPath(host, path);
	if (flag) {
		System.out.println(host + path + " call is present in Charles session");
		logStep(host + path + " call is present in Charles session");

		String actual = get_param_value_from_APIRequest(host, path, cust_param);

		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("Custom Parameter :" + cust_param + " value: " + actual
					+ " is matched with the expected value " + expected);
			logStep("Custom Parameter :" + cust_param + " value: " + actual + " is matched with the expected value "
					+ expected);
		} else {
			System.out.println("Custom Parameter :" + cust_param + " value: " + actual
					+ " is not matched with the expected value " + expected);
			logStep("Custom Parameter :" + cust_param + " value: " + actual
					+ " is not matched with the expected value " + expected);
			Assert.fail("Custom Parameter :" + cust_param + " value: " + actual
					+ " is not matched with the expected value " + expected);
		}

	} else {
		System.out.println(host + path + " call is not present in Charles session, hence Custom Parameter: "
				+ cust_param + " validation skipped");
		logStep(host + path + " call is not present in Charles session, hence Custom Parameter: " + cust_param
				+ " validation skipped");

		Assert.fail(host + path + " call is not present in Charles session, hence Custom Parameter: " + cust_param
				+ " validation skipped");

	}

}


public static String get_param_value_from_APIRequest(String host, String path, String cust_param) throws Exception {
	// readExcelValues.excelValues(excelName, sheetName);
	File fXmlFile = new File(CharlesFunctions.outfile.getName());

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	dbFactory.setValidating(false);
	dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
	// dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/validation", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	Document doc = dBuilder.parse(fXmlFile);
	// Getting the transaction element by passing xpath expression
	NodeList nodeList = doc.getElementsByTagName("transaction");
	String xpathExpression = "charles-session/transaction/@host";
	List<String> getQueryList = evaluateXPath(doc, xpathExpression);

	// Getting custom_params amzn_b values
	List<String> customParamsList = new ArrayList<String>();

	// String iuId = null;

	boolean iuExists = false;
	for (String qry : getQueryList) {
		if (qry.contains(host)) {
			iuExists = true;
			break;
		}
	}
	boolean hflag = false;
	boolean pflag = false;
	boolean resflag = false;
	String ApiParamValue = null;

	if (iuExists) {
		System.out.println(host + "  call is present");
		logStep(host + "  call is present");
		outerloop: for (int p = 0; p < nodeList.getLength(); p++) {
			// System.out.println("Total transactions: "+nodeList.getLength());
			if (nodeList.item(p) instanceof Node) {
				Node node = nodeList.item(p);
				if (node.hasChildNodes()) {
					NodeList nl = node.getChildNodes();
					for (int j = 0; j < nl.getLength(); j++) {
						// System.out.println("node1 length is: "+nl.getLength());
						Node innernode = nl.item(j);
						if (innernode != null) {
							// System.out.println("Innernode name is: "+innernode.getNodeName());
							if (innernode.getNodeName().equals("request")) {
								if (innernode.hasChildNodes()) {
									NodeList n2 = innernode.getChildNodes();
									for (int k = 0; k < n2.getLength(); k++) {
										// System.out.println("node2 length is: "+n2.getLength());
										Node innernode2 = n2.item(k);
										if (innernode2 != null) {
											// System.out.println("Innernode2 name is: "+innernode2.getNodeName());
											if (innernode2.getNodeType() == Node.ELEMENT_NODE) {
												Element eElement = (Element) innernode2;
												// System.out.println("Innernode2 element name is:
												// "+eElement.getNodeName());
												if (eElement.getNodeName().equals("headers")) {
													if (innernode2.hasChildNodes()) {
														NodeList n3 = innernode2.getChildNodes();
														for (int q = 0; q < n3.getLength(); q++) {
															// System.out.println("node3 length is:
															// "+n3.getLength());
															Node innernode3 = n3.item(q);
															if (innernode3 != null) {
																// System.out.println("Innernode3 name is:
																// "+innernode3.getNodeName());
																if (innernode3.getNodeType() == Node.ELEMENT_NODE) {
																	Element eElement1 = (Element) innernode3;
																	// System.out.println("Innernode3 element name
																	// is: "+eElement1.getNodeName());
																	if (eElement1.getNodeName().equals("header")) {
																		String content = eElement1.getTextContent();
																		// System.out.println("request body
																		// "+content);

																		if (content.contains(host)) {
																			hflag = true;
																			// System.out.println("request body
																			// found "
																			// + content);

																		} else if (content.contains(path)) {
																			pflag = true;
																			// System.out.println("request body
																			// found "
																			// + content);
																		}
																	}
																	
																	//if(hflag && !pflag) {
																		if (eElement1.getNodeName().equals("first-line")) {
																			String content = eElement1.getTextContent();
																			// System.out.println("request body
																			// "+content);

																			if (content.contains(path)) {
																				pflag = true;
																				// System.out.println("request body
																				// found "
																				// + content);
																			}
																		}
																//	}
																}
															}
														}
													}
												}
												if (hflag && pflag) {
													if (eElement.getNodeName().equals("body")) {
														String scontent = eElement.getTextContent();
														if (scontent.contains(cust_param)) {
															// System.out.println("request body " + scontent);
															ApiParamValue = get_Param_Value_inJsonBody(scontent,
																	cust_param);
															break outerloop;

														}

													}

												}

											}
										}
									}
								}
							}

							/*
							 * if (hflag && pflag) { resflag = true; break outerloop; }
							 */
						}
					}
				}
			}
			// flag = false;
		}

	} else {
		System.out.println(host + " ad call is not present");
		logStep(host + " ad call is not present");

	}

	// return resflag;
	// System.out.println("Parameter value obtined from criteo request is :" +
	// ApiParamValue);
	return ApiParamValue;

}



private static String get_Param_Value_inJsonBody(String qryValue, String param) {
	String b_paramValue = "";
	JSONParser parser = new JSONParser();
	try {
		JSONObject json = (JSONObject) parser.parse(qryValue);
		Object obj = checkKey(json, param);
		if (obj != null) {
			b_paramValue = obj.toString();
		} else {
			// inorder to not to disturb the existing method structure, when there is no
			// bidding happens i.e. response contains error, returning error explicitly
			b_paramValue = "error";
		}
	} catch (ParseException e) {
		// inorder to not to disturb the existing method structure, when there is no
		// bidding happens i.e. response contains error, returning error explicitly
		b_paramValue = "error";
		e.printStackTrace();
	}

	return b_paramValue;
}

// To get the value for "b". Here key is -> 'b'
public static Object checkKey(JSONObject object, String searchedKey) {
	boolean exists = object.containsKey(searchedKey);
	Object obj = null;
	if (exists) {
		obj = object.get(searchedKey);
	}
	if (!exists) {
		Set<String> keys = object.keySet();
		for (String key : keys) {
			if (object.get(key) instanceof JSONObject) {
				obj = checkKey((JSONObject) object.get(key), searchedKey);
			}
		}
	}
	return obj;
}


public static boolean verifyAPICalWithHostandPath(String host, String path) throws Exception {
	// readExcelValues.excelValues(excelName, sheetName);
	File fXmlFile = new File(CharlesFunctions.outfile.getName());

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	dbFactory.setValidating(false);
	dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
	// dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/validation", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	Document doc = dBuilder.parse(fXmlFile);
// Getting the transaction element by passing xpath expression
	NodeList nodeList = doc.getElementsByTagName("transaction");
	String xpathExpression = "charles-session/transaction/@host";
	List<String> getQueryList = evaluateXPath(doc, xpathExpression);

// Getting custom_params amzn_b values
	List<String> customParamsList = new ArrayList<String>();

	// String iuId = null;

	boolean iuExists = false;
	for (String qry : getQueryList) {
		if (qry.contains(host)) {
			iuExists = true;
			break;
		}
	}
	boolean hflag = false;
	boolean pflag = false;
	boolean resflag = false;

	if (iuExists) {
		System.out.println(host + "  call is present");
		logStep(host + "  call is present");
		outerloop: for (int p = 0; p < nodeList.getLength(); p++) {
			// System.out.println("Total transactions: "+nodeList.getLength());
			if (nodeList.item(p) instanceof Node) {
				Node node = nodeList.item(p);
				if (node.hasChildNodes()) {
					NodeList nl = node.getChildNodes();
					for (int j = 0; j < nl.getLength(); j++) {
						// System.out.println("node1 length is: "+nl.getLength());
						Node innernode = nl.item(j);
						if (innernode != null) {
							// System.out.println("Innernode name is: "+innernode.getNodeName());
							if (innernode.getNodeName().equals("request")) {
								if (innernode.hasChildNodes()) {
									NodeList n2 = innernode.getChildNodes();
									for (int k = 0; k < n2.getLength(); k++) {
										// System.out.println("node2 length is: "+n2.getLength());
										Node innernode2 = n2.item(k);
										if (innernode2 != null) {
											// System.out.println("Innernode2 name is: "+innernode2.getNodeName());
											if (innernode2.getNodeType() == Node.ELEMENT_NODE) {
												Element eElement = (Element) innernode2;
												// System.out.println("Innernode2 element name is:
												// "+eElement.getNodeName());
												if (eElement.getNodeName().equals("headers")) {
													if (innernode2.hasChildNodes()) {
														NodeList n3 = innernode2.getChildNodes();
														for (int q = 0; q < n3.getLength(); q++) {
															// System.out.println("node3 length is:
															// "+n3.getLength());
															Node innernode3 = n3.item(q);
															if (innernode3 != null) {
																// System.out.println("Innernode3 name is:
																// "+innernode3.getNodeName());
																if (innernode3.getNodeType() == Node.ELEMENT_NODE) {
																	Element eElement1 = (Element) innernode3;
																	// System.out.println("Innernode3 element name
																	// is: "+eElement1.getNodeName());
																	if (eElement1.getNodeName().equals("header")) {
																		String content = eElement1.getTextContent();
																		// System.out.println("request body
																		// "+content);

																		if (content.contains(host)) {
																			hflag = true;
																			// System.out.println("request body
																			// found "
																			// + content);

																		} else if (content.contains(path)) {
																			pflag = true;
																			// System.out.println("request body
																			// found "
																			// + content);
																		}
																	}
																	//if(hflag && !pflag) {
																		if (eElement1.getNodeName().equals("first-line")) {
																			String content = eElement1.getTextContent();
																			// System.out.println("request body
																			// "+content);

																			if (content.contains(path)) {
																				pflag = true;
																				// System.out.println("request body
																				// found "
																				// + content);
																			}
																		}
																	//}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}

							/*
							 * if (flag) { // System.out.println("Exiting after found true "); //
							 * System.out.println("checking innernode name is: "+innernode.getNodeName());
							 * if (innernode.getNodeName().equals("response")) { //
							 * System.out.println(innernode.getNodeName()); if (innernode.hasChildNodes()) {
							 * NodeList n2 = innernode.getChildNodes(); for (int k = 0; k < n2.getLength();
							 * k++) { Node innernode2 = n2.item(k); if (innernode2 != null) { if
							 * (innernode2.getNodeType() == Node.ELEMENT_NODE) { Element eElement =
							 * (Element) innernode2; if (eElement.getNodeName().equals("body")) { String
							 * content = eElement.getTextContent(); //
							 * System.out.println("response body "+content); if
							 * (content.contains(readExcelValues.data[13][Cap])) { resflag = true; break
							 * outerloop;
							 * 
							 * } } } } } } }
							 * 
							 * }
							 */
							if (hflag && pflag) {
								resflag = true;
								break outerloop;
							}
						}
					}
				}
			}
			// flag = false;
		}

	} else {
		System.out.println(host + " ad call is not present");
		logStep(host + " ad call is not present");

	}

	return resflag;

	// Get Pubad call from

	/*
	 * if (resflag) { System.out.println(host + path
	 * +" call is present in Charles session"); logStep(host + path
	 * +" call is present in Charles session"); return resflag;
	 * 
	 * } else { System.out .println(host + path
	 * +" call is not present in Charles session"); logStep(host + path
	 * +" call is not present in Charles session"); return resflag;
	 * //Assert.fail(host + path +" call is not present in Charles session");
	 * 
	 * }
	 */

}


public static void verifyAAX_SlotId(String sheetName) throws Exception {
	String[][] data = read_excel_data.exceldataread(sheetName);
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	// Read the content form file
	File fXmlFile = new File(CharlesFunctions.outfile.getName());

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	dbFactory.setValidating(false);
	dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
	dbFactory.setFeature("http://xml.org/sax/features/validation", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	Document doc = dBuilder.parse(fXmlFile);
	// Getting the transaction element by passing xpath expression
	NodeList nodeList = doc.getElementsByTagName("transaction");

	// Read JSONs and get b value
	// List<String> jsonBValuesList = new ArrayList<String>();

	// String slotId = "153f5936-781f-4586-8fdb-040ce298944a";

	// String slotId = "c4dd8ec4-e40c-4a63-ae81-8f756793ac5e";
	String slotId = data[12][Cap];

	boolean flag = false;
	// List<String> istofRequestBodies = new ArrayList<String>();
	// List<String> istofResponseBodies = new ArrayList<String>();
	// List<String> listOf_b_Params = new ArrayList<String>();

	nodeList: for (int i = 0; i < nodeList.getLength(); i++) {
		if (nodeList.item(i) instanceof Node) {
			Node node = nodeList.item(i);
			if (node.hasChildNodes()) {
				NodeList nl = node.getChildNodes();
				NodeList: for (int j = 0; j < nl.getLength(); j++) {
					Node innernode = nl.item(j);
					if (innernode != null) {
						if (innernode.getNodeName().equals("request")) {
							if (innernode.hasChildNodes()) {
								NodeList n2 = innernode.getChildNodes();
								for (int k = 0; k < n2.getLength(); k++) {
									Node innernode2 = n2.item(k);
									if (innernode2 != null) {
										if (innernode2.getNodeType() == Node.ELEMENT_NODE) {
											Element eElement = (Element) innernode2;
											if (eElement.getNodeName().equals("body")) {
												String content = eElement.getTextContent();
												if (content.contains(slotId)) {
													flag = true;
													// istofRequestBodies.add(content);

													break nodeList;

													// System.out.println("request body "+content);
												}
											}
										}
									}
								}

							}
						}

					}
				}

			}
		}

	}
	if (flag) {
		System.out.println("slot id: " + slotId + " is present");
		logStep("slot id: " + slotId + " is present");

	} else {
		System.out.println("slot id: " + slotId + " is not present");
		logStep("slot id: " + slotId + " is not present");
		Assert.fail("slot id: " + slotId + " is not present");
	}

}

//this retrives amazon bid values from aax calls and gampad calls of
	// correponding add calls and verifies any one matching.
	public static void validate_Criteo_SDK_inapp_v2_call_param_value_with_gampad_param_value(String sheetName, String cust_param, boolean clearList) throws Exception {

			String[][] data = read_excel_data.exceldataread(sheetName);
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		String placementId = null;
		if (sheetName.equalsIgnoreCase("Pulltorefresh")) {
			placementId = "weather.feed1";
		} else if (sheetName.equalsIgnoreCase("Hourly")) {
			placementId = "weather.hourly";
		} else if (sheetName.equalsIgnoreCase("Feed1")) {
			placementId = "weather.feed2";
		} else if (sheetName.equalsIgnoreCase("Feed2")) {
			placementId = "weather.feed3";
		} else if (sheetName.equalsIgnoreCase("Feed3")) {
			placementId = "weather.feed4";
		} else if (sheetName.equalsIgnoreCase("Feed4")) {
			placementId = "weather.feed5";
		} else if (sheetName.equalsIgnoreCase("Feed5")) {
			placementId = "weather.feed6";
		} else if (sheetName.equalsIgnoreCase("Feed6")) {
			placementId = "weather.feed6";
		} else if (sheetName.equalsIgnoreCase("Feed7")) {
			placementId = "weather.feed7";
		} else if (sheetName.equalsIgnoreCase("Air Quality(Content)")) {
			placementId = "weather.aq";
		} else if (sheetName.equalsIgnoreCase("SeasonalHub(Details)")) {
			placementId = "weather.sh.details";
		} else if (sheetName.equalsIgnoreCase("Today")) {
			placementId = "weather.trending";
		} else if (sheetName.equalsIgnoreCase("Daily(10day)")) {
			placementId = "weather.dailydetails.largeAds.1";
		} else if (sheetName.equalsIgnoreCase("Map")) {
			placementId = "weather.maps";
		} else if (sheetName.equalsIgnoreCase("MyAlerts")) {
			placementId = "weather.alerts.center";
		} else if (sheetName.equalsIgnoreCase("weather.alerts")) {
			placementId = "weather.alerts";
		} else if (sheetName.equalsIgnoreCase("Health(coldAndFluArticles)")) {
			/*
			 * News detils, flu articles, alergy articles has same placement Id, here anything can be used
			 */
			placementId = "weather.articles";
		} else if (sheetName.equalsIgnoreCase("Health(goRun)")) {
			placementId = "content.run.largeAds";
		} else if (sheetName.equalsIgnoreCase("Health(boatAndBeach)")) {
			placementId = "content.beach.largeAds";
		}
		
		String feedCall = null;
		// "iu=%2F7646%2Fapp_iphone_us%2Fdb_display%2Fhome_screen%2Ftoday";
		if (sheetName.equalsIgnoreCase("PreRollVideo")) {
			feedCall = videoIUValue;
		} /*
			 * else if (sheetName.equalsIgnoreCase("IDD")) { String today =
			 * dailyDetailsDayOfWeek.concat("1"); feedCall = readExcelValues.data[18][Cap];
			 * feedCall = feedCall.concat("_") + today; }
			 */else {
					feedCall = data[11][Cap];
		}

		boolean testpass = false;
		int failCount = 0;

		get_Criteo_SDK_inapp_v2_call_response_parameter_by_placementId("Criteo", placementId, cust_param,
				clearList);

		if (criteocallsSize == 0) {
			System.out.println("Criteo call is not generated in current session, so skipping the " + cust_param
					+ " value verification");
			logStep("Criteo call is not generated in current session, so skipping the " + cust_param
					+ " value verification");

		} else if (criteocallsResponseSize == 0) {
			System.out.println("Criteo call response doesn't have the placement id: " + placementId
					+ "  i.e. bidding is not happened in current session, so skipping the " + cust_param
					+ " value verification");
			logStep("Criteo call response doesn't have the placement id: " + placementId
					+ "  i.e. bidding is not happened in current session, so skipping the " + cust_param
					+ " value verification");

		} else if (criteoparamErrorCount == criteocallsResponseSize) {
			System.out.println(
					"Criteo call response contains error i.e. bidding is not happened in current session, so skipping the "
							+ cust_param + " value verification");
			logStep("Criteo call response contains error i.e. bidding is not happened in current session, so skipping the "
					+ cust_param + " value verification");

		} else if (nextGenIMadDisplayed && sheetName.equalsIgnoreCase("Pulltorefresh")) {
			/*
			 * There may be chances that gampad call might not generated.. for ex: when IM
			 * ad displayed on home screen, then homescreen today call doesnt generate
			 * 
			 */
			System.out.println("Since IM Ad displayed on App Launch, Homescreen Today call: " + cust_param
					+ " id validation is skipped");
			logStep("Since IM Ad displayed on App Launch, Homescreen Today call: " + cust_param
					+ " id validation is skipped");
		} else {
			if (cust_param.contentEquals("displayUrl")) {
				cust_param = "displayurl";
			}
			get_custom_param_values_from_gampadCalls(feedCall, "crt_" + cust_param);
			if (criteogampadcallcount == 0) {
				System.out.println("Ad Call :" + feedCall + " not found in charles session, hence Custom Parameter: "
						+ cust_param + " validation failed");
				logStep("Ad Call :" + feedCall + " not found in charles session, hence Custom Parameter: " + cust_param
						+ " validation failed");
				Assert.fail("Ad Call :" + feedCall + " not found in charles session, hence Custom Parameter: "
						+ cust_param + " validation failed");
			} else if (customParamsList.size() == 0) {
				System.out.println("Ad Call :" + feedCall + " not having Custom Parameter: " + cust_param
						+ ", hence Custom Parameter: " + cust_param + " validation failed");
				logStep("Ad Call :" + feedCall + " not having Custom Parameter: " + cust_param
						+ ", hence Custom Parameter: " + cust_param + " validation failed");
				Assert.fail("Ad Call :" + feedCall + " not having Custom Parameter: " + cust_param
						+ ", hence Custom Parameter: " + cust_param + " validation failed");
			} else {

				int maxIterations = 0;
				if (listOf_criteo_Params.size() > customParamsList.size()) {
					maxIterations = customParamsList.size();
				} else {
					maxIterations = listOf_criteo_Params.size();
				}

				if (!sheetName.equalsIgnoreCase("Health(goRun)")
						&& !sheetName.equalsIgnoreCase("Health(boatAndBeach)")) {
					for (int i = 0; i < maxIterations; i++) {

						if (listOf_criteo_Params.get(i).equalsIgnoreCase("-1")) {
							if (listOf_criteo_Params.size() == 1) {
								System.out.println(
										"It looks that the only Occurance of Criteo Call bidding is not happened..Hence skipping the further validation...inspect the charles response for more details");
								logStep("It looks that the only Occurance of Criteo Call bidding is not happened..Hence skipping the further validation...inspect the charles response for more details");
							} else {
								System.out.println("It looks that: " + i
										+ " Occurance of Criteo Call bidding is not happened..Hence skipping the current instance validation...inspect the charles response for more details");
								logStep("It looks that: " + i
										+ " Occurance of Criteo Call bidding is not happened..Hence skipping the current instance validation...inspect the charles response for more details");
							}

						} else {

							if (cust_param.equalsIgnoreCase("displayurl")) {

								if (customParamsList.get(i).equalsIgnoreCase("-1")) {
									System.out.println(i + " Occurance of corresponding " + sheetName + " gampad call: "
											+ feedCall + " not having parameter " + cust_param);
									logStep(i + " Occurance of corresponding " + sheetName + " gampad call: " + feedCall
											+ " not having parameter " + cust_param);
									failCount++;
								} else {
									System.out.println(i + " Occurance of Criteo call " + cust_param + " value: "
											+ listOf_criteo_Params.get(i) + " is  matched with " + i
											+ " Occurance of corresponding " + sheetName + " gampad call " + cust_param
											+ " value: " + customParamsList.get(i));
									logStep(i + " Occurance of Criteo call " + cust_param + " value: "
											+ listOf_criteo_Params.get(i) + " is  matched with " + i
											+ " Occurance of corresponding " + sheetName + " gampad call " + cust_param
											+ " value: " + customParamsList.get(i));

								}

							} else {
								if (listOf_criteo_Params.get(i).equalsIgnoreCase(customParamsList.get(i))) {

									System.out.println(i + " Occurance of Criteo call " + cust_param + " value: "
											+ listOf_criteo_Params.get(i) + " is matched with " + i
											+ " Occurance of corresponding " + sheetName + " gampad call " + cust_param
											+ " value: " + customParamsList.get(i));
									logStep(i + " Occurance of Criteo call " + cust_param + " value: "
											+ listOf_criteo_Params.get(i) + " is matched with " + i
											+ " Occurance of corresponding " + sheetName + " gampad call " + cust_param
											+ " value: " + customParamsList.get(i));
								} else {
									if (customParamsList.get(i).equalsIgnoreCase("-1")) {
										System.out.println(i + " Occurance of corresponding " + sheetName
												+ " gampad call: " + feedCall + " not having parameter " + cust_param);
										logStep(i + " Occurance of corresponding " + sheetName + " gampad call: "
												+ feedCall + " not having parameter " + cust_param);
										failCount++;
									} else {
										System.out.println(i + " Occurance of Criteo call " + cust_param + " value: "
												+ listOf_criteo_Params.get(i) + " is not matched with " + i
												+ " Occurance of corresponding " + sheetName + " gampad call "
												+ cust_param + " value: " + customParamsList.get(i));
										logStep(i + " Occurance of Criteo call " + cust_param + " value: "
												+ listOf_criteo_Params.get(i) + " is not matched with " + i
												+ " Occurance of corresponding " + sheetName + " gampad call "
												+ cust_param + " value: " + customParamsList.get(i));
										failCount++;
									}

								}
							}

						}

					}

				} else {
					for (int i = 0; i < maxIterations / 2; i++) {

						if (listOf_criteo_Params.get(i).equalsIgnoreCase("-1")) {
							if (listOf_criteo_Params.size() == 1) {
								System.out.println(
										"It looks that the only Occurance of Criteo Call bidding is not happened..Hence skipping the further validation...inspect the charles response for more details");
								logStep("It looks that the only Occurance of Criteo Call bidding is not happened..Hence skipping the further validation...inspect the charles response for more details");
							} else {
								System.out.println("It looks that: " + i
										+ " Occurance of Criteo Call bidding is not happened..Hence skipping the current instance validation...inspect the charles response for more details");
								logStep("It looks that: " + i
										+ " Occurance of Criteo Call bidding is not happened..Hence skipping the current instance validation...inspect the charles response for more details");
							}

						} else {
							if (cust_param.equalsIgnoreCase("displayurl")) {

								if (customParamsList.get(i + 1).equalsIgnoreCase("-1")) {
									System.out.println(i + 1 + " Occurance of corresponding " + sheetName
											+ " gampad call: " + feedCall + " not having parameter " + cust_param);
									logStep(i + 1 + " Occurance of corresponding " + sheetName + " gampad call: "
											+ feedCall + " not having parameter " + cust_param);
									failCount++;
								} else {
									System.out.println(i + " Occurance of Criteo call " + cust_param + " value: "
											+ listOf_criteo_Params.get(i) + " is  matched with " + i + 1
											+ " Occurance of corresponding " + sheetName + " gampad call " + cust_param
											+ " value: " + customParamsList.get(i + 1));
									logStep(i + " Occurance of Criteo call " + cust_param + " value: "
											+ listOf_criteo_Params.get(i) + " is  matched with " + i + 1
											+ " Occurance of corresponding " + sheetName + " gampad call " + cust_param
											+ " value: " + customParamsList.get(i + 1));

								}

							} else {
								if (listOf_criteo_Params.get(i).equalsIgnoreCase(customParamsList.get(i + 1))) {

									System.out.println(i + " Occurance of Criteo call " + cust_param + " value: "
											+ listOf_criteo_Params.get(i) + " is matched with " + i + 1
											+ " Occurance of corresponding " + sheetName + " gampad call " + cust_param
											+ " value: " + customParamsList.get(i + 1));
									logStep(i + " Occurance of Criteo call " + cust_param + " value: "
											+ listOf_criteo_Params.get(i) + " is matched with " + i + 1
											+ " Occurance of corresponding " + sheetName + " gampad call " + cust_param
											+ " value: " + customParamsList.get(i + 1));
								} else {
									if (customParamsList.get(i + 1).equalsIgnoreCase("-1")) {
										System.out.println(i + 1 + " Occurance of corresponding " + sheetName
												+ " gampad call: " + feedCall + " not having parameter " + cust_param);
										logStep(i + 1 + " Occurance of corresponding " + sheetName + " gampad call: "
												+ feedCall + " not having parameter " + cust_param);
										failCount++;
									} else {
										System.out.println(i + " Occurance of Criteo call " + cust_param + " value: "
												+ listOf_criteo_Params.get(i) + " is not matched with " + i + 1
												+ " Occurance of corresponding " + sheetName + " gampad call "
												+ cust_param + " value: " + customParamsList.get(i + 1));
										logStep(i + " Occurance of Criteo call " + cust_param + " value: "
												+ listOf_criteo_Params.get(i) + " is not matched with " + i + 1
												+ " Occurance of corresponding " + sheetName + " gampad call "
												+ cust_param + " value: " + customParamsList.get(i + 1));
										failCount++;
									}

								}
							}

						}

					}
				}

			}
		}

		if (failCount > 0) {
			System.out.println("Criteo call " + cust_param + " values  not matched with corresponding " + sheetName
					+ " gampad call " + cust_param + " values");
			logStep("Criteo call " + cust_param + " values  not matched with corresponding " + sheetName
					+ " gampad call " + cust_param + " values");
			Assert.fail("Criteo call " + cust_param + " values  not matched with corresponding " + sheetName
					+ "  gampad call " + cust_param + " values");
		}

	}
private static List<String> evaluateXPath(Document document, String xpathExpression) throws Exception {
	// Create XPathFactory object
	XPathFactory xpathFactory = XPathFactory.newInstance();
	// Create XPath object
	XPath xpath = xpathFactory.newXPath();
	List<String> values = new ArrayList<String>();
	try {
		// Create XPathExpression object
		XPathExpression expr = xpath.compile(xpathExpression);
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		for (int i = 0; i < nodes.getLength(); i++) {
			values.add(nodes.item(i).getNodeValue());
		}
	} catch (XPathExpressionException e) {
		e.printStackTrace();
	}
	return values;
}

public static void verifyCriteo_config_app_Call(String sheetName, boolean expected)
		throws Exception {
	String[][] data = read_excel_data.exceldataread(sheetName);
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	String host = data[2][Cap];
	String path = data[4][Cap];
	boolean flag = verifyAPICalWithHostandPath(host, path);
	if (flag) {
		System.out.println(host + path + " call is present in Charles session");
		logStep(host + path + " call is present in Charles session");

	} else {
		System.out.println(host + path + " call is not present in Charles session");
		logStep(host + path + " call is not present in Charles session");
	}
	if (expected == flag) {
		System.out.println(host + path + " :API Call Verification is successfull");
		logStep(host + path + " :API Call Verification is successfull");

	} else {
		System.out.println(host + path + " :API Call Verification is failed");
		logStep(host + path + " :API Call Verification is failed");
		if (expected) {
			System.out.println(host + path + " :API Call expected to present but it not exists");
			logStep(host + path + " :API Call expected to present but it not exists");
			Assert.fail(host + path + " :API Call expected to present but it not exists");
		} else {
			System.out.println(host + path + " :API Call is not expected to present but it exists");
			logStep(host + path + " :API Call is not expected to present but it exists");
			Assert.fail(host + path + " :API Call is not expected to present but it exists");
		}
	}

}

public static void verifyCriteo_inapp_v2_Call(String sheetName, boolean expected)
		throws Exception {
	String[][] data = read_excel_data.exceldataread(sheetName);
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	//readExcelValues.excelValues(excelName, sheetName);
	String host =data[2][Cap];
	String path =data[3][Cap];
	boolean flag = verifyAPICalWithHostandPath(host, path);
	if (flag) {
		System.out.println(host + path + " call is present in Charles session");
		logStep(host + path + " call is present in Charles session");

	} else {
		System.out.println(host + path + " call is not present in Charles session");
		logStep(host + path + " call is not present in Charles session");
	}

	if (expected == flag) {
		System.out.println(host + path + " :API Call Verification is successfull");
		logStep(host + path + " :API Call Verification is successfull");

	} else {
		System.out.println(host + path + " :API Call Verification is failed");
		logStep(host + path + " :API Call Verification is failed");
		if (expected) {
			System.out.println(host + path + " :API Call expected to present but it not exists");
			logStep(host + path + " :API Call expected to present but it not exists");
			Assert.fail(host + path + " :API Call expected to present but it not exists");
		} else {
			System.out.println(host + path + " :API Call is not expected to present but it exists");
			logStep(host + path + " :API Call is not expected to present but it exists");
			Assert.fail(host + path + " :API Call is not expected to present but it exists");
		}
	}
}

public static void get_custom_param_values_from_gampadCalls(String feedCall, String cust_param) throws Exception {
	// readExcelValues.excelValues(excelName, sheetName);

	// Read the content form file
	File fXmlFile = new File(CharlesFunctions.outfile.getName());
	customParamsList.clear();
	criteogampadcallcount = 0;

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	dbFactory.setValidating(false);
	dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
	dbFactory.setFeature("http://xml.org/sax/features/validation", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	Document doc = dBuilder.parse(fXmlFile);
	// Getting the transaction element by passing xpath expression
	// NodeList nodeList = doc.getElementsByTagName("transaction");
	String xpathExpression = "charles-session/transaction/@query";
	List<String> getQueryList = evaluateXPath(doc, xpathExpression);

	// Getting custom_params amzn_b values
	// List<String> customParamsList = new ArrayList<String>();

	// String iuId =
	// "iu=%2F7646%2Fapp_iphone_us%2Fdb_display%2Fhome_screen%2Ftoday";
	// String iuId = "iu=%2F7646%2Fapp_iphone_us%2Fdb_display%2Fcard%2Fdaily";

	for (String qry : getQueryList) {
		if (qry.contains(feedCall)) {
			criteogampadcallcount++;
			String tempCustmParam = getCustomParamBy_iu_value(qry, cust_param);
			if (!"".equals(tempCustmParam)) {
				customParamsList.add(getCustomParamBy_iu_value(qry, cust_param));
			} else {
				customParamsList.add("-1");
				}

		}
	}
	System.out.println(customParamsList);
	logStep(customParamsList.toString());
	System.out.println("No of times the criteo parameter found in gampad call is: " + customParamsList.size());
	logStep("No of times the criteo parameter found in gampad call is: " + customParamsList.size());
	System.out.println("No of times the gampad call found is: " + criteogampadcallcount);
	logStep("No of times the gampad call found is: " + criteogampadcallcount);

}

private static String getCustomParamBy_iu_value(String qryValue, String cust_param) {
	List<String> listOfUisQrys = new ArrayList<String>();
	String cust_params = "";
	String[] key = null;
	// if (qryValue != null && qryValue.contains("cust_params")) {
	if (qryValue != null && qryValue.contains("cust_params")) {
		cust_params = qryValue.substring(qryValue.indexOf("cust_params"));
		cust_params = cust_params.replace("%26", "&");
		cust_params = cust_params.replace("%2C", ",");
		cust_params = cust_params.replace("%3D", "=");
	}
	if (cust_params.indexOf(cust_param) > 0) {
		try {
			cust_params = cust_params.substring(cust_params.indexOf("&" + cust_param + "="));
			cust_params = cust_params.substring(cust_params.indexOf(cust_param));
		} catch (Exception e) {
			cust_params = cust_params.substring(cust_params.indexOf("=" + cust_param + "="));
			cust_params = cust_params.substring(cust_params.indexOf(cust_param));
		}
		// cust_params = cust_params.substring(cust_params.indexOf(cust_param));
		String b[] = cust_params.split("&");
		cust_params = b[0];
		key = cust_params.split("=");
		cust_params = key[1];
	} else {
		cust_params = "";
	}
	if (cust_param.equalsIgnoreCase("ct")) {
		cust_params = cust_params.replaceAll("_", " ");
	}

	return cust_params;
}
public static void get_Criteo_SDK_inapp_v2_call_response_parameter_by_placementId(String sheetName, String placementId, String cust_param, boolean clearList) throws Exception {
	String[][] data = read_excel_data.exceldataread(sheetName);
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	//readExcelValues.excelValues(excelName, sheetName);
	String host = data[2][1];
	String path = data[3][1];
	// Read the content form file
	File fXmlFile = new File(CharlesFunctions.outfile.getName());
	if (clearList) {
		listOf_criteo_Params.clear();
		criteocallsSize = 0;
	}

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	dbFactory.setValidating(false);
	dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
	dbFactory.setFeature("http://xml.org/sax/features/validation", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	Document doc = dBuilder.parse(fXmlFile);
	// Getting the transaction element by passing xpath expression
	NodeList nodeList = doc.getElementsByTagName("transaction");

	// Read JSONs and get b value
	// List<String> jsonBValuesList = new ArrayList<String>();

	// String slotId = readExcelValues.data[21][Cap];

	// String slotId = "c4dd8ec4-e40c-4a63-ae81-8f756793ac5e";
	// weather.hourly

	boolean flag = false;
	boolean hflag = false;
	boolean pflag = false;
	boolean resflag = false;
	List<String> istofRequestBodies = new ArrayList<String>();
	List<String> istofResponseBodies = new ArrayList<String>();
	// List<String> listOf_b_Params = new ArrayList<String>();

	for (int i = 0; i < nodeList.getLength(); i++) {
		hflag = false;
		pflag = false;
		if (nodeList.item(i) instanceof Node) {
			Node node = nodeList.item(i);
			if (node.hasChildNodes()) {
				NodeList nl = node.getChildNodes();
				for (int j = 0; j < nl.getLength(); j++) {
					Node innernode = nl.item(j);
					if (innernode != null) {
						if (innernode.getNodeName().equals("request")) {
							if (innernode.hasChildNodes()) {
								NodeList n2 = innernode.getChildNodes();
								for (int k = 0; k < n2.getLength(); k++) {
									Node innernode2 = n2.item(k);
									if (innernode2 != null) {
										if (innernode2.getNodeType() == Node.ELEMENT_NODE) {
											Element eElement = (Element) innernode2;
											if (eElement.getNodeName().equals("headers")) {
												if (innernode2.hasChildNodes()) {
													NodeList n3 = innernode2.getChildNodes();
													for (int q = 0; q < n3.getLength(); q++) {
														// System.out.println("node3 length is:
														// "+n3.getLength());
														Node innernode3 = n3.item(q);
														if (innernode3 != null) {
															// System.out.println("Innernode3 name is:
															// "+innernode3.getNodeName());
															if (innernode3.getNodeType() == Node.ELEMENT_NODE) {
																Element eElement1 = (Element) innernode3;
																// System.out.println("Innernode3 element name
																// is: "+eElement1.getNodeName());
																if (eElement1.getNodeName().equals("header")) {
																	String content = eElement1.getTextContent();
																	// System.out.println("request body
																	// "+content);

																	if (content.contains(host)) {
																		hflag = true;
																		// System.out.println("request body found "
																		// + content);

																	} else if (content.contains(path)) {
																		pflag = true;
																		// System.out.println("request body found "
																		// + content);
																	}
																	
																}
																//if(hflag && !pflag) {
																if (eElement1.getNodeName().equals("first-line")) {
																	String content = eElement1.getTextContent();
																	// System.out.println("request body
																	// "+content);

																	if (content.contains(path)) {
																		pflag = true;
																		// System.out.println("request body
																		// found "
																		// + content);
																	}
																}
														//	}
															}
														}
													}
												}
											}

											
											
											
											if (hflag && pflag) {
												if (eElement.getNodeName().equals("body")) {
													String scontent = eElement.getTextContent();

													/*
													 * if (scontent.contains(placementId)) { flag = true;
													 * criteocallsSize++; istofRequestBodies.add(scontent); //
													 * System.out.println("request body "+scontent);
													 * 
													 * }
													 */
													boolean tempFlag = verify_criteo_request_for_given_placementId_inJsonRequestBody(
															placementId, scontent);
													if (tempFlag) {
														flag = true;
														criteocallsSize++;
														istofRequestBodies.add(scontent);
														// System.out.println("request body "+scontent);

													}

												}

											}
											
											
											
											
											
										}
									}
								}
							}
						}

						if (flag) {
							if (innernode.getNodeName().equals("response")) {
								// System.out.println(innernode.getNodeName());
								if (innernode.hasChildNodes()) {
									NodeList n2 = innernode.getChildNodes();
									for (int k = 0; k < n2.getLength(); k++) {
										Node innernode2 = n2.item(k);
										if (innernode2 != null) {
											if (innernode2.getNodeType() == Node.ELEMENT_NODE) {
												Element eElement = (Element) innernode2;
												if (eElement.getNodeName().equals("body")) {
													String content = eElement.getTextContent();
													istofResponseBodies.add(content);
													// String tempBparam = get_b_value_inJsonResponseBody(content);
													String tempBparam = get_criteo_response_parameter_value_by_placementId_inJsonResponseBody(
															placementId, cust_param, content);
													System.out.println(tempBparam);
													if (!"".contentEquals(tempBparam)) {
														listOf_criteo_Params.add(tempBparam);
													}
													// System.out.println("response body "+content);
												}
											}
										}
									}
								}
							}

						}

					}
				}
			}
		}
		flag = false;
	}
	System.out.println(listOf_criteo_Params);
	logStep(listOf_criteo_Params.toString());

	criteocallsResponseSize = listOf_criteo_Params.size();
	criteoparamErrorCount = 0;
	for (String b : listOf_criteo_Params) {
		System.out.println(cust_param + " value from JSON-----------> " + b);
		if (b.contentEquals("error")) {
			criteoparamErrorCount++;
		}
	}
	System.out.println("Criteo calls Size is: " + criteocallsSize);
	System.out.println("Criteo callsResponse Size is: " + criteocallsResponseSize);
	System.out.println("Criteo Param ErrorCount size is: " + criteoparamErrorCount);

}



public static void get_iu_value_of_Feedcall(String sheetName) throws Exception {
	String[][] data = read_excel_data.exceldataread(sheetName);
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	boolean adCallFound = false;
	videoIUValue = null;
	CharlesFunctions.outfile = new File(System.getProperty("user.dir") + "/myoutputFile.xml");
	// Read the content form file
	File fXmlFile = new File(CharlesFunctions.outfile.getName());

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	dbFactory.setValidating(false);
	dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
	// dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/validation", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	Document doc = dBuilder.parse(fXmlFile);
	// Getting the transaction element by passing xpath expression
	NodeList nodeList = doc.getElementsByTagName("transaction");
	String xpathExpression = "charles-session/transaction/@query";
	List<String> getQueryList = evaluateXPath(doc, xpathExpression);

	//String iuId = null;
	iuId = null;
	String iuValue = null;

	String tempCustmParam = null;
	for (String qry : getQueryList) {
		if (qry.contains("iu=")) {
			adCallFound = true;
			tempCustmParam = getNonCustomParamBy_iu_value(qry, "iu");
			// if (!"".equals(tempCustmParam))
			// customParamsList.add(getCustomParamsBy_iu_value(qry));
			break;
		}
	}
	try {
		iuValue = tempCustmParam.replace("/", "%2F");
		iuValue = "iu=" + iuValue;
		if (sheetName.equalsIgnoreCase("PreRollVideo")) {
			videoIUValue = iuValue;
			iuId = iuValue;
		} else {
			iuId = iuValue;
		}
	} catch (Exception e) {
		System.out.println("There is an exception while framing iu value");
		logStep("There is an exception while framing iu value");
	}

	if (!adCallFound) {
		System.out.println("Ad Call not found in charles session");
		logStep("Ad Call not found in charles session");
		Assert.fail("Ad Call not found in charles session");
	} else if (iuValue == null || iuValue.isEmpty()) {
		System.out.println("Ad Call not found/no value in ad call");
		logStep("Ad Call not found/no value in ad call");
		Assert.fail("Ad Call not found/no value in ad call");
	} else {
		System.out.println("Ad Call " + iuId + " found in charles session");
		logStep("Ad Call " + iuId + " found in charles session");
	}

}

private static String getNonCustomParamBy_iu_value(String qryValue, String cust_param) {
	List<String> listOfUisQrys = new ArrayList<String>();
	String cust_params = "";
	String[] key = null;
	// if (qryValue != null && qryValue.contains("cust_params")) {
	if (qryValue != null && qryValue.contains(cust_param)) {
		cust_params = qryValue.substring(qryValue.indexOf(cust_param));
		cust_params = cust_params.replace("%26", "&");
		cust_params = cust_params.replace("%2C", ",");
		cust_params = cust_params.replace("%3D", "=");
		cust_params = cust_params.replace("%2F", "/");
		cust_params = cust_params.replace("%3A", ":");
		cust_params = cust_params.replace("%3F", "?");
	}
	if (cust_params.indexOf(cust_param) >= 0) {
		try {
			cust_params = cust_params.substring(cust_params.indexOf(cust_param + "="));
			cust_params = cust_params.substring(cust_params.indexOf(cust_param));
		} catch (Exception e) {
			cust_params = cust_params.substring(cust_params.indexOf("&" + cust_param + "="));
			cust_params = cust_params.substring(cust_params.indexOf(cust_param));
		}
		// cust_params = cust_params.substring(cust_params.indexOf(cust_param));
		String b[] = cust_params.split("&");
		cust_params = b[0];
		key = cust_params.split("=");
		cust_params = key[1];
	} else {
		cust_params = "";
	}
	return cust_params;
}


public static boolean verify_criteo_request_for_given_placementId_inJsonRequestBody(String placementId,
		String apiData) throws Exception {

	// Functions.Read_Turbo_api("Cust_Param", readSheet);

	JSONParser parser = new JSONParser();
	// System.out.println("adreq1 is : "+adreq1.toString());
	Object obj = parser.parse(new String(apiData));
	// System.out.println("obj : "+obj);
	JSONObject jsonObject = (JSONObject) obj;
	String ApiParamValue = "";
	String JsonParam = null;

	JSONObject mainTag = null;
	JSONArray eleArray = null;
	boolean matchFound = false;

	try {
		JsonParam = "slots".trim();
		eleArray = (JSONArray) jsonObject.get(JsonParam);
		// System.out.println(eleArray);
		try {

			ArrayList<String> Ingredients_names = new ArrayList<>();
			for (int i = 0; i < eleArray.size(); i++) {

				String arrayElement = String.valueOf(eleArray.get(i));

				Ingredients_names.add(arrayElement);
				obj = parser.parse(new String(arrayElement));
				jsonObject = (JSONObject) obj;
				mainTag = (JSONObject) obj;

				try {
					String cApiParamValue = String.valueOf(mainTag.get("placementId"));
					if (cApiParamValue.equalsIgnoreCase(placementId)) {
						/*
						 * if (cust_param.equalsIgnoreCase("size")) { String width =
						 * String.valueOf(mainTag.get("width")); String height =
						 * String.valueOf(mainTag.get("height")); ApiParamValue =
						 * width.concat("x").concat(height); } else { ApiParamValue =
						 * String.valueOf(mainTag.get(cust_param)); }
						 */
						matchFound = true;

					} else {
						// System.out.println("... noticed");
						continue;
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					continue;
				}

			}
		} catch (Exception e) {

		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	/*
	 * try { JSONArray arrayElementValues = (JSONArray) mainTag .get(cust_param);
	 * ApiParamValue = String.valueOf(arrayElementValues.get(0)); } catch (Exception
	 * e) { ApiParamValue = String.valueOf(mainTag.get(cust_param)); }
	 */
	// System.out.println(cust_param + " Param Values from Criteo API Call is : " +
	// ApiParamValue);
	// logStep(cust_param + " Param Values from Criteo API Call is : " +
	// ApiParamValue);
	/*
	 * if (ApiParamValue.equalsIgnoreCase("null")) { ApiParamValue = "nl"; }
	 */
	// return ApiParamValue;

	return matchFound;
}

public static String get_criteo_response_parameter_value_by_placementId_inJsonResponseBody(String placementId,
		String cust_param, String apiData) throws Exception {

	// Functions.Read_Turbo_api("Cust_Param", readSheet);

	JSONParser parser = new JSONParser();
	// System.out.println("adreq1 is : "+adreq1.toString());
	Object obj = parser.parse(new String(apiData));
	// System.out.println("obj : "+obj);
	JSONObject jsonObject = (JSONObject) obj;
	String ApiParamValue = "";
	String JsonParam = null;

	JSONObject mainTag = null;
	JSONArray eleArray = null;

	try {
		JsonParam = "slots".trim();
		eleArray = (JSONArray) jsonObject.get(JsonParam);
		// System.out.println(eleArray);
		try {

			ArrayList<String> Ingredients_names = new ArrayList<>();
			for (int i = 0; i < eleArray.size(); i++) {

				String arrayElement = String.valueOf(eleArray.get(i));

				Ingredients_names.add(arrayElement);
				obj = parser.parse(new String(arrayElement));
				jsonObject = (JSONObject) obj;
				mainTag = (JSONObject) obj;

				try {
					String cApiParamValue = String.valueOf(mainTag.get("placementId"));
					if (cApiParamValue.equalsIgnoreCase(placementId)) {
						if (cust_param.equalsIgnoreCase("size")) {
							String width = String.valueOf(mainTag.get("width"));
							String height = String.valueOf(mainTag.get("height"));
							ApiParamValue = width.concat("x").concat(height);
						} else {
							ApiParamValue = String.valueOf(mainTag.get(cust_param));
						}

					} else {
						// System.out.println("... noticed");
						continue;
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					continue;
				}

			}
		} catch (Exception e) {

		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	/*
	 * try { JSONArray arrayElementValues = (JSONArray) mainTag .get(cust_param);
	 * ApiParamValue = String.valueOf(arrayElementValues.get(0)); } catch (Exception
	 * e) { ApiParamValue = String.valueOf(mainTag.get(cust_param)); }
	 */
	System.out.println(cust_param + " Param Values from Criteo API Call is : " + ApiParamValue);
	logStep(cust_param + " Param Values from Criteo API Call is : " + ApiParamValue);
	/*
	 * if (ApiParamValue.equalsIgnoreCase("null")) { ApiParamValue = "nl"; }
	 */
	return ApiParamValue;
}


public static void verifyingdailydetailiuu() throws Exception {
	String expected_data = null;
	String today=null;
	String day1=null;
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	
	//String[][] exceldata=read_excel_data.exceldataread("NextGenIM");
//	logStep("Verifying  SOD custum param for  iu=%2F7646%2Fapp_android_us%2Fdb_display%2Fcard%2Fradar ad call");
	


	today=Ad.findElementById("com.weather.Weather:id/daily_details_day_title").getText();
	String days=today.replace(today, today+1);
	//System.out.println("day from the UI is  " +day);
	//logStep("day from the UI is  " +day);
	 currentday1=days.toLowerCase();
	
	
	

	
}


public static void validate_Amazon_aax_call_parameter(String sheetName,
		String cust_param, String expected) throws Exception {
	String[][] data = read_excel_data.exceldataread(sheetName);
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	String host = data[2][1];
	String path =data[3][1];

	boolean flag = verifyAPICalWithHostandPath(host, path);
	if (flag) {
		System.out.println(host + path + " call is present in Charles session");
		logStep(host + path + " call is present in Charles session");

		String actual = get_param_value_from_APIRequest(host, path, cust_param);

		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("Custom Parameter :" + cust_param + " value: " + actual
					+ " is matched with the expected value " + expected);
			logStep("Custom Parameter :" + cust_param + " value: " + actual + " is matched with the expected value "
					+ expected);
		} else {
			System.out.println("Custom Parameter :" + cust_param + " value: " + actual
					+ " is not matched with the expected value " + expected);
			logStep("Custom Parameter :" + cust_param + " value: " + actual
					+ " is not matched with the expected value " + expected);
			Assert.fail("Custom Parameter :" + cust_param + " value: " + actual
					+ " is not matched with the expected value " + expected);
		}

	} else {
		System.out.println(host + path + " call is not present in Charles session, hence Custom Parameter: "
				+ cust_param + " validation skipped");
		logStep(host + path + " call is not present in Charles session, hence Custom Parameter: " + cust_param
				+ " validation skipped");

		Assert.fail(host + path + " call is not present in Charles session, hence Custom Parameter: " + cust_param
				+ " validation skipped");

	}

}


public static void validate_Noncustom_param_val_of_gampad(String sheetName, String cust_param,
		String expected) throws Exception {
	
	String[][] data = read_excel_data.exceldataread(sheetName);
	DeviceStatus device_status = new DeviceStatus();
	int Cap = device_status.Device_Status();
	boolean adCallFound = false;

	// Read the content form file
	File fXmlFile = new File(CharlesFunctions.outfile.getName());

	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	dbFactory.setValidating(false);
	dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
	// dbFactory.setNamespaceAware(true);
	dbFactory.setFeature("http://xml.org/sax/features/validation", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
	dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	Document doc = dBuilder.parse(fXmlFile);
	// Getting the transaction element by passing xpath expression
	NodeList nodeList = doc.getElementsByTagName("transaction");
	String xpathExpression = "charles-session/transaction/@query";
	List<String> getQueryList = evaluateXPath(doc, xpathExpression);

	// Getting custom_params amzn_b values
	List<String> customParamsList = new ArrayList<String>();

	String iuId = null;
	// "iu=%2F7646%2Fapp_iphone_us%2Fdb_display%2Fhome_screen%2Ftoday";
	if (sheetName.equalsIgnoreCase("PreRollVideo")) {
		iuId = videoIUValue;
		System.out.println(iuId);
	} 
	else {
		iuId = data[11][1];
	}
	String tempCustmParam = null;
	for (String qry : getQueryList) {
		if (qry.contains(iuId)) {
			adCallFound = true;
			tempCustmParam = getNonCustomParamBy_iu_value(qry, cust_param);
			//if (!"".equals(tempCustmParam))
			// customParamsList.add(getCustomParamsBy_iu_value(qry));
			break;
		}

	}

	if (!adCallFound) {
		System.out.println("Ad Call :" + iuId + " not found in charles session, hence Custom Parameter: "
				+ cust_param + " validation skipped");
		logStep("Ad Call :" + iuId + " not found in charles session, hence Custom Parameter: " + cust_param
				+ " validation skipped");
		Assert.fail("Ad Call :" + iuId + " not found in charles session, hence Custom Parameter: " + cust_param
				+ " validation skipped");
	} else if (adCallFound && !tempCustmParam.isEmpty()) {
		System.out.println(cust_param + " value of from gampad call  of : " + iuId + " is " + tempCustmParam);
		if (expected.equalsIgnoreCase("NotNull")) {
			if (!tempCustmParam.equalsIgnoreCase("nl")) {
				System.out.println("Custom Parameter :" + cust_param + " value: " + tempCustmParam
						+ " is matched with the expected value " + expected);
				logStep("Custom Parameter :" + cust_param + " value: " + tempCustmParam
						+ " is matched with the expected value " + expected);
			} else {
				System.out.println("Custom Parameter :" + cust_param + " value: " + tempCustmParam
						+ " is not matched with the expected value " + expected);
				logStep("Custom Parameter :" + cust_param + " value: " + tempCustmParam
						+ " is not matched with the expected value " + expected);
				Assert.fail("Custom Parameter :" + cust_param + " value: " + tempCustmParam
						+ " is not matched with the expected value " + expected);
			}
		} else {
			if (tempCustmParam.equalsIgnoreCase(expected)) {
				System.out.println("Custom Parameter :" + cust_param + " value: " + tempCustmParam
						+ " is matched with the expected value " + expected);
				logStep("Custom Parameter :" + cust_param + " value: " + tempCustmParam
						+ " is matched with the expected value " + expected);
			} else {
				System.out.println("Custom Parameter :" + cust_param + " value: " + tempCustmParam
						+ " is not matched with the expected value " + expected);
				logStep("Custom Parameter :" + cust_param + " value: " + tempCustmParam
						+ " is not matched with the expected value " + expected);
				Assert.fail("Custom Parameter :" + cust_param + " value: " + tempCustmParam
						+ " is not matched with the expected value " + expected);
			}
		}

	} else if (tempCustmParam == null || tempCustmParam.isEmpty()) {
		System.out.println("Custom parameter :" + cust_param
				+ " not found/no value in ad call, hence Custom Parameter: " + cust_param + " validation skipped");
		logStep("Custom parameter :" + cust_param + " not found/no value in ad call, hence Custom Parameter: "
				+ cust_param + " validation skipped");
		Assert.fail("Custom parameter :" + cust_param + " not found/no value in ad call, hence Custom Parameter: "
				+ cust_param + " validation skipped");
	}

}



}