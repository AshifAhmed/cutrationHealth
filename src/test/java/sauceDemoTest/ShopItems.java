package sauceDemoTest;

import org.testng.annotations.Test;
import utility.DriverClass;


public class ShopItems extends DriverClass{
	
	@Test
	public void shopItemFromSaucedemo() throws Exception {
		
		saucedemoPage().user_logs_in_with_username_and_password();
		saucedemoPage().user_should_be_able_to_view_inventory_page();
		saucedemoPage().user_sorted_items();
		saucedemoPage().user_added_items_to_the_cart();
		saucedemoPage().user_verifies_if_the_items_are_added_to_cart();
		saucedemoPage().user_click_on_continue_shopping();
		saucedemoPage().user_added_another_item();
		saucedemoPage().user_checkout_items();
		saucedemoPage().user_verifies_if_purchasing_correct_items_and_total_price();
		saucedemoPage().finish_checkout();
		
	}

}
