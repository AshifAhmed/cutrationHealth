package pages;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.BasePage;



public class SaucedemoPage extends BasePage{
	private static WebDriver driver = null;
	private String userName = "//input[@id='user-name']";
	private String password = "//input[@id='password']";
	private String loginButton = "//input[@type='submit']";
	private String addToCart = "//button[@class='btn btn_primary btn_small btn_inventory']";
	private String item_name = "//div[@class='inventory_item_name']";
	private String cartLink = "//a[@class='shopping_cart_link']";
	private String cartItemsName = "//div[@class='inventory_item_name']";
	private String removeFromCart = "//button[@class='btn btn_secondary btn_small cart_button']";
	private String contShopButton = "//button[@class='btn btn_secondary back btn_medium']";
	private String items = "//div[@class='inventory_item_name']";
	private String checkOutButton = "//button[@class='btn btn_action btn_medium checkout_button']";
	private String continueButton = "//input[@class='submit-button btn btn_primary cart_button btn_action']";
	private String itemInCheckOut = "//div[@class='inventory_item_name']";
	private String itemPriceInCheckOut = "//div[@class='inventory_item_price']";
	private String subTotalInCheckOut = "//div[@class='summary_subtotal_label']";
	private String finishCheckOut = "//button[@class='btn btn_action btn_medium cart_button']";
	private String productLabel = "//span[@class='title']"; 
	private String sortList = "//select[@class='product_sort_container']";
	private static List<String> inventoryName = new ArrayList<>();

	public SaucedemoPage(WebDriver driver) {
		SaucedemoPage.driver = driver;
	}
	

	

	public void user_logs_in_with_username_and_password() {
	    
	    enterDataIntoTextField(driver, "xpath", userName, "Username","standard_user");
	    enterDataIntoTextField(driver, "xpath", password, "Password", "secret_sauce");
	    clickOnButton(driver, "xpath",loginButton, "Login");
	}


	public void user_should_be_able_to_view_inventory_page() {
		  
	    if(getText(driver, "xpath", productLabel, "Item").equalsIgnoreCase("Products")) {
	    	System.out.println("User is able to view Inventory Page");
	    }
	}


	public void user_sorted_items() {
		
	    selectDropDown(driver, sortList);
	}


	public void user_added_items_to_the_cart() throws Exception {
			
		List<WebElement> item_nameList = getElements(driver, item_name);
		List<WebElement> addToCartButtonList = getElements(driver, addToCart);
		System.out.println(item_nameList.size());
		if(item_nameList.size() >= 2) {
			for(int i=0; i<2; i++) {
				addToCartButtonList.get(i).click();
				String itemName = item_nameList.get(i).getText();
				System.out.println("Item name >> "+itemName);
				inventoryName.add(itemName);
			}
		}else {
			throw new Exception("There is no enough inventory");
		}
		
	    
	}


	public void user_verifies_if_the_items_are_added_to_cart() throws Exception {
	
		clickOnButton(driver, "xpath", cartLink, "Cart");
		List<WebElement> cartItemList = getElements(driver, cartItemsName);
	    System.out.println(cartItemList.size());
	    System.out.println(inventoryName.size());
	    if(cartItemList.size() == inventoryName.size()) {
	    	boolean itemMatched = false;
	    	
	    	for(int i=0; i<cartItemList.size(); i++) {
	    		String itemName = cartItemList.get(i).getText();
	    		System.out.println("item Name >> "+itemName);
	    		if(inventoryName.contains(itemName)) {
	    			itemMatched = true;
	    		}else {
	    			itemMatched = false;
	    		}
	    	}
	    	if(itemMatched == true) {
	    		
	    		System.out.println("Shopping cart items matched with added items");
	    		System.out.println("Removing one Item from the cart ..");
	    		
	    		clickOnButton(driver, "xpath", removeFromCart, "REMOVE");  		
	    		inventoryName.remove(0);
	    	}else {
	    		throw new Exception("Shopping cart did not match the items with added items");
	    	}
	    }else {
	    	throw new Exception("Shopping cart does not match the items count with added items");
	    }
	    
	}



	public void user_click_on_continue_shopping() {

	    clickOnButton(driver, "xpath", contShopButton, "Continue Shopping");
	}


	public void user_added_another_item() {
		
		clickOnButton(driver, "xpath", addToCart, "ADD TO CART");
		System.out.println("Added another item to the cart");
		clickOnButton(driver, "xpath", cartLink, "Cart");
		List<WebElement> invItems = getElements(driver, items);
		inventoryName.add(invItems.get(invItems.size()-1).getText());		
	}


	public void user_checkout_items() {
		
	   clickOnButton(driver, "xpath", checkOutButton, "CHECKOUT");
	   enterDataIntoTextField(driver, "id", "first-name", "first-name","Test");
	   enterDataIntoTextField(driver, "id", "last-name", "last-name","Tester");
	   enterDataIntoTextField(driver, "id", "postal-code", "postal-code","08854");
	   clickOnButton(driver, "xpath", continueButton, "CONTIUNE");	   
	}


	public void user_verifies_if_purchasing_correct_items_and_total_price() throws Exception {
	    
	    List<WebElement> checkOutItems = getElements(driver, itemInCheckOut);
	    List<WebElement> checkOutItemPrice = getElements(driver, itemPriceInCheckOut);
	    String subTotal = getText(driver, "xpath", subTotalInCheckOut, "SubTotal");
	    String subTot = subTotal.split(":")[1].substring(1);
	    NumberFormat format = NumberFormat.getCurrencyInstance();
	    Number number = format.parse(subTot);
	    
	    if(checkOutItems.size() == inventoryName.size()) {
	    	 for(int i=0; i<checkOutItems.size(); i++) {
	 	    	if(!inventoryName.contains(checkOutItems.get(i).getText())) {
	 	    		throw new Exception("Checkout item did not match with cart items");
	 	    	}	 	    	
	 	    }
	    }else {
	    	throw new Exception("Checkout item count did not match with cart items");
	    }
	    
	    
	    Double indvTotal = 0.0;
	    for(WebElement ele : checkOutItemPrice) {
	    	double price = Double.parseDouble(ele.getText().substring(1));
	    	indvTotal += price;
	    }
	    
	    System.out.println(subTotal);
	    
	    if(indvTotal == Double.parseDouble(number.toString())) {
	    	System.out.println("Total price matched !!");
	    }else {
	    	throw new Exception("Total price did not match");
	    }  	    	        
	}

	public void finish_checkout() {

	   clickOnButton(driver, "xpath", finishCheckOut, "FINISH");
	}
	
}
