package twc.Regression.CustomParamValidation;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;
import twc.Regression.Driver.Drivers;
import twc.Regression.ReadDataFromFile.*;

public class validate_CustomParameter extends Drivers {
	
	public static SoftAssert sf=new SoftAssert();
	public static String validateVal =null;
	
	public static String validate_Custom_Parameter(String sheetname, String expectedVal, String actualVal) throws Exception{
		
		File f_validation= new File(properties.getProperty("ExcelFilePath_CustParam_Result"));
		
		FileInputStream fis_validation = new FileInputStream(f_validation);
		HSSFWorkbook wb_validation = new HSSFWorkbook(fis_validation);
		HSSFSheet ws = wb_validation.getSheet(sheetname);

		int rownum = ws.getLastRowNum()+1;
		
		if(sheetname.equals("tmp") || sheetname.equals("tmpr") || sheetname.equals("cnd")){
			
			validateVal =  general_validate(sheetname,expectedVal,actualVal,rownum);
		}
		else if(sheetname.equals("hmid") ){
			validateVal =  hmid_validate(sheetname,expectedVal,actualVal,rownum);
		}
		else if(sheetname.equals("wind") ){
			validateVal =  wind_validate(sheetname,expectedVal,actualVal,rownum);
		}
		else if(sheetname.equals("uv") ){
			validateVal =  uv_validate(sheetname,expectedVal,actualVal,rownum);
		}
		else if(sheetname.equals("fltmpc") || sheetname.equals("tmpc")||sheetname.equals("fhic") ||sheetname.equals("floc")){
			validateVal =  fltmpc_validate(sheetname,expectedVal,actualVal,rownum);
		}
		else if(sheetname.equals("sev")){
			validateVal =  sev_validate(sheetname,expectedVal,actualVal,rownum);
		}
		else if(sheetname.equals("plln")){
			validateVal =  plln_validate(sheetname,expectedVal,actualVal,rownum);
		}
		return validateVal;
	}
	
	public static String general_validate(String sheetname, String expectedVal, String actualVal,int rownum) throws Exception{
		
		int expectedVal_Int=Integer.parseInt(expectedVal);
		
		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param_Result",sheetname);
		
		write_excel_data wrResult = new write_excel_data();
		
		for(int filln = 1;filln<rownum;filln++){
			wrResult.writeResult(sheetname,"n",filln,3);
		}
		
		if(expectedVal.contains("-") || expectedVal_Int > 100 ){
			wrResult.writeResult(sheetname,expectedVal,1,3);
			validateVal = "Pass";
		}
		else{
			for(int i =1;i<rownum;i++){
				
				if(data[i][1].contains(expectedVal) && data[i][2].contains(actualVal)){
					//System.out.println("Expected Value :: "+expectedVal);
					//System.out.println("Actual Value :: "+actualVal);
					System.out.println("turbo call Value :: "+expectedVal);
					System.out.println("pubAd call Value :: "+actualVal);
					logStep("Turbo call value ::" +expectedVal);
					logStep("PubAd call value ::" +actualVal);
					System.out.println("Values are matched");
					logStep("Values are matched");
					wrResult.writeResult(sheetname,expectedVal,i,3);
					validateVal = "Pass";
					break;
				}
				if(actualVal.contains("nl")) {
					
					System.out.println("turbo call Value :: "+expectedVal);
					System.out.println("pubAd call Value :: "+actualVal);
					logStep("Turbo call value ::" +expectedVal);
					logStep("PubAd call value ::" +actualVal);
					System.out.println("Values are not matched");
					logStep("Values are not matched");
					wrResult.writeResult(sheetname,expectedVal,i,3);
					validateVal = "Fail";
					break;
				}
}
				/*else{
					System.out.println("turbo call Value :: "+expectedVal);
					System.out.println("pubad call Value :: "+actualVal);
					//System.out.println("Expected Value :: "+expectedVal);
					//System.out.println("Actual Value :: "+actualVal);
					wrResult.writeResult(sheetname,expectedVal,i,3);
					validateVal = "Fail";
					
					break;
				}*/
				
				

			
			
			
			
		}
		
	
		return validateVal;
	}
	
	public static String plln_validate(String sheetname, String expectedVal, String actualVal,int rownum) throws Exception{
		
		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param_Result",sheetname);
		
		write_excel_data wrResult = new write_excel_data();
		
		for(int filln = 1;filln<rownum;filln++){
			wrResult.writeResult(sheetname,"n",filln,3);
		}
		
		if(data[1][1].contains(expectedVal) && data[1][2].contains(actualVal)){
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,1,3);
			validateVal = "Pass";
		}
		else if(data[2][1].contains(expectedVal) && data[2][2].contains(actualVal)){
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,2,3);
			validateVal = "Pass";
		}
		else if(data[3][1].contains(expectedVal) && data[3][2].contains(actualVal)){
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,3,3);
			validateVal = "Pass";
		}
		else if(data[4][1].contains(expectedVal) && data[4][2].contains(actualVal)){
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,4,3);
			validateVal = "Pass";
		}
		else if(data[5][1].contains(expectedVal) && data[5][2].contains(actualVal)){
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,5,3);
			validateVal = "Pass";
		}
		else{
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,5,3);
			validateVal = "Pass";
		}	
		
		return validateVal;
	}
	public static String sev_validate(String sheetname, String expectedVal, String actualVal,int rownum) throws Exception{
		
		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param_Result",sheetname);
		
		write_excel_data wrResult = new write_excel_data();
		
		for(int filln = 1;filln<rownum;filln++){
			wrResult.writeResult(sheetname,"n",filln,4);
		}
		if(data[1][2].contains(expectedVal) && data[1][3].contains(actualVal)){
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,1,4);
			validateVal = "Pass";
		}
		else if(data[2][2].contains(expectedVal) && data[2][3].contains(actualVal)){
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,2,4);
			validateVal = "Pass";
		}
		else if(data[3][2].contains(expectedVal) && data[3][3].contains(actualVal)){
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,3,4);
			validateVal = "Pass";
		}
		else if(data[4][2].contains(expectedVal) && data[4][3].contains(actualVal)){
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,4,4);
			validateVal = "Pass";
		}
		else if(data[5][2].contains(expectedVal) && data[5][3].contains(actualVal)){
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,5,4);
			validateVal = "Pass";
		}
		else if(data[6][2].contains(expectedVal) && data[6][3].contains(actualVal)){
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,6,4);
			validateVal = "Pass";
		}
		else{
			System.out.println("Expected Value :: "+expectedVal);
			System.out.println("Actual Value :: "+actualVal);
			wrResult.writeResult(sheetname,actualVal,7,4);
			validateVal = "Pass";
		}	
		
		return validateVal;
	}
	public static String hmid_validate(String sheetname, String expectedVal, String actualVal,int rownum) throws Exception{
		
		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param_Result",sheetname);
		
		write_excel_data wrResult = new write_excel_data();
		
		for(int filln = 1;filln<rownum;filln++){
			wrResult.writeResult(sheetname,"n",filln,4);
		}
		
		int first_val = Integer.parseInt(data[1][2]);
        int second_val = Integer.parseInt(data[2][2]);
        
        int expected_val = Integer.parseInt(expectedVal);
        
        
        if(expected_val <= first_val){
			
			
			
			if(actualVal.contains("nl") || !data[1][3].contains(actualVal)) {
				System.out.println("Turbo Value ::"+expectedVal);
				System.out.println("PubAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				wrResult.writeResult(sheetname,expectedVal,1,4);
				validateVal = "Fail";
				System.out.println("Values are not matched");
				logStep("Values are not matched");
			}
			if(data[1][3].contains(actualVal))
			{
				System.out.println("Turbo Value ::"+expectedVal);
				System.out.println("PubAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				System.out.println("values are  matched");
				wrResult.writeResult(sheetname,expectedVal,1,4);
				validateVal = "Pass";
				logStep("Values are matched");
			}
			
		}
        else if(expected_val >= second_val){
						
			if(actualVal.contains("nl") || !data[2][3].contains(actualVal)) {
				System.out.println("Turbo Value ::"+expectedVal);
				System.out.println("PubAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				wrResult.writeResult(sheetname,expectedVal,2,4);
				validateVal = "Fail";
				System.out.println("values are not matched");
				logStep("Values are not matched");
				
			}
			if(data[1][3].contains(actualVal))
			{
				System.out.println("Turbo Value ::"+expectedVal);
				System.out.println("PubAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				System.out.println("values are  matched");
				wrResult.writeResult(sheetname,expectedVal,2,4);
				validateVal = "Pass";
				logStep("Values are  matched");
			}
			
			
			//wrResult.writeResult(sheetname,expectedVal,2,4);
		//	validateVal = "Pass";
		}
        else if(expectedVal == ""){
        
        	if(actualVal.contains("nl") || !data[3][3].contains(actualVal)) {
        		System.out.println("Turbo Value ::"+expectedVal);
				System.out.println("PubAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				wrResult.writeResult(sheetname,expectedVal,3,4);
				validateVal = "Fail";
				System.out.println("values are not matched");
				logStep("Values are not matched");
			}
			if(data[1][3].contains(actualVal))
			{
				System.out.println("Turbo Value ::"+expectedVal);
				System.out.println("PubAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				System.out.println("values are  matched");
				wrResult.writeResult(sheetname,expectedVal,3,4);
				validateVal = "Pass";
				logStep("Values are not matched");
			}
        	
        	
        	
        	
        	//System.out.println("Expected Value ::"+expectedVal);
		//	System.out.println("Actual Value ::"+ actualVal);
		//	wrResult.writeResult(sheetname,expectedVal,3,4);
			//validateVal = "Pass";
		}
        else
		{
        	System.out.println("Turbo call Value ::"+expectedVal);
			System.out.println("PubAd call Value ::"+ actualVal);
			logStep("Turbo Value ::"+ expectedVal);			
			logStep("PubAd Value ::"+ actualVal);
			System.out.println("Values are not matched");
			logStep("Values are not matched");
			wrResult.writeResult(sheetname,"No data",3,4);
			validateVal = "Fail";
			
		}
        return validateVal;
	}
	
	public static String wind_validate(String sheetname, String expectedVal, String actualVal,int rownum) throws Exception{
		
		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param_Result",sheetname);
		
		write_excel_data wrResult = new write_excel_data();
		
		for(int filln = 1;filln<rownum;filln++){
			wrResult.writeResult(sheetname,"n",filln,4);
		}
		
		int first_val = Integer.parseInt(data[1][2]);
        int second_val = Integer.parseInt(data[2][2]);
        int third_val = Integer.parseInt(data[3][2]);
        
        int expected_val = Integer.parseInt(expectedVal);        
        if(expected_val < first_val){
			
	     if(data[1][3].contains(actualVal)) {
	    	 System.out.println("Turbo call Value ::"+expectedVal);
				System.out.println("PubAd call Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
	    	 System.out.println("Values are matched");
	    	 logStep("Values are matched");
		wrResult.writeResult(sheetname,expectedVal,1,4);
		validateVal = "Pass";
	}
			
		}
        else if(expected_val >= first_val && expected_val < second_val){
			
			
			if(actualVal.contains("nl")) {
				System.out.println("Turbo call Value ::"+expectedVal);
				System.out.println("PubAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				System.out.println("Values are not matched");
				logStep("Values are not matched");
				validateVal = "Fail";
			}
			
			if(data[2][3].contains(actualVal)) {
			System.out.println("Turbo call Value ::"+expectedVal);
			System.out.println("PubAd Value ::"+ actualVal);
			logStep("Turbo Value ::"+ expectedVal);			
			logStep("PubAd Value ::"+ actualVal);
			wrResult.writeResult(sheetname,expectedVal,2,4);
			System.out.println("Values are matched");
			logStep("Values are matched");
			validateVal = "Passed";
			}
		}
        else if(expected_val >= third_val){
			
			
			if(actualVal.contains("nl")) {
				System.out.println("Turbo call Value ::"+expectedVal);
				System.out.println("PudAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				System.out.println("Values are not matched");
				logStep("Values are not matched");
				validateVal = "Fail";
			}
			
			if(data[3][3].contains(actualVal)) {
				System.out.println("Turbo call Value ::"+expectedVal);
				System.out.println("PubAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				System.out.println("Values are matched");
				logStep("Values are matched");
				wrResult.writeResult(sheetname,expectedVal,3,4);
				validateVal = "Pass";
			}
			
		}
        else if(expectedVal == ""){
			
			System.out.println("Turbo call Value ::"+expectedVal);
			System.out.println("PubAd Value ::"+ actualVal);
			logStep("Turbo Value ::"+ expectedVal);			
			logStep("PubAd Value ::"+ actualVal);
			System.out.println("Values are matched");
			logStep("Values are matched");
			wrResult.writeResult(sheetname,expectedVal,4,4);
			validateVal = "Pass";
		}
        else
		{
        	System.out.println("Turbo call Value ::"+expectedVal);
			System.out.println("PubAd call Value ::"+ actualVal);
			logStep("Turbo Value ::"+ expectedVal);			
			logStep("PubAd Value ::"+ actualVal);
			System.out.println("Values are not matched");
			logStep("Values are not matched");
			wrResult.writeResult(sheetname,"No data",4,4);
			validateVal = "Fail";
			
		}
        return validateVal;
	}
	
	public static String uv_validate(String sheetname, String expectedVal, String actualVal,int rownum) throws Exception{
		
		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param_Result",sheetname);
		
		write_excel_data wrResult = new write_excel_data();
		
		for(int filln = 1;filln<rownum;filln++){
			wrResult.writeResult(sheetname,"n",filln,4);
		}
		
		int first_val = Integer.parseInt(data[1][2]);
        int second_val = Integer.parseInt(data[2][2]);
        
        int expected_val = Integer.parseInt(expectedVal);
        
        
        if(expected_val < first_val || expectedVal == ""){
			
			
		
			if(data[1][3].contains(actualVal)) {
				System.out.println("Turbo call Value ::"+expectedVal);
				System.out.println("PubAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				System.out.println("Values are matched");
				logStep("Values are matched");
				wrResult.writeResult(sheetname,expectedVal,1,4);
				validateVal = "Pass";
			}
			
		}
        else if(expected_val >= second_val){
        	
        	if(actualVal.contains("nl")) {
        		System.out.println("Turbo call Value ::"+expectedVal);
				System.out.println("PubAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				System.out.println("Values are not matched");
				logStep("Values are not matched");
    			wrResult.writeResult(sheetname,expectedVal,2,4);
    			validateVal = "Fail";
        	}
			if(data[1][3].contains(actualVal)) {
				System.out.println("Turbo call Value ::"+expectedVal);
				System.out.println("PubAd Value ::"+ actualVal);
				logStep("Turbo Value ::"+ expectedVal);			
				logStep("PubAd Value ::"+ actualVal);
				System.out.println("Values are matched");
				logStep("Values are matched");
			
			wrResult.writeResult(sheetname,expectedVal,2,4);
			validateVal = "Pass";
			}
		}
        else
		{
        	System.out.println("Turbo call Value ::"+expectedVal);
			System.out.println("PubAd Value ::"+ actualVal);
        	logStep("Turbo Value ::"+ expectedVal);			
			logStep("PubAd Value ::"+ actualVal);
			System.out.println("Values are not matched");
			logStep("Values are not matched");
			wrResult.writeResult(sheetname,"No data",1,4);
			validateVal = "Fail";
			
		}
        return validateVal;
	}
	
	public static String fltmpc_validate(String sheetname, String expectedVal, String actualVal,int rownum) throws Exception{
		
		String[][] data = read_excel_data.exceldataread_Custom_Parameters("Cust_Param_Result",sheetname);
		
		write_excel_data wrResult = new write_excel_data();
		
		for(int filln = 1;filln<rownum;filln++){
			wrResult.writeResult(sheetname,"n",filln,4);
		}
		
		int first_val = Integer.parseInt(data[1][2]);
        int second_val = Integer.parseInt(data[2][2]);
        int thrid_val = Integer.parseInt(data[3][2]);
        
        System.out.println("Turbo call Value ::" + expectedVal);
       logStep("Turbo call Value ::" + expectedVal);
        
        int convert_val = Integer.parseInt(expectedVal);
        //--- Strat Calculation Part ---//
        convert_val = convert_val-32;
       // System.out.println("Converting Turbo call date into double");
		double  convert_val_double = (double) ((convert_val)*(0.5555555556));
		convert_val_double = Math.round(convert_val_double);
		System.out.println("double value is::"+convert_val_double);
		logStep("double value is::"+convert_val_double);
		convert_val=(int) convert_val_double;
		String convert_val_string = Integer.toString(convert_val);
		//--- End Calculation Part ---//
		
        int expected_val = Integer.parseInt(convert_val_string);
        
        if(expected_val < first_val){
        	if(!actualVal.contains("nl")) {
        		System.out.println("Expected Value ::"+convert_val_string);
    			System.out.println("Actual Value ::"+ actualVal);
    			
            	logStep("Expected Value ::"+ convert_val_string);			
    			logStep("PubAd Value ::"+ actualVal);
    			if(convert_val_string.equalsIgnoreCase(actualVal)) {
    				System.out.println("Values are  matched");
        			logStep("Values are  matched");
        			validateVal = "Pass";
        		//	System.out.println("Result "+validateVal);
        			//logStep("Result "+validateVal);
        			
    			}
    			else {
    				System.out.println("Actual Value ::"+ actualVal);
    				logStep("Actual Value ::"+ actualVal);
    				System.out.println("Values are  not  matched");
        			logStep("Values are  not matched");
        			validateVal = "Fail";
    			}
    			
        	}
        	/*if(data[1][3].contains(actualVal)) {
			System.out.println("Expected Value ::"+convert_val_string);
			System.out.println("Actual Value ::"+ actualVal);
			logStep("Expected Value ::"+ convert_val_string);			
			logStep("PubAd Value ::"+ actualVal);
			System.out.println("Values are matched");
			logStep("Values are matched");
			wrResult.writeResult(sheetname,convert_val_string,1,4);
			validateVal = "Pass";
		}*/
        }
        else if(expected_val <= second_val && expected_val >= first_val ){
			
        	if(!actualVal.contains("nl")) {
        		System.out.println("Expected Value ::"+convert_val_string);
    			System.out.println("Actual Value ::"+ actualVal);
    			logStep("Expected Value ::"+ convert_val_string);			
    			logStep("PubAd Value ::"+ actualVal);
    			if(convert_val_string.equalsIgnoreCase(actualVal)) {
    				System.out.println("Values are  matched");
        			logStep("Values are  matched");
        			validateVal = "Pass";
        		//	System.out.println("Result "+validateVal);
        		//	logStep("Result "+validateVal);
    			}
    			else {
    				System.out.println("Values are  matched");
        			logStep("Values are  not matched");
        			validateVal = "Fail";
    			}
        	}
    			else {
    				System.out.println("Expected Value ::"+convert_val_string);
        			System.out.println("Actual Value ::"+ actualVal);
        			logStep("Expected Value ::"+ convert_val_string);			
        			logStep("PubAd Value ::"+ actualVal);
    				System.out.println("Values are  not  matched");
        			logStep("Values are  not matched");
        			validateVal = "Fail";
    			}
    			
    			
        	}
        /*	if(data[2][3].contains(actualVal)) {
        		System.out.println("Expected Value ::"+convert_val_string);
    			System.out.println("Actual Value ::"+ actualVal);
    			logStep("Expected Value ::"+ convert_val_string);			
    			logStep("PubAd Value ::"+ actualVal);
    			System.out.println("Values are matched");
    			logStep("Values are matched");
    			wrResult.writeResult(sheetname,convert_val_string,2,4);
    			validateVal = "Pass";
        	}*/
			
		
        else if(expected_val >= thrid_val){
        	if(!actualVal.contains("nl")) {
        		System.out.println("Expected Value ::"+convert_val_string);
    			System.out.println("Actual Value ::"+ actualVal);
    			logStep("Expected Value ::"+ convert_val_string);			
    			logStep("PubAd Value ::"+ actualVal);
    			if(convert_val_string.equalsIgnoreCase(actualVal)) {
    				System.out.println("Values are  matched");
        			logStep("Values are  matched");
        			validateVal = "Pass";
        		//	System.out.println("Result "+validateVal);
        			//logStep("Result "+validateVal);
    			}
    			else {
    				System.out.println("Values are  matched");
        			logStep("Values are  not matched");
        			validateVal = "Fail";
    			}
        	}
    			else {
    				System.out.println("Expected Value ::"+convert_val_string);
        			System.out.println("Actual Value ::"+ actualVal);
        			logStep("Expected Value ::"+ convert_val_string);			
        			logStep("PubAd Value ::"+ actualVal);
    				System.out.println("Values are  not  matched");
        			logStep("Values are  not matched");
        			validateVal = "Fail";
    			
        	}
        /*	if(data[3][3].contains(actualVal)) {
			System.out.println("Expected Value ::"+convert_val_string);
			System.out.println("Actual Value ::"+ actualVal);
			logStep("Expected Value ::"+ convert_val_string);			
			logStep("PubAd Value ::"+ actualVal);
			System.out.println("Values are matched");
			logStep("Values are matched");
			wrResult.writeResult(sheetname,convert_val_string,3,4);
			validateVal = "Pass";
        	}*/
		}
        else
		{
        	System.out.println("Expected Value ::"+convert_val_string);
			System.out.println("Actual Value ::"+ actualVal);
			logStep("Expected Value ::"+ convert_val_string);			
			logStep("PubAd Value ::"+ actualVal);
			System.out.println("Values are not matched");
			logStep("Values are not matched");
			wrResult.writeResult(sheetname,"No data",3,4);
			validateVal = "Fail";
			//System.out.println("Result "+validateVal);
		//	logStep("Result "+validateVal);
			
		}
        return validateVal;
	}
}