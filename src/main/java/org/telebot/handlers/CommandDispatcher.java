package org.telebot.handlers;

import org.telebot.commands.Commands;
import org.telebot.nixtabyte.jtelebot.exception.JsonParsingException;
import org.telebot.nixtabyte.jtelebot.requestfactory.TelegramRequestFactory;
import org.telebot.nixtabyte.jtelebot.response.json.Update;

public class CommandDispatcher {
    
    final static RequestHandler handler = new RequestHandler();
	protected String command;
	protected Update update;
	
	public CommandDispatcher() {}	
	
	public void processCommand(Update update)throws JsonParsingException{
        
        this.update = update;
        command = update.getMessage().getText();
        if (command != null){
        
        if (command.startsWith(Commands.commandInitChar)){  
            
            if (command.startsWith(Commands.startCommand))
            {               
                handler.executeRequest(
                        TelegramRequestFactory.createSendMessageRequest(
                                update.getMessage().getChat().getId(), "Start command", true, null, null));              
            } 
            else 
            {               
                handler.executeRequest(
                        TelegramRequestFactory.createSendMessageRequest(
                                update.getMessage().getChat().getId(), "That's not a valid command", true, null, null));              
            }
            
        } 
    }
    
	}
}
