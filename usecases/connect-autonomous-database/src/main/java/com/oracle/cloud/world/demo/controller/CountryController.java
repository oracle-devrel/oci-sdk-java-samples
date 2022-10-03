package com.oracle.cloud.world.demo.controller;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.cloud.world.demo.model.Country;
import com.oracle.cloud.world.demo.repository.CountryRepository;  

@RestController  
public class CountryController   
{  
    @Autowired
    private CountryRepository ctrRepo;
    
    @RequestMapping("/api/countries")  
    public List<Country> reports() {  
    	List<Country> reports = ctrRepo.findAll();
    	return reports;
    }
}