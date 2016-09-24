package org.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.telebot.config.BotConfig;
import org.telebot.handlers.CommandDispatcher;
import org.telebot.handlers.RequestHandler;
import org.telebot.nixtabyte.jtelebot.exception.JsonParsingException;
import org.telebot.nixtabyte.jtelebot.requestfactory.TelegramRequestFactory;
import org.telebot.nixtabyte.jtelebot.response.json.Update;

@Controller
public class MainController {
		
	private String message = "Home Page";
	final static CommandDispatcher dispatcher = new CommandDispatcher();
	
	 @RequestMapping(value="/", method = RequestMethod.GET)
	    public String index(ModelMap model) throws JsonParsingException {
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss:SSSS");
		 String date = format.format(new Date());
		 model.addAttribute("message", this.message);
		 model.addAttribute("date",date);
	        return "index";
	    }
		
	 @RequestMapping(value="/setwebhook", method = RequestMethod.GET)
	    public String setWebHook(ModelMap model) {
		 message = "Controller for setWebHook method";
		 model.addAttribute("message", this.message);
		 setWebHook();
	        return "index";
	    }
	 
	 @RequestMapping(value="URL/WEBHOOK", method = RequestMethod.POST, consumes="application/json")
	 public ResponseEntity<String> request(@RequestBody Update update) throws IOException, JsonParsingException{ 		 
		 	dispatcher.processCommand(update);
		    return new ResponseEntity<String>(HttpStatus.OK) ;		      
	    }
	 
	 private void setWebHook(){				 
		new RequestHandler(TelegramRequestFactory.createSetWebhookRequest(BotConfig.getUrlTemplate()));		
	 }

}
