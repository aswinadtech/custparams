package twc.Regression.TestCases;

import java.io.File;

import org.openqa.selenium.internal.Killable;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import twc.Regression.HandleWithCharles.CharlesProxy;

//import twc.Automation.HandleMapLocal.MapLocalFunctions;
import twc.Regression.CustomParamValidation.CustomParamFunctions;
import twc.Regression.CustomParamValidation.validate_CustomParameter;
import twc.Regression.Driver.Drivers;
import twc.Regression.General.Functions;
import twc.Regression.General.TwcAndroidBaseTest;
import twc.Regression.HandleWithAppium.AppiumFunctions;
import twc.Regression.HandleWithCharles.CharlesFunctions;
import twc.Regression.ReadDataFromFile.read_excel_data;
import twc.Regression.utils.DeleteFiles;

public class regressionTestCases extends TwcAndroidBaseTest {
	
	public static String CurrentWifiName=null;
	
	private static final String CONFIG_FILE_PATH = "charles_common.config";

	private static final String BN_SEVERE1_CONFIG_FILE_PATH = "BNSevere1charles_common.config";
	private static final String BN_SEVERE2_CONFIG_FILE_PATH = "BNSevere2charles_common.config";
	private static final String CRITEO_CONFIG_FILE_PATH = "Criteocharles_common.config";
	private static final String CRITEO_NONUS_CONFIG_FILE_PATH = "CriteoNonUScharles_common.config";
	// public static CharlesProxy proxy;
	public File configFile;
	private CharlesProxy proxy;



	// Not null
		@Test(priority = 1019)
		@Title("Verify custom parameter adid")
		public void C333213_Verify_cust_param_adid() throws Exception {
			
			
			System.out.println("================= Custom Parameter adid Verfication Started =========================");
			
		
			Custom_Parameters_Verification.parameters_Verification("adid");
			System.out.println("================= Custom Parameter adid Verfication End =========================");
		}

		@Test(priority = 1021)
		@Title("Verify custom parameter aid")
		public void C333213_Verify_cust_param_aid() throws Exception {
			System.out.println("================= Custom Parameter Aid Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("aid");
			System.out.println("================= Custom Parameter Aid Verfication End =========================");
		}
		
		@Test(priority = 1023)
		@Title("Verify custom parameter ltv")
		public void C333213_Verify_cust_param_ltv() throws Exception {
			System.out.println("================= Custom Parameter adid Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("ltv");
			System.out.println("================= Custom Parameter adid Verfication End =========================");
		}
		/*@Test(priority = 22)
		@Title("Verify custom parameter env")
		public void C333244_Verify_cust_param_env() throws Exception {
			System.out.println("================= Custom Parameter env Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("env");
			System.out.println("================= Custom Parameter env Verfication End =========================");
		}*/


		/*@Test(priority = 23)
		@Title("Verify custom parameter st")
		public void C333219_Verify_cust_param_st() throws Exception {
			System.out.println("================= Custom Parameter St Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("st");
			System.out.println("================= Custom Parameter St Verfication End =========================");
		}*/

		@Test(priority = 1026)
		@Title("Verify custom parameter ord")
		public void C333200_Verify_cust_param_ord() throws Exception {
			System.out.println("================= Custom Parameter ORD Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("ord");
			System.out.println("================= Custom Parameter ORD Verfication End =========================");
		}

		/*@Test(priority = 26)
		@Title("Verify custom parameter rmid")
		public void C333204_Verify_cust_param_rmid() throws Exception {
			System.out.println("================= Custom Parameter Rmid Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("rmid");
			System.out.println("================= Custom Parameter Rmid Verfication End =========================");
		}*/

	@Test(priority = 1029)
		@Title("Verify custom parameter ver")
		public void C333219_Verify_cust_param_ver() throws Exception {
			System.out.println("================= Custom Parameter Ver Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("ver");
			System.out.println("================= Custom Parameter Ver Verfication End =========================");
		}
		

		 /* @Test(priority=29)  
		  @Title("Verify custom parameter ftl") 
		  public void C333215_Verify_cust_param_ftl() throws Exception{ 
			  System.out.println("================= Custom Parameter Ftl Verfication Started =========================" );
		  Custom_Parameters_Verification.parameters_Verification("ftl"); 
		  System.out. println("================= Custom Parameter Ftl Verfication End =========================");  
		  }
		  @Test(priority=30)
		  @Title("Verify custom parameter g") 
		  public void C333236_Verify_cust_param_g()  throws Exception{ 
		 System.out. println("================= Custom Parameter G Verfication Started =========================");
		 Custom_Parameters_Verification.parameters_Verification("g"); 
		 System.out.println("================= Custom Parameter G Verfication End =========================" );
		 }*/


		
		@Test(priority = 1031)
		@Title("Verify custom parameter tf")
		public void C658716_Verify_cust_param_tf() throws Exception {
			System.out.println("================= Custom Parameter Tf Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("tf");
			System.out.println("================= Custom Parameter Tf Verfication End =========================");
		}



		
		
	//Hard code values

		@Test(priority = 1033)
		@Title("Verify custom parameter lang")
		public void C658711_Verify_cust_param_lang() throws Exception {
			System.out.println("================= Custom Parameter Lang Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("lang");
			System.out.println("================= Custom Parameter Lang Verfication End =========================");
		}

		@Test(priority = 1036)
		@Title("Verify custom parameter plat")
		public void C658712_Verify_cust_param_plat() throws Exception {
			System.out.println("================= Custom Parameter Plat Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("plat");
			System.out.println("================= Custom Parameter Plat Verfication End =========================");
		}

		@Test(priority = 1039)
		@Title("Verify custom parameter pos")
		public void C333218_Verify_cust_param_pos() throws Exception {
			System.out.println("================= Custom Parameter Pos Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("pos");
			System.out.println("================= Custom Parameter Pos Verfication End =========================");
		}

		@Test(priority = 1041)
		@Title("Verify custom parameter tile")
		public void C333205_Verify_cust_param_tile() throws Exception {
			System.out.println("================= Custom Parameter Tile Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("tile");
			System.out.println("================= Custom Parameter Tile Verfication End =========================");
		}

		
		
		
		
		

		// turbo call values
		@Test(priority = 1042)
		@Title("Verify custom parameter cnd")
		public void C333216_Verify_cust_param_cnd() throws Exception {
			System.out.println("================= Custom Parameter Cnd Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("cnd");

			System.out.println("================= Custom Parameter Cnd Verfication End =========================");
		}

		@Test(priority = 1043)
		@Title("Verify custom parameter ct")
		public void C333212_Verify_cust_param_ct() throws Exception {
			System.out.println("================= Custom Parameter Ct Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("ct");
			System.out.println("================= Custom Parameter Ct Verfication End =========================");
		}

		@Test(priority = 1046)
		@Title("Verify custom parameter dma")
		public void C333203_Verify_cust_param_dma() throws Exception {
			System.out.println("================= Custom Parameter Dma Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("dma");
			System.out.println("================= Custom Parameter Dma Verfication End =========================");
		}

		@Test(priority = 1049)
		@Title("Verify custom parameter dynght")
		public void C628160_Verify_Parameter_DyNght() throws Exception {
			System.out.println("================= Custom Parameter DayNight Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("dynght");
			System.out.println("================= Custom Parameter DayNight Verfication End =========================");
		}

		@Test(priority = 1051)
		@Title("Verify custom parameter cc")
		public void C333209_Verify_cust_param_cc() throws Exception {
			System.out.println("================= Custom Parameter Cc Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("cc");
			System.out.println("================= Custom Parameter Cc Verfication End =========================");
		}

		/*@Test(priority = 65)
		@Title("Verify custom parameter fhic")
		public void C333232_Verify_cust_param_fhic() throws Exception {
			System.out.println("================= Custom Parameter Fhic Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("fhic");
			System.out.println("================= Custom Parameter Fhic Verfication End =========================");
		}*/

		/*@Test(priority = 66)
		@Title("Verify custom parameter floc")
		public void C333238_Verify_cust_param_floc() throws Exception {
			System.out.println("================= Custom Parameter Floc Verfication Started =========================");
		//	Custom_Parameters_Verification.parameters_Verification("floc");
			System.out.println("================= Custom Parameter Floc Verfication End =========================");
		}*/

		@Test(priority = 1054)
		@Title("Verify custom parameter tmp")
		public void C333208_Verify_cust_param_tmp() throws Exception {
			System.out.println("================= Custom Parameter Tmp Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("tmp");
			System.out.println("================= Custom Parameter Tmp Verfication End =========================");
		}

		@Test(priority = 1057)
		@Title("Verify custom parameter tmpr")
		public void C333217_Verify_cust_param_tmpr() throws Exception {
			System.out.println("================= Custom Parameter Tmpr Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("tmpr");
			System.out.println("================= Custom Parameter Tmpr Verfication End =========================");
		}

		@Test(priority = 1059)
		@Title("Verify custom parameter tmpc")
		public void C333239_Verify_cust_param_tmpc() throws Exception {
			System.out.println("================= Custom Parameter Tmpc Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("tmpc");
			System.out.println("================= Custom Parameter Tmpc Verfication End =========================");
		}

		@Test(priority = 1061)
		@Title("Verify custom parameter zip from  turbo api")
		public void C333202_Verify_cust_param_zip() throws Exception {
			System.out.println(
					"================= Custom Parameter Zip Verfication  from turbo call Started =========================");
			Custom_Parameters_Verification.parameters_Verification("zip");

			System.out.println(
					"================= Custom Parameter Zip Verfication  from turbo call End =========================");
		}
		
		/*@Test(priority=73) 
		  @Title("Verify custom parameter hmid") 
	public void  C333207_Verify_cust_param_hmid() throws Exception{ 
		  System.out.println("================= Custom Parameter Hmid Verfication Started =========================");
		  Custom_Parameters_Verification.parameters_Verification("hmid"); 
		  System.out.println("================= Custom Parameters Hmid Verfication End =========================" );
		  }*/

		@Test(priority = 1063)
		@Title("Verify custom parameter wind")
		public void C333221_Verify_cust_param_wind() throws Exception {
			System.out.println("================= Custom Parameter Wind Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("wind");
			System.out.println("================= Custom Parameter Wind Verfication End =========================");
		}

		@Test(priority = 1065)
		@Title("Verify custom parameter uv")
		public void C333224_Verify_cust_param_uv() throws Exception {
			System.out.println("================= Custom Parameter Uv Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("uv");
			System.out.println("================= Custom Parameter Uv Verfication End =========================");
		}

		@Test(priority = 1069)
		@Title("Verify custom parameter fltmpc")
		public void C333232_Verify_cust_param_fltmpc() throws Exception {
			System.out.println("================= Custom Parameter fltmpc Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("fltmpc");
			System.out.println("================= Custom Parameter fltmpc Verfication End =========================");
		}

	
		
		
		
		/*@Test(priority = 1073)
		@Title("Verify custom parameter wfxtg")
		public void C333228_Verify_cust_param_wfxtg() throws Exception {
			System.out.println("================= Custom Parameter Wfxtg Verfication Started =========================");
			Custom_Parameters_Verification.parameters_Verification("wfxtg");
			System.out.println("================= Custom Parameter Wfxtg Verfication End =========================");
			
		}*/
	
	
		  @Test(priority =1076, enabled = true) 
	  @Title("Verifying IM Cust param value for home screen marquee ad all" )
	  public void Smoke_Test_Verifying_IM_Cust_Param_homescreen_marquee_adCall() throws Exception {
		 System.out.println("================= Verifying IM CUST_PARAM value for home screen marquee call  started =========================");
		 Functions.validate_IM_Cust_param_homescreenmarquee();
		 System.out.println("================= Verifying SOD CUST_PARAM value for home screen IM call  End =========================");
		 }
	  
	  
	  @Test(priority = 1079, enabled = true) 
	  @Title("Verifying SlotName Cust param value for home screen marquee ad all" )
	  public void Smoke_Test_Verifying_SlotName_Cust_Param_homescreen_marquee_adCall() throws Exception {
		 System.out.println("================= Verifying SlotName CUST_PARAM value for home screen marquee call  started =========================");
		 Functions.validate_SlotName_Cust_param_homescreenmarquee();
		 System.out.println("================= Verifying SlotName  CUST_PARAM value for home screen IM call  End =========================");
		 }
	  
	  @Test(priority = 1081, enabled = true) 
	  @Title("Verifying SlotName Cust param value for home screen hourly ad all" )
	  public void Smoke_Test_Verifying_SlotName_Cust_Param_homescreen_hourly_adCall() throws Exception {
		 System.out.println("================= Verifying SlotName CUST_PARAM value for home screen hourly call  started =========================");
		 Functions.validate_SlotName_Cust_param_homescreenhourly();
		 System.out.println("================= Verifying SlotName  CUST_PARAM value for home screen hourly call  End =========================");
		 }
	
	  @Test(priority = 1085, enabled = true) 
	  @Title("Verifying SlotName Cust param value for feed_1 ad all" )
	  public void Smoke_Test_Verifying_SlotName_Cust_Param_feed_1_adCall() throws Exception {
		 System.out.println("================= Verifying SlotName CUST_PARAM value for feed_1 ad call  started =========================");
		 Functions.validate_SlotName_Cust_param_feed1();
		  
		 System.out.println("================= Verifying SlotName  CUST_PARAM value for feed_1 ad  call  End =========================");
		 }
	
	
	
	
	
	

	
	
	
	
	
	

}
