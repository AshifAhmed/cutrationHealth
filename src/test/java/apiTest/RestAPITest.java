package apiTest;

import org.testng.annotations.Test;

import pages.JsonPlaceHolderPage;

import java.io.IOException;




public class RestAPITest extends JsonPlaceHolderPage
{
	@Test
    public void executeRestAPI() throws IOException
    {
       testGETMethod();
       testPOSTMethod(data.get("title"));
       testPUTMethod();
       testDELETEMethod("1");
    }
    
}
