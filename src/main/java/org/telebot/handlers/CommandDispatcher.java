package org.telebot.handlers;

import org.telebot.commands.Commands;
import org.telebot.nixtabyte.jtelebot.exception.JsonParsingException;
import org.telebot.nixtabyte.jtelebot.requestfactory.TelegramRequestFactory;
import org.telebot.nixtabyte.jtelebot.response.json.Update;

public class CommandDispatcher {
	protected String command;
	protected Update update;
	
	public CommandDispatcher(Update update) throws JsonParsingException{
		
		this.update = update;
		command = update.getMessage().getText();
		
		if (command.startsWith(Commands.commandInitChar)){	
			
			if (command.startsWith(Commands.startCommand))
			{				
				new RequestHandler(
						TelegramRequestFactory.createSendMessageRequest(
								update.getMessage().getChat().getId(), "Start command", true, null, null)).executeRequest();				
			} 
			else 
			{				
				new RequestHandler(
						TelegramRequestFactory.createSendMessageRequest(
								update.getMessage().getChat().getId(), "That's not a valid command", true, null, null)).executeRequest();				
			}
			
		} 
	}	
}
