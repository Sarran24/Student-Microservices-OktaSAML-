package com.logging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logging.model.SomeEntity;

import ch.qos.logback.classic.Level;

@RestController
@RequestMapping("/log")
public class Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Controller.class);
	
	
	@PostMapping
	public String  loggerResponse(@RequestBody SomeEntity someEntity ) {
	    String msgType  = someEntity.getMessageType();
	    if(msgType.equalsIgnoreCase("debug")) {
	        String debugMsg =someEntity.getDescription() ;
	        logger.debug(debugMsg);
	        return debugMsg;
	    } else if(msgType.equalsIgnoreCase("info")) {
	        String infoMsg = someEntity.getDescription();
	        logger.info(infoMsg);
	        return infoMsg;
	    }else if(msgType.equals("error")) {
	    	String errorMsg = someEntity.getDescription();
	    	logger.error(errorMsg);
	    }else if(msgType.equalsIgnoreCase("warn")) {
	    	String warnMsg = someEntity.getDescription();
	    	logger.warn(warnMsg);
	    }
	    return " messageType should be info or debug or error or warn";
	}
	
}
