package pageUIs;

public class MyAcccountPageUI {
	
	public enum Left_Menu_Xpaths{
		CUSTOMER_INFO("Customer Infor"), 
		ADDRESSES("Addresses"), 
		ORDERS("Orders"), 
		DOWNLOADABLE_PRODUCTS("Downloadable products"), 
		BACK_IN_STOCK_SUBSCRIPTIONS("Back in stock subscriptions"), 
		REWARD_POINTS("Reward points"), 
		CHANGE_PASSWORD("Change password"), 
		MY_PRODUCT_REVIEWS("My product reviews");
		
		private final String pageName;
		
		Left_Menu_Xpaths(String pageName){
			this.pageName = pageName;
		}
		
		public String getPageName() {
			return this.pageName;
		}

		public String getPageNameXpath() {
			return String.format("//div[contains(@class,'account-navigation')]//a[text()='%s']", pageName);
		}
	}
}
