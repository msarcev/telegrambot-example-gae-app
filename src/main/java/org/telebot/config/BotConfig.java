package org.telebot.config;

public class BotConfig {	
	
	private static final String URL_TEMPLATE = "https://api.telegram.org/bot{0}/{1}";
	private static final String TOKEN = "TOKEN";
	private static final String WEBHOOK = "WEBHOOK";
    
	public static String getUrlTemplate() {
		return URL_TEMPLATE;
	}
	public static String getToken() {
		return TOKEN;
	}
	public static String getWebhook() {
		return WEBHOOK;
	}
}
