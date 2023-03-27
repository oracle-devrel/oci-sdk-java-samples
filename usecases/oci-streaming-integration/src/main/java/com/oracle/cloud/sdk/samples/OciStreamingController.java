package com.oracle.cloud.sdk.samples;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.cloud.sdk.samples.StreamingService.Message;

@RestController
public class OciStreamingController {

	@Autowired
	StreamingService service;

	@RequestMapping(value = "/hello")
	public String hello() throws Exception {
		return "Hello World";
	}

	@RequestMapping(value = {
			"/publish" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String publishMessage(@RequestBody Message message) throws Exception {
		return service.publishMessage(message);
	}

	@RequestMapping(value = { "/retrieve" }, method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Message> getMessages() throws Exception {
		return service.readMessages();
	}
}
