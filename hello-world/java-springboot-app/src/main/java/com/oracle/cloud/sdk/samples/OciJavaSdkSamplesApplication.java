package com.oracle.cloud.sdk.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@SpringBootApplication
public class OciJavaSdkSamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OciJavaSdkSamplesApplication.class, args);
	}

	@Configuration 
	public class JacksonConfiguration { 
	    public JacksonConfiguration(ObjectMapper objectMapper) { 
	        objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false)); 
	    } 
	}
}
