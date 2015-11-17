package org.telebot.request;

import java.io.File;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

public class TelegramRequest {
	private RequestType requestType;
	private List<BasicNameValuePair> parameters;
	private File file;
	private String fileType;

	public TelegramRequest(final RequestType requestType, final List<BasicNameValuePair> parameters){
		this(requestType,parameters,null,null);
	}
	
	public TelegramRequest(final RequestType requestType, final List<BasicNameValuePair> parameters, final File file, final String fileType){
		this.requestType = requestType;
		this.parameters = parameters;
		this.file = file;
		this.fileType = fileType;
	}	
	public RequestType getRequestType() {
		return requestType;
	}	
	public List<BasicNameValuePair> getParameters() {

		return parameters;
	}
	public File getFile(){
		return file;
	}
	public String getFileType() {
		return fileType;
	}
}
