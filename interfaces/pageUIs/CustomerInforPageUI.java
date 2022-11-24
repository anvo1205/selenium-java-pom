package pageUIs;

public class CustomerInforPageUI {
	public static final String FIRST_NAME_TEXTBOX = "//input[@id='FirstName']";
	public static final String LAST_NAME_TEXTBOX = "//input[@id='LastName']";
	public static final String DOB_DAY_DROPDOWN = "//select[@name='DateOfBirthDay']";
	public static final String DOB_MONTH_DROPDOWN = "//select[@name='DateOfBirthMonth']";
	public static final String DOB_YEAR_DROPDOWN = "//select[@name='DateOfBirthYear']";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String COMPANY_NAME_TEXTBOX = "//input[@id='Company']";
	public static final String SAVE_BUTTON = "//button[@id='save-info-button']";
	
	public static String getGenderXpath(String gender) {
		return "//input[@id='gender-" + gender.toLowerCase() + "']";
	}
}
