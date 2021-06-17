# cutrationHealth

***SET UP***
- jdk_1.8
- Required dependecy added to pom.xml
- libs/chromedriver (Mac)

# How to Run :

# API Testing :
- Run /apiTestTestng.xml as TestNG Suite

**Files:**  
     /apiTest/RestAPITest.java  
     /pages/JsonPlaceHolderPage.java


# Web Testing :

**Files:**  
     /utility/BasePage.java  
   
     
**Solution  :** Selenium + TestNG (Covers Head and Headless browsers, Parallel run)
- Run /sauceDemoTest.xml as TestNG Suite (Chrome browser)
- Run /sauceDemoTestHeadles.xml as TestNG Suite (Headless Chrome browser)
- Run /sauceDemoTestParallel.xml (Parallel run)

**Files:**  
    /utility/DriverClass.java  
    /utility/BasePage.java  
    /pages/SaucedemoPage.java  
    /saucedemoTest/ShopItems.java
