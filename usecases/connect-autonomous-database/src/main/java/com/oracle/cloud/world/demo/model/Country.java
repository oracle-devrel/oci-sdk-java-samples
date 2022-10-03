package com.oracle.cloud.world.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "COUNTRIES")
public class Country {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="COUNTRIES_ID")
	public Integer id;
    
    @Column(name = "COUNTRIES_ISO")
	public String iso;
    
    @Column(name = "RAW_NAME")
	public String raw;
    
    @Column(name = "FORMATTED_NAME")
	public String formattedName;
    
    @Column(name = "COUNTRY_ISO3")
	public String iso3;
    
    @Column(name = "COUNTRY_CODE")
	public Integer countryCode;
    
    @Column(name = "CALLING_CODE")
	public Integer callingCode;
    
    @Column(name = "CREATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created;
}
