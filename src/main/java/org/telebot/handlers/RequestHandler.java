package org.telebot.handlers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.telebot.config.BotConfig;
import org.telebot.nixtabyte.jtelebot.response.json.TelegramResponse;
import org.telebot.request.TelegramRequest;


public class RequestHandler {
	
    private TelegramResponse<?> response = null;
	private HttpURLConnection connection = null;  
	private URL url;    
    
	public RequestHandler(){};
	public RequestHandler(TelegramRequest request){
		executeRequest(request);
	}
	
	public TelegramResponse<?> executeRequest(TelegramRequest request){
		
		String urlString = MessageFormat.format(BotConfig.getUrlTemplate(), BotConfig.getToken(), request.getRequestType().getMethodName());
		String parametersString = "";
		List<BasicNameValuePair> parameters =  request.getParameters();
		Iterator<BasicNameValuePair> iterator = parameters.iterator();
		while (iterator.hasNext()){
			BasicNameValuePair nextPair = iterator.next();
			parametersString = parametersString + nextPair.getName() + "=" + nextPair.getValue() + "&";
		}
		try {
			 //Create connection
		      url = new URL(urlString);
		      connection = (HttpURLConnection)url.openConnection();
		      connection.setRequestMethod("POST");
		      connection.setRequestProperty("Content-Type", 
		           "application/x-www-form-urlencoded");					
		      connection.setRequestProperty("Content-Length", "" + 
		               Integer.toString(parametersString.getBytes().length));
		      connection.setRequestProperty("Content-Language", "en-US");					
		      connection.setUseCaches (false);
		      connection.setDoInput(true);
		      connection.setDoOutput(true);

		      //Send request
		      DataOutputStream wr = new DataOutputStream (
		                  connection.getOutputStream ());
		      wr.writeBytes (parametersString);
		      wr.flush ();
		      wr.close ();
			
		      //Get Response	
		      InputStream is = connection.getInputStream();
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		      String line;
		      StringBuilder responseStringBuilder = new StringBuilder(); 
		      while((line = rd.readLine()) != null) {
		    	  responseStringBuilder.append(line);
		    	  responseStringBuilder.append('\r');
		      }
		      rd.close();		      
		      
		      ObjectMapper mapper = new ObjectMapper();
		      response = mapper.readValue(responseStringBuilder.toString(), TelegramResponse.class);
		      
		      return response;
		      
		} catch (Exception e) {
		      e.printStackTrace();
		      return null;
		} finally {
			 if(connection != null) {
			        connection.disconnect();
		}	
	}
}
}
