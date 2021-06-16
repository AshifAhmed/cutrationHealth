package pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;


public class JsonPlaceHolderPage 
{
	public static Map<String,String> data = new HashMap<String, String>();
    
    public static HttpURLConnection getConnection(String uri, String method) throws IOException {
    	URL reqUri = new URL(uri);
    	HttpURLConnection connection =  (HttpURLConnection) reqUri.openConnection();
    	connection.setRequestMethod(method);
    	connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    	return connection;
    }
    
    public static void testGETMethod() throws IOException {
    	HttpURLConnection connection = getConnection("https://jsonplaceholder.typicode.com/posts/1", "GET");
    	connection.setRequestProperty("userId", "1");
		int responseCode = connection.getResponseCode();
		System.out.println("===== GET METHOD =====");
		System.out.println(responseCode);
		boolean status = validateResponse(responseCode);
		String resp = "";
		if(status == true) {
			resp = getResponseBody(connection);
		}
		connection.disconnect();
		
		JSONObject obj = new JSONObject(resp);
		data.put("title", obj.get("title").toString());

		Assert.assertEquals(obj.getInt("id"), 1);
		System.out.println("Assertion successful");
    }
    
    public static void testPOSTMethod(String title) throws IOException {
    	
    	HttpURLConnection connection = getConnection("https://jsonplaceholder.typicode.com/posts", "POST");
    	JSONObject parameters = new JSONObject();
    	parameters.put("userId", 101);
    	parameters.put("id", 101);
    	if(title.isEmpty()) {
    		parameters.put("title", "This is POST test");
    	}else {
    		parameters.put("title",title);
    	}
    	
    	parameters.put("body", "This is POST method body");
		
		connection.setDoOutput(true);
		OutputStream os = connection.getOutputStream();
	    os.write(parameters.toString().getBytes());
	    os.flush();
	    os.close();
	    
		int responseCode = connection.getResponseCode();
		System.out.println("===== POST METHOD =====");
		System.out.println(responseCode);
		boolean status = validateResponse(responseCode);
		if(status == true) {
			getResponseBody(connection);
		}
		connection.disconnect();
    }
    
    public static void testPUTMethod() throws IOException {
    	
    	HttpURLConnection connection = getConnection("https://jsonplaceholder.typicode.com/posts/1", "PUT");
    	JSONObject parameters = new JSONObject();
    	parameters.put("userId", 1);
    	parameters.put("id", 1);
    	parameters.put("title", "This is PUT test");
    	parameters.put("body", "This is PUT method body");
    	
    	connection.setDoOutput(true);
		OutputStream os = connection.getOutputStream();
	    os.write(parameters.toString().getBytes());
	    os.flush();
	    os.close();
	    
		int responseCode = connection.getResponseCode();
		System.out.println("===== PUT METHOD =====");
		System.out.println(responseCode);
		boolean status = validateResponse(responseCode);
		if(status == true) {
			getResponseBody(connection);
		}
		connection.disconnect();
    }
    
    public static void testDELETEMethod(String userId) throws IOException {

    	HttpURLConnection connection = getConnection("https://jsonplaceholder.typicode.com/posts/"+userId, "DELETE");
		int responseCode = connection.getResponseCode();
		System.out.println("===== DELETE METHOD =====");
		System.out.println(responseCode);
		boolean status = validateResponse(responseCode);
		if(status == true) {
			getResponseBody(connection);
		}
		connection.disconnect();
    }
    
    public static boolean validateResponse(int responseCode) {
		boolean status = false;
		if(responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
			status = true;
		}else {
			status = false;
			System.out.println("API is not working properly - Status Code : "+responseCode);
		}
		
		return status;
	}
    
    public static String getResponseBody(HttpURLConnection connection) throws IOException {
		String readLine = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    StringBuffer response = new StringBuffer();
        while ((readLine = br .readLine()) != null) {
            response.append(readLine);
        } 
        br .close();

        System.out.println("Response :\n" + response.toString());
        return response.toString();
	}
}