package twc.Regression.CustomParamValidation;

import io.appium.java_client.MobileElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import twc.Regression.General.DeviceStatus;
import twc.Regression.ReadDataFromFile.*;
import twc.Regression.TestCases.Custom_Parameters_Verification;
import twc.Regression.CustomParamValidation.validate_CustomParameter;
import twc.Regression.Driver.Drivers;
public class CustomParamFunctions extends Drivers {

	static Map<String, String> fhicflocval=null;
	@SuppressWarnings("static-access")
	public static String validate_results(String sheetname,int feed) throws Exception{
		Map<String, String> turbo=null;
		String result=null;
		String plln = null;
		String sev = null;
		Map<String, String> pubad = get_pub_ad_custom_value(feed);
		if(sheetname.equals("plln")){
			turbo = get_plln_from_turbo_call();
			plln = turbo.get(sheetname).substring(1, turbo.get(sheetname).length() -1);
			String[] pllnres = plln.split(",");
			plln= pllnres[0];
		}
		else if(sheetname.equals("sev")){
			sev = fhicflocval.get(sheetname);
		}
		else{
			logStep("reading turbo call value");
			turbo = get_turbo_call_value();
		}

		validate_CustomParameter validate_cust = new validate_CustomParameter();

		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param", "Validate");
		File f_validation= new File(properties.getProperty("ExcelFilePath_CustParam"));

		FileInputStream fis_validation = new FileInputStream(f_validation);
		HSSFWorkbook wb_validation = new HSSFWorkbook(fis_validation);
		HSSFSheet ws = wb_validation.getSheet("Validate");

		int rownum = ws.getLastRowNum()+1;
		for(int i = 1;i<rownum;i++){
			if(data[i][2].contains(sheetname)){


				if(sheetname.equals("plln")){
					result = validate_cust.validate_Custom_Parameter(sheetname,plln,pubad.get(data[i][2]));
				}
				else if(sheetname.equals("sev")){
					result = validate_cust.validate_Custom_Parameter(sheetname,sev,pubad.get(data[i][2]));
				}else{
					result = validate_cust.validate_Custom_Parameter(sheetname,turbo.get(data[i][1]),
							pubad.get(data[i][2]));
				}
				System.out.println("Result "+result);
				logStep("Result "+result);
				break;
			}
		}
		return result;
	}

	public static String validate_hard_code_results(String parameter,int feed) throws Exception{


		String result = null;
		String hardcode = null;
		String lang = "en";
		String plat = "wx_droid_phone";
		String ftl = "new";
		String pos1 = "top300";
		String pos = "p1,p2,p3,p4,p5,p6,top300";
		String tile = "1";
		String par = "nl";
		String zip="17015";
		String hlzip="534350";
		if(parameter.equals("lang")){
			hardcode=lang;
		}
		else if(parameter.equals("plat")){
			hardcode=plat;
		}
		else if(parameter.equals("ftl")){
			hardcode=ftl;
		}
		else if(parameter.equals("pos")){
			hardcode=pos;
		}
		else if(parameter.equals("tile")){
			hardcode=tile;
		}
		else if(parameter.equals("par")){
			hardcode=par;
		}
		else if(parameter.equals("zip")){
			hardcode=zip;
		}
		else if(parameter.equals("hlzip")){
			hardcode=zip;
		}

		Map<String, String> pubad = get_pub_ad_custom_value(feed);

		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param", "Validate");
		File f_validation= new File(properties.getProperty("ExcelFilePath_CustParam"));

		FileInputStream fis_validation = new FileInputStream(f_validation);
		HSSFWorkbook wb_validation = new HSSFWorkbook(fis_validation);
		HSSFSheet ws = wb_validation.getSheet("Validate");

		int rownum = ws.getLastRowNum()+1;
		for(int i = 1;i<rownum;i++){
			if(data[i][2].contains(parameter)){
				String pubad_val =pubad.get(data[i][2]);
				System.out.println("Expected "+parameter+" value : " +hardcode);
				logStep("Expected "+parameter+" value : " +hardcode);				
				System.out.println("PubAd "+parameter+" value : "+pubad_val);
				logStep("PubAd "+parameter+" value : "+pubad_val);
				if(pubad_val.equals(hardcode) || hardcode.contains(pubad_val)){
					System.out.println(parameter +" value is matched with : "+hardcode+"===="+pubad_val);
					logStep(parameter +" value is matched with : "+hardcode+"===="+pubad_val);
					result="Pass";
					System.out.println("Result "+result);
					logStep("Result "+result);
					break;
				}
				
				if(pubad_val.contains("nl")){
					System.out.println(parameter +" value is not  matched with : "+hardcode+"===="+pubad_val);
					logStep(parameter +" value is matched with : "+hardcode+"===="+pubad_val);
					result="Fail";
					System.out.println("Result "+result);
					logStep("Result "+result);
					break;
				}
				if(pubad_val.isEmpty()){
					System.out.println(parameter +" value is not  matched with : "+hardcode+"===="+pubad_val);
				   logStep(parameter +" value is not  matched with : "+hardcode+"===="+pubad_val);
					result="Fail";
					System.out.println("Result "+result);
					break;
				}
			}
		}
		return result;
	
	}

	public static String validate_not_null_results(String parameter,int feed) throws Exception{

		String result = null;
		String pubad_val =null;
		Map<String, String> pubad = get_pub_ad_custom_value(feed);

		if(parameter.equals("tf") && feed !=1 ){
			pubad_val="tf";
		}
		else{
			pubad_val = pubad.get(parameter);
		}
		
		pubad_val = pubad.get(parameter);
		if(!pubad_val.contains("nl")){
			System.out.println("PubAd "+parameter+" Data : "+pubad_val);
			logStep("PubAd "+parameter+" Data : "+pubad_val);
			result="Pass";
			System.out.println("Result "+result);
			logStep("Result "+result);
		}
		if(pubad_val.isEmpty() ){
			System.out.println("PubAd "+parameter+" Data : "+pubad_val);
			logStep("PubAd "+parameter+" Data : "+pubad_val);
			result="Fail";
			System.out.println("Result "+result);
			logStep("Result "+result);
		}
		
		if(pubad_val.contains("nl") ){
			System.out.println("PubAd "+parameter+" Data : "+pubad_val);
			logStep("PubAd "+parameter+" Data : "+pubad_val);
			result="Fail";
			System.out.println("Result "+result);
			logStep("Result "+result);
		}
		return result;
	}

	public static String validate_fhic_floc_results(String parameter,int feed) throws Exception{

		String result = null;
		Map<String, String> pubad = get_pub_ad_custom_value(feed);
		System.out.println("PubAd "+parameter+" Data : "+pubad.get(parameter));
		System.out.println("UI Values "+parameter+" Data : " +fhicflocval.get(parameter));
		if(pubad.get(parameter).equals(fhicflocval.get(parameter))){
			System.out.println("PubAd "+parameter+" Data : "+pubad.get(parameter));
			System.out.println("UI Values "+parameter+" Data : " +fhicflocval.get(parameter));
			result="Pass";
			System.out.println("Result "+result);
		}
		return result;
	}

	public static Map<String, String> get_fhic_floc_value() throws Exception{

		fhicflocval = new HashMap<String, String>();
		Thread.sleep(3000);
		MobileElement alertmodule=null;
		MobileElement hilo=null;
		String sevalertText ="No allert";

		try {

			WebDriverWait wait0 = new WebDriverWait(Ad, 10);
			wait0.until(ExpectedConditions.visibilityOf(Ad.findElementById("com.weather.Weather:id/current_conditions_alert_headline_phrase")));
			alertmodule = (MobileElement) Ad.findElementById("com.weather.Weather:id/current_conditions_alert_headline_phrase");
			if(alertmodule.isDisplayed()){
				sevalertText = alertmodule.getText();
				System.out.println(alertmodule.getText());
			}
			System.out.println("Alert found");
		} catch (Exception e) {
			System.out.println("No alert found");
		}

		try {
			WebDriverWait wait = new WebDriverWait(Ad, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.Weather:id/current_conditions_temperature_high_low")));
			//com.weather.Weather:id/hilo
			hilo = (MobileElement) Ad.findElementById("com.weather.Weather:id/current_conditions_temperature_high_low");
			//hilo =(MobileElement) Ad.findElementByXPath("");
		} catch (Exception e) {
			System.out.println("Unable to find the Fhic/Floc Values from UI");
		}




		String fhic_floc_val = hilo.getText();
		String fhic =null;
		String floc =null;
		String [] fhic_floc = fhic_floc_val.split("/");

		if(fhic_floc[0].toString().contains("--") || fhic_floc[1].toString().contains("--")){
			fhic = "-17"; floc = "-17";
			fhicflocval.put("fhic", fhic.toString());
			fhicflocval.put("floc", floc.toString());
			fhicflocval.put("sev", sevalertText);
		}
		else{
			floc = fhic_floc[1].toString().replace("°", "");
			int flocvalue2 = Integer.parseInt(floc);
			flocvalue2 =flocvalue2-32;
			double flocvalue3 =(double) ((flocvalue2)*(0.5555555556));
			flocvalue3 =Math.round(flocvalue3);
			flocvalue2 = (int)flocvalue3;
			floc =Integer.toString(flocvalue2);

			fhic = fhic_floc[0].toString().replace("°", "");
			int fhicvalue2 = Integer.parseInt(fhic);
			fhicvalue2 =fhicvalue2-32;
			double fhicvalue3 =(double) ((fhicvalue2)*(0.5555555556));
			fhicvalue3 =Math.round(fhicvalue3);
			fhicvalue2 = (int)fhicvalue3;
			fhic =Integer.toString(fhicvalue2);

			fhicflocval.put("fhic", fhic.toString());
			fhicflocval.put("floc", floc.toString());
			fhicflocval.put("sev", sevalertText);
		}

		return fhicflocval;
	}

	public static String validate_dsxcms_results(String parameter,int feed) throws Exception{

		String result=null;
		Map<String, String> pubad = get_pub_ad_custom_value(feed);
		Map<String, String> dsxcmscall = get_dsxcms_call_value(parameter);


		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param", "Validate");
		File f_validation= new File(properties.getProperty("ExcelFilePath_CustParam"));

		FileInputStream fis_validation = new FileInputStream(f_validation);
		HSSFWorkbook wb_validation = new HSSFWorkbook(fis_validation);
		HSSFSheet ws = wb_validation.getSheet("Validate");

		int rownum = ws.getLastRowNum()+1;
		for(int i = 1;i<rownum;i++){
			if(data[i][2].contains(parameter)){
				String pubad_val =pubad.get(data[i][2]);
				System.out.println("PubAd "+parameter+" Data : "+pubad_val);
				logStep("PubAd "+parameter+" Data : "+pubad_val);
				System.out.println("DSXCMSAssets Call "+parameter+" Data : " +dsxcmscall.get(data[i][1]));
				logStep("DSXCMSAssets Call "+parameter+" Data : " +dsxcmscall.get(data[i][1]));
				if(pubad_val.equals(dsxcmscall.get(data[i][1]))){
					result="Pass";
					System.out.println("Result "+result);
					logStep("Result "+result);
					break;
				}

			}
		}
		return result;
	}

	public static String validate_dsx_results(String parameter,int feed) throws Exception{

		String result=null;
		Map<String, String> pubad = get_pub_ad_custom_value(feed);
		System.out.println("reading turbo call value");
		logStep("reading turbo call value");
		Map<String, String> dsxcall = get_dsx_call_value1();
	

		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param", "Validate");
		File f_validation= new File(properties.getProperty("ExcelFilePath_CustParam"));

		FileInputStream fis_validation = new FileInputStream(f_validation);
		HSSFWorkbook wb_validation = new HSSFWorkbook(fis_validation);
		HSSFSheet ws = wb_validation.getSheet("Validate");

		int rownum = ws.getLastRowNum()+1;
		for(int i = 1;i<rownum;i++){
			if(data[i][2].equals(parameter)){
				String pubad_val =pubad.get(data[i][2]).replace("%2520", " ");
				System.out.println("Turbo Call "+parameter+" Data : " +dsxcall.get(data[i][1]));
				System.out.println("PubAd "+parameter+" Data : "+pubad_val);
				logStep("Turbo Call "+parameter+" Data : " +dsxcall.get(data[i][1]));
				logStep("PubAd "+parameter+" Data : "+pubad_val);
				if(pubad_val.equalsIgnoreCase(dsxcall.get(data[i][1]))){
					//System.out.println("DSX Call Data " +dsxcall.get(data[i][1]+ "matched with PubAd call Data" +pubad_val);
					 logStep(parameter+" result  matched with ad request --"+pubad_val +" Api response -- "+dsxcall.get(data[i][1]));
				    	System.out.println(parameter+" result  matched with ad request --"+pubad_val +" Api response -- "+dsxcall.get(data[i][1]));
					result="Pass";
					System.out.println("Result "+result);
					logStep("Result "+result);
					break;
				}
				 if(dsxcall.get(data[i][1]) == null) {
					if(pubad_val.contains("nl")) {
					 logStep(parameter+" result  matched with ad request --"+pubad_val +" Api response -- "+dsxcall.get(data[i][1]));
				    	System.out.println(parameter+" result  matched with ad request --"+pubad_val +" Api response -- "+dsxcall.get(data[i][1]));
					result="Pass";
					System.out.println("Result "+result);
					logStep("Result "+result);
					break;
				}}
				else {
					result="Fail";
				 logStep(parameter+" result not matched with ad request --"+pubad_val +" Api response -- "+dsxcall.get(data[i][1]));
			    	System.out.println(parameter+" result not matched with ad request --"+pubad_val +" Api response -- "+dsxcall.get(data[i][1]));		
					//Assert.fail(parameter+" result not matched with ad request --"+pubad_val +" Api response -- "+dsxcall.get(data[i][1]));
				}

			}
		}
		return result;
	}

	public static String validate_fautual_results(String parameter,int feed) throws Exception{

		String result=null;
		Map<String, String> pubad = get_pub_ad_custom_value(feed);
		Map<String, String> factualcall = get_factual_call_value(parameter);


		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param", "Validate");
		File f_validation= new File(properties.getProperty("ExcelFilePath_CustParam"));

		FileInputStream fis_validation = new FileInputStream(f_validation);
		HSSFWorkbook wb_validation = new HSSFWorkbook(fis_validation);
		HSSFSheet ws = wb_validation.getSheet("Validate");

		int rownum = ws.getLastRowNum()+1;
		for(int i = 1;i<rownum;i++){
			if(data[i][2].contains(parameter)){
				String pubad_val =pubad.get(data[i][2]);
				System.out.println("PubAd "+parameter+" Data : "+pubad_val);
				String finalval = factualcall.get(parameter).substring(1, factualcall.get(parameter).length() -1);
				System.out.println("Factual Call "+parameter+" Data : " + finalval);
				if(pubad_val.equals(finalval) || pubad_val.equals("nl")){
					result="Pass";
					System.out.println("Result "+result);
					break;
				}

			}
		}
		return result;
	}

	public static String validate_lotame_results(String parameter,int feed) throws Exception{

		String result=null;
		Map<String, String> pubad = get_pub_ad_custom_value(feed);
		Map<String, String> lotamecall = get_lotame_call_value(parameter);


		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param", "Validate");
		File f_validation= new File(properties.getProperty("ExcelFilePath_CustParam"));

		FileInputStream fis_validation = new FileInputStream(f_validation);
		HSSFWorkbook wb_validation = new HSSFWorkbook(fis_validation);
		HSSFSheet ws = wb_validation.getSheet("Validate");

		int rownum = ws.getLastRowNum()+1;
		for(int i = 1;i<rownum;i++){
			if(data[i][2].contains(parameter)){
				String pubad_val =pubad.get(data[i][2]);
				System.out.println("PubAd "+parameter+" Data : "+pubad_val);
				String finalval = lotamecall.get(parameter).substring(1, lotamecall.get(parameter).length() -1);
				finalval = finalval.replace(" ", "");
				System.out.println("Factual Call "+parameter+" Data : " + finalval);
				if(pubad_val.equals(finalval)){
					result="Pass";
					System.out.println("Result "+result);
					break;
				}

			}
		}
		return result;
	}

	public static String validate_wfxtg_results(String parameter,int feed) throws Exception{

		String result=null;
		String wfxtgval=null;
		Map<String, String> pubad = get_pub_ad_custom_value(feed);
		System.out.println("reading wfxg api call");
		Map<String, String> wfxcall = get_wfxtg_call_value(parameter);

		//System.out.println("wfxcall"+wfxcall);
		if(parameter.equals("wfxtg")){
			wfxtgval = wfxcall.get("current").substring(1, wfxcall.get("current").length() -1); 
		}
		else if(parameter.equals("hlzip")){
			wfxtgval = wfxcall.get(parameter); 
		}
		else{
			wfxtgval = wfxcall.get(parameter).substring(1, wfxcall.get(parameter).length() -1);
		}


		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param", "Validate");
		File f_validation= new File(properties.getProperty("ExcelFilePath_CustParam"));

		FileInputStream fis_validation = new FileInputStream(f_validation);
		HSSFWorkbook wb_validation = new HSSFWorkbook(fis_validation);
		HSSFSheet ws = wb_validation.getSheet("Validate");

		int rownum = ws.getLastRowNum()+1;
		for(int i = 1;i<rownum;i++){
			if(data[i][2].contains(parameter)){
				String pubad_val =pubad.get(data[i][2]);
				System.out.println("PubAd "+parameter+" Data : "+pubad_val);
				logStep("PubAd "+parameter+" Data : "+ pubad_val);
				if(parameter.equals("wfxtg")){
					System.out.println("WFXTG Call "+parameter+" Data : " +wfxtgval);
					logStep("WFXTG Call "+parameter+" Data : " +wfxtgval);
					if(pubad_val.equals(wfxtgval) && !pubad_val.equals("nl")){
						result="Pass";
						System.out.println("Result "+result);
						logStep("Result "+result);
						break;
					}
				}
				else{
					System.out.println("WFXTG Call "+parameter+" Data : " +wfxtgval);
					if( pubad_val.equals("nl")){
						result="Fail";
						System.out.println("Result "+result);
						logStep("Result "+result);
						break;
					}
				}
			}
		}
		return result;
	}

	public static Map<String, String> get_pub_ad_custom_value(int feed) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String, String> mapkeys = new HashMap<String, String>();

		String expectedValues =null;
		String[][] exceldata = read_excel_data.exceldataread_Custom_Parameters("Cust_Param","PubAds");

		//			read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//			String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		if(sb.toString().contains(Custom_Parameters_Verification.feedAd)){

			String Read_API_Call_Data = sb.toString().substring(sb.toString().indexOf(Custom_Parameters_Verification.feedAd));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[7][Cap]));
			required_info= required_info.toString().replaceAll(exceldata[8][Cap], "=");
			required_info= required_info.toString().replaceAll(exceldata[9][Cap], "&");
			required_info= required_info.toString().replaceAll(exceldata[10][Cap], ",");

			String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[14][Cap]),required_info.indexOf(exceldata[15][Cap]));
			expectedValues = expected_data.toString();

			String[] arrays = expectedValues.split("&");
			for(String keys : arrays){
				if(keys.contains("=")){
					String[] key = keys.split("=");
					mapkeys.put(key[0].toString(), key[1].toString());
				}
			}
		}
		return mapkeys;
	}


	public static Map<String, String> get_turbo_call_value() throws Exception{
		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_map_results = new HashMap<String, String>();

		String expectedValues =null;
		String[][] exceldata = read_excel_data.exceldataread_Custom_Parameters("Cust_Param","TurboCall");

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		if(sb.toString().contains(exceldata[1][Cap])){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().indexOf(exceldata[3][Cap]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().
					indexOf(exceldata[3][Cap]));
			required_info=required_info.replaceAll(" decoded=\"true\"", "");
			String expected_data =required_info.toString().substring(required_info.indexOf(", \"v3-wx-observations-current\": "),required_info.indexOf("  , \"v3-wx-forecast-daily-15day\": "));
			//String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+9,
			//required_info.indexOf(exceldata[5][Cap]));
			expectedValues = expected_data.toString();
              expectedValues = expected_data.toString();
			
			expectedValues=expectedValues.replaceAll("\"cloudCeiling\":null,", "");
			expectedValues=expectedValues.replaceFirst(",", "");
			expectedValues=expectedValues.replace(expectedValues,"{" + expectedValues + "}" );

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(expectedValues);
			JSONObject jsonObject = (JSONObject) obj;

			JSONObject jsonObservation = (JSONObject) jsonObject.get("v3-wx-observations-current");
			for(Object key:jsonObservation.keySet()){
				if(jsonObservation.get(key) != null){
					expected_map_results.put(key.toString(), jsonObservation.get(key).toString());
				}
			}
		}
		return expected_map_results;
	
}

	public static Map<String, String> get_plln_from_turbo_call() throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_map_results = new HashMap<String, String>();

		String expectedValues =null;
		String[][] exceldata = read_excel_data.exceldataread_Custom_Parameters("Cust_Param","TurboCall");

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		if(sb.toString().contains(exceldata[1][Cap])){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[3][Cap]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[3][Cap]));
			String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+15,required_info.indexOf(exceldata[5][Cap]));
			expectedValues = expected_data.toString();

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(expectedValues);
			JSONObject jsonObject = (JSONObject) obj;

			JSONObject jsonObservation = (JSONObject) jsonObject.get("vt1pollenforecast");
			Object pllnvalue = jsonObservation.get("grass");
			expected_map_results.put("plln", pllnvalue.toString());
		}
		return expected_map_results;
	}
	public static Map<String, String> get_lotame_call_value(String parameter) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_map_results = new HashMap<String, String>();
		List<String> sg_res = new ArrayList<String>();

		String expectedValues =null;
		String[][] exceldata = read_excel_data.exceldataread_Custom_Parameters("Cust_Param","LotameCall");

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		if(sb.toString().contains(exceldata[1][Cap])){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[2][Cap]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[3][Cap]));
			String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+15,required_info.indexOf(exceldata[5][Cap]));
			expectedValues = expected_data.toString();

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(expectedValues);
			JSONObject jsonObject = (JSONObject) obj;

			JSONObject jsonProfile = (JSONObject) jsonObject.get("Profile");
			JSONObject jsonAudiences = (JSONObject) jsonProfile.get("Audiences");
			JSONArray jsonAudience = (JSONArray) jsonAudiences.get("Audience");

			for(int i=0;i< jsonAudience.size();i++){
				JSONObject filter = (JSONObject) jsonAudience.get(i);
				if(filter.containsKey("id")){
					sg_res.add(filter.get("id").toString());
				}
			}
			expected_map_results.put("sg", sg_res.toString());
		}
		return expected_map_results;
	}
	public static Map<String, String> get_factual_call_value(String parameter) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_map_results = new HashMap<String, String>();

		String expectedValues =null;
		String[][] exceldata = read_excel_data.exceldataread_Custom_Parameters("Cust_Param","FactualCall");

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		if(sb.toString().contains(exceldata[1][Cap])){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[2][Cap]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[3][Cap]));
			String expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+15,required_info.indexOf(exceldata[5][Cap]));
			expectedValues = expected_data.toString();

			List<String> fgeo_res = new ArrayList<String>();
			List<String> faud_res = new ArrayList<String>();

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(expectedValues);
			JSONObject jsonObject = (JSONObject) obj;

			if(parameter.equals("fgeo")){
				JSONArray fgeoval = (JSONArray) jsonObject.get("proximity");
				for(int i=0;i< fgeoval.size();i++){

					JSONObject filter = (JSONObject) fgeoval.get(i);
					if(filter.containsKey("index")){
						fgeo_res.add(filter.get("index").toString());
					}
				}
				expected_map_results.put("fgeo", fgeo_res.toString());
			}
			else{
				JSONArray faudval = (JSONArray) jsonObject.get("set");
				for(int i=0;i< faudval.size();i++){

					JSONObject filter = (JSONObject) faudval.get(i);
					if(filter.containsKey("group")){
						faud_res.add(filter.get("group").toString());
					}
				}
				expected_map_results.put("faud", faud_res.toString());
			}

		}
		return expected_map_results;
	}	
	public static Map<String, String> get_dsx_call_value() throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_map_results = new HashMap<String, String>();

		String expectedValues =null;
		String[][] exceldata = read_excel_data.exceldataread_Custom_Parameters("Cust_Param","DsxCall");

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		if(sb.toString().contains(exceldata[1][Cap])){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[3][Cap]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[3][Cap]));
			String expected_data;
			try
			{
				expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+15,required_info.indexOf(exceldata[5][Cap]));
			}catch(Exception e){
				expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+15,required_info.indexOf("]]></body>"));
			}
			expectedValues = expected_data.toString();

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(expectedValues);
			JSONObject jsonObject = (JSONObject) obj;

			for(Object key:jsonObject.keySet()){
				if(jsonObject.get(key) != null){
					expected_map_results.put(key.toString(), jsonObject.get(key).toString());
				}
			}
		}
		else{
			System.out.println("DsxCall Call Not Generated");
			Assert.fail("DsxCall Call Not Generated");
		}
		return expected_map_results;
	}

	public static Map<String, String> get_dsx_call_value1() throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_map_results = new HashMap<String, String>();

		String expectedValues =null;
		String[][] exceldata = read_excel_data.exceldataread_Custom_Parameters("Cust_Param","DsxCall");

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		if(sb.toString().contains(exceldata[1][Cap])){
		//	String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[3][Cap]));
		  String Read_API_Call_Data = sb.toString().substring(sb.toString().indexOf("v3-wx-forecast-daily-15day-cognitiveHealth"));
		//	String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[3][Cap]));
			
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[3][Cap]));
			String expected_data;
			String expectedValues2=null;
			String expectedValues3=null;
			expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap]),required_info.indexOf(exceldata[5][Cap]));
			expectedValues = expected_data.toString();
			expectedValues2=expectedValues.replaceFirst(",", "");
			expectedValues3=expectedValues2.replace(expectedValues2,"{" + expectedValues2 + "}" );
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(expectedValues3);
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject jsonObservation = (JSONObject) jsonObject.get("v3-location-point");
			JSONObject jsonObservation1 = (JSONObject) jsonObservation.get("location");
			
			for(Object key:jsonObservation1.keySet()){
			if(jsonObservation1.get(key) != null){
					expected_map_results.put(key.toString(), jsonObservation1.get(key).toString());
				}
			}
		}
		else{
			System.out.println("DsxCall Call Not Generated");
			Assert.fail("DsxCall Call Not Generated");
		}
		return expected_map_results;
		
	}
	
	public static Map<String, String> get_wfxtg_call_value(String parameter) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_map_results = new HashMap<String, String>();

		String expectedValues =null;
		String[][] exceldata = read_excel_data.exceldataread_Custom_Parameters("Cust_Param","WfxCall");

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		if(sb.toString().contains(exceldata[1][Cap])){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[2][Cap]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[3][Cap]));
			String expected_data;
			try {
				expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+15,required_info.indexOf("]]]]><![CDATA[></body>"));
				
			}catch(Exception e) {
				expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+15,required_info.indexOf(exceldata[5][Cap]));
			}
			expectedValues = expected_data.toString();

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(expectedValues);
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject wfxtgObject = (JSONObject) jsonObject.get("wfxtg");
			if(parameter.equals("wfxtg")){
				for(Object key:wfxtgObject.keySet()){
					if(wfxtgObject.get(key) != null){
						expected_map_results.put(key.toString(), wfxtgObject.get(key).toString());
					}
				}
			}
			else if(parameter.equals("hlzip")){
				JSONArray scatterSegs = (JSONArray) wfxtgObject.get("scatterSegs");
				JSONObject filter = (JSONObject) scatterSegs.get(1);
				JSONArray hlzip = (JSONArray) filter.get("hzcs");
				JSONObject hlzipVal = (JSONObject) hlzip.get(0);
				expected_map_results.put(parameter, hlzipVal.get("zip").toString());
			}
			else{
				JSONArray scatterSegs = (JSONArray) wfxtgObject.get("scatterSegs");
				for(int i=0;i< scatterSegs.size();i++){
					JSONObject filter = (JSONObject) scatterSegs.get(i);
					JSONArray zcsval = (JSONArray) filter.get(parameter);
					if(zcsval!=null){
						JSONObject zcsval1 = (JSONObject) zcsval.get(0);
						try {
							expected_map_results.put(parameter, zcsval1.get("segments").toString());
						}catch(Exception e) {
							System.out.println("segments are empty");
							//e.printStackTrace();
						}
					}
				}
			}
		}
		else{
			System.out.println("WFX TG Call Not Generated");
			//Assert.fail("WFX TG Call Not Generated");
		}
		return expected_map_results;
	}

	public static Map<String, String> get_dsxcms_call_value(String parameter) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();

		Map<String , String> expected_map_results = new HashMap<String, String>();

		String expectedValues =null;
		String[][] exceldata = read_excel_data.exceldataread_Custom_Parameters("Cust_Param","DsxCmsAssetsCall");

		//		read_xml_data_into_buffer xml_data_into_buffer = new read_xml_data_into_buffer();
		//		String sb = xml_data_into_buffer.read_xml_file_into_buffer_string();

		if(sb.toString().contains(exceldata[1][Cap])){
			String Read_API_Call_Data = sb.toString().substring(sb.toString().lastIndexOf(exceldata[2][Cap]));
			String required_info = Read_API_Call_Data.toString().substring(Read_API_Call_Data.toString().indexOf(exceldata[3][Cap]));
			String expected_data;
			try {
				expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+15,required_info.indexOf(exceldata[5][Cap]));
			}catch(Exception e) {
				 expected_data = required_info.toString().substring(required_info.indexOf(exceldata[4][Cap])+15,required_info.indexOf("]]></body>"));
			}
			expectedValues = expected_data.toString();
			String expectedValues4 = expectedValues.replace(expectedValues, expectedValues+ "}" );
		//	expected_data = expectedValues.toString().substring(beginIndex, endIndex);

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(expectedValues4);
			JSONObject jsonObject = (JSONObject) obj;

			//		JSONArray articles = (JSONArray) jsonObject.get("articles");
			//		JSONObject articles0 = (JSONObject) articles.get(0);
			String paramVal = jsonObject.get(parameter).toString();
			expected_map_results.put(parameter, paramVal);
		}
		else{
			System.out.println("DsxCMSCall Call Not Generated");
			Assert.fail("DsxCMSCall Call Not Generated");
		}
		return expected_map_results;
	}
	
	public static String validate_hard_code_zip_results(String parameter,int feed) throws Exception{


		String result = null;
		String hardcode = null;
		String lang = "en";
		String plat = "wx_droid_phone";
		String ftl = "new";
		String pos = "p1,p2,p3,p4,p5,p6,top300";
		String tile = "1";
		String par = "nl";
		String zip="500034";

		if(parameter.equals("lang")){
			hardcode=lang;
		}
		else if(parameter.equals("plat")){
			hardcode=plat;
		}
		else if(parameter.equals("ftl")){
			hardcode=ftl;
		}
		else if(parameter.equals("pos")){
			hardcode=pos;
		}
		else if(parameter.equals("tile")){
			hardcode=tile;
		}
		else if(parameter.equals("zip")){
			hardcode=zip;
		}
		

		Map<String, String> pubad = get_pub_ad_custom_value(feed);

		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param", "Validate1");
		File f_validation= new File(properties.getProperty("ExcelFilePath_CustParam"));

		FileInputStream fis_validation = new FileInputStream(f_validation);
		HSSFWorkbook wb_validation = new HSSFWorkbook(fis_validation);
		HSSFSheet ws = wb_validation.getSheet("Validate1");

		int rownum = ws.getLastRowNum()+1;
		for(int i = 1;i<rownum;i++){
			if(data[i][2].equals(parameter)){
				String pubad_val =pubad.get(data[i][2]);
				System.out.println("Expected "+parameter+" value : " +hardcode);
				
				System.out.println("PubAd "+parameter+" value : "+pubad_val);
				
				if(pubad_val.equals(hardcode)){
					System.out.println(parameter +" value is matched with : "+hardcode+"===="+pubad_val);
					result="Pass";
					System.out.println("Result "+result);
					break;
				}
			if(pubad_val.contains("nl"))
				{
				System.out.println(parameter +" value is not matched with : "+hardcode+"===="+pubad_val);
				result="Fail";
				System.out.println("Result "+result);
				break;
				}
			if(!hardcode.matches(pubad_val)) {
				System.out.println(parameter +" value is not matched with : "+hardcode+"===="+pubad_val);
				result="Fail";
				System.out.println("Result "+result);
				break;
			}
		}
	
	}
		return result;
	}
}