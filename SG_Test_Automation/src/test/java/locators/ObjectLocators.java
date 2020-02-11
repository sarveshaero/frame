package locators;

public interface ObjectLocators {
	String obj_UserName_Edit = "//input[@id='username']";
	String obj_Password_Edit = "//input[@name='pwd']";
	String obj_Login_Link = "//a[@id='loginButton']";
	String obj_ShortCut_Wnd = "//div[@style='display: block";
	String obj_ShortCut_Close_Btn = "//div[@id='gettingStartedShortcutsMenuCloseId']";
	String obj_TimeTrack_Page = "//td[@class='pagetitle']";
	String obj_Logout_Link = "//a[@id='logoutLink']";
	String obj_Users_Menu = "//div[text()='USERS']";
	String obj_AddUsers_Btn = "//div[text()='Add User']";
	String obj_FirstName_Edit = "//input[@name='firstName']";
	String obj_LastName_Edit = "//input[@name='lastName']";
	String obj_Email_Edit = "//input[@name='email']";
	String obj_User_UN_Edit = "//input[@name='username']";
	String obj_User_Pwd_Edit = "//input[@name='password']";
	String obj_User_RetypePwd_Edit = "//input[@name='passwordCopy']";
	String obj_Header_Label = "//td[@id='headerContainer']";
	String obj_CreateUser_Btn = "//span[text()='Create User']";
	String obj_DeleteUser_Btn = "//button[contains(text(),'Delete User')]";
}