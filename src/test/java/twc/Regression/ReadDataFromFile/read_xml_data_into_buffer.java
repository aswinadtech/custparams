package twc.Regression.ReadDataFromFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import org.testng.Assert;


import twc.Regression.Driver.Drivers;
import twc.Regression.General.DeviceStatus;
import twc.Regression.General.Functions;


public class read_xml_data_into_buffer extends Drivers{

	@SuppressWarnings("null")
	public StringBuffer read_xml_file_into_buffer_string(String XmlType) throws Exception{

		DeviceStatus device_status = new DeviceStatus();
		int Cap = device_status.Device_Status();
		sb=sb.delete(0, sb.length());
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

		
		int count=0;
		try {
			File xmlFile = new File(xml_file_path); 
			Reader fileReader = new FileReader(xmlFile); 
			BufferedReader bufReader = new BufferedReader(fileReader); 
			 
			String line = bufReader.readLine(); 
			if(XmlType.equals("aax")) {
				while((line=bufReader.readLine()) != null)
				{
					if(line.contains("\"slotId\":1,")) {
						//System.out.println("aax ad unit call found");
						//logStep("aax ad unit call found "+ line.toString());
						count=count+1;
						Functions.aaxSlots.add(line.toString());

					}

				}
				if(count==0) {
					System.out.println("aax ad unit calls not found"+ count);
					//logStep("aax ad unit calls not found"+ count);
					Assert.fail("aax ad unit calls not found"+ count);
				}else {
					System.out.println("Total AdUnit calls are : "+ count);
					//logStep("Total AdUnit calls are :"+ count);
				}
			}else {

				while( (line=bufReader.readLine()) != null)
				{ 
					
					sb.append(line).append("\n"); 
				} 
				bufReader.close();
			}
		} catch (Exception e) {
			System.out.println("No Data Found in XML File");
		}
		return sb;

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
		
}
