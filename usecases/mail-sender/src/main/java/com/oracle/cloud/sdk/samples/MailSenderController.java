package com.oracle.cloud.sdk.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailSenderController {
	
	@Autowired
	MailSenderService service;
	
	@RequestMapping(value="/hello")
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping(value="/send", method = RequestMethod.GET)
	public String send() throws Exception{
		service.sendMail(new MailSenderService.MailContent("<YOUR_MAIL_ID>", "Hello from OCI SpringBoot", "Test Message"));
		return "Done";
	}
	
	@RequestMapping(
	    value = { "/send" }, 
	    method = RequestMethod.POST, 
	    produces = "application/json", 
	    consumes = "application/json")
	@ResponseBody
	public String sendPost(@RequestBody MailSenderService.MailContent content) throws Exception {
		service.sendMail(content);
		return "Done";
	}
}
